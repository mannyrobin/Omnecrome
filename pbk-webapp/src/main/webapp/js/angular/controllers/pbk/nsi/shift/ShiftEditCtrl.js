'use strict';

/**
 * @ngdoc function
 * @name armada.controller:VenueEditCtrl
 * @description
 *
 * Место встречи
 */
angular.module('armada')
	.controller('ShiftEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'ShiftsService',
        function ($controller, $scope, $modalInstance, data, ShiftsService) {

			/* Поля ввода */
			$scope.fields = [
                {
                    name: "code",
                    type: "text",
                    label: "Код",
                    required: true
                },
                {
                    name: "name",
                    type: "text",
                    label: "Название",
                    required: true
                }, {
                    name: "workStartTime",
                    type: "time",
                    label: "Начало",
                    required: true
                },
                {
                    name: "workEndTime",
                    type: "time",
                    label: "Окончание",
                    required: true
                },
                {
                    name: "breakStartTime",
                    type: "time",
                    label: "Начало",
                    required: true
                },
                {
                    name: "breakEndTime",
                    type: "time",
                    label: "Окончание",
                    required: true
                },
                {
                    name: "reserveCount",
                    type: "text",
                    defval: 0,
                    hide: true
                },
                {
                    name: "description",
                    type: "textarea",
                    label: "Описание",
                    required: false
                }];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, ShiftsService, $modalInstance);

		}]);