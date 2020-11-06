angular.module('armada')
	.controller('BsoGenerateCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'BsoNumberRulesService', 'BsoTypesService', 'DepartmentsService', 'BsosService', 'UtilService', 'Notification',
		function ($controller, $scope, $modalInstance, data, BsoNumberRulesService, BsoTypesService, DepartmentsService, BsosService, UtilService, Notification) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "departmentId",
					type: "select",
					placeholder: "Подразделение",
					load: function () {
						return DepartmentsService.getDepartsForBso();
					},
					label: "Подразделение",
					required: true
				}, {
					name: "bsoTypeId",
					type: "select",
					placeholder: "Тип БСО",
					load: BsoTypesService.getList,
					label: "Тип БСО",
					required: true
				}, {
					name: "count",
					type: "text",
					placeholder: "Количество",
					label: "Количество",
					defval: 100,
					required: true
				}];

			$scope.generate = function () {
				var departmentId = $scope.fields[0].value.id;
				var bsoTypeId = $scope.fields[1].value.id;
				var count = $scope.fields[2].value;
				BsosService.generateBulk(departmentId, bsoTypeId, count).then(function (result) {
					$modalInstance.close();
				}).catch(function (response) {
					var errors = UtilService.getFormErrors(response);
					//var errMsg = errors.errMessages[0];
					$scope.errTitle = errors.errTitle;
					$scope.errMessages = errors.errMessages;
					//Notification.error(errMsg);
				});
			};

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, BsosService, $modalInstance);
			$scope.fields[1].value = BsoTypesService.getList()[0];
		}]);
