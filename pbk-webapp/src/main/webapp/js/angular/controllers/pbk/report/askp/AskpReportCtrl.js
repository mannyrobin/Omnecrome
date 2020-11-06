'use strict';

angular.module('armada')
	.controller('AskpReportCtrl', ['$controller', '$scope',
		function ($controller, $scope) {
			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.report.askp",
				tabs: [
					{heading: 'Пассажиропоток по остановке', route: 'stops'}
				]
			});
		}]);
