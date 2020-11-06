angular.module('armada')
	.controller('PuskHistoryDetailsCtrl', ['$scope', '$modalInstance', 'data',
		function ($scope, $modalInstance, data) {

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
					name: "puskNumber",
					type: "text",
					label: "Номер",
					readonly: true,
					value: data.item.puskNumber,
					required: false
				}, {
					name: "puskModel",
					type: "text",
					label: "Модель",
					readonly: true,
					value: data.item.puskModel,
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
				}];

			$scope.close = function () {
				if ($scope.modalInstance) {
					$scope.modalInstance.close();
				}
			}

		}]);
