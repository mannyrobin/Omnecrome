'use strict';

/**
 * @ngdoc function
 * @name armada.controller:AddfilmsCtrl
 * @description
 *
 * Main controller
 */
angular.module('armada')
	.controller('MainCtrl', ['$scope', 'Notification', '$state', 'Restangular', '$modal', function ($scope, Notification, $state, Restangular, $modal) {
		/*
		 $scope.prefix = '';
		 $scope.templatePrefix = '';

		 $scope.getPrefix = function() {
		 return $scope.prefix;
		 };

		 $scope.getTemplatePrefix = function() {
		 return $scope.templatePrefix;
		 };

		 $scope.getCurrentYear = function() {
		 return new Date().getFullYear();
		 };

		 Restangular.setErrorInterceptor(function (response, deferred, responseHandler) {
		 var title = "Ошибка сервера";
		 var message = "Информация об ошибке не доступна";
		 var name = "Нет информации о названии ошибки";
		 var stack = null; // "Нет детальной информации об ошибке"
		 if (response.status === 500) {
		 if (angular.isUndefined(response.data.exceptionInfo)) {
		 throw new Error("В ответе сервера нет информации об ошибке");
		 }
		 message = response.data.exceptionInfo.message;
		 name = response.data.exceptionInfo.name;
		 stack = response.data.exceptionInfo.stack ? response.data.exceptionInfo.stack : null;
		 $modal.open({
		 templateUrl: $scope.getTemplatePrefix() + "/templates/dialogs/serverErrorDialog.html",
		 windowTemplateUrl: $scope.getTemplatePrefix() + '/templates/dialogs/draggableTemplate.html',
		 controller: 'RequestErrorCtrl',
		 size: 'lg',
		 resolve: {
		 data: function () {
		 return {
		 title: title,
		 message: message,
		 stack: stack,
		 name: name
		 };
		 }
		 }
		 });
		 return false;
		 }
		 if (response.status === 401) {
		 window.location.href = window.location.pathname + '/login';
		 return false; // error handled
		 }
		 if (response.status === 403) {
		 $state.go('forbidden');
		 return false; // error handled
		 }
		 return true; // error not handled
		 });
		 */
	}]);
