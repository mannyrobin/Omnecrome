angular.module('armada').service("WktParseService", ['AppConfig', function (AppConfig) {
	return {
		wktToGeoJson: function (wktString) {
			var wkt = new Wkt.Wkt();
			wkt.read(wktString);
			return wkt.toJson();
		}
	};
}]);