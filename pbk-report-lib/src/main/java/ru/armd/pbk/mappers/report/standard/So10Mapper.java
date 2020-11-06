package ru.armd.pbk.mappers.report.standard;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;

/**
 * Маппер стандартного отчёта "Сводная форма по эффективности работы контролеров".
 */
@IsMapper
public interface So10Mapper
	extends IDomainMapper<BaseDomain> {

   /**
	* Получить первую строку отчета.
	*
	* @param <View>   Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @param params   параметры.
	* @return Список view объектов.
	*/
   <View extends BaseGridView, Params extends BaseGridViewParams> View getFirstRow(Params params);

   /**
	* Получить вторую строку отчета.
	*
	* @param <View>   Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @param params   параметры.
	* @return Список view объектов.
	*/
   <View extends BaseGridView, Params extends BaseGridViewParams> View getSecondRow(Params params);

   /**
	* Получить третью строку отчета.
	*
	* @param <View>   Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @param params   параметры.
	* @return Список view объектов.
	*/
   <View extends BaseGridView, Params extends BaseGridViewParams> View getThridRow(Params params);

   /**
	* Получить четвертую строку отчета.
	*
	* @param <View>   Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @param params   параметры.
	* @return Список view объектов.
	*/
   <View extends BaseGridView, Params extends BaseGridViewParams> View getFourthRow(Params params);

   /**
	* Получить пятую строку отчета.
	*
	* @param <View>   Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @param params   параметры.
	* @return Список view объектов.
	*/
   <View extends BaseGridView, Params extends BaseGridViewParams> View getFifthRow(Params params);

   /**
	* Получить шестую строку отчета.
	*
	* @param <View>   Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @param params   параметры.
	* @return Список view объектов.
	*/
   <View extends BaseGridView, Params extends BaseGridViewParams> View getSixthRow(Params params);

   /**
	* Получить вторую строку отчета с ВЕСБ.
	*
	* @param <View>   Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @param params   параметры.
	* @return Список view объектов.
	*/
   <View extends BaseGridView, Params extends BaseGridViewParams> View getSecondRowWithVesb(Params params);

   /**
	* Получить третью строку отчета с ВЕСБ.
	*
	* @param <View>   Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @param params   параметры.
	* @return Список view объектов.
	*/
   <View extends BaseGridView, Params extends BaseGridViewParams> View getThridRowWithVesb(Params params);

   /**
	* Получить четвертую строку отчета с ВЕСБ.
	*
	* @param <View>   Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @param params   параметры.
	* @return Список view объектов.
	*/
   <View extends BaseGridView, Params extends BaseGridViewParams> View getFourthRowWithVesb(Params params);
}
