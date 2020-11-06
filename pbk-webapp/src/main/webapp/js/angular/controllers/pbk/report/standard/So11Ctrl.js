angular.module('armada')
	.controller('So11Ctrl', ['$scope', 'So11Service', 'GridService', '$state', 'So11ReportService', 'canExportReport11', 'DepartmentsService',
		function ($scope, So11Service, GridService, $state, So11ReportService, canExportReport11, DepartmentsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So11Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport11
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Работа контролеров",
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
				id: "planEmployeeCount",
				label: "Плановое кол-во контролеров",
				class: "col-md-1"
			}, {
				id: "factEmployeeCount",
				label: "Фактическое кол-во контролеров",
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
				id: "exemptLpkCount",
				label: "Изъято ЛПК",
				class: "col-md-1"
			}, {
				id: "ticketSoldCount",
				label: "Реализовано проездных билетов",
				class: "col-md-1"
			}, {
				id: "tsCheckCount",
				label: "Проверено подвижного состава",
				class: "col-md-1"
			}, {
				id: "exemptValidLessCount",
				label: "Изъято нелегитимных билетов",
				class: "col-md-1"
			}, {
				id: "plantStowawayCount",
				label: "Высажено безбилетных пассажиров",
				class: "col-md-1"
			}, {
				id: "deliveryOvdCount",
				label: "Доставлено в ОВД",
				class: "col-md-1"
			}, {
				id: "totalSkmSkmoVesb",
				label: "Всего изъято СКМ, СКМО, ВЕСБ",
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
				So11Service.getTotal($scope.gridScope.filters).then(function (response) {
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
				gridName: $state.current.name + '.so11Grid',
				load: $scope.gridScope.service.getPage,
				export: So11ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
				afterLoad: $scope.refreshTotal,
                lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;
			$scope.gridScope.grid.columnCount = 13;
		}]);
