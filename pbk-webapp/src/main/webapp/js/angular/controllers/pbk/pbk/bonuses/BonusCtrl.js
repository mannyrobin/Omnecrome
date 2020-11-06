angular.module('armada')
	.controller('BonusCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'BonusesService',
		function ($controller, $scope, $stateParams, UtilService, BonusesService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = BonusesService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.pbk.bonuses.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				]
			});
		}]);