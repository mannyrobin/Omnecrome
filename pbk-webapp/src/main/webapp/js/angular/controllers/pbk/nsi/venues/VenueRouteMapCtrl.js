angular.module('armada')
	.controller('VenueRouteMapCtrl', ['$scope', 'ArMapService', 'RoutesService',
		function ($scope, ArMapService, RoutesService) {
			$scope.service = ArMapService;
			var mapParams =
				{
					type: "ya"
				};
			$scope.mapService = ArMapService.initService(mapParams);

			$scope.routeNum = $scope.row.route;

			$scope.onLoadRouteMap = function () {
				RoutesService.getRouteTrajectory($scope.row.routeId).then(function (trajectories) {
					for (var i = 0; i < trajectories.length; i++) {
						function processTrajectory(trajectory) {
							var onStops = function (stops) {
								$scope.mapService.addRouteLine(trajectory, stops);
							};
							RoutesService.getRouteStops(trajectories[i].id, trajectories[i].trajectoryTypeId).then(onStops);
						}

						processTrajectory(trajectories[i]);
					}

				});
			};

		}]);