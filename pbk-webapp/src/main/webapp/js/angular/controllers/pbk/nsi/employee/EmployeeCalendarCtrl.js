'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ContractsCtrl
 * @description
 *
 * Контроллер режима работы сотрудника.
 */
angular.module('armada')
	.controller('EmployeeCalendarCtrl', ['$scope', '$stateParams', 'EmployeeCalendarService', 'Notification', 'AppConfig', 'uiCalendarConfig', 'UtilService', 'PermissionService',
		function ($scope, $stateParams, EmployeeCalendarService, Notification, AppConfig, uiCalendarConfig, UtilService, PermissionService) {

			$scope.calendarColors = AppConfig.EMPLOYEE_CALENDAR_COLORS;

			$scope.calendarEvents = {
				color: 'rgba(0,0,0,0)',
				textColor: 'black',
				events: []
			};

			$scope.getCalendarForPeriod = function () {
				$scope.eventSources.splice(0, $scope.eventSources.length);
				EmployeeCalendarService.getCalendarForPeriod($stateParams.itemId, $scope.startDate, $scope.endDate).then(function (data) {
					if (data) {
						var events = [];

						//начало обработки полученных данных
						angular.forEach(data, function (value) {
							//если не установлена дата начала события, значит данные некорректны
							//так же проверяем существует ли переданный тип события
							if (value.startDate) {
								var color = getColor(value);
								//если дата окончания не установлена, значит событие на один день
								if (value.endDate == null && checkDate(value.startDate)) {
									setDayValue(value, color, value.startDate, events);
									//иначе на период
								} else {
									var endDate = (new Date(value.endDate)).getTime() + AppConfig.DAY_TIME_IN_MIL_SEC;
									var tmpDate = null;
									for (var i = (new Date(value.startDate)).getTime(); i < endDate; i += AppConfig.DAY_TIME_IN_MIL_SEC) {
										tmpDate = moment(new Date(i)).format('YYYY-MM-DD');
										if (checkDate(tmpDate)) {
											setDayValue(value, color, tmpDate, events);
										}
									}
								}
							}
						});
						$scope.calendarEvents.events = events;
						$scope.eventSources.push($scope.calendarEvents);
					}
					$scope.$broadcast('calendar-re-render');
				}).catch(function () {
					Notification.error("Ошибка при получении данных для календаря");
				})
			};

			$scope.eventSources = [];

			/* config object */
			$scope.uiConfig = {
				calendar: {
					firstDay: 1,
					height: "auto",
					editable: false,
					timeFormat: 'HH:mm',
					displayEventTime: false,
					header: {
						left: 'title',
						center: '',
						right: 'today prev,next'
					},
					viewRender: function (view, element) {
						$scope.startDate = moment(view.start).format('DD.MM.YYYY');
						$scope.endDate = moment(view.end).format('DD.MM.YYYY');
						$scope.getCalendarForPeriod();
					},
					monthNames: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'],
					monthNamesShort: ['Янв.', 'Фев.', 'Март', 'Апр.', 'Май', 'Июнь', 'Июль', 'Авг.', 'Сент.', 'Окт.', 'Ноя.', 'Дек.'],
					dayNames: ["Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"],
					dayNamesShort: ["ВС", "ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ"],
					buttonText: {
						today: "Сегодня",
						month: "Месяц",
						week: "Неделя",
						day: "День"
					}
				}
			};

			/**
			 * Получаем цвет смены для элемента.
			 * @param elem элемент
			 * @returns цвет смены
			 */
			function getColor(elem) {
				switch (elem.shiftType) {
					case 1:
						return AppConfig.EMPLOYEE_CALENDAR_COLORS.HOLIDAY;
					case 2:
						return AppConfig.EMPLOYEE_CALENDAR_COLORS.DAY;
					case 3:
						return AppConfig.EMPLOYEE_CALENDAR_COLORS.NIGHT;
					case 4:
						return AppConfig.EMPLOYEE_CALENDAR_COLORS.NOT_WORK;
					case 5:
						return AppConfig.EMPLOYEE_CALENDAR_COLORS.HOSPITAL;
					case 6:
						return AppConfig.EMPLOYEE_CALENDAR_COLORS.VACATION;
					default:
						return "";
				}
			}

			/**
			 * Получаем текстовое отображение текущей смены для элемента.
			 * @param elem элемент
			 * @returns текстовое отображение смены
			 */
			function getShiftTitle(elem) {
				switch (elem.shiftType) {
					case 1:
						return "Выходной";
					case 2:
						return "Дневная смена";
					case 3:
						return "Ночная смена";
					case 4:
						return "Не работает";
					case 5:
						return "Больничный";
					case 6:
						return "Отпуск";
					default:
						return "";
				}
			}

			/**
			 * Проверить дата на вхождение в текущий месяц.
			 * @param value дата
			 * @returns true, если дата входит в текущий месяц
			 */
			function checkDate(value) {
				var jDay = angular.element('.calendar').find('td[data-date="' + value + '"].fc-day');
				return !jDay.hasClass('fc-other-month');
			}

			function setDayValue(dayValue, color, date, events) {
				var jDay = angular.element('.calendar').find('td[data-date="' + date + '"].fc-day');
				jDay.css("background-color", color);
				events.push({title: getShiftTitle(dayValue), start: date});
				if (dayValue.workPlanHours != null) {
					events.push({
						title: "Плановое кол-во часов: " + dayValue.workPlanHours,
						start: new Date((new Date(date)).getTime() + AppConfig.DELTA_TIME)
					});
				}
				if (dayValue.workFactHours != null) {
					events.push({
						title: "Фактическое кол-во часов: " + dayValue.workFactHours,
						start: new Date((new Date(date)).getTime() + 2 * AppConfig.DELTA_TIME)
					});
				}
			}
		}]);
