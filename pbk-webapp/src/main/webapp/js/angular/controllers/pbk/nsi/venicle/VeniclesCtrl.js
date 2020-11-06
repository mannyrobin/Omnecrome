angular.module('armada')
	.controller('VeniclesCtrl', ['$scope', 'VenicleService', 'TsTypesService', 'VehiclesReportService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, VenicleService, TsTypesService, VehiclesReportService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = VenicleService;

			/* Права */
			$scope.gridScope.perms = {
				//add: isNotReadOnlyUser,
				//remove: isNotReadOnlyUser,
				export: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "ТС",
					addTip: "Добавить ТС"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "model",
				label: "Марка Модель",
				class: "col-md-2",
				get: function (row) {
					var result = '';
					if (row.make) {
						result = row.make + ' ';
					}
					return result + row.model;
					;
				},
				href: function (row) {
					return "nsi/venicles/" + row.id + "/info";
				}
			}, {
				id: "tsTypeName",
				label: "Тип ТС",
				class: "col-md-1"
			}, {
				id: "tsCapacities",
				label: "Вместимость",
				class: "col-md-1"
				//disableSort: true
			}, {
				id: "depoNumber",
				label: "Бортовой номер",
				class: "col-md-2"
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
			}, {
				name: "depoNumber",
				type: "text",
				placeholder: "Бортовой номер"
			}, {
				name: "stateNumber",
				type: "text",
				placeholder: "Гос номер"
			}, {
				name: "vinNumber",
				type: "text",
				placeholder: "ВИН номер"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти ТС",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить ТС",
					removeConfirm: "Вы действительно хотите удалить ТС?"
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
				defaultPredicate: "depoNumber",
				gridName: $state.current.name + '.tsGrid',
				load: $scope.gridScope.service.getPage,
				export: VehiclesReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/venicles/VenicleEditDlg.html", "VenicleEditCtrl", "Создание ТС", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении ТС", $scope.gridScope, $scope.gridScope.service.removeItem, true);

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
