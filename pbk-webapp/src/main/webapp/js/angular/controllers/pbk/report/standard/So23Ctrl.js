angular.module('armada')
	.controller('So23Ctrl', ['$scope', 'So23Service', 'GridService', '$state', 'AppConfig', 'So23ReportService', 'DepartmentsService', 'EmployeesService', 'canExportReport23',
		function ($scope, So23Service, GridService, $state, AppConfig, So23ReportService, DepartmentsService, EmployeesService, canExportReport23) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So23Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport23
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Сводные данные по работе контролеров ГУП \"Мосгортранс\" за период"
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
				name: "employeeId",
				type: "select",
				placeholder: "Сотрудник (табельный номер)",
				load: function () {
					return EmployeesService.getList([{
						name: "forPlanUse",
						value: 1,
						type: "id"
					}, {
						name: "includeFired",
						value: 1,
						type: "id"
					}, {
						name: "departmentId",
						value: $scope.gridScope.filters[1].value,
						type: "select"
					}]);
				}
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти сводные данные по работе контролёров ГУП \"Мосгортранс\" за период",
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
							$scope.days.push($scope.startDate.add(1, 'day').format('YYYY-MM-DD'));
						}
						So23Service.getTotal($scope.gridScope.filters).then(function (response) {
							$scope.gridScope.footerRecord = response;
						});
					}
				}
			};

			$scope.gridScope.getFooterRecord = function (id) {
				if ($scope.gridScope.footerRecord != undefined)
					return $scope.gridScope.footerRecord[id];
			}

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "",
				gridName: $state.current.name + '.So23Grid',
				load: $scope.gridScope.service.getPage,
				export: So23ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
				afterLoad: initAfterLoad,
                lazyLoad: true
			});
   
			$scope.getDayExemptSkmCount = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].exemptSkm;
					}
				}
				return 0;
			};

			$scope.getDayExemptSkmoCount = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].exemptSkmo;
					}
				}
				return 0;
			};
			
			$scope.getDayExemptVesbCount = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].exemptVesb;
					}
				}
				return 0;
			};
			
			$scope.getDayLpkCount = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].lpk;
					}
				}
				return 0;
			};
			
			$scope.getDayTsCheckCount = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].tsCheck;
					}
				}
				return 0;
			};
			
			$scope.getDayExemptValidLessCount = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].exemptValidLess;
					}
				}
				return 0;
			};
			
			$scope.getDayPlantStowawayCount = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].plantStowaway;
					}
				}
				return 0;
			};
			
			$scope.getDayDeliveryOvdCount = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].deliveryOvd;
					}
				}
				return 0;
			};
			
			
			$scope.getDayTicketSoldCount = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].ticketSold;
					}
				}
				return 0;
			};
			
			$scope.getDayOrdinance1000Count = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].ordinance1000;
					}
				}
				return 0;
			};
			
			$scope.getDayOrdinance2500Count = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].ordinance2500;
					}
				}
				return 0;
			};

			
			$scope.getShiftId = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].shiftId;
					}
				}
				return 0;
			};
			
			
			$scope.getSumByShifts = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].sumByShifts;
					}
				}
				return 0;
			};

			$scope.getDate = function (date) {
				return new Date(date);
			};

			$scope.getRounded = function (value, precision) {
				return Math.round(value * Math.pow(10, precision)) / Math.pow(10, precision);
			};

			$scope.gridScope.hideIndexColumn = true;

		}]);
