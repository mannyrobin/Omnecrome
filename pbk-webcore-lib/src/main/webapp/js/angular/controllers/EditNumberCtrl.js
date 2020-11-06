angular.module('armada')
	.controller('EditNumberCtrl', ['$scope', '$modalInstance', 'data',
		function ($scope, $modalInstance, data) {
			$scope.title = data.title;
			$scope.label = data.label;
			$scope.value = data.value;

			$scope.close = function () {
				$modalInstance.dismiss('cancel');
			};

			$scope.save = function () {
				$modalInstance.close({
					value: $scope.value
				});
			};
		}]);