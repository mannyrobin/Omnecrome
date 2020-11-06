'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DepartmentParksCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('DepartmentParksCtrl', ['$scope', '$state', '$stateParams', 'DepartmentParksService', 'GridService', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, DepartmentParksService, GridService, isNotReadOnlyUser) {
			$scope.gridScope = $scope;
			$scope.gridScope.service = DepartmentParksService;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Парки",
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "rnum",
				label: "№",
				disableSort: true,
				index: true
			}, {
				id: "versionStartDate",
				label: "Период актуальности",
				class: "col-md-2",
				get: function (row) {
					return row.versionStartDate + ' - ' + row.versionEndDate
				}
			}, {
				id: "name",
				label: "Название",
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
				name: "departmentId",
				type: "text",
				defval: $stateParams.itemId,
				hide: true
			}];

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить парк",
					removeConfirm: "Вы действительно хотите удалить парк?"
				},
				check: {
					isRemoveEnable: function (row) {
						return row['delete'] != undefined && !row.delete;
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
				defaultPredicate: "name",
				gridName: $state.current.name + '.departmentParksGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/department/DepartmentParkAddDlg.html", "DepartmentParkAddCtrl", "Добавление парка к подразделению", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении парка", $scope.gridScope, $scope.gridScope.service.removeItem);
			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.delete == true ? 'remove-text-record' : null;
			};

			$scope.gridScope.getRowClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.delete ? 'remove-record' : null;
			};
		}]);