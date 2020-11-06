'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TSEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования ТС.
 */
angular.module('armada')
	.controller('VenicleEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'VenicleService', 'TSModelsService',
		function ($controller, $scope, $modalInstance, data, VenicleService, TSModelsService) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "tsModelId",
					type: "select",
					label: "Модель ТС",
					required: true,
					load: TSModelsService.getList
				},
				{
					name: "depoNumber",
					type: "text",
					label: "Бортовой номер",
					required: true
				},
				{
					name: "stateNumber",
					type: "text",
					label: "Гос номер",
					required: true
				},
				{
					name: "vinNumber",
					type: "text",
					label: "ВИН номер",
					required: true
				},
				{
					name: "asduVenicleId",
					type: "text",
					label: "ID ТС в ВИС АСДУ",
					required: true
				},
				{
					name: "asduDepotId",
					type: "text",
					label: "ID депо в ВИС АСДУ",
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, {
				addItem: VenicleService.addItem,
				getItem: VenicleService.getItem
			}, $modalInstance);
		}]);
