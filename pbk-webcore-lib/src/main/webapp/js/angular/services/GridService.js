'use strict';

/**
 * @ngdoc service
 * @name armada.GridService
 * @description
 *
 * Сервис урлов.
 */
angular.module('armada')
	.service('GridService', ['localStorageService', 'UtilService', '$modal', 'Notification',
		function (localStorageService, UtilService, $modal, Notification) {

			function findColumn(predicate, multiParams) {
				for (var i = 0; i < multiParams.predicate.length; i++) {
					if (multiParams.predicate[i] == predicate) {
						return i;
					}
				}
				return false;
			}

			var GRID_LOCAL_STORAGE_PREFIX = 'gridParams_';
			var GRID_FIRST_RECORD = 0;

			return {
				setGridTableStateParams: function (tableState, predicate, reverse) {
					if (angular.isUndefined(tableState)) {
						throw new Error("tableState параметр должен быть определен");
					}
					if (angular.isUndefined(tableState.sort.predicate)) {
						tableState.sort.predicate = predicate;
					}
					if (angular.isUndefined(tableState.sort.reverse)) {
						tableState.sort.reverse = reverse;
					}
				},
				sortTable: function (predicate, reverse, multiParams) {
					if (reverse != null) {
						var columuIndex = findColumn(predicate, multiParams);
						if (columuIndex || columuIndex === 0) {
							multiParams.predicate[columuIndex] = predicate;
							multiParams.reverse[columuIndex] = reverse ? "asc" : "desc";
						} else {
							multiParams.predicate.push(predicate);
							multiParams.reverse.push(reverse ? "asc" : "desc");
						}
					} else {
						var index = findColumn(predicate, multiParams);
						multiParams.predicate.splice(index, 1);
						multiParams.reverse.splice(index, 1);
					}
				},
				getHeaderClass: function (id, multiParams) {
					var index = findColumn(id, multiParams);
					if (index || index === 0) {
						return multiParams.reverse[index] == "asc" ? "st-sort-ascent" : "st-sort-descent";
					}
					return "";
				},
				getGridParamsById: function (gridId) {
					var params = localStorageService.get(GRID_LOCAL_STORAGE_PREFIX + gridId);
					if (params && params.date && (new Date(params.date)).getUTCDate() == (new Date()).getUTCDate()) {
						return params;
					}
					return null;
				},
				setGridParams: function (gridId, params) {
					params.date = new Date();
					localStorageService.set(GRID_LOCAL_STORAGE_PREFIX + gridId, params);
				},
				/**
				 * Получает выбранный элемент из списка по id
				 *
				 * @param id - id нужного элемента
				 * @param list - все элементы списка
				 */
				getFilterSelectFromSavedParams: function (id, list) {
					if (angular.isDefined(id) && id != null) {
						for (var i = 0; i < list.length; i++) {
							if (list[i].id == id) {
								return list[i];
							}
						}
					}
					return null;
				},
				resetPageParams: function (tableState) {
					if (angular.isDefined(tableState) && angular.isDefined(tableState.pagination) && tableState.pagination != null) {
						tableState.pagination.start = GRID_FIRST_RECORD;
					}
				},
				// TODO: место этому коду в BaseItemService
				getFilterParams: function (filters, getParams) {
					if (getParams == null) {
						getParams = {};
					}
					if (filters && filters != null) {
						for (var index = 0; index < filters.length; ++index) {
							var filter = filters[index];
							if (filter.value == null) {
								continue;
							}
							if (filter.type == "select" && filter.value == filter.skip) {
								continue;
							}
							if (filter.type == "multiselect" && filter.value.length > 0 && filter.value[0] == filter.skip) {
								continue;
							}

							if (filter.type == "text" && filter.value.trim().length != 0) {
								getParams["t_" + filter.name] = filter.value;
							}
							else if (filter.type == "date" && filter.value != null) {
								getParams["d_" + filter.name] = moment(filter.value).isValid() ? moment(filter.value).format('DD.MM.YYYY') : "";
							}
							else if (filter.type == "range" && (filter.value.fromDate != null || filter.value.toDate != null)) {
								getParams["d_" + filter.name.fromName] = moment(filter.value.fromDate).isValid() ? moment(filter.value.fromDate).format('DD.MM.YYYY') : "";
								getParams["d_" + filter.name.toName] = moment(filter.value.toDate).isValid() ? moment(filter.value.toDate).format('DD.MM.YYYY') : "";
							}
							else if (filter.type == "time-range" && (filter.value.fromDate != null || filter.value.toDate != null)) {
								getParams["v_" + filter.name.fromName] = moment(filter.value.fromDate).isValid() ? moment(filter.value.fromDate).format('HH:mm') : "";
								getParams["v_" + filter.name.toName] = moment(filter.value.toDate).isValid() ? moment(filter.value.toDate).format('HH:mm') : "";
							}
							else if (filter.type == "select" && filter.value.id != null) {
								getParams["i_" + filter.name] = filter.value.id;
							}
							else if (filter.type == "yesno" && filter.value.id != null && filter.value.id != -1) {
								getParams["i_" + filter.name] = filter.value.id;
							}
							else if (filter.type == "id" && filter.value != null) {
								getParams["i_" + filter.name] = filter.value;
							}
							else if (filter.type == "multiselect" && filter.value.length > 0) {
								getParams["l_" + filter.name] = [];
								angular.forEach(filter.value, function (s) {
									getParams["l_" + filter.name].push(s.id);
								});
							}
						}
					}
					return getParams;
				},
				getFilterParamsForUrl: function (filters, getParams) {
					if (filters && filters != null) {
						var url = "";
						for (var index = 0; index < filters.length; ++index) {
							var filter = filters[index];
							if (filter.value == null) {
								continue;
							}
							if (filter.type == "select" && filter.value == filter.skip) {
								continue;
							}
							if (filter.type == "multiselect" && filter.value.length > 0 && filter.value[0] == filter.skip) {
								continue;
							}

							if (filter.type == "text" && filter.value.trim().length != 0) {
								url += "&t_" + filter.name + "=" + filter.value;
							}
							else if (filter.type == "date" && filter.value != null) {
								url += moment(filter.value).isValid() ? "&d_" + filter.name + "=" + moment(filter.value).format('DD.MM.YYYY') : "";
							}
							else if (filter.type == "range" && (filter.value.fromDate != null || filter.value.toDate != null)) {
								url += moment(filter.value.fromDate).isValid() ? "&d_" + filter.name.fromName + "=" + moment(filter.value.fromDate).format('DD.MM.YYYY') : "";
								url += moment(filter.value.toDate).isValid() ? "&d_" + filter.name.toName + "=" + moment(filter.value.toDate).format('DD.MM.YYYY') : "";
							}
							else if (filter.type == "select" && filter.value.id != null) {
								url += "&i_" + filter.name + "=" + filter.value.id;
							}
							else if (filter.type == "yesno" && filter.value.id != null && filter.value.id != -1) {
								url += "&i_" + filter.name + "=" + filter.value.id;
							}
							else if (filter.type == "id" && filter.value != null) {
								url += "&i_" + filter.name + "=" + filter.value;
							}
							else if (filter.type == "multiselect" && filter.value.length > 0) {
								angular.forEach(filter.value, function (s) {
									url += "&l_" + filter.name + "=" + s.id;
								});
							}
						}
					}
					return url;
				},
				// TODO: место этому коду в BaseItemService
				getPageParams: function (start, number, predicate, reverse, filters) {
					var sord = reverse ? "asc" : "desc";
					var getParams = {
						rows: number,
						page: UtilService.getPageNumber(start, number),
						sord: sord,
						sidx: predicate
					};
					return this.getFilterParams(filters, getParams);
				},
				getAddAction: function (template, controller, title, size, gridScope, item) {
					return function () {
						var modalInstance = $modal.open({
							templateUrl: "templates/dialogs/" + template,
							windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
							controller: controller,
							size: size,
							resolve: {
								data: function () {
									return {
										title: title,
										id: null,
										item: item
									}
								}
							}
						});
						modalInstance.result.then(function () {
							gridScope.grid.refreshAction();
						});
					};
				},
				getEditAction: function (template, controller, title, size, gridScope) {
					return function (item) {
						var modalInstance = $modal.open({
							templateUrl: "templates/dialogs/" + template,
							windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
							controller: controller,
							size: size,
							resolve: {
								data: function () {
									return {
										title: title,
										id: item.id
									}
								}
							}
						});
						modalInstance.result.then(function () {
							gridScope.grid.refreshAction();
						});
					};
				},
				getRemoveAction: function (error, gridScope, removeAction, tryDelete) {
					return function (item) {
						if (removeAction) {
							var removeObj = {ids: item.id};
							if (tryDelete != null) {
								removeObj.tryDelete = tryDelete;
							}
							removeAction(removeObj).then(function () {
								gridScope.grid.refreshAction();
								Notification.info("Запись успешно удалена.");
							}).catch(function (response) {
								var errMsg = "";
								if (response.status == 400) {
									var errors = UtilService.getFormErrors(response);
									errMsg = " Причина:" + errors.errMessages;
								}
								Notification.error("Удалить не удалось" + errMsg);
							});
						} else {
							var errors = UtilService.getFormErrors(response);
							$scope.errTitle = errors.errTitle;
							$scope.errMessages = errors.errMessages;
						}
					};
				},
				initGridData: function (config) {
					var self = this;
					/* save params begin */
					var result = {};

					result.pagination = {};
					result.params = {};
					result.params.filters = [];
					result.isPortionLoading = false;

					result.gridParams = self.getGridParamsById(config.gridName);

					// TODO: Запоминание config.default* можно будет убрать, когда будут исправлены отчеты
					// там по ошибке используются дефолтные параметры конфига, а не переменные скопа
					if (config.defaultStart == null) {
						config.defaultStart = 0;
					}
					result.pagination.start = config.defaultStart;

					if (config.defaultNumber == null) {
						config.defaultNumber = 50;
					}
					result.pagination.number = config.defaultNumber;
					config.tableScope.colRowsOnPage = result.pagination.number;
					if (!config.tableScope.viewRowsOnPage) config.tableScope.viewRowsOnPage = config.tableScope.colRowsOnPage;

					if (typeof config.tableScope.infinityLoad === 'undefined') config.tableScope.infinityLoad = false;

					if (config.defaultPredicate == null) {
						config.defaultPredicate = "id";
					}
					result.predicate = config.defaultPredicate;

					if (config.defaultReverse == null) {
						config.defaultReverse = true;
					}
					result.reverse = config.defaultReverse;

					result.exportFormats = config.exportFormats ? config.exportFormats : ["xlsx", "pdf", "csv"];

					var saveGridParams = function () {
						self.setGridParams(config.gridName, {
							number: result.pagination.number,
							predicate: result.predicate,
							reverse: result.reverse,
							filters: result.params.filters
						});
					};

					result.saveGridParams = saveGridParams;

					if (result.gridParams != null) {
						result.pagination.start = 0;
						result.pagination.number = angular.isDefined(result.gridParams.number) ? result.gridParams.number : number;
						result.predicate = angular.isDefined(result.gridParams.predicate) ? result.gridParams.predicate : predicate;
						result.reverse = angular.isDefined(result.gridParams.reverse) ? result.gridParams.reverse : reverse;
						for (var index = 0; index < config.tableScope.filters.length; ++index) {
							if (angular.isDefined(result.gridParams.filters[index])) {
								config.tableScope.filters[index].value = result.gridParams.filters[index];
							}
						}
					} else {
						saveGridParams();
					}

					/* save params end */

					/* Pagination begin */

					config.tableScope.currentColOfAddedPages = 0;
					/**
					 * Подгружает следующую порцию данных в грид на той же странице.
					 *
					 * @param stItemsByPage - по сколько данных подгружать
					 */
					result.pagination.setColOnPage = function (stItemsByPage) {
						result.isPortionLoading = true;
						config.tableScope.viewRowsOnPage += stItemsByPage;
						setAddPageParams();
						config.load(
							result.pagination.start,
							result.pagination.number,
							result.predicate,
							result.reverse,
							config.tableScope.filters
						).then(function (response) {
							saveGridParams();
							angular.forEach(response.rows, function (value) {
								result.rows.push(value);
							});
							result.isPortionLoading = false;
						}).catch(function (response) {
							result.isPortionLoading = false;
							if (angular.isFunction(config.errorLoadFunc)) {
								config.errorLoadFunc(response);
							}
						});
					};

					config.tableScope.currentPage = 1;
					config.tableScope.colOfRecords = 0;

					/**
					 * Посылает пагинатору актуальную информацию о гриде.
					 */
					function sendGridInfoToPaginator() {
						config.tableScope.$broadcast('gridInfoForPagination', {
							currentColOfAddedPages: config.tableScope.currentColOfAddedPages,
							colOfRecords: config.tableScope.colOfRecords,
							setColOnPage: result.pagination.setColOnPage,
							setCurrentPage: function (page) {
								config.tableScope.currentPage = page;
							}
						});
					}

					/**
					 * Устанавливает параметры пагинации.
					 */
					function setAddPageParams() {
						config.tableScope.currentColOfAddedPages++;
						sendGridInfoToPaginator();
						if (config.tableScope.tableState && config.tableScope.tableState != null) {
							var pagination = config.tableScope.tableState.pagination;
							result.pagination.start = pagination.start + pagination.number * config.tableScope.currentColOfAddedPages;
							result.pagination.number = pagination.number;
							result.predicate = config.tableScope.tableState.sort.predicate;
							result.reverse = config.tableScope.tableState.sort.reverse;
						}
					}

					result.refreshAction = function () {
						if (angular.isDefined(config.tableScope.viewRowsOnPage)) {
							if (config.tableScope.colRowsOnPage != config.tableScope.viewRowsOnPage) {
								config.tableScope.colRowsOnPage = config.tableScope.viewRowsOnPage;
							} else {
								getAction(config.tableScope.tableState, null);
							}
						}
					};

					result.exportAction = !config.export ? undefined : function (format) {
						var fltrs = [];
						angular.forEach(config.tableScope.filters, function (val) {
							fltrs.push(val);
						});
						fltrs.push({
							"type": "text",
							"name": "sidx",
							"value": config.tableScope.tableState.sort.predicate
						});
						fltrs.push({
							"type": "text",
							"name": "sord",
							"value": !config.tableScope.tableState.sort.reverse ? "desc" : "asc"
						});
						if (config.tableScope.getSelectedItemId != null && config.tableScope.getSelectedItemId().length > 0) {
							var ids = [];
							angular.forEach(config.tableScope.getSelectedItemId(), function (id) {
								ids.push({id: id});
							});
							fltrs.push({"type": "multiselect", "name": "ids", "value": ids});
						}
						config.export(format, fltrs, config.tableScope.grid, config.tableScope.getSelectedItemId != null ? config.tableScope.getSelectedItemId() : null);
					};

					/* Pagination end */

					config.tableScope.$watch(function () {
						return config.tableScope.colRowsOnPage;
					}, function (newColPages, oldColPages) {
						if (newColPages != null && newColPages != oldColPages) {
							getAction(null, parseInt(newColPages));
						}
					});

					result.rows = [];
					config.tableScope.tableState = null;

					var getAction = function (tableState, colPages, isFirstPage) {
						config.tableScope.currentColOfAddedPages = 0;
						if (config.tableScope.tableState == null) {
							config.tableScope.tableState = tableState;
							config.tableScope.tableState.pagination.start = result.pagination.start;

						}
						result.isLoading = true;
						if (config.tableScope.tableState && config.tableScope.tableState != null) {
							self.setGridTableStateParams(config.tableScope.tableState, result.predicate, result.reverse); // установка параметров пагинации по умолчанию
							var pagination = config.tableScope.tableState.pagination;
							if (!isFirstPage) {
								result.pagination.start = typeof  pagination.start == 'number' ? pagination.start : result.pagination.start;
							} else {
								config.tableScope.tableState.pagination.start = 0;
							}
							result.pagination.number = typeof  pagination.number == 'number' ? pagination.number : result.pagination.number;
							result.predicate = config.tableScope.tableState.sort.predicate;
							result.reverse = config.tableScope.tableState.sort.reverse;
							for (var index = 0; index < config.tableScope.filters.length; ++index) {
								var filter = config.tableScope.filters[index];
								// skip 'all' keyword 
								if (filter.hide || filter.type == "multiselect" && filter.value != null && filter.value.length > 0 && filter.value[0] == filter.skip ||
									filter.type == "select" && filter.value == filter.skip) {
									result.params.filters[index] = null;
								}
								else {
									result.params.filters[index] = filter.value;
								}
							}
						}
						result.pagination.number = typeof colPages == "number" ? colPages : result.pagination.number;

						if (config.lazyLoad === true) {
							result.isLoading = false;
							config.lazyLoad = false;
							return;
						}

						config.load(result.pagination.start,
							result.pagination.number,
							result.predicate,
							result.reverse,
							config.tableScope.filters
						).then(function (response) {
							saveGridParams();
							result.rows = response.rows;
							config.tableScope.colOfRecords = response.records;
							config.tableScope.tableState.pagination.numberOfPages = response.total;
							config.tableScope.viewRowsOnPage = config.tableScope.colRowsOnPage;
							result.isLoading = false;
							sendGridInfoToPaginator();
							if (config.afterLoad) {
								config.afterLoad();
							}
						}).catch(function (response) {
							result.isLoading = false;
							if (angular.isFunction(config.errorLoadFunc)) {
								config.errorLoadFunc(response);
							}
						});
					};

					result.isShowColumn = function (index) {
						var visible = config.tableScope.headers[index].visible;
						return angular.isDefined(visible) ? visible : true;
					};

					result.getType = function (index) {
						var type = config.tableScope.headers[index].type;
						return angular.isDefined(type) ? type : 'text';
					};

					result.getIndex = function (index) {
						if (!config.tableScope.tableState || config.tableScope.tableState == null)
							return index;
						return config.tableScope.tableState.pagination.start + index + 1;
					};

					result.loadAction = getAction;

					console.debug(config.gridName + " грид успешно инициализирован");
					return result;
				}

			};
		}]);
