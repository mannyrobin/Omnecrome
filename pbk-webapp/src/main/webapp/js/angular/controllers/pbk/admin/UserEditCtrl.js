'use strict';

/**
 * @ngdoc function
 * @name armada.controller:UserEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования пользователя.
 */
angular.module('armada')
	.controller('UserEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'UsersService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, UsersService, AppConfig) {
			/* Поля ввода */
			$scope.fields = [{
				name: "login",
				type: "text",
				label: "Логин",
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
			}, {
				name: "password",
				type: "text",
				label: "Пароль",
				inputType: "password",
				autocomplete: "off",
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
			}, {
				name: "passwordRepeat",
				type: "text",
				label: "Повторение пароля",
				inputType: "password",
				autocomplete: "off",
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
			}, {
				name: "name",
				type: "text",
				label: "Имя",
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
			}, {
				name: "expirationDate",
				type: "date",
				label: "Дата прекращения доступа",
				required: false
			}];

			var service = Object.create(UsersService);
			service.addItem = function (item) {
				item['isLdap'] = false;
				item['isActive'] = true;
				return UsersService.addItem(item);
			};

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, service, $modalInstance);
		}]);