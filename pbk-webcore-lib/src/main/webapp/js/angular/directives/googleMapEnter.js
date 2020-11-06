'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:arTimepickerRange
 * @description
 * Директива для ввода точек на карте
 * @deprecated НЕ ИСПОЛЬЗОВАТЬ для новых карт! Вместо этой директивы следует пользоваться map.
 */
angular.module('armada').directive('arGoogleMapEnter', ['$timeout', function ($timeout) {
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
			location: "="
			//callback: "="
		},
		replace: true,
		template: '<div></div>',
		link: function (scope, element, attrs) {
			var map = null;

			$timeout(function () {
				map = new google.maps.Map(document.getElementById(attrs.id), options);

				setMarker(new google.maps.LatLng(scope.location.lat, scope.location.lng), attrs.tooltip);

				map.addListener('click', function (event) {
					setMarker(event.latLng, attrs.tooltip);
					scope.location.lat = event.latLng.lat();
					scope.location.lng = event.latLng.lng();
					//scope.location = {
					//    lat: event.latLng.lat(),
					//    lng: event.latLng.lng()
					//}
					//scope.callback(event.latLng.lat(), event.latLng.lng());
				});
			});

			var marker = null;

			scope.$watch(function () {
				return scope.location;
			}, function (location) {
				if (location && location != null && marker != null) {
					setMarker(new google.maps.LatLng(location.lat, location.lng), attrs.tooltip)
				}
			});

			function setMarker(latlng, tooltip) {
				if (marker == null) {
					marker = new google.maps.Marker({
						position: latlng,
						map: map,
						zIndex: 9999999,
						title: tooltip && tooltip != null ? tooltip : ""
					});
				} else {
					marker.setPosition(latlng);
				}
			}
		}
	};
}]);