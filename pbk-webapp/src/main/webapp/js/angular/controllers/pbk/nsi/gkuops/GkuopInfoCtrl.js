'use strict';

/**
 * @ngdoc function
 * @name armada.controller:GkuopInfoCtrl
 * @description
 *
 */angular.module('armada')
	.controller('GkuopInfoCtrl', ['$controller', '$scope', '$stateParams', 'GkuopsService', 'AppConfig', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, GkuopsService, AppConfig, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "surname",
					type: "text",
					label: "Фамилия",
					required: true,
					readonly: true,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				}, {
					name: "name",
					type: "text",
					label: "Имя",
					required: true,
					readonly: true,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				}, {
					name: "patronumic",
					type: "text",
					label: "Отчество",
					required: false,
					readonly: true,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				}, {
					name: "personalNumber",
					type: "text",
					label: "Табельный номер",
					required: true,
					readonly: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "positionName",
					type: "text",
					label: "Должность",
					required: true,
					readonly: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "gkuopDeptName",
					type: "text",
					label: "Подразделение",
					required: true,
					readonly: true,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				}, {
					name: "visGkuopId",
					type: "text",
					label: "ID в ВИС ГКУ ОП",
					required: false,
					readonly: true,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					required: false,
					readonly: true,
					maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE
				}, {
					name: "forPlanUse",
					type: "yesno",
					label: "Участвует в бригадах",
					readonly: !isNotReadOnlyUser,
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, GkuopsService);
		}]);