angular.module('armada')
	.controller('AskpPuskCheckBindsCtrl', ['$scope', 'GridService', 'AskpPuskChecksService', '$state', 'DepartmentsService',
		function ($scope, GridService, AskpPuskChecksService, $state, DepartmentsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = AskpPuskChecksService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "АСКП: проверки ПУсК"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "workDate",
				label: "Дата проверки"
			}, {
				id: "routeCode",
				label: "Код АСКП маршрута"
			}, {
				id: "moveCode",
				label: "Выход ТС"
			}, {
				id: "cardNumber",
				label: "Номер карты"
			}, {
				id: "ticketName",
				label: "Билет"
			}, {
				id: "checkStartTime",
				label: "Время начала проверки"
			}, {
				id: "checkEndTime",
				label: "Время конца проверки"
			}, {
				id: "checkResult1Count",
				label: "Кол-во с результатом \"1\"(нет проходов)"
			}, {
				id: "checkResult2Count",
				label: "Кол-во с результатом \"2\"(один проход)"
			}, {
				id: "checkResult3Count",
				label: "Кол-во с результатом \"3\"( >1 прохода)"
			}, {
				id: "employeeName",
				label: "Сотрудник"
			}, {
				id: "deptName",
				label: "Подразделение"
			}, {
				id: "isBind",
				icon: "link",
				iconTip: "Закреплен",
				label: "",
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: {
					fromName: "dateFrom",
					toName: "dateTo"
				},
				defval: {
					fromDate: new Date(),
					toDate: new Date()
				},
				type: "range",
				placeholder: {
					from: "Начало периода",
					to: "Окончание периода"
				}
			}, {
				name: "isBindOnly",
				type: "text",
				defval: "1",
				hide: true
			}, {
				name: "isBind",
				type: "yesno",
				placeholder: "Закреплен"
			}, {
				name: "name",
				type: "text",
				placeholder: "ФИО"
			}, {
				name: "departmentId",
				type: "select",
				placeholder: "Подразделение",
				load: function () {
					return DepartmentsService.getList([{
						name: "forPlanUse",
						value: "1",
						type: "text"
					}
					]);
				},
			}, {
				name: "cardNumber",
				type: "text",
				placeholder: "Номер карты"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти данные из АСКП",
				gridScope: $scope.gridScope
			};

			/* Кнопки */
			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "workDate",
				gridName: $state.current.name + '.askpPuskChecksByBindGrid',
				load: $scope.gridScope.service.getPage
			});

		}]);
