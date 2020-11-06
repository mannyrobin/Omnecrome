angular.module('armada')
	.controller('GkuopHistoryDetailsCtrl', ['$scope', '$modalInstance', 'data',
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
					name: "gkuopDeptName",
					type: "text",
					label: "Департамент",
					readonly: true,
					value: data.item.gkuopDeptName,
					required: false
				}, {
					name: "surname",
					type: "text",
					label: "Фамилия",
					readonly: true,
					value: data.item.surname,
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
					name: "positionName",
					type: "text",
					label: "Должность",
					readonly: true,
					value: data.item.positionName,
					required: false
				}, {
					name: "personalNumber",
					type: "text",
					label: "Персональный номер",
					readonly: true,
					value: data.item.personalNumber,
					required: false
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					readonly: true,
					value: data.item.description,
					required: false
				}, {
					name: "visGkuopId",
					type: "text",
					label: "ID в ВИС ГКУ ОП",
					readonly: true,
					value: data.item.visGkuopId,
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