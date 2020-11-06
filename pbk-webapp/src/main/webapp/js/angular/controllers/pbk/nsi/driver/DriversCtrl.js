angular.module('armada')
	.controller('DriversCtrl', ['$scope', 'DriversService', 'DriversReportService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, DriversService, DriversReportService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = DriversService;
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Права */
			$scope.gridScope.perms = {
				//add: isNotReadOnlyUser,
				export: isNotReadOnlyUser,
				//remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Водители",
					addTip: "Добавить водителя"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [
				{
					id: "surname",
					label: "ФИО",
					class: "col-md-6",
					href: function (row) {
						return "nsi/drivers/" + row.id + "/info";
					},
					get: function (row) {
						var result = "";
						if (row.surname) {
							result += row.surname;
						}
						if (row.name) {
							result = result.trim() + ' ' + row.name;
						}
						if (row.patronumic) {
							result = result.trim() + ' ' + row.patronumic;
						}
						return result.trim();
					}
				}, {
					id: "personalNumber",
					label: "Табельный номер водителя",
					class: "col-md-2"
				}, {
					id: "parkName",
					label: "Эксплуатационный филиал",
					class: "col-md-2"
				}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "fio",
				type: "text",
				placeholder: "ФИО"
			}, {
				name: "personalNumber",
				type: "text",
				placeholder: "Табельный номер водителя"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти водителя",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить водителя",
					removeConfirm: "Вы действительно хотите удалить водителя?"
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
				defaultPredicate: "surname",
				gridName: $state.current.name + '.driversGrid',
				load: $scope.gridScope.service.getPage,
				export: DriversReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/driver/DriversEditDlg.html", "DriversEditCtrl", "Создание водителя", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении водителя", $scope.gridScope, $scope.gridScope.service.removeItem, true);

			$scope.gridScope.addSelItemColumn = true;

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
