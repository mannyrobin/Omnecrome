angular.module('armada')
	.controller('So12Ctrl', ['$scope', 'So12Service', 'GridService', '$state', 'So12ReportService', 'AppConfig', 'DepartmentsService', 'CountiesService',
		'DistrictsService', 'canExportReport12',
		function ($scope, So12Service, GridService, $state, So12ReportService, AppConfig, DepartmentsService, CountiesService, DistrictsService, canExportReport12) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So12Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport12
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Совместный график по местам встреч"
				},
				gridScope: $scope.gridScope
			};

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
				filterString: "Найти график по местам встреч",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			var initAfterLoad = function () {
				$scope.days = [];
				if ($scope.gridScope.grid.rows && $scope.gridScope.grid.rows.length > 0) {
					if ($scope.gridScope.grid.rows && $scope.gridScope.grid.rows.length > 0) {
						if (!$scope.gridScope.filters[0].value || !$scope.gridScope.filters[0].value.fromDate ||
							!$scope.gridScope.filters[0].value || !$scope.gridScope.filters[0].value.toDate ||
							(new Date($scope.gridScope.filters[0].value.fromDate)).getTime() > (new Date($scope.gridScope.filters[0].value.toDate)).getTime()) {
							return;
						} else {
							$scope.startDate = moment($scope.gridScope.filters[0].value.fromDate).add(-1, 'day');
							$scope.endDate = moment($scope.gridScope.filters[0].value.toDate);
						}
						for (; $scope.startDate.isBefore($scope.endDate);) {
							$scope.days.push($scope.startDate.add(1, 'day').format('DD.MM.YYYY'));
						}
					}
					So12Service.getTotal($scope.gridScope.filters).then(function (response) {
						$scope.gridScope.footerRecord = response;
					});
				}
			};

			$scope.gridScope.getFooterRecord = function (id) {
				if ($scope.gridScope.footerRecord != undefined)
					return $scope.gridScope.footerRecord[id];
			}

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "venue",
				gridName: $state.current.name + '.So12Grid',
				load: $scope.gridScope.service.getPage,
				export: So12ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
				afterLoad: initAfterLoad,
                lazyLoad: true
			});

			$scope.getCount = function (employeeCounts, date) {
				for (var i = 0; i < employeeCounts.length; i++) {
					if (employeeCounts[i].date === date) {
						return employeeCounts[i].employeeCount;
					}
				}
				return 0;
			};

			$scope.getDayOfMonth = function (date) {
				return moment(date, "DD.MM.YYYY").date();
			};

			$scope.gridScope.hideIndexColumn = true;

		}]);
