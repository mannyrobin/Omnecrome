package ru.armd.pbk.utils.files;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import ru.armd.pbk.exceptions.PBKConnectionException;
import ru.armd.pbk.exceptions.PBKException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Фабрика фаловых провайдеров для доступа к сетевым ресурсам.
 */
public class FileProviderFactory {

   /**
	* Создание провайдера для доступа к файлам на удаленном сервере посредством ФТП.
	*
	* @param address  адрес ресурса.
	* @param login    логин.
	* @param password пароль.
	* @return фаловый провайдер.
	* @throws PBKException
	*/
   public static FileProvider constructFtpFileProvider(String address, String login, String password)
	   throws PBKException {
	  final FTPClient ftp = new FTPClient();
	  try {
		 ftp.connect(address);
		 ftp.setSoTimeout(60 * 60 * 1000);

		 if (!ftp.login(login, password)) {
			ftp.logout();
			throw new PBKException("Ошибка подключения к FTP серверу");
		 }
		 ftp.enterLocalPassiveMode();
		 ftp.setFileType(FTP.BINARY_FILE_TYPE);
		 int reply = ftp.getReplyCode();
		 if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			throw new PBKException("Ошибка работы с FTP сервером");
		 }
		 // ftp.setControlEncoding("UTF-8"); //не работает с сервером если на сервере не utf8

		 return new FileProvider() {

			@Override
			public void rename(String fileName, String newFileName)
				throws IOException {
			   ftp.rename(fileName, newFileName);
			}

			@Override
			public InputStream openFileStream(String name)
				throws IOException {
			   return ftp.retrieveFileStream(name);
			}

			@Override
			public void makeDirectory(String path)
				throws IOException {
			   ftp.makeDirectory(path);
			}

			@Override
			public File[] listFiles(String path)
				throws IOException {
			   FTPFile[] ftpFiles = ftp.listFiles(path);
			   File[] files = new File[ftpFiles.length];
			   for (int i = 0; i < ftpFiles.length; i++) {
				  final FTPFile ftpFile = ftpFiles[i];
				  files[i] = new File() {

					 @Override
					 public boolean isDirectory() {
						return ftpFile.isDirectory();
					 }

					 @Override
					 public String getName() {
						return ftpFile.getName();
					 }
				  };
			   }
			   return files;
			}

			@Override
			public void completeFileStream()
				throws IOException {
			   ftp.completePendingCommand();
			}

			@Override
			public void closeProvider()
				throws IOException {
			   if (ftp.isConnected()) {
				  ftp.logout();
				  ftp.disconnect();
			   }
			}
		 };
	  } catch (UnknownHostException ex) {
		 throw new PBKConnectionException("Неизвестный адрес хоста: " + address, ex);
	  } catch (ConnectException ex) {
		 throw new PBKConnectionException("Ошибка подключения к FTP серверу", ex);
	  } catch (IOException ex) {
		 throw new PBKException("Ошибка при работе с FTP", ex);
	  } catch (Exception ex) {
		 throw new PBKException("Ошибка создания файлового провайдера для FTP", ex);
	  }
   }

   /**
	* Создание провайдера для доступа к файлам из локальной сети.
	*
	* @param address адрес ресурса.
	* @return Файловый провайдер.
	* @throws PBKException
	*/
   public static FileProvider constructFileSystemProvider(String address)
	   throws PBKException {
	  java.io.File root = Paths.get(address).toFile();
	  if (root == null || !root.isDirectory()) {
		 throw new PBKConnectionException("Ошибка подключения к файловой системе в локальной сети");
	  }
	  final String prefix = address;
	  return new FileProvider() {

		 @Override
		 public void rename(String fileName, String newFileName)
			 throws IOException {
			Files.move(Paths.get(prefix + fileName), Paths.get(prefix + newFileName));
		 }

		 @Override
		 public InputStream openFileStream(String name)
			 throws FileNotFoundException {
			return new FileInputStream(Paths.get(prefix + name).toFile());
		 }

		 @Override
		 public void makeDirectory(String path)
			 throws IOException {
			Path p = Paths.get(prefix + path);
			if (Files.notExists(p)) {
			   Files.createDirectory(p);
			}
		 }

		 @Override
		 public File[] listFiles(String path) {
			java.io.File[] ioFiles = Paths.get(prefix + path).toFile().listFiles();
			File[] files = new File[ioFiles.length];
			for (int i = 0; i < ioFiles.length; i++) {
			   final java.io.File ioFile = ioFiles[i];
			   files[i] = new File() {

				  @Override
				  public boolean isDirectory() {
					 return ioFile.isDirectory();
				  }

				  @Override
				  public String getName() {
					 return ioFile.getName();
				  }
			   };
			}
			return files;
		 }

		 @Override
		 public void completeFileStream() {

		 }

		 @Override
		 public void closeProvider() {

		 }
	  };
   }
}
