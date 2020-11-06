'use strict';

/**
 * @ngdoc function
 * @name armada.controller:SexInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('SexInfoCtrl', ['$controller', '$scope', '$stateParams', 'SexesService',
		function ($controller, $scope, $stateParams, SexesService) {
			$scope.fields = [{
				name: "cod",
				type: "text",
				label: "Код",
				readonly: true
			}, {
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
			$scope.init({id: $stateParams.itemId}, $scope.fields, SexesService);
		}]);