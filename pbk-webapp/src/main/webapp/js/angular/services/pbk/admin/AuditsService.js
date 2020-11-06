angular.module('armada')
	.service('AuditsService', ['Restangular', 'BaseItemService', 'UrlService', 'GridService', function (Restangular, BaseItemService, UrlService, GridService) {

		var url = UrlService.getApiPrefix() + 'admin/audit';

		var service = BaseItemService.init({
			serviceurl: url,
			api: ['page', 'get']
		});

		service.getLevels = function () {
			var baseLevels = Restangular.all(url + '/levels');
			return baseLevels.getList();
		};
		service.getTypes = function () {
			var baseTypes = Restangular.all(url + '/types');
			return baseTypes.getList();
		};
		service.getOperations = function () {
			var baseOperationsTypes = Restangular.all(url + '/operations');
			return baseOperationsTypes.getList();
		};
		return service;

		/*
		 function getPageNumber(start, number) {
		 if (!start || !number) {
		 return null;
		 }
		 return Math.round(start / number) + 1;
		 }

		 // TODO: привести к общему виду
		 var serviceurl = UrlService.getApiPrefix() + 'admin/audit';
		 return {
		 getPage: function (start, number, predicate, reverse, filters) {
		 return Restangular.one(serviceurl).get(
		 GridService.getPageParams(start, number, predicate, reverse, filters));
		 },
		 getAuditEvents: function (start, number, predicate, reverse, params) {
		 var sord = reverse ? "asc" : "desc";
		 var getParams = {rows: number, page: getPageNumber(start, number), sord: sord, sidx: predicate};
		 if (params && params != null) {
		 angular.forEach(params, function (value, name) {
		 getParams[name] = value;
		 });
		 }
		 return Restangular.one(serviceurl).get(getParams);
		 },
		 getLevels: function () {
		 var baseLevels = Restangular.all(serviceurl + '/levels');
		 return baseLevels.getList();
		 },
		 getTypes: function () {
		 var baseTypes = Restangular.all(serviceurl + '/types');
		 return baseTypes.getList();
		 },
		 getOperations: function () {
		 var baseOperationsTypes = Restangular.all(serviceurl + '/operations');
		 return baseOperationsTypes.getList();
		 }

		 };*/
	}]);