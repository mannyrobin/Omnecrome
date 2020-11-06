'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DistrictsEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования района.
 */
angular.module('armada')
	.controller('CountyDistrictEditCtrl', ['$controller', '$scope', '$state', '$stateParams', '$modalInstance', 'data', 'DistrictsService', 'CountiesService', 'GmRegionsService', 'AppConfig',
		function ($controller, $scope, $state, $stateParams, $modalInstance, data, DistrictsService, CountiesService, GmRegionsService, AppConfig) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "name",
					type: "text",
					label: "Название",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					required: true
				}, {
					name: "cod",
					type: "text",
					label: "Код",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
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
					defval: {id: $stateParams.itemId},
					label: "Округ",
					required: true,
					readonly: true
				}, {
					name: "gmRegionId",
					type: "select",
					load: GmRegionsService.getList,
					label: "Район в ВИС ГИС МГТ",
					required: true
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE,
					required: false
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, DistrictsService, $modalInstance);
		}]);