'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:GridTitle
 * @description
 * Директива для отображения заголовка грида
 */
angular.module('armada').directive('arGridTitle', [function () {
	return {
		restrict: 'A',
		replace: true,
		scope: {
			parameters: '='
		},
		controller: function ($scope) {

			/**
			 * Параметры:
			 * Необходимо установить:
			 *   gridScope
			 *
			 * Можно переопределить (указаны параметры по умолчанию):
			 *   labels.title = ""
			 *   labels.addTip = "Добавить"
			 *   perms = { add: gridScope.perms.add }
			 *   actions = { refresh: gridScope.grid.refreshAction,
                 *               add: gridScope.addRow }
			 */

			// labels
			if ($scope.parameters.labels == null) {
				$scope.parameters.labels = {};
			}

			// labels.title
			if ($scope.parameters.labels.title == null) {
				$scope.parameters.labels.title = "";
			}

			// labels.addTip
			if ($scope.parameters.labels.addTip == null) {
				$scope.parameters.labels.addTip = "Добавить";
			}
			if ($scope.parameters.labels.addTipIcon == null) {
				$scope.parameters.labels.addTipIcon = "plus";
			}

			if ($scope.parameters.gridScope.titleWidth == null || $scope.parameters.gridScope.titleButtonsWidth == null) {
				$scope.parameters.gridScope.titleWidth = 'col-lg-8';
				$scope.parameters.gridScope.titleButtonsWidth = 'col-lg-4';
			}

			// perms
			if ($scope.parameters.perms == null) {
				if ($scope.parameters.gridScope.perms != null) {
					$scope.parameters.perms = $scope.parameters.gridScope.perms;
				} else {
					$scope.parameters.perms = {
						add: $scope.parameters.gridScope.perms != null ? $scope.parameters.gridScope.perms.add : false
					};
					//треш конечно пора бы забить на перекладывание из  $scope.parameters.gridScope в $scope.parameters
					if ($scope.parameters.buttons != null) {
						for (var i = 0; i < $scope.parameters.buttons.length; i++) {
							$scope.parameters.perms[$scope.parameters.buttons[i].name] = false;
						}
					}
				}

			}

			// actions
			if ($scope.parameters.actions == null) {
				$scope.parameters.actions = {
					refresh: $scope.parameters.gridScope.grid.refreshAction,
					export: $scope.parameters.gridScope.grid.exportAction,
					add: $scope.parameters.gridScope.addRow
				};
			}

			// headers
			var headers = $scope.parameters.gridScope.headers;
			angular.forEach(headers, function (header) {
				if (header.visible == null) {
					header.visible = true;
				}
			});

			// infinityload
			if ($scope.parameters.hideInfinityLoad == null) {
				$scope.parameters.hideInfinityLoad = false;
			}
		},
		templateUrl: 'templates/directives/grid/gridTitle.html'
	}
}]);