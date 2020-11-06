angular.module('armada')
	.controller('TsCapacitiesCtrl', ['$scope', 'TsCapacitiesService', 'TsCapacitiesReportService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, TsCapacitiesService, TsCapacitiesReportService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = TsCapacitiesService;
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
					title: "Вместимость ТС",
					addTip: "Добавить вместимость ТС",
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				href: function (row) {
					return "nsi/ts-capacities/" + row.id + "/info";
				}
			}, {
				id: "tsName",
				label: "Тип ТС"
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
					removeTip: "Удалить вместимость ТС",
					removeConfirm: "Вы действительно хотите удалить вместимость ТС?"
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
				filterString: "Найти вместимость ТС",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.TsCapacitiesGrid',
				load: $scope.gridScope.service.getPage,
				export: TsCapacitiesReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/ts-capacities/TsCapacityEditDlg.html", "TsCapacityEditCtrl", "Создание вместимость ТС", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении вместимость ТС", $scope.gridScope, $scope.gridScope.service.removeItem, true);

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
