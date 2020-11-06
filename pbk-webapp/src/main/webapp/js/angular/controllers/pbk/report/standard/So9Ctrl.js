angular.module('armada')
	.controller('So9Ctrl', ['$scope', 'So9Service', 'GridService', '$state', 'So9ReportService', 'DepartmentsService', 'EmployeesService', 'canExportReport9',
		function ($scope, So9Service, GridService, $state, So9ReportService, DepartmentsService, EmployeesService, canExportReport9) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So9Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport9
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Сводные данные по работе контролеров по подразделениям",
					addTip: "Добавить запись"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "employee",
				label: "ФИО контролера",
				class: "col-md-2"
			}, {
				id: "personalNumber",
				label: "Табельный номер",
				class: "col-md-1"
			}, {
				id: "exemptSkmCount",
				label: "Изъято СКМ",
				class: "col-md-1"
			}, {
				id: "exemptSkmoCount",
				label: "Изъято СКМО",
				class: "col-md-1"
			}, {
				id: "exemptVesbCount",
				label: "Изъято ВЕСБ",
				class: "col-md-1"
			}, {
				id: "lpkCount",
				label: "ЛПК",
				class: "col-md-1"
			}, {
				id: "tsCheckCount",
				label: "Проверено подвижного состава",
				class: "col-md-1"
			}, {
				id: "exemptValidLessCount",
				label: "Изъято нелегитимной билетной продукции",
				class: "col-md-1"
			}, {
				id: "plantStowawayCount",
				label: "Высажено",
				class: "col-md-1"
			}, {
				id: "deliveryOvdCount",
				label: "Доставлено в ОВД",
				class: "col-md-1"
			}, {
				id: "ordinance1000Count",
				label: "Постановления 1000р",
				class: "col-md-1"
			}, {
				id: "ordinance2500Count",
				label: "Постановления 2500р",
				class: "col-md-1"
			}, {
				id: "protocol1000Count",
				label: "Протокол 1000р",
				class: "col-md-1"
			}, {
				id: "protocol2500Count",
				label: "Протокол 2500р",
				class: "col-md-1"
			}, {
				id: "ticketSoldCount",
				label: "Количество реализованных билетов",
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
			},
				{
					name: "employeeId",
					type: "multiselect",
					placeholder: "Сотрудник",
					lazyload: EmployeesService.getList
				}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти сводные данные по работе контролеров по подразделениям",
				gridScope: $scope.gridScope
			};

			$scope.refreshTotal = function () {
				So9Service.getTotal($scope.gridScope.filters).then(function (response) {
					$scope.gridScope.footerRecord = response;
				});
			}

			$scope.gridScope.getFooterRecord = function (id) {
				if (id == "employee") {
					return "Итого";
				}
				if ($scope.gridScope.footerRecord != undefined)
					return $scope.gridScope.footerRecord[id];
			}

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "employee",
				gridName: $state.current.name + '.so9Grid',
				load: $scope.gridScope.service.getPage,
				export: So9ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
				afterLoad: $scope.refreshTotal,
                lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;

		}]);
