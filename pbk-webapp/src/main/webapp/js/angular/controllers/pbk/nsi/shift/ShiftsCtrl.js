angular.module('armada')
	.controller('ShiftsCtrl', ['$scope', 'ShiftsService', 'GridService', 'isNotReadOnlyUser', '$state',
		function ($scope, ShiftsService, GridService, isNotReadOnlyUser, $state) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = ShiftsService;

            $scope.isNotReadOnlyUser = isNotReadOnlyUser;

            $scope.gridScope.perms = {
                add: isNotReadOnlyUser
            };

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Смены"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-2",
				href: function (row) {
					return "nsi/shifts/" + row.id + "/info";
				}
			}, {
				id: "workStartTime",
				label: "Часы работы",
				class: "col-md-3",
				get: function (row) {
					return row.workStartTime + ' - ' + row.workEndTime;
				}
			}, {
				id: "workEndTime",
				label: "Часы перерыва",
				class: "col-md-3",
				get: function (row) {
					return row.breakStartTime + ' - ' + row.breakEndTime;
				}
			}, {
				id: "description",
				label: "Описание",
				class: "col-md-2"
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
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти смену",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить смену",
					removeConfirm: "Вы действительно хотите удалить смену?"
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
				gridName: $state.current.name + '.shiftsGrid',
				load: $scope.gridScope.service.getPage
			});

/*            $scope.gridScope.addRow = GridService.getAddAction(
                "pbk/nsi/shift/shiftEditDlg.html", "ShiftEditCtrl", "Создание смены", "lg", $scope.gridScope);*/

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
		}]);
