angular.module('armada')
	.controller('ExchangeObjectsCtrl', ['$scope', 'ExchangeObjectService', 'VisService', 'GridService', '$state',
		function ($scope, ExchangeObjectService, VisService, GridService, $state) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = ExchangeObjectService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Объекты обмена с ВИС"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-3",
				href: function (row) {
					return "vis/exchange-objects/" + row.id + "/info";
				}
			}, {
				id: "vis",
				label: "ВИС",
				class: "col-md-1"
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
			}, {
				name: "vis",
				type: "select",
				load: VisService.getList,
				placeholder: "ВИС"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти объект обмена с ВИС",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.ExhangeObjects',
				load: $scope.gridScope.service.getPage
			});
		}]);
