'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ExchangeObjectInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('ExchangeObjectInfoCtrl', ['$controller', '$scope', '$stateParams', 'ExchangeObjectService',
		function ($controller, $scope, $stateParams, ExchangeObjectService) {
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
					name: "vis",
					type: "text",
					label: "ВИС",
					readonly: true
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					readonly: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, ExchangeObjectService);
		}]);