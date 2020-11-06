angular.module('armada')
	.service('RoutesService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', 'WktParseService', function (UrlService, BaseItemService, Restangular, GridService, WktParseService) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/routes',
			api: ['page', 'get', 'add', 'edit', 'remove', 'history', 'slist', 'version']
		});

		function processTrajectories(trajectories) {
			var results = [];
			for (var i = 0; i < trajectories.length; i++) {
				results.push({
					id: trajectories[i].id,
					routeNumber: trajectories[i].routeNumber,
					geoJsonLine: WktParseService.wktToGeoJson(trajectories[i].wktLineStr),
					trajectoryTypeId: trajectories[i].trajectoryTypeId,
					trajectoryTypeName: trajectories[i].trajectoryTypeName,
					routeType: trajectories[i].routeType
				});
			}
			return results;
		}

		service.getRouteTrajectory = function (id) {
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/routes' + '/' + id + '/trajectory').get().then(function (trajectories) {
				return processTrajectories(trajectories);
			});
		};

		service.getRouteTrajectoryByRouteNum = function (routeNum) {
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/routes/trajectories').get({routeNum: routeNum}).then(function (trajectories) {
				return processTrajectories(trajectories);
			});
		};
		service.getRouteStops = function (id, trType) {
			var trTypeParam;
			if (angular.isDefined(trType)) {
				trTypeParam = {trType: trType};
			}
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/routes' + '/' + id + '/stops').get(trTypeParam).then(function (stops) {
				var results = [];
				for (var i = 0; i < stops.length; i++) {
					results.push({
						id: stops[i].id,
						type: 'stop',
						routeId: stops[i].routeId,
						name: stops[i].name,
						routeNames: stops[i].routeNames,
						nameWithoutDistrict: stops[i].nameWithoutDistrict,
						geoJsonPoint: WktParseService.wktToGeoJson(stops[i].wktPointStr)
					});
				}
				return results;
			});
		};

		service.getSelectItemsForTask = function (filters) {
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/routes/slist-task').get(
				GridService.getFilterParams(filters));
		};

		service.getRoutesByDistrict = function (filter, forVenue) {
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/routes/slist-district').get(GridService.getFilterParams(filter));
		};

		service.getRoutesByVenue = function (id) {
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/routes/slist-venue').get({venueId: id});
		};

		service.getRoutesForDistrictAdd = function (filter, forVenue) {
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/routes/slist-route-distr-add').get(GridService.getFilterParams(filter));
		};

		service.getLazySelectItemsForVenues = function (filter) {
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/routes/slist-route-distr-lazy').get(GridService.getFilterParams(filter));
		};

		service.getNightRoutes = function (filter) {
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/routes/slist-night-routes').get(GridService.getFilterParams(filter));
		};

		service.getNightRoutesForTask = function (filter) {
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/routes/slist-night-routes-for-task').get(GridService.getFilterParams(filter));
		};

		service.getRoutesOfCounty = function (filter) {
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/routes/slist-route-county').get(GridService.getFilterParams(filter));
		};

		service.getRoutesForVenueDistrict = function (filter) {
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/routes/slist-route-venue-district').get(GridService.getFilterParams(filter));
		};

		service.getGtfsRouteList = function (filter) {
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/routes/slist-gtfs-routes').get(GridService.getFilterParams(filter));
		}

		return service;
	}]);
