'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DepartmentCtrl
 * @description
 *
 * Подразделение
 */
angular.module('armada')
	.controller('DepartmentCtrl', ['$controller', '$scope', '$state', '$stateParams', 'UtilService', 'DepartmentsService', 'forPlanUse',
		function ($controller, $scope, $state, $stateParams, UtilService, DepartmentsService, forPlanUse) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.reloadState = function () {
				$state.reload();
			};

			$scope.getItem = DepartmentsService.getItem;

			$scope.forPlanUse = forPlanUse;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				checkPermission: function (tab) {
					return tab.show;
				},
				route: "app.main.nsi.departments.detail",
				tabs: [
					{heading: 'Общее', route: 'info', show: true},
					{heading: 'Местоположение', route: 'location', show: true},
					{heading: 'История', route: 'history', show: true},
					{heading: 'Правила формирования БСО', route: 'rules.list', show: $scope.forPlanUse}
				]
			});
		}]);