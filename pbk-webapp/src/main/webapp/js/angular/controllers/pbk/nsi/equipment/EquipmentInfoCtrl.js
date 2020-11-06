'use strict';

/**
 * @ngdoc function
 * @name armada.controller:EquipmentInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('EquipmentInfoCtrl', ['$controller', '$scope', '$stateParams', 'EquipmentsService', 'VenicleService', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, EquipmentsService, VenicleService, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				/*{ похоже эти поля не будут участвовать в работе. из внешней системы не приходят((
				 name: "brand",
				 type: "text",
				 label: "Марка",
				 readonly: true,
				 required: true
				 },
				 {
				 name: "model",
				 type: "text",
				 label: "Модель",
				 readonly: true,
				 required: true
				 },

				 {
				 name: "firmwareVersion",
				 type: "text",
				 label: "Версия прошивки",
				 readonly: true,
				 required: true
				 },
				 {
				 name: "cellNumber",
				 type: "text",
				 label: "Номер абонента",
				 readonly: true,
				 required: true
				 },*/
				{
					name: "venicleId",
					type: "select",
					label: "TC",
					readonly: true,
					required: true,
					load: function () {
						return VenicleService.getList([
							{
								name: "currentId",
								value: $scope.fields[2].value,
								type: "select"
							}
						])
					}
				},
				{
					name: "asduEquipmentId",
					type: "text",
					label: "ID в ВИС АСДУ",
					readonly: true,
					required: true
				},
				{
					name: "asduVenicleId",
					type: "text",
					label: "ID ТС в ВИС АСДУ",
					readonly: true,
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, EquipmentsService);
		}]);