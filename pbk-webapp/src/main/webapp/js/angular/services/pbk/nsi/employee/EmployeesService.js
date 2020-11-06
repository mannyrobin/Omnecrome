angular.module('armada')
    .service('EmployeesService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
        var serviceUrl = UrlService.getApiPrefix() + 'nsi/employees';

        var service = BaseItemService.init({
            serviceurl: serviceUrl,
            api: ['page', 'get', 'add', 'edit', 'remove', 'history', 'slist', 'version']
        });

        service.getEmployeeByTaskId = function (id) {
            return Restangular.one(serviceUrl + '/get-by-task/' + id).get();
        };
        service.getEmployeesOnDate = function (id) {
            return Restangular.one(serviceUrl + '/get-on-task-date/' + id[0].value).get();
        };
        service.getEmployeesForSign = function (filters) {
            return Restangular.one(serviceUrl + '/slist_sign').get(GridService.getFilterParams(filters));
        };
        service.getPhoto = function (id) {
            return Restangular.one(serviceUrl + '/get-photo-by-id/' + id).get();
        };
        service.deletePhoto = function (id) {
            return Restangular.one(serviceUrl + '/delete-photo/' + id).get();
        };
        service.addPhoto = function (id, photo) {
            var dataObj = {};
            dataObj.photo=photo;
            return Restangular.one(serviceUrl + '/add-photo/' + id).post("", photo);
        };
        return service;
    }]);
