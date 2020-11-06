angular.module('armada')
    .controller('So14Ctrl', ['$scope', 'So14Service', 'GridService', '$state', 'So14ReportService', 'DepartmentsService', 'canExportReport14',
        function ($scope, So14Service, GridService, $state, So14ReportService, DepartmentsService, canExportReport14) {

            $scope.gridScope = $scope;
            $scope.gridScope.service = So14Service;

            /* Права */
            $scope.gridScope.perms = {
                export: canExportReport14
            };

            /* Заголовок */
            $scope.gridScope.titleParams = {
                labels: {
                    title: "Результаты ПБК за период",
                    addTip: "Добавить запись"
                },
                gridScope: $scope.gridScope
            };

            /* Колонки грида */
            $scope.gridScope.headers = [{
                id: "toSdik",
                label: "ТО СДиК",
                class: "col-md-1"
            }, {
                id: "curFactEmployeeTotal",
                label: "Фактическое кол-во контролеров (Отчетный период)",
                class: "col-md-1"
            }, {
                id: "curExemptSkmSkmoVesbCount",
                label: "Изъято СКМ, СКМО, ВЕСБ (Отчетный период)",
                class: "col-md-1"
            }, {
                id: "curExemptLpkCount",
                label: "Изъято ЛПК (Отчетный период)",
                class: "col-md-1"
            }, {
                id: "curExemptValidlessCount",
                label: "Изъято нелегитимных билетов (Отчетный период)",
                class: "col-md-1"
            }, {
                id: "curPlantStowawayCount",
                label: "Высажено безбилетных пассажиров (Отчетный период)",
                class: "col-md-1"
            }, {
                id: "curDeliveryOvdCount",
                label: "Доставлено в ОВД (Отчетный период)",
                class: "col-md-1"
            }, {
                id: "curTicketSoldCount",
                label: "Кол-во реализованных билетов (Отчетный период)",
                class: "col-md-1"
            }, {
                id: "curEmployeeTaskCount",
                label: "Количество наряд-заданий контролерам (Отчетный период)",
                class: "col-md-1"
            }, {
                id: "prevFactEmployeeTotal",
                label: "Фактическое кол-во контролеров (Аналогичный предыдущий период)",
                class: "col-md-1"
            }, {
                id: "prevExemptSkmSkmoVesbCount",
                label: "Изъято СКМ, СКМО, ВЕСБ (Аналогичный предыдущий период)",
                class: "col-md-1"
            }, {
                id: "prevExemptLpkCount",
                label: "Изъято ЛПК (Аналогичный предыдущий период)",
                class: "col-md-1"
            }, {
                id: "prevExemptValidlessCount",
                label: "Изъято нелегитимных билетов (Аналогичный предыдущий период)",
                class: "col-md-1"
            }, {
                id: "prevPlantStowawayCount",
                label: "Высажено безбилетных пассажиров (Аналогичный предыдущий период)",
                class: "col-md-1"
            }, {
                id: "prevDeliveryOvdCount",
                label: "Доставлено в ОВД (Аналогичный предыдущий период)",
                class: "col-md-1"
            }, {
                id: "prevTicketSoldCount",
                label: "Кол-во реализованных билетов (Аналогичный предыдущий период)",
                class: "col-md-1"
            }, {
                id: "prevEmployeeTaskCount",
                label: "Количество наряд-заданий контролерам (Аналогичный предыдущий период)",
                class: "col-md-1"
            }];

            /* Фильтры */
            $scope.gridScope.filters = [{
                name: {
                    fromName: "curDateFrom",
                    toName: "curDateTo"
                },
                defval: {
                    fromDate: new Date(),
                    toDate: new Date()
                },
                type: "range",
                placeholder: {
                    from: "Начало отчётного периода",
                    to: "Окончание отчётного периода"
                }
            }, {
                name: {
                    fromName: "prevDateFrom",
                    toName: "prevDateTo"
                },
                defval: {
                    fromDate: new Date(),
                    toDate: new Date()
                },
                type: "range",
                placeholder: {
                    from: "Начало аналогичного предыдущего периода",
                    to: "Окончание аналогичного предыдущего периода"
                }
            }, {
                name: "isWorkDays",
                type: "yesno",
                placeholder: "Рабочие дни",
                defval: {id: -1, name: "Все"}
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

            /* Параметры фильтрации */
            $scope.gridScope.filterParams = {
                filterString: "Найти записи",
                gridScope: $scope.gridScope
            };

            $scope.refreshTotal = function () {
                So14Service.getTotal($scope.gridScope.filters).then(function (response) {
                    $scope.gridScope.footerRecord = response;
                });
            }

            $scope.gridScope.getFooterRecord = function (id) {
                if (id == "toSdik") {
                    return "Итого";
                }
                if ($scope.gridScope.footerRecord != undefined)
                    return $scope.gridScope.footerRecord[id];
            }

            /* Грид */
            $scope.gridScope.grid = GridService.initGridData({
                tableScope: $scope.gridScope,
                defaultPredicate: "toSdik",
                gridName: $state.current.name + '.so14Grid',
                load: $scope.gridScope.service.getPage,
                export: So14ReportService.exportReport,
                exportFormats: ["xlsx", "zip", "pdf"],
                afterLoad: $scope.refreshTotal,
                lazyLoad: true
            });

            $scope.gridScope.hideIndexColumn = true;
            $scope.gridScope.grid.columnCount = 17;
        }]);
