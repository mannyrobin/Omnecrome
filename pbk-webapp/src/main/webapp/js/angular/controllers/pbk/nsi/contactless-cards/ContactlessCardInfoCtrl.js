'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ContactlessCardInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('ContactlessCardInfoCtrl', ['$controller', '$scope', '$stateParams', 'ContactlessCardsService', 'AppConfig', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, ContactlessCardsService, AppConfig, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "cardNumber",
					type: "text",
					label: "Номер карты",
					required: true,
					readonly: !isNotReadOnlyUser,
					//arFormat: "^\\w{10}$",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
				}, {
					name: "uid",
					type: "text",
					label: "UID",
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
			$scope.init({id: $stateParams.itemId}, $scope.fields, ContactlessCardsService);
		}]);