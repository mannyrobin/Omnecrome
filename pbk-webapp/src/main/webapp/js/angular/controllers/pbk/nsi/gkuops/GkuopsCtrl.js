angular.module('armada')
	.controller('GkuopsCtrl', ['$scope', 'GkuopsService', 'GkuopsReportService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, GkuopsService, GkuopsReportService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = GkuopsService;
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Права */
			$scope.gridScope.perms = {
				//add: isNotReadOnlyUser,
				edit: isNotReadOnlyUser,
				export: isNotReadOnlyUser,
				//remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "ГКУ ОП",
					addTip: "Добавить ГКУ ОП"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "surname",
				label: "ФИО",
				class: "col-md-4",
				get: function (row) {
					var result = '';
					if (row.surname) {
						result = row.surname;
					}
					if (row.name) {
						result = result + ' ' + row.name;
					}
					if (row.patronumic) {
						result = result + ' ' + row.patronumic;
					}
					return result;
				},
				href: function (row) {
					return "nsi/gkuops/" + row.id + "/info";
				}
			}, {
				id: "gkuopDeptName",
				label: "Подразделение",
				class: "col-md-2"
			}, {
				id: "personalNumber",
				label: "Табельный номер",
				class: "col-md-2"
			}, {
				id: "visGkuopId",
				label: "ID в ВИС ГКУ ОП",
				class: "col-md-2"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "name",
				type: "text",
				placeholder: "Имя"
			}, {
				name: "gkuopDeptName",
				type: "text",
				placeholder: "Подразделение"
			}, {
				name: "personalNumber",
				type: "text",
				placeholder: "Табельный номер"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти ГКУ ОП",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					editTip: "Редактировать ГКУ ОП",
					removeTip: "Удалить ГКУ ОП",
					removeConfirm: "Вы действительно хотите удалить ГКУ ОП?"
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
				defaultPredicate: "surname",
				gridName: $state.current.name + '.gkuopsGrid',
				load: $scope.gridScope.service.getPage,
				export: GkuopsReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/gkuops/GkuopEditDlg.html", "GkuopEditCtrl", "Создание ГКУ ОП", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении ГКУ ОП", $scope.gridScope, $scope.gridScope.service.removeItem, true);

			$scope.gridScope.addSelItemColumn = true;

			$scope.gridScope.getRowClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.delete ? 'remove-record' : null;
			};

			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				if (row.delete) {
					return 'remove-text-record';
				}
				return row.fireDate ? 'fire-user-record' : null;
			};
		}]);
