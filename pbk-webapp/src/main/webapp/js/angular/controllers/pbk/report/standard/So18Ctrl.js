angular.module('armada')
	.controller('So18Ctrl', ['$scope', 'So18Service', 'GridService', '$state', 'So18ReportService', 'canExportReport18', 'DepartmentsService',
		function ($scope, So18Service, GridService, $state, So18ReportService, canExportReport18, DepartmentsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So18Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport18
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Сопоставительный анализ данных по наряд-заданию и из АСКП",
					addTip: "Добавить запись"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "date",
				label: "Дата",
				class: "col-md-2"
			}, {
				id: "employee",
				label: "ФИО контролера",
				class: "col-md-5"
			}, {
				id: "tsCheckTaskReportCount",
				label: "Проверено подвижного состава (шт.) (Отчет по наряд заданию)",
				class: "col-md-2"
			}, {
				id: "tsCheckAskpCount",
				label: "Проверено подвижного состава (шт.) (АСКП)",
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

			$scope.refreshTotal = function () {
				So18Service.getTotal($scope.gridScope.filters).then(function (response) {
					$scope.gridScope.footerRecord = response;
				});
			}

			$scope.gridScope.getFooterRecord = function (id) {
				if (id == "date") {
					return "Итого";
				}
				if ($scope.gridScope.footerRecord != undefined)
					return $scope.gridScope.footerRecord[id];
			}

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "date",
				gridName: $state.current.name + '.so18Grid',
				load: $scope.gridScope.service.getPage,
				export: So18ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
				afterLoad: $scope.refreshTotal,
                lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;

		}]);
