angular.module('armada')
	.controller('VeniclesHistoryDetailsCtrl', ['$scope', '$modalInstance', 'data', 'TSModelsService', '$controller',
		function ($scope, $modalInstance, data, TSModelsService, $controller) {

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
					name: "depoNumber",
					type: "text",
					label: "Бортовой номер",
					readonly: true,
					value: data.item.depoNumber,
					required: false
				}, {
					name: "asduDepotId",
					type: "text",
					label: "ID депо в ВИС АСДУ",
					readonly: true,
					value: data.item.asduDepotId,
					required: false
				}, {
					name: "stateNumber",
					type: "text",
					label: "Гос. номер",
					value: data.item.stateNumber,
					readonly: true,
					required: false
				}, {
					name: "vinNumber",
					type: "text",
					label: "ВИН номер",
					value: data.item.vinNumber,
					readonly: true,
					required: false
				}, {
					name: "tsModelId",
					type: "select",
					label: "Модель ТС",
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

			$scope.fillSelectItemsParameterized(TSModelsService.getList, "currentId", "tsModelId", 10);

			$scope.close = function () {
				if ($scope.modalInstance) {
					$scope.modalInstance.close();
				}
			}

		}]);