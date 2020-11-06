'use strict';

/**
 * @ngdoc function
 * @name armada.controller:AuditTypeInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('AuditTypeInfoCtrl', ['$controller', '$scope', '$stateParams', 'AuditTypesService',
		function ($controller, $scope, $stateParams, AuditTypesService) {
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
			$scope.init({id: $stateParams.itemId}, $scope.fields, AuditTypesService);
		}]);