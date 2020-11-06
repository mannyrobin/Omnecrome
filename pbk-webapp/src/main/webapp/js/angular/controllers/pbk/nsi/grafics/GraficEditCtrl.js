'use strict';

/**
 * @ngdoc function
 * @name armada.controller:VenueEditCtrl
 * @description
 *
 * Место встречи
 */
angular.module('armada')
	.controller('GraficEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'GraficsService',
        function ($controller, $scope, $modalInstance, data, GraficsService) {

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
                },
                {
                    name: "firstDate",
                    type: "date",
                    label: "Дневная",
                    required: true
                },
                {
                    name: "secondDate",
                    type: "date",
                    label: "Ночная",
                    required: true
                },
                {
                    name: "thirdDate",
                    type: "date",
                    label: "Выходной 1",
                    required: true
                },
                {
                    name: "fourthDate",
                    type: "date",
                    label: "Выходной 2",
                    required: true
                }];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, GraficsService, $modalInstance);

		}]);