'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TsTypeCtrl
 * @description
 *
 * Тип ТС
 */
angular.module('armada')
	.controller('TsTypeCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'TsTypesService',
		function ($controller, $scope, $stateParams, UtilService, TsTypesService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = TsTypesService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.ts-types.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				]
			});
		}]);