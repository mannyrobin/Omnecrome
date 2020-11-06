angular.module('armada')
	.controller('CountyDistrictsCtrl', ['$scope', '$stateParams', 'DistrictsService', 'DistrictsReportService', 'GridService', '$state', 'isNotReadOnlyUser', 'CountiesService',
		function ($scope, $stateParams, DistrictsService, DistrictsReportService, GridService, $state, isNotReadOnlyUser, CountiesService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = DistrictsService;

			/* Права */
			$scope.gridScope.perms = {
				//add: isNotReadOnlyUser,
				//edit: isNotReadOnlyUser,
				//remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Районы Округа",
					addTip: "Добавить район",
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "cod",
				label: "Код"
			}, {
				id: "name",
				label: "Название",
				class: "col-md-2"
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
			}, {
				name: "countyId",
				type: "text",
				defval: $stateParams.itemId,
				hide: true

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
				gridName: $state.current.name + '.countyDistrictsGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/districts/DistrictEditDlg.html", "CountyDistrictEditCtrl", "Создание района Округа", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении пользователя", $scope.gridScope, $scope.gridScope.service.removeItem, true);
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
