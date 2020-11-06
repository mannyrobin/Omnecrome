'use strict';

/**
 * @ngdoc function
 * @name armada.controller:RouteTsKindsInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('RouteTsKindInfoCtrl', ['$controller', '$scope', '$stateParams', 'RouteTsKindsService', 'GmRouteTsKindsService', 'AppConfig', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, RouteTsKindsService, GmRouteTsKindsService, AppConfig, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "name",
					type: "text",
					label: "Название",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					readonly: true,
					required: true
				}, {
					name: "cod",
					type: "text",
					label: "Код",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					readonly: true,
					required: true
				}, {
					name: "routeTsKindId",
					type: "select",
					load: function () {
						return GmRouteTsKindsService.getList([{
							name: "currentId",
							value: $scope.fields[2].value.id,
							type: "id"
						}
						]);
					},
					label: "Вид транспорта маршрута в ВИС ГИС МГТ",
					readonly: true,
					required: true
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE,
					readonly: true,
					required: false
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, RouteTsKindsService);
		}]);