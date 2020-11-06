'use strict';

/**
 * @ngdoc function
 * @name armada.controller:SexCtrl
 * @description
 *
 * Пол
 */
angular.module('armada')
	.controller('SexCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'SexesService',
		function ($controller, $scope, $stateParams, UtilService, SexesService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = SexesService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.sexes.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				],
				service: null
			});
		}]);