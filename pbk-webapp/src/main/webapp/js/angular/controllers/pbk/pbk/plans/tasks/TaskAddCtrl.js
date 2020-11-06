angular.module('armada')
	.controller('TaskAddCtrl', ['$controller', '$scope', '$modalInstance', '$stateParams', 'data', 'TasksService', 'EmployeesService', 'ShiftsService', 'VenuesService', 'BsosService',
		'DepartmentsService', 'PlanSchedulesService', 'PlanBrigadesService', 'TaskStatesService', 'TaskTypesService', 'RoutesService', 'AppConfig',
		function ($controller, $scope, $modalInstance, $stateParams, data, TasksService, EmployeesService, ShiftsService, VenuesService, BsosService, DepartmentsService,
				  PlanSchedulesService, PlanBrigadesService, TaskStatesService, TaskTypesService, RoutesService, AppConfig) {

			var isCurrentId = true;
			var routesList = [];
			var oldState = data.item.stateId;
			var isSetRouteValue = false;
			$scope.isNight = false;

			function fillDefVal() {
				if ($scope.fields[10].value == null || $scope.fields[10].value.length == 0) {
					$scope.fields[10].value = [{id: -1, name: "Все маршруты МГТ"}];
				} else if ($scope.fields[10].value.length == 2 && $scope.fields[10].value[0].id == -1) {
					$scope.fields[10].value.shift();
				}
			}

			/* Поля ввода */
			$scope.fields = [
				{
					name: "taskDate",
					type: "date",
					default: new Date(),
					label: "Дата выполнения",
					required: true,
					onChange: function () {
						$scope.fields[3].refresh();
						$scope.fields[4].refresh();
						$scope.fields[6].value = "";
						$scope.fields[7].value = "";
						$scope.fields[8].value = "";
						$scope.fields[9].value = "";
						$scope.fields[10].refresh();
					}
				}, {
					name: "taskTypeId",
					type: "select",
					default: data.item.taskTypeId,
					load: TaskTypesService.getList,
					readonly: true,
					label: "Тип задания",
					required: false
				}, {
					name: "deptId",
					type: "select",
					load: function () {
						return DepartmentsService.getList([
							{
								name: "currentId",
								value: $scope.fields[2].value,
								type: "select"
							},
							{
								name: "forPlanUse",
								value: 1,
								type: "id"
							}
						]);
					},
					label: "Подразделение",
					required: true,
					onChange: function () {
						$scope.fields[3].refresh();
						$scope.fields[4].refresh();
						$scope.fields[6].value = "";
						$scope.fields[7].value = "";
						$scope.fields[8].value = "";
						$scope.fields[9].value = "";
						$scope.fields[10].refresh();
					}
				}, {
					name: "planScheduleId",
					type: "select",
					load: function () {
						return PlanSchedulesService.getSelectItemsForCreatingTask([
							{
								name: "deptId",
								value: $scope.fields[2].value,
								type: "select"
							},
							{
								name: "workDate",
								value: $scope.fields[0].value,
								type: "date"
							}
						]);
					},
					label: "Сотрудник",
					required: true,
					onChange: function () {
						isCurrentId = false;
						if ($scope.fields[3].value != null && $scope.fields[3].value.id != null) {
							return ShiftsService.getShiftByScheduleId($scope.fields[3].value.id).then(function (item) {
								$scope.fields[6].value = item.workStartTime;
								$scope.fields[7].value = item.workEndTime;
								$scope.fields[8].value = item.breakStartTime;
								$scope.fields[9].value = item.breakEndTime;
								$scope.isNight = item.id == 3;
								return DepartmentsService.getDepartmentByScheduleId($scope.fields[3].value.id).then(function (item) {
									$scope.fields[2].value = {id: item.id};
									$scope.matchSelect($scope.fields[2]);
									$scope.fields[10].refresh();
									$scope.fields[4].refresh();
								});
							});

						}
					}
				}, {
					name: "bsoId",
					type: "select",
					chooseItemAfterLoad: true,
					load: function () {
						var employeeScheduleId = $scope.fields[3].value && $scope.fields[3].value.id ? $scope.fields[3].value.id : null;
						return BsosService.getBsosForSchedule(null, employeeScheduleId, isCurrentId && $scope.fields[4].value ? $scope.fields[4].value.id : null);
					},
					label: "Номер БСО",
					required: true
				}, {
					name: "stateId",
					type: "select",
					default: data.item.stateId,
					load: TaskStatesService.getList,
					label: "Статус задания",
					required: true,
					onChange: function () {
						if (oldState != 6 && $scope.fields[5].value.id == 6) {
							routesList = [];
							$scope.fields[10].refresh();
						}
						oldState = $scope.fields[5].value.id;
					}
				}, {
					name: "workStartTime",
					type: "time",
					label: "Начало работы",
					required: true
				}, {
					name: "workEndTime",
					type: "time",
					label: "Окончание работы",
					required: true
				}, {
					name: "breakStartTime",
					type: "time",
					label: "Начало перерыва",
					required: true
				}, {
					name: "breakEndTime",
					type: "time",
					label: "Окончание перерыва",
					required: true
				}, {
					name: "routes",
					type: "multiselect",
					load: function () {
						return $scope.isNight ? RoutesService.getNightRoutesForTask([{
							name: "currentIds",
							value: $scope.fields[10].value,
							type: "multiselect"
						}]) : RoutesService.getSelectItemsForTask([
							{
								name: "deptId",
								value: $scope.fields[2].value,
								type: "select"
							}, {
								name: "workDate",
								value: $scope.fields[0].value,
								type: "date"
							}, {
								name: "currentIds",
								value: $scope.fields[10].value,
								type: "multiselect"
							}
						])
					},
					afterLoad: function () {
						fillDefVal();
						if ($scope.oldValues[10] != undefined && $scope.oldValues[10].length == 0) {
							$scope.oldValues[10] = [{id: -1, name: "Все маршруты МГТ"}];
						}

					},
					label: "Маршруты",
					required: false,
					onChange: fillDefVal
				}, {
					name: "factHours",
					type: "number",
					max: 24,
					min: 0,
					label: "Фактически отработано часов",
					required: false
				}, {
					name: "specialMark",
					type: "textarea",
					label: "Особые отметки",
					maxLength: AppConfig.XX_SHORT_INPUT_SIZE,
					required: false
				}, {
					name: "specialTask",
					type: "textarea",
					label: "Особое задание",
					maxLength: AppConfig.XX_SHORT_INPUT_SIZE,
					required: false
				}];

			$scope.fillRoutesList = function () {
				if (routesList == null || routesList.length == 0 || ($scope.fields[10].value != null && $scope.fields[10].value.length == 1 && $scope.fields[10].value[0].id == -1 && !isSetRouteValue)) {
					routesList = $scope.fields[10].list;
				}
				var sels = $scope.fields[10].value;
				var list = [];
				if (sels == undefined || sels.length == 0) {
					list = routesList;
				} else {
					var countyId;
					angular.forEach(routesList, function (r) {
						if (r.id == sels[0].id) {
							countyId = sels[0].countyId;
							return false;
						}
					});
					if (countyId == null) {
						list = routesList;
					} else {
						angular.forEach(routesList, function (r) {
							if (r.countyId == countyId) {
								list.push(r);
							}
						});
					}
				}
				$scope.fields[10].list = list;
				isSetRouteValue = $scope.fields[10].value != null && ($scope.fields[10].value.length > 1 || $scope.fields[10].value.length == 1 && $scope.fields[10].value[0].id != -1);
			};
			var watchRouteList = $scope.$watch(function () {
				return $scope.fields[10].list != undefined ? $scope.fields[10].list : undefined;
			}, function (newValue, oldValue) {
				if (routesList == null || routesList.length == 0) {
					routesList = $scope.fields[10].list;
					$scope.fillRoutesList();
				} else {
					watchRouteList();
				}
			});
			$scope.$watch(function () {
				return $scope.fields[10].value != undefined ? $scope.fields[10].value : undefined;
			}, function (newValue, oldValue) {
				$scope.fillRoutesList();
			});

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, TasksService, $modalInstance);
		}]);