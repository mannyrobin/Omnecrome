'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TicketsEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования водителей.
 */
angular.module('armada')
	.controller('DriversEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'DriversService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, DriversService, AppConfig) {


			/* Поля ввода */
			$scope.fields = [
				{
					name: "surname",
					type: "text",
					label: "Фамилия",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				},
				{
					name: "name",
					type: "text",
					label: "Имя",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				},
				{
					name: "patronumic",
					type: "text",
					label: "Отчество",
					required: false,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				},
				{
					name: "personalNumber",
					type: "text",
					label: "Табельный номер водителя",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "parkId",
					type: "select",
					label: "Эксплуатационный филиал",
					required: false,
					load: function () {
						return ParksService.getList([{
							name: "currentId",
							value: $scope.fields[4].value.id,
							type: "id"
						}
						]);
					}
				}, {
					name: "asduDriverId",
					type: "text",
					label: "ID в ВИС АСДУ",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				},
				{
					name: "asduDepotId",
					type: "text",
					label: "ID парка в ВИС АСДУ",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, {
				addItem: DriversService.addItem,
				getItem: DriversService.getItem
			}, $modalInstance);
		}]);
