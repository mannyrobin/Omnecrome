angular.module('armada')
	.controller('PlanCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'DepartmentsService',
		function ($controller, $scope, $stateParams, UtilService, DepartmentsService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = DepartmentsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.pbk.plans.detail",
				tabs: [
					{heading: 'Расписание', route: 'schedules'},
					{heading: 'Бригады', route: 'brigades'},
					{heading: 'Задания', route: 'tasks-table'},
					{heading: 'Табель', route: 'timesheets'}
				]
			});
		}]);