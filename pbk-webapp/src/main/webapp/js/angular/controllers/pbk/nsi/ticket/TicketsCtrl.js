angular.module('armada')
	.controller('TicketsCtrl', ['$scope', 'TicketsService', 'TicketsReportService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, TicketsService, TicketsReportService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = TicketsService;
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
					title: "Билеты",
					addTip: "Добавить билет"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "code",
				label: "Код",
				class: "col-md-1"
			}, {
				id: "name",
				label: "Название",
				class: "col-md-3",
				href: function (row) {
					return "nsi/tickets/" + row.id + "/info";
				}
			}, {
				id: "description",
				label: "Описание",
				class: "col-md-4"
			}, {
				id: "ticketTypeName",
				label: "Тип билета",
				disableSort: true,
				class: "col-md-2"
			}, {
				id: "useInAnalysisText",
				label: "Входит в анализ ПП",
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
				filterString: "Найти билет",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					editTip: "Редактировать билет",
					removeTip: "Удалить билет",
					removeConfirm: "Вы действительно хотите удалить билет?"
				},
				//check :{
				//    isRemoveEnable : function(row){
				//        return row['emplTypeCode'] && row.emplTypeCode == 'PBK';
				//    }
				//},
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "code",
				gridName: $state.current.name + '.ticketsGrid',
				load: $scope.gridScope.service.getPage,
				export: TicketsReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/ticket/TicketsEditDlg.html", "TicketsEditCtrl", "Создание билета", "md", $scope.gridScope);
			$scope.gridScope.editRow = GridService.getEditAction(
				"pbk/nsi/ticket/TicketsEditDlg.html", "TicketsEditCtrl", "Редактирование билета", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении билета", $scope.gridScope, $scope.gridScope.service.removeItem);
			$scope.gridScope.addSelItemColumn = true;
		}]);
