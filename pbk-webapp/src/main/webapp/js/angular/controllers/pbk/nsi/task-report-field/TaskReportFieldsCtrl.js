angular.module('armada')
	.controller('TaskReportFieldsCtrl', ['$scope', 'TaskReportFieldsService', '$modal', 'Notification', 'GridService', '$state',
		function ($scope, TaskReportFieldsService, $modal, Notification, GridService, $state) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = TaskReportFieldsService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Виды операций, выполняемые контролерами в рамках проведения билетного контроля"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-3",
				href: function (row) {
					return "nsi/task-report-fields/" + row.id + "/info";
				}
			}, {
				id: "description",
				label: "Описание"
			}]

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "name",
				type: "text",
				placeholder: "Имя"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти вид операции, выполняемые контролерами в рамках проведения билетного контроля",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.TaskReportFieldesGrid',
				load: $scope.gridScope.service.getPage
			});

		}]);
