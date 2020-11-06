angular.module('armada')
	.controller('ShiftReservesCtrl', ['$scope', 'ShiftDepartmentsService', 'GridService', '$state', '$stateParams', 'isNotReadOnlyUser',
		function ($scope, ShiftDepartmentsService, GridService, $state, $stateParams, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = ShiftDepartmentsService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Резерв"
				},
				gridScope: $scope.gridScope
			};

			/* Права */
			$scope.gridScope.perms = {
				edit: isNotReadOnlyUser
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "departmentName",
				label: "Подразделение",
				class: "col-md-2"
			}, {
				id: "day",
				label: "Тип дня",
				class: "col-md-3"
			}, {
				id: "reserveCount",
				label: "Резерв",
				class: "col-md-3"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "shiftId",
				type: "id",
				defval: $stateParams.itemId,
				hide: true
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти резерв",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					editTip: "Редактировать значение резерва"
				},
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "departmentName",
				gridName: $state.current.name + '.shiftsDepartmentGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.editRow = GridService.getEditAction(
				"pbk/nsi/shift/shiftReserveEdit.html", "ShiftReserveEditCtrl", "Редактирование резерва", "sm", $scope.gridScope);

		}]);
