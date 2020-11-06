'use strict';

/**
 * @ngdoc function
 * @name armada.controller:RequestErrorCtrl
 * @description
 *
 * Контроллер сообщений об ошибке.
 */
angular.module('armada')
	.controller('RequestErrorCtrl', ['$scope', '$modalInstance', 'data',
		function ($scope, $modalInstance, data) {
			$scope.title = data.title;
			$scope.message = data.message;
			$scope.errors = data.errors;
			$scope.stack = data.stack;
			$scope.name = data.name;

			$scope.isShowStack = function () {
				return $scope.stack != null;
			};

			$scope.close = function () {
				$modalInstance.dismiss('cancel');
			};

			$scope.isOpenStackMessage = false;

			$scope.toggleOpenStackMessage = function () {
				$scope.isOpenStackMessage = !$scope.isOpenStackMessage;
			}

		}]);