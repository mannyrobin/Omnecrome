'use strict';

/**
 * @ngdoc function
 * @name armada.controller:BsoNumberRuleCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('BsoNumberRuleCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'BsoNumberRulesService',
		function ($controller, $scope, $stateParams, UtilService, BsoNumberRulesService) {

			$scope.getItemCrumb = function (item) {
				var result = "";
				if (item.bsoTypeCode) {
					result += item.bsoTypeCode;
				}
				if (item.branchCode) {
					result += item.branchCode;
				}
				if (item.departmentCode) {
					result += item.departmentCode;
				}
				if (item.increment) {
					result += item.increment;
				}
				;
				return result;
			};

			$scope.getItem = BsoNumberRulesService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.bso-number-rules.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				]
			});
		}]);