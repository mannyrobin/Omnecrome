angular.module('armada')
	.controller('DriverHistoryDetailsCtrl', ['$scope', '$modalInstance', 'data', 'ParksService', '$controller',
		function ($scope, $modalInstance, data, ParksService, $controller) {

			$scope.title = data.title;
			$scope.modalInstance = $modalInstance;

			$controller('BaseHistoryDetailsCtrl', {$scope: $scope});
			$scope.init(data.item);

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
					name: "name",
					type: "text",
					label: "Имя",
					readonly: true,
					value: data.item.name,
					required: false
				}, {
					name: "patronumic",
					type: "text",
					label: "Отчество",
					readonly: true,
					value: data.item.patronumic,
					required: false
				}, {
					name: "surname",
					type: "text",
					label: "Фамилия",
					readonly: true,
					value: data.item.surname,
					required: false
				}, {
					name: "asduDriverId",
					type: "text",
					label: "ID в ВИС АСДУ",
					readonly: true,
					value: data.item.asduDriverId,
					required: false
				}, {
					name: "asduDepotId",
					type: "text",
					label: "ID парка в ВИС АСДУ",
					readonly: true,
					value: data.item.asduDepotId,
					required: false
				}, {
					name: "personalNumber",
					type: "text",
					label: "Персональный номер",
					readonly: true,
					value: data.item.personalNumber,
					required: false
				}, {
					name: "parkId",
					type: "select",
					label: "Эксплуатационный филиал",
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

			$scope.close = function () {
				if ($scope.modalInstance) {
					$scope.modalInstance.close();
				}
			}

			$scope.fillSelectItemsParameterized(ParksService.getList, "currentId", "parkId", 12);

		}]);