angular.module('armada')
	.controller('UserPassChangeCtrl', ['$controller', '$scope', '$stateParams', '$modalInstance', 'data', 'UserService', 'UsersService', 'UtilService',
		function ($controller, $scope, $stateParams, $modalInstance, data, UserService, UsersService, UtilService) {

			$scope.isSelfPassChange = data.isSelfPassChange;

			$scope.title = data.title;

			/* Поля ввода */
			$scope.fields = [
				{
					name: "currentPass",
					type: "text",
					value: "",
					inputType: "password",
					label: "Текущий пароль",
					required: true
				},
				{
					name: "password",
					type: "text",
					value: "",
					inputType: "password",
					label: "Новый пароль",
					required: true
				},
				{
					name: "passwordRepeat",
					type: "text",
					value: "",
					inputType: "password",
					label: "Подтверждение",
					required: true
				}
			];

			UsersService.getItem(data.id).then(function (result) {
				$scope.item = result;
			});

			$scope.close = function () {
				$modalInstance.dismiss('cancel');
			};

			function changePass() {
				$scope.item['password'] = $scope.fields[1].value;
				$scope.item['passwordRepeat'] = $scope.fields[2].value;
				UsersService.changePass($scope.item).then(
					function () {
						if ($modalInstance) {
							$modalInstance.close();
						}
					}
				).catch(function (response) {
					var errors = UtilService.getFormErrors(response);
					$scope.errTitle = errors.errTitle;
					$scope.errMessages = errors.errMessages;
				});
			};

			$scope.save = function () {
				if ($scope.fields[1].value != $scope.fields[2].value) {
					var errors = [];
					errors.push("Пароль и подтверждение не совпали");
					$scope.errTitle = "Ошибка валидации";
					$scope.errMessages = errors;
					return;
				}
				if ($scope.isSelfPassChange == true) {
					var userForm = {username: $scope.userInfo.name, password: $scope.fields[0].value};
					var promises = UserService.checkpass(userForm);
					promises.then(function (authenticationResult) {
						if (authenticationResult == true) {
							changePass();
						} else {
							var errors = [];
							errors.push("Текущий пароль неверен!");
							$scope.errTitle = "Ошибка валидации";
							$scope.errMessages = errors;
						}
					});
				}
				else {
					changePass();
				}

			};

			/* Инициализация */
			//$controller('BaseEditCtrl', {$scope: $scope});
			//$scope.init(data, $scope.fields, {addItem: UsersService.addItem}, $modalInstance, null);
		}]);