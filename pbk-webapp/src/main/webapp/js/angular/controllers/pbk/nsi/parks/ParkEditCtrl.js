'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ParkEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования парка.
 */
angular.module('armada')
	.controller('ParkEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'ParksService', 'GmParksService', 'TsTypesService', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, ParksService, GmParksService, TsTypesService, AppConfig) {

			$scope.fields = [{
				name: "shortName",
				type: "text",
				label: "Короткое название",
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE
			}, {
				name: "name",
				type: "text",
				label: "Название",
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE
			}, {
				name: "parkNumber",
				type: "int",
				label: "Номер парка",
				required: true,
				maxLength: 7
			}, {
				name: "tsKindId",
				type: "select",
				load: TsTypesService.getList,
				label: "Вид транспорта",
				required: true
			}, {
				name: "gmParkId",
				type: "select",
				label: "ГИС МГТ ИД парка",
				load: GmParksService.getList,
				required: false
			}, {
				name: "asduDepotId",
				type: "text",
				label: "ID парка в ВИС АСДУ",
				required: false,
				maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
			}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, {
				addItem: ParksService.addItem,
				getItem: ParksService.getItem
			}, $modalInstance)
		}]);