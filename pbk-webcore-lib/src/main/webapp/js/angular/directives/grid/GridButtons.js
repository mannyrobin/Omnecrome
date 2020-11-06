'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:GridButtons
 * @description
 * Директива для отображения кнопок в каждой строке грида
 */
angular.module('armada').directive('arGridButtons', [function () {
	return {
		restrict: 'A',
		replace: true,
		scope: {
			parameters: '=',
			row: '=',
			showEditButton: '&',
			showRemoveButton: '&'
		},
		controller: function ($scope) {

			/**
			 * Параметры:
			 * Необходимо установить:
			 *   gridScope
			 *
			 * Можно переопределить (указаны параметры по умолчанию):
			 *   labels.editTip = "Редактировать"
			 *   labels.removeTip = "Удалить"
			 *   labels.removeConfirm = "Вы действительно хотите удалить объект?"
			 *   perms = { edit: gridScope.perms.add,
                 *             remove: gridScope.perms.remove}
			 *   check = {
                 *      isEditEnable : function(row){ return true;},
                        isRemoveEnable : function(row){ return true;}
                 *   }
			 *   actions = { edit: gridScope.editRow,
                 *               remove: gridScope.removeRow }
			 */

			// labels
			if ($scope.parameters.labels == null) {
				$scope.parameters.labels = {};
			}

			// labels.editTip
			if ($scope.parameters.labels.editTip == null) {
				$scope.parameters.labels.editTip = "Редактировать";
			}

			// labels.removeTip
			if ($scope.parameters.labels.removeTip == null) {
				$scope.parameters.labels.removeTip = "Удалить";
			}

			// labels.removeConfirm
			if ($scope.parameters.labels.removeConfirm == null) {
				$scope.parameters.labels.removeConfirm = "Вы действительно хотите удалить объект?";
			}

			// perms
			if ($scope.parameters.perms == null) {
				$scope.parameters.perms = {
					edit: $scope.parameters.gridScope.perms != null ? $scope.parameters.gridScope.perms.edit : false,
					remove: $scope.parameters.gridScope.perms != null ? $scope.parameters.gridScope.perms.remove : false
				};
			}

			if ($scope.parameters.check == null) {
				$scope.parameters.check = {
					isEditEnable: function (row) {
						return true;
					},
					isRemoveEnable: function (row) {
						return true;
					}
				}
			}

			if ($scope.parameters.check.isEditEnable == null) {
				$scope.parameters.check.isEditEnable = function (row) {
					return true;
				};
			}

			if ($scope.parameters.check.isRemoveEnable == null) {
				$scope.parameters.check.isRemoveEnable = function (row) {
					return true;
				};
			}

			// actions
			if ($scope.parameters.actions == null) {
				$scope.parameters.actions = {
					edit: $scope.parameters.gridScope.editRow,
					remove: $scope.parameters.gridScope.removeRow
				};
			}

			// extraButtons
			if ($scope.parameters.extraButtons == null) {
				$scope.parameters.extraButtons = [];
			}
		},
		templateUrl: 'templates/directives/grid/gridButtons.html'
	}
}]);