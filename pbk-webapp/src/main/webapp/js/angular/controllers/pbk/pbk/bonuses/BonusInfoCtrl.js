angular.module('armada')
	.controller('BonusInfoCtrl', ['$controller', '$scope', '$stateParams', 'BonusesService', 'isNotReadOnlyUser',
		function ($controller, $scope, $stateParams, BonusesService, isNotReadOnlyUser) {
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "periodStartDate",
					type: "date",
					label: "Дата начала периода премирования",
					readonly: !isNotReadOnlyUser,
					required: true
				}, {
					name: "periodEndDate",
					type: "date",
					label: "Дата конца периода премирования",
					readonly: !isNotReadOnlyUser,
					required: true
				}, {
					name: "tickets",
					type: "multiselect",
					label: "Билеты",
					readonly: !isNotReadOnlyUser,
					load: BonusesService.getList,
					required: true
				}, {
					name: "name",
					type: "text",
					label: "Название периода премирования",
					readonly: !isNotReadOnlyUser,
					required: true
				}, {
					name: "count",
					type: "int",
					label: "Плановое кол-во",
					readonly: !isNotReadOnlyUser,
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, BonusesService);
		}]);