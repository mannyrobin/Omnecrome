angular.module('armada')
	.controller('EditDateCtrl', ['$scope', '$modalInstance', 'data',
		function ($scope, $modalInstance, data) {
			$scope.title = data.title;
			$scope.label = data.label;
			$scope.date = data.date;

			$scope.close = function () {
				$modalInstance.dismiss('cancel');
			};

			$scope.save = function () {
				$modalInstance.close({
					selectedDate: $scope.date
				});
			};
		}]);