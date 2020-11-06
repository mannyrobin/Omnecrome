angular.module('armada')
	.controller('VissCtrl', ['$scope', 'VisService', 'GridService', '$state',
		function ($scope, VisService, GridService, $state) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = VisService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "ВИС"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-3",
				href: function (row) {
					return "vis/vis/" + row.id + "/info";
				}
			}, {
				id: "description",
				label: "Описание"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "name",
				type: "text",
				placeholder: "Название"
			}, {
				name: "cod",
				type: "text",
				placeholder: "Код"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти ВИС",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.VissGrid',
				load: $scope.gridScope.service.getPage
			});

		}]);
