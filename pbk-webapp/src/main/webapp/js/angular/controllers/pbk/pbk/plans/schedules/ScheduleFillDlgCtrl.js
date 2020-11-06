angular.module('armada')
	.controller('ScheduleFillDlgCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'PlanSchedulesService',
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
							}
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
					required: true,
					default: data.date,
					onChange: function () {
						$scope.fields[5].refresh();
						$scope.item.planDate = $scope.fields[2].value;
					}
				},
				{
					name: "dateTo",
					type: "date",
					label: "Окончание",
					required: true,
					default: data.date,
					onChange: function () {
						$scope.fields[5].refresh();
					}
				},
				{
					name: "isForNextDays",
					type: "yesno",
					label: "Пересчитать смены для последующих дней",
					required: false,
					default: true
				}, {
					name: "employeeId",
					required: true,
					label: "Сотрудник",
					type: "select",
					load: function () {
						return PlanSchedulesService.getEmployeesWithoutSchedule([{
							name: "dateFrom",
							value: $scope.fields[2].value,
							type: "date"
						}, {
							name: "dateTo",
							value: $scope.fields[3].value,
							type: "date"
						}, {
							name: "deptId",
							type: "text",
							value: data.item.deptId
						}])
					}
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, PlanSchedulesService, $modalInstance);

			$scope.$watch(function () {
				return $scope.item;
			}, function (newValue, oldValue) {
				if (newValue != null) {
					$scope.fields[3].value = DateTimeService.addDays(data.date, newValue.settingShiftDay);
				}
			});

		}]);
    