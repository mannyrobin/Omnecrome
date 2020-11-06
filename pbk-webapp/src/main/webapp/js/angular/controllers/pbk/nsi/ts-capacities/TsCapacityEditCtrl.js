'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TsCapacityEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования вместимости ТС.
 */
angular.module('armada')
	.controller('TsCapacityEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'TsCapacitiesService', 'TsTypesService',
		function ($controller, $scope, $modalInstance, data, TsCapacitiesService, TsTypesService) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "name",
					type: "text",
					label: "Название",
					required: true
				}, {
					name: "cod",
					type: "text",
					label: "Код",
					required: true
				}, {
					name: "tsTypeId",
					type: "select",
					label: "Тип ТС",
					load: TsTypesService.getList,
					required: true
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					required: false
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, TsCapacitiesService, $modalInstance);
		}]);