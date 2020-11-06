'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:GridFilter
 * @description
 * Директива для отображения фильтра грида
 */
angular.module('armada').directive('arGridFilter', ['GridService', '$q', function (GridService, $q) {
	return {
		restrict: 'A',
		replace: true,
		scope: {
			parameters: '='
		},
		controller: function ($scope) {
			/**
			 * Элемент массива filters:
			 *   name   - имя
			 *   type   - тип (text, range, multiselect, select)
			 *   placeholder - placeholder
			 *   class  - css класс
			 *   value  - предустановленое значение
			 *   defval - дефолтное значение
			 *   class  - css-стиль
			 */

			$scope.isShow = function () {
				for (var index = 0; index < $scope.filters.length; ++index) {
					if (!$scope.filters[index].hide) {
						return true;
					}
				}
				return false;
			};

			$scope.all = {id: -1, name: "Все"};

			$scope.yesnoAll = [{id: -1, name: "Все"}, {id: 1, name: 'Да'}, {id: 0, name: 'Нет'}];

			$scope.addAll = function (filter) {
				if (filter.type == "select") {
					filter.list.unshift($scope.all);
				}
			};

			$scope.reset = function (filter) {
				if (filter.type == "select" ||
					filter.type == "multiselect") {
					filter.skip = $scope.all;
				}

				if (filter.defval != null && filter.value == null) {
					// override saved value
					filter.value = filter.defval;
					return;
				} else if (filter.defval != null) {
					return;
				}

				if (filter.type == "range") {
					filter.defval = {fromDate: null, toDate: null};
				}
				else if (filter.type == "text") {
					filter.defval = "";
				}
				else if (filter.type == "int") {
					filter.defval = "";
				}
				else if (filter.type == "select") {
					filter.defval = $scope.all;
				}
				else if (filter.type == "multiselect") {
					filter.defval = [$scope.all];
				}
				else if (filter.type == "yesno") {
					filter.defval = $scope.yesnoAll[1];
				}
				else {
					filter.defval = null;
				}
			};

			$scope.onChange = function (filter) {
				if (filter.type == "multiselect" && filter.value != null) {
					if (filter.value.length > 1 && filter.value[0] == $scope.all) {
						filter.value.shift();
					}
					else if (filter.value.length == 0) {
						filter.value.push($scope.all);
					}
				}
				if (angular.isDefined(filter.onChange)) {
					//использовать с осторожностью с мультиселектом были проблемы.
					//когда было два поля фильтрации и в зависимости от одного заново запрашивались значения селект листа
					//ng-change провоцировал бесконечный цикл. из onChsnge метода дёргал refresh для другого поля,
					//а refresh снова запускал onChange :(((
					filter.onChange(filter);
				}
			};

			$scope.filters = $scope.parameters.gridScope.filters;

			// filters preparation
			for (var index = 0; index < $scope.filters.length; ++index) {
				var filter = $scope.filters[index];

				$scope.reset(filter);
				if (filter.value == null) {
					filter.value = filter.defval;
				}

				if ((filter.type == "multiselect" || filter.type == "select" || filter.type == "yesno")) {
					if (filter.load != null) {
						filter.list = [];
						var load = function (index) {
							filter.load().then(function (response) {
								$scope.filters[index].list = response.slice();
								$scope.addAll($scope.filters[index]);
							});
						};
						load(index);
						filter.refresh = function () {
							var filter = this;
							if (filter.load != null) {
								filter.load().then(function (response) {
									filter.list = response.slice();
									$scope.addAll(filter);
								});
								filter.value = $scope.all;
							}
						};
					}
					else {
						if (filter.list == null) {
							filter.list = [];
						}
						$scope.addAll($scope.filters[index]);
					}
					if (filter.lazyload != null) {
						if (filter.lazymin == null) {
							filter.lazymin = 3;
						}
						// TODO: Реализовано только для добавления.
						// При редактировании, возникает вопрос, как подгружать значения для id из dto
						// matchSelect не вызывается, потому что в случае с добавлением он не нужен
						filter.lazyfunc = function (query) {
							var filter = this;
							var deferred = $q.defer();
							if ((query.length < filter.lazymin) || (angular.isFunction(filter.isLazyload) && !filter.isLazyload())) {
								deferred.resolve(filter.list);
							} else {
								filter.lazyload(query).then(function (response) {
									filter.lazyList = response.slice();
									deferred.resolve(filter.lazyList);
								});
							}
							return deferred.promise;
						}
					}
				}

				if ((filter.type == "yesno")) {
					if (filter.value == null) {
						filter.value = $scope.yesnoAll[1];
					}
					filter.list = $scope.yesnoAll;
				}

				if (filter.class == null) {
					if (filter.type == "range") {
						filter.class = "filter-range";
					}
					else {
						filter.class = "filter-base";
					}
				}
			}

			// filterString
			if ($scope.parameters.filterString == null) {
				$scope.parameters.filterString = "Найти";
			}

			// clearString
			if ($scope.parameters.clearString == null) {
				$scope.parameters.clearString = "Сбросить фильтры";
			}

			// applyFiltersFunc
			if ($scope.parameters.applyFiltersFunc == null) {
				$scope.parameters.applyFiltersFunc = function () {
					GridService.resetPageParams($scope.parameters.gridScope.tableState);
					$scope.parameters.gridScope.grid.loadAction();
				};
			}

			// isShowClearFunc
			if ($scope.parameters.isShowClearFunc == null) {
				$scope.parameters.isShowClearFunc = function () {
					for (var index = 0; index < $scope.filters.length; ++index) {
						var filter = $scope.filters[index];
						if (!filter.hide && filter.type == "text" && filter.value.length != 0 ||
							filter.type == "date" && (filter.value != null) ||
							filter.type == "range" && (filter.value.fromDate != null || filter.value.toDate != null) ||
							filter.type == "time-range" && (filter.value.fromDate != null || filter.value.toDate != null) ||
							filter.type == "select" && filter.value != null && filter.value != $scope.all ||
							filter.type == "yesno" && filter.value != null && filter.value.id != filter.defval.id ||
							filter.type == "multiselect" && filter.value != null && (filter.value.length != 1 || filter.value[0] != $scope.all)) {
							return true;
						}
					}

					return false;
				};
			}

			// clearFiltersFunc
			if ($scope.parameters.clearFiltersFunc == null) {
				$scope.parameters.clearFiltersFunc = function () {
					for (var index = 0; index < $scope.filters.length; ++index) {
						$scope.filters[index].value = $scope.filters[index].defval;
					}

					$scope.parameters.gridScope.grid.saveGridParams();
					$scope.parameters.gridScope.grid.loadAction($scope.parameters.gridScope.tableState, null, true);
				};
			}
		},
		templateUrl: 'templates/directives/grid/gridFilter.html'
	}
}]);