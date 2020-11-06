/**
 * Контроллер диалога редактирования настроек.
 */
angular.module('armada')
	.controller('SettingsEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'SettingsService', 'UtilService', 'Notification',
		function ($controller, $scope, $modalInstance, data, SettingsService) {

			$scope.isSelect = false;

			/* Поля ввода */
			$scope.fields = [{
				name: "value",
				type: "text",
				label: "Значение свойства",
				required: true
			}, {
				name: "selectValue",
				type: "select",
				label: "Значение свойства",
				required: false,
				load: function () {
					return SettingsService.getList([{
						name: "id",
						value: data.id,
						type: "id"
					}])
				},
				afterLoad: function () {
					$scope.isSelect = $scope.fields[1].list.length > 0;
					if ($scope.isSelect) {
						for (var i = 0; i < $scope.fields[1].list.length; i++) {
							if ($scope.fields[1].list[i].name === $scope.fields[0].value) {
								$scope.fields[1].value = $scope.fields[1].list[i];
							}
						}
					}
				},
				onChange: function () {
					$scope.fields[0].value = $scope.fields[1].value.name;
				}
			}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, SettingsService, $modalInstance);
		}]);