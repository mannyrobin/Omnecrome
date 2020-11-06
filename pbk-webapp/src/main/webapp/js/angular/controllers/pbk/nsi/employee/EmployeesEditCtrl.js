'use strict';

/**
 * @ngdoc function
 * @name armada.controller:EmployeesEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования роли.
 */
angular.module('armada')
	.controller('EmployeesEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'EmployeesService',
		'DepartmentsService', 'UsersService', 'ContactlessCardsService', 'OfficialCardsService', 'PusksService',
		'SexesService', 'DvrsService', function ($controller, $scope, $modalInstance, data, EmployeesService,
												 DepartmentsService, UsersService, ContactlessCardsService, OfficialCardsService, PusksService, SexesService, DvrsService) {


			/* Поля ввода */
			$scope.fields = [
				{
					name: "surname",
					type: "text",
					label: "Фамилия",
					required: true
				}, {
					name: "name",
					type: "text",
					label: "Имя",
					required: true
				}, {
					name: "patronumic",
					type: "text",
					label: "Отчество",
					required: false
				}, {
					name: "sexId",
					type: "select",
					load: SexesService.getList,
					label: "Пол",
					required: true
				}, {
					name: "easuFhdId",
					type: "text",
					label: "ID в ЕАСУ ФХД",
					required: true
				}, {
					name: "personalNumber",
					type: "text",
					label: "Табельный номер",
					required: true
				}, {
					name: "licenceDetails",
					type: "text",
					label: "Реквизиты служебного удостоверения",
					required: false
				}, {
					name: "positionName",
					type: "text",
					label: "Должность",
					required: false
				}, {
					name: "phone",
					type: "text",
					label: "Телефон",
					required: false
				}, {
					name: "fireDate",
					type: "date",
					label: "Дата увольнения"
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					required: false
				}, {
					name: "departmentId",
					type: "select",
					load: DepartmentsService.getList,
					label: "Подразделение",
					required: true
				}, {
					name: "userId",
					type: "select",
					load: UsersService.getListForEmployee,
					label: "Пользователь",
					required: false
				}, {
					name: "dvrId",
					type: "select",
					load: DvrsService.getListForEmployee,
					label: "Видеорегистратор",
					required: false
				}, {
					name: "contCardId",
					type: "select",
					load: ContactlessCardsService.getListForEmployee,
					label: "БСК",
					required: false
				}, {
					name: "offCardId",
					type: "select",
					load: OfficialCardsService.getListForEmployee,
					label: "СКК",
					required: false
				}, {
					name: "puskId",
					type: "select",
					load: PusksService.getListForEmployee,
					label: "ПУсК",
					required: false
				}, {
					name: "forPlanUse",
					type: "yesno",
					label: "Участвует в планировании",
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, EmployeesService, $modalInstance);

			$scope.fields[17].value = {id: 1, name: 'Да'};
			$scope.fields[17].list = [{id: 1, name: 'Да'}, {id: 0, name: 'Нет'}];
		}]);