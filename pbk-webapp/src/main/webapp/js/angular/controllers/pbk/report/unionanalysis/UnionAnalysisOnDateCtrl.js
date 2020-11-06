'use strict';

angular.module('armada')
	.controller('UnionAnalysisOnDateCtrl', ['$controller', '$scope',
		function ($controller, $scope) {
			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.report.unionanalysisondate",
				tabs: [
					{heading: 'По маршрутам и выходам', route: 'move-routes'},
					{heading: 'По рейсам маршрута-выхода', route: 'trip-move-routes'},
					{heading: 'По остановкам рейса', route: 'trip-stops'}
				]
			});
		}]);
