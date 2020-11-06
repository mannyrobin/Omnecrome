angular.module('armada')
	.controller('TicketInfoCtrl', ['$controller', '$scope', '$stateParams', 'TicketsService', 'TicketTypesService', 'AppConfig', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, TicketsService, TicketTypesService, AppConfig, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "code",
					type: "text",
					label: "Код",
					required: true,
					readonly: !isNotReadOnlyUser,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "name",
					type: "text",
					label: "Название",
					required: true,
					readonly: !isNotReadOnlyUser,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "applicationCode",
					type: "text",
					label: "Код приложения",
					readonly: !isNotReadOnlyUser,
					required: false
				}, {
					name: "ticketTypeId",
					type: "select",
					load: TicketTypesService.getList,
					label: "Тип билета",
					readonly: !isNotReadOnlyUser,
					required: true
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					required: false,
					readonly: !isNotReadOnlyUser,
					maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, TicketsService);
		}]);