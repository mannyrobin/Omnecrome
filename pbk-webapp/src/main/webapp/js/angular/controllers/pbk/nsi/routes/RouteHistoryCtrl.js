angular.module('armada')
	.controller('RouteHistoryCtrl', ['$scope', '$state', '$stateParams', 'RoutesService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, RoutesService, $controller, isNotReadOnlyUser) {

			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История Маршрута", "routeHeadId", RoutesService, $stateParams.itemId, $state.current.name + ".routeHistoryGrid",
				"RouteHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/routes/RouteHistoryDetailsDlg.html", isNotReadOnlyUser);

		}]);
