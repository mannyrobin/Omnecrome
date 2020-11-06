'use strict';

/**
 * @ngdoc function
 * @name armada.controller:OfficialCardInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('OfficialCardInfoCtrl', ['$controller', '$scope', '$stateParams', 'OfficialCardsService', 'AppConfig', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, OfficialCardsService, AppConfig, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "cardNumber",
					type: "text",
					label: "Номер карты",
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
			$scope.init({id: $stateParams.itemId}, $scope.fields, OfficialCardsService, null, null, $scope.save);
		}]);