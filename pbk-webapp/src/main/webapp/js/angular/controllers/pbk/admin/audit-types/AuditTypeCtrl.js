'use strict';

/**
 * @ngdoc function
 * @name armada.controller:AuditTypeCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('AuditTypeCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'AuditTypesService',
		function ($controller, $scope, $stateParams, UtilService, AuditTypesService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = AuditTypesService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.admin.audit-types.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				],
				service: null,
			});
		}]);