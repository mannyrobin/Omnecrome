'use strict';

/**
 * @ngdoc service
 * @name UsersService
 * @description
 *
 * Сервис контрактов.
 */
angular.module('armada')
	.service('BaseItemService', ['Restangular', 'GridService', function (Restangular, GridService) {
		return {
			init: function (parameters) {
				if (parameters.api == null) {
					parameters.api = [];
				}

				return {

					// Страница с пагинацией и фильтрами для грида
					getPage: parameters.api.indexOf('page') == -1 ? null : function (start, number, predicate, reverse, filters) {
						return Restangular.one(parameters.serviceurl).get(
							// TODO: переместить getPageParams из GridService в BaseItemService
							GridService.getPageParams(start, number, predicate, reverse, filters));
					},

					// Страница истории с пагинацией и фильтрами для грида
					getHistoryPage: parameters.api.indexOf('history') == -1 ? null : function (start, number, predicate, reverse, filters) {
						return Restangular.one(parameters.serviceurl + '/history').get(
							// TODO: переместить getPageParams из GridService в BaseItemService
							GridService.getPageParams(start, number, predicate, reverse, filters));
					},

					getOwnersHistoryPage: parameters.api.indexOf('ownershistory') == -1 ? null : function (start, number, predicate, reverse, filters) {
						return Restangular.one(parameters.serviceurl + '/ownershistory').get(
							// TODO: переместить getPageParams из GridService в BaseItemService
							GridService.getPageParams(start, number, predicate, reverse, filters));
					},

					// Список пар id, name для выпадающих списков
					getList: parameters.api.indexOf('slist') == -1 ? null : function (filters) {
						return Restangular.one(parameters.serviceurl + '/slist').get(
							// TODO: переместить getPageParams из GridService в BaseItemService
							GridService.getFilterParams(filters));
					},

					// Получить объект по id
					getItem: parameters.api.indexOf('get') == -1 ? null : function (id) {
						return Restangular.one(parameters.serviceurl + '/' + id).get();
					},

					// Добавить объект
					addItem: parameters.api.indexOf('add') == -1 ? null : function (item) {
						return Restangular.one(parameters.serviceurl).post("", item);
					},

					changeVersion: parameters.api.indexOf('version') == -1 ? null : function (item) {
						return Restangular.one(parameters.serviceurl + '/version').customPUT(item);
					},

					// Редактировать объект
					editItem: parameters.api.indexOf('edit') == -1 ? null : function (item) {
						return Restangular.one(parameters.serviceurl).customPUT(item);
					},

					// Удалить объекты
					removeItem: parameters.api.indexOf('remove') == -1 ? null : function (removeObj) {
						return Restangular.one(parameters.serviceurl + (removeObj.tryDelete != null ? '/deleteSoft' : '/delete')).get(removeObj);
					},

					// Восстановить версию объекта
					restoreItem: parameters.api.indexOf('history') == -1 ? null : function (id) {
						return Restangular.one(parameters.serviceurl + '/restore-version/' + id).get();
					}

				}
			}
		};
	}]);