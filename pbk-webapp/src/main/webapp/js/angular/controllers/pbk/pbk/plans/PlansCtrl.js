angular.module('armada')
	.controller('PlansCtrl', ['$scope', 'DepartmentsService', '$stateParams', '$modal', 'Notification', 'GridService', '$state', 'isNotReadOnlyUser', 'isBrigadeEditRole',
		function ($scope, DepartmentsService, $stateParams, $modal, Notification, GridService, $state, isNotReadOnlyUser, isBrigadeEditRole) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = DepartmentsService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Планирование ПБК",
					addTip: "Согласовать бригады по всем подразделениям",
					addTipIcon: "thumbs-up"
				},
				gridScope: $scope.gridScope
			};

			/* Права */
			$scope.gridScope.perms = {
				add: isBrigadeEditRole,
				export: isNotReadOnlyUser
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-3",
				href: function (row) {
					return "pbk/plans/" + row.id + "/schedules";
				}
			}, {
				id: "parentName",
				label: "Родительское подразделение",
				class: "col-md-3"
			}, {
				id: "description",
				label: "Описание",
				class: "col-md-3"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "name",
				type: "text",
				placeholder: "Название"
			}, {
				name: "forPlanUse",
				type: "text",
				defval: $stateParams.forPlanUse,
				hide: true
			}];

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти подразделения",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.plansGrid',
				load: $scope.gridScope.service.getPage,
				export: GridService.getAddAction(
					"pbk/pbk/plans/brigades/BrigadesExportDlg.html", "BrigadesExportCtrl", "Экспорт графиков бригад", "md", $scope.gridScope, {format: 'xlsx'}),
				exportFormats: ['xlsx']
			});

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

			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/pbk/plans/brigades/BrigadeApproveDlg.html", "BrigadeApproveCtrl", "Согласование бригад", "md", $scope.gridScope);
		}]);