angular.module('armada')
    .controller('So19Ctrl', ['$scope', 'So19Service', 'GridService', '$state', 'So19ReportService', 'canExportReport19', 'DepartmentsService', 'ShiftsService',
        function ($scope, So19Service, GridService, $state, So19ReportService, canExportReport19, DepartmentsService, ShiftsService) {

            $scope.gridScope = $scope;
            $scope.gridScope.service = So19Service;

            /* Права */
            $scope.gridScope.perms = {
                export: canExportReport19
            };

            /* Заголовок */
            $scope.gridScope.titleParams = {
                labels: {
                    title: "Количество совместных бригад с ГКУ ОП",
                    addTip: ""
                },
                gridScope: $scope.gridScope
            };

            /* Колонки грида */
            $scope.gridScope.headers = [{
                id: "workDate",
                label: "Дата",
                class: "col-md-2"
            }, {
                id: "shiftName",
                label: "Смена",
                class: "col-md-1"
            }, {
                id: "gkuPodrName",
                label: "Отдел ГКУ",
                class: "col-md-1"
            }, {
                id: "mgtEmployees",
                label: "Контролеры МГТ",
                class: "col-md-2"
            }, {
                id: "gkuEmployees",
                label: "Контролеры ГКУ",
                class: "col-md-2"
            }, {
                id: "venue",
                label: "Место встречи",
                class: "col-md-2"
            }, {
                id: "county",
                label: "Округ",
                class: "col-md-1"
            }, {
                id: "routes",
                label: "Маршруты",
                class: "col-md-2"
            }, {
                id: "brKind0",
                label: "без ГКУ",
                class: "col-md-1"
            }, {
                id: "brKind11",
                label: "1 МГТ / 1 ГКУ",
                class: "col-md-1"
            }, {
                id: "brKind12",
                label: "1 МГТ / 2 ГКУ",
                class: "col-md-1"
            }, {
                id: "brKind13",
                label: "1 МГТ / 3 ГКУ",
                class: "col-md-1"
            }, {
                id: "brKind21",
                label: "2 МГТ / 1 ГКУ",
                class: "col-md-1"
            }, {
                id: "brKind22",
                label: "2 МГТ / 2 ГКУ",
                class: "col-md-1"
            }, {
                id: "brKind23",
                label: "2 МГТ / 3 ГКУ",
                class: "col-md-1"
            }, {
                id: "dopMGT",
                label: "Доп-но МГТ",
                class: "col-md-1"
            }, {
                id: "dopGKU",
                label: "Доп-но ГКУ",
                class: "col-md-1"
            }, {
                id: "dopMGTnoGKU",
                label: "Доп-но МГТ без ГКУ",
                class: "col-md-1"
            }, {
                id: "mgtCountInDay",
                label: "Кол-во МГТ",
                class: "col-md-1"
            }, {
                id: "brCommon",
                label: "Совместных бригад",
                class: "col-md-1"
            }, {
                id: "mgtCountInCommonBr",
                label: "МГТ в совместных",
                class: "col-md-1"
            }, {
                id: "gkuCountInCommonBr",
                label: "ГКУ в совместных",
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
                name: "shiftId",
                type: "multiselect",
                placeholder: "Смена",
                load: function () {
                    return ShiftsService.getList([
                        {
                            name: "isWork",
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

            /* Грид */
            $scope.gridScope.grid = GridService.initGridData({
                tableScope: $scope.gridScope,
                defaultPredicate: "workDate",
                gridName: $state.current.name + '.So19Grid',
                load: $scope.gridScope.service.getPage,
                export: So19ReportService.exportReport,
                exportFormats: ["xlsx", "zip", "pdf"],
                lazyLoad: true
            });

            $scope.gridScope.hideIndexColumn = true;

        }]);
