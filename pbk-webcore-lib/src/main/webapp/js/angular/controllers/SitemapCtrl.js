'use strict';

/**
 * @ngdoc function
 * @name armada.controller:SitemapCtrl
 * @description
 *
 * Контроллер карты сайта.
 */
angular.module('armada')
	.controller('SitemapCtrl', ['$scope', '$state', function ($scope, $state) {
		$scope.statesList = $state.get();

		$scope.getUrlByName = function (state) {
			var name = state.name;
			if (angular.isUndefined(name))
				return null;
			var nameSections = name.split(".");
			var namesForUrl = [nameSections[0]];
			var currentNameIncr = "";
			var i;
			for (i = 1; i < nameSections.length; i++) {
				currentNameIncr += nameSections[i - 1] + ".";
				namesForUrl.push(currentNameIncr + nameSections[i]);
			}
			var urlForState = "";
			for (i = 0; i < namesForUrl.length; i++) {
				var urlCurrentSection = findUrlByName(namesForUrl[i]);
				if (urlCurrentSection != null) {
					urlForState += urlCurrentSection;
				}
			}
			state.fullUrl = urlForState;
			return urlForState;
		};

		function findUrlByName(name) {
			for (var i = 0; i < $scope.statesList.length; i++) {
				if (name === $scope.statesList[i].name) {
					return $scope.statesList[i].url;
				}
			}
			return null;
		}

		$scope.toggleInfo = function (state) {
			state.isShowDevInfo = !state.isShowDevInfo;
		};

		$scope.devInfoOn = function () {
			for (var i = 0; i < $scope.statesList.length; i++) {
				$scope.statesList[i].isShowDevInfo = true;
			}
		};

		$scope.devInfoOff = function () {
			for (var i = 0; i < $scope.statesList.length; i++) {
				$scope.statesList[i].isShowDevInfo = false;
			}
		};

		$scope.isShowRow = function (state) {
			return state.abstract !== true && state.ncyBreadcrumb && state.ncyBreadcrumb.skip !== true;
		};

		$scope.goToState = function (name) {
			$state.go(name);
		};

		$scope.isShowUrl = function (url) {
			if (angular.isUndefined(url))
				return false;
			return !(url.indexOf(':') != -1);
		}

	}]);
