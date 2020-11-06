'use strict';

/**
 * @ngdoc function
 * @name armada.controller:VenueRoutesCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('VenueRoutesCtrl', ['$scope', '$state', '$stateParams', '$modal', 'VenueRoutesService', 'GridService', 'VenueRouteTypesService', 'Notification',
		function ($scope, $state, $stateParams, $modal, VenueRoutesService, GridService, VenueRouteTypesService, Notification) {
			$scope.gridScope = $scope;
			$scope.gridScope.service = VenueRoutesService;

			/* Права */
			$scope.gridScope.perms = {
				add: $scope.row.isNotReadOnlyUser,
				remove: $scope.row.isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Сопутствующие маршруты"
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
				id: "venueRouteType",
				label: "Тип маршрута",
				class: "col-md-2"
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
				name: "venueId",
				type: "text",
				defval: $stateParams.itemId,
				hide: true
			}, {
				name: "venueRouteTypeId",
				type: "select",
				placeholder: "Тип маршрута",
				load: VenueRouteTypesService.getList
			}, {
				name: "districtId",
				type: "id",
				defval: $scope.row.id,
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
				gridName: $state.current.name + '.VenueRoutesGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = function () {
				var modalInstance = $modal.open({
					templateUrl: "templates/dialogs/pbk/nsi/venues/venueRouteAddDlg.html",
					windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
					controller: "VenueRouteAddCtrl",
					size: "lg",
					resolve: {
						data: function () {
							return {
								title: "Добавление cопутствующего маршрута",
								id: null,
								item: $scope.row
							}
						}
					}
				});
				modalInstance.result.then(function () {
					$scope.gridScope.grid.refreshAction();
					$scope.row.clearMainMap();
				});
			};

			$scope.gridScope.removeRow = function (item) {
				var removeObj = {ids: item.id, tryDelete: true};
				$scope.gridScope.service.removeItem(removeObj).then(function () {
					$scope.gridScope.grid.refreshAction();
					$scope.row.clearMainMap();
					Notification.info("Запись успешно удалена.");
				}).catch(function (response) {
					var errMsg = "";
					if (response.status == 400) {
						var errors = UtilService.getFormErrors(response);
						errMsg = " Причина:" + errors.errMessages;
					}
					Notification.error("Удалить не удалось" + errMsg);
				});
			};

			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return !row.active ? 'history-record' : null;
			};
		}]);