angular.module('armada')
    .controller('So8Ctrl', ['$scope', 'So8Service', 'GridService', '$state', 'So8ReportService', 'DepartmentsService', 'canExportReport8',
        function ($scope, So8Service, GridService, $state, So8ReportService, DepartmentsService, canExportReport8) {

            $scope.gridScope = $scope;
            $scope.gridScope.service = So8Service;

            /* Права */
            $scope.gridScope.perms = {
                export: canExportReport8
            };

            /* Заголовок */
            $scope.gridScope.titleParams = {
                labels: {
                    title: "Работа контролеров на маршруте",
                    addTip: "Добавить запись"
                },
                gridScope: $scope.gridScope
            };

            /* Колонки грида */
            $scope.gridScope.headers = [{
                id: "date",
                label: "Дата",
                class: "col-md-1"
            }, {
                id: "tsCheckCount",
                label: "Проверено подвижного состава",
                class: "col-md-1"
            }, {
                id: "exemptSkmCount",
                label: "Изъято СКМ",
                class: "col-md-1"
            }, {
                id: "exemptSkmoCount",
                label: "Изъято СКМО",
                class: "col-md-1"
            }, {
                id: "exemptVesbCount",
                label: "Изъято ВЕСБ",
                class: "col-md-1"
            }, {
                id: "exemptOtherLpkCount",
                label: "Изъято ЛПК",
                class: "col-md-1"
            }, {
                id: "exemptValidlessCount",
                label: "Изъято нелегитимной билетной продукции",
                class: "col-md-1"
            }, {
                id: "ticketSoldCount",
                label: "Реализовано посадочных талонов",
                class: "col-md-1"
            }, {
                id: "protocol2500Count",
                label: "Составлено протоколов на сумму 2500 р.",
                class: "col-md-1"
            }, {
                id: "protocol1000Count",
                label: "Составлено протоколов на сумму 1000 р.",
                class: "col-md-1"
            }, {
                id: "ordinance2500Count",
                label: "Оформлено постановлений квитанций на сумму 2500 р.",
                class: "col-md-1"
            }, {
                id: "ordinance1000Count",
                label: "Оформлено постановлений квитанций на сумму 1000 р.",
                class: "col-md-1"
            }, {
                id: "mgtEmployeeCount",
                label: "Кол-во контролеров ГУП \"Мосгортранс\"",
                class: "col-md-1"
            }, {
                id: "gkuopEmployeeCount",
                label: "Кол-во контролеров ГКУ \"Организатор перевозок\"",
                class: "col-md-1"
            }, {
                id: "deliveryOvdCount",
                label: "Доставлено в ОВД",
                class: "col-md-1"
            }, {
                id: "getOffCount",
                label: "Высажено",
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
                So8Service.getTotal($scope.gridScope.filters).then(function (response) {
                    $scope.gridScope.footerRecord = response;
                });
            }

            $scope.gridScope.getFooterRecord = function (id) {
                if (id == "date") {
                    return "Итого";
                }
                if ($scope.gridScope.footerRecord != undefined)
                    return $scope.gridScope.footerRecord[id];
            }

            /* Параметры фильтрации */
            $scope.gridScope.filterParams = {
                filterString: "Найти результаты работы контролеров на маршруте",
                gridScope: $scope.gridScope
            };

            /* Грид */
            $scope.gridScope.grid = GridService.initGridData({
                tableScope: $scope.gridScope,
                defaultPredicate: "date",
                gridName: $state.current.name + '.so8Grid',
                load: $scope.gridScope.service.getPage,
                export: So8ReportService.exportReport,
                exportFormats: ["xlsx", "zip", "pdf"],
                afterLoad: $scope.refreshTotal,
                lazyLoad: true
            });

            $scope.gridScope.hideIndexColumn = true;
            $scope.gridScope.grid.columnCount = 14;

        }]);
