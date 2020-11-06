angular.module('armada').directive('customTooltipPopup', function () {
	return {
		restrict: 'A',
		replace: true,
		scope: {content: '@', placement: '@', animation: '&', isOpen: '&'},
		templateUrl: 'js/angular/directives/tooltip/customTooltip.html'
	};
})

	.directive('customTooltip', ['$tooltip', function ($tooltip) {
		return $tooltip('customTooltip', 'tooltip', 'mouseenter');
	}]);
