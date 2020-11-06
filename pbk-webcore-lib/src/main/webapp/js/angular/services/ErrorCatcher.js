angular.module('armada')
	.factory('$exceptionHandler', function () {
		return function errorCatcherHandler(exception, cause) {
			var out = "Ошибка была получена в области видимости AngularJS\n";
			if (angular.isDefined(exception.message)) {
				out += "Сообщение: " + exception.message + "\n";
			}
			if (angular.isDefined(exception.stack)) {
				out += "Стек: " + exception.stack + "\n";
			}
			if (typeof exception == "string") {
				out += "Исключение: " + exception
			}

			console.error(out);
			alert(out);
		};
	});