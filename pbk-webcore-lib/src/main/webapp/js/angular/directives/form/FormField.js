'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:FormField
 * @description
 * Директива для отображения поля ввода на форме
 */
angular.module('armada').directive('arFormField', [function () {
	return {
		restrict: 'A',
		replace: true,
		scope: {
			colClass: '=',
			field: '='
		},
		controller: function ($scope) {
			if ($scope.colClass == null) {
				$scope.colClass = "col-sm-12";
			}
			if ($scope.field.placeholder == null) {
				$scope.field.placeholder = $scope.field.label;
			}
			if ($scope.field.required == null) {
				$scope.field.required = false;
			}
			if ($scope.field.readonly == null) {
				$scope.field.readonly = false;
			}
			if ($scope.field.inputType == null) {
				$scope.field.inputType = '';
			}
			if ($scope.field.autocomplete == null) {
				$scope.field.autocomplete = 'on';
			}

			if ($scope.field.type == "yesno") {
				$scope.field.list = [{id: 1, name: 'Да'}, {id: 0, name: 'Нет'}];
			}

			// Поле типа Legitimate
			if ($scope.field.type == "legitimate") {
				$scope.field.list = [{id: true, name: 'Да'}, {id: false, name: 'Нет'}];
			}


			if ($scope.field.type == "mapType") {
				if ($scope.field.value == null) {
					$scope.field.value = $scope.field.default == 1 ? {id: 1, name: 'ЕГКО'} : {id: 0, name: 'Яндекс'};
				}
				$scope.field.list = [{id: 0, name: 'Яндекс'}, {id: 1, name: 'ЕГКО'}];
			}

			if ($scope.field.value == null && $scope.field.default) {
				if ($scope.field.type == "multiselect") {
					$scope.field.value = [];
					var t = [];
					angular.forEach($scope.field.default, function (s) {
						$scope.field.value.push({id: s});
						t.push({id: s});
					});
				}
				else if ($scope.field.type == "select") {
					$scope.field.value = {id: $scope.field.default};
				}
				else if ($scope.field.type == "timeRange") {
					$scope.field.value = {fromDate: $scope.field.default.fromDate, toDate: $scope.field.default.toDate};
				}
				else if ($scope.field.type == "yesno") {
					$scope.field.value = $scope.field.default ? {id: 1, name: 'Да'} : {id: 0, name: 'Нет'};
					$scope.field.list = [{id: 1, name: 'Да'}, {id: 0, name: 'Нет'}];
				}
				else if ($scope.field.type == "mapType") {
					$scope.field.value = $scope.field.default == 1 ? {id: 1, name: 'ЕГКО'} : {id: 0, name: 'Яндекс'};
					$scope.field.list = [{id: 0, name: 'Яндекс'}, {id: 1, name: 'ЕГКО'}];
				}
				else {
					$scope.field.value = $scope.field.default;
				}
			}
		},
		templateUrl: 'templates/directives/form/formField.html'
	}
}]);