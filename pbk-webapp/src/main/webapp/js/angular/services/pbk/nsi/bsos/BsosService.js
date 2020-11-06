angular.module('armada')
    .service('BsosService', ['UrlService', 'BaseItemService', 'Restangular', 'Notification', 'GridService',
        function (UrlService, BaseItemService, Restangular, Notification, GridService) {
            var serviceUrl = UrlService.getPrefix() + 'nsi/bsos';
            var service = BaseItemService.init({
                serviceurl: serviceUrl,
                api: ['page', 'get']
            });
            service.getStates = function () {
                return Restangular.all(serviceUrl + '/states').getList();
            };
            service.generateBulk = function (departmentId, bsoTypeId, count) {
                return Restangular.all(serviceUrl + '/bulk').post('', {
                    departmentId: departmentId, bsoTypeId: bsoTypeId, count: parseInt(count)
                });
            };
            service.bindBsos = function (ids, employeeId) {
                return Restangular.one(serviceUrl + '/bind').put({ids: ids, employeeId: employeeId}).then(function () {
                    Notification.info("Выбранные БСО успешно привязаны");
                }).catch(function (response) {
                    if (response.status === 400) {
                        var message = response.data.notValid.errors[0];
                        Notification.error(message);
                    }
                });
            };
            service.unbindBsos = function (gridScope) {
                return function () {
                    var body = {
                        ids: gridScope.getSelectedItemId()
                    };
                    Restangular.one(serviceUrl + '/unbind').put(body).then(function () {
                        gridScope.grid.refreshAction();
                        gridScope.setSelectionAll(false);
                        Notification.info("Выбранные БСО успешно отвязаны");
                    }).catch(function (response) {
                        if (response.status === 400) {
                            var message = response.data.notValid.errors[0];
                            Notification.error(message);
                        }
                    });
                };
            };
            service.trashBso = function (trashBso) {
                return Restangular.one(serviceUrl + '/trash').post('', trashBso);
            };

            service.fixBsos = function (gridScope) {
                return function () {
                    var body = {
                        ids: gridScope.getSelectedItemId()
                    };
                    Restangular.one(serviceUrl + '/fix').put(body).then(function () {
                        gridScope.grid.refreshAction();
                        gridScope.setSelectionAll(false);
                        Notification.info("Выбранные БСО успешно исправлены");
                    });
                };
            };
            service.printBsos = function (gridScope) {
                return function () {
                    var body = {
                        ids: gridScope.getSelectedItemId()
                    };
                    Restangular.one(serviceUrl + '/print').put(body).then(function () {
                        gridScope.grid.refreshAction();
                        gridScope.setSelectionAll(false);
                    });
                };
            };
            service.useBsos = function (gridScope, taskId) {
                return function () {
                    var body = {
                        bsoIds: gridScope.getSelectedItemId(),
                        taskId: taskId
                    };
                    Restangular.one(serviceUrl + '/use').put(body).then(function () {
                        gridScope.grid.refreshAction();
                        gridScope.setSelectionAll(false);
                        Notification.info("Выбранные БСО были успешно использованы");
                    }).catch(function (response) {
                        if (response.status === 400) {
                            var message = response.data.notValid.errors[0];
                            Notification.error(message);
                        }
                    });
                };
            };
            service.disuseBsos = function (gridScope, taskId) {
                return function () {
                    var body = {
                        bsoIds: gridScope.getSelectedItemId(),
                        taskId: taskId
                    };
                    Restangular.one(serviceUrl + '/disuse').put(body).then(function () {
                        gridScope.grid.refreshAction();
                        gridScope.setSelectionAll(false);
                        Notification.info("Выбранные БСО были успешно возвращены");
                    }).catch(function (response) {
                        if (response.status === 400) {
                            var message = response.data.notValid.errors[0];
                            Notification.error(message);
                        }
                    });
                };
            };
            service.getBindDateById = function (bsoId) {
                return Restangular.one(serviceUrl + '/date?bsoId=' + bsoId).get();
            };
            service.getTrashBsoByBsoId = function (bsoId) {
                return Restangular.one(serviceUrl + '/trash-info?bsoId=' + bsoId).get();
            };
            service.getBsosForTask = function (filters) {
                return Restangular.one(serviceUrl + '/task-slist').get(GridService.getFilterParams(filters));
            };
            service.getBsosForTaskCard = function (filters) {
                return Restangular.one(serviceUrl + '/task-card-slist').get(GridService.getFilterParams(filters));
            };
            service.getBsosForSchedule = function (taskId, employeeScheduleId, currenId) {
                var params = {
                    taskId: taskId !== null ? taskId : 0,
                    employeeScheduleId: employeeScheduleId !== null ? employeeScheduleId : 0,
                    currentId: currenId
                };
                return Restangular.one(serviceUrl + '/schedule-slist').get(params);
            };
            return service;
        }]);
