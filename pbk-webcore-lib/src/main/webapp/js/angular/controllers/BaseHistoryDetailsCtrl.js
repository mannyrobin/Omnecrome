angular.module('armada')
	.controller('BaseHistoryDetailsCtrl', ['$scope',
		function ($scope) {

			$scope.init = function (item) {

				$scope.fillSelectItems = function (selectFunc, itemFieldName, fieldsIndex) {
					selectFunc().then(function (response) {
						$scope.fields[fieldsIndex].list = response.slice();
						for (var index = 0; index < $scope.fields[fieldsIndex].list.length; ++index) {
							if ($scope.fields[fieldsIndex].list[index].id == item[itemFieldName]) {
								$scope.fields[fieldsIndex].value = $scope.fields[fieldsIndex].list[index];
							}
						}
					});
				}

				$scope.fillSelectItemsParameterized = function (selectFunc, selectFilterName, itemFieldName, fieldsIndex) {
					selectFunc([{
						name: selectFilterName,
						value: item[itemFieldName],
						type: "id"
					}
					]).then(function (response) {
						$scope.fields[fieldsIndex].list = response.slice();
						for (var index = 0; index < $scope.fields[fieldsIndex].list.length; ++index) {
							if ($scope.fields[fieldsIndex].list[index].id == item[itemFieldName]) {
								$scope.fields[fieldsIndex].value = $scope.fields[fieldsIndex].list[index];
							}
						}
					});
				}
			}
		}
	]);