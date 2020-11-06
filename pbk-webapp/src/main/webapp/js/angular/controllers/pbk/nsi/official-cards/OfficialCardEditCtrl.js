'use strict';

/**
 * @ngdoc function
 * @name armada.controller:OfficialCardEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования округа.
 */
angular.module('armada')
	.controller('OfficialCardEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'OfficialCardsService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, OfficialCardsService, AppConfig) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "cardNumber",
					type: "text",
					label: "Номер карты",
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
			$scope.init(data, $scope.fields, OfficialCardsService, $modalInstance);
		}]);