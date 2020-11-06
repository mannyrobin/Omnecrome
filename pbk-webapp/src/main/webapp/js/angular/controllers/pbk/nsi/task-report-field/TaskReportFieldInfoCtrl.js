'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TaskReportFieldInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('TaskReportFieldInfoCtrl', ['$controller', '$scope', '$stateParams', 'TaskReportFieldsService',
		function ($controller, $scope, $stateParams, TaskReportFieldsService) {
			$scope.fields = [{
				name: "name",
				type: "text",
				label: "Название",
				readonly: true
			}, {
				name: "description",
				type: "textarea",
				label: "Описание",
				readonly: true
			}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, TaskReportFieldsService);
		}]);