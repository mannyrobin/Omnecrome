angular.module('armada')
	.controller('ParkHistoryDetailsCtrl', ['$scope', '$modalInstance', 'data', 'TsTypesService', 'GmParksService', '$controller',
		function ($scope, $modalInstance, data, TsTypesService, GmParksService, $controller) {

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
					name: "shortName",
					type: "text",
					label: "Сокращение",
					readonly: true,
					value: data.item.shortName,
					required: false
				}, {
					name: "name",
					type: "text",
					label: "Название",
					readonly: true,
					value: data.item.name,
					required: false
				}, {
					name: "parkNumber",
					type: "text",
					label: "Номер",
					readonly: true,
					required: false
				}, {
					name: "gmParkId",
					type: "select",
					label: "ГИС МГТ ИД парка",
					readonly: true,
					required: false
				}, {
					name: "tsKindId",
					type: "select",
					label: "ГИС МГТ ИД парка",
					readonly: true,
					required: false
				}, {
					name: "asduDepotId",
					type: "text",
					label: "ID парка в ВИС АСДУ",
					readonly: true,
					value: data.item.asduDepotId,
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

			$scope.fillSelectItemsParameterized(GmParksService.getList, "currentId", "gmParkId", 9);
			$scope.fillSelectItems(TsTypesService.getList, "tsKindId", 10);

			$scope.close = function () {
				if ($scope.modalInstance) {
					$scope.modalInstance.close();
				}
			}

		}]);