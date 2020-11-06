angular.module('armada')
	.controller('RouteHistoryDetailsCtrl', ['$scope', '$modalInstance', 'data', '$controller', 'GmRoutesService', 'RouteTsKindsService',
		function ($scope, $modalInstance, data, $controller, GmRoutesService, RouteTsKindsService) {

			$scope.title = data.title;
			$scope.modalInstance = $modalInstance;

			$scope.fields = [
				{
					name: "createUser",
					type: "text",
					label: "Создавший пользователь",
					readonly: true,
					value: data.item.createUser,
					required: false
				}, {
					name: "createDate",
					type: "text",
					label: "Дата создания",
					readonly: true,
					value: data.item.createDate,
					required: false
				}, {
					name: "updateUser",
					type: "text",
					label: "Обновивший пользователь",
					readonly: true,
					value: data.item.updateUser,
					required: false
				}, {
					name: "updateDate",
					type: "text",
					label: "Дата обновления",
					readonly: true,
					value: data.item.updateDate,
					required: false
				}, {
					name: "versionStartDate",
					type: "text",
					label: "Начало периода",
					readonly: true,
					value: data.item.versionStartDate,
					required: false
				}, {
					name: "versionEndDate",
					type: "text",
					label: "Окончание периода",
					readonly: true,
					value: data.item.versionEndDate,
					required: false
				}, {
					name: "routeNumber",
					type: "text",
					label: "Номер маршрута",
					readonly: true,
					value: data.item.routeNumber,
					required: false
				}, {
					name: "routeNumberInt",
					type: "text",
					label: "Номер маршрута(число)",
					readonly: true,
					value: data.item.routeNumberInt,
					required: false
				}, {
					name: "asduRouteId",
					type: "text",
					label: "ID Маршрута в ВИС ASDU",
					readonly: true,
					value: data.item.asduRouteId,
					required: false
				}, {
					name: "askpRouteCode",
					type: "text",
					label: "Код Маршрута в ВИС ASKP",
					readonly: true,
					value: data.item.askpRouteCode,
					required: false
				}, {
					name: "asmppRouteCode",
					type: "text",
					label: "Код Маршрута в ВИС ASMPP",
					readonly: true,
					value: data.item.asmppRouteCode,
					required: false
				}, {
					name: "routeId",
					type: "select",
					label: "Номер Маршрута в ВИС ГИС МГТ",
					readonly: true,
					required: false
				}, {
					name: "routeTsKindId",
					type: "select",
					label: "Вид транспорта",
					readonly: true,
					required: false
				}, {
					name: "profitRatio",
					type: "number",
					label: "Коэффициент рентабельности",
					readonly: true,
					value: data.item.profitRatio,
					required: false
				}, {
					name: "easuFhdRouteCode",
					type: "text",
					label: "Код Маршрута ЕАСУ ФХД",
					readonly: true,
					value: data.item.easuFhdRouteCode,
					required: false
				}, {
					name: "active",
					type: "text",
					label: "Состояние",
					readonly: true,
					value: data.item.active ? "Актуально" : "Устарела",
					required: false
				}, {
					name: "isNight",
					type: "text",
					label: "Ночной маршрут",
					readonly: true,
					value: data.item.isNight ? "Да" : "Нет",
					required: false
				}];

			$controller('BaseHistoryDetailsCtrl', {$scope: $scope});
			$scope.init(data.item);

			$scope.fillSelectItemsParameterized(GmRoutesService.getList, "currentId", "routeId", 11);
			$scope.fillSelectItemsParameterized(RouteTsKindsService.getList, "routeId", "routeTsKindId", 12);

			$scope.close = function () {
				if ($scope.modalInstance) {
					$scope.modalInstance.close();
				}
			}

		}]);
