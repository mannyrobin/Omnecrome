'use strict';

angular.module('armada')
	.controller('StandardReportsCtrl', ['$scope', 'UserService',
		function ($scope, UserService) {

			$scope.hasWatchPrivilegeStandartReports1 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD1']);
			$scope.hasWatchPrivilegeStandartReports2 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD2']);
			$scope.hasWatchPrivilegeStandartReports3 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD3']);
			$scope.hasWatchPrivilegeStandartReports4 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD4']);
			$scope.hasWatchPrivilegeStandartReports5 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD5']);
			$scope.hasWatchPrivilegeStandartReports6 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD6']);
			$scope.hasWatchPrivilegeStandartReports7 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD7']);
			$scope.hasWatchPrivilegeStandartReports8 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD8']);
			$scope.hasWatchPrivilegeStandartReports9 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD9']);
			$scope.hasWatchPrivilegeStandartReports10 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD10']);
			$scope.hasWatchPrivilegeStandartReports11 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD11']);
			$scope.hasWatchPrivilegeStandartReports12 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD12']);
			$scope.hasWatchPrivilegeStandartReports13 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD13']);
			$scope.hasWatchPrivilegeStandartReports14 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD14']);
			$scope.hasWatchPrivilegeStandartReports15 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD15']);
			$scope.hasWatchPrivilegeStandartReports16 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD16']);
			$scope.hasWatchPrivilegeStandartReports17 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD17']);
			$scope.hasWatchPrivilegeStandartReports18 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD18']);
			$scope.hasWatchPrivilegeStandartReports19 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD19']);
			$scope.hasWatchPrivilegeStandartReports20 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD20']);
			$scope.hasWatchPrivilegeStandartReports21 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD21']);
			$scope.hasWatchPrivilegeStandartReports22 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD22']);
			$scope.hasWatchPrivilegeStandartReports23 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD23']);
            $scope.hasWatchPrivilegeStandartReports24 = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD24']);
		}]);
