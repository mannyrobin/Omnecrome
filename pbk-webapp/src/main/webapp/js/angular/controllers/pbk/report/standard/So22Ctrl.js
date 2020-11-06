angular.module('armada')
    .controller('So22Ctrl', ['$scope', 'So22Service', 'GridService', '$state', 'So22ReportService', 'canExportReport22', 'DepartmentsService', 'ShiftsService',
        function ($scope, So22Service, GridService, $state, So22ReportService, canExportReport22, DepartmentsService, ShiftsService) {

            $scope.gridScope = $scope;
            $scope.gridScope.service = So22Service;

            /* Права */
            $scope.gridScope.perms = {
                export: canExportReport22
            };

            /* Заголовок */
            $scope.gridScope.titleParams = {
                labels: {
                    title: "Сверка с ГКУ ОП",
                    addTip: "Добавить запись"
                },
                gridScope: $scope.gridScope
            };

            /* Колонки грида */
            $scope.gridScope.headers = [{
                id: "workDateId",
                label: "Дата",
                class: "col-md-1"
            }, {
                id: "deptName",
                label: "ТО СДИК",
                class: "col-md-2"
            }, {
                id: "gkuopName",
                label: "Отдел ГКУ ОП",
                class: "col-md-2"
            }, {
                id: "shiftName",
                label: "Смена",
                class: "col-md-1"
            }, {
                id: "mgtHead",
                label: "ФИО МГТ",
                class: "col-md-3"
            }, {
                id: "gkuopHead",
                label: "ФИО ГКУ ОП",
                class: "col-md-3"
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
                placeholder: "Подразделение МГТ",
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
                name: "gkuopId",
                type: "text",
                placeholder: "Отдел ГКУ ОП"
            }, {
                name: "shiftId",
                type: "multiselect",
                load: function () {
                    return ShiftsService.getList([
                        {
                            name: "isWork",
                            value: 1,
                            type: "id"
                        }
                    ]);
                },
                placeholder: "Смена"
            }, {
                name: "mgt",
                type: "text",
                placeholder: "ФИО МГТ"
            }, {
                name: "hasGkuop",
                type: "yesno",
                placeholder: "Совместная бригада",
                defval: {id: -1, name: "Все"}
            }, {
                name: "gkuop",
                type: "text",
                placeholder: "ФИО ГКУ ОП"
            }];

            $scope.refreshTotal = function () {
                So22Service.getTotal($scope.gridScope.filters).then(function (response) {
                    $scope.gridScope.footerRecord = response;
                });
            }

            $scope.gridScope.getFooterRecord = function (id) {
                if (id == "workDateId") {
                    return "Количество смен";
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
                defaultPredicate: "workDateId",
                gridName: $state.current.name + '.so22Grid',
                load: $scope.gridScope.service.getPage,
                export: So22ReportService.exportReport,
                exportFormats: ["xlsx", "zip", "pdf"],
                afterLoad: $scope.refreshTotal,
                lazyLoad: true
            });

            $scope.gridScope.hideIndexColumn = true;

        }]);
