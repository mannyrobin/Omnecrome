angular.module('armada')
	.controller('GraficsCtrl', ['$scope', 'GraficsService', 'GridService', 'isNotReadOnlyUser', '$state',
		function ($scope, GraficsService, GridService, isNotReadOnlyUser, $state) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = GraficsService;

            $scope.isNotReadOnlyUser = isNotReadOnlyUser;

            $scope.gridScope.perms = {
                add: isNotReadOnlyUser,
                remove: isNotReadOnlyUser
            };

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Графики",
                    addTip: "Добавить график"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-2",
				href: function (row) {
					return "nsi/grafics/" + row.id + "/info";
				}
			}, {
				id: "code",
				label: "Код",
				class: "col-md-2"
            }, {
                id: "firstDate",
                label: "Дневная",
                class: "col-md-2"
            }, {
                id: "secondDate",
                label: "Ночная",
                class: "col-md-2"
            }, {
                id: "thirdDate",
                label: "Выходной 1",
                class: "col-md-2"
            }, {
                id: "fourthDate",
                label: "Выходной 2",
                class: "col-md-2"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
                name: "name",
                type: "text",
                placeholder: "Название"
            },
			{
				name: "code",
				type: "text",
				placeholder: "Код"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти график",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить график",
					removeConfirm: "Вы действительно хотите удалить график?"
				},
				check: {
					isRemoveEnable: function (row) {
						if (!row) {
							return false;
						}
						return !row.delete;
					}
				},
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.graficsGrid',
				load: $scope.gridScope.service.getPage
			});

            $scope.gridScope.addRow = GridService.getAddAction(
                "pbk/nsi/grafics/graficEditDlg.html", "GraficEditCtrl", "Создание графика", "lg", $scope.gridScope);
            $scope.gridScope.removeRow = GridService.getRemoveAction(
                "Ошибка при удалении графика", $scope.gridScope, $scope.gridScope.service.removeItem);
		}]);
