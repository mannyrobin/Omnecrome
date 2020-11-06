'use strict';

/**
 * @ngdoc function
 * @name armada.controller:VenueDistrictAddCtrl
 * @description
 *
 * Контроллер модального окна связывания района и места встречи.
 */
angular.module('armada')
	.controller('VenueDistrictAddCtrl', ['$controller', '$scope', '$stateParams', '$modalInstance', 'data', 'VenuesService', 'DistrictsService',
		function ($controller, $scope, $stateParams, $modalInstance, data, VenuesService, DistrictsService) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "districtId",
					type: "select",
					load: function () {
						return DistrictsService.getDistrictsByVenueId($stateParams.itemId);
					},
					label: "Район",
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			data.item = {venueId: $stateParams.itemId};
			$scope.init(data, $scope.fields, {addItem: VenuesService.addDistrict}, $modalInstance);
		}]);