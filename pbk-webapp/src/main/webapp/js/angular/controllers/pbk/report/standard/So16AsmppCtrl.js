/**
 * @ngdoc function
 * @name armada.controller:So16AsmppCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования конфигурации взаимодействия с ВИС.
 */
angular.module('armada')
	.controller('So16AsmppCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'So16Service', 'Notification', 'UtilService',
		function ($controller, $scope, $modalInstance, data, So16Service, Notification, UtilService) {

			$scope.rowsCount = data.rows.length;
			$scope.inProgress = false;

			$scope.fields = [];

			$scope.save = function () {
				$scope.inProgress = true;
				var ids = [];
				for (var i = 0; i < data.rows.length; i++) {
					ids.push(data.rows[i].id);
				}

				So16Service.asmpp(ids).then(function () {
					$scope.inProgress = false;
					if ($scope.modalInstance) {
						$scope.modalInstance.close();
					}
				}).catch(function (response) {
					$scope.inProgress = false;
					var errors = UtilService.getFormErrors(response);
					$scope.errTitle = errors.errTitle;
					$scope.errMessages = errors.errMessages;
					Notification.error("Данные АСМПП получить не удалось");
				});
			};

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, {}, $modalInstance);
		}]);
