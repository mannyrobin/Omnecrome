angular.module('armada')
	.controller('PuskEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'PusksService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, PusksService, AppConfig) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "puskNumber",
					type: "text",
					label: "Номер ПУсК",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "puskModel",
					type: "text",
					label: "Модель ПУсК",
					required: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					required: false,
					maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, PusksService, $modalInstance);
		}]);