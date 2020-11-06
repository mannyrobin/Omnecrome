angular.module('armada')
	.controller('EquipmentsHistoryDetailsCtrl', ['$scope', '$modalInstance', 'data', '$controller', 'VenicleService',
		function ($scope, $modalInstance, data, $controller, VenicleService) {

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
					name: "brand",
					type: "text",
					label: "Марка",
					readonly: true,
					value: data.item.brand,
					required: false
				}, {
					name: "model",
					type: "text",
					label: "Модель",
					readonly: true,
					value: data.item.model,
					required: false
				}, {
					name: "venicleId",
					type: "select",
					label: "ТС",
					readonly: true,
					required: false
				}, {
					name: "firmwareVersion",
					type: "text",
					label: "Версия прошивки",
					readonly: true,
					value: data.item.firmwareVersion,
					required: false
				}, {
					name: "cellNumber",
					type: "text",
					label: "Номер абонента",
					readonly: true,
					value: data.item.cellNumber,
					required: false
				}, {
					name: "asduEquipmentId",
					type: "text",
					label: "ID в ВИС АСДУ",
					readonly: true,
					value: data.item.asduEquipmentId,
					required: false
				}, {
					name: "asduVenicleId",
					type: "text",
					label: "ID ТС в ВИС АСДУ",
					readonly: true,
					value: data.item.asduVenicleId,
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
			$scope.fillSelectItemsParameterized(VenicleService.getList, "currentId", "venicleId", 8);

			$scope.close = function () {
				if ($scope.modalInstance) {
					$scope.modalInstance.close();
				}
			}

		}]);