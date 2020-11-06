'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TSModelCtrl
 * @description
 *
 * Модель ТС
 */
angular.module('armada')
	.controller('TSModelCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'TSModelsService',
		function ($controller, $scope, $stateParams, UtilService, TSModelsService) {

			$scope.getItemCrumb = function (item) {
				return item.make + ' ' + item.model;
			};

			$scope.getItem = TSModelsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.ts-models.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				]
			});
		}]);