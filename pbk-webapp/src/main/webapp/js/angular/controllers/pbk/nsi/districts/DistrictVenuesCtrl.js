'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DistrictVenuesCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('DistrictVenuesCtrl', ['$scope', '$state', '$stateParams', 'DistrictVenuesService', 'VenuesService', 'GridService', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, DistrictVenuesService, VenuesService, GridService, isNotReadOnlyUser) {
			$scope.gridScope = $scope;
			$scope.gridScope.service = DistrictVenuesService;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Места встреч"
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
				id: "name",
				label: "Название",
				class: "col-md-2"
			}, {
				id: "createUser",
				label: "Кто и когда создал",
				class: "col-md-2",
				get: function (row) {
					return row.createUser + ' (' + row.createDate + ')'
				}
			}, {
				id: "updateUser",
				label: "Кто и когда изменил",
				class: "col-md-2",
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
					removeTip: "Удалить место встречи",
					removeConfirm: "Вы действительно хотите удалить место встречи?"
				},
				check: {
					isRemoveEnable: function (row) {
						return row['active'] != undefined && row.active && !row.deleted;
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
				gridName: $state.current.name + '.DistrictVenuesGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/districts/DistrictVenueAddDlg.html", "DistrictVenueAddCtrl", "Добавление места встречи к району Москвы", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении места встречи", $scope.gridScope, VenuesService.removeDistrict, true);

			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return !row.active || row.deleted ? 'history-record' : null;
			};
		}]);