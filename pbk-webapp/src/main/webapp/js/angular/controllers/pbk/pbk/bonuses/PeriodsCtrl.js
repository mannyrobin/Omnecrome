angular.module('armada')
	.controller('PeriodsCtrl', ['$scope', 'PeriodsService', 'GridService', '$state', '$stateParams',
		function ($scope, PeriodsService, GridService, $state, $stateParams) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = PeriodsService;
			//$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Права */
			$scope.gridScope.perms = {
				add: true,//isNotReadOnlyUser,
				edit: true,
				remove: true//isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Коэфициенты премирования",
					addTip: "Добавить коэфициент премирования"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [
				{
					id: "countFrom",
					type: "text",
					label: "От"
					//hrefClick: function(row) {
					//    $state.go('app.main.pbk.bonuses.detail.period.info', {periodId: row.id});
					//}
				}, {
					id: "countTo",
					type: "text",
					label: "До"
				}, {
					id: "percents",
					type: "text",
					label: "Процент"
				}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "bonusId",
				type: "text",
				defval: $stateParams.itemId,
				hide: true
			}];

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить коэфициент премирования",
					editTip: "Редактировать коэфициент премирования",
					removeConfirm: "Вы действительно хотите удалить коэфициент премирования?"
				},
				gridScope: $scope.gridScope
			};

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти коэфициент премирования",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "percents",
				gridName: $state.current.name + '.periodsgrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/pbk/bonuses/PeriodEditDlg.html", "PeriodEditCtrl", "Создание коэфициента премирования", "lg", $scope.gridScope, {bonusId: $stateParams.itemId});
			$scope.gridScope.editRow = GridService.getEditAction(
				"pbk/pbk/bonuses/PeriodEditDlg.html", "PeriodEditCtrl", "Редактирование коэфициента премирования", "lg", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении коэфициент премирования", $scope.gridScope, $scope.gridScope.service.removeItem);

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
