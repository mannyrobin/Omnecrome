angular.module('armada')
	.service('PlanBrigadesService', ['UrlService', 'BaseItemService', 'Restangular',
		function (UrlService, BaseItemService, Restangular) {
			var baseUrl = UrlService.getApiPrefix() + 'pbk/plan/brigades';

			var service = BaseItemService.init({
				serviceurl: UrlService.getApiPrefix() + 'pbk/plan/brigades',
				api: ['page', 'get', 'add', 'edit', 'slist']
			});

			service.approveBrigades = function (item) {
				return Restangular.one(baseUrl + '/approve').post("", item);
			};

			service.disapproveBrigades = function (item) {
				return Restangular.one(baseUrl + '/disapprove').post("", item);
			};

			service.recreateBrigades = function (item) {
				return Restangular.one(UrlService.getApiPrefix() + 'pbk/plans/recreate').post("", item);
			};

			service.distributeRoutes = function (item) {
				return Restangular.one(UrlService.getApiPrefix() + 'pbk/plans/distribute-routes').post("", item);
			};

			service.ratioReCalc = function (item) {
				return Restangular.one(UrlService.getApiPrefix() + 'pbk/plans/ratio-recalc').post("", item);
			};

			service.exportBrigades = function (format) {
				window.location.href = "/api/pbk/reports/aspose/pbk/plans/brigades?format=" + format
					+ "&deptId=" + this.tableScope.filters[0].value
					+ "&fromDate=" + moment(this.tableScope.filters[1].value.fromDate).format('DD.MM.YYYY')
					+ "&toDate=" + moment(this.tableScope.filters[1].value.toDate).format('DD.MM.YYYY');
			}

			service.exportAllBrigades = function (format, fromDate, toDate) {
				window.location.href = "/api/pbk/reports/aspose/pbk/plans/brigades?format=" + format
					+ "&fromDate=" + moment(fromDate).format('DD.MM.YYYY')
					+ "&toDate=" + moment(toDate).format('DD.MM.YYYY');
			}

			service.importAllBrigades = function (formData, tfile) {
				return Restangular.one(UrlService.getPrefix() + "reports/aspose/pbk/plans/brigades/import")
					.withHttpConfig({transformRequest: angular.identity})
					.customPOST(formData, '', null, {'Content-Type': undefined})
			};

			return service;
		}]);