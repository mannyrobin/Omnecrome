angular.module('armada')
	.controller('So20Ctrl', ['$scope', 'So20Service', 'GridService', 'RoutesService', 'EmployeesService', '$state', 'So20ReportService', 'canExportReport20', 'DepartmentsService',
		function ($scope, So20Service, GridService, RoutesService, EmployeesService, $state, So20ReportService, canExportReport20) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So20Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport20
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Статистика проверок маршрута",
					addTip: ""
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "number",
				label: "№",
				class: "col-md-1"
			}, {
				id: "checkTime",
				label: "Дата и время проверки",
				class: "col-md-2"
			}, {
				id: "smena",
				label: "Смена",
				class: "col-md-1"
			}, {
				id: "fio",
				label: "Контролер",
				class: "col-md-2"
			}, {
				id: "tosdik",
				label: "ТО СДиК",
				class: "col-md-1"
			}, {
				id: "scm",
				label: "Изъято СКМ",
				class: "col-md-1"
			}, {
				id: "scmo",
				label: "Изъято СКМО",
				class: "col-md-1"
			}, {
				id: "vesb",
				label: "Изъято ВЕСБ",
				class: "col-md-1"
			}, {
				id: "other",
				label: "Изъято ЛПК",
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
			},
				{
					name: "routes",
					type: "multiselect",
					lazymin: 1,
					lazyload: function (query) {
						return RoutesService.getList([{
							name: "name",
							value: query,
							type: "text"
						}, {
							name: "tsType",
							value: $scope.gridScope.filters[1].value,
							type: "multiselect"
						}]);
					},
					placeholder: "Маршруты",
					required: false
				},
				{
					name: "employeeId",
					type: "multiselect",
					placeholder: "Сотрудник",
					lazyload: EmployeesService.getList
				}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти записи",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "criterion",
				gridName: $state.current.name + '.So20Grid',
				load: $scope.gridScope.service.getPage,
				export: So20ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
                lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;

			$scope.getDateColor = function (row) {
				return row.workTypeCode == 'HOLIDAY' ? 'background-color:pink'
					: (row.workTypeCode == 'DAY_OFF' && moment(row.date, 'DD.MM.YYYY').isoWeekday() == 6) ? 'background-color:lightgreen' :
						(row.workTypeCode == 'DAY_OFF' && moment(row.date, 'DD.MM.YYYY').isoWeekday() == 7) ? 'background-color:darkgreen' : '';
			}
		}]);
