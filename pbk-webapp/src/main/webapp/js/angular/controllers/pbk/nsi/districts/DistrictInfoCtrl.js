'use strict';

/**
 * @ngdoc function
 * @name armada.controller:CountyInfoCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('DistrictInfoCtrl', ['$controller', '$scope', '$stateParams', 'DistrictsService', 'CountiesService', 'GmRegionsService', 'AppConfig', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, DistrictsService, CountiesService, GmRegionsService, AppConfig, isNotReadOnlyUser) {
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
					name: "cod",
					type: "text",
					label: "Код",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					readonly: true,
					required: true
				}, {
					name: "countyId",
					type: "select",
					load: function () {
						return CountiesService.getList([{
							name: "currentId",
							value: $scope.fields[2].value.id,
							type: "id"
						}
						]);
					},
					label: "Округ",
					readonly: true,
					required: true
				}, {
					name: "gmRegionId",
					type: "select",
					load: function () {
						return GmRegionsService.getList([{
							name: "currentId",
							value: $scope.fields[3].value.id,
							type: "id"
						}
						]);
					},
					label: "ID Района в ВИС ГИС МГТ",
					readonly: true,
					required: true
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE,
					readonly: !isNotReadOnlyUser,
					required: false
				}, {
					name: "planCountyId",
					type: "select",
					load: CountiesService.getList,
					label: "Округ для планирования",
					readonly: !isNotReadOnlyUser,
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, DistrictsService);
		}]);