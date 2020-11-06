'use strict';

angular.module('armada')
	.controller('AuditInfoCtrl', ['$controller', '$scope', '$stateParams', 'AuditsService',
		function ($controller, $scope, $stateParams, AuditsService) {
			$scope.fields = [{
				name: "auditTypeId",
				type: "select",
				label: "Тип события",
				load: AuditsService.getTypes,
				readonly: true,
				required: true
			}, {
				name: "auditLevelId",
				type: "select",
				label: "Уровень",
				load: AuditsService.getLevels,
				readonly: true,
				required: true
			}, {
				name: "objOperationId",
				type: "select",
				label: "Тип операции",
				load: AuditsService.getOperations,
				readonly: true,
				required: true,
			}, {
				name: "objInfo",
				type: "textarea",
				label: "Объект",
				readonly: true,
				required: true
			}, {
				name: "createDate",
				type: "text",
				label: "Дата",
				readonly: true,
				required: true
			}, {
				name: "message",
				type: "textarea",
				label: "Сообщение",
				readonly: true,
				required: true
			}, {
				name: "stackTrace",
				type: "textarea",
				label: "Стек",
				readonly: true,
				required: false
			}, {
				name: "userIPAddress",
				type: "text",
				readonly: true,
				label: "ip адрес",
				required: false
			}, {
				name: "threadInfo",
				type: "text",
				readonly: true,
				label: "Нить выполнения",
				required: false
			}, {
				name: "createUserInfo",
				type: "text",
				readonly: true,
				label: "Пользователь",
				required: false
			}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, {getItem: AuditsService.getItem});
		}]);