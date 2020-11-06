angular.module('armada')
	.controller('So4Ctrl', ['$scope', 'So4Service', 'GridService', '$state', 'So4ReportService', 'DepartmentsService', 'canExportReport4',
		function ($scope, So4Service, GridService, $state, So4ReportService, DepartmentsService, canExportReport4) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So4Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport4
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Ежедневная посменная численность контролёров ГУП \"Мосгортранс\" по территориальному подразделению",
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
				id: "firstShiftCount",
				label: "1 смена",
				class: "col-md-2"
			}, {
				id: "secondShiftCount",
				label: "2 смена",
				class: "col-md-2"
			}, {
				id: "dayCount",
				label: "День (12 ч.)",
				class: "col-md-2"
			}, {
				id: "nightCount",
				label: "Ночь (12 ч.)",
				class: "col-md-2"
			}, {
				id: "totalCount",
				label: "Всего за сутки",
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
				filterString: "Найти численности контролеров",
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
				So4Service.getTotal($scope.gridScope.filters).then(function (response) {
					$scope.gridScope.footerRecord = response;
				});
			};

			$scope.gridScope.getFooterRecord = function (id, item) {
				if (item != undefined) {
					if (id == "date") {
						return item.totalTitle;
					}
					return item[id];
				}
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "date",
				gridName: $state.current.name + '.so4Grid',
				load: $scope.gridScope.service.getPage,
				export: So4ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
				afterLoad: $scope.refreshTotal,
                lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;

		}]);
