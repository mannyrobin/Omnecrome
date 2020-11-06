'use strict';

/**
 * @ngdoc function
 * @name armada.controller:StopInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('StopInfoCtrl', ['$controller', '$scope', '$stateParams', 'StopsService', 'GmStopsService', 'AppConfig', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, StopsService, GmStopsService, AppConfig, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "name",
					type: "text",
					label: "Название",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					readonly: true,
					required: true
				}, {
					name: "gmStopId",
					type: "select",
					label: "ID Остановочного пункт в ВИС ГИС МГТ",
					readonly: true,
					required: true,
					load: function () {
						return GmStopsService.getList([{
							name: "currentId",
							value: $scope.fields[1].value.id,
							type: "id"
						}
						]);
					}
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, StopsService);
		}]);