angular.module('armada')
	.controller('LoginCtrl', ['$scope', '$rootScope', '$timeout', '$cookies', 'UserService', 'UtilService', '$state', 'AppConfig', function ($scope, $rootScope, $timeout, $cookies, UserService, UtilService, $state, AppConfig) {

		$scope.loginMaxLength = AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE;
		$scope.passMaxLength = AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE;
		$scope.rememberMe = false;
		$scope.errTitle = false;
		$scope.login = function () {
			var userForm = {username: $scope.username, password: $scope.password}
			var promises = UserService.authenticate(userForm);
			promises.then(function (authenticationResult) {
				var authToken = authenticationResult.token;
				$rootScope.authToken = authToken;
				$cookies.put('authToken', authToken);
				UserService.getUserInfo().then(function (user) {
					$rootScope.userInfo = user;
					UserService.setUser(user);
					$timeout(function () {
						$state.go("app.main");
					}, 0);
				});
			}).catch(function (response) {
				if (response.status === 400) {
					var errors = UtilService.getFormErrors(response);
					$scope.errMessages = errors.errMessages;
					$scope.errTitle = true;
					return;
				}
				if (response.data.exceptionInfo != null && !angular.isUndefined(response.data.exceptionInfo)) {
					$scope.errMessages = [response.data.exceptionInfo.message];
					$scope.errTitle = true;
				} else {
					$scope.errMessages = ["Ошибка при входе в систему"];
					$scope.errTitle = true;
				}
			});
		};
	}]);