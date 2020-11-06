angular.module('armada')
	.controller('DvrHistoryDetailsCtrl', ['$scope', '$modalInstance', '$controller', 'data', 'DepartmentsService',
		function ($scope, $modalInstance, $controller, data, DepartmentsService) {

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
					name: "dvrNumber",
					type: "text",
					label: "Номер",
					readonly: true,
					value: data.item.dvrNumber,
					required: false
				}, {
					name: "dvrModel",
					type: "text",
					label: "Модель видеорегистратора",
					readonly: true,
					value: data.item.dvrModel,
					required: false
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					readonly: true,
					value: data.item.description,
					required: false
				}, {
					name: "active",
					type: "text",
					label: "Состояние",
					readonly: true,
					value: data.item.active ? "Актуально" : "Устарела",
					required: false
				}, {
					name: "deptId",
					type: "select",
					label: "Подразделение",
					readonly: true,
					required: false
				}];

			$scope.close = function () {
				if ($scope.modalInstance) {
					$scope.modalInstance.close();
				}
			}

			$scope.fillSelectItemsParameterized(DepartmentsService.getList, "deptId", "deptId", 10);

		}]);
