angular.module('armada')
	.controller('TaskWithdrawnCardsCtrl', ['$scope', 'TaskWithdrawnCardsService', 'GridService', '$state', '$modal', 'isNotReadOnlyUser', '$stateParams', 'RoutesService',
		function ($scope, TaskWithdrawnCardsService, GridService, $state, $modal, isNotReadOnlyUser, $stateParams, RoutesService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = TaskWithdrawnCardsService;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				remove: isNotReadOnlyUser,
				edit: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Журнал карт к изъятию",
					addTip: "Добавить карту к изъятию"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "actNumber",
				label: "Номер акта об изъятии",
				class: "col-md-2"
			}, {
				id: "cardNumber",
				label: "Номер карты",
				class: "col-md-2"
			}, {
				id: "cardName",
				label: "Тип карты",
				class: "col-md-2"
			}, {
				id: "routeNumber",
				label: "Маршрут",
				class: "col-md-2"
			}, {
				id: "ownerFio",
				label: "ФИО владельца карты",
				class: "col-md-2"
			}, {
				id: "violatorFio",
				label: "ФИО нарушителя",
				class: "col-md-2"
			}, {
				id: "legitimate",
				label: "Легитимный документ",
				class: "col-md-2"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "taskId",
				type: "text",
				defval: $stateParams.taskId != null ? $stateParams.taskId : $stateParams.taskId != null ? $stateParams.taskId : $stateParams.itemId,
				hide: true
			}, {
				name: "actNumber",
				type: "text",
				placeholder: "Номер акта об изъятии"
			}, {
				name: "cardNumber",
				type: "text",
				placeholder: "Номер карты"
			}, {
				name: "cardName",
				type: "text",
				placeholder: "Тип карты"
			}, {
				name: "routeIds",
				type: "multiselect",
				placeholder: "Маршрут",
				load: RoutesService.getList
			}, {
				name: "ownerFio",
				type: "text",
				placeholder: "ФИО владельца карты"
			}, {
				name: "violatorFio",
				type: "text",
				placeholder: "ФИО нарушителя"
			}, {
				name: "legitimate",
				type: "yesno",
				defval: {id: -1, name: "Все"},
				placeholder: "Легитимный"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти карту к изъятию",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить карту к изъятию",
					removeConfirm: "Вы действительно хотите удалить карту к изъятию?",
					editTip: "Редактировать карту к изъятию"
				},
				check: {
					isRemoveEnable: function (row) {
						return !row.delete;
					}
				},
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "id",
				gridName: $state.current.name + '.withdrawnCardsGrid',
				load: $scope.gridScope.service.getPage
			});

			$scope.gridScope.addSelItemColumn = true;

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/pbk/tasks/TaskWithdrawnCardEditDlg.html", "TaskWithdrawnCardEditCtrl", "Создание карты к изъятию", "md", $scope.gridScope);
			$scope.gridScope.editRow = GridService.getEditAction(
				"pbk/pbk/tasks/TaskWithdrawnCardEditDlg.html", "TaskWithdrawnCardEditCtrl", "Редактирование карты к изъятию", "md", $scope.gridScope);

			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.delete == true ? 'remove-text-record' : null;
			};

			$scope.gridScope.getRowClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.delete ? 'remove-record' : null;
			};

			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении карты к изъятию", $scope.gridScope, $scope.gridScope.service.removeItem);
		}]);
