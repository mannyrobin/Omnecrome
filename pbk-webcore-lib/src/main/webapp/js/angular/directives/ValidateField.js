'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:arValidateField
 * @description
 * Директива для валидации поля
 */
angular.module('armada').directive('arValidateField', ['$compile', function ($compile) {
	var FIELD_ERROR_TEMPLATES = {
		required: "Поле обязательно для заполнения",
		pattern: "Неверное значение поля",
		parse: "Неверное значение поля"
	};
	return {
		restrict: 'A',
		require: '^form',
		replace: false,
		scope: {},
		controller: function ($scope) {
			$scope.errorContainerVisible = false;
			$scope.fieldName = null;
			$scope.errors = {};

			$scope.setFieldName = function (fieldName) {
				$scope.fieldName = fieldName;
			};

			$scope.getErrorText = function (errorType) {
				return FIELD_ERROR_TEMPLATES[errorType];
			};
		},
		link: function (scope, element, attrs, formController) {
			angular.element(element).append($compile('<div class="error-container" ng-show="errorContainerVisible">' +
				'<span class="help-block" ng-repeat="(errorType, errorValue) in errors">{{::getErrorText(errorType)}}</span>' +
				'</div>')(scope));
			var fieldName = attrs.arValidateField;
			var aElement = angular.element(element);

			var unBindWatch = scope.$watch(function () {
				return formController[fieldName];
			}, function (field) {
				if (field) {
					unBindWatch();

					scope.errors = field.$error;
					scope.setFieldName(fieldName);

					if (field.$validators.required) {
						var labelElementArr = aElement.find('label');
						angular.forEach(labelElementArr, function (value) {
							var labelElement = angular.element(value);
							if (labelElement.attr('for') == fieldName) {
								labelElement.append('&nbsp;<span class="glyphicon glyphicon-asterisk text-danger"></span>');
							}
						});
					}
					scope.$watch(function () {
						return field.$invalid && field.$dirty;
					}, function (newValue, oldValue) {
						if (newValue != oldValue) {
							scope.errorContainerVisible = newValue;
							var hasErrorClass = aElement.hasClass('has-error');
							if (newValue && !hasErrorClass) {
								aElement.addClass('has-error');
							} else if (hasErrorClass) {
								aElement.removeClass('has-error');
							}
						}
					});
				}
			});
		}
	}
}]);