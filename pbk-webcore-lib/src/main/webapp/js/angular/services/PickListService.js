'use strict';

/**
 * @ngdoc service
 * @name armada.GridService
 * @description
 *
 * Сервис урлов.
 */
angular.module('armada')
	.service('PickListService', ['localStorageService', 'UtilService', '$modal', 'Notification',
		function (localStorageService, UtilService, $modal, Notification) {

			return {

				initPickList: function (config) {
					var result = {};
					result.active = {};
					result.noActive = {};

					result.itemId = config.itemId;

					result.active.header = config.activeHeader;
					result.active.load = config.loadActiveItems;
					result.active.selectedItems = [];
					result.active.action = config.activeAction;
					result.active.items = [];

					result.noActive.header = config.noActiveHeader;
					result.noActive.load = config.loadNoActiveItems;
					result.noActive.selectedItems = [];
					result.noActive.action = config.noActiveAction;
					result.noActive.items = [];

					return result;
				}

			};
		}]);