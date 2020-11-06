angular.module('armada')
	.controller('SexesCtrl', ['$scope', 'SexesService', '$modal', 'Notification', 'GridService', '$state',
		function ($scope, SexesService, $modal, Notification, GridService, $state) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = SexesService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Пол"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-3",
				href: function (row) {
					return "nsi/sexes/" + row.id + "/info";
				}
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "name",
				type: "text",
				placeholder: "Имя"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти пол",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.sexesGrid',
				load: $scope.gridScope.service.getPage
			});

		}]);
