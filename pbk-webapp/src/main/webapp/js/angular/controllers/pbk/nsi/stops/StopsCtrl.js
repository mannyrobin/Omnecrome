angular.module('armada')
	.controller('StopsCtrl', ['$scope', 'StopsService', 'StopsReportService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, StopsService, StopsReportService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = StopsService;
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
					title: "Остановочные пункты",
					addTip: "Добавить остановочный пункт",
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				href: function (row) {
					return "nsi/stops/" + row.id + "/info";
				}
			}, {
				id: "gmStopName",
				label: "Остановочный пункт в ВИС ГИС МГТ"
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
					removeTip: "Удалить остановочный пункт",
					removeConfirm: "Вы действительно хотите удалить остановочный пункт?"
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
				filterString: "Найти остановочный пункт",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.StopsGrid',
				load: $scope.gridScope.service.getPage,
				export: StopsReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/stops/stopEditDlg.html", "StopEditCtrl", "Создание остановочный пункта", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении остановочного пункта", $scope.gridScope, $scope.gridScope.service.removeItem, true);

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
