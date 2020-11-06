angular.module('armada')
	.controller('BonusEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'BonusesService',
		function ($controller, $scope, $modalInstance, data, BonusesService) {

			$scope.fields = [
				{
					name: "periodStartDate",
					type: "date",
					label: "Дата начала периода премирования",
					required: true
				}, {
					name: "periodEndDate",
					type: "date",
					label: "Дата конца периода премирования",
					required: true
				}, {
					name: "tickets",
					type: "multiselect",
					label: "Билеты",
					load: BonusesService.getList,
					required: true
				}, {
					name: "name",
					type: "text",
					label: "Название периода премирования",
					required: true
				}, {
					name: "count",
					type: "int",
					label: "Плановое кол-во",
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, BonusesService, $modalInstance);
		}]);