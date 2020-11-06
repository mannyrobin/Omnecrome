angular.module('armada')
	.controller('BsoNumberRuleInfoCtrl', ['$controller', '$scope', '$stateParams', 'BsoNumberRulesService', 'BsoTypesService', 'DepartmentsService', 'BranchesService', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, BsoNumberRulesService, BsoTypesService, DepartmentsService, BranchesService, isNotReadOnlyUser) {

			var year = new Date();
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "bsoTypeId",
					type: "select",
					load: BsoTypesService.getList,
					label: "Тип БСО",
					readonly: !isNotReadOnlyUser,
					required: true
				}, /*{
				 name: "bsoTypeCode",
				 type: "text",
				 label: "Код типа БСО",
				 readonly: !isNotReadOnlyUser,
				 required: false
				 },*/ {
					name: "branchCode",
					type: "text",
					label: "Код филиала",
					readonly: !isNotReadOnlyUser,
					required: false
				}, {
					name: "departmentId",
					type: "select",
					load: function () {
						return DepartmentsService.getDepartsForBso();
					},
					readonly: !isNotReadOnlyUser,
					label: "Подразделение",
					required: true
				}, {
					name: "departmentCode",
					type: "text",
					label: "Код подразделения",
					readonly: !isNotReadOnlyUser,
					required: false
				}, {
					name: "increment",
					type: "text",
					label: "Текущее значение",
					readonly: !isNotReadOnlyUser,
					required: true
				}, {
					name: "branchId",
					type: "select",
					load: BranchesService.getList,
					label: "Филиал",
					readonly: !isNotReadOnlyUser,
					required: false
				}/*, {
				 name: "year",
				 type: "date",
				 label: "Год",
				 value: year,
				 required: false
				 }*/];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, {
				editItem: BsoNumberRulesService.editItem,
				getItem: BsoNumberRulesService.getItem
			});
		}]);
