'use strict';

/**
 * @ngdoc function
 * @name armada.controller:RoleEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования роли.
 */
angular.module('armada')
	.controller('RolesEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'RolesService',
		function ($controller, $scope, $modalInstance, data, RolesService) {
			/* Поля ввода */
			$scope.fields = [{
				name: "name",
				type: "text",
				label: "Название",
				required: true
			}, {
				name: "description",
				type: "textarea",
				label: "Описание",
				required: false
			}, {
				name: "ldapRole",
				type: "text",
				label: "Имя группы в базе Active Directory",
				required: false
			}, {
				name: "isAllDepartments",
				type: "yesno",
				label: "Доступ ко всем подразделениям",
				required: true
			}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, RolesService, $modalInstance);
		}]);