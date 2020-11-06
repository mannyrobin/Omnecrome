angular.module('armada')
	.controller('ParkInfoCtrl', ['$controller', '$scope', '$stateParams', 'ParksService', 'GmParksService', 'TsTypesService', 'AppConfig', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, ParksService, GmParksService, TsTypesService, AppConfig, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			$scope.fields = [{
				name: "shortName",
				type: "text",
				label: "Короткое название",
				required: true,
				readonly: true,
				maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE
			}, {
				name: "name",
				type: "text",
				label: "Название",
				required: true,
				readonly: true,
				maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE
			}, {
				name: "parkNumber",
				type: "int",
				label: "Номер парка",
				readonly: true,
				required: true,
				maxLength: 7
			}, {
				name: "tsKindId",
				type: "select",
				label: "Вид транспорта",
				load: TsTypesService.getList,
				readonly: true,
				required: true
			}, {
				name: "gmParkId",
				type: "select",
				label: "ГИС МГТ ИД парка",
				load: function () {
					return GmParksService.getList([{
						name: "currentId",
						value: $scope.fields[4].value.id,
						type: "id"
					}
					]);
				},
				readonly: true,
				required: false
			}, {
				name: "asduDepotId",
				type: "text",
				label: "ID парка в ВИС АСДУ",
				required: false,
				readonly: true,
				maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
			}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, ParksService);
		}]);