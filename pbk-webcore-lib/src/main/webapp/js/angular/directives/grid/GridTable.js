'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:GridTitle
 * @description
 * Директива для отображения заголовка грида
 */
angular.module('armada').directive('arGridTable', ['AppConfig', function (AppConfig) {
	return {
		restrict: 'A',
		replace: true,
		scope: {
			gridScope: '='
		},
		controller: function ($scope) {

			$scope.choosenItemIds = [];

			/**
			 * Поля элемента headers:
			 * id           - имя поля
			 * label        - лейбл колонки
			 * visible      - отображать колонку?
			 * disableSort  - запретить сортировку по полю
			 * index        - использовать getIndex для заполнения значений колонки
			 * href         - функция, возвращающая ссылку
			 * class        - класс отображения колонки
			 */

			// buttons
			if ($scope.gridScope.buttons == null) {
				$scope.gridScope.buttons = {
					gridScope: $scope.gridScope
				};
			}

			$scope.gridScope.buttons.show = $scope.gridScope.editRow != null || $scope.gridScope.removeRow != null ||
				($scope.gridScope.buttons != null && $scope.gridScope.buttons.extraButtons != null);

			if ($scope.gridScope.grid.columnCount == null) {
				$scope.gridScope.grid.columnCount = AppConfig.COLUMN_COUNT;
			}

			// column classes
			var headers = $scope.gridScope.headers;
			angular.forEach(headers, function (header) {
				if (header.class == null) {
					header.class = header.index ? "col-md-1" : "col-md-2";
				}
			});

			// get field text
			$scope.getText = function (row, rnum, index, a) {
				var header = $scope.gridScope.headers[index];

				if ((header.href != null || header.hrefClick != null) && !a || (header.href == null && header.hrefClick == null) && a) {
					return "";
				}

				if (header.get != null) {
					return header.get(row);
				}

				var text;
				if (header.index) {
					text = $scope.gridScope.grid.getIndex(rnum);
				}
				else {
					text = row[header.id];
				}

				return text;
			};

			// get field index
			$scope.getIndex = function (rnum) {
				return $scope.gridScope.grid.getIndex(rnum);
			};

			$scope.getRowClass = function (row) {
				if ($scope.gridScope.getRowClassFunc) {
					return $scope.gridScope.getRowClassFunc(row);
				} else {
					return "";
				}
			};

			$scope.getRowTextClass = function (row) {
				if ($scope.gridScope.getRowTextClassFunc) {
					return $scope.gridScope.getRowTextClassFunc(row);
				} else {
					return "";
				}
			};

			$scope.gridScope.getRowById = function (id) {
				var row = null;
				angular.forEach($scope.gridScope.grid.rows, function (nextRow) {
					if (nextRow.id == id) {
						row = nextRow;
					}
				});
				return row;
			};

			$scope.gridScope.getSelectedItemId = function () {
				var selectedItemId = [];
				angular.forEach($scope.gridScope.grid.rows, function (row) {
					if ($scope.choosenItemIds[row.id]) {
						selectedItemId.push(row.id);
					}
				});
				return selectedItemId;
			};

			$scope.gridScope.setSelectionAll = function (state) {
				$scope.choosenItemIds[-1] = state;
				changeAllValue(state);
			};

			$scope.selectAll = function () {
				changeAllValue($scope.choosenItemIds[-1]);
			};

			$scope.init = function () {
				if ($scope.gridScope.addSelItemColumn) {
					$scope.choosenItemIds[-1] = false;
					changeAllValue(false);
				}
			};

			$scope.checkSelectAll = function (row) {
				if ($scope.gridScope.resetIndex) {
					changeAllValue(false);
					$scope.choosenItemIds[row.id] = true;
				} else {
					for (var i = 0; i < $scope.gridScope.grid.rows.length; i++) {
						if (!$scope.choosenItemIds[$scope.gridScope.grid.rows[i].id]) {
							$scope.choosenItemIds[-1] = false;
							return;
						}
					}
					$scope.choosenItemIds[-1] = true;
				}
			};

			$scope.showSelItemColumn = function (row) {
				return $scope.gridScope.addSelItemColumn && (!$scope.gridScope.historyGrid || row.versionEndDate.split(' ')[0].split('.')[2] < 2100);
			};

			function changeAllValue(value) {
				angular.forEach($scope.gridScope.grid.rows, function (row) {
					if ($scope.gridScope.disabledSelItemColumn == undefined || !$scope.gridScope.disabledSelItemColumn(row)) {
						$scope.choosenItemIds[row.id] = value;
					}
				});
			}
		},
		templateUrl: 'templates/directives/grid/gridTable.html'
	}
}]);