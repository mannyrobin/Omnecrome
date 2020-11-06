angular.module('armada')
	.controller('EquipmentsCtrl', ['$scope', 'EquipmentsService', 'GridService', 'EquipmentsReportService', '$state', 'isNotReadOnlyUser',
		function ($scope, EquipmentsService, GridService, EquipmentsReportService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = EquipmentsService;
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
					title: "Бортовое оборудование",
					addTip: "Добавить бортовое оборудование"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [/*{  похоже эти поля не будут участвовать в работе. из внешней системы не приходят((
			 id: "brand",
			 label: "Марка",
			 class: "col-md-1"
			 }, {
			 id: "model",
			 label: "Модель",
			 class: "col-md-1",
			 href: function(row) { return  "nsi/equipments/" + row.id + "/info"; }
			 }, {
			 id: "firmwareVersion",
			 label: "Версия прошивки",
			 class: "col-md-2"
			 }, {
			 id: "cellNumber",
			 label: "Номер абонента",
			 class: "col-md-2" },*/
				{
					id: "tsView",
					label: "ТС",
					href: function (row) {
						return "nsi/equipments/" + row.id + "/info";
					},
					class: "col-md-4"
				}, {
					id: "asduEquipmentId",
					label: "ID в ВИС АСДУ",
					class: "col-md-4"
				}, {
					id: "asduVenicleId",
					label: "ID ТС в ВИС АСДУ",
					class: "col-md-3"
				}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "tsView",
				type: "text",
				placeholder: "ТС"
			}/*, {
			 name: "model",
			 type: "text",
			 placeholder: "Модель"
			 }*/];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти бортовое оборудование",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить бортовое оборудование",
					removeConfirm: "Вы действительно хотите удалить бортовое оборудование?"
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
				defaultPredicate: "tsView",
				gridName: $state.current.name + '.equipmentsGrid',
				load: $scope.gridScope.service.getPage,
				export: EquipmentsReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/equipment/EquipmentsEditDlg.html", "EquipmentsEditCtrl", "Создание бортового оборудования", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении бортового оборудования", $scope.gridScope, $scope.gridScope.service.removeItem, true);

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
