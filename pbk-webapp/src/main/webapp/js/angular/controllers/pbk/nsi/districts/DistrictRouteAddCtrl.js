'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DistrictsEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования маршрута Района Москвы.
 */
angular.module('armada')
	.controller('DistrictRouteAddCtrl', ['$controller', '$scope', '$stateParams', '$modalInstance', 'data', 'DistrictRoutesService', 'RoutesService',
		function ($controller, $scope, $stateParams, $modalInstance, data, DistrictRoutesService, RoutesService) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "routeIds",
					type: "multiselect",
					load: function () {
						return RoutesService.getList([{
							name: "districtId",
							value: $stateParams.itemId,
							type: "id"
						}
						]);
					},
					label: "Маршрут",
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			data.item = {districtId: $stateParams.itemId};
			$scope.init(data, $scope.fields, DistrictRoutesService, $modalInstance);
		}]);