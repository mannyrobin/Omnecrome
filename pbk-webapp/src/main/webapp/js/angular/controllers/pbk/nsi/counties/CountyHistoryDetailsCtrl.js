angular.module('armada')
	.controller('CountyHistoryDetailsCtrl', ['$scope', '$modalInstance', 'data', 'GmDistrictsService', 'DepartmentsService', '$controller',
		function ($scope, $modalInstance, data, GmDistrictsService, DepartmentsService, $controller) {

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
					name: "name",
					type: "text",
					label: "Имя",
					readonly: true,
					value: data.item.name,
					required: false
				}, {
					name: "cod",
					type: "text",
					label: "Код",
					readonly: true,
					value: data.item.cod,
					required: false
				}, {
					name: "shortName",
					type: "text",
					label: "Сокращение",
					readonly: true,
					value: data.item.shortName,
					required: false
				}, {
					name: "description",
					type: "text",
					label: "Описание",
					readonly: true,
					value: data.item.description,
					required: false
				}, {
					name: "gmDistrictId",
					type: "select",
					label: "ID Округа в ВИС ГИС МГТ",
					readonly: true,
					required: false
				}, {
					name: "departmentId",
					type: "select",
					label: "ID Подразделения",
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

			$scope.fillSelectItemsParameterized(GmDistrictsService.getList, "currentId", "gmDistrictId", 10);
			$scope.fillSelectItemsParameterized(DepartmentsService.getList, "currentId", "departmentId", 11);

			$scope.close = function () {
				if ($scope.modalInstance) {
					$scope.modalInstance.close();
				}
			}

		}]);
