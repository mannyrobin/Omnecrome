angular.module('armada')
	.controller('So7Ctrl', ['$scope', 'So7Service', 'GridService', '$state', 'So7ReportService', 'canExportReport7', 'DepartmentsService', 'RoutesService', 'RouteTsKindsService',
		function ($scope, So7Service, GridService, $state, So7ReportService, canExportReport7, DepartmentsService, RoutesService, RouteTsKindsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So7Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport7
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Количество перевезенных пассажиров по маршрутам",
					addTip: "Добавить запись"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "branch",
				label: "Эксплуатационный филиал",
				class: "col-md-3"
			}, {
				id: "county",
				label: "Округ",
				class: "col-md-3"
			}, {
				id: "route",
				label: "Маршрут",
				class: "col-md-2"
			}, {
				id: "curPassengerCount",
				label: "Количество пассажиров общее (в т.ч. б/пл.) на текущую дату",
				class: "col-md-2"
			}, {
				id: "prevPassengerCount",
				label: "Количество пассажиров (в т.ч. б/пл.) на аналогичную дату за прошлый год",
				class: "col-md-2"
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
				name: "tsType",
				type: "multiselect",
				placeholder: "Тип ТС",
				load: RouteTsKindsService.getList,
			}, {
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
						value: $scope.gridScope.filters[2].value,
						type: "multiselect"
					}]);
				},
				placeholder: "Маршруты",
				required: false
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
				defaultPredicate: "branch",
				gridName: $state.current.name + '.so7Grid',
				load: $scope.gridScope.service.getPage,
				export: So7ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
                lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;

		}]);
