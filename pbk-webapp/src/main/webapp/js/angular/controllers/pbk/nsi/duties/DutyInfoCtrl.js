'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DutyInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('DutyInfoCtrl', ['$controller', '$scope', '$stateParams', 'DutiesService', 'AppConfig', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, DutiesService, AppConfig, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "workDate",
					type: "date",
					label: "Транспортные сутки",
					readonly: !isNotReadOnlyUser,
					required: true
				},
				{
					name: "moveCode",
					type: "text",
					label: "Маршрут",
					required: true,
					readonly: !isNotReadOnlyUser,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				},
				{
					name: "easuFhdRouteCode",
					type: "text",
					label: "Код выхода на маршрут",
					required: true,
					readonly: !isNotReadOnlyUser,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				},
				{
					name: "tsDepoNumber",
					type: "text",
					label: "ТС",
					required: true,
					readonly: !isNotReadOnlyUser,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				},
				{
					name: "driverPersonalNumber",
					type: "text",
					label: "Водитель",
					required: true,
					readonly: !isNotReadOnlyUser,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				},
				{
					name: "moveStartTime",
					type: "time",
					label: "Начало работы",
					readonly: !isNotReadOnlyUser,
					required: true
				},
				{
					name: "moveEndTime",
					type: "time",
					label: "Завершение работы",
					readonly: !isNotReadOnlyUser,
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, {
				editItem: DutiesService.editItem,
				getItem: DutiesService.getItem
			});
		}]);