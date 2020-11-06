angular.module('armada')
	.controller('BaseHistCtrl', ['$scope', 'GridService', '$modal', 'Notification', 'UtilService',
		function ($scope, GridService, $modal, Notification, UtilService) {

			$scope.init = function (title, headIdColumn, service, itemId, gridName, dlgController, dlgTemplate, isNotReadOnlyUser) {

				$scope.gridScope = $scope;
				$scope.gridScope.service = service;

				/* Права */
				$scope.gridScope.perms = {
					restore: isNotReadOnlyUser && ($scope.$parent.item == null || $scope.$parent.item.isDelete == 0)
				};

				/* Заголовок */
				$scope.gridScope.titleParams = {
					labels: {
						title: title
					},
					buttons: [{
						name: "restore",
						tip: "Восстановить версию",
						action: function () {
							var id = $scope.gridScope.getSelectedItemId();
							if (id == null || id.length == 0) {
								Notification.info("Необходимо выбрать версию объекта");
								return;
							}
							if (id.length > 1) {
								Notification.info("Необходимо выбрать одну конкретную версию объекта");
								return;
							}
							$scope.gridScope.service.restoreItem($scope.gridScope.getSelectedItemId()).then(function (result) {
								if ($scope.$parent.refreshBreadCrumbles) {
									$scope.$parent.refreshBreadCrumbles();
								}
								if ($scope.$parent.reloadState) {
									$scope.$parent.reloadState();
								} else {
									$scope.gridScope.grid.refreshAction();
								}
								Notification.info("Версия успешно восстановлена");
							}).catch(function (response) {
								var errMsg = "";
								if (response.status == 400) {
									var errors = UtilService.getFormErrors(response);
									errMsg = " Причина: " + errors.errMessages;
								}
								Notification.error("Ошибка при восстановлении версии" + errMsg);
							});
						},
						icon: "plus"
					}],
					gridScope: $scope.gridScope
				};

				/* Колонки грида */
				$scope.gridScope.headers = [{
					id: "versionStartDate",
					label: "Период актуальности",
					class: "col-md-4",
					get: function (row) {
						return row.versionStartDate + ' - ' + row.versionEndDate
					}
				}, {
					id: "updateDate",
					label: "Дата обновления",
					class: "col-md-4",
					get: function (row) {
						return row.updateUser + ' (' + row.updateDate + ')'
					}
				}];

				/* Фильтры */
				$scope.gridScope.filters = [{
					name: headIdColumn,
					type: "text",
					defval: itemId,
					hide: true
				}];

				/* Параметры фильтрации */
				$scope.gridScope.filterParams = {
					filterString: "",
					gridScope: $scope.gridScope
				};

				/* Грид */
				$scope.gridScope.grid = GridService.initGridData({
					tableScope: $scope.gridScope,
					defaultPredicate: "versionStartDate",
					gridName: gridName,
					load: $scope.gridScope.service.getHistoryPage
				});

				$scope.gridScope.buttons = {
					extraButtons: [{
						tip: "Подробности",
						action: function (row) {
							$modal.open({
								templateUrl: dlgTemplate,
								windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
								controller: dlgController,
								size: "md",
								resolve: {
									data: function () {
										return {
											title: "Просмотр истории",
											url: row.url,
											item: row,
											rootItemId: itemId
										}
									}
								}
							});
						},
						class: "glyphicon glyphicon-list-alt",
						show: function (row) {
							return true;
						}
					}],
					gridScope: $scope.gridScope
				};
				$scope.gridScope.addSelItemColumn = isNotReadOnlyUser && ($scope.$parent.item == null || $scope.$parent.item.isDelete == 0);
				$scope.gridScope.historyGrid = true;
				$scope.gridScope.resetIndex = true;

				$scope.gridScope.getRowTextClassFunc = function (row) {
					if (!row) {
						return null;
					}
					return !row.active ? 'history-record' : null;
				};
			}
		}
	]);