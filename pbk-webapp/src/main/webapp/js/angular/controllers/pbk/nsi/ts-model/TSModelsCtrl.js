angular.module('armada')
	.controller('TSModelsCtrl', ['$scope', 'TSModelsService', 'TsModelsReportService', 'GridService', '$state', 'isNotReadOnlyUser', 'TsTypesService',
		function ($scope, TSModelsService, TsModelsReportService, GridService, $state, isNotReadOnlyUser, TsTypesService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = TSModelsService;
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Права */
			$scope.gridScope.perms = {
				//add: isNotReadOnlyUser,
				//remove: isNotReadOnlyUser,
				export: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Модель ТС",
					addTip: "Добавить модель ТС"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "model",
				label: "Марка Модель",
				href: function (row) {
					return "nsi/ts-models/" + row.id + "/info";
				},
				class: "col-md-2",
				get: function (row) {
					return row.make + ' ' + row.model;
				}
			}, {
				id: "tsTypeName",
				label: "Тип ТС",
				class: "col-md-2",
				get: function (row) {
					return row.tsTypeName + '(' + row.tsCapacities + ')';
				}
			}, {
				id: "passengerCountMax",
				label: "Вместимость пассажиров",
				class: "col-md-3",
				get: function (row) {
					return row.passengerCountMax + ' (' + row.seatCount + ')';
				}
			}, {
				id: "length",
				label: "Габариты",
				class: "col-md-3",
				get: function (row) {
					return row.length + ' - ' + row.width + ' - ' + row.height;
				}
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "make",
				type: "text",
				placeholder: "Марка"
			}, {
				name: "model",
				type: "text",
				placeholder: "Модель"
			}, {
				name: "tsType",
				type: "multiselect",
				placeholder: "Тип ТС",
				load: TsTypesService.getList
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти модель ТС",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить модель ТС",
					removeConfirm: "Вы действительно хотите удалить модель ТС?"
				},
				check: {
					isRemoveEnable: function (row) {
						if (!row) {
							return false;
						}
						return !row.delete;
					}
				},
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "model",
				gridName: $state.current.name + '.tsModelsGrid',
				load: $scope.gridScope.service.getPage,
				export: TsModelsReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/ts-model/TSModelsEditDlg.html", "TSModelsEditCtrl", "Создание модели ТС", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении модели ТС", $scope.gridScope, $scope.gridScope.service.removeItem, true);

			$scope.gridScope.addSelItemColumn = true;

			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.delete == true ? 'remove-text-record' : null;
			};

			$scope.gridScope.getRowClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.delete ? 'remove-record' : null;
			};
		}]);
