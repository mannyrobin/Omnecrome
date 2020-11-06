'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:arValidateField
 * @description
 * Директива для валидации поля
 */
angular.module('armada').directive('arTooltip', function () {
	return {
		restrict: 'A',
        link: function (scope, element, attrs) {
            var jqe = angular.element(element);
            jqe.attr("data-toggle", "tooltip");
            jqe.attr("title", attrs.arTooltip);
            jqe.attr("data-container", "body");
            jqe.tooltip();
            scope.$on('$locationChangeStart', function closeTooltipOnLocationChangeSuccess() {
                jqe.tooltip("hide");
            });

            scope.$on('$stateChangeStart',function(){
                jqe.tooltip("hide");
            });
            $("body").click(function(e){
                jqe.tooltip("hide");
            });
        }
	};
});