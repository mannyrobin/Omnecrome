angular.module('armada')
	.service('TasksExportReportService', ['UrlService', 'Notification', 'TasksService', 'GridService', function (UrlService, Notification, TasksService, GridService) {

		return {
			exportReport: function (format, filters, grid, ids) {
				var url = "/api/pbk/reports/aspose/pbk/tasks/detail?format=" + format;
				if (ids.length == 0 || angular.isObject(ids[0])) {
					Notification.info("Выберите по крайней мере одно задание для выгрузки.");
				} else if (ids.length > 50) {
					Notification.info("Число выгружаемых заданий должно быть не более 50.");
				} else {
					for (var i = 0; i < ids.length; i++) {
						url += "&ids=" + ids[i];
					}
					TasksService.processTasks(ids).then(function () {
						grid.refreshAction();
					});
					window.location.href = url;
				}
			},

			exportGridReport: function (format, filters, predicate, reverse) {
                var fltrs = [];
                angular.forEach(filters, function (val) {
                    fltrs.push(val);
                });
                fltrs.push({
                    "type": "text",
                    "name": "sidx",
                    "value": predicate
                });
                fltrs.push({
                    "type": "text",
                    "name": "sord",
                    "value": !reverse ? "desc" : "asc"
                });
				window.location.href = "/api/pbk/reports/aspose/pbk/tasks?format=" + format + GridService.getFilterParamsForUrl(fltrs);
			}

		};
	}]);
