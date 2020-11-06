'use strict';

/**
 * @ngdoc function
 * @name armada.controller:BaseEditCtrl
 * @description
 *
 * Базовый контроллер модального окна создания или редактирования объекта.
 */
angular.module('armada')
	.controller('BaseEditCtrl', ['$scope', '$q', 'UtilService', 'Notification', '$modal', '$state',
		function ($scope, $q, UtilService, Notification, $modal, $state) {

			$scope.init = function (data, fields, actions, modalInstance) {
				/**
				 * Входные параметры:
				 *   title    - заголовок
				 *   id       - id объекта
				 *   actions  - методы для работы с данными
				 *   item     - начальная инициализпция элемента
				 *   fields   - редактируемые поля
				 *     name   - имя
				 *     type   - тип (text, date, multiselect, select)
				 *     label  - лейбл
				 *     required - обязательное поле
				 *     list   - список значений для выпадающего списка
				 *     load   - функция получения списка значений для ввыпадающего списка
				 *     defval - дефолтное значение
				 *
				 * Можно переопределить
				 *   save     - действие при сохранении
				 *   close    - действие при отмене
				 *   fillItem - заполнение объекта перед сохранением
				 */

				$scope.title = data.title;
				$scope.id = data.id;

				if (angular.isDefined(data.backUrl)) {
					$scope.backUrl = angular.isDefined(data.backUrl);
				} else {
					var split = $state.current.name.split(".");
					$scope.backUrl = split.slice(0, split.length - 2).join(".") + ".list";
				}

				if (!$scope.save) {
					$scope.save = $scope.id == null ? $scope.defaultAddAction : $scope.defaultEditAction;
				}
				if (!$scope.close) {
					$scope.close = $scope.defaultCloseAction;
				}
				if (!$scope.reset) {
					$scope.reset = $scope.defaultResetAction;
				}

				if (!$scope.saveVersion) {
					$scope.saveVersion = $scope.defaultChangeVersion;
				}

				$scope.modalInstance = modalInstance;
				$scope.actions = actions;
				$scope.item = data.item;

				$scope.reset(true);
			};

			$scope.isChange = $scope.item == null || !angular.isDefined($scope.item);
			$scope.oldValues = [];

			$scope.fillItem = function () {
				if ($scope.item == null) {
					$scope.item = {};
				}
				for (var index = 0; index < $scope.fields.length; ++index) {
					var field = $scope.fields[index];
					if (field.type == "multiselect") {
						$scope.item[field.name] = [];
						angular.forEach(field.value, function (s) {
							$scope.item[field.name].push(s.id);
						});
					}
					else if (field.type == "select") {
						$scope.item[field.name] = field.value ? field.value.id : null;
					}
					else if (field.type == "date") {
						$scope.item[field.name] = moment(field.value).isValid() ? moment(field.value).format('DD.MM.YYYY') : "";
					}
					else if (field.type == "timeRange") {
						$scope.item[field.name.fromDate] = field.value.fromDate;
						$scope.item[field.name.toDate] = field.value.toDate;
					}
					else if (field.type == "yesno") {
						if (field.value) {
							$scope.item[field.name] = field.value.id == 1;
						}
					}
					else if (field.type == "legitimate") {
						$scope.item[field.name] = field.value ? field.value.id : null;
					}
					else {
						$scope.item[field.name] = field.value;
					}
				}
			};

			$scope.defaultCloseAction = function () {
				$scope.modalInstance.dismiss('cancel');
			};

			$scope.defaultAddAction = function () {
				if ($scope.id != null && $scope.item == null) {
					return;
				}
				$scope.fillItem();
				if ($scope.actions.addItem) {
					$scope.actions.addItem($scope.item).then(
						function () {
							if ($scope.refreshBreadCrumbles) {
								$scope.refreshBreadCrumbles();
							}
							$scope.errTitle = "";
							$scope.errMessages = "";
							Notification.info("Запись успешно создана.");
							if ($scope.modalInstance) {
								$scope.modalInstance.close();
							}
						}
					).catch(function (response) {
						var errors = UtilService.getFormErrors(response);
						$scope.errTitle = errors.errTitle;
						$scope.errMessages = errors.errMessages;
					});
				} else {
					$scope.errTitle = "Действие не определено";
					var errors = [];
					errors.push("Действие не определено");
					$scope.errMessages = errors;
				}
			};

			$scope.defaultEditAction = function () {
				if ($scope.id != null && $scope.item == null) {
					return;
				}
				$scope.fillItem();
				if ($scope.actions.editItem) {
					$scope.actions.editItem($scope.item).then(
						function () {
							//$scope.item;
							if ($scope.refreshBreadCrumbles) {
								$scope.refreshBreadCrumbles();
							}
							$scope.errTitle = "";
							$scope.errMessages = "";
							if ($scope.refreshAfterLoad) {
								for (var index = 0; index < $scope.fields.length; ++index) {
									if ($scope.fields[index].type == "multiselect" || $scope.fields[index].type == "select") {
										$scope.fields[index].loaded = false;
									}
								}
								$scope.defaultResetAction();
							} else {
								clearOldValue();
							}
							Notification.info("Запись успешно обновлена.");
							if ($scope.modalInstance) {
								$scope.modalInstance.close();
							}
						}
					).catch(function (response) {
						var errors = UtilService.getFormErrors(response);
						$scope.errTitle = errors.errTitle;
						$scope.errMessages = errors.errMessages;
					});
				} else {
					$scope.errTitle = "Действие не определено";
					var errors = [];
					errors.push("Действие не определено");
					$scope.errMessages = errors;
				}
			};

			$scope.defaultChangeVersion = function () {
				if ($scope.actions.changeVersion) {
					var modalInstance = $modal.open({
						templateUrl: "templates/dialogs/editDateDlg.html",
						windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
						controller: "EditDateCtrl",
						size: "sm",
						resolve: {
							data: function () {
								return {
									title: "Дата вступления в силу изменений",
									id: "Дата вступления в силу изменений",
									date: new Date()
								}
							}
						}
					});
					modalInstance.result.then(function (result) {
						$scope.fillItem();
						$scope.item['versionStartDate'] = result.selectedDate;
						$scope.actions.changeVersion($scope.item).then(
							function (response) {
								$scope.item = response;
								if ($scope.refreshBreadCrumbles) {
									$scope.refreshBreadCrumbles();
								}
								$scope.errTitle = "";
								$scope.errMessages = "";
								if ($scope.refreshAfterLoad) {
									for (var index = 0; index < $scope.fields.length; ++index) {
										if ($scope.fields[index].type == "multiselect" || $scope.fields[index].type == "select") {
											$scope.fields[index].loaded = false;
										}
									}
									$scope.defaultResetAction();
								} else {
									clearOldValue();
								}
								Notification.info("Запись успешно обновлена.");
								if ($scope.modalInstance) {
									$scope.modalInstance.close();
								}
							}
						).catch(function (response) {
							var errors = UtilService.getFormErrors(response);
							$scope.errTitle = errors.errTitle;
							$scope.errMessages = errors.errMessages;
						});
					});

				} else {
					$scope.errTitle = "Действие не определено";
					var errors = [];
					errors.push("Действие не определено");
					$scope.errMessages = errors;
				}
			};

			$scope.defaultResetAction = function (firstReset) {
				if ($scope.id != null) {
					if (!$scope.isChange && !firstReset) {
						$state.go($scope.backUrl);
					}
					if ($scope.actions.getItem) {
						$scope.actions.getItem($scope.id).then(function (result) {
							$scope.item = result;
							$scope.oldValues = [];
							for (var index = 0; index < $scope.fields.length; ++index) {
								var field = $scope.fields[index];
								if (field.type == "multiselect") {
									field.value = [];
									var t = [];
									angular.forEach($scope.item[field.name], function (s) {
										field.value.push({id: s});
										t.push({id: s});
									});

									$scope.oldValues.push(t);
								}
								else {
									if (field.type == "select") {
										field.value = {id: $scope.item[field.name]};
									}
									else if (field.type == "date") {
										field.value = moment($scope.item[field.name], "DD.MM.YYYY").toDate();
									}
									else if (field.type == "timeRange") {
										field.value = {
											fromDate: $scope.item[field.name.fromDate],
											toDate: $scope.item[field.name.toDate]
										};
									}
									else if (field.type == "yesno") {
										field.value = $scope.item[field.name] ? {id: 1, name: 'Да'} : {
											id: 0,
											name: 'Нет'
										};
										field.list = [{id: 1, name: 'Да'}, {id: 0, name: 'Нет'}];
									}
									else if (field.type == "legitimate") {
										switch ($scope.item[field.name]) {
											case true:
												field.value = {id: true, name: 'Да'};
												break;
											case false:
												field.value = {id: false, name: 'Нет'};
												break;
											default:
												field.value = null;
										}
										field.list = [{id: true, name: 'Да'}, {id: false, name: 'Нет'}];
									}
									else if (field.type == "mapType") {
										field.value = $scope.item[field.name] && $scope.item[field.name] == 1 ? {
											id: 1,
											name: 'внутренняя'
										} : {id: 0, name: 'Яндекс'};
										field.list = [{id: 0, name: 'Яндекс'}, {id: 1, name: 'ЕГКО'}];
									}
									else {
										field.value = $scope.item[field.name];
									}
									$scope.oldValues.push(field.value);
								}

								$scope.$watch('fields[' + index + '].value', function () {
									for (var index = 0; index < $scope.fields.length; ++index) {

										if ($scope.fields[index].type == "select" || $scope.fields[index].type == "yesno" || $scope.fields[index].type == "legitimate") {
											if (($scope.fields[index].value == null && $scope.oldValues[index] != null && $scope.oldValues[index].id != null) ||
												($scope.fields[index].value != null && ($scope.oldValues[index] == null || $scope.oldValues[index].id != $scope.fields[index].value.id))) {
												$scope.isChange = true;
												return;
											}
										}
										else if ($scope.fields[index].type == "multiselect") {
											if (($scope.fields[index].value == null && $scope.oldValues[index] != null) ||
												($scope.fields[index].value != null && $scope.oldValues[index] == null)) {
												$scope.isChange = true;
												return;
											}
											if ($scope.fields[index].value != null && $scope.oldValues[index] != null) {
												if ($scope.fields[index].value.length != $scope.oldValues[index].length) {
													$scope.isChange = true;
													return;
												} else {
													for (var i = 0; i < $scope.fields[index].value.length; i++) {
														var k = $scope.fields[index].value[i].id;
														var find = false;
														for (var j = 0; j < $scope.oldValues[index].length && !find; j++) {
															find = $scope.oldValues[index][j].id == k;
														}
														if (!find) {
															$scope.isChange = true;
															return;
														}
													}
												}
											}
										}

										else if ($scope.fields[index].type == "date") {
											if (moment($scope.oldValues[index]).format('YYYY-MM-DD') != moment($scope.fields[index].value).format('YYYY-MM-DD')) {
												$scope.isChange = true;
												return;
											}
										}
										else if ($scope.fields[index].type == "time") {
											if (moment($scope.oldValues[index]).format('HH-mm') != moment($scope.fields[index].value).format('HH-mm')) {
												$scope.isChange = true;
												return;
											}
										}
										else if ($scope.fields[index].type == "timeRange") {
											if (($scope.fields[index].value == null && ($scope.oldValues[index].fromDate != null || $scope.oldValues[index].toDate)) ||
												($scope.fields[index].value != null && moment($scope.oldValues[index].fromDate).format('YYYY-MM-DD') != moment($scope.fields[index].value.fromDate).format('YYYY-MM-DD')
												|| moment($scope.oldValues[index].toDate).format('YYYY-MM-DD') != moment($scope.fields[index].value.toDate).format('YYYY-MM-DD'))) {
												$scope.isChange = true;
												return;
											}
										} else if ($scope.oldValues[index] != $scope.fields[index].value) {
											$scope.isChange = true;
											return;
										}

									}
									$scope.isChange = false;
								});

							}
							$scope.loadSelects();
							if (angular.isFunction($scope.callbackResetAction)) {
								$scope.callbackResetAction();
							}
						}).catch(function () {
							Notification.error("Ошибка при получении информации о редактируемом объекте");
						});
					} else {
						Notification.error("Ошибка при получении информации о редактируемом объекте");
					}
				}
				else {
					for (var index = 0; index < $scope.fields.length; ++index) {
						var field = $scope.fields[index];
						field.value = field.defval != null ? field.defval : null;
					}
					$scope.loadSelects();
				}
			};

			$scope.loadSelects = function () {
				for (var index = 0; index < $scope.fields.length; ++index) {
					var field = $scope.fields[index];
					if (field.type == "multiselect" || field.type == "select") {
						if (field.loaded) {
							$scope.matchSelect(field);
							continue;
						}

						if (field.load != null) {
							field.list = [];
							var load = function (index) {
								var params = field.loadParams;
								var afterLoad = function (response) {
									$scope.fields[index].loaded = true;
									$scope.fields[index].list = response.slice();
									$scope.matchSelect($scope.fields[index]);
									if ($scope.fields[index].defaultIndex != null && $scope.fields[index].list[$scope.fields[index].defaultIndex] != null) {
										$scope.fields[index].value = $scope.fields[index].list[$scope.fields[index].defaultIndex];
									} else if ($scope.fields[index].value == null && $scope.fields[index].default) {
										$scope.fields[index].value = $scope.fields[index].default;
									}
									if ($scope.fields[index].afterLoad != null)
										$scope.fields[index].afterLoad();
								}
								if (params != null) {
									field.load(params).then(afterLoad);
								}
								else {
									field.load().then(afterLoad);
								}
							};
							load(index);
							field.refresh = function () {
								var field = this;
								if (field.load != null) {
									field.load().then(function (response) {
										field.list = response.slice();
										if (field.chooseItemAfterLoad && field.list.length > 0) {
											field.value = field.list[0];
										}
										$scope.matchSelect(field);
										if (field.defaultIndex != null && field.list[field.defaultIndex] != null) {
											field.value = field.list[field.defaultIndex];
										} else if (field.value == null && field.default) {
											field.value = field.default;
										}
										if (field.afterLoad != null)
											field.afterLoad();
									});
									if (!field.notSetAll) {
										field.value = $scope.all;
									}
								}
							};
						}
						else {
							if (field.list == null) {
								field.list = [];
							}
							field.loaded = true;
							$scope.matchSelect(field);
						}

						if (field.lazyload != null) {
							if (field.lazymin == null) {
								field.lazymin = 3;
							}
							// TODO: Реализовано только для добавления.
							// При редактировании, возникает вопрос, как подгружать значения для id из dto
							// matchSelect не вызывается, потому что в случае с добавлением он не нужен
							field.lazyfunc = function (query) {
								var field = this;
								var deferred = $q.defer();
								if ((query.length < field.lazymin) || (angular.isFunction(field.isLazyload) && !field.isLazyload())) {
									deferred.resolve(field.list);
								} else {
									field.lazyload(query).then(function (response) {
										field.lazyList = response.slice();
										deferred.resolve(field.lazyList);
									});
								}
								return deferred.promise;
							}
						}
					}
				}
			};

			$scope.matchSelect = function (field) {
				var selectedItems = [];
				if (field.type == "multiselect") {
					if (field.value != null) {
						for (var i = 0; i < field.value.length; i++) {
							if (field.value[i] != null && field.value[i].id == null) {
								field.value[i] = null;
							}
						}
						selectedItems = field.value;
					}
				}
				else if (field.type == "select") {
					if (field.value != null && field.value.id == null) {
						field.value = null;
					} else {
						selectedItems.push(field.value);
					}
				}
				else {
					return;
				}

				angular.forEach(selectedItems, function (selectedItem) {
					if (selectedItem != null) {
						var it = 0;
						for (; it < field.list.length; it++) {
							if (selectedItem.id == field.list[it].id) {
								selectedItem.name = field.list[it].name;
								break;
							}
						}
						if (it >= field.list.length) {
							field.value = null;
						}
					}
				});

				if (selectedItems != null) {
					if (field.type == "multiselect") {
						for (var i = 0; i < selectedItems.length; i++) {
                            if (selectedItems[i].name == null && field.value != null) {
                                field.value[i] = null;
							}
						}
					} else if (field.type == "select" && (selectedItems[0] == null || selectedItems[0].name == null)) {
						field.value = null;
						for (var it = 0; it < field.list.length; it++) {
							if (field.list[it].id == null) {
								field.value = field.list[it];
							}
						}
					}
				}
			};

			function clearOldValue() {
				$scope.oldValues = [];
				for (var index = 0; index < $scope.fields.length; ++index) {
					if (Array.isArray($scope.fields[index].value)) {
						var arr = [];
						angular.forEach($scope.fields[index].value, function (el) {
							arr.push(el);
						});
						$scope.oldValues.push(arr);
					} else {
						$scope.oldValues.push($scope.fields[index].value);
					}
				}
				$scope.isChange = false;
			}

			function fillFields(item) {

			}

		}]);