'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ContractsCtrl
 * @description
 *
 * Контроллер календаря
 */
angular.module('armada')
	.controller('CalendarCtrl', ['$scope', 'CalendarService', 'Notification', 'AppConfig', 'uiCalendarConfig', 'UtilService', 'PermissionService', 'isNotReadOnlyUser',
		function ($scope, CalendarService, Notification, AppConfig, uiCalendarConfig, UtilService, PermissionService, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			$scope.calendarColors = AppConfig.CALENDAR_COLORS;

			$scope.getCalendarForPeriod = function () {
				CalendarService.getCalendarForPeriod($scope.startDate, $scope.endDate).then(function (data) {
					if (data) {
						angular.forEach(data, function (value) {
							if (value.workDayTypeId <= $scope.calendarColors.length) {
								setColorToRow(value.workDate, $scope.calendarColors[value.workDayTypeId - 1]);
							}
						})
					}
					$scope.data = data;
					$scope.$broadcast('calendar-re-render');
				}).catch(function () {
					Notification.error("Ошибка при получении данных для календаря");
				})
			};

			$scope.fillCalendar = function () {
				var from = $scope.dateRange == null ? null : moment($scope.dateRange.fromDate).format('DD.MM.YYYY');
				var to = $scope.dateRange == null ? null : moment($scope.dateRange.toDate).format('DD.MM.YYYY');
				CalendarService.fillCalendarForPeriod(from, to).then(function () {
					$scope.getCalendarForPeriod();
				}).catch(function (response) {
					if (angular.isDefined(response.data) && angular.isDefined(response.data.notValid)) {
						$scope.errors = UtilService.getAllResponseErrors(response.data);
					} else {
						Notification.error("Ошибка при заполнении календаря на период");
					}
				});
			};

			$scope.eventSources = [];
			$scope.data = null;

			/* config object */
			$scope.uiConfig = {
				calendar: {
					firstDay: 1,
					height: 450,
					editable: isNotReadOnlyUser,
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

			if (isNotReadOnlyUser) {

				$scope.uiConfig.calendar.dayClick = function (date, jsEvent, view) {
					//if (PermissionService.hasPermission('ASDU_MANAGE_CALENDAR')){
					var currentDate = new Date();
					currentDate.setHours(0, 0, 0, 0);
					var dateInSec = new Date(date).getTime();
					if (currentDate.getTime() > dateInSec) {
						Notification.info('Нельзя создавать и редактировать задания на прошедшую дату.');
						return;
					}
					// TODO перенести работу с DOM-ом в директиву
					var elementSelector = $(this);
					var mDate = moment(date);
					changeMonthIfNotCurrent(elementSelector, mDate);

					var currentIndex = setCurrentIndexByColor(elementSelector);

					if (currentIndex != -1) {
						currentIndex = currentIndex == 3 ? 1 : ++currentIndex;
					}

					if (currentIndex == -1) {
						currentIndex = 2;
					}

					var clearDate = getClearDate(mDate).format('DD MMMM YYYY');
					CalendarService.setCalendarDay({
						code: clearDate,
						name: clearDate,
						workDate: getClearDate(mDate).format('DD.MM.YYYY'),
						workDayTypeId: currentIndex
					}).then(function () {
						$scope.errors = [];
						setColorByIndex(currentIndex, elementSelector);
					}).catch(function (response) {
						if (angular.isDefined(response.data) && angular.isDefined(response.data.notValid)) {
							$scope.errors = UtilService.getAllResponseErrors(response.data);
						} else {
							Notification.error("Ошибка при получении данных для календаря");
						}
					});
					//};

				};
			}

			function setCurrentIndexByColor(elementSelector) {
				switch (elementSelector.css('background-color')) {
					case $scope.calendarColors[0]:
						return 1;
					case $scope.calendarColors[1]:
						return 2;
					case $scope.calendarColors[2]:
						return 3;
					default:
						return -1;
				}
			}

			function setColorByIndex(currentIndex, elementSelector) {
				if (currentIndex <= $scope.calendarColors.length) {
					elementSelector.css('background-color', $scope.calendarColors[currentIndex - 1]);
				}
			}

			function getClearDate(mDate) {
				mDate.hours(0);
				mDate.minutes(0);

				var commonDate = moment("1970-01-01 00:00:00.000");
				commonDate.date(mDate.date());  // убираем время
				commonDate.month(mDate.month());
				commonDate.year(mDate.year());
				return commonDate;
			}

			function changeMonthIfNotCurrent(elementSelector, mDate) {
				if (elementSelector.hasClass('fc-other-month')) {
					if (mDate.date() < 15) {
						uiCalendarConfig.calendars.calendarInstance.fullCalendar('next');
					} else {
						uiCalendarConfig.calendars.calendarInstance.fullCalendar('prev');
					}
				}
			}

			/**
			 * Установка цвета ячейки календаря.
			 * @param selector дата
			 * @param color цвет
			 */
			function setColorToRow(selector, color) {
				var jDay = angular.element('.calendar').find('td[data-date="' + selector + '"].fc-day');
				if (!jDay.hasClass('fc-other-month')) {
					jDay.css("background-color", color);
				}
			}

		}]);
