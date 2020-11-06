'use strict';

/**
 * @ngdoc function
 * @name armada.controller:HeaderCtrl
 * @description
 *
 * Контроллер страницы логина
 */
angular.module('armada')
	.controller('HeaderCtrl', ['$scope', '$rootScope', '$cookieStore', '$state', 'UserService', 'UtilService', 'Notification', '$window',
		function ($scope, $rootScope, $cookieStore, $state, UserService, UtilService, Notification) {

			$scope.manual = function () {
				window.location.href = "/api/pbk/report/manual/download"
			}

			$scope.logout = function () {
				UserService.logout();
				delete $rootScope.userInfo;
				delete $rootScope.authToken;
				$cookieStore.remove('authToken');
				UserService.clearReportsAuth();
				$state.go("login");
			};

			var roleNamesStr = "";
			if ($rootScope.userInfo.roleNames.length > 0) {
				for (var i = 0; i < $rootScope.userInfo.roleNames.length; i++) {
					roleNamesStr = roleNamesStr + $rootScope.userInfo.roleNames[i];
					if (i != ($rootScope.userInfo.roleNames.length - 1)) {
						roleNamesStr = roleNamesStr + ", ";
					}
				}
				roleNamesStr = "(" + roleNamesStr + ")";
			}
			$scope.roleNamesStr = roleNamesStr;
			//var test = $scope.userInfo.name;
			//$scope.userName = UserService.getCurrUsrName();
			/*function getUserName() {
			 UserService.getUserName().then(function (result) {
			 $scope.userName = result.result;
			 }).catch(function(){
			 Notification.error("Ошибка при получении пользователя");
			 });
			 }
			 getUserName();*/

			//admin menu
			$scope.hasWatchAdminMenuPrivilege = UserService.hasAnyRole(['VIS', 'DEBUG_TO_REPLACE', 'VIS_EXCHANGES_OBJ', 'VIS_EXCHANGES_CONF', 'VIS_EXCHANGES', 'ADMIN_AUDIT', 'ADMIN_SETTING', 'ADMIN_USER', 'ADMIN_ROLE', 'ADMIN_AUDIT_TYPES', 'DEBUG_TO_REPLACE', 'ASKP_PUSK_CHECKS']);

			//vis
			$scope.hasWatchVisMenuPrivilege = UserService.hasAnyRole(['VIS', 'DEBUG_TO_REPLACE', 'VIS_EXCHANGES_OBJ', 'VIS_EXCHANGES_CONF', 'VIS_EXCHANGES', 'ASKP_PUSK_CHECKS']);
			$scope.hasWatchVisPrivilege = UserService.hasAnyRole(['VIS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchVisExchangePrivilege = UserService.hasAnyRole(['VIS_EXCHANGES', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchVisExchangeObjPrivilege = UserService.hasAnyRole(['VIS_EXCHANGES_OBJ', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchVisExchangeConfPrivilege = UserService.hasAnyRole(['VIS_EXCHANGES_CONF', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchAskpPuskCheckBindPrivilege = UserService.hasAnyRole(['ASKP_PUSK_CHECKS', 'DEBUG_TO_REPLACE']);

			$scope.hasWatchAuditPrivilage = UserService.hasAnyRole(['ADMIN_AUDIT', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchAuditTypePrivilage = UserService.hasAnyRole(['ADMIN_AUDIT_TYPES', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchUserPrivilege = UserService.hasAnyRole(['ADMIN_USER', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchRolesPrivilege = UserService.hasAnyRole(['ADMIN_ROLE', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchSettingsPrivilege = UserService.hasAnyRole(['ADMIN_SETTING', 'DEBUG_TO_REPLACE']);

			//pbk
			$scope.hasWatchPbkMenuPrivilege = UserService.hasAnyRole(['BSO', 'BONUSES', 'PBK_PLANS', 'PBK_TASKS', 'ROUTES_RATIO', 'WITHDRAWN_CARDS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchPlansPrivilege = UserService.hasAnyRole(['PBK_PLANS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchTasksPrivilege = UserService.hasAnyRole(['PBK_TASKS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchRouteRatioPrivilege = UserService.hasAnyRole(['ROUTES_RATIO', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchBSOPrivilege = UserService.hasAnyRole(['BSO', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchBonusesPrivilege = UserService.hasAnyRole(['BONUSES', 'DEBUG_TO_REPLACE']);
			$scope.hasWithdrawnDrawnCardsPrivilege = UserService.hasAnyRole(['WITHDRAWN_CARDS', 'DEBUG_TO_REPLACE']);
			$scope.hasCreateTasksPrivilege = UserService.hasAnyRole(['CREATE_TASKS', 'DEBUG_TO_REPLACE']);
			//nsi
			$scope.hasWatchNsiMenuPrivilege = UserService.hasAnyRole(['BSO_NUMBERS', 'OPERATION_TYPES', 'TS_MODELS', 'SHIFTS', 'VENICLES', 'DUTIES', 'COUNTIES', 'DRIVERS', 'EQUIPMENTS', 'BSO', 'TS_TYPES', 'TS_CAPACITIES', 'DVRS', 'CONTRACTLESS_CARD', 'DEPARTMENTS', 'EMPLOYEES', 'ROUTE_TS_KINDS', 'ROUTES', 'STOPS', 'PUSKS', 'OFFICIAL_CARDS', 'VENUES', 'TICKETS', 'EMPLOYEES_SEX', 'GKUOPS_EMPLOYEES', 'CALENDAR', 'PARKS', 'DISTRICTS', 'DEBUG_TO_REPLACE']);
			//nsi-plans
			$scope.hasWatchNsiPlansMenuPrivilege = UserService.hasAnyRole(['BSO_NUMBERS', 'BSO', 'OPERATION_TYPES', 'SHIFTS', 'VENUES', 'CALENDAR', 'TICKETS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchNsiBSOMenuPrivilege = UserService.hasAnyRole(['BSO_NUMBERS', 'BSO', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchBSONumRulesPrivilege = UserService.hasAnyRole(['BSO_NUMBERS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchVenuesPrivilege = UserService.hasAnyRole(['VENUES', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchCalendarPrivilege = UserService.hasAnyRole(['CALENDAR', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchTicketsPrivilege = UserService.hasAnyRole(['TICKETS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchShiftsPrivilege = UserService.hasAnyRole(['SHIFTS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchTaskReportFieldsPrivilege = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'OPERATION_TYPES']);

			//nsi-employee
			$scope.hasWatchNsiEmployeeMenuPrivilege = UserService.hasAnyRole(['DRIVERS', 'OFFICIAL_CARDS', 'DVRS', 'PUSKS', 'CONTRACTLESS_CARD', 'EMPLOYEES', 'GKUOPS_EMPLOYEES', 'EMPLOYEES_SEX', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchDriversPrivilege = UserService.hasAnyRole(['DRIVERS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchDepartmentsPrivilege = UserService.hasAnyRole(['DEPARTMENTS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchGkuopsEmployeesPrivilege = UserService.hasAnyRole(['GKUOPS_EMPLOYEES', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchSexEmployeesPrivilege = UserService.hasAnyRole(['EMPLOYEES_SEX', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchEmployeesPrivilege = UserService.hasAnyRole(['EMPLOYEES', 'DEBUG_TO_REPLACE']);

			//nsi-employee-devices
			$scope.hasWatchEmployeeDevicesMenuPrivilege = UserService.hasAnyRole(['OFFICIAL_CARDS', 'DVRS', 'PUSKS', 'CONTRACTLESS_CARD', 'ROUTES', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchPusksPrivilege = UserService.hasAnyRole(['PUSKS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchDvrsPrivilege = UserService.hasAnyRole(['DVRS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchContactLessCardPrivilege = UserService.hasAnyRole(['CONTRACTLESS_CARD', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchOficialCardsPrivilege = UserService.hasAnyRole(['OFFICIAL_CARDS', 'DEBUG_TO_REPLACE']);

			$scope.hasWatchNsiOtherMenuPrivilege = UserService.hasAnyRole(['PARKS', 'ROUTES', 'TS_MODELS', 'EQUIPMENTS', 'TS_CAPACITIES', 'DISTRICTS', 'STOPS', 'COUNTIES', 'ROUTE_TS_KINDS', 'DEBUG_TO_REPLACE']);
			//nsi-routes
			//$scope.hasWatchNsiRoutesMenuPrivilege = UserService.hasAnyRole(['PARKS', 'DISTRICTS', 'ROUTES', 'STOPS', 'COUNTIES', 'ROUTE_TS_KINDS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchParksPrivilege = UserService.hasAnyRole(['PARKS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchDistrictsPrivilege = UserService.hasAnyRole(['DISTRICTS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchRouteTsTypesPrivilege = UserService.hasAnyRole(['ROUTE_TS_KINDS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchRoutesPrivilege = UserService.hasAnyRole(['ROUTES', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchCountiesPrivilege = UserService.hasAnyRole(['COUNTIES', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchStopsPrivilege = UserService.hasAnyRole(['STOPS', 'DEBUG_TO_REPLACE']);

			//nsi-passangers
			//$scope.hasWatchPassangersMenuPrivilege = UserService.hasAnyRole(['TS_TYPES', 'TS_MODELS', 'VENICLES', 'EQUIPMENTS', 'DRIVERS', 'TS_CAPACITIES', 'DUTIES', 'DEBUG_TO_REPLACE']);

			//nsi-ts
			//$scope.hasWatchPassangersTsMenuPrivilege = UserService.hasAnyRole(['TS_TYPES', 'TS_MODELS', 'VENICLES', 'EQUIPMENTS', 'TS_CAPACITIES', 'DUTIES', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchPassangersTsTypesPrivilege = UserService.hasAnyRole(['TS_TYPES', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchPassangersTsCapacitiesPrivilege = UserService.hasAnyRole(['TS_CAPACITIES', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchPassangersVeniclesPrivilege = UserService.hasAnyRole(['VENICLES', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchPassangersTsModelsPrivilege = UserService.hasAnyRole(['TS_MODELS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchEquipmentPrivilege = UserService.hasAnyRole(['EQUIPMENTS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchDutiesPrivilege = UserService.hasAnyRole(['DUTIES', 'DEBUG_TO_REPLACE']);

			//Пример реализации, если что можно написать и что то хитрее
			$scope.hasUserPriviled = UserService.hasAnyRole(['SIMPLE_USER', 'DEBUG_TO_REPLACE']);
			$scope.hasAdminPriviled = UserService.hasAnyRole(['ADMIN', 'DEBUG_TO_REPLACE']);
			$scope.hasSelfAdminPriviled = UserService.hasRole(['PBK_USERS']);

			$scope.hasOnlySelfAdminPriviled = $scope.hasSelfAdminPriviled && UserService.user.roleNames.length == 1 || UserService.user.roleNames.length == 0;
			//reports
			$scope.hasWatchReportMenuPrivilege = UserService.hasAnyRole(['TELEMATICS', 'REPORTS_STANDARD1', 'REPORTS_STANDARD2', 'REPORTS_STANDARD3',
				'REPORTS_STANDARD4', 'REPORTS_STANDARD5', 'REPORTS_STANDARD6', 'REPORTS_STANDARD7',
				'REPORTS_STANDARD8', 'REPORTS_STANDARD9', 'REPORTS_STANDARD10', 'REPORTS_STANDARD11',
				'REPORTS_STANDARD12', 'REPORTS_STANDARD13', 'REPORTS_STANDARD14', 'REPORTS_STANDARD15',
				'REPORTS_STANDARD16', 'REPORTS_STANDARD17', 'REPORTS_STANDARD18', 'REPORTS_STANDARD19', 
				'REPORTS_STANDARD20', 'REPORTS_STANDARD21', 'REPORTS_STANDARD22', 'REPORTS_STANDARD23',
				'REPORTS_CONSTRUCTOR', 'REPORTS_USER', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchTelematicsPrivilege = UserService.hasAnyRole(['TELEMATICS', 'DEBUG_TO_REPLACE']);
			$scope.hasWatchStandartReportsPrivilege = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD1', 'REPORTS_STANDARD2', 'REPORTS_STANDARD3',
				'REPORTS_STANDARD4', 'REPORTS_STANDARD5', 'REPORTS_STANDARD6', 'REPORTS_STANDARD7',
				'REPORTS_STANDARD8', 'REPORTS_STANDARD9', 'REPORTS_STANDARD10', 'REPORTS_STANDARD11',
				'REPORTS_STANDARD12', 'REPORTS_STANDARD13', 'REPORTS_STANDARD14', 'REPORTS_STANDARD15',
				'REPORTS_STANDARD16', 'REPORTS_STANDARD17', 'REPORTS_STANDARD18', 'REPORTS_STANDARD19', 
				'REPORTS_STANDARD20', 'REPORTS_STANDARD21', 'REPORTS_STANDARD22', 'REPORTS_STANDARD23']);
			$scope.hasWatchReportsConstructorPrivilege = UserService.hasAnyRole(['REPORTS_CONSTRUCTOR', 'DEBUG_TO_REPLACE']);

			$scope.hasWatchReportsUserPrivilege = UserService.hasAnyRole(['REPORTS_USER', 'DEBUG_TO_REPLACE']);

			$scope.getReportsAuth = function () {
				return UserService.getReportsAuth();
			};

			/*$scope.versionInfo = {};
			 UtilService.getVersionInfo().then(function(result){
			 $scope.versionInfo.pomVersion = result.pomVersion;
			 $scope.versionInfo.buildNumber = result.buildNumber;
			 $scope.versionInfo.buildDate = result.buildDate;
			 $scope.versionInfo.buildTag = result.buildTag;
			 });

			 $scope.popoverTemplate = "templates/popovers/versionInfo.html";*/

		}]);
