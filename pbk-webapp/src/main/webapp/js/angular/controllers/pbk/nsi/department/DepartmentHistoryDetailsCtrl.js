angular.module('armada')
	.controller('DepartmentHistoryDetailsCtrl', ['$scope', '$modalInstance', 'data',
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
					name: "name",
					type: "text",
					label: "Имя",
					readonly: true,
					value: data.item.name,
					required: false
				}, {
					name: "planCount",
					type: "text",
					label: "Плановое количество КПТ",
					readonly: true,
					value: data.item.planCount,
					required: false
				}, {
					name: "fullName",
					type: "text",
					label: "Полное имя",
					readonly: true,
					value: data.item.fullName,
					required: false
				}, {
					name: "description",
					type: "text",
					label: "Описание",
					readonly: true,
					value: data.item.description,
					required: false
				}, {
					name: "forPlanUse",
					type: "text",
					label: "Используется в планировании",
					readonly: true,
					value: data.item.forPlanUse ? "Да" : "Нет",
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
