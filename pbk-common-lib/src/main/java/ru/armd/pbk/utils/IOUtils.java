package ru.armd.pbk.utils;

import org.apache.log4j.Logger;
import ru.armd.pbk.utils.date.DateUtils;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Утилиты для работы с вводом/выводом.
 */
public class IOUtils {

   protected static final Logger LOG = Logger.getLogger(IOUtils.class);

   private static final int BUFFER_SIZE = 4096;

   /**
	* Копирует содержимое входного потока в выходной поток.
	*
	* @param input  Входной поток.
	* @param output Выходной поток.
	* @param close  Если <code>true</code>, закрывает оба потока по окончании операции.
	* @throws IOException Если возникла ошибка ввода-вывода при копировании.
	*/
   public static void copyStream(InputStream input, OutputStream output, boolean close)
	   throws IOException {
	  if (input == null
		  || output == null) {
		 return;
	  }

	  byte[] buffer = new byte[BUFFER_SIZE];
	  int count;
	  while ((count = input.read(buffer)) != -1) {
		 output.write(buffer, 0, count);
	  }

	  if (close) {
		 input.close();
		 output.close();
	  }
   }

   public static void copyStream(InputStream input, OutputStream output, long inputSize, long start, long length, int bufferSize)
	   throws IOException {
	  if (input == null
		  || output == null) {
		 return;
	  }
	  byte[] buffer = new byte[bufferSize];
	  int read;
	  if (inputSize == length) {
		 while ((read = input.read(buffer)) > 0) {
			output.write(buffer, 0, read);
			output.flush();
		 }
	  } else {
		 input.skip(start);
		 long toRead = length;

		 while ((read = input.read(buffer)) > 0) {
			if ((toRead -= read) > 0) {
			   output.write(buffer, 0, read);
			   output.flush();
			} else {
			   output.write(buffer, 0, (int) toRead + read);
			   output.flush();
			   break;
			}
		 }
	  }
   }

   public static void copyStream(InputStream input, OutputStream output, long inputSize, long start, long length)
	   throws IOException {
	  copyStream(input, output, inputSize, start, length, BUFFER_SIZE);
   }

   /**
	* Копирует содержимое входного потока в выходной поток и закрывает оба потока.
	*
	* @param input  Входной поток.
	* @param output Выходной поток.
	* @throws IOException Если возникла ошибка ввода-вывода при копировании.
	*/
   public static void copyStream(InputStream input, OutputStream output)
	   throws IOException {
	  copyStream(input, output, true);
   }

   /**
	* Создаёт zip-архив из файлов.
	*
	* @param files Соответствие между файлами и именами.
	* @return Байтовый поток с архивом.
	* @throws IOException
	*/
   public static ByteArrayOutputStream zipFiles(Map<String, File> files)
	   throws IOException {
	  if (files == null) {
		 return null;
	  }

	  ByteArrayOutputStream out = new ByteArrayOutputStream();
	  ZipOutputStream zipOut = new ZipOutputStream(out);

	  for (Entry<String, File> entry : files.entrySet()) {
		 zipOut.putNextEntry(new ZipEntry(entry.getKey()));

		 FileInputStream in = new FileInputStream(entry.getValue());
		 copyStream(in, zipOut, false);
		 in.close();
	  }

	  zipOut.close();

	  return out;
   }


   /**
	* Создаёт zip-архив из стримов.
	*
	* @param streams Соответствие между stream и именами.
	* @return Байтовый поток с архивом.
	* @throws IOException
	*/
   public static ByteArrayOutputStream zipStreams(Map<String, InputStream> streams)
	   throws IOException {
	  if (streams == null) {
		 return null;
	  }

	  ByteArrayOutputStream out = new ByteArrayOutputStream();
	  ZipOutputStream zipOut = new ZipOutputStream(out);

	  for (Entry<String, InputStream> entry : streams.entrySet()) {
		 zipOut.putNextEntry(new ZipEntry(entry.getKey()));
		 copyStream(entry.getValue(), zipOut, false);
	  }

	  zipOut.close();

	  return out;
   }

   /**
	* Переобразование входного потока в массив(byte).
	*
	* @param input Входной поток.
	* @param close Если <code>true</code>, закрывает выходной поток после записи.
	* @throws IOException Если возникла ошибка ввода-вывода при копировании.
	*/
   public static byte[] toByteArray(InputStream input, boolean close)
	   throws IOException {
	  if (input == null) {
		 return null;
	  }
	  ByteArrayOutputStream output = new ByteArrayOutputStream();

	  byte[] buffer = new byte[BUFFER_SIZE];
	  int count;
	  while ((count = input.read(buffer)) != -1) {
		 output.write(buffer, 0, count);
	  }

	  if (close) {
		 output.close();
	  }
	  output.flush();
	  return output.toByteArray();
   }


   /**
	* Метод добавляет к изображению content дату date в формате dd.MM.yyyy HH:Mi в правый нижний угол.
	*
	* @param content    Изображение.
	* @param formatName Формат файла: jpg, png и т.п.
	* @param date       Дата для добавления.
	* @return Изображение с добавленной датой.
	*/
   public static byte[] appendDateToImage(byte[] content, String formatName, Date date) {
	  ByteArrayInputStream bais = new ByteArrayInputStream(content);
	  ByteArrayOutputStream baos = new ByteArrayOutputStream();
	  BufferedImage image;
	  byte[] result = null;
	  try {
		 image = ImageIO.read(bais);
		 int fontSize = 10;
		 String text = DateUtils.dateToString(date) + " " + DateUtils.makeGeneralTimeFormat(date);

		 int sizeDelta = image.getHeight() / 500;
		 fontSize = (sizeDelta > 0) ? fontSize * sizeDelta : fontSize;
		 int x = image.getWidth() - 100 * sizeDelta;
		 int y = image.getHeight() - 10 * sizeDelta;

		 Graphics g = image.getGraphics();
		 g.setColor(Color.red);
		 g.setFont(new Font(g.getFont().getFontName(), g.getFont().getStyle(), fontSize));
		 g.drawString(text, x, y);
		 g.dispose();

		 ImageIO.write(image, formatName, baos);
		 baos.flush();
		 result = baos.toByteArray();
	  } catch (IOException e) {
		 LOG.error(e);
	  } finally {
		 try {
			if (bais != null) {
			   bais.close();
			}
			if (baos != null) {
			   baos.close();
			}
		 } catch (IOException e) {
			LOG.error(e);
		 }
	  }
	  return result;
   }
}
