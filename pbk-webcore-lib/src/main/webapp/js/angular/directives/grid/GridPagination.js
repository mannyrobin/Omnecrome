'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:arGridPagination
 * @description
 *
 * Директива для управления пагинацией грида.
 */
angular.module('armada').directive('arGridPagination', [function () {
	var templateUrl = 'templates/directives/grid/gridPagination.html';
	return {
		restrict: 'A',
		scope: {
			rowsOnPage: '=',
			hideButton: '=',
			gridScope: '='
		},
		template: "<div st-items-by-page='rowsOnPage' st-pagination='' st-template='" + templateUrl + "'></div>"
	}
}]);

/**
 * @ngdoc directive
 * @name armada.directive:arGridPaginationPageSelect
 * @description
 *
 * Вспомогательная директива, для отображение инпута с текущей страницей.
 */
angular.module('armada').directive('arGridPaginationPageSelect', [function () {

	function isNormalInteger(str) {
		var n = ~~Number(str);
		return String(n) === str && n >= 0;
	}

	return {
		restrict: 'E',
		templateUrl: 'templates/directives/grid/gridPaginationPageSelect.html',
		link: function (scope, element, attrs) {
			scope.isValidNumberOfPage = true;
			scope.selectPageWithValidation = function () {
				scope.isValidNumberOfPage = !isNaN(scope.inputPage);
				if (scope.isValidNumberOfPage) {
					scope.isValidNumberOfPage = isNormalInteger(scope.inputPage) && parseInt(scope.inputPage) > 0 && parseInt(scope.inputPage) <= scope.numPages;
				}
				scope.selectPage(scope.inputPage);
			};

			scope.$watch('currentPage', function (c) {
				scope.inputPage = c;
			});
		}
	}
}]);

/**
 * @ngdoc directive
 * @name armada.directive:arGridPaginationRowsSet
 * @description
 *
 * Вспомогательная директива, для отображения кнопки добавления записей в грид.
 */
angular.module('armada').directive('arGridPaginationRowsSet', ['$timeout', function ($timeout) {
	return {
		restrict: 'E',
		template: '<button ng-if="!hideButton" class="btn btn-primary" ng-disabled="isEndOfCollection()" ng-click="plusPage()">+ {{stItemsByPage}}</button>',
		link: function (scope, element, attrs) {
			var currentColOfAddedPages = 0;
			var setColOnPage = null;
			scope.$on('gridInfoForPagination', function (event, data) {
				currentColOfAddedPages = data.currentColOfAddedPages;
				setColOnPage = data.setColOnPage;
			});
			scope.plusPage = function () {
				if (setColOnPage != null) {
					setColOnPage(scope.stItemsByPage);
				}
			};
			scope.isEndOfCollection = function () {
				return scope.numPages == scope.currentPage || scope.currentPage + currentColOfAddedPages >= scope.numPages;
			};
			scope.hideButton = scope.$parent.$parent.hideButton;
		}
	}
}]);

/**
 * @ngdoc directive
 * @name armada.directive:arGridPaginationPaginationInfo
 * @description
 *
 * Вспомогательная директива, для отображения количества записей.
 */
angular.module('armada').directive('arGridPaginationPaginationInfo', [function () {
	return {
		restrict: 'E',
		template: 'c {{getNumOfFirstRecordOnPage()}} по {{getNumOfLastRecordOnPage()}} из {{getColOfRecords()}}',
		link: function (scope, element, attrs) {
			var colOfRecords = 0;
			scope.plusPages = 1;
			scope.$on('gridInfoForPagination', function (event, data) {
				colOfRecords = data.colOfRecords;
				scope.plusPages = data.currentColOfAddedPages;
			});

			scope.getColOfRecords = function () {
				return colOfRecords;
			};
			scope.getNumOfLastRecordOnPage = function () {
				var numPages = scope.currentPage * scope.stItemsByPage;
				// если находимся не на последней странице, увеличиваем счетчик
				if ((scope.currentPage + scope.plusPages) * scope.stItemsByPage < colOfRecords) {
					numPages = numPages + scope.plusPages * scope.stItemsByPage;
				} else {
					numPages = colOfRecords;
				}
				return numPages;
			};
			scope.getNumOfFirstRecordOnPage = function () {
				return !colOfRecords ? 0 : (scope.currentPage - 1) * scope.stItemsByPage + 1;
				//return scope.numPages == scope.currentPage ? colOfRecords : scope.currentPage*scope.stItemsByPage;
			};
		}
	}
}]);

/**
 * @ngdoc directive
 * @name armada.directive:arGridPaginationRowsSet
 * @description
 *
 * Вспомогательная директива, для отображения кнопки добавления записей в грид.
 */
angular.module('armada').directive('arGridPaginationInfinity', ['$timeout', '$window', function ($timeout, $window) {
	return {
		restrict: 'E',
		template: '<div ng-if="!hideButton" ng-disabled="isEndOfCollection()">{{getText()}}</div>',
		link: function (scope, elem, attrs) {
			scope.hideButton = scope.$parent.hideButton;
			if (!scope.hideButton) {
				var currentColOfAddedPages = 0;
				var setColOnPage = null;

				scope.$on('gridInfoForPagination', function (event, data) {
					currentColOfAddedPages = data.currentColOfAddedPages;
					setColOnPage = data.setColOnPage;
					if (scope.useInfinity && scope.gridScope.viewRowsOnPage < 20) {
						scope.scrollfunc();
					}
				});

				scope.plusPage = function () {
					if (setColOnPage != null) {
						setColOnPage(scope.stItemsByPage);
					}
				};

				scope.height = function (element) {
					var el = element[0] || element;

					if (isNaN(el.offsetHeight)) {
						return el.document.documentElement.clientHeight;
					}
					return el.offsetHeight;
				};

				scope.pageYOffset = function (element) {
					var el = element[0] || element;

					if (isNaN(window.pageYOffset)) {
						return el.document.documentElement.scrollTop;
					}
					return el.ownerDocument.defaultView.pageYOffset;
				};

				scope.offsetTop = function (element) {
					if (!(!element[0].getBoundingClientRect || element.css('none'))) {
						return element[0].getBoundingClientRect().top + scope.pageYOffset(element);
					}
					return undefined;
				};

				scope.isEndOfCollection = function () {
					return scope.numPages == scope.currentPage || scope.currentPage + currentColOfAddedPages >= scope.numPages;
				};

				scope.getText = function () {
					if (typeof scope.grid === 'undefined') {
						return "";
					}
					return scope.grid.isPortionLoading ? "Загрузка данных..." : "";
				};

				scope.scrollfunc = function () {
					if (!scope.isEndOfCollection() && !scope.grid.isLoading && !scope.grid.isPortionLoading && scope.gridScope.infinityLoad) {

						var scrollDistance = 1;
						var container = angular.element($window);
						var containerBottom = scope.height(container) + scope.pageYOffset(container[0].document.documentElement);
						var elementBottom = scope.offsetTop(elem) + scope.height(elem);

						var remaining = elementBottom - containerBottom;
						var shouldScroll = remaining <= scope.height(container) * scrollDistance + 1;

						if (shouldScroll) {
							scope.plusPage();
						}
					}
				};
				angular.element($window).bind("scroll", scope.scrollfunc);

				scope.grid = scope.$parent.gridScope.grid;
				scope.gridScope = scope.$parent.gridScope;

				scope.usePagging = !scope.gridScope.infinityLoad;
				scope.useInfinity = scope.gridScope.infinityLoad;
				scope.gridScope.$watch('infinityLoad', function (c) {
					scope.usePagging = !c;
					scope.useInfinity = c;
					if (scope.useInfinity) {
						scope.scrollfunc();
					}
				});
			} else {
				scope.usePagging = true;
				scope.useInfinity = false;
			}
		}
	}
}]);
