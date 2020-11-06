angular.module('armada')
	.controller('RolePermissionEditCtrl', ['$scope', 'RolesPermissonService', 'GridService', '$state',
		function ($scope, RolesPermissonService, GridService, $state) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = RolesPermissonService;

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-6"
			}, {
				id: "canRead",
				label: "Просмотр",
				class: "col-md-2",
				type: 'checkbox'
			}, {
				id: "canUpdate",
				label: "Управление",
				class: "col-md-2",
				type: 'checkbox'
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "roleId",
				type: "text",
				defval: $scope.row.roleId,
				hide: true
			}, {
				name: "moduleId",
				type: "id",
				defval: $scope.row.id,
				hide: true
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.rolesPermissonGrid',
				load: $scope.gridScope.service.getPage
			});

			$scope.send = function (check, id) {
				if (check == 1) {
					RolesPermissonService.removePermissons({id: $scope.row.roleId, ids: id}).then(function () {
						$scope.grid.isLoading = false;
						check = 0;
					});
				} else {
					RolesPermissonService.addPermissons({id: $scope.row.roleId, ids: id}).then(function () {
						$scope.grid.isLoading = false;
						check = 1;
					});
				}
			}

			$scope.clickCheckbox = function (row, index) {
				if (!$scope.grid.isLoading) {
					$scope.grid.isLoading = true;
				} else {
					return;
				}
				if ($scope.gridScope.headers[index].id == "canRead") {
					if (row.canRead == 1) {
						RolesPermissonService.removePermissons({
							id: $scope.row.roleId,
							ids: row.readPermId
						}).then(function () {
							$scope.grid.isLoading = false;
							row.canRead = 0;
						});
					} else {
						RolesPermissonService.addPermissons({
							id: $scope.row.roleId,
							ids: row.readPermId
						}).then(function () {
							$scope.grid.isLoading = false;
							row.canRead = 1;
						});
					}
				}
				if ($scope.gridScope.headers[index].id == "canUpdate") {
					if (row.canUpdate == 1) {
						RolesPermissonService.removePermissons({
							id: $scope.row.roleId,
							ids: row.updatePermId
						}).then(function () {
							$scope.grid.isLoading = false;
							row.canUpdate = 0;
						});
					} else {
						RolesPermissonService.addPermissons({
							id: $scope.row.roleId,
							ids: row.updatePermId
						}).then(function () {
							$scope.grid.isLoading = false;
							row.canUpdate = 1;
						});
					}
				}
			}

			$scope.checkedCheckbox = function (row, index) {
				if ($scope.gridScope.headers[index].id == "canRead") {
					return row.canRead == 1;
				}
				if ($scope.gridScope.headers[index].id == "canUpdate") {
					return row.canUpdate == 1;
				}
			}

			$scope.disabledCheckbox = function (row, index) {
				if ($scope.gridScope.headers[index].id == "canRead") {
					return row.canReadChange == 0;
				}
				if ($scope.gridScope.headers[index].id == "canUpdate") {
					return row.canUpdateChange == 0;
				}
			}
		}]);
