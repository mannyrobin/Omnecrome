angular.module('armada')
	.controller('So16Ctrl', ['$scope', '$modal', 'So16Service', 'GridService', '$state', 'So16ReportService', 'canExportReport16', 'DepartmentsService', 'RoutesService', 'RouteTsKindsService', '$timeout',
		function ($scope, $modal, So16Service, GridService, $state, So16ReportService, canExportReport16, DepartmentsService, RoutesService, RouteTsKindsService, $timeout) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So16Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport16,
				asmpp: true
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП) поостановочно",
					addTip: "Добавить запись"
				},
				buttons: [{
					tip: "Загрузить пассажиропоток из АСМПП",
					name: "asmpp",
					action: function (row) {
						var modalInstance = $modal.open({
							templateUrl: "templates/views/pbk/report/standard/so-16asmppDlg.html",
							windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
							controller: "So16AsmppCtrl",
							size: "md",
							resolve: {
								data: function () {
									return {
										title: "Загрузка пассажиропотока из АСМПП",
										rows: $scope.gridScope.grid.rows
									}
								}
							}
						});
						modalInstance.result.then(function () {
							$scope.gridScope.grid.refreshAction();
						});
					},
					icon: "download"
				}],
				gridScope: $scope.gridScope
			};

			var t = 0;

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
				id: "routeOutgoNumber",
				label: "Номер выхода на маршруте",
				class: "col-md-1"
			}, {
				id: "stopName",
				label: "Наименование остановки",
				class: "col-md-2"
			}, {
				id: "askpArrivalTime",
				label: "Время прибытия на остановку",
				class: "col-md-2"
			}, {
				id: "askpPassengerCount",
				label: "Количество вошедших пассажиров (АСКП)",
				class: "col-md-2"
			}, {
				id: "asmppPassengerCount",
				label: "Количество вошедших пассажиров (АСМ-ПП)",
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
				name: "moveCode",
				type: "text",
				placeholder: "Номер выхода",
				maxLength: 6
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
						value: $scope.gridScope.filters[3].value,
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
				gridName: $state.current.name + '.so16Grid',
				load: $scope.gridScope.service.getPage,
				export: So16ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
                lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;

		}]);
