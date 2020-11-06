'use strict';

/**

 */
angular.module('armada')
	.controller('GraficInfoCtrl', ['$controller', '$scope', '$stateParams', 'GraficsService', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, GraficsService, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "code",
					type: "text",
					label: "Код",
					readonly: !isNotReadOnlyUser,
					required: true
				},
                {
                    name: "name",
                    type: "text",
                    label: "Название",
                    readonly: !isNotReadOnlyUser,
                    required: true
                },
                {
                    name: "firstDate",
                    type: "date",
                    label: "Дневная",
                    readonly: !isNotReadOnlyUser,
                    required: true
                },
                {
                    name: "secondDate",
                    type: "date",
                    label: "Ночная",
                    readonly: !isNotReadOnlyUser,
                    required: true
                },
                {
                    name: "thirdDate",
                    type: "date",
                    label: "Выходной 1",
                    readonly: !isNotReadOnlyUser,
                    required: true
                },
                {
                    name: "fourthDate",
                    type: "date",
                    label: "Выходной 2",
                    readonly: !isNotReadOnlyUser,
                    required: true
                }];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, {
				editItem: GraficsService.editItem,
				getItem: GraficsService.getItem
			});
		}]);