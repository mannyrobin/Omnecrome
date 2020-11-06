'use strict';

angular.module('armada')
	.controller('RoleInfoCtrl', ['$controller', '$scope', '$stateParams', 'RolesService', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, RolesService, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			$scope.fields = [{
				name: "name",
				type: "text",
				label: "Название",
				readonly: !isNotReadOnlyUser,
				required: true
			}, {
				name: "description",
				type: "textarea",
				label: "Описание",
				readonly: !isNotReadOnlyUser,
				required: false
			}, {
				name: "ldapRole",
				type: "text",
				readonly: !isNotReadOnlyUser,
				label: "Имя группы в базе Active Directory",
				required: false
			}, {
				name: "isAllDepartments",
				type: "yesno",
				readonly: !isNotReadOnlyUser,
				label: "Доступ ко всем подразделениям",
				required: true
			}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, {
				editItem: RolesService.editItem,
				getItem: RolesService.getItem
			});
		}]);