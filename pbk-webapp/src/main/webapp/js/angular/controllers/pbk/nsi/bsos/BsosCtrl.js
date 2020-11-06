angular.module('armada')
    .controller('BsosCtrl', ['$scope', '$modal', 'BsosService', 'GridService', '$state', 'BsoTypesService', 'DepartmentsService', 'isNotReadOnlyUser', 'BsosReportService', 'EmployeesService',
        function ($scope, $modal, BsosService, GridService, $state, BsoTypesService, DepartmentsService, isNotReadOnlyUser, BsosReportService, EmployeesService) {

            $scope.gridScope = $scope;
            $scope.gridScope.service = BsosService;

            /* Права */
            $scope.gridScope.perms = {
                generate: isNotReadOnlyUser,
                bind: isNotReadOnlyUser,
                unbind: isNotReadOnlyUser,
                export: isNotReadOnlyUser,
                trash: isNotReadOnlyUser
                //print: isNotReadOnlyUser
            };

            $scope.openGenerateWindow = function (gridScope) {
                return function () {
                    var modalInstance = $modal.open({
                        templateUrl: "templates/dialogs/pbk/nsi/bsos/BsosGenerateDlg.html",
                        windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
                        controller: "BsoGenerateCtrl",
                        size: "md",
                        resolve: {
                            data: function () {
                                return {
                                    title: "Генерация БСО"
                                }
                            }
                        }
                    });
                    modalInstance.result.then(function () {
                        gridScope.grid.refreshAction();
                        gridScope.setSelectionAll(false);
                    });
                };
            };

            $scope.openBindWindow = function (gridScope) {
                return function () {
                    var modalInstance = $modal.open({
                        templateUrl: "templates/dialogs/pbk/nsi/bsos/BsosBindDlg.html",
                        windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
                        controller: "BsoBindCtrl",
                        size: "md",
                        resolve: {
                            data: function () {
                                var ids = gridScope.getSelectedItemId();
                                var firstBso = gridScope.getRowById(ids[0]);
                                return {
                                    title: "Привязка БСО",
                                    ids: ids,
                                    departmentId: firstBso.departmentId,
                                    departmentName: firstBso.departmentName
                                };
                            }
                        }
                    });
                    modalInstance.result.then(function () {
                        gridScope.grid.refreshAction();
                        gridScope.setSelectionAll(false);
                    });
                };
            };

            $scope.openTrashWindow = function (gridScope) {
                return function () {
                    var modalInstance = $modal.open({
                        templateUrl: "templates/dialogs/pbk/nsi/bsos/BsosTrashDlg.html",
                        windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
                        controller: "BsoTrashCtrl",
                        size: "md",
                        resolve: {
                            data: function () {
                                var ids = gridScope.getSelectedItemId();
                                var firstBso = gridScope.getRowById(ids[0]);
                                return {
                                    title: "В брак",
                                    firstBso: firstBso
                                };
                            }
                        }
                    });
                    modalInstance.result.then(function () {
                        gridScope.grid.refreshAction();
                        gridScope.setSelectionAll(false);
                    });
                };
            };

            $scope.bsosSameDeptValidator = function (gridScope) {
                var ids = gridScope.getSelectedItemId();
                if (ids.length == 0) return false;
                var firstBso = gridScope.getRowById(ids[0]);
                for (var i = 1; i < ids.length; i++) {
                    var nextBso = gridScope.getRowById(ids[i]);
                    if (nextBso.departmentId != firstBso.departmentId) return false;
                }
                return true;
            };

            /* Заголовок */
            $scope.gridScope.titleParams = {
                labels: {
                    title: "Журнал БСО",
                    addTip: "Добавить БСО"
                },
                buttons: [{
                    name: "generate",
                    tip: "Сгенерировать",
                    action: $scope.openGenerateWindow($scope.gridScope),
                    icon: "plus"
                }, {
                    name: "bind",
                    tip: "Привязать",
                    action: $scope.openBindWindow($scope.gridScope),
                    icon: "resize-small",
                    activeWhenItemsSelected: true,
                    customActiveValidator: $scope.bsosSameDeptValidator
                }, {
                    name: "unbind",
                    tip: "Отвязать",
                    action: BsosService.unbindBsos($scope.gridScope),
                    icon: "resize-full",
                    activeWhenItemsSelected: true
                }, {
                    name: "trash",
                    tip: "В брак",
                    action: $scope.openTrashWindow($scope.gridScope),
                    icon: "trash",
                    activeWhenItemsSelected: true
                    // }, {
                    //    name: "print",
                    //    tip: "Распечатать",
                    //    action: BsosService.printBsos($scope.gridScope),
                    //    icon: "print",
                    //    activeWhenItemsSelected: true
                }],
                gridScope: $scope.gridScope
            };

            /* Колонки грида */
            $scope.gridScope.headers = [{
                id: "departmentName",
                label: "Подразделение",
                class: "col-md-2"
            }, {
                id: "bsoTypeName",
                label: "Тип БСО",
                class: "col-md-2"
            }, {
                id: "bsoNumber",
                label: "Номер БСО",
                class: "col-md-2"
            }, {
                id: "employeeName",
                label: "Сотрудник",
                class: "col-md-2"
            }, {
                id: "isPrinted",
                icon: "print",
                iconTip: "Распечатан",
                label: "",
                class: "col-md-1"
            }, {
                id: "isBound",
                icon: "link",
                iconTip: "Закреплен",
                label: "",
                class: "col-md-1"
            }, {
                id: "isUsed",
                icon: "asterisk",
                iconTip: "Использован",
                label: "",
                class: "col-md-1"
            }, {
                id: "isTrashed",
                icon: "trash",
                iconTip: "Брак",
                label: "",
                class: "col-md-1"
            }];

            /* Фильтры */
            $scope.gridScope.filters = [{
                name: "bsoTypeId",
                type: "select",
                placeholder: "Тип БСО",
                load: BsoTypesService.getList
            }, {
                name: "bsoNumber",
                type: "text",
                placeholder: "Номер БСО"
            }, {
                name: "bsoStateId",
                type: "select",
                placeholder: "Состояние БСО",
                load: BsosService.getStates
            }, {
                name: "departmentId",
                type: "select",
                placeholder: "Подразделение",
                load: DepartmentsService.getDepartsForBso,
                onChange: function () {
                    $scope.filters[4].refresh();
                }
            }, {
                name: "employeeId",
                type: "select",
                placeholder: "Сотрудник, к которому привязан БСО",
                load: function () {
                    return EmployeesService.getList([{
                        name: "departmentId",
                        value: $scope.filters[3].value == null
                        || $scope.filters[3].value.id == -1 ? null : $scope.filters[3].value.id,
                        type: "id"
                    }, {
                        name: "forPlanUse",
                        value: 1,
                        type: "id"
                    }, {
                        name: "isNotFire",
                        value: 1,
                        type: "id"
                    }]);
                }
            }];

            /* Параметры фильтрации */
            $scope.gridScope.filterParams = {
                filterString: "Найти БСО",
                gridScope: $scope.gridScope
            };

            /* Кнопки Edit, Remove */
            $scope.gridScope.buttons = {
                extraButtons: [{
                    tip: "Информация о браке",
                    action: function (row) {
                        $modal.open({
                            templateUrl: "templates/dialogs/pbk/nsi/bsos/BsosTrashInfoDlg.html",
                            windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
                            controller: "BsoTrashInfoCtrl",
                            size: "md",
                            resolve: {
                                data: function () {
                                    var firstBsoId = row.id;
                                    return {
                                        title: "В брак",
                                        firstBsoId: firstBsoId
                                    };
                                }
                            }
                        });
                    },
                    class: "glyphicon glyphicon-remove text-danger",
                    show: function (row) {
                        return row.isTrashed !== "✘";
                    }
                }],
                labels: {
                    removeTip: "Удалить БСО",
                    removeConfirm: "Вы действительно хотите удалить БСО?"
                },
                check: {
                    isRemoveEnable: function (row) {
                        return !row.delete;
                    }
                },
                gridScope: $scope.gridScope
            };

            $scope.gridScope.titleWidth = 'col-lg-7';
            $scope.gridScope.titleButtonsWidth = 'col-lg-5';

            /* Грид */
            $scope.gridScope.grid = GridService.initGridData({
                tableScope: $scope.gridScope,
                defaultPredicate: "departmentName",
                gridName: $state.current.name + '.BsosGrid',
                export: BsosReportService.exportReport,
                exportFormats: ["pdf", "xlsx"],
                load: $scope.gridScope.service.getPage
            });

            $scope.gridScope.addSelItemColumn = true;

        }]);
