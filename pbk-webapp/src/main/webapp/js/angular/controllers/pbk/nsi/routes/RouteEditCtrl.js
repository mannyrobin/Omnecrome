'use strict';

/**
 * @ngdoc function
 * @name armada.controller:RouteEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования округа.
 */
angular.module('armada')
	.controller('RouteEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'RoutesService', 'GmRoutesService', 'RouteTsKindsService', 'UtilService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, RoutesService, GmRoutesService, RouteTsKindsService, UtilService, AppConfig) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "routeNumber",
					type: "text",
					label: "Номер маршрута",
					required: true,
					onChange: function () {
						$scope.fields[1].value = UtilService.getIntFromStr($scope.fields[0].value);
					},
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
				}, {
					name: "routeNumberInt",
					type: "int",
					label: "Числовой номер маршрута",
					readonly: true,
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					required: false
				}, {
					name: "asduRouteId",
					type: "text",
					label: "ID Маршрута в ВИС ASDU",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					required: false
				}, {
					name: "askpRouteCode",
					type: "text",
					label: "Код Маршрута в ВИС ASKP",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					required: false
				}, {
					name: "asmppRouteCode",
					type: "text",
					label: "Код Маршрута в ВИС ASMPP",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					required: false
				}, {
					name: "easuFhdRouteCode",
					type: "text",
					label: "Код Маршрута ЕАСУ ФХД",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					required: false
				}, {
					name: "routeId",
					type: "select",
					label: "Номер Маршрута в ВИС ГИС МГТ",
					load: GmRoutesService.getList,
					required: true
				}, {
					name: "routeTsKindId",
					type: "select",
					label: "Вид транспорта",
					load: RouteTsKindsService.getList,
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, RoutesService, $modalInstance);
		}]);