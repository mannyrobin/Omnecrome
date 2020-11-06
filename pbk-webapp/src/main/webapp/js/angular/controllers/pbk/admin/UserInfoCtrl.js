'use strict';

angular.module('armada')
	.controller('UserInfoCtrl', ['$controller', '$scope', '$modal', 'userItem', '$stateParams', 'UsersService', 'isNotReadOnlyUser', 'Notification', 'AppConfig',
		function ($controller, $scope, $modal, userItem, $stateParams, UsersService, isNotReadOnlyUser, Notification, AppConfig) {

			$scope.readonly = userItem.isDelete == 1 || userItem.isLdap || !isNotReadOnlyUser;
			$scope.savedUserTypeIsLdap = userItem.isLdap;
			$scope.isLockUser = !userItem.isActive;
			$scope.isDeleteUser = userItem.isDelete == 1;
			$scope.isNotReadOnlyUser = isNotReadOnlyUser && userItem.isDelete == 0;
			$scope.fields = [{
				name: "login",
				type: "text",
				readonly: $scope.readonly,
				label: "Логин",
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
			}, {
				name: "name",
				type: "text",
				readonly: $scope.readonly,
				label: "Имя",
				required: true,
				maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE
			}, {
				name: "expirationDate",
				type: "date",
				readonly: $scope.readonly,
				label: "Дата прекращения доступа",
				required: false
			}, {
				name: "isLdap",
				type: "yesno",
				label: "Пользователь является внешним",
				readonly: !isNotReadOnlyUser || userItem.isDelete == 1
			}];

			if (isNotReadOnlyUser && userItem.isDelete == 0) {
				$scope.$watch(function () {
					return $scope.fields[3].value != undefined ? $scope.fields[3].value.id : undefined;
				}, function (newValue, oldValue) {
					if (newValue != oldValue) {
						$scope.readonly = newValue == 1;
						for (var i = 0; i < $scope.fields.length - 1; i++) {
							$scope.fields[i]['readonly'] = $scope.readonly;
						}
					}
				});
			}

			$scope.changePass = function () {
				if ($scope.item.isLdap) {
					Notification.error("Перед сменой пароля сохраните изменения статуса пользователя.");
					return;
				}
				var modalInstance = $modal.open({
					templateUrl: "templates/dialogs/pbk/admin/changePassDlg.html",
					windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
					controller: 'UserPassChangeCtrl',
					size: "sm",
					resolve: {
						data: function () {
							return {
								title: "Изменение пароля",
								id: $stateParams.itemId,
								isSelfPassChange: false
							}
						}
					}
				});
			}

			$scope.unlockUser = function () {
				if ($scope.item.isLdap) {
					Notification.error("Перед снятием блокировки сохраните изменения статуса пользователя.");
					return;
				}
				UsersService.getItem(data.id).then(function (result) {
					$scope.item = result;
					$scope.item['isActive'] = true;
					UsersService.unlock($scope.item).then(function () {
						$scope.isLockUser = false;
						Notification.info("Пользователь разблокирован.");
					});
				});
			}

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, {
				editItem: UsersService.editItem,
				getItem: UsersService.getItem
			});

		}]);