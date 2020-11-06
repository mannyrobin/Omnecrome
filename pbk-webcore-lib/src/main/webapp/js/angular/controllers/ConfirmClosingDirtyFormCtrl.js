'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ConfirmClosingDirtyFormCtrl
 * @description
 *
 * Контроллер окна предупреждения, при закрытии формы
 */
angular.module('armada')
	.controller('ConfirmClosingDirtyFormCtrl', ['$scope', '$modalInstance', 'data',
		function ($scope, $modalInstance, data) {

			$scope.message = data && data.message ? data.message : "Вы уверены, что хотите закрыть форму вместе с введенными данными?";

			$scope.close = function () {
				$modalInstance.dismiss('cancel');
			};

			$scope.save = function () {
				$modalInstance.close({});
			};
		}]);