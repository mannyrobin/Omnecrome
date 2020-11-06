'use strict';

/**
 * @ngdoc service
 * @name armada.ModalService
 * @description
 *
 * Сервис управления модальными окнами.
 */
angular.module('armada')
	.service('ModalService', ['$modal',
		function ($modal) {
			return {
				/**
				 * Проверяет, пустая ли форма по набору полей формы.
				 *
				 * @param fields - набор ngModele из scope
				 * @returns {boolean} - true, если форма пустая
				 */
				isDirty: function (fields) {
					var isDirty = false;
					for (var i = 0; i < fields.length; i++) {
						if (fields[i] && fields[i] != null && fields[i].length > 0 || fields[i] instanceof Date == true || fields[i] instanceof Object == true) {
							isDirty = true;
							break;
						}
					}
					return isDirty;
				},
				/**
				 * Ставит ограничение на форму в диалоге, при котором вызывается алерт с подтверждением, если форма не пустая.
				 *
				 * @param fields - набор ngModele из scope
				 * @param dialogModalInstance - инстанс окна диалога, в котором находится форма
				 * @param message - пользовательское сообщение (при отсутствии будет показано стандартное)
				 */
				protectedClose: function (fields, dialogModalInstance, message) {
					if (this.isDirty(fields)) {
						var modalInstance = $modal.open({
							templateUrl: "templates/dialogs/confirmClosingDirtyForm.html",
							windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
							controller: 'ConfirmClosingDirtyFormCtrl',
							size: 'md',
							resolve: {
								data: function () {
									return {
										message: message
									}
								}
							}
						});
						modalInstance.result.then(function () {
							dialogModalInstance.dismiss('cancel'); // закрываем форму
						}, function () {
							modalInstance.dismiss('cancel'); // закрываем alert
						});
					} else {
						dialogModalInstance.dismiss('cancel');
					}
				},

				/**
				 * Ставит ограничение на форму в диалоге, при котором вызывается алерт с подтверждением, если закрытие запрещает пользовательская функция.
				 *
				 * @param dialogModalInstance - инстанс окна диалога, в котором находится форма
				 * @param canClose - функция для вызова в момент закрытия. Если вернет true, диалог будет закрыт.
				 * @param message - пользовательское сообщение (при отсутствии будет показано стандартное)
				 */
				protectedCloseCustom: function (canClose, dialogModalInstance, message) {
					if (!canClose()) {
						var modalInstance = $modal.open({
							templateUrl: "templates/dialogs/confirmClosingDirtyForm.html",
							windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
							controller: 'ConfirmClosingDirtyFormCtrl',
							size: 'md',
							resolve: {
								data: function () {
									return {
										message: message
									}
								}
							}
						});
						modalInstance.result.then(function () {
							dialogModalInstance.dismiss('cancel'); // закрываем форму
						}, function () {
							modalInstance.dismiss('cancel'); // закрываем alert
						});
					} else {
						dialogModalInstance.dismiss('cancel');
					}
				},

				createModalDataController: function (fieldsGetter, dialogModalInstance, message) {

					var objTypeName = "object";

					function equals(oldV, newV) {
						if ((oldV === null || newV === null) || (oldV === undefined || newV === undefined))
							return oldV === newV;
						if (typeof oldV === objTypeName) {
							if (typeof newV !== objTypeName)
								return false;
							else {
								var oldKeys = Object.keys(oldV);
								var newKeys = Object.keys(newV);
								if (oldKeys.length != newKeys.length)
									return false;
								for (var i in oldKeys) {
									if (!equals(oldV[oldKeys[i]], newV[oldKeys[i]]))
										return false;
								}
								return true;
							}
						} else {
							if (typeof newV === objTypeName)
								return false;
							else {
								return oldV === newV;
							}
						}
					}

					return {
						oldValue: fieldsGetter(),
						initValue: function () {
							this.oldValue = fieldsGetter();
						},
						protectedClose: function (customChecker) {
							var value = fieldsGetter();

							if (!equals(this.oldValue, value) || (customChecker && !customChecker())) {
								var modalInstance = $modal.open({
									templateUrl: "templates/dialogs/confirmClosingDirtyForm.html",
									windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
									controller: 'ConfirmClosingDirtyFormCtrl',
									size: 'md',
									resolve: {
										data: function () {
											return {
												message: message
											}
										}
									}
								});
								modalInstance.result.then(function () {
									dialogModalInstance.dismiss('cancel'); // закрываем форму
								}, function () {
									modalInstance.dismiss('cancel'); // закрываем alert
								});
							} else {
								dialogModalInstance.dismiss('cancel');
							}
						},
						resultClose: function () {
							dialogModalInstance.close(fieldsGetter());
						},
						fields: function () {
							return fieldsGetter();
						}
					};
				}
			};
		}]);
