angular.module('armada')
	.controller('DutiesCtrl', ['$scope', 'DutiesService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, DutiesService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = DutiesService;
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Права */
			$scope.gridScope.perms = {
				//add: isNotReadOnlyUser,
				//remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Наряды",
					addTip: "Добавить наряд"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "workDate",
				label: "Транспортные сутки",
				href: function (row) {
					return "nsi/duties/" + row.id + "/info";
				}
			}, {
				id: "moveCode",
				label: "Маршрут",
				class: "col-md-2"
			}, {
				id: "easuFhdRouteCode",
				label: "Код выхода на маршрут",
				class: "col-md-2"
			}, {
				id: "tsDepoNumber",
				label: "ТС",
				class: "col-md-2"
			}, {
				id: "driverPersonalNumber",
				label: "Водитель",
				class: "col-md-2"
			}, {
				id: "workTime",
				label: "Время работы",
				class: "col-md-3",
				get: function (row) {
					return row.moveStartTime + ' - ' + row.moveEndTime;
				}
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: {
					fromName: "dateFrom",
					toName: "dateTo"
				},
				type: "range",
				defval: {fromDate: new Date(), toDate: new Date()},
				placeholder: {
					from: "Начало периода",
					to: "Окончание периода"
				}
			}, {
				name: "moveCode",
				type: "text",
				placeholder: "Маршрут"
			}, {
				name: "easuFhdRouteCode",
				type: "text",
				placeholder: "Код выхода"
			}, {
				name: "tsDepoNumber",
				type: "text",
				placeholder: "ТС"
			}, {
				name: "driverPersonalNumber",
				type: "text",
				placeholder: "Водитель"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти наряд",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "moveCode",
				gridName: $state.current.name + '.dutiesGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/duties/dutyEditDlg.html", "DutyEditCtrl", "Создание наряда", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении наряда", $scope.gridScope, $scope.gridScope.service.removeItem);
		}]);
