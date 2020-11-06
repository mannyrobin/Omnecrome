'use strict';

angular.module('armada')
	.controller('UnionAnalysisCtrl', ['$controller', '$scope',
		function ($controller, $scope) {
			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.report.unionanalysis",
				tabs: [
					{heading: 'По маршрутам', route: 'routes'},
					{heading: 'По остановкам', route: 'stops'}
				]
			});
		}]);
