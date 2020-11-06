angular.module('armada')
	.controller('ExchangesCtrl', ['$scope', 'ExchangesService', 'GridService', '$state', 'ExchangeStateService', 'VisService', 'TransportTypeService', 'ExchangeOperationService', 'ExchangeObjectService', 'Notification',
		function ($scope, ExchangesService, GridService, $state, ExchangeStateService, VisService, TransportTypeService, ExchangeOperationService, ExchangeObjectService, Notification) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = ExchangesService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Журнал обмена с ВИС"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "workDate",
				label: "Дата",
				class: "col-md-1",
				href: function (row) {
					return "vis/exchanges/" + row.id + "/info";
				}
			}, {
				id: "vis",
				label: "ВИС",
				class: "col-md-1"
			}, {
				id: "exchangeObject",
				label: "Объект взаимодействия",
				class: "col-md-2"
			}, {
				id: "exchangeOperation",
				label: "Операция",
				class: "col-md-1"
			}, {
				id: "transportType",
				label: "Транспорт",
				class: "col-md-1"
			}, {
				id: "parameter",
				label: "Параметр",
				class: "col-md-2"
			}, {
				id: "exchangeState",
				label: "Статус",
				class: "col-md-1"
			}, {
				id: "errorContent",
				label: "Сообщение об ошибке",
				class: "col-md-3"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: {
					fromName: "dateFrom",
					toName: "dateTo"
				},
				defval: {
					fromDate: new Date(),
					toDate: new Date()
				},
				type: "range",
				placeholder: {
					from: "Начало периода",
					to: "Окончание периода"
				}
			}, {
				name: "vis",
				type: "select",
				load: VisService.getList,
				placeholder: "ВИС"
			}, {
				name: "transportType",
				type: "select",
				load: TransportTypeService.getList,
				placeholder: "Вид транспорта"
			}, {
				name: "exchangeOperation",
				type: "select",
				load: ExchangeOperationService.getList,
				placeholder: "Тип операции"
			}, {
				name: "exchangeObject",
				type: "select",
				load: ExchangeObjectService.getList,
				placeholder: "Объект взаимодействия"
			}, {
				name: "exchangeState",
				type: "select",
				load: ExchangeStateService.getList,
				placeholder: "Статус"
			}, {
				name: "parameter",
				type: "text",
				placeholder: "Параметр"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти взаимодействие",
				gridScope: $scope.gridScope
			};

			/* Кнопки */
			$scope.gridScope.buttons = {
				extraButtons: [{
					tip: "Повторить",
					action: function (row) {
						$scope.gridScope.service.repeat({id: row.id}
						).then(function (response) {
							Notification.info("Обмен успешно запущен");
						}).catch(function (response) {
							Notification.error("Обмен запустить не удалось");
						});
					},
					class: "glyphicon glyphicon-repeat"
				}],
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "workDate",
				gridName: $state.current.name + '.exchangesGrid',
				load: $scope.gridScope.service.getPage
			});
		}]);

