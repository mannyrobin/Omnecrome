'use strict';

/**
 * @ngdoc service
 * @name armada.UserService
 * @description
 *
 * Сервис логина.
 */
angular.module('armada')
	.service('UserService', ['Restangular', 'UrlService', '$window', '$http', function (Restangular, UrlService, $window, $http) {

		var serviceurl = /*UrlService.getApiPrefix() +*/ 'auth';

		return {
			authenticate: function (userForm) {
				return Restangular.all(serviceurl).post(userForm);
			},
			getUserInfo: function () {
				return Restangular.one(serviceurl).get();
			},
			user: null,
			getCurrUsrName: function () {
				return this.user.name;
			},
            getCurrUsrLogin: function () {
                return this.user.login;
            },
			hasUser: function () {
				return this.user != null;
			},

			setUser: function (user) {
				this.user = user;
			},
			logout: function () {
				this.user = null;
				return Restangular.all(serviceurl + "/logout").post();
			},
			hasRole: function (role) {
				if (this.user == null || !angular.isDefined(this.user)) {
					return false;
				}

				if (this.user.rolesPermissions[role] === undefined) {
					return false;
				}
				return this.user.rolesPermissions[role];
			},
			hasAnyRole: function (roles) {
				var result = false;
				for (var i = 0; i < roles.length; i++) {
					if (this.hasRole(roles[i])) {
						return true;
					}
				}
				return result;
			},

			checkpass: function (userForm) {
				return Restangular.all(serviceurl + '/checkpass').post(userForm);
			},

			getReportsAuth: function () {
				if (this.user == null || !angular.isDefined(this.user) || this.user.reportsAuth == null || !angular.isDefined(this.user.reportsAuth)) {
					return "";
				}
				return this.user.reportsAuth;
			},

			clearReportsAuth: function () {
				$http({method: 'GET', url: '/reports/exit/'});
			},

			checkDBConnection: function () {
				return Restangular.one(serviceurl + '/check-connection').get();
			}
			/**
			 * Редирект на страницу логаута пользователя.
			 */
			/*logout: function() {
			 $window.location.href = UrlService.getPrefix() + "auth/logout";
			 },*/
			/**
			 * Возвращает имя пользователя. ВНИМАНИЕ! Данные не хранятся, каждый раз направляется запрос серверу.
			 * @returns {*}
			 */
			/*getUserName: function() {
			 return Restangular.one(UrlService.getApiPrefix() + "info/user").get();
			 }*/
		};
	}]);