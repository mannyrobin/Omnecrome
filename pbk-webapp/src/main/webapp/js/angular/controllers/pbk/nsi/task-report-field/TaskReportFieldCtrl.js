'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TaskReportFieldCtrl
 * @description
 *
 * Пол
 */
angular.module('armada')
	.controller('TaskReportFieldCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'TaskReportFieldsService',
		function ($controller, $scope, $stateParams, UtilService, TaskReportFieldsService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = TaskReportFieldsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.task-report-fieldes.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				],
				service: null,
			});
		}]);