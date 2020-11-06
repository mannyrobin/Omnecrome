'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TSInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('VenicleInfoCtrl', ['$controller', '$scope', '$stateParams', 'VenicleService', 'TSModelsService', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, VenicleService, TSModelsService, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Поля ввода */
			$scope.fields = [
				{
					name: "tsModelId",
					type: "select",
					label: "Модель ТС",
					readonly: true,
					required: true,
					load: function () {
						return TSModelsService.getList([{
							name: "currentId",
							value: $scope.fields[0].value,
							type: "select"
						}
						]);
					}
				},
				{
					name: "depoNumber",
					type: "text",
					label: "Бортовой номер",
					readonly: true,
					required: true
				},
				{
					name: "stateNumber",
					type: "text",
					label: "Гос номер",
					readonly: true,
					required: true
				},
				{
					name: "vinNumber",
					type: "text",
					label: "ВИН номер",
					readonly: true,
					required: true
				},
				{
					name: "asduVenicleId",
					type: "text",
					label: "ID ТС в ВИС АСДУ",
					readonly: true,
					required: true
				},
				{
					name: "asduDepotId",
					type: "text",
					label: "ID депо в ВИС АСДУ",
					readonly: true,
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, VenicleService);
		}]);