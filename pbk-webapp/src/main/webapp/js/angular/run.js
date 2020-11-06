angular.module('armada').run(function ($rootScope, $location, PermissionService, Restangular, UrlService, editableOptions, ngProgressFactory) {

	editableOptions.theme = 'bs3';

	$rootScope.$on('$locationChangeSuccess', function () {
		$rootScope.actualLocation = $location.path();
	});

	//Restangular.one(UrlService.getPrefix() + 'api/mcc/privileges').get().then(function (result) {
	PermissionService.setPermission(document.privileges);
	document.privileges = null;
	//}).catch(function () {
	//    throw new Error("Не удалось получить привилегии. Дальнейшая работа не возможна.");
	//});

	$rootScope.$watch(function () {
		return $location.path()
	}, function (newLocation, oldLocation) {
		if ($rootScope.actualLocation === newLocation) {
			if (getLevel(newLocation) === getLevel(oldLocation)) {  // проверяем уровень вложенности
				var newLocationBackUrl = levelUp(levelUp(newLocation));
				var oldLocationBackUrl = levelUp(levelUp(oldLocation));
				// убеждаемся, что переход был совершен с того же набора вкладок и что это не страница стандартного отчета
				if (newLocationBackUrl == oldLocationBackUrl && oldLocationBackUrl != '/report') {
					$location.path(newLocationBackUrl)
				}
			}
		}

		function getLevel(input) {
			return (input.split("/").length - 1);
		}

		function levelUp(newLocation) {
			for (var i = newLocation.length; i >= 0; i--) {
				if (newLocation[i] === "/") {
					break;
				}
			}
			return newLocation.substr(0, i)
		}
	});

	$rootScope.progressbar = ngProgressFactory.createInstance();

	$rootScope.$on('$stateChangeStart', function (ev, data) {
		$rootScope.progressbar.start();
	});
	$rootScope.$on('$stateChangeSuccess', function (ev, data) {
		$rootScope.progressbar.complete();
	});
	$rootScope.$on('$stateChangeError', function (ev, data) {
		$rootScope.progressbar.reset();
	});

});
