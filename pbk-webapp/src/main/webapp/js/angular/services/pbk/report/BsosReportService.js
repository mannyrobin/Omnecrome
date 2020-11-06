angular.module('armada')
	.service('BsosReportService', ['Notification', function (Notification) {
		return {
			exportReport: function (format, filters, grid, ids) {
				for (var i = 0; i < grid.rows.length; i++) {
					if (grid.rows[i].bsoTypeName != 'Акт изъятия проездных документов') {
						var id = ids.indexOf(grid.rows[i].id);
						if (id >= 0) {
							ids.splice(id, 1);
						}
					}
				}
				if (ids.length == 0 || angular.isObject(ids[0])) {
					Notification.info('Выберите по крайней мере один акт изъятия проездных документов для выгрузки.');
				} else if (ids.length > 25) {
					Notification.info('Число выгружаемых актов изъятия проездных документов должно быть не более 25.');
				} else if (ids.length > 0) {
					for (var i = 0; i < ids.length; i++) {
						if (typeof ids[i] !== 'number') {
							return;
						}
					}
                    var url = "/api/pbk/reports/aspose/nsi/bsos/export?format=" + format + "&ids=" + ids;
                    if(document.querySelector("#printPdf") != null){
                        document.querySelector("#printPdf").remove();
                    }
                    var errMsg = "Попытка повторно распечатать БСО, обратитесь к начальнику ИАО";
                    var fn = 'var pdfFrame = window.frames["printPdf"];pdfFrame.focus(); setTimeout(function(){if(pdfFrame.document.getElementsByTagName("pre").length !=1){pdfFrame.print();} else {alert("'+errMsg+'");}},3000);';
                    $("body").append("<iframe id='printPdf' src='"+url+"' name='printPdf' style='visibility:hidden;' onload='"+fn+"'></iframe>");
				}
			}
		};
	}]);
