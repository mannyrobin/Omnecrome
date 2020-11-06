'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TaskReportInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('TaskReportInfoCtrl', ['$controller', '$scope', '$stateParams', 'TaskReportsService', 'isNotReadOnlyUser', 'GkuopsService', 'EmployeesService',
		function ($controller, $scope, $stateParams, TaskReportsService, isNotReadOnlyUser, GkuopsService, EmployeesService) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			var taskId = $stateParams.taskId != null ? $stateParams.taskId : $stateParams.itemId;
			/* Поля ввода */
			$scope.fields = [{
				name: "ticketIssueCount",
				type: "text",
				label: "Количество полученных билетов для реализации",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "tsCheckCount",
				type: "text",
				label: "Количество проверенных единиц подвижного состава",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "ticketSoldCount",
				type: "text",
				label: "Количество реализованных билетов",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "plantStowawayCount",
				type: "text",
				label: "Высажено безбилетных пассажиров",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "deliveryOvdCount",
				type: "text",
				label: "Доставлено в ОВД",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "exemptSkmCount",
				type: "text",
				label: "Изъятые социальные карты москвича",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "exemptSkmoCount",
				type: "text",
				label: "Изъятые социальные карты жителя Московской области",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "exemptVesbCount",
				type: "text",
				label: "Изъятые временные единые социальные билеты",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "exemptOtherLpkCount",
				type: "text",
				label: "Изъятые прочие льготные персонифицированные карты",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "exemptValidLessCount",
				type: "text",
				label: "Нелегитимные",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "driverRaportCount",
				type: "text",
				label: "Количество рапортов на работу водителей",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "ordinance1000Count",
				type: "text",
				label: "Постановления 1000р",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "ordinance2500Count",
				type: "text",
				label: "Постановления 2500р",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "protocol1000Count",
				type: "text",
				label: "Протокол 1000р",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "protocol2500Count",
				type: "text",
				label: "Протокол 2500р",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "gkuopEmployees",
				type: "multiselect",
				load: function () {
					return GkuopsService.getList([
						{
							name: "taskId",
							value: taskId,
							type: "id"
						}
					]);
				},
				label: "Сотрудники ГКУ ОП ",
				required: false
			}, {
				name: "mgtEmployees",
				type: "multiselect",
				load: function () {
					return EmployeesService.getEmployeesOnDate([
						{
							name: "taskId",
							value: taskId,
							type: "id"
						}
					]);
				},
				label: "Сотрудники МГТ ",
				required: false
			}, {
				name: "routeCount",
				type: "text",
				hide: true
			}, {
				name: "bskCount",
				type: "text",
				hide: true
			}];

			$scope.select = function (event) {
				if (event.target) event.target.select();
			}

			$scope.$watch('fields[16].value', function (newVal, oldVal) {
				if ((oldVal == null && newVal != null && newVal.length > 0)
					|| (oldVal != null && newVal != null && newVal.length > oldVal.length)) {
					var ids = {}; // добавленные id 
					var newVals = {};
					angular.forEach(newVal, function (valN) {
						if (valN.countyId != null) {
							var b = true;
							if (oldVal != null) {
								angular.forEach(oldVal, function (valO) {
									if (valN == valO) {
										b = false;
									}
								});
							}
							if (b && ids[valN.countyId] == null) {
								ids[valN.countyId] = valN;
							}
						}
						newVals[valN.id] = valN;
					});
					var vals = [];
					angular.forEach($scope.fields[16].list, function (val) {
						if (ids[val.countyId] != null || newVals[val.id] != null) {
							vals.push(val);
						}
					});
					if (vals.length > 0)
						$scope.fields[16].value = vals;
				}
			});

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: taskId}, $scope.fields, {
				editItem: TaskReportsService.editItem,
				getItem: TaskReportsService.getItem
			});
		}]);
