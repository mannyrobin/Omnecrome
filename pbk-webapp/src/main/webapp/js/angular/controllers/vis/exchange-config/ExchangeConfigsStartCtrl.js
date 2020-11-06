/**
 * @ngdoc function
 * @name armada.controller:ExchangeConfigsStartCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования конфигурации взаимодействия с ВИС.
 */
angular.module('armada')
	.controller('ExchangeConfigsStartCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'ExchangeConfigsService', 'Notification', 'UtilService',
		function ($controller, $scope, $modalInstance, data, ExchangeConfigsService, Notification, UtilService) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "start",
					type: "date",
					label: "Дата начала",
					required: true
				},
				{
					name: "end",
					type: "date",
					label: "Дата окончания",
					required: true
				},
				{
					name: "parameter",
					type: "text",
					label: "Параметр"
				}];

			$scope.reset = function () {
				$scope.item = {id: $scope.id};
				$scope.fields[0].value = new Date();
				$scope.fields[1].value = new Date();
			};

			$scope.save = function () {
				$scope.fillItem();
				ExchangeConfigsService.start($scope.item).then(function () {
					$scope.errTitle = "";
					$scope.errMessages = "";
					Notification.info("Обмен успешно запущен");
					if ($scope.modalInstance) {
						$scope.modalInstance.close();
					}
				}).catch(function (response) {
					var errors = UtilService.getFormErrors(response);
					$scope.errTitle = errors.errTitle;
					$scope.errMessages = errors.errMessages;
					Notification.error("Обмен запустить не удалось");
				});
			};

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, {}, $modalInstance);
		}]);
