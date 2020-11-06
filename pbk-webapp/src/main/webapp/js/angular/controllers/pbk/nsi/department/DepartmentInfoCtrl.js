angular.module('armada')
	.controller('DepartmentInfoCtrl', ['$controller', '$scope', '$modal', '$stateParams', 'DepartmentsService', 'EmployeesService', 'AppConfig', 'isNotReadOnlyUser', 'CountiesService', 'Notification', 'UtilService',
		function ($controller, $scope, $modal, $stateParams, DepartmentsService, EmployeesService, AppConfig, isNotReadOnlyUser, CountiesService, Notification, UtilService) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
						
			/*$scope.init(data.item);*/
			$scope.fields = [{
				name: "easuFhdId",
				type: "text",
				label: "ID в ЕАСУ ФХД",
				readonly: true,
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
			}, {
				name: "name",
				type: "text",
				label: "Название",
				readonly: true,
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
			}, {/*Вставленная часть*/
				name: "planCount",
				type: "text",
				label: "Плановое количество КПТ",
				/*value: data.item.planCount*/
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE
			},{
				name: "fullName",
				type: "text",
				label: "Полное наименование",
				readonly: true,
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.X_LONG_INPUT_SIZE
			}, {
				name: "parentDeptId",
				type: "select",
				load: function () {
					return DepartmentsService.getList([
						{
							name: "deprecatedId",
							value: $stateParams.itemId,
							type: "id"
						},
						{
							name: "currentId",
							value: $scope.fields[3].value,
							type: "select"
						}
					]);
				},
				label: "Родительское подразделение",
				readonly: true,
				required: false
			}, {
				name: "description",
				type: "textarea",
				label: "Описание",
				readonly: true,
				required: false,
				maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE
			}, {
				name: "forPlanUse",
				type: "yesno",
				label: "Участвует в планировании",
				readonly: !isNotReadOnlyUser
			}, {
				name: "employeeSignId",
				type: "select",
				load: function () {

					return EmployeesService.getEmployeesForSign([{
						/*name: "departmentId",
                        value: $stateParams.itemId,
                        type: "id"
                    }, {*/
						name: "currentId",
						value: $scope.fields[6].value,
						type: "select"
					}, {
						name: "forEmployeeSignId",
						value: "1",
						type: "text"
					}
					]);
				},
				label: "Подписант",
				readonly: false
			}, {
				name: "countyIds",
				/*value: $scope.fields[7].value,*/
				type: "multiselect",
				load: function () {
					return CountiesService.getList([{
						name: "deptId",
						value: $stateParams.itemId,
						type: "id"
					}]);
				},
				label: "Округа",
				readonly: !isNotReadOnlyUser,
				required: false
			}];

			$scope.save = function () {

				if ($scope.id != null && $scope.item == null) {
					return;
				}
				$scope.fillItem();
				if ($scope.actions.editItem) {
					$scope.actions.editItem($scope.item).then(
						function () {

							if ($scope.reloadState) {
								$scope.reloadState();
							}

							$scope.errTitle = "";
							$scope.errMessages = "";

							Notification.info("Запись успешно обновлена.");
							if ($scope.modalInstance) {
								$scope.modalInstance.close();
							}
						}
					).catch(function (response) {
						var errors = UtilService.getFormErrors(response);
						$scope.errTitle = errors.errTitle;
						$scope.errMessages = errors.errMessages;
					});
				} else {
					$scope.errTitle = "Действие не определено";
					var errors = [];
					errors.push("Действие не определено");
					$scope.errMessages = errors;
				}
			};

			$scope.saveVersion = function () {
				if ($scope.actions.changeVersion) {
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
						$scope.fillItem();
						$scope.item['versionStartDate'] = result.selectedDate;
						$scope.actions.changeVersion($scope.item).then(
							function (response) {
								if ($scope.reloadState) {
									$scope.reloadState();
								}

								$scope.errTitle = "";
								$scope.errMessages = "";

								Notification.info("Запись успешно обновлена.");
								if ($scope.modalInstance) {
									$scope.modalInstance.close();
								}
							}
						).catch(function (response) {
							var errors = UtilService.getFormErrors(response);
							$scope.errTitle = errors.errTitle;
							$scope.errMessages = errors.errMessages;
						});
					});

				} else {
					$scope.errTitle = "Действие не определено";
					var errors = [];
					errors.push("Действие не определено");
					$scope.errMessages = errors;
				}
			};

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, DepartmentsService);
		}]);