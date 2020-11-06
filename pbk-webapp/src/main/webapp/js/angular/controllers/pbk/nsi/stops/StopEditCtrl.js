'use strict';

/**
 * @ngdoc function
 * @name armada.controller:StopEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования округа.
 */
angular.module('armada')
	.controller('StopEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'StopsService', 'GmStopsService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, StopsService, GmStopsService, AppConfig) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "name",
					type: "text",
					label: "Название",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					required: true
				}, {
					name: "gmStopId",
					type: "select",
					label: "Остановочный пункт в ВИС ГИС МГТ",
					load: GmStopsService.getList,
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, StopsService, $modalInstance);
		}]);