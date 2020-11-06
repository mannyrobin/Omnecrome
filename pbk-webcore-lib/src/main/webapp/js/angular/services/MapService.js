'use strict';

/**
 * @ngdoc service
 * @name armada.GoogleMapService
 * @description
 *
 * Сервис управления гугл картами.
 */
angular.module('armada')
	.service('MapService', [
		function () {
			return {
				setGoogleMarkerPosition: function (latlng, googleMarker, googleMap) {
					if (googleMarker == null) {
						googleMarker = new google.maps.Marker({position: latlng, map: googleMap});
					} else {
						googleMarker.setPosition(latlng);
					}
					return googleMarker;
				},
				setEventLocations: function (location, e) {
					location.lat = e.latLng.lat();
					location.lng = e.latLng.lng();
				},
				setFloatLocations: function (lat, lng, location) {
					location.lat = parseFloat(lat);
					location.lng = parseFloat(lng);
				},
				setYaCursor: function (lat, lng, yaMap, yaMarker) {
					if (yaMap == null)
						return;
					if (yaMarker == null) {
						yaMarker = new ymaps.Placemark([lng, lat]);
						yaMap.geoObjects
							.add(yaMarker);
					} else {
						yaMarker.geometry.setCoordinates([lng, lat]);
					}
					return yaMarker;
				},
				setYandexCursor: function (lat, lng, yaMap, myGeoObject) {
					if (yaMap == null)
						return;
					if (myGeoObject == null) {
						myGeoObject = new ymaps.GeoObject({
							// Описание геометрии.
							geometry: {
								type: "Point",
								coordinates: [lng, lat]
							},
							// Свойства.
							properties: {
								// Контент метки.
								iconContent: 'Здесь'
							}
						}, {
							// Опции.
							// Иконка метки будет растягиваться под размер ее содержимого.
							preset: 'islands#redStretchyIcon',
							// Метку можно перемещать.
							draggable: true
						});
						yaMap.geoObjects
							.add(myGeoObject);
					} else {
						myGeoObject.geometry.setCoordinates([lng, lat]);
					}
					return myGeoObject;
				},
				setNewYaCursor: function (lat, lng, yaMap, workTime, clickCallback) {
					if (yaMap == null)
						return;
					var myGeoObject = new ymaps.GeoObject({
						// Описание геометрии.
						geometry: {
							type: "Point",
							coordinates: [lng, lat]
						},
						// Свойства.
						properties: {
							// Контент метки.
							iconContent: workTime
						}
					}, {
						// Опции.
						// Иконка метки будет растягиваться под размер ее содержимого.
						preset: 'islands#redStretchyIcon',
						// Метку можно перемещать.
						draggable: false
					});
					if (clickCallback)
						myGeoObject.events.add('click', clickCallback);
					yaMap.geoObjects
						.add(myGeoObject);
					return myGeoObject;
				}

			}
		}]);
