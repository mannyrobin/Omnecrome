angular.module('armada')
	.controller('BsoBindCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'DepartmentsService', 'BsosService', 'EmployeesService', 'Restangular', 'UrlService', 'UtilService', 'Notification',
		function ($controller, $scope, $modalInstance, data, DepartmentsService, BsosService, EmployeesService, Restangular, UrlService, UtilService, Notification) {
			var serviceUrl = UrlService.getPrefix() + 'nsi/bsos';

			/* Поля ввода */
			$scope.fields = [
				{
					name: "departmentName",
					type: "text",
					label: "Подразделение",
					defval: data.departmentName,
					readonly: true,
					onChange: function () {
						$scope.fields[1].refresh();
					}
				}, {
					name: "employeeId",
					type: "select",
					load: function () {
						return EmployeesService.getList([{
							name: "departmentId",
							value: data.departmentId,
							type: "id"
						}, {
							name: "forPlanUse",
							value: 1,
							type: "id"
						}, {
							name: "isNotFire",
							value: 1,
							type: "id"
						}]);
					},
					label: "Сотрудник",
					required: true
				}];

			$scope.bind = function () {
				var employeeId = $scope.fields[1].value.id;
				BsosService.bindBsos(data.ids, employeeId).then(function (result) {
					$modalInstance.close();
				}).catch(function (response) {
					var errors = UtilService.getFormErrors(response);
					//var errMsg = errors.errMessages[0];
					//Notification.error(errMsg);
					$scope.errTitle = errors.errTitle;
					$scope.errMessages = errors.errMessages;
				});
			};

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, BsosService, $modalInstance);
		}]);
