angular.module('armada')
	.controller('So17Ctrl', ['$scope', 'So17Service', 'GridService', '$state', 'So17ReportService', 'canExportReport17', 'DepartmentsService', 'RouteTsKindsService', 'RoutesService', '$timeout',
		function ($scope, So17Service, GridService, $state, So17ReportService, canExportReport17, DepartmentsService, RouteTsKindsService, RoutesService, $timeout) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So17Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport17
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Сводный сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП)",
					addTip: "Добавить запись"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "date",
				label: "Дата",
				class: "col-md-1"
			}, {
				id: "routeNumber",
				label: "Номер маршрута",
				class: "col-md-1"
			}, {
				id: "askpPassengerCount",
				label: "Количество перевезенных пассажиров (АСКП)",
				class: "col-md-2"
			}, {
				id: "asmppPassengerCount",
				label: "Количество перевезенных пассажиров (АСМ-ПП)",
				class: "col-md-2"
			}, {
				id: "diffs",
				label: "Расхождения",
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
					return DepartmentsService.getList([{
						name: "forPlanUse",
						value: 1,
						type: "id"
					}]);
				}
			}, {
				name: "tsType",
				type: "multiselect",
				placeholder: "Тип ТС",
				load: RouteTsKindsService.getList
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
				defaultPredicate: "routeCode",
				gridName: $state.current.name + '.so17Grid',
				load: $scope.gridScope.service.getPage,
				export: So17ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
                lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;

		}]);
