'use strict';

/**
 * @ngdoc function
 * @name armada.controller:CountyInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('CountyInfoCtrl', ['$controller', '$scope', '$stateParams', 'CountiesService', 'GmDistrictsService', 'DepartmentsService', 'AppConfig', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, CountiesService, GmDistrictsService, DepartmentsService, AppConfig, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "name",
					type: "text",
					label: "Название",
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE,
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
					name: "shortName",
					type: "text",
					label: "Короткое название",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					readonly: true,
					required: false
				}, {
					name: "departmentId",
					type: "select",
					label: "ID Подразделения",
					load: function () {
						return DepartmentsService.getList([{
							name: "currentId",
							value: $scope.fields[3].value.id,
							type: "id"
						}
						]);
					},
					readonly: !isNotReadOnlyUser,
					required: false
				}, {
					name: "gmDistrictId",
					type: "select",
					label: "ID Округа в ВИС ГИС МГТ",
					load: function () {
						return GmDistrictsService.getList([{
							name: "currentId",
							value: $scope.fields[4].value.id,
							type: "id"
						}
						]);
					},
					readonly: true,
					required: true
				}, {
					name: "description",
					type: "textarea",
					maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE,
					label: "Описание",
					readonly: !isNotReadOnlyUser,
					required: false
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, CountiesService);
		}]);
