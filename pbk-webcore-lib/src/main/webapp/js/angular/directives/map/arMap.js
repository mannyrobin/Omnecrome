angular.module('armada').directive('arMap', ['$q', function ($q) {
	return {
		restrict: 'E',
		replace: true,
		scope: {
			idElem: '@id',
			service: '=service',
			onInit: '&initFunc',
			isNotReset: '@'
		},
		link: function link(scope, element, attrs) {
			var deferred = $q.defer();
			var promise = scope.service.initMap(deferred, scope.idElem, scope.isNotReset);
			promise.then(function (map) {
				if (angular.isDefined(map)
					&& angular.isDefined(scope.onInit)) {
					scope.onInit();
				}
			})
		},
		controller: function ($scope) {
		}
	};
}]);