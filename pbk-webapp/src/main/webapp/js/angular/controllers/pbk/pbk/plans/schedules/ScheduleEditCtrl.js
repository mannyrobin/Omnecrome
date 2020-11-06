angular.module('armada')
	.controller('ScheduleEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'PlanSchedulesService',
		'ShiftsService', 'AppConfig', 'DateTimeService',
		function ($controller, $scope, $modalInstance, data, PlanSchedulesService, ShiftsService, AppConfig, DateTimeService) {


			/* Поля ввода */
			$scope.fields = [
				{
					name: "shiftId",
					type: "select",
					label: "Смена",
					load: function () {
						return ShiftsService.getList([
							{
								name: "currentId",
								value: $scope.fields[0].value,
								type: "select"
							}/*,
							 {
							 name: "notHoliday",
							 value: 1,
							 type: "id"
							 }*/
						]);
					},
					required: true
				},
				{
					name: "shiftModeId",
					type: "select",
					label: "Выходные",
					load: PlanSchedulesService.getHolidayShifts,
					required: true
				},
				{
					name: "dateFrom",
					type: "date",
					label: "Начало",
					required: false,
					default: data.date
				},
				{
					name: "dateTo",
					type: "date",
					label: "Окончание",
					required: false,
					default: data.date
				},
				{
					name: "isForNextDays",
					type: "yesno",
					label: "Пересчитать смены для последующих дней",
					required: false,
					onChange: function () {
						$scope.fields[5].value = $scope.fields[4].value.id != 1;
					}
				},
				{
					name: "isForCurrentDay",
					type: "text"
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.fields[4].list = [{id: 1, name: 'Да'}, {id: 0, name: 'Нет'}];
			$scope.fields[4].value = {id: 0, name: 'Нет'};
			$scope.init(data, $scope.fields, PlanSchedulesService, $modalInstance);

			$scope.$watch(function () {
				return $scope.item;
			}, function (newValue, oldValue) {
				if (newValue != null) {
					$scope.fields[3].value = DateTimeService.addDays(data.date, newValue.settingShiftDay);
				}
			});

		}]);