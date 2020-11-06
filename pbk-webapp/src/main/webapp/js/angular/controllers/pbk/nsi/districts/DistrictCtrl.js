'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DistrictCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('DistrictCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'DistrictsService',
		function ($controller, $scope, $stateParams, UtilService, DistrictsService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = DistrictsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.districts.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'Маршруты', route: 'routes'},
					{heading: 'Места встреч', route: 'venues'},
					{heading: 'История', route: 'history'}
				]
			});
		}]);