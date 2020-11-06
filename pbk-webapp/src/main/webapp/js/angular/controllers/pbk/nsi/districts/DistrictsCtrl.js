angular.module('armada')
	.controller('DistrictsCtrl', ['$scope', 'DistrictsService', 'DistrictsReportService', 'GridService', '$state', 'isNotReadOnlyUser', 'CountiesService',
		function ($scope, DistrictsService, DistrictsReportService, GridService, $state, isNotReadOnlyUser, CountiesService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = DistrictsService;
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Права */
			$scope.gridScope.perms = {
				//add: isNotReadOnlyUser,
				edit: isNotReadOnlyUser,
				//remove: isNotReadOnlyUser,
				export: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Районы Москвы",
					addTip: "Добавить район",
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-2",
				href: function (row) {
					return "nsi/districts/" + row.id + "/info";
				}
			}, {
				id: "countyName",
				label: "Округ",
				class: "col-md-2"
			}, {
				id: "countRoute",
				label: "Кол-во маршрутов",
				class: "col-md-3"
			}, {
				id: "countVenues",
				label: "Кол-во мест встреч",
				class: "col-md-3"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "name",
				type: "text",
				placeholder: "Название"
			},
				{
					name: "countyId",
					type: "select",
					placeholder: "Округ",
					load: CountiesService.getList

				}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти районы",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить район",
					removeConfirm: "Вы действительно хотите удалить район?"
				},
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.districtsGrid',
				load: $scope.gridScope.service.getPage,
				export: DistrictsReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/districts/DistrictEditDlg.html", "DistrictEditCtrl", "Создание района", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении пользователя", $scope.gridScope, $scope.gridScope.service.removeItem, true);

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
