angular.module('armada')
	.controller('UserRoleAddCtrl', ['$controller', '$scope', '$stateParams', '$modalInstance', 'data', 'UserRolesService',
		function ($controller, $scope, $stateParams, $modalInstance, data, UserRolesService) {


			/* Поля ввода */
			$scope.fields = [
				{
					name: "roleId",
					type: "select",
					load: function () {
						return UserRolesService.getList([{
							name: "userId",
							value: $stateParams.itemId,
							type: "id"
						}
						]);
					},
					label: "Роли",
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			data.item = {userId: $stateParams.itemId};
			$scope.init(data, $scope.fields, {addItem: UserRolesService.addItem}, $modalInstance, {userId: $stateParams.itemId});
		}]);