angular.module('armada')
	.controller('TsTypesCtrl', ['$scope', 'TsTypesService', 'TsTypesReportService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, TsTypesService, TsTypesReportService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = TsTypesService;
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
					title: "Типы ТС",
					addTip: "Добавить тип ТС",
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				href: function (row) {
					return "nsi/ts-types/" + row.id + "/info";
				}
			}, {
				id: "tsName",
				label: "Тип ТС ГИС МГТ"
			}, {
				id: "description",
				label: "Описание"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "name",
				type: "text",
				placeholder: "Название"
			}];

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить тип ТС",
					removeConfirm: "Вы действительно хотите удалить тип ТС?"
				},
				check: {
					isRemoveEnable: function (row) {
						return row['delete'] != undefined && !row.delete;
					}
				},
				gridScope: $scope.gridScope
			};

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти тип ТС",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.tsTypesGrid',
				load: $scope.gridScope.service.getPage,
				export: TsTypesReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/ts-types/TsTypeEditDlg.html", "TsTypeEditCtrl", "Создание типа ТС", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении типа ТС", $scope.gridScope, $scope.gridScope.service.removeItem, true);
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
