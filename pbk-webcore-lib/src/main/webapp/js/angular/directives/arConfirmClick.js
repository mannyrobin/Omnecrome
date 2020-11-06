'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:DateRange
 * @description
 *
 * Директива для обработки подтверждения
 */
angular.module('armada').directive('arConfirmClick', ['$modal',
	function ($modal) {

		return {
			scope: {
				'action': "&action",
				'autoconfirm': "&autoconfirm"
			},
			link: function (scope, element, attr) {

				function confirm() {
					var modalInstance = $modal.open({
						templateUrl: "templates/dialogs/confirmDialog.html",
						windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
						controller: 'ConfirmDialogCtrl',
						size: 'md',
						resolve: {
							data: function () {
								return {
									title: "Подтверждение",
									message: msg
								}
							}
						}
					});
					modalInstance.result.then(function () {
						scope.action();
					});
				}

				var msg = attr.arConfirmClick || "Are you sure?";
				element.bind('click', function (event) {
					if (scope.autoconfirm && typeof scope.autoconfirm === "function") {
						var res = scope.autoconfirm()
						if (res !== undefined && typeof res.then === "function") {
							res.then(function (res) {
								if (res)
									confirm();
								else
									return;
							});
						} else {
							confirm();
						}
					} else {
						confirm();
					}
				});
			}
		};
	}]);