'use strict';

/**
 * @ngdoc function
 * @name armada.controller:EmployeeCtrl
 * @description
 *
 * Сотрудник
 */
angular.module('armada')
	.controller('EmployeeCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'EmployeesService',
		function ($controller, $scope, $stateParams, UtilService, EmployeesService) {

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

			$scope.getItem = EmployeesService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.employees.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'История', route: 'history'},
					{heading: 'Режим работы', route: 'calendar'}
				]
			});
		}]);