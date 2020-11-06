'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ContactlessCardEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования округа.
 */
angular.module('armada')
	.controller('ContactlessCardEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'ContactlessCardsService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, ContactlessCardsService, AppConfig) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "cardNumber",
					type: "text",
					label: "Номер карты",
					required: true,
					//arFormat: "^\\w{10}$",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "uid",
					type: "text",
					label: "UID",
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
			$scope.init(data, $scope.fields, ContactlessCardsService, $modalInstance);
		}]);