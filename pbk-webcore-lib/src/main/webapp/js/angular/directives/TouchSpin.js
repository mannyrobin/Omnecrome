angular.module('armada')
	.directive('arTouchSpin', [function () {
		return {
			restrict: 'E',
			require: 'ngModel',
			replace: true,
			scope: {},
			link: function (scope, element, attrs, ctrl) {
				var params = {
					verticalbuttons: true
				};
				if (angular.isDefined(attrs.min)) {
					params.min = attrs.min;
				}
				if (angular.isDefined(attrs.min)) {
					params.max = attrs.max;
				}
				$(element).TouchSpin(params);

				$(element).on('touchspin.on.startspin', function () {
					var value = angular.element(this).val();
					ctrl.$setViewValue(value);
					ctrl.$render(value);
				});
			},
			template: '<input id="demo_vertical" type="text">'
		};
	}]);
