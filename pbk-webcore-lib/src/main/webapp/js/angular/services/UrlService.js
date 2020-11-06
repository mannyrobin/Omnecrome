'use strict';

/**
 * @ngdoc service
 * @name armada.UrlService
 * @description
 *
 * Сервис урлов.
 */
angular.module('armada')
	.service('UrlService', [function () {
		return {
			/**
			 * Возвращает текущий контекст приложения.
			 *
			 * @returns {string}
			 */
			getPrefix: function () {
				return '';//document.getElementById('prefix').value.substring(1);
			},
			/**
			 * Возвращает текущий путь для получения шаблонов.
			 * например: UrlService.getTemplatePrefix() + "/templates/dialogs/addEditContract.html"
			 *
			 * @returns {string}
			 */
			getTemplatePrefix: function () {
				return '';//document.getElementById('templatePrefix').value.substring(1);
			},
			/**
			 * Возвращает путь api.
			 *
			 * @returns {string}
			 */
			getApiPrefix: function () {
				return "";
			}
		};
	}]);