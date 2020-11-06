'use strict';

/**
 * @ngdoc service
 * @name armada.AppConfig
 * @description
 *
 * Сервис хранения констант.
 */
angular.module('armada')
	.service('AppConfig', [function () {
		return {
			DEFAULT_MAP_LOCATION: {
				lat: 55.755907,
				lng: 37.628174
			},
			DEFAULT_MAP_ZOOM: 12,

			PLAN_PERIOD_SETTING_ID: 2010,

			CALENDAR_COLORS: [
				"rgb(92, 184, 92)",//weekday
				"rgb(217, 83, 79)",//weekend
				"rgb(240, 137, 78)"//holiday
			],
			EMPLOYEE_CALENDAR_COLORS: {
				HOLIDAY: "#C0C0C0",//holiday
				DAY: "#FFDEAD",//day
				NIGHT: "#2b669a",//night
				NOT_WORK: "#5F9EA0",//not work
				HOSPITAL: "#6495ED",//hospital
				VACATION: "#A0A0A4",//vacation
				FIRST: "#b81900",//first
				SECOND: "#3c763d",//second
				RESERVE: "#FF9900" //"#b81900"//reserve
			},

			PLAN_TASK_CALENDAR_COLORS: {
				PLAN_COLOR: "#31b0d5",
				FORMED_TASK_COLOR: "#5cb85c"
			},

			BRIGADE_COLORS: {
				AGREE: "#5cb85c",
				NOT_FULL: "#FFCCCC",//"#b81900",
				NOT_ENOUGH: "#ff5050",
				HAVE_FREE_CONTROLERS: "#FF9900",
				NOT_FULL_NOT_AGREE: "#66CCCC",//"#EB254e",
				HAVE_FREE_CONTROLERS_NOT_AGREE: "#FFD728"
			},

			FIELD_LENGTHS: {
				X_SHORT_INPUT_SIZE: 32,
				XX_SHORT_INPUT_SIZE: 64,
				SHORT_INPUT_SIZE: 128,
				MIDDLE_INPUT_SIZE: 256,
				LONG_INPUT_SIZE: 512,
				X_LONG_INPUT_SIZE: 1024
			},

			FIELD_PATTERNS: {
				INPUT_NUMERIC_10_2: "^\\d{1,8}(\\.\\d{0,2})?$",
				INPUT_NUMERIC_20_16: "^\\d{1,4}(\\.\\d{0,16})?$",
				INPUT_NUMERIC_10_4: "^\\d{1,6}(\\.\\d{0,4})?$"
			},

			DATE_TIME_FORMAT: 'DD.MM.YYYY HH:mm',

			DAY_TIME_IN_MIL_SEC: 24 * 3600000,
			DELTA_TIME: 100,

			USER_COLORS: {
				INNER_USER: "#5cb85c",
				LDAP_USER: "#5bc0de",
				BLOCKED_USER: "#e97c8e",
				REMOVED_USER: "#dddddd"
			},

			MAP_COLORS: {
				DEPARTMENT: "#245580",
				VENUE: "#ac2925",
				STOP: "#419641",
				STOP_IN_ROUTE: "#e78f08"
			},

			MAP_ICONS: {
				DEPARTMENT: "department",
				VENUE: "venue",
				STOP: "stop",
				STOP_IN_ROUTE: "stop-route"
			},

			MIN_WIDTH_PLAN_COLUMN: 30,

			COLUMN_COUNT: 12
		};
	}]);
