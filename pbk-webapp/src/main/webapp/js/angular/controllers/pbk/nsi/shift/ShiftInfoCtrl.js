'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ShiftInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('ShiftInfoCtrl', ['$controller', '$scope', '$stateParams', 'ShiftsService', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, ShiftsService, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "code",
					type: "text",
					label: "Код",
					readonly: true,
					required: true
				},
				{
					name: "name",
					type: "text",
					label: "Название",
					readonly: !isNotReadOnlyUser,
					required: true
				}, {
					name: "workStartTime",
					type: "time",
					label: "Начало",
					readonly: !isNotReadOnlyUser,
					required: true
				},
				{
					name: "workEndTime",
					type: "time",
					label: "Окончание",
					readonly: !isNotReadOnlyUser,
					required: true
				},
				{
					name: "breakStartTime",
					type: "time",
					label: "Начало",
					readonly: !isNotReadOnlyUser,
					required: true
				},
				{
					name: "breakEndTime",
					type: "time",
					label: "Окончание",
					readonly: !isNotReadOnlyUser,
					required: true
				},
				{
					name: "description",
					type: "textarea",
					label: "Описание",
					readonly: !isNotReadOnlyUser,
					required: false
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, {
				editItem: ShiftsService.editItem,
				getItem: ShiftsService.getItem
			});
		}]);