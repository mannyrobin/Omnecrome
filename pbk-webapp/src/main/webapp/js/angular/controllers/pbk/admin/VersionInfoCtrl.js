'use strict';

angular.module('armada')
	.controller('VersionInfoCtrl', ['$scope', 'UtilService',
		function ($scope, UtilService) {
			$scope.versionInfo = {};
			UtilService.getVersionInfo().then(function (result) {
				$scope.versionInfo.pomVersion = result.pomVersion;
				$scope.versionInfo.buildNumber = result.buildNumber;
				$scope.versionInfo.buildDate = result.buildDate;
				$scope.versionInfo.buildTag = result.buildTag;
			});
		}]);