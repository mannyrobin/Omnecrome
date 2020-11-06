'use strict';

angular.module('armada')
	.directive('arTabset', function () {
		return {
			restrict: 'E',
			replace: true,
			transclude: true,
			controller: function ($scope) {
				$scope.templateUrl = '';
				var tabs = $scope.tabs = [];
				var controller = this;

				this.selectTab = function (tab) {
					angular.forEach(tabs, function (tab) {
						tab.selected = false;
					});
					tab.selected = true;
				};

				this.setTabTemplate = function (templateUrl) {
					$scope.templateUrl = templateUrl;
				};

				this.addTab = function (tab) {
					if (tabs.length == 0) {
						controller.selectTab(tab);
					}
					tabs.push(tab);
				};
			},
			template: '<div class="row">' +
			'<div class="nav nav-tabs" ng-transclude></div>' +
			'<div class="tab-content" ng-include="templateUrl"></div>' +
			'</div>'
		};
	})
	.directive('arTab', function () {
		return {
			restrict: 'E',
			replace: true,
			require: '^arTabset',
			scope: {
				title: '@',
				templateUrl: '@',
				hide: "="
			},
			link: function (scope, element, attrs, tabsetController) {
				tabsetController.addTab(scope);

				scope.select = function () {
					tabsetController.selectTab(scope);
				};

				scope.$watch('selected', function () {
					if (scope.selected) {
						tabsetController.setTabTemplate(scope.templateUrl);
					}
				});
			},
			template: '<li ng-class="{active: selected}" ng-hide="hide">' +
			'<a href="" ng-click="select()">{{ title }}</a>' +
			'</li>'
		};
	});