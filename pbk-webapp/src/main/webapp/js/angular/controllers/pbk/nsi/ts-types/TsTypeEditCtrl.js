'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TsTypeEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования типа ТС.
 */
angular.module('armada')
	.controller('TsTypeEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'TsTypesService', 'GmTsTypesService',
		function ($controller, $scope, $modalInstance, data, TsTypesService, GmTsTypesService) {

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
					name: "tsKindId",
					type: "select",
					label: "Тип ТС ГИС МГТ",
					load: GmTsTypesService.getList,
					required: true
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					required: false
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, TsTypesService, $modalInstance);
		}]);