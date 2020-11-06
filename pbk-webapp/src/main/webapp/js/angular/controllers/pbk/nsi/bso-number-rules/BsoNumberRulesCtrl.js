angular.module('armada')
	.controller('BsoNumberRulesCtrl', ['$scope', 'BsoNumberRulesService', 'BsoTypesService', 'DepartmentsService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, BsoNumberRulesService, BsoTypesService, DepartmentsService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = BsoNumberRulesService;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Журнал правил формирования номеров БСО",
					addTip: "Добавить правило формирования номера БСО"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "departmentName",
				label: "Подразделение",
				class: "col-md-2",
				href: function (row) {
					return "nsi/bso-number-rules/" + row.id + "/info";
				}
			}, {
				id: "bsoTypeName",
				label: "Тип БСО",
				class: "col-md-2"
			}, {
				id: "increment",
				label: "Текущее значение",
				class: "col-md-1"
			}, {
				id: "branchCode",
				label: "Код филиала",
				class: "col-md-1"
			}, {
				id: "deptCode",
				label: "Код подразделения",
				class: "col-md-1"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "bsoTypeId",
				type: "select",
				placeholder: "Тип БСО",
				load: BsoTypesService.getList,
				class: "col-md-3"
			}, {
				name: "departmentId",
				type: "select",
				placeholder: "Подразделение",
				load: DepartmentsService.getDepartsForBso,
				class: "col-md-3"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти правила формирования номеров БСО",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить правило формирования номера БСО",
					removeConfirm: "Вы действительно хотите удалить правило формирования номера БСО?"
				},
				check: {
					isRemoveEnable: function (row) {
						return !row.delete;
					}
				},
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "departmentName",
				gridName: $state.current.name + '.bsoNumberRulesGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/bso-number-rules/BsoNumberRulesEditDlg.html", "BsoNumberRuleEditCtrl", "Создание правила формирования номера БСО", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении правила формирования номера БСО", $scope.gridScope, $scope.gridScope.service.removeItem)
		}]);