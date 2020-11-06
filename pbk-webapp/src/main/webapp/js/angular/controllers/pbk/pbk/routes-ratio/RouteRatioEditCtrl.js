angular.module('armada')
	.controller('RouteRatioEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'RouteRatiosService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, RouteRatiosService, AppConfig) {
			$scope.isEdit = data.item === undefined;
			$scope.bExpired = $scope.isEdit && (data.bExpired === undefined || data.bExpired == null || data.bExpired == true);
			/* Поля ввода */
			$scope.fields = [
				{
					name: "routeRatio",
					type: "int",
					label: "Рейтинг маршрута",
					required: true,
					maxLength: 1,
					readonly: $scope.bExpired
				}, {
					name: "descr",
					type: "text",
					required: false,
					readonly: true,
					visible: false
				}, {
					type: "textarea",
					label: "Расчет рейтинга",
					required: false,
					readonly: true,
					rows: 6
				}, {
					name: "manual",
					type: "yesno",
					label: "Ручной ввод",
					required: false,
					readonly: $scope.bExpired
				}];

			$scope.$watch('fields[1].value', function () {
				if ($scope.fields[1].value != null && $scope.fields[1].value.length > 0 && $scope.fields[1].value[0] == "{") {
					var obj = eval("(" + $scope.fields[1].value + ")");
					$scope.fields[2].value = "Рейтинг на " + moment(data.workDate).format("DD.MM.YYYY") + ":\n"
						+ "ПП общ. нед. - " + obj["week"]["date"] + " - " + (obj["week"]["common"] == 1 ? "да" : "нет") + "\n"
						+ "ПП общ. год - " + obj["year"]["date"] + " - " + (obj["year"]["common"] == 1 ? "да" : "нет") + "\n"
						+ "ПП соц. нед. - " + obj["week"]["date"] + " - " + (obj["week"]["social"] == 1 ? "да" : "нет") + "\n"
						+ "ПП соц. год - " + obj["year"]["date"] + " - " + (obj["year"]["social"] == 1 ? "да" : "нет") + "\n"
						+ "Итоговый рейтинг: " + (eval(obj["week"]["common"]) + eval(obj["year"]["common"]) + eval(obj["week"]["social"]) + eval(obj["year"]["social"]));
					$scope.fields[1].value = null;
				}
			});

			$scope.isManualVisible = false;
			$scope.$watch('fields[3].value', function (newVal, oldVal) {
				if (oldVal === undefined && !(newVal === undefined) && newVal.id == 1) {
					$scope.isManualVisible = true;
				} else if ((oldVal === undefined || oldVal == null) && (!$scope.isEdit || (!(newVal === undefined) && newVal.id == 0))) {
					$scope.fields[3].value = {id: 1};
				}
			});

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, RouteRatiosService, $modalInstance);
		}]);