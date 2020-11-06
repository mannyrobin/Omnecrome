'use strict';

/**
 * @ngdoc function
 * @name armada.controller:GkuopEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования ГКУ ОП.
 */
angular.module('armada')
	.controller('GkuopEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'GkuopsService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, GkuopsService, AppConfig) {


			/* Поля ввода */
			$scope.fields = [
				{
					name: "surname",
					type: "text",
					label: "Фамилия",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				}, {
					name: "name",
					type: "text",
					label: "Имя",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				}, {
					name: "patronumic",
					type: "text",
					label: "Отчество",
					required: false,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				}, {
					name: "personalNumber",
					type: "text",
					label: "Табельный номер",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "positionName",
					type: "text",
					label: "Должность",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "gkuopDeptName",
					type: "text",
					label: "Подразделение",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				}, {
					name: "visGkuopId",
					type: "text",
					label: "ID в ВИС ГКУ ОП",
					required: false,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					required: false,
					maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, {
				addItem: GkuopsService.addItem,
				getItem: GkuopsService.getItem
			}, $modalInstance);
		}]);