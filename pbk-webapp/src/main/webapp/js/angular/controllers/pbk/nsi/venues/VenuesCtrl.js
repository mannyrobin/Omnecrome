angular.module('armada')
	.controller('VenuesCtrl', ['$scope', 'VenuesService', 'GridService', '$state', 'CountiesService', 'DistrictsService', 'DepartmentsService', 'isNotReadOnlyUser', '$modal', 'Notification',
		function ($scope, VenuesService, GridService, $state, CountiesService, DistrictsService, DepartmentsService, isNotReadOnlyUser, $modal, Notification) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = VenuesService;
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Места встреч",
					addTip: "Добавить место встречи"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				href: function (row) {
					return "nsi/venues/" + row.id + "/info";
				}
				/*}, {
				 id: "district",
				 label: "Район"
				 }, {
				 id: "county",
				 label: "Округ"*/
			}, {
				id: "department",
				label: "Подразделение"
			}, {
				id: "description",
				label: "Описание"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "name",
				type: "text",
				placeholder: "Название"
				/*}, {
				 name: "countyId",
				 type: "select",
				 load: CountiesService.getList,
				 placeholder: "Округ",
				 onChange: function(){
				 $scope.filters[3].refresh();
				 }
				 }, {
				 name: "districtId",
				 type: "select",
				 load: function() {
				 return DistrictsService.getList([{
				 name: "countyId",
				 value: $scope.filters[2].value == null ? null : $scope.filters[2].value.id,
				 type: "id"}
				 ]);
				 },
				 placeholder: "Район"*/
			}, {
				name: "deptId",
				type: "select",
				load: function () {
					return DepartmentsService.getList();
				},
				placeholder: "Подразделение"
			}];

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить место встречи",
					removeConfirmFunc: function (row) {
						if (row.inPlan) {
							return "Место встречи будет помечено как удаленное. Планирование по данному месту встречи осуществляться не будет. Вы действительно хотите удалить место встречи?";
						} else {
							return "Вы действительно хотите удалить место встречи?";
						}
					}
				},
				extraButtons: [{
					tip: "Восстановить место встречи",
					show: function (row) {
						return row['delete'] != undefined && row.delete;
					},
					action: function (row) {
						var modalInstance = $modal.open({
							templateUrl: "templates/dialogs/editDateDlg.html",
							windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
							controller: "EditDateCtrl",
							size: "sm",
							resolve: {
								data: function () {
									return {
										title: "Дата вступления в силу изменений",
										id: "Дата вступления в силу изменений",
										date: new Date()
									}
								}
							}
						});
						modalInstance.result.then(function (result) {
							$scope.gridScope.service.restore({headId: row.id, versionStartDate: result.selectedDate}
							).then(function (response) {
								Notification.info("Место встречи восстановлено");
								$scope.gridScope.grid.refreshAction();
							}).catch(function (response) {
								Notification.error("Место встречи восстановить не удалось");
							});
						});
					},
					class: "glyphicon glyphicon-repeat"
				}],
				check: {
					isRemoveEnable: function (row) {
						return row['delete'] != undefined && !row.delete;
					}
				},
				gridScope: $scope.gridScope
			};

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти место встречи",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.venuesGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/venues/venueEditDlg.html", "VenueEditCtrl", "Создание места встречи", "lg", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении места встречи", $scope.gridScope, $scope.gridScope.service.removeItem, true);
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
