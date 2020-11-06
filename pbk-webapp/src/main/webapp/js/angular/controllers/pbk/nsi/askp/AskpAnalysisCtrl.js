'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TaskCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('AskpAnalysisCtrl', ['$controller', '$scope',
		function ($controller, $scope) {
			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.askp.analysis",
				tabs: [
					{heading: 'По маршрутам', route: 'routes'}
				]
			});
		}]);
