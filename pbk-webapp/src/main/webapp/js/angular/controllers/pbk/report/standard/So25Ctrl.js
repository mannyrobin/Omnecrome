angular.module('armada')
    .controller('So25Ctrl', ['$scope', 'So25Service', 'GridService', '$state','$stateParams', 'DateTimeService', 'So25ReportService', 'AsmppStopsService', 'canExportReport24',
        function ($scope, So25Service, GridService, $state, $stateParams, DateTimeService, So25ReportService, AsmppStopsService, canExportReport24) {

            $scope.gridScope = $scope;
            $scope.gridScope.service = So25Service;

            /* Права */
            $scope.gridScope.perms = {
                export: canExportReport24
            };

            $scope.$watch('gridScope.filters[0].value', function (newValue, oldValue) {
                if (oldValue && newValue && oldValue.toDate && oldValue.fromDate && newValue.toDate && newValue.fromDate && (newValue.fromDate.valueOf() !== oldValue.fromDate.valueOf()
                    || newValue.toDate.valueOf() !== oldValue.toDate.valueOf())) {
                    $scope.gridScope.filters[1].refresh();
                }
            });

            /* Заголовок */
            $scope.gridScope.titleParams = {
                labels: {
                    title: "Cписок маршрутов АСМ-ПП"
                },
                gridScope: $scope.gridScope
            };

            /* Колонки грида */
            $scope.gridScope.headers = [{
                id: "workDate",
                label: "Дата",
                class: "col-md-1"
            }, {
                id: "routeName",
                label: "Номер маршрута",
                class: "col-md-3"
            }, {
                id: "typeTs",
                label: "Тип ТС",
                class: "col-md-1"
            }, {
                id: "stops",
                label: "Записи",
                class: "col-md-1"
            }];

            var now = new Date();

            /* Фильтры */
            $scope.gridScope.filters = [{
                name: {
                    fromName: "dateFrom",
                    toName: "dateTo"
                },
                defval: {
                    fromDate: DateTimeService.addDays(now, -7),
                    toDate: now
                },
                type: "range",
                placeholder: {
                    from: "Начало периода",
                    to: "Окончание периода"
                }
            }, {
                name: "routeId",
                type: "select",
                load: function (query) {
                    return AsmppStopsService.getRouteSelectList([{
                        name: "dateFrom",
                        value: $scope.gridScope.filters[0].value.fromDate,
                        type: "date"
                    }, {
                        name: "dateTo",
                        value: $scope.gridScope.filters[0].value.toDate,
                        type: "date"
                    }]);
                },
                lazymin: 1,
                placeholder: "Маршрут",
                required: false,
                value: $stateParams.routeName == null ? null : {id: $stateParams.routeId, name: $stateParams.routeName}
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

            /* Грид */
            $scope.gridScope.grid = GridService.initGridData({
                tableScope: $scope.gridScope,
                defaultPredicate: "",
                gridName: $state.current.name + '.so25Grid',
                load: $scope.gridScope.service.getPage,
                export: So25ReportService.exportReport,
                exportFormats: ["xlsx", "zip", "pdf"],
                lazyLoad: true
            });

            $scope.gridScope.hideIndexColumn = true;

        }]);
