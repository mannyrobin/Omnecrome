angular.module('armada')
	.controller('So3Ctrl', ['$scope', 'So3Service', 'GridService', '$state', 'So3ReportService', 'DepartmentsService', 'CountiesService', 'DistrictsService',
		'canExportReport3',
		function ($scope, So3Service, GridService, $state, So3ReportService, DepartmentsService, CountiesService, DistrictsService, canExportReport3) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So3Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport3
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Количество бригад по местам встречи",
					addTip: "Добавить запись"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "date",
				label: "Дата",
				class: "col-md-2"
				//}, {
				//    id: "county",
				//    label: "Округ",
				//    class: "col-md-2"
				//}, {
				//    id: "district",
				//    label: "Район",
				//    class: "col-md-2"
			}, {
				id: "venue",
				label: "Место встречи",
				class: "col-md-5"
			}, {
				id: "brigadeType",
				label: "Вид бригады",
				class: "col-md-3",
				disableSort: true
			}, {
				id: "brigadeCount",
				label: "Количество контролеров",
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
			}, {
				name: "countyId",
				type: "select",
				placeholder: "Округ",
				load: CountiesService.getList,
				onChange: function () {
					$scope.filters[3].refresh();
				}
			}, {
				name: "districtId",
				type: "multiselect",
				placeholder: "Район",
				load: function () {
					return DistrictsService.getList([{
						name: "countyId",
						value: $scope.filters[2].value == null ? null : $scope.filters[2].value.id,
						type: "id"
					}
					]);
				}
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти количества бригад по местам встречи",
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
				So3Service.getTotal($scope.gridScope.filters).then(function (response) {
					$scope.gridScope.footerRecord = response;
				});
			};

			$scope.gridScope.getFooterRecord = function (id) {
				if (id == "date") {
					return "Итого";
				}
				if ($scope.gridScope.footerRecord != undefined)
					return $scope.gridScope.footerRecord[id];
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "date",
				gridName: $state.current.name + '.so3Grid',
				load: $scope.gridScope.service.getPage,
				export: So3ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
				afterLoad: $scope.refreshTotal,
                lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;

		}]);
