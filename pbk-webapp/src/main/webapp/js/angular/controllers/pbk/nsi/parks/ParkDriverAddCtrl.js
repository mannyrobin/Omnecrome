'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ParkDriverAddCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования водителя парка.
 */
angular.module('armada')
	.controller('ParkDriverAddCtrl', ['$controller', '$scope', '$stateParams', '$modalInstance', 'data', 'ParkDriversService', 'DriversService',
		function ($controller, $scope, $stateParams, $modalInstance, data, ParkDriversService, DriversService) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "tsDriverIds",
					type: "multiselect",
					lazyload: function (query) {
						return DriversService.getList([{
							name: "fio",
							value: query,
							type: "text"
						}, {
							name: "parkId",
							value: $stateParams.itemId,
							type: "text"
						}]);
					},
					label: "Водители",
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			data.item = {parkId: $stateParams.itemId};
			$scope.init(data, $scope.fields, ParkDriversService, $modalInstance);
		}]);