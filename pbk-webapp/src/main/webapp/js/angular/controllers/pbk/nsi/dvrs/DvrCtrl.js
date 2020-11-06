angular.module('armada')
	.controller('DvrCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'DvrsService',
		function ($controller, $scope, $stateParams, UtilService, DvrsService) {

			$scope.getItemCrumb = function (item) {
				return item.dvrNumber;
			};

			$scope.getItem = DvrsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.dvrs.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'История', route: 'history'},
					{heading: 'Записи', route: 'records'},
					{heading: 'История владения', route: 'ownershistory'}
				]
			});
		}]);