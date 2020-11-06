angular.module('armada')
	.service('BaseSoReportService', ['GridService', function (GridService) {
		return {
			getBaseSoUrl: function (soNumber, format, filters, rows, sidx, sord) {
				var url = "/api/pbk/reports/aspose/report/standard/so-" + soNumber + "/export?";
				url += "format=" + format + "&";
				url += "rows=" + rows + "&";
				url += "sidx=" + sidx + "&";
				url += "sord=" + (sord ? "asc" : "desc");
				var filterParams = GridService.getFilterParams(filters);
				for (var prop in filterParams) {
					if (filterParams.hasOwnProperty(prop)) {
						var filterParam = prop;
						if (filterParams[filterParam] !== "") {
							url += "&" + filterParam + "=" + filterParams[filterParam];
						}
					}
				}
				return url;
			}
		};
	}]);
