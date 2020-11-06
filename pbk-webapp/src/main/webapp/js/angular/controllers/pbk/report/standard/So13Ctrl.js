angular.module('armada')
	.controller('So13Ctrl', ['$scope', 'So13Service', 'GridService', '$state', 'So13ReportService', 'canExportReport13', 'RoutesService',
		function ($scope, So13Service, GridService, $state, So13ReportService, canExportReport13, RoutesService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So13Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport13
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Маршруты и выходы"
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
				name: "routeId",
				type: "select",
				placeholder: "Маршрут",
				load: RoutesService.getList
			}, {
				name: "moveCode",
				type: "text",
				placeholder: "Код выхода"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти записи",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			var initAfterLoad = function () {
				$scope.hours = [];
				for (var i = 0; i < 24; i++) {
					$scope.hours.push((i + 3) % 24);
				}

			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "ticketTypeCode",
				gridName: $state.current.name + '.So13Grid',
				load: $scope.gridScope.service.getPage,
				export: So13ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
				afterLoad: initAfterLoad,
                lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;

			$scope.getPassCount = function (count) {
				if (count) {
					return count;
				}
				return 0;
			}

		}]);
