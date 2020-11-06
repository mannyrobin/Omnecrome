'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TSModelsEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования моделей ТС.
 */
angular.module('armada')
	.controller('TSModelsEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'TSModelsService', 'TsCapacitiesService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, TSModelsService, TsCapacitiesService, AppConfig) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "make",
					type: "text",
					label: "Марка",
					required: true
				},
				{
					name: "model",
					type: "text",
					label: "Модель",
					required: true
				},
				{
					name: "tsCapacityId",
					type: "select",
					label: "Вместимость ТС",
					required: true,
					load: TsCapacitiesService.getList
				},
				{
					name: "passengerCountMax",
					type: "int",
					label: "Максимальное кол-во мест",
					required: true,
					hint: "Человек"
				},
				{
					name: "seatCount",
					type: "int",
					label: "Максимальное кол-во сидящих",
					required: true,
					hint: "Человек"
				},
				{
					name: "mass",
					type: "number",
					label: "Масса",
					required: true,
					hint: "Килограмм",
					arNumber: AppConfig.FIELD_PATTERNS.INPUT_NUMERIC_10_2
				},
				{
					name: "length",
					type: "number",
					label: "Длина",
					required: true,
					hint: "Метров",
					arNumber: AppConfig.FIELD_PATTERNS.INPUT_NUMERIC_10_2
				},
				{
					name: "width",
					type: "number",
					label: "Ширина",
					required: true,
					hint: "Метров",
					arNumber: AppConfig.FIELD_PATTERNS.INPUT_NUMERIC_10_2
				},
				{
					name: "height",
					type: "number",
					label: "Высота",
					required: true,
					hint: "Метров",
					arNumber: AppConfig.FIELD_PATTERNS.INPUT_NUMERIC_10_2
				},
				{
					name: "square",
					type: "number",
					label: "Полезная площадь салона",
					required: true,
					hint: "Квадратных метров",
					arNumber: AppConfig.FIELD_PATTERNS.INPUT_NUMERIC_10_2
				},
				{
					name: "speedMax",
					type: "number",
					label: "Максимальная скорость",
					required: true,
					hint: "Киллометров в час",
					arNumber: AppConfig.FIELD_PATTERNS.INPUT_NUMERIC_10_2
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, {
				addItem: TSModelsService.addItem,
				getItem: TSModelsService.getItem
			}, $modalInstance);
		}]);
