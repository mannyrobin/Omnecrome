'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ConfirmDialogCtrl
 * @description
 *
 * Контроллер диалога подтверждения.
 */
angular.module('armada')
	.controller('ConfirmDialogCtrl', ['$scope', '$modalInstance', 'data',
		function ($scope, $modalInstance, data) {
			$scope.title = data.title;
			$scope.message = data.message;
			$scope.aTitle = data.aTitle;
			$scope.aHref = data.aHref;

			$scope.close = function () {
				$modalInstance.dismiss('cancel');
			};

			$scope.save = function () {
				$modalInstance.close({});
			};

		}]);