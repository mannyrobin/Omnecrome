'use strict';

/**
 * @ngdoc function
 * @name armada.controller:BaseTabsCtrl
 * @description
 *
 * Сотрудник
 */
angular.module('armada')
	.controller('BaseTabsCtrl', ['$scope', '$stateParams', 'UtilService', 'Notification',
		function ($scope, $stateParams, UtilService, Notification) {

			$scope.init = function (parameters) {

				$scope.tabRoute = parameters.route;

				$scope.tabData = [];//parameters.tabs;
				angular.forEach(parameters.tabs, function (tab) {
					if ((angular.isDefined(parameters.checkPermission) && parameters.checkPermission(tab))
						|| !angular.isDefined(parameters.checkPermission)) {
						tab.route = $scope.tabRoute + "." + tab.route;
						$scope.tabData.push(tab);
					}
				});

				/*$scope.tabData = parameters.tabs;
				 angular.forEach($scope.tabData, function(tab) {
				 tab.route = $scope.tabRoute + "." + tab.route;
				 });*/

				$scope.isShowTabs = function () {
					return UtilService.isShowRouteTabs($scope.tabData, [$scope.tabRoute]);
				};

				if ($scope.id == null) {
					$scope.id = $stateParams.itemId;
				}

				if (!$scope.getItemCrumb) {
					$scope.getItemCrumb = function (item) {
						return item.id;
					}
				}

				$scope.refreshBreadCrumbles = function () {
					if ($scope.getItem != null && $scope.id != null && $scope.id != "") {
						$scope.getItem($scope.id).then(function (result) {
							$scope.item = result;
							$scope.itemCrumb = $scope.getItemCrumb(result);
						}).catch(function () {
							Notification.error("Ошибка при получении информации об объекте");
						});
					}
				};

				$scope.refreshBreadCrumbles();
			}
		}]);