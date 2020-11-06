'use strict';

/**
 * @ngdoc service
 * @name UsersService
 * @description
 *
 * Сервис контрактов.
 */
angular.module('armada')
	.service('FileViewerService', ['Restangular', 'GridService', '$http', '$window', function (Restangular, GridServic, $http, $window) {
		return {

			init: function (name) {

				if (!name)
					return undefined;

				var filename = name.split(".");
				if (filename.length <= 1)
					return undefined;

				var contentType = undefined;

				if (filename[filename.length - 1] === "png") {
					contentType = "image/png";
				} else if (filename[filename.length - 1] === "jpg"
					|| filename[filename.length - 1] === "jpeg") {
					contentType = "image/jpg";
				} else if (filename[filename.length - 1] === "bmp") {
					contentType = "image/bmp";
				} else if (filename[filename.length - 1] === "pdf") {
					contentType = "application/pdf";
				}

				return contentType === undefined ? contentType : {

					contentType: contentType,

					view: function (uri) {

						var tab = $window.open("about:blank", "_blank");
						tab.document.write('<img src="/images/spinner.gif" style="width: 100px; height: 100px; position: fixed; top: 50%; left: 50%; margin-top: -50px; margin-left: -50px;"/>');
						$http.get(uri, {
							headers: {'Content-Type': contentType},
							responseType: 'arraybuffer'
						}).then(function (response) {
							var file = new Blob([response.data], {type: contentType});
							var fileURL = URL.createObjectURL(file);
							tab.location.href = fileURL;
						})
					}
				}
			}

		};
	}]);