'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DepartmentLocationCtrl
 * @description
 *
 * Местоположение
 */
angular.module('armada')
	.controller('DepartmentLocationCtrl', ['$controller', '$scope', '$stateParams', 'DepartmentsService', 'ArMapService', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, DepartmentsService, ArMapService, isNotReadOnlyUser) {

			$scope.mapServiceYa = ArMapService.initService({mapType: "ya"});
			$scope.mapServiceOther = ArMapService.initService({mapType: "other"});
			$scope.isMapYa = true;
			$scope.isMapOther = false;
			$scope.mapService = $scope.mapServiceYa;
			$scope.point = undefined;
			$scope.name = undefined;

			function onClickMap(lng, lat) {
				$scope.fields[0].value = lng;
				$scope.fields[1].value = lat;
				$scope.$apply();
				if (angular.isDefined($scope.point)) {
					$scope.mapService.changePointLocation($scope.point, lng, lat);
				} else {
					addPoint(lng, lat);
				}
			}

			$scope.onLoadMap = function () {
				if (isNotReadOnlyUser) {
					$scope.mapService.addOnClickFunc(onClickMap);
				}
				DepartmentsService.getItem($scope.id).then(function (result) {
					$scope.name = result.name;
					addPoint(result.longitude, result.latitude);
				});
			};

			function addPoint(lng, lat) {
				if (lng != null && lat != null) {
					var point = {
						id: $scope.id,
						geoJsonPoint: {
							coordinates: [lng, lat],
							type: 'Point'
						},
						type: "department",
						name: $scope.name
					};
					$scope.point = $scope.mapService.addPoint(point);
				}
			}

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
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, DepartmentsService);
		}]);
