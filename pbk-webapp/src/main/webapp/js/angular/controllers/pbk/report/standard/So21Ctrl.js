angular.module('armada')
    .controller('So21Ctrl', ['$scope', 'So21Service', 'GridService', '$state', 'So21ReportService', 'canExportReport21', 'DepartmentsService', 'EmployeesService', 'RoutesService', 'RouteTsKindsService',
        function ($scope, So21Service, GridService, $state, So21ReportService, canExportReport21, DepartmentsService, EmployeesService, RoutesService, RouteTsKindsService) {

            //localStorage.setItem("armada.gridParams_app.main.report.standard.so-21.so21Grid", null);

            $scope.gridScope = $scope;
            $scope.gridScope.service = So21Service;

            /* Права */
            $scope.gridScope.perms = {
                export: canExportReport21
            };

            /* Заголовок */
            $scope.gridScope.titleParams = {
                labels: {
                    title: "Проходы по БСК контролера",
                    addTip: "Добавить запись"
                },
                gridScope: $scope.gridScope
            };

            /* Колонки грида */
            $scope.gridScope.headers = [{
                id: "branch",
                label: "ТО СДиК",
                class: "col-md-1"
            }, {
                id: "employee",
                label: "Сотрудник (таб. №)",
                class: "col-md-3"
            }, {
                id: "bsk",
                label: "Номер карты",
                class: "col-md-1"
            }, {
                id: "dateTimes",
                label: "Дата и время прохода",
                class: "col-md-2"
            }, {
                id: "operator",
                label: "Парк/Депо",
                class: "col-md-2"
            }, {
                id: "route",
                label: "Маршрут",
                class: "col-md-1"
            }, {
                id: "run",
                label: "Выход",
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
            },
                {
                    name: {
                        fromName: "timeFrom",
                        toName: "timeTo"
                    },
                    defval: {
                        fromDate: moment().startOf('day'),
                        toDate: moment().startOf('day')
                    },
                    type: "time-range",
                    placeholder: {
                        from: "Время с",
                        to: "Время по"
                    }
                },
                {
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
                    placeholder: "Сотрудник",
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
                }, {
                    name: "routeId",
                    type: "multiselect",
                    placeholder: "Маршрут",
                    load: function () {

                        return RoutesService.getList([{
                            name: "deptInId",
                            value: 1,
                            type: "multiselect"
                        }]);

                    }
                }, {
                    name: "bskId",
                    type: "text",
                    placeholder: "Номер карты"
                }];

            $scope.refreshTotal = function () {
                So21Service.getTotal($scope.gridScope.filters).then(function (response) {
                    $scope.gridScope.footerRecord = response;
                });
            }

            $scope.gridScope.getFooterRecord = function (id) {
                if (id == "branch") {
                    return "Итого";
                }
                if (id == "route") {
                    return "";
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
                defaultPredicate: "branch",
                gridName: $state.current.name + '.so21Grid',
                load: $scope.gridScope.service.getPage,
                export: So21ReportService.exportReport,
                exportFormats: ["xlsx", "zip", "pdf"],
                afterLoad: $scope.refreshTotal,
                lazyLoad: true
            });

            $scope.gridScope.hideIndexColumn = true;

        }]);
