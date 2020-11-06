'use strict';

/**
 * @ngdoc directive
 * @name
 * @description
 * Директива для переключения фокуса контрола по нажатию стрелки вниз и вверх.
 */
angular.module('armada').directive('arFocus', function () {
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
				switch (code) {
					case 40:
						nextTabIndex = arTabIndex + 1;
						e.preventDefault();
						break;
					case 38:
						nextTabIndex = arTabIndex - 1;
						e.preventDefault();
						break;
				}
				$("[ar-tab-index=" + nextTabIndex + "]").focus();

			});
		}
	}
});
