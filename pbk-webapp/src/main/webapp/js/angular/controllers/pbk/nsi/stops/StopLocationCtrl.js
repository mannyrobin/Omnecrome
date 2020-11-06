'use strict';

/**
 * @ngdoc function
 * @name armada.controller:StopLocationCtrl
 * @description
 *
 * Местоположение
 */
angular.module('armada')
	.controller('StopLocationCtrl', ['$controller', '$scope', '$stateParams', 'StopsService', 'ArMapService', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, StopsService, ArMapService, isNotReadOnlyUser) {

			$scope.mapServiceYa = ArMapService.initService({mapType: "ya"});
			$scope.mapServiceOther = ArMapService.initService({mapType: "other"});
			$scope.isMapYa = true;
			$scope.isMapOther = false;
			$scope.mapService = $scope.mapServiceYa;

			function onClickMap(lng, lat) {

			}

			$scope.onLoadMap = function () {
				if (isNotReadOnlyUser) {
					$scope.mapService.addOnClickFunc(onClickMap);
				}
				StopsService.getItem($scope.id).then(function (result) {
					$scope.point = {
						geoJsonPoint: {
							coordinates: [result.longitude, result.latitude],
							type: 'Point'
						},
						name: result.name
					};
					$scope.point = $scope.mapService.addPoint($scope.point);
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
						$scope.point = null;
						$scope.mapService = $scope.fields[2].value.id == 1 ? $scope.mapServiceOther : $scope.mapServiceYa;
						$scope.onLoadMap();
					}
				}];

			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, StopsService);
		}]);
