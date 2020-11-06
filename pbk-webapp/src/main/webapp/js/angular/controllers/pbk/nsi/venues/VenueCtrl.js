angular.module('armada')
	.controller('VenueCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'VenuesService', 'venue',
		function ($controller, $scope, $stateParams, UtilService, VenuesService, venue) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = VenuesService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.venues.detail",
				checkPermission: function (tab) {
					return tab.show;
				},
				tabs: [
					{heading: 'Общее', route: 'info', show: true},
					{heading: 'Местоположение', route: 'location', show: true},
					{heading: 'Районы', route: 'districts', show: !venue.shiftNight},
					{heading: 'История', route: 'history', show: true}
				]
			});
		}]);