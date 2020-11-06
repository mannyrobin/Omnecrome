'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ParkDriversCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('ParkDriversCtrl', ['$scope', '$state', '$stateParams', 'ParkDriversService', 'GridService', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, ParkDriversService, GridService, isNotReadOnlyUser) {
			$scope.gridScope = $scope;
			$scope.gridScope.service = ParkDriversService;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Водители"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "versionStartDate",
				label: "Период актуальности",
				class: "col-md-2",
				get: function (row) {
					return row.versionStartDate + ' - ' + row.versionEndDate
				}
			}, {
				id: "surname",
				label: "ФИО",
				class: "col-md-8",
				href: function (row) {
					return "nsi/drivers/" + row.tsDriverId + "/info";
				},
				get: function (row) {
					var result = "";
					if (row.surname) {
						result += row.surname;
					}
					if (row.name) {
						result = result.trim() + ' ' + row.name;
					}
					if (row.patronumic) {
						result = result.trim() + ' ' + row.patronumic;
					}
					return result.trim();
				}
			}, {
				id: "personalNumber",
				label: "Табельный номер водителя",
				class: "col-md-2"
			}, {
				id: "createUser",
				label: "Кто и когда создал",
				class: "col-md-3",
				get: function (row) {
					return row.createUser + ' (' + row.createDate + ')'
				}
			}, {
				id: "updateUser",
				label: "Кто и когда изменил",
				class: "col-md-3",
				get: function (row) {
					return row.updateUser + ' (' + row.updateDate + ')'
				}
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "parkId",
				type: "text",
				defval: $stateParams.itemId,
				hide: true
			}];

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить водителя",
					removeConfirm: "Вы действительно хотите удалить водителя?"
				},
				check: {
					isRemoveEnable: function (row) {
						return row['active'] != undefined && row.active;
					}
				},
				gridScope: $scope.gridScope
			};

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "surname",
				gridName: $state.current.name + '.parkDriversGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/parks/ParkDriverAddDlg.html", "ParkDriverAddCtrl", "Добавление водителя к эксплуатационному филиалу", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении водителя", $scope.gridScope, $scope.gridScope.service.removeItem, true);

			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return !row.active ? 'history-record' : null;
			};
		}]);