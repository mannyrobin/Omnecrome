'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:DateRange
 * @description
 * Директива для обработки диапазона дат
 */
angular.module('armada').directive('arGridMenu', ['$timeout', function ($timeout) {
	return {
		restrict: 'A',
		replace: true,
		scope: {
			refreshAction: '=',
			rowsOnPage: '=',
			headers: '=',
			viewRowsOnPage: '=',
			showRowCount: '=',
			infinityLoad: '=',
			hideInfinityLoad: '='
		},
		controller: function ($scope) {
			$scope.viewRowsOnPage = $scope.rowsOnPage;  // init
			$scope.pageRowsNum = [10, 50, 100, 250];
			if ($scope.showRowCount == null) {
				$scope.showRowCount = true;
			}
			angular.element(".show-hide-grid-cols").tooltip({
				title: 'Скрыть/показать столбцы',
				placement: 'top',
				container: 'body'
			});
			angular.element(".grid-rows-on-page").tooltip({
				title: 'Сменить количество строк',
				placement: 'top',
				container: 'body'
			});
			angular.element(".grid-select-report-format").tooltip({
				title: 'Выбрать формат отчета',
				placement: 'top',
				container: 'body'
			});
			$scope.selectPageRowsNum = function (pageRowsNum) {
				if ($scope.rowsOnPage == pageRowsNum && $scope.rowsOnPage != $scope.viewRowsOnPage) {
					$scope.viewRowsOnPage = $scope.rowsOnPage;
					$timeout(function () {
						$scope.refreshAction();
					});
				}
				$scope.rowsOnPage = pageRowsNum;
				$scope.viewRowsOnPage = pageRowsNum;
				return false;
			}
			// TODO: Не знаю почему, но именно infinityLoad не меняется через ng-model
			$scope.onInfinityLoad = function () {
				$scope.infinityLoad = !$scope.infinityLoad;
			}
		},
		templateUrl: 'templates/directives/grid/gridMenu.html'
	}
}]);