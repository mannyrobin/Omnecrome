angular.module('armada')
	.controller('TSModelInfoCtrl', ['$controller', '$scope', '$stateParams', 'TSModelsService', 'TsCapacitiesService', 'isNotReadOnlyUser', 'AppConfig',
		function ($controller, $scope, $stateParams, TSModelsService, TsCapacitiesService, isNotReadOnlyUser, AppConfig) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Поля ввода */
			$scope.fields = [
				{
					name: "make",
					type: "text",
					label: "Марка",
					readonly: true,
					required: true
				},
				{
					name: "model",
					type: "text",
					label: "Модель",
					readonly: true,
					required: true
				},
				{
					name: "tsCapacityId",
					type: "select",
					label: "Вместимость ТС",
					required: true,
					readonly: true,
					load: function () {
						return TsCapacitiesService.getList([{
							name: "currentId",
							value: $scope.fields[2].value,
							type: "select"
						}
						]);
					}
				},
				{
					name: "passengerCountMax",
					type: "int",
					label: "Максимальное кол-во мест",
					readonly: true,
					required: true,
					hint: "Человек"
				},
				{
					name: "seatCount",
					type: "int",
					label: "Максимальное кол-во сидящих",
					readonly: true,
					required: true,
					hint: "Человек"
				},
				{
					name: "mass",
					type: "number",
					label: "Масса",
					readonly: true,
					required: true,
					hint: "Килограмм",
					arNumber: AppConfig.FIELD_PATTERNS.INPUT_NUMERIC_10_2

				},
				{
					name: "length",
					type: "number",
					label: "Длина",
					readonly: true,
					required: true,
					hint: "Метров",
					arNumber: AppConfig.FIELD_PATTERNS.INPUT_NUMERIC_10_2
				},
				{
					name: "width",
					type: "number",
					label: "Ширина",
					readonly: true,
					required: true,
					hint: "Метров",
					arNumber: AppConfig.FIELD_PATTERNS.INPUT_NUMERIC_10_2
				},
				{
					name: "height",
					type: "number",
					label: "Высота",
					readonly: true,
					required: true,
					hint: "Метров",
					arNumber: AppConfig.FIELD_PATTERNS.INPUT_NUMERIC_10_2
				},
				{
					name: "square",
					type: "number",
					label: "Полезная площадь салона",
					readonly: true,
					required: true,
					hint: "Квадратных метров",
					arNumber: AppConfig.FIELD_PATTERNS.INPUT_NUMERIC_10_2
				},
				{
					name: "speedMax",
					type: "number",
					label: "Максимальная скорость",
					readonly: true,
					required: true,
					hint: "Киллометров в час",
					arNumber: AppConfig.FIELD_PATTERNS.INPUT_NUMERIC_10_2
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, {
				editItem: TSModelsService.editItem,
				getItem: TSModelsService.getItem
			});
		}]);