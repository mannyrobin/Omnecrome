'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:arTimepickerRange
 * @description
 * Директива для отображения маршрута на карте
 * @deprecated НЕ ИСПОЛЬЗОВАТЬ для новых карт! Вместо этой директивы следует пользоваться map.
 */
angular.module('armada').directive('arGoogleMap', function () {
	var defaultCoords = {
		latitude: 55.755907,
		longitude: 37.628174
	};
	var options = {
		zoom: 12,
		center: new google.maps.LatLng(defaultCoords.latitude, defaultCoords.longitude),
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	return {
		restrict: 'A',
		scope: {
			points: "="
		},
		replace: true,
		template: '<div></div>',
		link: function (scope, element, attrs) {
			scope.$watch(function () {
				return scope.points;
			}, function (points) {
				if (points instanceof Array) {
					initMap();
				}
			});

			function initMap() {
				var map = new google.maps.Map(document.getElementById(attrs.id), options);
				angular.forEach(scope.points, function (point) {
					new google.maps.Marker({
						position: new google.maps.LatLng(point.lat, point.lng),
						map: map,
						zIndex: 9999999,
						title: point.name
					});
				});

				var transportPath = new google.maps.Polyline({
					path: scope.points,
					geodesic: true,
					strokeColor: '#FF0000',
					strokeOpacity: 1.0,
					strokeWeight: 2
				});
				transportPath.setMap(map);
			}
		}
	};
});
