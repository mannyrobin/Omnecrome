angular.module('armada')
	.controller('PuskInfoCtrl', ['$controller', '$scope', '$stateParams', 'PusksService', 'AppConfig', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, PusksService, AppConfig, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "puskNumber",
					type: "text",
					label: "Номер ПУсК",
					required: true,
					readonly: !isNotReadOnlyUser,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "puskModel",
					type: "text",
					label: "Модель ПУсК",
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
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, PusksService);
		}]);