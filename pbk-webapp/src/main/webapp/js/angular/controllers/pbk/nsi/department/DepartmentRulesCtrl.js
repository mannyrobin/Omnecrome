'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DepartmentRulesCtrl
 * @description
 *
 */
angular.module('armada')
	.controller('DepartmentRulesCtrl', ['$scope', '$stateParams', 'BsoNumberRulesService', 'BsoTypesService', 'DepartmentsService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, $stateParams, BsoNumberRulesService, BsoTypesService, DepartmentsService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = BsoNumberRulesService;

			$scope.depId = $stateParams.itemId;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				edit: isNotReadOnlyUser,
				remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Журнал правил формирования номеров БСО для подразделения",
					addTip: "Добавить правило формирования номера БСО"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "bsoTypeName",
				label: "Тип БСО",
				class: "col-md-2"
			}, {
				id: "increment",
				label: "Текущее значение",
				class: "col-md-1"
			}, {
				id: "branchCode",
				label: "Код филиала",
				class: "col-md-1"
			}, {
				id: "deptCode",
				label: "Код подразделения",
				class: "col-md-1"
			}, {
				id: "departmentName",
				label: "Подразделение"

			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "bsoTypeId",
				type: "select",
				placeholder: "Тип БСО",
				load: BsoTypesService.getList,
				class: "col-md-3"
			}, {
				name: "departmentId",
				type: "select",
				placeholder: "Подразделение",
				defval: {id: $scope.depId},
				load: function () {
					return DepartmentsService.getDepartsForBso([{
						name: "currentId",
						value: $scope.filters[1].value.id,
						type: "id"
					}]);
				},
				hide: true
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти правила формирования номеров БСО",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					editTip: "Редактировать правило формирования номера БСО",
					removeTip: "Удалить правило формирования номера БСО",
					removeConfirm: "Вы действительно хотите удалить правило формирования номера БСО?"
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
				defaultPredicate: "bsoTypeName",
				gridName: $state.current.name + '.depBsoNumberRulesGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/bso-number-rules/BsoNumberRulesEditDlg.html", "DepartmentRuleEditCtrl", "Создание правила формирования номера БСО для Подразделения", "md", $scope.gridScope);
			$scope.gridScope.editRow = GridService.getEditAction(
				"pbk/nsi/bso-number-rules/BsoNumberRulesEditDlg.html", "DepartmentRuleInfoCtrl", "Редактирование правила формирования номера БСО для Подразделения", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении правила формирования номера БСО", $scope.gridScope, $scope.gridScope.service.removeItem)
		}]);