angular.module('armada')
	.controller('PlanTaskCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'TasksService', 'TaskAskpContactlessCardsService',
		function ($controller, $scope, $stateParams, UtilService, TasksService, TaskAskpContactlessCardsService) {

			$scope.id = $stateParams.taskId;

			$scope.getItemCrumb = function (item) {
				var result = '';
				var fio = item.employeeName.split(' ');
				if (fio[0] && fio[0].length > 0) {
					result = fio[0];
				}
				if (fio[1] && fio[1].length > 0) {
					result = result + ' ' + fio[1][0] + '.';
				}
				if (fio[2] && fio[2].length > 0) {
					result = result + ' ' + fio[2][0] + '.';
				}
				if (item.shiftName) {
					result = result + ' - ' + item.shiftName;
				}
				if (item.planVenueName) {
					result = result + ' - ' + item.planVenueName;
				}
				return result;
			};

			$scope.getItem = TasksService.getGridViewByTaskId;
			TaskAskpContactlessCardsService.getTitle($stateParams.itemId).then(function (title) {
				$scope.concatlessTitle = title;
			});

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.pbk.plans.detail.task-detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'Краткий отчет', route: 'report'},
					{heading: 'АСКП по маршрутам', route: 'askp-pusk-checks-routes'},
					{heading: 'АСКП по часам', route: 'askp-pusk-checks-hours'},
					{heading: 'Использованные БСО', route: 'used-bsos'},
					{heading: 'Файлы задания', route: 'files'},
					{heading: 'Карты к изъятию', route: 'withdrawn-cards'},
					{heading: 'Маршруты', route: 'routes'},
					{heading: 'Проходы по БСК контролера', route: 'contactless-card'}
				]
			});
		}]);
