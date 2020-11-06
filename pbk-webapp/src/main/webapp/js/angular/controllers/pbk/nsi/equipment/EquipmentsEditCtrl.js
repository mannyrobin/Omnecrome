'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TicketsEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования бортового оборудования.
 */
angular.module('armada')
	.controller('EquipmentsEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'EquipmentsService', 'VenicleService',
		function ($controller, $scope, $modalInstance, data, EquipmentsService, VenicleService) {


			/* Поля ввода */
			$scope.fields = [
				{
					name: "brand",
					type: "text",
					label: "Марка",
					required: true
				},
				{
					name: "model",
					type: "text",
					label: "Модель",
					required: true
				},
				{
					name: "venicleId",
					type: "select",
					label: "TC",
					required: true,
					load: VenicleService.getList
				},
				{
					name: "firmwareVersion",
					type: "text",
					label: "Версия прошивки",
					required: true
				},
				{
					name: "cellNumber",
					type: "text",
					label: "Номер абонента",
					required: true
				},
				{
					name: "asduEquipmentId",
					type: "text",
					label: "ID в ВИС АСДУ",
					required: true
				},
				{
					name: "asduVenicleId",
					type: "text",
					label: "ID ТС в ВИС АСДУ",
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, {
				addItem: EquipmentsService.addItem,
				editItem: EquipmentsService.editItem,
				getItem: EquipmentsService.getItem
			}, $modalInstance);
		}]);
