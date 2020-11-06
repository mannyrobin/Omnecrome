'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DistrictRoutesCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('DistrictRoutesCtrl', ['$scope', '$state', '$stateParams', 'DistrictRoutesService', 'GridService', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, DistrictRoutesService, GridService, isNotReadOnlyUser) {
			$scope.gridScope = $scope;
			$scope.gridScope.service = DistrictRoutesService;

			/* Права */
			$scope.gridScope.perms = {
				//add: isNotReadOnlyUser,
				//remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Маршруты"
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
				id: "route",
				label: "Маршрут",
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
				name: "districtId",
				type: "text",
				defval: $stateParams.itemId,
				hide: true
			}];

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить маршрут",
					removeConfirm: "Вы действительно хотите удалить маршрут?"
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
				defaultPredicate: "route",
				gridName: $state.current.name + '.districtRoutesGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/districts/DistrictRouteAddDlg.html", "DistrictRouteAddCtrl", "Добавление маршрута к району Москвы", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении маршрута", $scope.gridScope, $scope.gridScope.service.removeItem, true);

			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return !row.active ? 'history-record' : null;
			};
		}]);