'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TaskInfoCtrl
 * @description
 *
 */
angular.module('armada')
    .controller('TaskInfoCtrl', ['$controller', '$scope', '$stateParams', 'TasksService', 'EmployeesService', 'ShiftsService', 'VenuesService', 'BsosService',
        'DepartmentsService', 'PlanSchedulesService', 'PlanBrigadesService', 'TaskStatesService', 'isNotReadOnlyUser', 'TaskTypesService', 'RoutesService', 'AppConfig', 'curTaskItem',
        function ($controller, $scope, $stateParams, TasksService, EmployeesService, ShiftsService, VenuesService, BsosService, DepartmentsService,
                  PlanSchedulesService, PlanBrigadesService, TaskStatesService, isNotReadOnlyUser, TaskTypesService, RoutesService, AppConfig, curTaskItem) {
            $scope.isNotReadOnlyUser = isNotReadOnlyUser;

            var taskDate = new Date();
            var isCurrentId = true;
            var routesList = [];
            var isSetRouteValue = false;

            var oldState = curTaskItem.stateId;

            function fillDefVal() {
                if ($scope.fields[14].value == null || $scope.fields[14].value.length == 0) {
                    $scope.fields[14].value = [{id: -1, name: "Все маршруты МГТ"}];
                } else if ($scope.fields[14].value.length == 2 && $scope.fields[14].value[0].id == -1) {
                    $scope.fields[14].value.shift();
                }
            }

            /* Поля ввода */
            $scope.fields = [
                {
                    name: "taskDate",
                    type: "date",
                    label: "Дата выполнения",
                    readonly: true,
                    required: false,
                    onChange: function () {
                        $scope.fields[14].refresh();
                    }

                }, {
                    name: "planScheduleId",
                    type: "select",
                    load: function () {
                        return PlanSchedulesService.getList([
                            {
                                name: "deptId",
                                value: $scope.item.deptId,
                                type: "id"
                            },
                            {
                                name: "workDate",
                                value: $scope.fields[0].value,
                                type: "date"
                            },
                            {
                                name: "isReserve",
                                value: 1,
                                type: "id"
                            },
                            {
                                name: "currentId",
                                value: $scope.fields[1].value,
                                type: "select"
                            }
                        ]);
                    },
                    readonly: true,
                    label: "Сотрудник",
                    required: false,
                    afterLoad: function () {
                        EmployeesService.getEmployeeByTaskId($stateParams.itemId).then(function(resp){
                            $scope.taskCtrlEmplId = resp.headId;
                            var link = "nsi/employees/"+$scope.taskCtrlEmplId+"/info";
                            $("#linkToEmplCard").attr("href",link);
                        });
                    }
                }, {
                    name: "changePlanScheduleId",
                    type: "select",
                    load: function () {
                        return PlanSchedulesService.getList([
                            {
                                name: "deptId",
                                value: $scope.item.deptId,
                                type: "id"
                            },
                            {
                                name: "workDate",
                                value: $scope.fields[0].value,
                                type: "date"
                            },
                            {
                                name: "scheduleId",
                                value: $scope.fields[2].value != null && $scope.fields[2].value.id != null ? $scope.fields[2].value : $scope.fields[1].value,
                                type: "select"
                            },
                            {
                                name: "isReserve",
                                value: 1,
                                type: "id"
                            },
                            {
                                name: "currentId",
                                value: $scope.fields[2].value,
                                type: "select"
                            }
                        ]);
                    },
                    label: "Сотрудник на замену",
                    required: false
                }, {
                    name: "causeShiftId",
                    type: "select",
                    label: "Причина замены",
                    load: function () {
                        return ShiftsService.getList([
                            {
                                name: "currentId",
                                value: $scope.fields[3].value,
                                type: "select"
                            },
                            {
                                name: "isChangeSchedule",
                                value: 1,
                                type: "id"
                            }
                        ]);
                    },
                    required: true
                }, {
                    name: "planVenueId",
                    type: "select",
                    load: function () {
                        return PlanBrigadesService.getList([
                            {
                                name: "deptId",
                                value: $scope.item.deptId,
                                type: "id"
                            },
                            {
                                name: "workDate",
                                value: $scope.fields[0].value,
                                type: "date"
                            },
                            {
                                name: "shiftId",
                                value: curTaskItem.taskTypeId != 2 && curTaskItem.stateId != 6/*IN_RESERVE*/ ? data.shiftId : curTaskItem.shiftId,
                                type: "id"
                            },
                            {
                                name: "currentId",
                                value: $scope.fields[4].value,
                                type: "select"
                            }
                        ]);
                    },
                    label: "Место встречи",
                    required: false
                }, {
                    name: "bsoId",
                    type: "select",
                    load: function () {
                        var employeeScheduleId = $scope.fields[1].value && $scope.fields[1].value.id ? $scope.fields[1].value.id :
                            $scope.fields[2].value && $scope.fields[2].value.id ? $scope.fields[2].value.id : $scope.item.planScheduleId != null ? $scope.item.planScheduleId : null;
                        return BsosService.getBsosForSchedule(data.id ? data.id : null, employeeScheduleId, isCurrentId && $scope.fields[5].value ? $scope.fields[5].value.id : null);
                    },
                    label: "Номер БСО",
                    readonly: !isNotReadOnlyUser,
                    required: false
                }, {
                    name: "deptId",
                    type: "select",
                    load: DepartmentsService.getList,
                    readonly: true,
                    label: "Подразделение",
                    required: false
                }, {
                    name: "stateId",
                    type: "select",
                    load: function () {
                        return TaskStatesService.getList([
                            {
                                name: "currentStateId",
                                value: $scope.item.stateId,
                                type: "id"
                            }
                        ]);
                    },
                    readonly: !isNotReadOnlyUser,
                    onChange: function () {
                        if (oldState != 6 && $scope.fields[7].value.id == 6) {
                            routesList = [];
                            $scope.fields[14].refresh();
                        }
                        oldState = $scope.fields[7].value.id;
                        if ($scope.fields[7].value.id != 4) {
                            $scope.fields[18].value = '';
                        }
                        if ($scope.fields[7].value.id == 1) {
                            $scope.fields[2].value = null;
                            $scope.fields[3].value = null;
                        }
                        setFactHours();
                        readonly();
                    },
                    label: "Статус задания",
                    required: false
                }, {
                    name: "taskTypeId",
                    type: "select",
                    load: TaskTypesService.getList,
                    label: "Тип задания",
                    required: false,
                    onChange: function () {
                        $scope.fields[14].refresh();
                        readonly();
                    }
                }, {
                    name: "shiftId",
                    type: "select",
                    load: function () {
                        return ShiftsService.getList([
                            {
                                name: "currentId",
                                value: data.shiftId,
                                type: "id"
                            }
                        ]);
                    },
                    defval: data.shiftId,
                    label: "Смена",
                    required: false,
                    readonly: true
                }, {
                    name: "workStartTime",
                    type: "time",
                    readonly: true,
                    label: "Начало работы",
                    required: false
                }, {
                    name: "workEndTime",
                    type: "time",
                    readonly: true,
                    label: "Окончание работы",
                    required: false
                }, {
                    name: "breakStartTime",
                    type: "time",
                    readonly: true,
                    label: "Начало перерыва",
                    required: false
                }, {
                    name: "breakEndTime",
                    type: "time",
                    readonly: true,
                    label: "Окончание перерыва",
                    required: false
                }, {
                    name: "routes",
                    type: "multiselect",
                    load: function () {
                        return $scope.fields[9].value.id == 3 ? RoutesService.getNightRoutesForTask([{
                            name: "currentIds",
                            value: $scope.fields[14].value,
                            type: "multiselect"
                        }]) : RoutesService.getSelectItemsForTask([
                            {
                                name: "deptId",
                                value: $scope.item.deptId,
                                type: "id"
                            }, {
                                name: "workDate",
                                value: $scope.fields[0].value,
                                type: "date"
                            }, {
                                name: "brigadeId",
                                value: $scope.fields[8].value.id == 2 ? null : $scope.item.planVenueId,
                                type: "id"
                            }, {
                                name: "currentIds",
                                value: $scope.fields[14].value,
                                type: "multiselect"
                            }
                        ]);
                    },
                    label: "Маршруты",
                    required: false,
                    afterLoad: function () {
                        fillDefVal();
                        if ($scope.oldValues[14] != undefined && $scope.oldValues[14].length == 0) {
                            $scope.oldValues[14] = [{id: -1, name: "Все маршруты МГТ"}];
                        }
                        fillRoutesValue();
                    },
                    onChange: fillDefVal
                }, {
                    name: "factHours",
                    type: "number",
                    max: 24,
                    min: 0,
                    label: "Фактически отработано часов",
                    required: true
                }, {
                    name: "specialMark",
                    type: "textarea",
                    label: "Особые отметки",
                    maxLength: AppConfig.XX_SHORT_INPUT_SIZE,
                    required: false
                }, {
                    name: "specialTask",
                    type: "textarea",
                    label: "Особое задание",
                    maxLength: AppConfig.XX_SHORT_INPUT_SIZE,
                    required: false
                }, {
                    name: "cancelReason",
                    type: "textarea",
                    label: "Причина отмены",
                    maxLength: AppConfig.X_LONG_INPUT_SIZE,
                    required: true
                }, {
                    name: "districtId",
                    type: "select",
                    label: "Район",
                    load: function () {
                        return TasksService.getTaskDistricts($stateParams.itemId, curTaskItem.districtId);
                    }
                }];

            function fillRoutesValue() {
                if (routesList == null || routesList.length == 0 || ($scope.fields[14].value != null && $scope.fields[14].value.length == 1 && $scope.fields[14].value[0].id == -1 && !isSetRouteValue)) {
                    routesList = $scope.fields[14].list;
                }
                if (curTaskItem.districtRoutes && routesList) {
                    angular.forEach(curTaskItem.districtRoutes, function (dr) {
                        if (dr.districtId) {
                            angular.forEach(routesList, function (r) {
                                if (r.id === dr.routeId
                                    && r.countyId === dr.districtId) {
                                    angular.forEach($scope.fields[14].value, function (vr) {
                                        if (vr.id === r.id) {
                                            vr.name = r.name;
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            }

            $scope.fillRoutesList = function () {
                if (routesList == null || routesList.length == 0 || ($scope.fields[14].value != null && $scope.fields[14].value.length == 1 && $scope.fields[14].value[0].id == -1 && !isSetRouteValue)) {
                    routesList = $scope.fields[14].list;
                }
                var sels = $scope.fields[14].value;
                var list = [];
                if (sels == undefined || sels.length == 0) {
                    list = routesList;
                } else {
                    var countyId;
                    angular.forEach(routesList, function (r) {
                        if (r.id == sels[0].id) {
                            countyId = sels[0].countyId;
                            return false;
                        }
                    });
                    if (countyId == null) {
                        list = routesList;
                    } else {
                        angular.forEach(routesList, function (r) {
                            if (r.countyId == countyId) {
                                list.push(r);
                            }
                        });
                    }
                }
                $scope.fields[14].list = list;
                isSetRouteValue = $scope.fields[14].value != null && ($scope.fields[14].value.length > 1 || $scope.fields[14].value.length == 1 && $scope.fields[14].value[0].id != -1);
            };
            var watchRouteList = $scope.$watch(function () {
                return $scope.fields[14].list != undefined ? $scope.fields[14].list : undefined;
            }, function (newValue, oldValue) {
                if (routesList == null || routesList.length == 0) {
                    routesList = $scope.fields[14].list;
                    $scope.fillRoutesList();
                } else {
                    watchRouteList();
                }
            });
            $scope.$watch(function () {
                return $scope.fields[14].value != undefined ? $scope.fields[14].value : undefined;
            }, function (newValue, oldValue) {
                $scope.fillRoutesList();
            });

            function setFactHours() {
                if ($scope.fields[7].value.id === 5) {
                    var workStartTime = moment.duration($scope.fields[10].value).asHours();
                    var workEndTime = moment.duration($scope.fields[11].value).asHours();
                    var breakStartTime = moment.duration($scope.fields[12].value).asHours();
                    var breakEndTime = moment.duration($scope.fields[13].value).asHours();

                    $scope.fields[15].value = workEndTime - workStartTime - (breakEndTime - breakStartTime);
                } else {
                    $scope.fields[15].value = $scope.oldValues[15];
                }
            }

            function readonly() {
                $scope.fields[4].readonly = $scope.fields[8].value.id != 2 && $scope.fields[7].value.id != 6/*IN_RESERVE*/;
                $scope.fields[10].readonly = $scope.fields[8].value.id != 2;
                $scope.fields[11].readonly = $scope.fields[8].value.id != 2;
                $scope.fields[12].readonly = $scope.fields[8].value.id != 2;
                $scope.fields[13].readonly = $scope.fields[8].value.id != 2;
            }

            $scope.fields[4].readonly = curTaskItem.taskTypeId != 2 && curTaskItem.stateId != 6/*IN_RESERVE*/;
            $scope.fields[10].readonly = curTaskItem.taskTypeId != 2;
            $scope.fields[11].readonly = curTaskItem.taskTypeId != 2;
            $scope.fields[12].readonly = curTaskItem.taskTypeId != 2;
            $scope.fields[13].readonly = curTaskItem.taskTypeId != 2;

            var service = Object.create(TasksService);
            service.editItem = function (item) {
                return TasksService.editItem(item);
            };
            service.getItem = function (id) {
                return TasksService.getTaskWithShift(id);
            }

            /* Инициализация */
            $controller('BaseEditCtrl', {$scope: $scope});
            $scope.init({id: $stateParams.taskId != null ? $stateParams.taskId : $stateParams.itemId}, $scope.fields, service);
            $scope.refreshAfterLoad = true;
        }]);
