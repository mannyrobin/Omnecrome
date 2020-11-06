angular.module('armada')
	.controller('BonusesCtrl', ['$scope', 'BonusesService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, BonusesService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = BonusesService;
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			var date = new Date();
			var defStartFilterDate = new Date(date.getFullYear(), date.getMonth(), 1);
			var defEndFilterDate = new Date(date.getFullYear(), date.getMonth() + 1, 0);

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Премирование",
					addTip: "Добавить премирование"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "periodStartDate",
				label: "Период",
				get: function (row) {
					return row.periodStartDate + ' - ' + row.periodEndDate
				},
				href: function (row) {
					return "pbk/bonuses/" + row.id + "/info";
				}
			}, {
				id: "name",
				type: "text",
				label: "Название"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: {
					fromName: "dateFrom",
					toName: "dateTo"
				},
				defval: {
					fromDate: defStartFilterDate,
					toDate: defEndFilterDate
				},
				type: "range",
				placeholder: {
					from: "Начало периода",
					to: "Окончание периода"
				}
			}, {
				name: "name",
				type: "text",
				placeholder: "Название премирования"
			}];

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить премирование",
					removeConfirm: "Вы действительно хотите удалить премирование?"
				},
				gridScope: $scope.gridScope
			};

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти премирование",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.bonusesgrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/pbk/bonuses/BonusEditDlg.html", "BonusEditCtrl", "Создание премирования", "lg", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении премирования", $scope.gridScope, $scope.gridScope.service.removeItem);

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
