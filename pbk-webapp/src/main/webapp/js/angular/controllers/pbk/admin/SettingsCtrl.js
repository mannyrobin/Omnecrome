/**
 * Контроллер страницы настроек.
 */
angular.module('armada')
	.controller('SettingsCtrl', ['$scope', 'SettingsService', '$modal', 'Notification', 'GridService', '$state', '$stateParams', 'isNotReadOnlyUser',
		function ($scope, SettingsService, $modal, Notification, GridService, $state, $stateParams, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = SettingsService;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				edit: isNotReadOnlyUser,
				remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Настройки системы"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название"
			}, {
				id: "value",
				label: "Значение"
			}, {
				id: "description",
				label: "Описание",
				class: "col-md-3"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "settingsTab",
				type: "text",
				defval: $stateParams.itemId,
				hide: true
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					editTip: "Редактировать значение настройки"
				},
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.settingsGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.editRow = GridService.getEditAction(
				"pbk/admin/SettingsEditDlg.html", "SettingsEditCtrl", "Редактирование настройки", "sm", $scope.gridScope);
		}]);