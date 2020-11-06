'use strict';

angular.module('armada')
	.controller('CurrentUserInfoCtrl', ['$controller', '$scope', '$modal', '$stateParams', 'UsersService',
		function ($controller, $scope, $modal, $stateParams, UsersService) {
			var readonly = (angular.isDefined($scope.userInfo.isLdapUser) && $scope.userInfo.isLdapUser == true);
			$scope.readonly = readonly;
			$scope.fields = [{
				name: "login",
				type: "text",
				label: "Код",
				readonly: true,
				required: true
			}, {
				name: "name",
				type: "text",
				label: "Имя",
				readonly: readonly,
				required: true
			}, {
				name: "expirationDate",
				type: "date",
				readonly: true,
				label: "Дата прекращения доступа",
				required: false
			}, {
				name: "isLdap",
				type: "yesno",
				label: "Пользователь является внешним",
				readonly: true,
				//readonly: $stateParams.itemId == null
			}];

			$scope.changePass = function () {
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
								isSelfPassChange: true
							}
						}
					}
				});
			}

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, {
				editItem: UsersService.editItem,
				getItem: UsersService.getItem
			});

		}]);