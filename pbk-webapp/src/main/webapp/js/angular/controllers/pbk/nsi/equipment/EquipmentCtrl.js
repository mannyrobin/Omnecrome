'use strict';

/**
 * @ngdoc function
 * @name armada.controller:EquipmentCtrl
 * @description
 *
 * Бортовое оборудование
 */
angular.module('armada')
	.controller('EquipmentCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'EquipmentsService',
		function ($controller, $scope, $stateParams, UtilService, EquipmentsService) {

			$scope.getItemCrumb = function (item) {
				return item.model;
			};

			$scope.getItem = EquipmentsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.equipments.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'История', route: 'history'}
				]
			});
		}]);