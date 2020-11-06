angular.module('armada')
	.controller('DepartmentRuleEditCtrl', ['$controller', '$scope', '$state', '$stateParams', '$modalInstance', 'data', 'BsoNumberRulesService', 'BsoTypesService', 'DepartmentsService', 'BranchesService',
		function ($controller, $scope, $state, $stateParams, $modalInstance, data, BsoNumberRulesService, BsoTypesService, DepartmentsService, BranchesService) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "bsoTypeId",
					type: "select",
					load: BsoTypesService.getList,
					label: "Тип БСО",
					required: true
				}, /* {
				 name: "bsoTypeCode",
				 type: "text",
				 label: "Код типа БСО",
				 required: false
				 },*/ {
					name: "branchCode",
					type: "text",
					label: "Код филиала",
					required: false
				}, {
					name: "departmentId",
					type: "select",
					load: function () {
						return DepartmentsService.getDepartsForBso([{
							name: "currentId",
							value: $scope.fields[2].value.id,
							type: "id"
						}]);
					},
					label: "Подразделение",
					required: true,
					readonly: true,
					defval: {id: $stateParams.itemId}
				}, {
					name: "departmentCode",
					type: "text",
					label: "Код подразделения",
					required: false
				}, {
					name: "increment",
					type: "int",
					label: "Текущее значение",
					required: true
				}, {
					name: "branchId",
					type: "select",
					load: BranchesService.getList,
					label: "Филиал",
					required: false
				} /*, {
				 name: "year",
				 type: "date",
				 label: "Год",
				 value: year,
				 required: false
				 }*/];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, BsoNumberRulesService, $modalInstance);

			$scope.fields[5].value = {id: 1, name: 'Главный филиал'};
		}]);
