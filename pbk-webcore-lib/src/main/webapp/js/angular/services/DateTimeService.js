'use strict';

/**
 * @ngdoc service
 * @name armada.UrlService
 * @description
 *
 * Сервис урлов.
 */
angular.module('armada')
	.service('DateTimeService', ['Restangular', function (Restangular) {
		return {
			//Add N days to date
			addDays: function (date, days) {
				var result = new Date(date);
				result.setDate(result.getDate() + days);
				return result;
			}
		};
	}]);