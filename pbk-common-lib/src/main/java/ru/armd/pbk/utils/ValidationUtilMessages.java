package ru.armd.pbk.utils;

/**
 * Класс для валидационных сообщений.
 */
public class ValidationUtilMessages {

   public static final String INCORRECT_SHIFT_WORK_PERIOD = "Время начала работы должно быть меньше времени окончания.";
   public static final String INCORRECT_SHIFT_BREAK_PERIOD = "Время начала перерыва должно быть меньше времени окончания.";
   public static final String INCORRECT_DUTY_WORK_PERIOD = "Время начала работы должно быть меньше времени окончания.";
   public static final String INCORRECT_CALENDAR_DATE = "Невозможно обновить календарь за прошедшую дату.";
   public static final String INCORRECT_PASSWORD = "Пароль должен совпадать с подтверждением";
   public static final String INCORRECT_VENICLE_USE_PERIOD = "Дата начала периода использования должна быть раньше даты окончания.";
   public static final String BSO_NUMBER_RULE_ALREADY_EXISTS = "Правило формирования номера \"%s\" для подразделения \"%s\" уже существует.";
   public static final String BSO_NUMBER_RULE_VERY_BIG = "Число символов в поле \"Текущее значение\" не должно превышать \"%s\" символов.";
   public static final String BSO_NUMBER_RULE_INC_LESS = "Значение в поле \"Текущее значение\" должно быть больше предыдущего значения. (%d)";
   public static final String INCORRECT_DRIVER_ASDU_ID = "Значение поля \"ID в ВИС АСДУ\" должно быть уникальным.";
   public static final String INCORRECT_PLAN_PERIOD = "Дата начала действия должна быть раньше даты окончания.";
   public static final String INCORRECT_COD = "Значение поля \"Код\" должно быть уникальным.";
   public static final String INCORRECT_GKUOP_PERSONAL_NUMBER = "Значение поля \"Табельный номер\" должно быть уникальным.";
   public static final String INCORRECT_PARK_ASDU_ID = "Значение поля \"ID парка в ВИС АСДУ\" должно быть уникальным.";
   public static final String INCORRECT_TS_VIS_ASDU_ID = "Значение поля \"ID ТС в ВИС АСДУ\" должно быть уникальным.";
   public static final String INCORRECT_PLAN = "Комбинация значений полей \"Подразделение\", \"Дата начала действия\", \"Дата окончания действия\" должна быть уникальной";
   public static final String INCORRECT_EQUIPMENT_ASDU_ID = "Значение поля \"ID бортового оборудования в ВИС АСДУ\" должно быть уникальным.";
   public static final String INCORRECT_DVR_NUMBER = "Значение поля \"Номер видеорегистратора\" должно быть уникальным.";
   public static final String INCORRECT_EASU_FHD_ID = "Значение поля \"ID в ЕАСУ ФХД\" должно быть уникальным.";
   public static final String INCORRECT_PUSK_NUMBER = "Значение поля \"Номер ПУсК\" должно быть уникальным.";
   public static final String INCORRECT_CARD_NUMBER = "Значение поля \"Номер карты\" должно быть уникальным.";
   public static final String INCORRECT_BONUS_PERIOD = "Периоды премирования не должны пересекаться.";
   public static final String INCORRECT_BONUS_PERIOD_START_END = "Дата начала периода премирования не должна быть позднее, чем дата окончания.";
   public static final String INCORRECT_BONUS_FROM_TO_OTHER = "Диапазоны премирования \"От\" - \"До\" не должны пересекаться  другими диапазонами этого премирования.";
   public static final String INCORRECT_BONUS_FROM_TO = "Значение поля \"От\" не должен превышать значение поля \"До\".";

   public static final String INCORRECT_ROUTE_ASDU_ID = "Значение поля \"ID Маршрута в ВИС ASDU\" должно быть уникальным";
   public static final String INCORRECT_ROUTE_ASKP_COD = "Значение поля \"Код Маршрута в ВИС ASKP\" должно быть уникальным";
   public static final String INCORRECT_ROUTE_ASMPP_COD = "Значение поля \"Код Маршрута в ВИС ASMPP\" должно быть уникальным";
   public static final String INCORRECT_VENUE_ROUTE = "Данное сочетание Маршрут-Тип маршрута уже существует";
   public static final String INCORRECT_SCHEDULE_PERIOD = "Время начала периода должно быть меньше времени окончания.";
   public static final String INCORRECT_BRIGADE_PERIOD = "Время начала периода должно быть меньше времени окончания.";

   public static final String INCORRECT_SHOW_DELETE_SETTING = "Значение настройки \"Отображение удаленных записей\" должно быть либо \"Да\", либо \"Нет\".";
   public static final String INCORRECT_CLEAN_TIME_LOGS = "Значение настройки \"Время хранения логов аудита\" должно быть целочисленным.";
   public static final String INCORRECT_CLEAN_TIME_LOGS_MORE = "Значение настройки \"Время хранения логов аудита\" должно быть больше или равно нуля.";
   public static final String INCORRECT_PLAN_PERIOD_SETTING = "Значение настройки \"Количество дней пересчета смены сотрудника\" должно быть больше нуля.";
}
