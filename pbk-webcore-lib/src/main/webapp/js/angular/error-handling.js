window.onerror = function (msg, url, line, col, error) {
	// Note that col & error are new to the HTML 5 spec and may not be
	// supported in every browser.  It worked for me in Chrome.
	var extra = !col ? '' : '\nколонка: ' + col;
	extra += !error ? '' : '\nошибка: ' + error;

	// You can view the information in an alert to see things working like this:
	var out = "Ошибка была получена во внешнем скрипте:\nСообщение: " + msg + "\nПуть к файлу: " + url + "\nЛиния: " + line + extra;

	console.error(out);
	if (url && line != 0) {
		alert(out);
	}

	var suppressErrorAlert = false;
	// If you return true, then error alerts (like in older versions of
	// Internet Explorer) will be suppressed.
	return suppressErrorAlert;
};