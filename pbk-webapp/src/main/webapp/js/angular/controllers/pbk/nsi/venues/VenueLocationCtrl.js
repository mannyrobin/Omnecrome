'use strict';

/**
 * @ngdoc function
 * @name armada.controller:VenueLocationCtrl
 * @description
 *
 * Местоположение
 */
angular.module('armada')
	.controller('VenueLocationCtrl', ['$controller', '$scope', '$stateParams', 'VenuesService', 'ArMapService', 'isNotReadOnlyUser', 'venue',
		function ($controller, $scope, $stateParams, VenuesService, ArMapService, isNotReadOnlyUser, venue) {

			$scope.readonly = !isNotReadOnlyUser || venue.isDelete == 1;
			$scope.mapServiceYa = ArMapService.initService({mapType: "ya"});
			$scope.mapServiceOther = ArMapService.initService({mapType: "other"});
			$scope.isMapYa = true;
			$scope.isMapOther = false;
			$scope.mapService = $scope.mapServiceYa;

			function onClickMap(lng, lat) {
				$scope.fields[0].value = lng;
				$scope.fields[1].value = lat;
				$scope.$apply();
				$scope.mapService.changePointLocation($scope.point, lng, lat);
			}

			$scope.onLoadMap = function () {
				if (!$scope.readonly) {
					$scope.mapService.addOnClickFunc(onClickMap);
				}
				VenuesService.getItem($scope.id).then(function (result) {
					if (result.longitude != null && result.latitude != null) {
						var point = {
							id: $scope.id,
							geoJsonPoint: {
								coordinates: $scope.point != null ? [$scope.fields[0].value, $scope.fields[1].value] : [result.longitude, result.latitude],
								type: 'Point'
							},
							type: "venue",
							name: result.name
						};
						$scope.point = $scope.mapService.addPoint(point);
					}
				});
			};

			$scope.fields = [
				{
					type: "text",
					label: "Долгота",
					name: "longitude",
					readonly: true
				},
				{
					type: "text",
					label: "Широта",
					name: "latitude",
					readonly: true
				}, {
					name: "mapType",
					type: "mapType",
					label: "Тип карты",
					required: false,
					onChange: function () {
						$scope.isMapYa = $scope.fields[2].value.id == 0;
						$scope.isMapOther = $scope.fields[2].value.id == 1;
						$scope.mapService.removeAll();
						$scope.mapService = $scope.fields[2].value.id == 1 ? $scope.mapServiceOther : $scope.mapServiceYa;
						$scope.onLoadMap();
					}
				}];

			$scope.callbackResetAction = function () {
				if (angular.isDefined($scope.point)) {
					$scope.mapService.changePointLocation($scope.point, $scope.fields[0].value, $scope.fields[1].value);
				}
			};

			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, VenuesService);
		}]);
