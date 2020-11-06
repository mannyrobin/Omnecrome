'use strict';

/**
 * @ngdoc service
 * @name armada.UrlService
 * @description
 *
 * Сервис урлов.
 */
angular.module('armada')
	.service('UtilService', ['Restangular', 'UrlService', '$state', function (Restangular, UrlService, $state) {
		Array.prototype.insert = function (index) {
			this.splice.apply(this, [index, 0].concat(
				Array.prototype.slice.call(arguments, 1)));
			return this;
		};
		Array.prototype.removeItem = function (val) {
			var i = this.indexOf(val);
			return i > -1 ? this.splice(i, 1) : [];
		};

		Number.prototype.toMoney = function () {
			var self = this;
			var rounded = Math.round(self * 100) / 100;
			var str = String(rounded);
			var arr = str.split('.');
			str = arr[0];
			var res = str.replace(/./g, function (c, i, a) {
				return i && c !== "." && ((a.length - i) % 3 === 0) ? ' ' + c : c;
			});
			if (arr.length > 1) {
				var period = arr[1];
				if (period.length > 2)
					period = period.substr(0, 2);
				res += '.' + period;
			}
			return res;
		};

		Number.prototype.toDistance = function () {
			var self = this;
			var rounded = Math.round(self * 1000) / 1000;
			var str = String(rounded);
			var arr = str.split('.');
			if (arr.length == 1)
				str = arr[0] + ".000";
			else {
				str = arr[0] + "." + arr[1];
				var diff = 3 - arr[1].length;
				var suff = "";
				for (var i = 0; i < diff; i++)
					suff += "0";
				str += suff;
			}
			return str;
		};
		return {
			getErrorMessages: function (validationResult) {
				var msg = "";
				angular.forEach(validationResult, function (value, key) {
					for (var i = 0; i < value.length; i++) {
						msg += value[i] + "\n";
					}
				});
				return msg;
			},
			getVersionInfo: function () {
				return Restangular.one("info/version").get();
			},
			/**
			 * Возвращает ошибки формы и сообщение по респонсу.
			 *
			 * @param response - результат запроса, пойманный в catch
			 * @returns {{errTitle: string, errMessages: Array}} - заголовок сообщения и массив с ошибками
			 */
			getFormErrors: function (response) {
				var title = "Ошибка выполнения запроса.";
				var errors = [];
				if (response != null && response.data != null && response.data.notValid != null) {
					angular.forEach(response.data.notValid, function (field) {
						if (Array.isArray(field)) {
							angular.forEach(field, function (error) {
								errors.push(error);
							});
						}
					});
					if (errors.length == 1)
						title = "В отправленной форме присутствует ошибка:";
					else if (errors.length > 1)
						title = "В отправленной форме присутствуют следующие ошибки:";
				}
				return {
					errTitle: title,
					errMessages: errors
				};
			},
			isShowRouteTabs: function (tabData, extraRoutes) {
				var i;
				for (i = 0; i < tabData.length; i++) {
					if ($state.current.name === tabData[i].route)
						return true;
				}
				for (i = 0; i < extraRoutes.length; i++) {
					if ($state.current.name === extraRoutes[i])
						return true;
				}
				return false;
			},
			getAllResponseErrors: function (data) {
				var errors = [];
				angular.forEach(data.notValid, function (field) {
					if (Array.isArray(field)) {
						angular.forEach(field, function (error) {
							errors.push(error);
						});
					}
				});
				return errors;
			},
			validateInt: function (str) {
				return /^\d{0,9}$/.test(str);
			},
			getIntFromStr: function (str) {
				if (str && str != "" && /^\d{0,9}$/.test(str[0])) {
					var number = str[0];
					for (var i = 1; i < str.length && /^\d{0,9}$/.test(str[i]); i++) {
						number += str[i];
					}
					return parseInt(number);
				} else {
					return 0;
				}
			},
			getPageNumber: function (start, number) {
				if (!start || !number) {
					return null;
				}
				return Math.round(start / number) + 1;
			},
			checkWebGLSupport: function () {
				try {
					var canvas = document.createElement("canvas");
					return canvas.getContext("webgl")
						|| canvas.getContext("experimental-webgl");
				} catch (e) {
					return false;
				}
			}
		};
	}]);