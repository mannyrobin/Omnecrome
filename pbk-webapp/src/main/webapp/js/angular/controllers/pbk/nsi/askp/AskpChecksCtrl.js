'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TaskCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('AskpChecksCtrl', ['$controller', '$scope',
		function ($controller, $scope) {
			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.askp.checks",
				tabs: [
					{heading: 'По выходам', route: 'moves'},
					{heading: 'По часам', route: 'hours'},
					{heading: 'Поостановочно', route: 'stops'}
				]
			});
		}]);
