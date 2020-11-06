angular.module('armada')
    .controller('So24Ctrl', ['$scope', 'So24Service', 'GridService', '$state', 'DateTimeService', 'So24ReportService', 'canExportReport24', 'DepartmentsService', 'EmployeesService',
        function ($scope, So24Service, GridService, $state, DateTimeService, So24ReportService, canExportReport24, DepartmentsService, EmployeesService) {

            $scope.gridScope = $scope;
            $scope.gridScope.service = So24Service;

            /* Права */
            $scope.gridScope.perms = {
                export: canExportReport24
            };

            /* Заголовок */
            $scope.gridScope.titleParams = {
                labels: {
                    title: "Сводные данные по наряд заданиям"
                },
                gridScope: $scope.gridScope
            };

            /* Колонки грида */
            $scope.gridScope.headers = [{
                id: "taskDate",
                label: "Дата выполнения",
                class: "col-md-1",
                href: function (row) {
                    return "pbk/tasks/" + row.id + "/info";
                }
            }, {
                id: "employeeName",
                label: "Сотрудник",
                class: "col-md-3"
            }, {
                id: "personnelNumber",
                label: "Табельный номер",
                class: "col-md-1"
            }, {
                id: "shiftName",
                label: "Смена",
                class: "col-md-1"
            }, {
                id: "departmentName",
                label: "Подразделение",
                class: "col-md-1"
            }, {
                id: "bsoNumber",
                label: "Номер БСО",
                class: "col-md-1"
            }, {
                id: "taskState",
                label: "Статус",
                class: "col-md-1"
            }, {
                id: "isBsk",
                icon: "flag",
                label: "БСК",
                iconTip: "БСК",
                class: "col-md-1"
            }, {
                id: "isVideo",
                icon: "flag",
                label: "Видео",
                iconTip: "Видео",
                class: "col-md-1"
            }, {
                id: "isClosed",
                icon: "flag",
                label: "Закрыто",
                iconTip: "Закрыто",
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
                    fromDate: DateTimeService.addDays(now, -15),
                    toDate: DateTimeService.addDays(now, -8)
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
                type: "multiselect",
                placeholder: "Сотрудник",
                lazyload: EmployeesService.getList
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
                gridName: $state.current.name + '.so24Grid',
                load: $scope.gridScope.service.getPage,
                export: So24ReportService.exportReport,
                exportFormats: ["xlsx", "zip", "pdf"],
                lazyLoad: true
            });

            $scope.gridScope.hideIndexColumn = true;

        }]);
