'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DistrictsEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования района.
 */
angular.module('armada')
	.controller('DistrictVenueAddCtrl', ['$controller', '$scope', '$stateParams', '$modalInstance', 'data', 'VenuesService',
		function ($controller, $scope, $stateParams, $modalInstance, data, VenuesService) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "venueId",
					type: "select",
					load: function () {
						return VenuesService.getList([{
							name: "districtId",
							value: $stateParams.itemId,
							type: "text"
						}])
					},
					label: "Место встречи",
					required: true
				}];
			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			data.item = {districtId: $stateParams.itemId};
			$scope.init(data, $scope.fields, {addItem: VenuesService.addDistrict}, $modalInstance);
		}]);