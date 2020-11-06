'use strict';

/**
 * @ngdoc function
 * @name armada.controller:VisInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('VisInfoCtrl', ['$controller', '$scope', '$stateParams', 'VisService',
		function ($controller, $scope, $stateParams, VisService) {
			/* Поля ввода */
			$scope.fields = [
				{
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
			$scope.init({id: $stateParams.itemId}, $scope.fields, VisService);
		}]);