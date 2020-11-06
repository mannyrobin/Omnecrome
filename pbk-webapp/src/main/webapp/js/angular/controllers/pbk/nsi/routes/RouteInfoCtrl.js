'use strict';

/**
 * @ngdoc function
 * @name armada.controller:RouteInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('RouteInfoCtrl', ['$controller', '$scope', '$stateParams', 'RoutesService', 'GmRoutesService', 'RouteTsKindsService', 'UtilService', 'AppConfig', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, RoutesService, GmRoutesService, RouteTsKindsService, UtilService, AppConfig, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "routeNumber",
					type: "text",
					label: "Номер маршрута",
					onChange: function () {
						$scope.fields[1].value = UtilService.getIntFromStr($scope.fields[0].value);
					},
					readonly: true,
					required: true
				}, {
					name: "routeNumberInt",
					type: "int",
					label: "Числовой номер маршрута",
					readonly: true,
					required: false
				}, {
					name: "asduRouteId",
					type: "text",
					label: "ID Маршрута в ВИС ASDU",
					readonly: true,
					required: false
				}, {
					name: "askpRouteCode",
					type: "text",
					label: "Код Маршрута в ВИС ASKP",
					readonly: true,
					required: false
				}, {
					name: "asmppRouteCode",
					type: "text",
					label: "Код Маршрута в ВИС ASMPP",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					readonly: true,
					required: false
				}, {
					name: "easuFhdRouteCode",
					type: "text",
					label: "Код Маршрута ЕАСУ ФХД",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					readonly: true,
					required: false
				}, {
					name: "routeId",
					type: "select",
					label: "Номер Маршрута в ВИС ГИС МГТ",
					load: function () {
						return GmRoutesService.getList([{
							name: "currentId",
							value: $scope.fields[6].value.id,
							type: "id"
						}
						]);
					},
					readonly: true,
					required: true
				}, {
					name: "routeTsKindId",
					type: "select",
					label: "Вид транспорта",
					load: function () {
						return RouteTsKindsService.getList([{
							name: "routeId",
							value: $stateParams.itemId,
							type: "id"
						}
						]);
					},
					readonly: true,
					required: true
				}, {
					name: "profitRatio",
					type: "number",
					label: "Коэффициент рентабельности",
					readonly: true,
					required: false
				}, {
					name: "isNight",
					type: "yesno",
					label: "Ночной маршрут",
					readonly: !isNotReadOnlyUser,
					required: false
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, RoutesService);
		}]);