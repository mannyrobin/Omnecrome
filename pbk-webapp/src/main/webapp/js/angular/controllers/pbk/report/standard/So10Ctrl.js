angular.module('armada')
	.controller('So10Ctrl', ['$scope', 'So10Service', 'GridService', '$state', 'So10ReportService', 'canExportReport10', 'DepartmentsService',
		function ($scope, So10Service, GridService, $state, So10ReportService, canExportReport10, DepartmentsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So10Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport10
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Сводная форма по эффективности работы контролеров",
					addTip: "Добавить запись"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "criterion",
				label: "Показатели",
				class: "col-md-10"
			}, {
				id: "curPeriod",
				label: "Текущий период",
				class: "col-md-1"
			}, {
				id: "prevPeriod",
				label: "Прошлый период",
				class: "col-md-1"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: {
					fromName: "dateFrom",
					toName: "dateTo"
				},
				defval: {
					fromDate: new Date(),
					toDate: new Date()
				},
				type: "range",
				placeholder: {
					from: "Начало периода",
					to: "Окончание периода"
				}
			}, {
				name: "departmentId",
				type: "multiselect",
				placeholder: "Подразделение",
				load: function () {
					return DepartmentsService.getList([
						{
							name: "forPlanUse",
							value: 1,
							type: "id"
						}
					]);
				}
			}, {
				name: "isWorkDate",
				type: "yesno",
				placeholder: "Рабочий день"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти записи",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить запись",
					removeConfirm: "Вы действительно хотите удалить запись?"
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
				defaultPredicate: "criterion",
				gridName: $state.current.name + '.so10Grid',
				load: $scope.gridScope.service.getPage,
				export: So10ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
                lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;

		}]);
