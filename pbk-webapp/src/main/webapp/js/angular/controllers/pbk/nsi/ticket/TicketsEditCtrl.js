'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TicketsEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования билета.
 */
angular.module('armada')
	.controller('TicketsEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'TicketsService', 'TicketTypesService',
		function ($controller, $scope, $modalInstance, data, TicketsService, TicketTypesService) {


			/* Поля ввода */
			$scope.fields = [
				{
					name: "name",
					type: "text",
					label: "Название",
					required: true
				},
				{
					name: "code",
					type: "text",
					label: "Код",
					required: true
				},
				{
					name: "applicationCode",
					type: "text",
					label: "Код приложения",
					required: false
				},
				{
					name: "ticketTypeId",
					type: "select",
					load: TicketTypesService.getList,
					label: "Тип билета",
					required: true
				},
				{
					name: "description",
					type: "textarea",
					label: "Описание",
					required: false
				},
				{
					name: "useInAnalysis",
					type: "yesno",
					label: "Входит в анализ пассажиропотока",
					required: true

				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, {
				addItem: TicketsService.addItem,
				editItem: TicketsService.editItem,
				getItem: TicketsService.getItem
			}, $modalInstance);
		}]);
