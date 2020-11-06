angular.module('armada')
	.controller('DvrInfoCtrl', ['$controller', '$scope', '$stateParams', 'DvrsService', 'AppConfig', 'isNotReadOnlyUser', 'DepartmentsService',
		function ($controller, $scope, $stateParams, DvrsService, AppConfig, isNotReadOnlyUser, DepartmentsService) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "dvrNumber",
					type: "text",
					label: "Номер видеорегистратора",
					required: true,
					readonly: !isNotReadOnlyUser,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "dvrModel",
					type: "text",
					label: "Модель видеорегистратора",
					required: true,
					readonly: !isNotReadOnlyUser,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					required: false,
					readonly: !isNotReadOnlyUser,
					maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE
				}, {
					name: "deptId",
					type: "select",
					label: "Подразделение",
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
					required: false,
					readonly: !isNotReadOnlyUser,
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, DvrsService);
		}]);