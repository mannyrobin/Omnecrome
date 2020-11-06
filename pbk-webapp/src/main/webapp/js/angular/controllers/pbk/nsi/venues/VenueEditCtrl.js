'use strict';

/**
 * @ngdoc function
 * @name armada.controller:VenueEditCtrl
 * @description
 *
 * Место встречи
 */
angular.module('armada')
	.controller('VenueEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'VenuesService', 'CountiesService', 'DistrictsService', 'DepartmentsService', 'ArMapService',
		function ($controller, $scope, $modalInstance, data, VenuesService, CountiesService, DistrictsService, DepartmentsService, ArMapService) {

			$scope.mapServiceYa = ArMapService.initService({mapType: "ya"});
			$scope.mapServiceOther = ArMapService.initService({mapType: "other"});
			$scope.isMapYa = true;
			$scope.isMapOther = false;
			$scope.mapService = $scope.mapServiceYa;

			function onClickMap(lng, lat) {
				$scope.fields[7].value = lng;
				$scope.fields[8].value = lat;
				if ($scope.point == null) {
					var point = {
						id: $scope.id,
						geoJsonPoint: {
							coordinates: [lng, lat],
							type: 'Point'
						},
						type: "venue"
					};
					$scope.point = $scope.mapService.addPoint(point);
				} else {
					$scope.mapService.changePointLocation($scope.point, lng, lat);
				}
				$scope.$apply();
			};

			$scope.onLoadMap = function () {
				$scope.mapService.addOnClickFunc(onClickMap);
				if ($scope.fields[7].value != null && $scope.fields[8].value != null) {
					var point = {
						id: $scope.id,
						geoJsonPoint: {
							coordinates: [$scope.fields[7].value, $scope.fields[8].value],
							type: 'Point'
						},
						type: "venue"
					};
					$scope.point = $scope.mapService.addPoint(point);
				}
			};

			/* Поля ввода */
			$scope.fields = [
				{
					name: "name",
					type: "text",
					label: "Название",
					required: true
				}, {
					name: "countyId",
					type: "select",
					label: "Округ",
					load: CountiesService.getList,
					onChange: function () {
						$scope.fields[2].refresh();
					},
					required: true
				}, {
					name: "districtId",
					type: "select",
					label: "Район",
					load: function () {
						return DistrictsService.getList([{
							name: "countyId",
							value: $scope.fields[1].value,
							type: "select"
						}
						]);
					},
					required: true
				}, {
					name: "shiftI",
					type: "yesno",
					label: "Первая смена"
				}, {
					name: "shiftII",
					type: "yesno",
					label: "Вторая смена"
				}, {
					name: "shiftDay",
					type: "yesno",
					label: "Дневная смена"
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					required: false
				}, {
					type: "text",
					label: "Долгота",
					name: "longitude",
					readonly: true,
					required: true
				}, {
					type: "text",
					label: "Широта",
					name: "latitude",
					readonly: true,
					required: true
				}, {
					name: "deptId",
					type: "select",
					label: "Подразделение",
					load: function () {
						return DepartmentsService.getList([{
							name: "forPlanUse",
							value: 1,
							type: "id"
						}]);
					},
					required: true
				}, {
					name: "tpu",
					type: "yesno",
					label: "ТПУ"
				}, {
					name: "mapType",
					type: "mapType",
					label: "Тип карты",
					required: false,
					onChange: function () {
						$scope.isMapYa = $scope.fields[11].value.id == 0;
						$scope.isMapOther = $scope.fields[11].value.id == 1;
						$scope.mapService.removeAll();
						$scope.mapService = $scope.fields[11].value.id == 1 ? $scope.mapServiceOther : $scope.mapServiceYa;
						$scope.onLoadMap();
					}
				}, {
					name: "shiftNight",
					type: "yesno",
					label: "Ночная смена"
				}, {
					name: "deptIds",
					type: "multiselect",
					label: "Подразделения",
					load: function () {
						return DepartmentsService.getList([{
							name: "forPlanUse",
							value: 1,
							type: "id"
						}]);
					},
					required: true
				}, {
					name: "shiftIII",
					type: "yesno",
					label: "Третья смена"
				}];

			$scope.$watch('fields[12].value', function (newVal, oldVal) {
				$scope.procesNightTPU(oldVal);
			});
			$scope.$watch('fields[10].value', function (newVal, oldVal) {
				$scope.procesNightTPU(oldVal);
			});
			$scope.procesNightTPU = function (oldVal) {
				if ($scope.fields[12].value === undefined || $scope.fields[10].value === undefined) {
					return;
				}
				var idTPU = $scope.fields[10].value.id;
				var id = $scope.fields[12].value.id;
				$scope.fields[9].required = id == 0 ? true : false;
				$scope.fields[9].readonly = id == 0 ? false : true;

				$scope.fields[13].required = id != 0 && idTPU != 0 ? true : false;
				$scope.fields[13].readonly = id != 0 && idTPU != 0 ? false : true;

				if (!(oldVal === undefined)) {
					$scope.fields[9].value = null;
					$scope.fields[13].value = null;
				}
				$scope.fields[3].readonly = id == 0 ? false : true;
				$scope.fields[4].readonly = id == 0 ? false : true;
				$scope.fields[5].readonly = id == 0 ? false : true;
                $scope.fields[14].readonly = id == 0 ? false : true;

				if (id == 1) {
					$scope.fields[3].value = {id: 0, name: 'Нет'};
					$scope.fields[4].value = {id: 0, name: 'Нет'};
					$scope.fields[5].value = {id: 0, name: 'Нет'};
                    $scope.fields[14].value = {id: 0, name: 'Нет'};
				}
			}

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, VenuesService, $modalInstance);

			/* Заполнение полей "Да/Нет" */
			$scope.fields[3].value = {id: 0, name: 'Нет'};
			$scope.fields[4].value = {id: 0, name: 'Нет'};
			$scope.fields[5].value = {id: 0, name: 'Нет'};
			$scope.fields[10].value = {id: 0, name: 'Нет'};
			$scope.fields[12].value = {id: 0, name: 'Нет'};
            $scope.fields[14].value = {id: 0, name: 'Нет'};
			$scope.fields[3].list = [{id: 1, name: 'Да'}, {id: 0, name: 'Нет'}];
			$scope.fields[4].list = [{id: 1, name: 'Да'}, {id: 0, name: 'Нет'}];
			$scope.fields[5].list = [{id: 1, name: 'Да'}, {id: 0, name: 'Нет'}];
			$scope.fields[10].list = [{id: 1, name: 'Да'}, {id: 0, name: 'Нет'}];
			$scope.fields[12].list = [{id: 1, name: 'Да'}, {id: 0, name: 'Нет'}];
            $scope.fields[14].list = [{id: 1, name: 'Да'}, {id: 0, name: 'Нет'}];
		}]);