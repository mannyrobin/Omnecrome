'use strict';

/**
 * @ngdoc function
 * @name armada.controller:RouteCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('RouteCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'RoutesService',
		function ($controller, $scope, $stateParams, UtilService, RoutesService) {

			$scope.getItemCrumb = function (item) {
				return item.routeNumber;
			};

			$scope.getItem = RoutesService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.routes.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'История', route: 'history'},
					{heading: 'Карта', route: 'map'}
				],
				service: null,
			});
		}]);