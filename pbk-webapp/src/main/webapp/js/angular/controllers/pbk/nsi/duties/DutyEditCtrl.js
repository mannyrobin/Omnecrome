'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DutyEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования наряда.
 */
angular.module('armada')
	.controller('DutyEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'DutiesService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, DutiesService, AppConfig) {


			/* Поля ввода */
			$scope.fields = [
				{
					name: "workDate",
					type: "date",
					label: "Транспортные сутки",
					required: true
				},
				{
					name: "moveCode",
					type: "text",
					label: "Маршрут",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				},
				{
					name: "easuFhdRouteCode",
					type: "text",
					label: "Код выхода на маршрут",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				},
				{
					name: "tsDepoNumber",
					type: "text",
					label: "ТС",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				},
				{
					name: "driverPersonalNumber",
					type: "text",
					label: "Водитель",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				},
				{
					name: "moveStartTime",
					type: "time",
					label: "Начало работы",
					required: true
				},
				{
					name: "moveEndTime",
					type: "time",
					label: "Завершение работы",
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, {
				addItem: DutiesService.addItem,
				getItem: DutiesService.getItem
			}, $modalInstance);
		}]);
