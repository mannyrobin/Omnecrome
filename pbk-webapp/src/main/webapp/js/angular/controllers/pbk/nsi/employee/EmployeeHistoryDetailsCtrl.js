angular.module('armada')
	.controller('EmployeeHistoryDetailsCtrl', ['$scope', '$modalInstance', 'data', 'SexesService', 'DepartmentsService', 'DvrsService',
		'ContactlessCardsService', 'OfficialCardsService', 'PusksService', 'UsersService', 'CountiesService', '$controller',
		function ($scope, $modalInstance, data, SexesService, DepartmentsService, DvrsService,
				  ContactlessCardsService, OfficialCardsService, PusksService, UsersService, CountiesService, $controller) {

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
					name: "sexId",
					type: "select",
					label: "Пол",
					readonly: true,
					required: true
				}, {
					name: "easuFhdId",
					type: "text",
					label: "ID в ЕАСУ ФХД",
					value: data.item.easuFhdId,
					readonly: true,
					required: true
				}, {
					name: "personalNumber",
					type: "text",
					label: "Табельный номер",
					value: data.item.personalNumber,
					readonly: true,
					required: true
				}, {
					name: "licenceDetails",
					type: "text",
					label: "Реквизиты служебного удостоверения",
					value: data.item.licenceDetails,
					readonly: true,
					required: true
				}, {
					name: "departmentId",
					type: "select",
					label: "Подразделение",
					readonly: true,
					required: true
				}, {
					name: "dvrId",
					type: "select",
					label: "Видеорегистратор",
					readonly: true,
					required: false
				}, {
					name: "contCardId",
					type: "select",
					label: "БСК",
					readonly: true,
					required: false
				}, {
					name: "offCardId",
					type: "select",
					label: "СКК",
					readonly: true,
					required: false
				}, {
					name: "puskId",
					type: "select",
					label: "ПУсК",
					readonly: true,
					required: false
				}, {
					name: "userId",
					type: "select",
					label: "Пользователь",
					readonly: true,
					required: false
				}, {
					name: "positionName",
					type: "text",
					label: "Должность",
					value: data.item.positionName,
					readonly: true,
					required: false
				}, {
					name: "phone",
					type: "text",
					label: "Телефон",
					value: data.item.phone,
					readonly: true,
					required: false
				}, {
					name: "fireDate",
					type: "text",
					label: "Дата увольнения",
					value: data.item.fireDate,
					readonly: true
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					value: data.item.description,
					readonly: true,
					required: false
				}, {
					name: "forPlanUse",
					type: "text",
					label: "Участвует в планировании",
					value: data.item.forPlanUse ? "Да" : "Нет",
					readonly: true
				}, {
					name: "active",
					type: "text",
					label: "Состояние",
					readonly: true,
					value: data.item.active ? "Актуально" : "Устарела",
					required: false
				}, {
					name: "cityCountyId",
					type: "select",
					label: "Округ",
					readonly: true,
					required: false
				}, {
					name: "licenseEndDate",
					type: "text",
					label: "Срок действия",
					value: data.item.licenseEndDate,
					readonly: true
				}];

			$scope.close = function () {
				if ($scope.modalInstance) {
					$scope.modalInstance.close();
				}
			}

			$scope.fillSelectItems(SexesService.getList, "sexId", 9)
			$scope.fillSelectItemsParameterized(DepartmentsService.getList, "currentId", "departmentId", 13);
			$scope.fillSelectItemsParameterized(DvrsService.getListForEmployee, "dvrId", "dvrId", 14);
			$scope.fillSelectItemsParameterized(ContactlessCardsService.getListForEmployee, "contCardId", "contCardId", 15);
			$scope.fillSelectItemsParameterized(OfficialCardsService.getListForEmployee, "offCardId", "offCardId", 16);
			$scope.fillSelectItemsParameterized(PusksService.getListForEmployee, "puskId", "puskId", 17);
			$scope.fillSelectItemsParameterized(UsersService.getListForEmployee, "userId", "userId", 18);
			$scope.fillSelectItems(CountiesService.getList, "cityCountyId", 25);
		}]);
