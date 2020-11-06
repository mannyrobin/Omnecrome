/**
 * @ngdoc function
 * @name armada.controller:
 * @description
 *
 * Контроллер модального окна создания пользователя сотрудника.
 */
angular.module('armada')
	.controller('RolePermissionAddCtrl', ['$controller', '$scope', '$stateParams', '$modalInstance', 'data', 'RolesPermissonService',
		function ($controller, $scope, $stateParams, $modalInstance, data, RolesPermissonService) {


			/* Поля ввода */
			$scope.fields = [
				{
					name: "permId",
					type: "select",
					load: function () {
						return RolesPermissonService.getList([{
							name: "roleId",
							value: $stateParams.itemId,
							type: "id"
						}
						]);
					},
					label: "Права",
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			data.item = {roleId: $stateParams.itemId};
			$scope.init(data, $scope.fields, {addItem: RolesPermissonService.addItem}, $modalInstance);
		}]);