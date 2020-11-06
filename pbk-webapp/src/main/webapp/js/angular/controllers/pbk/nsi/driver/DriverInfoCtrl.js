'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DriverInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('DriverInfoCtrl', ['$controller', '$scope', '$stateParams', 'DriversService', 'ParksService', 'AppConfig', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, DriversService, ParksService, AppConfig, isNotReadOnlyUser) {
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
				},
				{
					name: "name",
					type: "text",
					label: "Имя",
					required: true,
					readonly: true,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				},
				{
					name: "patronumic",
					type: "text",
					label: "Отчество",
					required: false,
					readonly: true,
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
				},
				{
					name: "personalNumber",
					type: "text",
					label: "Табельный номер водителя",
					required: true,
					readonly: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "parkId",
					type: "select",
					label: "Эксплуатационный филиал",
					readonly: !isNotReadOnlyUser,
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
					readonly: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				},
				{
					name: "asduDepotId",
					type: "text",
					label: "ID парка в ВИС АСДУ",
					required: true,
					readonly: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, DriversService);
		}]);