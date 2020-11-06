'use strict';

/**
 * @ngdoc function
 * @name armada.controller:RouteTsKindsEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования округа.
 */
angular.module('armada')
	.controller('RouteTsKindEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'RouteTsKindsService', 'GmRouteTsKindsService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, RouteTsKindsService, GmRouteTsKindsService, AppConfig) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "name",
					type: "text",
					label: "Название",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					required: true
				}, {
					name: "cod",
					type: "text",
					label: "Код",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					required: true
				}, {
					name: "routeTsKindId",
					type: "select",
					load: GmRouteTsKindsService.getList,
					label: "Вид транспорта маршрута в ВИС ГИС МГТ",
					required: true
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE,
					required: false
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, RouteTsKindsService, $modalInstance);
		}]);