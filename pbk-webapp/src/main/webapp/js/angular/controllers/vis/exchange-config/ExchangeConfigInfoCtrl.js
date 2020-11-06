/**
 * @ngdoc function
 * @name armada.controller:ExchangeConfigInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('ExchangeConfigInfoCtrl', ['$controller', '$scope', '$stateParams', 'ExchangeConfigsService', 'TransportTypeService', 'ExchangeOperationService', 'ExchangeObjectService', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, ExchangeConfigsService, TransportTypeService, ExchangeOperationService, ExchangeObjectService, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Поля ввода */
			$scope.fields = [
				{
					name: "cod",
					type: "text",
					label: "Код",
					readonly: !isNotReadOnlyUser,
					required: true
				},
				{
					name: "name",
					type: "text",
					label: "Название",
					readonly: !isNotReadOnlyUser,
					required: true
				},
				{
					name: "exchangeOperationId",
					type: "select",
					load: ExchangeOperationService.getList,
					label: "Тип операции",
					readonly: !isNotReadOnlyUser,
					required: true
				},
				{
					name: "transportTypeId",
					type: "select",
					load: TransportTypeService.getList,
					label: "Вид транспорта",
					readonly: !isNotReadOnlyUser,
					required: true
				},
				{
					name: "exchangeObjectId",
					type: "select",
					load: ExchangeObjectService.getList,
					label: "Объект взаимодействия",
					readonly: !isNotReadOnlyUser,
					required: true
				},
				{
					name: "isActive",
					type: "select",
					list: [{id: 0, name: 'Неактивный'}, {id: 1, name: 'Активный'}],
					label: "Активность",
					defval: {id: 1},
					readonly: !isNotReadOnlyUser,
					required: true
				},
				{
					name: "uri",
					type: "text",
					label: "Адрес ресурса",
					readonly: !isNotReadOnlyUser,
					required: true
				},
				{
					name: "login",
					type: "text",
					label: "Логин",
					readonly: !isNotReadOnlyUser,
					required: false
				},
				{
					name: "password",
					type: "text",
					label: "Пароль",
					inputType: "password",
					readonly: !isNotReadOnlyUser,
					required: false
				},
				{
					name: "confirmPassword",
					type: "text",
					label: "Подтверждение пароля",
					inputType: "password",
					readonly: !isNotReadOnlyUser,
					required: false
				},
				{
					name: "description",
					type: "textarea",
					label: "Описание",
					readonly: !isNotReadOnlyUser,
					required: false
				}];
			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, {
				editItem: ExchangeConfigsService.editItem,
				getItem: ExchangeConfigsService.getItem
			});
		}]);