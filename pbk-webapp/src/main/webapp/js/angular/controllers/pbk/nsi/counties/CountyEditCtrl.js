'use strict';

/**
 * @ngdoc function
 * @name armada.controller:CountyEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования округа.
 */
angular.module('armada')
	.controller('CountyEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'CountiesService', 'DepartmentsService', 'GmDistrictsService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, CountiesService, DepartmentsService, GmDistrictsService, AppConfig) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "cod",
					type: "text",
					label: "Код",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "name",
					type: "text",
					label: "Название",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				}, {
					name: "departmentId",
					type: "select",
					label: "ID Подразделения",
					load: DepartmentsService.getList,
					required: false
				}, {
					name: "gmDistrictId",
					type: "select",
					label: "ID Округа в ВИС ГИС МГТ",
					load: GmDistrictsService.getList,
					required: true
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					required: false,
					maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, CountiesService, $modalInstance);
		}]);