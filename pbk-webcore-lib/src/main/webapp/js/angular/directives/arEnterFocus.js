'use strict';

/**
 * @ngdoc directive
 * @name
 * @description
 * Директива для переключения фокуса контрола по нажатию кнопки enter.
 */
angular.module('armada').directive('arEnterFocus', function () {
	return {
		restrict: 'A',
		link: function (scope, elem, attrs) {
			elem.bind('keydown', function (e) {
				var code = e.keyCode || e.which;
				var arTabIndex = attrs.arTabIndex;
				if (isNaN(arTabIndex))
					return;
				arTabIndex = Number(arTabIndex);
				var nextTabIndex;
				if (code === 13) {
					nextTabIndex = arTabIndex + 1;
					e.preventDefault();
					$("[ar-tab-index=" + nextTabIndex + "]").focus();
				}
			});
		}
	}
});
