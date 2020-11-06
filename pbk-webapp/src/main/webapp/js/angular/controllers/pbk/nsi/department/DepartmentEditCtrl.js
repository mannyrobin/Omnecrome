angular.module('armada')
	.controller('DepartmentEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'DepartmentsService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, DepartmentsService, AppConfig) {
			$scope.fields = [{
				name: "easuFhdId",
				type: "text",
				label: "ID в ЕАСУ ФХД",
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
			}, {
				name: "name",
				type: "text",
				label: "Название",
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
			}, {
				name: "fullName",
				type: "text",
				label: "Полное наименование",
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.X_LONG_INPUT_SIZE
			}, {
				name: "parentDeptId",
				type: "select",
				load: DepartmentsService.getList,
				label: "Родительское подразделение",
				required: false
			}, {
				name: "description",
				type: "textarea",
				label: "Описание",
				required: false,
				maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE
			}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, {
				addItem: DepartmentsService.addItem,
				getItem: DepartmentsService.getItem
			}, $modalInstance);
		}]);