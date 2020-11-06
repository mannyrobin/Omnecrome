'use strict';

/**
 * @ngdoc function
 * @name armada.filter:to_trusted
 * @description
 *
 * Фильтр для биндинга html из доверенного источника.
 */
angular.module('armada')
	.filter('to_trusted', ['$sce', function ($sce) {
		return function (text) {
			return $sce.trustAsHtml(text);
		};
	}]);
