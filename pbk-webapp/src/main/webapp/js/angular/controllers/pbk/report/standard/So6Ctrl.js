angular.module('armada')
	.controller('So6Ctrl', ['$scope', 'So6Service', 'GridService', '$state', 'So6ReportService', 'canExportReport6', 'DepartmentsService',
		function ($scope, So6Service, GridService, $state, So6ReportService, canExportReport6, DepartmentsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So6Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport6
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Результаты контроля (мотивация)",
					addTip: "Добавить запись"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "toSdik",
				label: "ТО СДиК",
				class: "col-md-1"
			}, {
				id: "surname",
				label: "Фамилия",
				class: "col-md-1"
			}, {
				id: "name",
				label: "Имя",
				class: "col-md-1"
			}, {
				id: "patronumic",
				label: "Отчество",
				class: "col-md-1"
			}, {
				id: "personalNumber",
				label: "Табельный номер",
				class: "col-md-1"
			}, {
				id: "forPlanUse",
				label: "Участвует в планировании",
				class: "col-md-1"
			}, {
				id: "scheduleNumber",
				label: "№ графика",
				class: "col-md-1"
			}, {
				id: "planShiftCount",
				label: "Количество смен план",
				class: "col-md-1"
			}, {
				id: "factShiftCount",
				label: "Количество смен факт",
				class: "col-md-1"
			}, {
				id: "totalPlanCount",
				label: "Количество изъятых карт план",
				class: "col-md-1"
			}, {
				id: "totalFactCount",
				label: "Количество изъятых карт факт",
				class: "col-md-1"
			}, {
				id: "factSkmCount",
				label: "Количество СКМ",
				class: "col-md-1"
			}, {
				id: "factSkmoCount",
				label: "Количество СКМО",
				class: "col-md-1"
			}, {
				id: "factVesbCount",
				label: "Количество ВЕСБ",
				class: "col-md-1"
			}, {
				id: "factValidlessCount",
				label: "Количество Нелегитимных",
				class: "col-md-1"
			}, {
				id: "factLpkCount",
				label: "Количество ЛПК",
				class: "col-md-1"
			}, {
				id: "factSoldCount",
				label: "Продано билетов",
				class: "col-md-1"
			}, {
				id: "factStowawayCount",
				label: "Высажено",
				class: "col-md-1"
			}, {
				id: "factDeliveryCount",
				label: "Доставлено в ОВД",
				class: "col-md-1"
			}, {
				id: "fact1000Count",
				label: "Постановления 1000р",
				class: "col-md-1"
			}, {
				id: "fact2500Count",
				label: "Постановления 2500р",
				class: "col-md-1"
			}, {
				id: "excessSkmCount",
				label: "Количество изъятых карт сверх плана",
				class: "col-md-1"
			}, {
				id: "underSkm",
				label: "Недовыполнение",
				class: "col-md-1"
			}, {
				id: "underSkmValue",
				label: "Недовыполнение (+ значение)",
				class: "col-md-1"
			}, {
				id: "planSubtr",
				label: "Вычет из плана",
				class: "col-md-1"
			}, {
				id: "pcnt",
				label: "%",
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
				name: "forPlanUse",
				type: "yesno",
				defval: {id: -1, name: "Все"},
				placeholder: "Участвует в планировании"
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
				So6Service.getTotal($scope.gridScope.filters).then(function (response) {
					$scope.gridScope.footerRecord = response;
				});
			}

			$scope.gridScope.getFooterRecord = function (id) {
				if (id == "toSdik") {
					return "Итого";
				}
				if ($scope.gridScope.footerRecord != undefined)
					return $scope.gridScope.footerRecord[id];
			}

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "toSdik",
				gridName: $state.current.name + '.so6Grid',
				load: $scope.gridScope.service.getPage,
				export: So6ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
				afterLoad: $scope.refreshTotal,
                lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;
			$scope.gridScope.grid.columnCount = 15;
		}]);
