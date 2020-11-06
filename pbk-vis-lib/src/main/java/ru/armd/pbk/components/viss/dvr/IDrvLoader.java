package ru.armd.pbk.components.viss.dvr;

import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;

/**
 * Интерфейс для загрузчиков ВИС ДОЗОР ПРО.
 */
public interface IDrvLoader {

   /**
	* Загрузить данные из входного потока.
	*
	* @param inputStream - входной поток.
	* @return
	*/
   ImportResult<?> importFile(InputStream inputStream);

}
