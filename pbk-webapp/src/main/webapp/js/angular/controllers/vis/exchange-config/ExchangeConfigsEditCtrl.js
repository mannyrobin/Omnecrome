/**
 * @ngdoc function
 * @name armada.controller:ExchangeConfigsEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования конфигурации взаимодействия с ВИС.
 */
angular.module('armada')
	.controller('ExchangeConfigsEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'ExchangeConfigsService', 'TransportTypeService', 'ExchangeOperationService', 'ExchangeObjectService',
		function ($controller, $scope, $modalInstance, data, ExchangeConfigsService, TransportTypeService, ExchangeOperationService, ExchangeObjectService) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "cod",
					type: "text",
					label: "Код",
					required: true
				},
				{
					name: "name",
					type: "text",
					label: "Название",
					required: true
				},
				{
					name: "exchangeOperationId",
					type: "select",
					load: ExchangeOperationService.getList,
					label: "Тип операции",
					required: true
				},
				{
					name: "transportTypeId",
					type: "select",
					load: TransportTypeService.getList,
					label: "Вид транспорта",
					required: true
				},
				{
					name: "exchangeObjectId",
					type: "select",
					load: ExchangeObjectService.getList,
					label: "Объект взаимодействия",
					required: true
				},
				{
					name: "isActive",
					type: "select",
					list: [{id: 0, name: 'Неактивный'}, {id: 1, name: 'Активный'}],
					label: "Активность",
					defval: {id: 1},
					required: true
				},
				{
					name: "uri",
					type: "text",
					label: "Адрес ресурса",
					required: true
				},
				{
					name: "login",
					type: "text",
					label: "Логин",
					required: false
				},
				{
					name: "password",
					type: "text",
					label: "Пароль",
					inputType: "password",
					required: false
				},
				{
					name: "confirmPassword",
					type: "text",
					label: "Подтверждение пароля",
					inputType: "password",
					required: false
				},
				{
					name: "description",
					type: "textarea",
					label: "Описание",
					required: false
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, {
				addItem: ExchangeConfigsService.addItem,
				getItem: ExchangeConfigsService.getItem
			}, $modalInstance);
		}]);
