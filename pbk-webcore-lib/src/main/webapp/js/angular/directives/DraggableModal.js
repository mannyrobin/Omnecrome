angular.module('armada')
	.directive('draggableModal', function () {
		return {
			restrict: 'EA',
			link: function (scope, element, attrs) {
				$(element).closest(".draggable").draggable({
					handle: '.modal-header',
					cursor: 'crosshair',
					containment: $(element).closest(".modal"),
					scroll: false
				});
			}
		}
	});
