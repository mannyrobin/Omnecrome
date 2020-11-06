angular.module('armada')
	.controller('EquipmentHistoryCtrl', ['$scope', '$state', '$stateParams', 'EquipmentsService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, EquipmentsService, $controller, isNotReadOnlyUser) {

			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История бортового оборудования", "equipmentHeadId", EquipmentsService, $stateParams.itemId, $state.current.name + ".equipmentHistoryGrid",
				"EquipmentsHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/equipment/EquipmentHistoryDetails.html", isNotReadOnlyUser);

		}]);
