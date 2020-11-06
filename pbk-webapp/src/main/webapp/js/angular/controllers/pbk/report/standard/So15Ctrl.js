angular.module('armada')
	.controller('So15Ctrl', ['$scope', 'So15Service', 'GridService', '$state', 'So15ReportService', 'canExportReport15', 'DepartmentsService',
		function ($scope, So15Service, GridService, $state, So15ReportService, canExportReport15, DepartmentsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So15Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport15
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Итоги ПБК по контролеру по данным АСУ ПБК",
					addTip: "Добавить запись"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "checkStartDateTime",
				label: "Дата и время начала проверки",
				class: "col-md-2"
			}, {
				id: "routeNumber",
				label: "Номер маршрута",
				class: "col-md-2"
			}, {
				id: "tsOutgoNumber",
				label: "Номер выхода ТС",
				class: "col-md-1"
			}, {
				id: "employee",
				label: "ФИО контролера",
				class: "col-md-4"
			}, {
				id: "department",
				label: "№ МОК",
				class: "col-md-2"
			}, {
				id: "checkedPassengerCount",
				label: "Количество проверенных пассажиров",
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

			$scope.refreshTotal = function () {
				So15Service.getTotal($scope.gridScope.filters).then(function (response) {
					$scope.gridScope.footerRecord = response;
				});
			}

			$scope.gridScope.getFooterRecord = function (id) {
				if (id == "checkStartDateTime") {
					return "Итого";
				}
				if ($scope.gridScope.footerRecord != undefined)
					return $scope.gridScope.footerRecord[id];
			}

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
				defaultPredicate: "checkStartDateTime",
				gridName: $state.current.name + '.so15Grid',
				load: $scope.gridScope.service.getPage,
				export: So15ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
				afterLoad: $scope.refreshTotal,
                lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;

		}]);
