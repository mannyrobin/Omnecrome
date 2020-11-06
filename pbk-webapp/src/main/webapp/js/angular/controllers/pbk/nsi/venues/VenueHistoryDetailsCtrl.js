angular.module('armada')
	.controller('VenueHistoryDetailsCtrl', ['$scope', '$modalInstance', 'data', 'DistrictsService', 'WktParseService', '$controller',
		function ($scope, $modalInstance, data, DistrictsService, WktParseService, $controller) {

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
					name: "name",
					type: "text",
					label: "Название",
					readonly: true,
					value: data.item.name,
					required: false
				}, {
					name: "cod",
					type: "text",
					label: "Краткое наименование",
					readonly: true,
					value: data.item.cod,
					required: false
				}, {
					name: "description",
					type: "text",
					label: "Описание",
					value: data.item.description,
					readonly: true,
					required: false
				}, {
					name: "shiftI",
					type: "text",
					label: "Первая смена",
					value: data.item.shiftI == 1 ? "Да" : "Нет",
					readonly: true,
					required: false
				}, {
					name: "shiftII",
					type: "text",
					label: "Вторая смена",
					value: data.item.shiftII == 1 ? "Да" : "Нет",
					readonly: true,
					required: false
				}, {
					name: "shiftDay",
					type: "text",
					label: "Дневная смена",
					value: data.item.shiftDay == 1 ? "Да" : "Нет",
					readonly: true,
					required: false
				}, {
					type: "text",
					label: "Долгота",
					name: "longitude",
					readonly: true
				}, {
					type: "text",
					label: "Широта",
					name: "latitude",
					readonly: true
				}, {
					name: "districtId",
					type: "select",
					label: "Район",
					readonly: true,
					required: false
				}, {
					name: "active",
					type: "text",
					label: "Состояние",
					readonly: true,
					value: data.item.active ? "Актуально" : "Устарела",
					required: false
				}, {
					name: "shiftNight",
					type: "text",
					label: "Ночная смена",
					value: data.item.shiftNight == 1 ? "Да" : "Нет",
					readonly: true,
					required: false
				}, {
                    name: "shiftIII",
                    type: "text",
                    label: "Третья смена",
                    value: data.item.shiftIII == 1 ? "Да" : "Нет",
                    readonly: true,
                    required: false
                }];

			$controller('BaseHistoryDetailsCtrl', {$scope: $scope});
			$scope.init(data.item);
			$scope.fillSelectItems(DistrictsService.getList, "districtId", 14);

			var pointGeoJson = WktParseService.wktToGeoJson(data.item.wktGeom);
			if (pointGeoJson.coordinates != null) {
				$scope.fields[12].value = pointGeoJson.coordinates[0];
				$scope.fields[13].value = pointGeoJson.coordinates[1];
			}

			$scope.close = function () {
				if ($scope.modalInstance) {
					$scope.modalInstance.close();
				}
			}

		}]);
