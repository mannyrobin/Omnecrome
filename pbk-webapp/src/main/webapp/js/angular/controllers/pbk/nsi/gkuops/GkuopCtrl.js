'use strict';

/**
 * @ngdoc function
 * @name armada.controller:GkuopCtrl
 * @description
 *
 * ГКУ ОП.
 */
angular.module('armada')
	.controller('GkuopCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'GkuopsService',
		function ($controller, $scope, $stateParams, UtilService, GkuopsService) {

			$scope.getItemCrumb = function (item) {
				var result = '';
				if (item.surname && item.surname.length > 0) {
					result = item.surname;
				}
				if (item.name && item.name.length > 0) {
					result = result + ' ' + item.name[0] + '.';
				}
				if (item.patronumic && item.patronumic.length > 0) {
					result = result + ' ' + item.patronumic[0] + '.';
				}
				return result;
			};

			$scope.getItem = GkuopsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.gkuops.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'История', route: 'history'}
				]
			});
		}]);