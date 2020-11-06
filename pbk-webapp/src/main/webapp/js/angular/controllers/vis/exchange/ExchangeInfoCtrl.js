/**
 * @ngdoc function
 * @name armada.controller:ExchangeInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('ExchangeInfoCtrl', ['$controller', '$scope', '$stateParams', 'ExchangesService',
		function ($controller, $scope, $stateParams, ExchangesService) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "workDate",
					type: "text",
					label: "Дата взаимодействия",
					required: false,
					readonly: true
				},
				{
					name: "stateName",
					type: "text",
					label: "Статус",
					required: false,
					readonly: true
				},
				{
					name: "dayFail",
					type: "text",
					label: "Нарушение расписания",
					required: false,
					readonly: true
				},
				{
					name: "configName",
					type: "text",
					label: "Конфигурация взаимодействия",
					required: false,
					readonly: true
				},
				{
					name: "visName",
					type: "text",
					label: "Вис",
					required: false,
					readonly: true
				},
				{
					name: "exchangeObjectName",
					type: "text",
					label: "Объект взаимодействия",
					required: false,
					readonly: true
				},
				{
					name: "exchangeOperationName",
					type: "text",
					label: "Тип операции",
					required: false,
					readonly: true
				},
				{
					name: "transportTypeName",
					type: "text",
					label: "Вид транспорта",
					required: false,
					readonly: true
				},
				{
					name: "errorContent",
					type: "textarea",
					label: "Сообщение об ошибке",
					required: false,
					readonly: true
				}];
			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, {getItem: ExchangesService.getItem});
		}]);