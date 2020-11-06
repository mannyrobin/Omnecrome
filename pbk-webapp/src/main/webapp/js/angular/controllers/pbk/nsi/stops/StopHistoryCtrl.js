angular.module('armada')
	.controller('StopHistoryCtrl', ['$scope', '$state', '$stateParams', 'StopsService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, StopsService, $controller, isNotReadOnlyUser) {
			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История Остановочного пункта", "stopHeadId", StopsService, $stateParams.itemId, $state.current.name + ".stopHistoryGrid",
				"StopHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/stops/StopHistoryDetailsDlg.html", isNotReadOnlyUser);

		}]);
