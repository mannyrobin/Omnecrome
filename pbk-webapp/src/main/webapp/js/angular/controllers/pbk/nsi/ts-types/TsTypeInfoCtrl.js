'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TsTypeInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('TsTypeInfoCtrl', ['$controller', '$scope', '$stateParams', 'TsTypesService', 'GmTsTypesService', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, TsTypesService, GmTsTypesService, isNotReadOnlyUser) {
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
					name: "tsKindId",
					type: "select",
					label: "Тип ТС ГИС МГТ",
					load: function () {
						return GmTsTypesService.getList([{
							name: "currentId",
							value: $scope.fields[2].value,
							type: "select"
						}
						]);
					},
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
			$scope.init({id: $stateParams.itemId}, $scope.fields, TsTypesService);
		}]);