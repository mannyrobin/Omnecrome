'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DepartmentParkAddCtrl
 * @description
 *
 * Контроллер модального окна создания пользователя сотрудника.
 */
angular.module('armada')
	.controller('DepartmentParkAddCtrl', ['$controller', '$scope', '$stateParams', '$modalInstance', 'data', 'DepartmentParksService',
		function ($controller, $scope, $stateParams, $modalInstance, data, DepartmentParksService) {


			/* Поля ввода */
			$scope.fields = [
				{
					name: "parkId",
					type: "select",
					load: DepartmentParksService.getList,
					label: "Парк",
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, {addItem: DepartmentParksService.addItem}, $modalInstance, {departmentId: $stateParams.itemId});
		}]);
