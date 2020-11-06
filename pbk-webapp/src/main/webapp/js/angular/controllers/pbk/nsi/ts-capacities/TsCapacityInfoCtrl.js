'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TsCapacityInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('TsCapacityInfoCtrl', ['$controller', '$scope', '$stateParams', 'TsCapacitiesService', 'TsTypesService', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, TsCapacitiesService, TsTypesService, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Поля ввода */
			$scope.fields = [
				{
					name: "name",
					type: "text",
					label: "Название",
					readonly: true,
					required: true
				}, {
					name: "cod",
					type: "text",
					label: "Код",
					readonly: true,
					required: true
				}, {
					name: "tsTypeId",
					type: "select",
					label: "Тип ТС",
					load: TsTypesService.getList,
					readonly: true,
					required: true
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					readonly: true,
					required: false
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, TsCapacitiesService);
		}]);