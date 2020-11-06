angular.module('armada')
	.controller('ExchangeConfigsCtrl', ['$scope', 'ExchangeConfigsService', 'GridService', '$state', 'ExchangeOperationService', 'ExchangeObjectService', 'VisService', 'TransportTypeService', 'isNotReadOnlyUser', '$modal',
		function ($scope, ExchangeConfigsService, GridService, $state, ExchangeOperationService, ExchangeObjectService, VisService, TransportTypeService, isNotReadOnlyUser, $modal) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = ExchangeConfigsService;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Конфигурации взаимодействия",
					addTip: "Добавить конфигурацию взаимодействия"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-2",
				href: function (row) {
					return "vis/exchange-configs/" + row.id + "/info";
				}
			}, {
				id: "vis",
				label: "ВИС",
				class: "col-md-1"
			}, {
				id: "transportType",
				label: "Вид транспорта",
				class: "col-md-1"
			}, {
				id: "exchangeOperation",
				label: "Тип операции",
				class: "col-md-1"
			}, {
				id: "exchangeObject",
				label: "Объект взаимодействия",
				class: "col-md-2"
			}, {
				id: "description",
				label: "Описание",
				class: "col-md-3"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "code",
				type: "text",
				placeholder: "Код"
			}, {
				name: "name",
				type: "text",
				placeholder: "Название"
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
			},
				{
					name: "isActive",
					type: "select",
					list: [{id: 0, name: 'Неактивный'}, {id: 1, name: 'Активный'}],
					placeholder: "Активность"
				}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти конфигурацию взаимодействия",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить конфигурацию взаимодействия",
					removeConfirm: "Вы действительно хотите удалить конфигурацию взаимодействия?"
				},
				check: {
					isRemoveEnable: function (row) {
						if (!row) {
							return false;
						}
						return !row.delete;
					}
				},
				extraButtons: [{
					tip: "Запустить",
					action: function (row) {
						$modal.open({
							templateUrl: "templates/dialogs/" + "vis/exchange-config/ExchangeConfigsStartDlg.html",
							windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
							controller: "ExchangeConfigsStartCtrl",
							size: "md",
							resolve: {
								data: function () {
									return {
										title: "Запуск обмена",
										id: row.id
									}
								}
							}
						});
					},
					class: "glyphicon glyphicon-play-circle",
					show: function (row) {
						return !row.delete && row.active;
					}
				}],
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.exchangeConfigsGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"vis/exchange-config/ExchangeConfigsEditDlg.html", "ExchangeConfigsEditCtrl", "Создание конфигурации взаимодействия", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении конфигурации взаимодействия", $scope.gridScope, $scope.gridScope.service.removeItem, true);

			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				if (row.delete) {
					return 'remove-text-record';
				}
				return !row.active ? 'inactive-record' : null;
			};

			$scope.gridScope.getRowClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.delete ? 'remove-record' : null;
			};
		}]);

