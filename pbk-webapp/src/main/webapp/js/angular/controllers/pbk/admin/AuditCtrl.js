angular.module('armada')
	.controller('AuditCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'AuditsService',
		function ($controller, $scope, $stateParams, UtilService, AuditsService) {

			$scope.getItemCrumb = function (item) {
				var result = '';
				if (item.name && item.name.length > 0) {
					result = result + ' ' + item.name;
				}
				if (result == '') {
					result = item.id;
				}
				return result;
			};

			$scope.getItem = AuditsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.admin.audit.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
				]
			});
		}]);