'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TsTypeCtrl
 * @description
 *
 * Тип ТС
 */
angular.module('armada')
	.controller('VenueInfoCtrl', ['$controller', '$scope', '$stateParams', 'VenuesService', 'CountiesService', 'DistrictsService', 'DepartmentsService', 'isNotReadOnlyUser', 'venue',
		function ($controller, $scope, $stateParams, VenuesService, CountiesService, DistrictsService, DepartmentsService, isNotReadOnlyUser, venue) {

			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			$scope.readonly = !isNotReadOnlyUser || venue.isDelete == 1;

			/* Поля ввода */
			$scope.fields = [
				{
					name: "name",
					type: "text",
					label: "Название",
					readonly: $scope.readonly,
					required: true
				}, {
					name: "countyId",
					type: "select",
					label: "Округ",
					readonly: $scope.readonly,
					load: function () {
						return CountiesService.getList([{
							name: "currentId",
							value: $scope.fields[1].value.id,
							type: "id"
						}
						]);
					},
					onChange: function () {
						$scope.fields[2].refresh();
					},
					required: true
				}, {
					name: "districtId",
					type: "select",
					label: "Район",
					readonly: $scope.readonly,
					load: function () {
						return DistrictsService.getList([{
							name: "countyId",
							value: $scope.fields[1].value.id,
							type: "id"
						}
						]);
					},
					required: true
				}, {
					name: "shiftI",
					type: "yesno",
					label: "Первая смена",
					readonly: $scope.readonly || venue.shiftNight
				}, {
					name: "shiftII",
					type: "yesno",
					label: "Вторая смена",
					readonly: $scope.readonly || venue.shiftNight
				}, {
					name: "shiftDay",
					type: "yesno",
					label: "Дневная смена",
					readonly: $scope.readonly || venue.shiftNight
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					readonly: $scope.readonly,
					required: false
				}, {
					name: "deptId",
					type: "select",
					label: "Подразделение",
					readonly: $scope.readonly || venue.shiftNight,
					load: function () {
						return DepartmentsService.getList([{
							name: "forPlanUse",
							value: 1,
							type: "id"
						}]);
					},
					required: true || venue.shiftNight
				}, {
					name: "tpu",
					type: "yesno",
					label: "ТПУ",
					readonly: $scope.readonly
				}, {
					name: "shiftNight",
					type: "yesno",
					label: "Ночная смена",
					readonly: $scope.readonly,
					afterLoad: function () {

					}
				}, {
					name: "deptIds",
					type: "multiselect",
					label: "Подразделения",
					readonly: $scope.readonly || !venue.shiftNight,
					load: function () {
						return DepartmentsService.getList([{
							name: "forPlanUse",
							value: 1,
							type: "id"
						}]);
					},
					required: true || !venue.shiftNight
				}, {
                    name: "shiftIII",
                    type: "yesno",
                    label: "Третья смена"
                }];

			$scope.$watch('fields[9].value', function (newVal, oldVal) {
				if (!(!(newVal === undefined) && !(oldVal === undefined) && newVal.id == oldVal.id)) {
					$scope.procesNightTPU(oldVal);
				}
			});
			$scope.$watch('fields[8].value', function (newVal, oldVal) {
				if (!(!(newVal === undefined) && !(oldVal === undefined) && newVal.id == oldVal.id)) {
					$scope.procesNightTPU(oldVal);
				}
			});
			$scope.procesNightTPU = function (oldVal) {
				if ($scope.fields[9].value === undefined || $scope.fields[8].value === undefined) {
					return;
				}
				var idTPU = $scope.fields[8].value.id;
				var id = $scope.fields[9].value.id;
				$scope.fields[7].required = id == 0 ? true : false;
				$scope.fields[7].readonly = $scope.readonly || (id == 0 ? false : true);

				$scope.fields[10].required = id != 0 && idTPU != 0 ? true : false;
				$scope.fields[10].readonly = $scope.readonly || (id != 0 && idTPU != 0 ? false : true);

				if (!(oldVal === undefined)) {
					$scope.fields[7].value = null;
					$scope.fields[10].value = null;
				}
				$scope.fields[3].readonly = $scope.readonly || (id == 0 ? false : true);
				$scope.fields[4].readonly = $scope.readonly || (id == 0 ? false : true);
				$scope.fields[5].readonly = $scope.readonly || (id == 0 ? false : true);
                $scope.fields[11].readonly = $scope.readonly || (id == 0 ? false : true);

				if (id == 1) {
					$scope.fields[3].value = {id: 0, name: 'Нет'};
					$scope.fields[4].value = {id: 0, name: 'Нет'};
					$scope.fields[5].value = {id: 0, name: 'Нет'};
                    $scope.fields[11].value = {id: 0, name: 'Нет'};
				}
			}

			$scope.isShowMultiDepts = function () {
				return $scope.fields[9].value != null && $scope.fields[9].value.id == 1 && $scope.fields[8].value != null && $scope.fields[8].value.id == 1;
			}

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, VenuesService);
		}]);