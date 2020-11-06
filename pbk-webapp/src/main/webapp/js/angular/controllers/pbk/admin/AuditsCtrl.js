'use strict';

/**
 * @ngdoc function
 * @name armada.controller:UsersCtrl
 * @description
 *
 * Контроллер контрактов
 */
angular.module('armada')
	.controller('AuditsCtrl', ['$scope', '$rootScope', 'AuditsService', '$modal', 'Notification', 'GridService', '$state',
		function ($scope, $rootScope, AuditsService, $modal, Notification, GridService, $state) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = AuditsService;

			/* Права */
			$scope.gridScope.perms = {
				add: false,
				edit: false,
				remove: false
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Журнал действий",
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [
				{
					id: "createDate",
					label: "Дата и время",
					visible: true,
					href: function (row) {
						return "admin/audit/" + row.id + "/info";
					},
					class: "col-md-2"
				}, {
					id: "createUserInfo",
					label: "Пользователь",
					visible: true,
					class: "col-md-3"
				}, {
					id: "auditTypeName",
					label: "Тип события",
					visible: true,
					class: "col-md-2"
				}, {
					id: "message",
					label: "Сообщение",
					visible: true,
					class: "col-md-5"
				}
			];

			/* Фильтры */
			$scope.gridScope.filters = [
				{
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
				},
				{
					name: "lvlId",
					type: "multiselect",
					placeholder: "Уровень",
					load: AuditsService.getLevels,
					defval: [{"id": 3, "name": "Информация"}]
				},
				{
					name: "typeId",
					type: "multiselect",
					placeholder: "Тип",
					load: AuditsService.getTypes
				},
				{
					name: "createUserInfo",
					type: "text",
					placeholder: "Пользователь",
				}
			];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти записи аудита",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "createDate",
				gridName: $state.current.name + '.auditsGrid',
				load: $scope.gridScope.service.getPage
			});

			$rootScope.getRowClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.auditLevelId != null ? "level-" + row.auditLevelId : null;//row.delete == true ? 'history-record' : null;
			};

			/* Экшены */
			//нету для аудита
		}]);