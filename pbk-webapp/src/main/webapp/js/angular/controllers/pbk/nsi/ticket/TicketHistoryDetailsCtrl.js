angular.module('armada')
	.controller('TicketHistoryDetailsCtrl', ['$scope', '$modalInstance', 'data', 'TicketTypesService', '$controller',
		function ($scope, $modalInstance, data, TicketTypesService, $controller) {

			$scope.title = data.title;
			$scope.modalInstance = $modalInstance;

			$scope.fields = [
				{
					name: "createUser",
					type: "text",
					label: "Создавший пользователь",
					readonly: true,
					value: data.item.createUser,
					required: false
				}, {
					name: "createDate",
					type: "text",
					label: "Дата создания",
					readonly: true,
					value: data.item.createDate,
					required: false
				}, {
					name: "updateUser",
					type: "text",
					label: "Обновивший пользователь",
					readonly: true,
					value: data.item.updateUser,
					required: false
				}, {
					name: "updateDate",
					type: "text",
					label: "Дата обновления",
					readonly: true,
					value: data.item.updateDate,
					required: false
				}, {
					name: "versionStartDate",
					type: "text",
					label: "Начало периода",
					readonly: true,
					value: data.item.versionStartDate,
					required: false
				}, {
					name: "versionEndDate",
					type: "text",
					label: "Окончание периода",
					readonly: true,
					value: data.item.versionEndDate,
					required: false
				}, {
					name: "code",
					type: "text",
					label: "Код",
					readonly: true,
					value: data.item.code,
					required: false
				}, {
					name: "name",
					type: "text",
					label: "Название",
					readonly: true,
					value: data.item.name,
					required: false
				}, {
					name: "description",
					type: "text",
					label: "Описание",
					readonly: true,
					value: data.item.description,
					required: false
				}, {
					name: "applicationCode",
					type: "text",
					label: "Код приложения",
					value: data.item.applicationCode,
					readonly: true,
					required: false
				}, {
					name: "ticketTypeId",
					type: "select",
					label: "Тип билета",
					readonly: true,
					required: false
				}, {
					name: "active",
					type: "text",
					label: "Состояние",
					readonly: true,
					value: data.item.active ? "Актуально" : "Устарела",
					required: false
				}];

			$controller('BaseHistoryDetailsCtrl', {$scope: $scope});
			$scope.init(data.item);
			$scope.fillSelectItems(TicketTypesService.getList, "ticketTypeId", 10);

			$scope.close = function () {
				if ($scope.modalInstance) {
					$scope.modalInstance.close();
				}
			}

		}]);