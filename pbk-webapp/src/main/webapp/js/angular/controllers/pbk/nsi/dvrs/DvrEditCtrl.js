angular.module('armada')
	.controller('DvrEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'DvrsService', 'AppConfig', 'DepartmentsService',
		function ($controller, $scope, $modalInstance, data, DvrsService, AppConfig, DepartmentsService, isNotReadOnlyUser) {

			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Поля ввода */
			$scope.fields = [
				{
					name: "dvrNumber",
					type: "text",
					label: "Номер видеорегистратора",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "dvrModel",
					type: "text",
					label: "Модель видеорегистратора",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					required: false,
					maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE
				}, {
					name: "deptId",
					type: "select",
					label: "Подразделение",
					required: false,
					load: function () {
						return DepartmentsService.getList([{
							name: "forPlanUse",
							value: 1,
							type: "id"
						}, {
							name: "currentId",
							value: $scope.fields[3].value,
							type: "select"
						}]);
					},
					readonly: !isNotReadOnlyUser,
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, DvrsService, $modalInstance);
		}]);
