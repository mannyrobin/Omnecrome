'use strict';

angular
	.module('armada', [
		'ui.router',
		'ngAnimate',
		'restangular',
		'smart-table',
		'ncy-angular-breadcrumb',
		'ui-notification',
		'ui.bootstrap',
		'oi.select',
		'ngCookies',
		'ngMap',
		'yaMap',
		'ui.router.tabs',
		'ui.calendar',
		'LocalStorageModule',
		'xeditable',
		'ngProgress',
		'ui.bootstrap.contextMenu'
	]).config(function ($stateProvider, $urlRouterProvider, RestangularProvider, NotificationProvider, $locationProvider, localStorageServiceProvider, $breadcrumbProvider) {

	$breadcrumbProvider.setOptions({
		includeAbstract: true
	});

	$locationProvider.html5Mode(true);// .hashPrefix('!');

	localStorageServiceProvider
		.setPrefix('armada');

	RestangularProvider.setBaseUrl('api/pbk/');

	RestangularProvider.setDefaultHttpFields({
		withCredentials: true
	});

	var defaultHeaders = {
		"X-Requested-With": "XMLHttpRequest" // this is ajax request
	};

	if (isIe11()) {
		defaultHeaders["If-Modified-Since"] = "Mon, 26 Jul 1997 05:00:00 GMT";
	}

	RestangularProvider.setDefaultHeaders(defaultHeaders);

	function isIe11() {
		var trident = !!navigator.userAgent.match(/Trident\/7.0/);
		var rv = navigator.userAgent.indexOf("rv:11.0");
		return !!(trident && rv != -1);
	}

	NotificationProvider.setOptions({
		delay: 5000,
		startTop: 120,
		startRight: 10,
		verticalSpacing: 20,
		horizontalSpacing: 20,
		positionX: 'right',
		positionY: 'top'
	});

	$stateProvider
		.state('app', {
			abstract: true,
			url: "",
			template: '<ui-view/>',
			ncyBreadcrumb: {
				skip: true
			},
			resolve: {
				checkUserAuth: ['$state', '$timeout', '$cookies', '$rootScope', '$q', 'UserService', function ($state, $timeout, $cookies, $rootScope, $q, UserService) {

					var def = $q.defer();
					var authToken = $cookies.get('authToken');
					if (!angular.isDefined(authToken) && !angular.isDefined($rootScope.userInfo)) {
						def.reject();
						$timeout(function () {
							$state.go("login");
						}, 0);
					}
					if (authToken != undefined && !angular.isDefined($rootScope.userInfo)) {
						$rootScope.authToken = authToken;
						UserService.getUserInfo().then(function (user) {
							$rootScope.userInfo = user;
							UserService.setUser(user);
							def.resolve();
						}).catch(function (error) {
							def.reject();
						});
					}
					if (angular.isDefined($rootScope.userInfo)) {
						def.resolve();
					}
					return def.promise;
				}]
			}
		})
		.state('app.main', {
			url: "/",
			templateUrl: "templates/main.html",
			resolve: {
				authorizeMain: ['UserService', '$timeout', '$state', '$q', 'checkUserAuth', function (UserService, $timeout, $state, $q, checkUserAuth) {
					var def = $q.defer();

					var hasUser = UserService.hasUser(); // UserService.hasAnyRole(['ADMIN',
					// 'SIMPLE_USER',
					// 'DEBUG_TO_REPLACE']);
					if (!hasUser) {
						def.reject();
						$timeout(function () {
							$state.go("forbidden");
						}, 0);
					} else {
						def.resolve();
					}
					return def.promise;
				}]
			},
			ncyBreadcrumb: {
				label: 'Главная'
			}
		})
		.state('app.main.admin', {
			abstract: false,
			url: "^/admin",
			templateUrl: "templates/views/pbk/admin/adminMain.html",
			// template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Администрирование'
			}

		})
		.state('forbidden', {
			url: "/forbidden",
			ncyBreadcrumb: {
				skip: true
			},
			templateUrl: "templates/forbidden.html"
		})

		.state('inDeveloping', {
			url: "/inDeveloping",
			ncyBreadcrumb: {
				skip: true
			},
			templateUrl: "templates/inDeveloping.html"
		})

		.state('app.main.curuser', {
			abstract: true,
			url: "^/current/user",
			template: '<ui-view/>',
			resolve: {
				authorizeCurUser: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['PBK_USERS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}]
			},
			ncyBreadcrumb: {
				skip: true
			},
		})
		.state('app.main.curuser.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "CurrentUserCtrl",
			ncyBreadcrumb: {
				parent: 'app.main',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.curuser.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/admin/current-user/curUserInfo.html",
			controller: "CurrentUserInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.curuser.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Пользователь - Общее",
				description: "Детализация пользователя. Общее."
			}
		})
		.state('app.main.curuser.detail.roles', {
			url: "/roles",
			templateUrl: "templates/views/pbk/admin/current-user/curUserRoles.html",
			controller: "CurrentUserRolesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.curuser.detail',
				label: '{{itemCrumb}} - Роли'
			},
			data: {
				name: "Пользователь - Роли",
				description: "Роли пользователя."
			}
		})

		.state('app.main.admin.users', {
			abstract: true,
			url: "^/admin/users",
			resolve: {
				authorizeUser: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['ADMIN_USER', 'DEBUG_TO_REPLACE']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['ADMIN_USER_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Пользователи',
				skip: true
			},
		})
		.state('app.main.admin.users.list', {
			url: "",
			templateUrl: "templates/views/pbk/admin/users.html",
			controller: "UsersCtrl",
			ncyBreadcrumb: {
				label: 'Пользователи системы'
			},
			data: {
				name: "Пользователи системы",
				description: "Пользователи системы. Страница отображает грид, со списком пользователей."
			}
		})
		.state('app.main.admin.users.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "UserCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.admin.users.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.admin.users.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/admin/userInfo.html",
			controller: "UserInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.admin.users.detail',
				label: '{{itemCrumb}} - Общее'
			},
			resolve: {
				userItem: ['UsersService', '$stateParams', 'authorizeUser', function (UsersService, $stateParams, authorizeUser) {
					return UsersService.getItem($stateParams.itemId).then(function (result) {
						return result;
					});
				}]
			},
			data: {
				name: "Пользователь - Общее",
				description: "Детализация пользователя. Общее."
			}
		})
		.state('app.main.admin.users.detail.roles', {
			url: "/roles",
			templateUrl: "templates/views/pbk/admin/userRoles.html",
			controller: "UserRolesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.admin.users.detail',
				label: '{{itemCrumb}} - Роли'
			},
			resolve: {
				userItem: ['UsersService', '$stateParams', 'authorizeUser', function (UsersService, $stateParams, authorizeUser) {
					return UsersService.getItem($stateParams.itemId).then(function (result) {
						return result;
					});
				}]
			},
			data: {
				name: "Пользователь - Роли",
				description: "Роли пользователя."
			}
		})

		.state('bad-gateway', {
			url: "/bad-gateway",
			templateUrl: "templates/views/pbk/bad-gateway.html",
			ncyBreadcrumb: {
				label: 'Bad Gateway'
			}
		})

		.state('login', {
			url: "/login",
			templateUrl: "templates/views/pbk/login.html",
			controller: "LoginCtrl",
			ncyBreadcrumb: {
				label: 'Страница логина'
			},
			resolve: {
				checkLogin: ['$timeout', '$q', 'UserService', function ($timeout, $q, UserService) {
					var def = $q.defer();
					return UserService.checkDBConnection().then(function () {
						return def.resolve();
					});
				}]
			}
		})

		.state('app.main.admin.settings', {
			abstract: true,
			url: "^/admin/settings",
			resolve: {
				authorizeSetting: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['ADMIN_SETTING', 'DEBUG_TO_REPLACE']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['ADMIN_SETTING_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Настройки',
				skip: true
			}
		})
		.state('app.main.admin.settings.detail', {
			url: "",
			templateUrl: "templates/tabs.html",
			controller: "SettingsMainCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.admin.settings',
				label: 'Настройки',
				skip: true
			}
		})
		.state('app.main.admin.settings.detail.common', {
			url: "/common",
			templateUrl: "templates/views/pbk/admin/settings.html",
			controller: "SettingsCtrl",
			params: {
				itemId: "1"
			},
			ncyBreadcrumb: {
				parent: 'app.main.admin.settings.detail',
				label: 'Настройки - общие'
			}
		})
		.state('app.main.admin.settings.detail.vis', {
			url: "/vis",
			templateUrl: "templates/views/pbk/admin/settings.html",
			controller: "SettingsCtrl",
			params: {
				itemId: "6"
			},
			ncyBreadcrumb: {
				parent: 'app.main.admin.settings.detail',
				label: 'Настройки - ВИС'
			}
		})
		.state('app.main.admin.settings.detail.ad', {
			url: "/ad",
			templateUrl: "templates/views/pbk/admin/settings.html",
			controller: "SettingsCtrl",
			params: {
				itemId: "4"
			},
			ncyBreadcrumb: {
				parent: 'app.main.admin.settings.detail',
				label: 'Настройки - Active Directory'
			}
		})

		.state('app.main.admin.audit', {
			url: "^/admin/audit",
			abstract: true,
			template: '<ui-view/>',
			resolve: {
				authorizeAudit: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['ADMIN_AUDIT', 'DEBUG_TO_REPLACE']);
					return createPromise(hasRole, $state, $timeout, $q);
				}]
			},
			ncyBreadcrumb: {
				label: 'Журнал действий',
				skip: true
			}
		})
		.state('app.main.admin.audit.list', {
			url: "",
			templateUrl: "templates/views/pbk/admin/audits.html",
			controller: "AuditsCtrl",
			ncyBreadcrumb: {
				label: 'Журнал аудита'
			}
		})
		.state('app.main.admin.audit-logs', {
			url: "^/admin/audit-logs",
			abstract: true,
			template: '<ui-view/>',
			resolve: {
				authorizeAudit: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['ADMIN_AUDIT', 'DEBUG_TO_REPLACE']);
					return createPromise(hasRole, $state, $timeout, $q);
				}]
			},
			ncyBreadcrumb: {
				label: 'Журнал очистки',
				skip: true
			}
		})
		.state('app.main.admin.audit-logs.list', {
			url: "",
			templateUrl: "templates/views/pbk/admin/audit-logs.html",
			controller: "AuditLogsCtrl",
			ncyBreadcrumb: {
				label: 'Журнал очистки'
			}
		})
		.state('app.main.admin.audit.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "AuditCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.admin.audit.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.admin.audit.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/admin/auditInfo.html",
			controller: "AuditInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.admin.audit.detail',
				label: '{{itemCrumb}} - Общее'
			},

			data: {
				name: "Запись аудита - Общее",
				description: "Детализация записи аудита. Общее."
			}
		})

		.state('app.main.admin.audit-types', {
			abstract: true,
			url: "^/admin/audit-types",
			resolve: {
				authorizeAuditTypes: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['ADMIN_AUDIT_TYPES', 'DEBUG_TO_REPLACE']);
					return createPromise(hasRole, $state, $timeout, $q);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Справочник действий',
				skip: true
			},
		})
		.state('app.main.admin.audit-types.list', {
			url: "",
			templateUrl: "templates/views/pbk/admin/audit-types/audit-types.html",
			controller: "AuditTypesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.admin.audit-types',
				label: 'Справочник действий'
			}
		})
		.state('app.main.admin.audit-types.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "AuditTypeCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.admin.audit-types.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.admin.audit-types.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/admin/audit-types/audit-typeInfo.html",
			controller: "AuditTypeInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.admin.audit-types.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "События Аудита - Общее",
				description: "Детализация события Аудита. Общее."
			}
		})
		.state('app.main.admin.version', {
			url: "^/admin/version",
			templateUrl: "templates/views/pbk/admin/versionInfo.html",
			controller: "VersionInfoCtrl",
			ncyBreadcrumb: {
				label: 'Информация о сборке'
			}
		})
		.state('app.main.admin.roles', {
			abstract: true,
			url: "^/admin/roles",
			resolve: {
				authorizeRoles: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['ADMIN_ROLE', 'DEBUG_TO_REPLACE']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['ADMIN_ROLE_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Роли',
				skip: true
			}
		})
		.state('app.main.admin.roles.list', {
			url: "",
			templateUrl: "templates/views/pbk/admin/roles.html",
			controller: "RolesCtrl",
			ncyBreadcrumb: {
				label: 'Роли пользователя'
			}
		})

		.state('app.main.admin.roles.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "RoleCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.admin.roles.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.admin.roles.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/admin/roleInfo.html",
			controller: "RoleInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.admin.roles.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Роль - Общее",
				description: "Детализация роли. Общее."
			}
		})
		.state('app.main.admin.roles.detail.permissions', {
			url: "/permissions",
			templateUrl: "templates/views/pbk/admin/rolePermissions.html",
			controller: "RolePermissionCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.admin.roles.detail',
				label: '{{itemCrumb}} - Права'
			},
			data: {
				name: "Роль - Права",
				description: "Права роли."
			}
		})

		.state('app.main.pbk', {
			abstract: false,
			url: "^/pbk",
			templateUrl: "templates/views/pbk/pbk/pbkMain.html",
			// template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Билетный контроль'
			}
		})

		/* PBK plans */
		.state('app.main.pbk.plans', {
			abstract: true,
			url: "/plans",
			resolve: {
				authorizePbkPlans: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'PBK_PLANS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['PBK_PLANS_EDIT', 'DEBUG_TO_REPLACE']);
				}],
				isBrigadeEditRole: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['PBK_PLAN_BRIGADE_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Планирование ПБК',
				skip: true
			}
		})
		.state('app.main.pbk.plans.list', {
			url: "",
			templateUrl: "templates/views/pbk/pbk/plans/plans.html",
			controller: "PlansCtrl",
			params: {
				forPlanUse: "1"
			},
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans',
				label: 'Планирование ПБК'
			}
		})
		.state('app.main.pbk.plans.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "PlanCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.pbk.plans.detail.schedules', {
			url: "/schedules",
			templateUrl: "templates/views/pbk/pbk/plans/plan-schedules.html",
			controller: "PlanSchedulesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.detail',
				label: '{{itemCrumb}} - Расписание'
			},
			data: {
				name: "Планирование ПБК - Расписание",
				description: "Детализация планирования ПБК. Расписание."
			}
		})
		.state('app.main.pbk.plans.detail.brigades', {
			url: "/brigades",
			templateUrl: "templates/views/pbk/pbk/plans/plan-brigades.html",
			controller: "PlanBrigadesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.detail',
				label: '{{itemCrumb}} - Бригады'
			},
			data: {
				name: "Планирование ПБК - Бригады",
				description: "Детализация планирования ПБК. Бригады."
			}
		})
		.state('app.main.pbk.plans.detail.tasks-table', {
			url: "/tasks-table",
			templateUrl: "templates/views/pbk/pbk/plans/plan-tasks-table.html",
			controller: "PlanTasksCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.detail',
				label: 'Задания (таблица)'
			},
			data: {
				name: "Планирование ПБК - Задания",
				description: "Детализация планирования ПБК. Задания."
			}
		})

		.state('app.main.pbk.plans.detail.task-detail', {
			url: "/task/:taskId",
			templateUrl: "templates/tabs.html",
			controller: "PlanTaskCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.detail.tasks-table',
				label: '{{itemCrumb}}',
				skip: true
			}
		})

		.state('app.main.pbk.plans.detail.task-detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/pbk/tasks/taskInfo.html",
			controller: "TaskInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.detail.task-detail',
				label: 'Общее - {{itemCrumb}}'
			},
			resolve: {
				curTaskItem: ['TasksService', '$stateParams', function (TasksService, $stateParams) {
					return TasksService.getItem($stateParams.taskId).then(function (result) {
						return result;
					});
				}]
			}
			,
			data: {
				name: "Задание - Общее",
				description: "Детализация задания. Общее."
			}
		})
		.state('app.main.pbk.plans.detail.task-detail.report', {
			url: "/report",
			templateUrl: "templates/views/pbk/pbk/tasks/taskReport.html",
			controller: "TaskReportInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.detail.task-detail',
				label: 'Краткий отчет - {{itemCrumb}}'
			},
			data: {
				name: "Задание - Краткий отчет",
				description: "Детализация задания. Краткий отчет."
			}
		})
		.state('app.main.pbk.plans.detail.task-detail.askp-pusk-checks-routes', {
			url: "/askp-pusk-checks-routes",
			templateUrl: "templates/views/pbk/pbk/tasks/taskAskpPuskChecksByRoutes.html",
			controller: "TaskAskpPuskChecksByRoutesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.detail.task-detail',
				label: 'АСКП по маршрутам - {{itemCrumb}}'
			},
			data: {
				name: "Задание - АСКП по маршрутам",
				description: "Детализация задания. АСКП по маршрутам."
			}
		})
		.state('app.main.pbk.plans.detail.task-detail.askp-pusk-checks-hours', {
			url: "/askp-pusk-checks-hours",
			templateUrl: "templates/views/pbk/pbk/tasks/taskAskpPuskChecksByHours.html",
			controller: "TaskAskpPuskChecksByHoursCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.detail.task-detail',
				label: 'АСКП по часам - {{itemCrumb}}'
			},
			data: {
				name: "Задание - АСКП по часам",
				description: "Детализация задания. АСКП по часам."
			}
		})
		.state('app.main.pbk.plans.detail.task-detail.used-bsos', {
			url: "/used-bsos",
			templateUrl: "templates/views/pbk/pbk/tasks/taskUsedBsos.html",
			controller: "TaskUsedBsosCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.detail.task-detail',
				label: 'Использованные БСО - {{itemCrumb}}'
			},
			data: {
				name: "Задание - Использованные БСО",
				description: "Детализация задания. Использованные БСО."
			}
		})
		.state('app.main.pbk.plans.detail.task-detail.files', {
			url: "/files",
			templateUrl: "templates/views/pbk/pbk/tasks/taskFiles.html",
			controller: "TaskFilesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.detail.task-detail',
				label: 'Файлы задания - {{itemCrumb}}'
			},
			data: {
				name: "Задание - Файлы",
				description: "Детализация задания. Файлы."
			}
		})
		.state('app.main.pbk.plans.detail.task-detail.withdrawn-cards', {
			url: "/withdrawn-cards",
			templateUrl: "templates/views/pbk/pbk/tasks/taskWithdrawnCards.html",
			controller: "TaskWithdrawnCardsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.detail.task-detail',
				label: 'Карты к изъятию - {{itemCrumb}}'
			},
			data: {
				name: "Задание - Карты к изъятию",
				description: "Детализация задания. Карты к изъятию."
			}
		})
		.state('app.main.pbk.plans.detail.task-detail.routes', {
			url: "/routes",
			templateUrl: "templates/views/pbk/pbk/tasks/tasksRoutes.html",
			controller: "TaskRoutesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.detail.task-detail',
				label: 'Маршруты - {{itemCrumb}}'
			},
			data: {
				name: "Задание - Маршруты",
				description: "Детализация задания. Маршруты."
			}
		})
		.state('app.main.pbk.plans.detail.task-detail.contactless-card', {
			url: "/contactless-card",
			templateUrl: "templates/views/pbk/pbk/tasks/taskAskpContactlessCards.html",
			controller: "TaskAskpContactlessCardsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.detail.task-detail',
				label: 'Проходы по БСК контролера - {{itemCrumb}}'
			},
			data: {
				name: "Задание - Проходы по БСК контролера",
				description: "Детализация задания. Проходы по БСК контролера."
			}, resolve: {
				taskWithShift: ['TasksService', '$stateParams', function (TasksService, $stateParams) {
					return TasksService.getTaskWithShift($stateParams.taskId).then(function (response) {
						return response;
					});
				}]
			}
		})

		.state('app.main.pbk.plans.detail.timesheets', {
			url: "/timesheets",
			templateUrl: "templates/views/pbk/pbk/plans/plan-timesheets.html",
			controller: "PlanTimesheetsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.plans.detail',
				label: '{{itemCrumb}} - Табель'
			},
			data: {
				name: "Планирование ПБК - Табель",
				description: "Детализация планирования ПБК. Табель."
			}
		})
		.state('app.main.pbk.bonuses', {
			url: "/bonuses",
			abstract: true,
			template: '<ui-view/>',
			controller: "BonusesCtrl",
			ncyBreadcrumb: {
				label: 'Премирование',
				skip: true
			},
			resolve: {
				authorizeBonuses: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'BONUSES']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['BONUSES_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			}
		})
		.state('app.main.pbk.bonuses.list', {
			url: "",
			templateUrl: "templates/views/pbk/pbk/bonuses/bonuses.html",
			controller: "BonusesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.bonuses',
				label: 'Премирование'
			}
		})
		.state('app.main.pbk.bonuses.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "BonusCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.bonuses.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.pbk.bonuses.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/pbk/bonuses/bonusInfo.html",
			controller: "BonusInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.bonuses.detail',
				label: 'Общее - {{itemCrumb}}'
			},
			data: {
				name: "Премирование - Общее",
				description: "Детализация премирования. Общее."
			}
		})

		.state('app.main.pbk.routes-ratio', {
			abstract: true,
			url: "/routes-ratio",
			resolve: {
				authorizeDuties: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'ROUTES_RATIO']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['ROUTES_RATIO_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Рейтинги маршрутов',
				skip: true
			}
		})
		.state('app.main.pbk.routes-ratio.list', {
			url: "",
			templateUrl: "templates/views/pbk/pbk/plans/plan-routes.html",
			controller: "RouteRatiosCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.routes-ratio',
				label: 'Рейтинги маршрутов'
			}
		})
		.state('app.main.pbk.withdrawn-cards', {
			url: "/withdrawn-cards",
			abstract: true,
			template: '<ui-view/>',
			resolve: {
				authorizePbkTasks: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'WITHDRAWN_CARDS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['WITHDRAWN_CARDS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			ncyBreadcrumb: {
				label: 'Изъятые проездные документы',
				skip: true
			}
		})
		.state('app.main.pbk.withdrawn-cards.list', {
			url: "",
			templateUrl: "templates/views/pbk/pbk/tasks/taskWithdrawnCards.html",
			controller: "TasksWithdrawnCardsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.withdrawn-cards',
				label: 'Изъятые проездные документы'
			}
		})
		.state('app.main.pbk.withdrawn-cards.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "TaskWithdrawnCardCtrl",
			resolve: {
				withdrawnCard: ['TaskWithdrawnCardsService', 'authorizePbkTasks', '$stateParams',
					function (TaskWithdrawnCardsService, authorizePbkTasks, $stateParams) {
						return TaskWithdrawnCardsService.getItem($stateParams.itemId).then(function (result) {
							return result;
						});
					}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.pbk.withdrawn-cards.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.pbk.withdrawn-cards.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/pbk/tasks/taskWithdrawnCardInfo.html",
			controller: "TaskWithdrawnCardInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.withdrawn-cards.detail',
				label: 'Общее - {{itemCrumb}}'
			},
			data: {
				name: "Изъятые проездные документы - Общее",
				description: "Детализация изъятого проездного документа. Общее."
			}
		})

		.state('app.main.pbk.tasks', {
			url: "/tasks",
			abstract: true,
			template: '<ui-view/>',
			controller: "TasksCtrl",
			ncyBreadcrumb: {
				label: 'Задания',
				skip: true
			},
			resolve: {
				authorizePbkTasks: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'PBK_TASKS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['PBK_TASKS_EDIT', 'DEBUG_TO_REPLACE']);
				}],
				isCreateTasks: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['CREATE_TASKS', 'DEBUG_TO_REPLACE']);
				}]
			}
		})
		.state('app.main.pbk.tasks.list', {
			url: "",
			templateUrl: "templates/views/pbk/pbk/tasks/tasks.html",
			controller: "TasksCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.tasks',
				label: 'Задания'
			}
		})
		.state('app.main.pbk.tasks.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "TaskCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.tasks.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.pbk.tasks.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/pbk/tasks/taskInfo.html",
			controller: "TaskInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.tasks.detail',
				label: 'Общее - {{itemCrumb}}'
			},
			resolve: {
				curTaskItem: ['TasksService', '$stateParams', function (TasksService, $stateParams) {
					return TasksService.getItem($stateParams.itemId).then(function (result) {
						return result;
					});
				}]
			}
			,
			data: {
				name: "Задание - Общее",
				description: "Детализация задания. Общее."
			}
		})
		.state('app.main.pbk.tasks.detail.report', {
			url: "/report",
			templateUrl: "templates/views/pbk/pbk/tasks/taskReport.html",
			controller: "TaskReportInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.tasks.detail',
				label: 'Краткий отчет - {{itemCrumb}}'
			},
			data: {
				name: "Задание - Краткий отчет",
				description: "Детализация задания. Краткий отчет."
			}
		})
		.state('app.main.pbk.tasks.detail.askp-pusk-checks-routes', {
			url: "/askp-pusk-checks-routes",
			templateUrl: "templates/views/pbk/pbk/tasks/taskAskpPuskChecksByRoutes.html",
			controller: "TaskAskpPuskChecksByRoutesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.tasks.detail',
				label: 'АСКП по маршрутам - {{itemCrumb}}'
			},
			data: {
				name: "Задание - АСКП по маршрутам",
				description: "Детализация задания. АСКП по маршрутам."
			}
		})
		.state('app.main.pbk.tasks.detail.askp-pusk-checks-hours', {
			url: "/askp-pusk-checks-hours",
			templateUrl: "templates/views/pbk/pbk/tasks/taskAskpPuskChecksByHours.html",
			controller: "TaskAskpPuskChecksByHoursCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.tasks.detail',
				label: 'АСКП по часам - {{itemCrumb}}'
			},
			data: {
				name: "Задание - АСКП по часам",
				description: "Детализация задания. АСКП по часам."
			}
		})
		.state('app.main.pbk.tasks.detail.used-bsos', {
			url: "/used-bsos",
			templateUrl: "templates/views/pbk/pbk/tasks/taskUsedBsos.html",
			controller: "TaskUsedBsosCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.tasks.detail',
				label: 'Использованные БСО - {{itemCrumb}}'
			},
			data: {
				name: "Задание - Использованные БСО",
				description: "Детализация задания. Использованные БСО."
			}
		})
		.state('app.main.pbk.tasks.detail.files', {
			url: "/files",
			templateUrl: "templates/views/pbk/pbk/tasks/taskFiles.html",
			controller: "TaskFilesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.tasks.detail',
				label: 'Файлы задания - {{itemCrumb}}'
			},
			data: {
				name: "Задание - Файлы",
				description: "Детализация задания. Файлы."
			}
		})
		.state('app.main.pbk.tasks.detail.withdrawn-cards', {
			url: "/withdrawn-cards",
			templateUrl: "templates/views/pbk/pbk/tasks/taskWithdrawnCards.html",
			controller: "TaskWithdrawnCardsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.tasks.detail',
				label: 'Карты к изъятию - {{itemCrumb}}'
			},
			data: {
				name: "Задание - Карты к изъятию",
				description: "Детализация задания. Карты к изъятию."
			}
		})
		.state('app.main.pbk.tasks.detail.routes', {
			url: "/routes",
			templateUrl: "templates/views/pbk/pbk/tasks/tasksRoutes.html",
			controller: "TaskRoutesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.tasks.detail',
				label: 'Маршруты - {{itemCrumb}}'
			},
			data: {
				name: "Задание - Маршруты",
				description: "Детализация задания. Маршруты."
			}
		})
		.state('app.main.pbk.task-files', {
			abstract: true,
			url: "^/pbk/task-files",
			resolve: {
				authorizeSetting: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['PBK_TASKS', 'DEBUG_TO_REPLACE']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['PBK_TASKS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			ncyBreadcrumb: {
				label: 'Файлы',
				skip: true
			}
		})
		.state('app.main.pbk.tasks.detail.contactless-card', {
			url: "/contactless-card",
			templateUrl: "templates/views/pbk/pbk/tasks/taskAskpContactlessCards.html",
			controller: "TaskAskpContactlessCardsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.pbk.tasks.detail',
				label: 'Проходы по БСК контролера - {{itemCrumb}}'
			},
			data: {
				name: "Задание - Проходы по БСК контролера",
				description: "Детализация задания. Проходы по БСК контролера."
			}, resolve: {
				taskWithShift: ['TasksService', '$stateParams', function (TasksService, $stateParams) {
					return TasksService.getTaskWithShift($stateParams.itemId).then(function (response) {
						return response;
					});
				}]
			}
		})

		.state('app.main.sitemap', {
			url: "^/sitemap",
			templateUrl: "templates/sitemap.html",
			controller: "SitemapCtrl",
			ncyBreadcrumb: {
				label: 'Карта сайта'
			},
			data: {
				name: "Карта сайта",
				description: "Список всех доступных страниц подсистемы ПБК"
			}
		})
		.state('app.main.nsi', {
			abstract: false,
			url: "^/nsi",
			// template: '<ui-view/>',
			templateUrl: "templates/views/pbk/nsi/nsiMain.html",
			ncyBreadcrumb: {
				label: 'НСИ'
			},
		})
		.state('app.main.nsi.employee', {
			url: "/employee",
			abstract: false,
			templateUrl: "templates/views/pbk/nsi/employeesMain.html",
			ncyBreadcrumb: {
				label: 'Сотрудники'
			}
		})
		.state('app.main.nsi.plan', {
			url: "/planning",
			abstract: false,
			templateUrl: "templates/views/pbk/nsi/planningMain.html",
			ncyBreadcrumb: {
				label: 'Планирование'
			}
		})
		.state('app.main.nsi.route', {
			url: "",
			abstract: false,
			ncyBreadcrumb: {
				label: 'Маршруты'
			}
		})
		.state('app.main.nsi.bso', {
			url: "/bso",
			abstract: false,
			templateUrl: "templates/views/pbk/nsi/bsoMain.html",
			ncyBreadcrumb: {
				label: 'БСО',
				parent: 'app.main.nsi.plan'
			}
		})
		.state('app.main.nsi.employee.facility', {
			url: "/facility",
			templateUrl: "templates/views/pbk/nsi/facilityMain.html",
			abstract: false,
			ncyBreadcrumb: {
				label: 'Персональные устройства и карты'
			}
		})
		.state('app.main.nsi.ts', {
			url: "",
			abstract: false,
			ncyBreadcrumb: {
				label: 'Транспортные средства'
			}
		})
		.state('app.main.nsi.other', {
			url: "/other",
			abstract: false,
			templateUrl: "templates/views/pbk/nsi/otherMain.html",
			ncyBreadcrumb: {
				label: 'Прочее'
			}
		})
		.state('app.main.nsi.bso-number-rules', {
			url: "/bso-number-rules",
			abstract: true,
			template: '<ui-view/>',
			templateUrl: "templates/views/pbk/nsi/bso-number-rules/bso-number-rules.html",
			controller: "BsoNumberRulesCtrl",
			resolve: {
				authorizeBSONumbers: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'BSO_NUMBERS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['BSO_NUMBERS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.nsi.bso',
				label: 'Правила формирования номеров БСО',
				skip: true
			}
		})
		.state('app.main.nsi.bso-number-rules.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/bso-number-rules/bso-number-rules.html",
			controller: "BsoNumberRulesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.bso-number-rules',
				label: 'Правила формирования номера БСО'
			}
		})
		.state('app.main.nsi.bso-number-rules.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "BsoNumberRuleCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.bso-number-rules.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.bso-number-rules.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/bso-number-rules/bso-number-ruleInfo.html",
			controller: "BsoNumberRuleInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.bso-number-rules.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Правило формирования номера БСО - Общее",
				description: "Детализация правила формирования номера БСО. Общее."
			}
		})
		.state('app.main.nsi.bsos', {
			url: "/bsos",
			abstract: true,
			template: '<ui-view/>',
			templateUrl: "templates/views/pbk/nsi/bsos/bsos.html",
			controller: "BsosCtrl",
			resolve: {
				authorizeBSO: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'BSO']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['BSO_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			ncyBreadcrumb: {
				label: 'БСО',
				parent: 'app.main.pbk',
				skip: true
			}
		})
		.state('app.main.nsi.bsos.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/bsos/bsos.html",
			controller: "BsosCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.bsos',
				label: 'Журнал БСО'
			}
		})
		.state('app.main.nsi.departments', {
			abstract: true,
			url: "/departments",
			resolve: {
				authorizeDepartments: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'DEPARTMENTS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['DEPARTMENTS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Подразделения',
				skip: true
			}
		})
		.state('app.main.nsi.departments.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/departments/departments.html",
			controller: "DepartmentsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.departments',
				label: 'Подразделения'
			}
		})
		.state('app.main.nsi.departments.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "DepartmentCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.departments.list',
				label: '{{itemCrumb}}',
				skip: true
			},
			resolve: {
				forPlanUse: ['DepartmentsService', '$stateParams', function (DepartmentsService, $stateParams) {
					return DepartmentsService.getItem($stateParams.itemId).then(
						function (item) {
							return item.forPlanUse;
						});

				}]

			}
		})
		.state('app.main.nsi.departments.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/departments/departmentInfo.html",
			controller: "DepartmentInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.departments.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Подразделения - Общее",
				description: "Детализация подразделения. Общее."
			}
		})

		.state('app.main.nsi.departments.detail.location', {
			url: "/location",
			templateUrl: "templates/views/pbk/nsi/departments/departmentLocation.html",
			controller: "DepartmentLocationCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.departments.detail',
				label: '{{itemCrumb}} - Местоположение'
			},
			data: {
				name: "Остановочные пункты - Подразделения",
				description: "Детализация подразделения. Местоположение."
			}
		})

		.state('app.main.nsi.departments.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/departments/department-history.html",
			controller: "DepartmentHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.departments.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Подразделение - История",
				description: "Детализация подразделения. История."
			}
		})

		.state('app.main.nsi.departments.detail.rules', {
			url: "/rules",
			abstract: true,
			template: '<ui-view/>',
			resolve: {
				authorizeBSONumbers: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'BSO_NUMBERS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['BSO_NUMBERS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.nsi.departments.detail',
				label: '{{itemCrumb}} - Правила формирования БСО',
				skip: true
			}
		})

		.state('app.main.nsi.departments.detail.rules.list', {
			url: "/list",
			templateUrl: "templates/views/pbk/nsi/bso-number-rules/bso-number-rules.html",
			controller: "DepartmentRulesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.departments.detail.rules',
				label: '{{itemCrumb}} - Правила формирования БСО'
			},
			data: {
				name: "Подразделение - Правила формирования БСО",
				description: "Детализация подразделения. Правила формирования БСО."
			}
		})

		.state('app.main.nsi.departments.detail.rules.detail', {
			url: "/:ruleId",
			templateUrl: ""
		})

		.state('app.main.nsi.departments.detail.rules.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/bso-number-rules/bso-number-ruleInfo.html",
			controller: "DepartmentRuleInfoCtrl",
			data: {
				name: "Правило формирования номера БСО - Общее",
				description: "Детализация правила формирования номера БСО. Общее."
			}
		})

		.state('app.main.nsi.gkuops', {
			abstract: true,
			url: "/gkuops",
			resolve: {
				authorizeGkuops: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'GKUOPS_EMPLOYEES']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['GKUOPS_EMPLOYEES_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				parent: 'app.main.nsi.employee',
				label: 'Сотрудники ГКУ ОП',
				skip: true
			}
		})
		.state('app.main.nsi.gkuops.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/gkuops/gkuops.html",
			controller: "GkuopsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.gkuops',
				label: 'Сотрудники ГКУ ОП'
			}
		})
		.state('app.main.nsi.gkuops.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "GkuopCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.gkuops.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.gkuops.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/gkuops/gkuopInfo.html",
			controller: "GkuopInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.gkuops.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Сотрудники ГКУ ОП - Общее",
				description: "Детализация сотрудников ГКУ ОП. Общее."
			}
		})
		.state('app.main.nsi.gkuops.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/gkuops/gkuopHistory.html",
			controller: "GkuopHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.gkuops.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Сотрудники ГКУ ОП - История",
				description: "Детализация сотрудников ГКУ ОП. История."
			}
		})

		.state('app.main.nsi.task-report-fields', {
			abstract: true,
			url: "/task-report-fields",
			resolve: {
				authorizeSex: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'OPERATION_TYPES']);
					return createPromise(hasRole, $state, $timeout, $q);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Пол',
				skip: true,
				parent: 'app.main.nsi.employee'
			}
		})
		.state('app.main.nsi.task-report-fields.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/task-report-field/task-report-fields.html",
			controller: "TaskReportFieldsCtrl",
			ncyBreadcrumb: {
				label: 'Виды операций, выполняемые контролерами в рамках проведения билетного контроля'
			}
		})
		.state('app.main.nsi.task-report-fields.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "TaskReportFieldCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.task-report-fields.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.task-report-fields.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/task-report-field/task-report-fieldInfo.html",
			controller: "TaskReportFieldInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.task-report-fields.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Пол - Общее",
				description: "Детализация пола. Общее."
			}
		})

		.state('app.main.nsi.sexes', {
			abstract: true,
			url: "/sexes",
			resolve: {
				authorizeSex: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'EMPLOYEES_SEX']);
					return createPromise(hasRole, $state, $timeout, $q);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Пол',
				skip: true,
				parent: 'app.main.nsi.employee'
			}
		})
		.state('app.main.nsi.sexes.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/sex/sex.html",
			controller: "SexesCtrl",
			ncyBreadcrumb: {
				label: 'Пол'
			}
		})
		.state('app.main.nsi.sexes.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "SexCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.sexes.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.sexes.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/sex/sexInfo.html",
			controller: "SexInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.sexes.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Пол - Общее",
				description: "Детализация пола. Общее."
			}
		})

		.state('app.main.nsi.parks', {
			abstract: true,
			url: "/parks",
			resolve: {
				authorizeParks: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'PARKS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['PARKS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Эксплуатационные филиалы',
				skip: true,
				parent: 'app.main.nsi.other'
			}
		})
		.state('app.main.nsi.parks.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/parks/parks.html",
			controller: "ParksCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.parks',
				label: 'Эксплуатационные филиалы'
			}
		})
		.state('app.main.nsi.parks.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "ParkCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.parks.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.parks.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/parks/parkInfo.html",
			controller: "ParkInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.parks.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Эксплуатационный филиал - Общее",
				description: "Детализация эксплуатационного филиала. Общее."
			}
		})
		.state('app.main.nsi.parks.detail.drivers', {
			url: "/drivers",
			templateUrl: "templates/views/pbk/nsi/parks/parkDrivers.html",
			controller: "ParkDriversCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.parks.detail',
				label: '{{itemCrumb}} - Водители'
			},
			data: {
				name: "Эксплуатационные филиалы - Водители",
				description: "Детализация эксплуатационного филиала. Водители."
			}
		})
		.state('app.main.nsi.parks.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/parks/park-history.html",
			controller: "ParkHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.parks.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Эксплуатационный филиал - История",
				description: "Детализация эксплуатационного филиала. История."
			}
		})

		.state('app.main.nsi.districts', {
			abstract: true,
			url: "/districts",
			resolve: {
				authorizeDistricts: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'DISTRICTS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['DISTRICTS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Районы Москвы',
				skip: true,
				parent: 'app.main.nsi.other'
			}
		})
		.state('app.main.nsi.districts.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/districts.html",
			controller: "DistrictsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.districts',
				label: 'Районы Москвы'
			}
		})
		.state('app.main.nsi.districts.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "DistrictCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.districts.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.districts.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/districts/districtInfo.html",
			controller: "DistrictInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.districts.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Районы Москвы - Общее",
				description: "Детализация района Москвы. Общее."
			}
		})
		.state('app.main.nsi.districts.detail.routes', {
			url: "/routes",
			templateUrl: "templates/views/pbk/nsi/districts/districtRoutes.html",
			controller: "DistrictRoutesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.districts.detail',
				label: '{{itemCrumb}} - Маршруты'
			},
			data: {
				name: "Районы Москвы - Маршруты",
				description: "Детализация района Москвы. Маршруты."
			}
		})
		.state('app.main.nsi.districts.detail.venues', {
			url: "/venues",
			templateUrl: "templates/views/pbk/nsi/districts/districtVenues.html",
			controller: "DistrictVenuesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.districts.detail',
				label: '{{itemCrumb}} - Места встреч'
			},
			data: {
				name: "Районы Москвы - Места встреч",
				description: "Детализация района Москвы. Места встреч."
			}
		})
		.state('app.main.nsi.districts.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/districts/districtHistory.html",
			controller: "DistrictHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.districts.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Районы Москвы - История",
				description: "Детализация района Москвы. История."
			}
		})

		.state('app.main.nsi.tickets', {
			abstract: true,
			url: "/tickets",
			resolve: {
				authorizeTickets: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'TICKETS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['TICKETS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Виды билетов',
				skip: true,
				parent: 'app.main.nsi.plan'
			}
		})
		.state('app.main.nsi.tickets.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/tickets/tickets.html",
			controller: "TicketsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.tickets',
				label: 'Билеты'
			}
		})
		.state('app.main.nsi.tickets.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "TicketCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.tickets.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.tickets.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/tickets/ticketInfo.html",
			controller: "TicketInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.tickets.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Билеты - Общее",
				description: "Детализация билета. Общее."
			}
		})
		.state('app.main.nsi.tickets.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/tickets/ticketHistory.html",
			controller: "TicketHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.tickets.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Билеты - История",
				description: "Детализация билета. История."
			}
		})

		/* NSI route-ts-kinds */
		.state('app.main.nsi.route-ts-kinds', {
			abstract: true,
			url: "/route-ts-kinds",
			resolve: {
				authorizeRouteTsKinds: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'ROUTE_TS_KINDS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['ROUTE_TS_KINDS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Виды транспорта маршрута',
				skip: true,
				parent: 'app.main.nsi.other'
			}
		})
		.state('app.main.nsi.route-ts-kinds.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/route-ts-kinds/route-ts-kinds.html",
			controller: "RouteTsKindsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.route-ts-kinds',
				label: 'Виды транспорта маршрута'
			}
		})
		.state('app.main.nsi.route-ts-kinds.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "RouteTsKindCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.route-ts-kinds.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.route-ts-kinds.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/route-ts-kinds/route-ts-kindInfo.html",
			controller: "RouteTsKindInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.route-ts-kinds.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Виды транспорта маршрута - Общее",
				description: "Детализация вида транспорта маршрута. Общее."
			}
		})
		/* NSI official-cards */
		.state('app.main.nsi.official-cards', {
			abstract: true,
			url: "/official-cards",
			resolve: {
				authorizeOfficialCards: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'OFFICIAL_CARDS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['OFFICIAL_CARDS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Служебные карты контролера',
				skip: true,
				parent: 'app.main.nsi.employee.facility'
			}
		})
		.state('app.main.nsi.official-cards.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/official-cards/official-cards.html",
			controller: "OfficialCardsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.official-cards',
				label: 'Служебные карты контролера'
			}
		})
		.state('app.main.nsi.official-cards.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "OfficialCardCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.official-cards.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.official-cards.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/official-cards/official-cardInfo.html",
			controller: "OfficialCardInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.official-cards.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Служебная карта контролера - Общее",
				description: "Детализация служебной карты контролера. Общее."
			}
		})
		.state('app.main.nsi.official-cards.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/official-cards/official-card-history.html",
			controller: "OfficialCardHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.official-cards.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Служебная карта контролера - История",
				description: "Детализация служебной карты контролера. История."
			}
		})
		.state('app.main.nsi.official-cards.detail.ownershistory', {
			url: "/ownershistory",
			templateUrl: "templates/views/pbk/nsi/official-cards/official-card-owners-history.html",
			controller: "OfficialCardOwnersHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.official-cards.detail',
				label: '{{itemCrumb}} - История смены владельцев'
			},
			data: {
				name: "Служебная карта контролера - История смены владельцев",
				description: "Детализация служебной карты контролера. История смены владельцев."
			}
		})


		/* NSI pusks */
		.state('app.main.nsi.pusks', {
			abstract: true,
			url: "/pusks",
			resolve: {
				authorizePusks: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'PUSKS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['PUSKS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'ПУсК',
				skip: true,
				parent: 'app.main.nsi.employee.facility'
			}
		})
		.state('app.main.nsi.pusks.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/pusks/pusks.html",
			controller: "PusksCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.pusks',
				label: 'ПУсК'
			}
		})
		.state('app.main.nsi.pusks.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "PuskCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.pusks.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.pusks.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/pusks/puskInfo.html",
			controller: "PuskInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.pusks.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "ПУсК - Общее",
				description: "Детализация ПУсК. Общее."
			}
		})

		.state('app.main.nsi.pusks.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/pusks/pusk-history.html",
			controller: "PuskHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.pusks.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "ПУсК - История",
				description: "Детализация ПУсК. История."
			}
		})
		.state('app.main.nsi.pusks.detail.ownershistory', {
			url: "/ownershistory",
			templateUrl: "templates/views/pbk/nsi/pusks/pusk-owners-history.html",
			controller: "PuskOwnersHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.pusks.detail',
				label: '{{itemCrumb}} - История владельцев'
			},
			data: {
				name: "ПУсК - История",
				description: "Детализация ПУсК. История владельцев."
			}
		})

		/* NSI dvrs */
		.state('app.main.nsi.dvrs', {
			abstract: true,
			url: "/dvrs",
			resolve: {
				authorizeDvrs: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'DVRS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['DVRS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Видеорегистраторы',
				skip: true,
				parent: 'app.main.nsi.employee.facility'
			}
		})
		.state('app.main.nsi.dvrs.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/dvrs/dvrs.html",
			controller: "DvrsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.dvrs',
				label: 'Видеорегистраторы'
			}
		})
		.state('app.main.nsi.dvrs.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "DvrCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.dvrs.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.dvrs.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/dvrs/dvrInfo.html",
			controller: "DvrInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.dvrs.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Видеорегистратор - Общее",
				description: "Детализация видеорегистратора. Общее."
			}
		})

		.state('app.main.nsi.dvrs.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/dvrs/dvr-history.html",
			controller: "DvrHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.dvrs.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Видеорегистратор - История",
				description: "Детализация видеорегистратора. История."
			}
		})
		.state('app.main.nsi.dvrs.detail.records', {
			url: "/records",
			templateUrl: "templates/views/pbk/nsi/dvrs/dvr-records.html",
			controller: "DvrRecordsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.dvrs.detail',
				label: '{{itemCrumb}} - Записи с видеорегистратора'
			},
			data: {
				name: "Видеорегистратор - Записи",
				description: "Детализация видеорегистратора. Записи с видеорегистратора."
			}
		})

		.state('app.main.nsi.dvrs.detail.ownershistory', {
			url: "/ownershistory",
			templateUrl: "templates/views/pbk/nsi/dvrs/dvr-owners-history.html",
			controller: "DvrOwnersHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.dvrs.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Видеорегистратор - История смены владельцев",
				description: "Детализация видеорегистратора. История смены владельцев."
			}
		})

		/* NSI contactless-cards */
		.state('app.main.nsi.contactless-cards', {
			abstract: true,
			url: "/contactless-cards",
			resolve: {
				authorizeContractlessCard: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'CONTRACTLESS_CARD']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['CONTRACTLESS_CARD_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'БСК',
				skip: true,
				parent: 'app.main.nsi.employee.facility'
			}
		})
		.state('app.main.nsi.contactless-cards.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/contactless-cards/contactless-cards.html",
			controller: "ContactlessCardsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.contactless-cards',
				label: 'БСК'
			}
		})
		.state('app.main.nsi.contactless-cards.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "ContactlessCardCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.contactless-cards.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.contactless-cards.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/contactless-cards/contactless-cardInfo.html",
			controller: "ContactlessCardInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.contactless-cards.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "БСК - Общее",
				description: "Детализация БСК. Общее."
			}
		})
		.state('app.main.nsi.contactless-cards.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/contactless-cards/contactless-card-history.html",
			controller: "ContactlessCardHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.contactless-cards.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "БСК - История",
				description: "Детализация БСК. История."
			}
		})
		.state('app.main.nsi.contactless-cards.detail.ownershistory', {
			url: "/ownershistory",
			templateUrl: "templates/views/pbk/nsi/contactless-cards/contractless-owners-history.html",
			controller: "ContractlessCardOwnersHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.contactless-cards.detail',
				label: '{{itemCrumb}} - История смены владельцев'
			},
			data: {
				name: "БСК - История",
				description: "Детализация БСК. История смены владельцев."
			}
		})
		.state('app.main.nsi.map-routes', {
			abstract: true,
			url: "/map-routes",
			resolve: {
				authorizeRoutes: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'ROUTES']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['ROUTES_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Маршруты',
				skip: true
			}
		})
		.state('app.main.nsi.map-routes.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/routes/map-routes.html",
			controller: "MapRoutesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.map-routes',
				label: 'Маршруты'
			}
		})
		.state('app.main.nsi.routes', {
			abstract: true,
			url: "/routes",
			resolve: {
				authorizeRoutes: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'ROUTES']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['ROUTES_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Маршруты',
				skip: true,
				parent: 'app.main.nsi.other'
			}
		})
		.state('app.main.nsi.routes.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/routes/routes.html",
			controller: "RoutesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.routes',
				label: 'Маршруты'
			}
		})
		.state('app.main.nsi.routes.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "RouteCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.routes.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.routes.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/routes/routeInfo.html",
			controller: "RouteInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.routes.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Маршруты - Общее",
				description: "Детализация маршрута. Общее."
			}
		})
		.state('app.main.nsi.routes.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/routes/routeHistory.html",
			controller: "RouteHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.routes.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Маршруты - История",
				description: "Детализация маршрута. История."
			}
		})
		.state('app.main.nsi.routes.detail.map', {
			url: "/map",
			templateUrl: "templates/views/pbk/nsi/routes/routeMap.html",
			controller: "RouteMapCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.routes.detail',
				label: '{{itemCrumb}} - Карта'
			},
			data: {
				name: "Маршруты - Карта",
				description: "Детализация маршрута. Карта."
			}
		})

		/* NSI Counties */
		.state('app.main.nsi.counties', {
			abstract: true,
			url: "/counties",
			resolve: {
				authorizeCounties: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'COUNTIES']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['COUNTIES_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Административные округа Москвы',
				skip: true,
				parent: 'app.main.nsi.other'
			}
		})
		.state('app.main.nsi.counties.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/counties/counties.html",
			controller: "CountiesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.counties',
				label: 'Административные округа Москвы'
			}
		})
		.state('app.main.nsi.counties.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "CountyCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.counties.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.counties.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/counties/countyInfo.html",
			controller: "CountyInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.counties.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Административные округа Москвы - Общее",
				description: "Детализация административного округа Москвы. Общее."
			}
		})
		.state('app.main.nsi.counties.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/counties/countyHistory.html",
			controller: "CountyHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.counties.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Административные округа Москвы - История",
				description: "Детализация административного округа Москвы. История."
			}
		})
		.state('app.main.nsi.counties.detail.districts', {
			url: "/districts",
			templateUrl: "templates/views/pbk/nsi/counties/countyDistricts.html",
			controller: "CountyDistrictsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.counties.detail',
				label: '{{itemCrumb}} - Районы'
			},
			data: {
				name: "Административные округа Москвы - Районы",
				description: "Детализация административного округа Москвы. Районы."
			}
		})
		/* NSI Stops */
		.state('app.main.nsi.stops', {
			abstract: true,
			url: "/stops",
			resolve: {
				authorizeStops: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'STOPS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['STOPS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Остановочные пункты',
				skip: true,
				parent: 'app.main.nsi.other'
			}
		})
		.state('app.main.nsi.stops.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/stops/stops.html",
			controller: "StopsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.stops',
				label: 'Остановочные пункты'
			}
		})
		.state('app.main.nsi.stops.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "StopCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.stops.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.stops.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/stops/stopInfo.html",
			controller: "StopInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.stops.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Остановочные пункты  - Общее",
				description: "Детализация остановочного пункта. Общее."
			}
		})
		.state('app.main.nsi.stops.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/stops/stopHistory.html",
			controller: "StopHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.stops.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Остановочные пункты - История",
				description: "Детализация остановочного пункта. История."
			}
		})
		.state('app.main.nsi.stops.detail.location', {
			url: "/location",
			templateUrl: "templates/views/pbk/nsi/stops/stopLocation.html",
			controller: "StopLocationCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.stops.detail',
				label: '{{itemCrumb}} - Местоположение'
			},
			data: {
				name: "Остановочные пункты - Местоположение",
				description: "Детализация остановочного пункта. Местоположение."
			}
		})
		/* NSI Venues */
		.state('app.main.nsi.venues', {
			abstract: true,
			url: "/venues",
			resolve: {
				authorizeVenues: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'VENUES']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['VENUES_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Места встреч',
				skip: true,
				parent: 'app.main.nsi.plan'
			}
		})
		.state('app.main.nsi.venues.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/venues/venues.html",
			controller: "VenuesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.venues',
				label: 'Места встреч'
			}
		})
		.state('app.main.nsi.venues.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "VenueCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.venues.list',
				label: '{{itemCrumb}}',
				skip: true
			},
			resolve: {
				venue: ['VenuesService', 'authorizeVenues', '$stateParams', function (VenuesService, authorizeVenues, $stateParams) {
					return VenuesService.getItem($stateParams.itemId).then(function (result) {
						return result;
					});
				}]
			}
		})
		.state('app.main.nsi.venues.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/venues/venueInfo.html",
			controller: "VenueInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.venues.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Места встреч - Общее",
				description: "Детализация места встречи. Общее."
			}
		})
		.state('app.main.nsi.venues.detail.location', {
			url: "/location",
			templateUrl: "templates/views/pbk/nsi/venues/venueLocation.html",
			controller: "VenueLocationCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.venues.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Места встреч - Местоположение",
				description: "Детализация места встречи. Местоположение."
			}
		})

		.state('app.main.nsi.venues.detail.districts', {
			url: "/districts",
			templateUrl: "templates/views/pbk/nsi/venues/venueDistricts.html",
			controller: "VenueDistrictsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.venues.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Места встреч - Районы",
				description: "Детализация места встречи. Районы."
			}
		})
		.state('app.main.nsi.venues.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/venues/venueHistory.html",
			controller: "VenueHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.venues.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Места втреч - История",
				description: "Детализация места встречи. История."
			}
		})

		/* NSI TS Type */
		.state('app.main.nsi.ts-types', {
			abstract: true,
			url: "/ts-types",
			resolve: {
				authorizeTsTypes: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'TS_TYPES']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['TS_TYPES_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				parent: 'app.main.nsi.other',
				label: 'Типы ТС',
				skip: true
			},
		})
		.state('app.main.nsi.ts-types.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/ts-types/ts-types.html",
			controller: "TsTypesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.ts-types',
				label: 'Типы ТС'
			}
		})
		.state('app.main.nsi.ts-types.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "TsTypeCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.ts-types.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.ts-types.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/ts-types/ts-typeInfo.html",
			controller: "TsTypeInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.ts-types.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Типы ТС - Общее",
				description: "Детализация типа ТС. Общее."
			}
		})
		/* NSI TS Capacity */
		.state('app.main.nsi.ts-capacities', {
			abstract: true,
			url: "/ts-capacities",
			resolve: {
				authorizeTsCapacity: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'TS_CAPACITIES']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['TS_CAPACITIES_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				parent: 'app.main.nsi.other',
				label: 'Вместимости ТС',
				skip: true
			},

		})
		.state('app.main.nsi.ts-capacities.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/ts-capacities/ts-capacities.html",
			controller: "TsCapacitiesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.ts-capacities',
				label: 'Вместимость ТС'
			}
		})
		.state('app.main.nsi.ts-capacities.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "TsCapacityCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.ts-capacities.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.ts-capacities.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/ts-capacities/ts-capacityInfo.html",
			controller: "TsCapacityInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.ts-capacities.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Вместимость ТС - Общее",
				description: "Детализация вместимости ТС. Общее."
			}
		})
		/* NSI Employee */
		.state('app.main.nsi.employees', {
			abstract: true,
			url: "/employees",
			resolve: {
				authorizeEmployees: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'EMPLOYEES']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['EMPLOYEES_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Сотрудники СДиК',
				skip: true,
				parent: 'app.main.nsi.employee'
			}
		})

		.state('app.main.nsi.employees.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/employee/employees.html",
			controller: "EmployeesCtrl",
			ncyBreadcrumb: {
				label: 'Сотрудники СДиК'
			}
		})

		.state('app.main.nsi.employees.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "EmployeeCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.employees.list',
				label: '{{itemCrumb}}',
				skip: true
			},
			resolve: {
				isDelete: ['EmployeesService', 'authorizeEmployees', '$stateParams', function (EmployeesService, authorizeVenues, $stateParams) {
					return EmployeesService.getItem($stateParams.itemId).then(function (result) {
						return result.isDelete == 1 || result.fireDate != null;
					});
				}]
			}
		})
		.state('app.main.nsi.employees.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/employee/employeeInfo.html",
			controller: "EmployeeInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.employees.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Сотрудник - Общее",
				description: "Детализация сотрудника. Общее."
			}
		})

		.state('app.main.nsi.employees.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/employee/employee-history.html",
			controller: "EmployeeHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.employees.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Сотрудник - История",
				description: "Детализация сотрудника. История."
			}
		})

		.state('app.main.nsi.employees.detail.calendar', {
			url: "/calendar",
			templateUrl: "templates/views/pbk/nsi/employee/employee-calendar.html",
			controller: "EmployeeCalendarCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.employees.detail',
				label: '{{itemCrumb}} - Режим работы'
			},
			data: {
				name: "Сотрудник - Режим работы",
				description: "Детализация сотрудника. Режим работы."
			}
		})

		// водители
		.state('app.main.nsi.drivers', {
			abstract: true,
			url: "/drivers",
			resolve: {
				authorizeDrivers: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'DRIVERS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['DRIVERS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Водители',
				skip: true,
				parent: 'app.main.nsi.employee'
			}
		})

		.state('app.main.nsi.drivers.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/driver/drivers.html",
			controller: "DriversCtrl",
			ncyBreadcrumb: {
				label: 'Водители'
			}
		})

		.state('app.main.nsi.drivers.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "DriverCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.drivers.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})

		.state('app.main.nsi.drivers.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/driver/driverInfo.html",
			controller: "DriverInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.drivers.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Водитель - Общее",
				description: "Детализация водителя. Общее."
			}
		})

		.state('app.main.nsi.drivers.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/driver/driver-history.html",
			controller: "DriverHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.drivers.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Водитель - История",
				description: "Детализация водителя. История."
			}
		})

		// бортовое оборудование
		.state('app.main.nsi.equipments', {
			abstract: true,
			url: "/equipments",
			resolve: {
				authorizeEquipments: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'EQUIPMENTS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['EQUIPMENTS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Бортовое оборудование',
				skip: true,
				parent: 'app.main.nsi.other'
			}
		})

		.state('app.main.nsi.equipments.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/equipment/equipments.html",
			controller: "EquipmentsCtrl",
			ncyBreadcrumb: {
				label: 'Бортовое оборудование'
			}
		})

		.state('app.main.nsi.equipments.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "EquipmentCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.equipments.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})

		.state('app.main.nsi.equipments.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/equipment/equipmentInfo.html",
			controller: "EquipmentInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.equipments.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Бортовое оборудование - Общее",
				description: "Детализация бортового оборудования. Общее."
			}
		})

		.state('app.main.nsi.equipments.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/equipment/equipment-history.html",
			controller: "EquipmentHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.equipments.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "Бортовое оборудование - История",
				description: "Детализация бортового оборудования. История."
			}
		})

		// смены
		.state('app.main.nsi.shifts', {
			abstract: true,
			url: "/shifts",
			resolve: {
				authorizeShifts: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'SHIFTS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['SHIFTS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Смены',
				skip: true,
				parent: 'app.main.nsi.plan'
			}
		})

		.state('app.main.nsi.shifts.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/shift/shifts.html",
			controller: "ShiftsCtrl",
			ncyBreadcrumb: {
				label: 'Смены'
			}
		})

		.state('app.main.nsi.shifts.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "ShiftCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.shifts.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})

		.state('app.main.nsi.shifts.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/shift/shiftInfo.html",
			controller: "ShiftInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.shifts.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Смена - Общее",
				description: "Детализация смены. Общее."
			}
		})

		.state('app.main.nsi.shifts.detail.reserve', {
			url: "/reserve",
			templateUrl: "templates/views/pbk/nsi/shift/shiftReserves.html",
			controller: "ShiftReservesCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.shifts.detail',
				label: '{{itemCrumb}} - Резерв'
			},
			data: {
				name: "Смена - Резерв",
				description: "Детализация смены. Резерв."
			}
		})
		
		// графики
        .state('app.main.nsi.grafics', {
            abstract: true,
            url: "/grafics",
            resolve: {
                authorizeGrafics: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
                    var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'VENUES']);
                    return createPromise(hasRole, $state, $timeout, $q);
                }],
                isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
                    return UserService.hasAnyRole(['VENUES_EDIT', 'DEBUG_TO_REPLACE']);
                }]
            },
            template: '<ui-view/>',
            ncyBreadcrumb: {
                label: 'Графики',
                skip: true,
                parent: 'app.main.nsi.plan'
            }
        })

        .state('app.main.nsi.grafics.list', {
            url: "",
            templateUrl: "templates/views/pbk/nsi/grafics/grafics.html",
            controller: "GraficsCtrl",
            ncyBreadcrumb: {
                label: 'Графики'
            }
        })

        .state('app.main.nsi.grafics.detail', {
            url: "/:itemId",
            templateUrl: "templates/tabs.html",
            controller: "GraficCtrl",
            ncyBreadcrumb: {
                parent: 'app.main.nsi.grafics.list',
                label: '{{itemCrumb}}',
                skip: true
            }
        })

        .state('app.main.nsi.grafics.detail.info', {
            url: "/info",
            templateUrl: "templates/views/pbk/nsi/grafics/graficInfo.html",
            controller: "GraficInfoCtrl",
            ncyBreadcrumb: {
                parent: 'app.main.nsi.grafics.detail',
                label: '{{itemCrumb}} - Общее'
            },
            data: {
                name: "График - Общее",
                description: "Детализация графика. Общее."
            }
        })

		// TODO уже не в nsi
		.state('app.main.nsi.duties', {
			abstract: true,
			url: "/duties",
			resolve: {
				authorizeDuties: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'DUTIES']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['DUTIES_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Наряды',
				skip: true,
				parent: 'app.main.nsi.other'
			}
		})
		.state('app.main.nsi.duties.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/duties/duties.html",
			controller: "DutiesCtrl",
			ncyBreadcrumb: {
				label: 'Наряды'
			}
		})
		.state('app.main.nsi.duties.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "DutyCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.duties.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.duties.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/duties/dutyInfo.html",
			controller: "DutyInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.duties.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Наряд - Общее",
				description: "Детализация наряда. Общее."
			}
		})
		
		// TODO уже не в nsi
		.state('app.main.nsi.telematicses', {
			abstract: true,
			url: "/telematicses",
			template: '<ui-view/>',
			resolve: {
				authorizeTelematics: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'TELEMATICS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				canExport: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['TELEMATICS', 'DEBUG_TO_REPLACE']);
				}]
			},
			ncyBreadcrumb: {
				label: 'Анализ пассажиропотока',
				skip: true,
				parent: 'app.main.report'
			}

		})
		.state('app.main.nsi.telematicses.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/telematicses/telematicses.html",
			controller: "TelematicsesCtrl",
			ncyBreadcrumb: {
				label: 'Анализ пассажиропотока'
			}
		})
		.state('app.main.nsi.telematicses.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "TelematicsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.telematicses.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.nsi.telematicses.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/telematicses/telematicsInfo.html",
			controller: "TelematicsInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.telematicses.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Анализ пассажиропотока - Общее",
				description: "Детализация пассажиропотока. Общее."
			}
		})

		// ТС
		.state('app.main.nsi.venicles', {
			abstract: true,
			url: "/venicles",
			resolve: {
				authorizeVenicles: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'VENICLES']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['VENICLES_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				// parent: 'app.main.nsi.ts',
				label: 'ТС',
				skip: true
			}
		})

		.state('app.main.nsi.venicles.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/venicles/venicles.html",
			controller: "VeniclesCtrl",
			ncyBreadcrumb: {
				label: 'ТС'
			}
		})

		.state('app.main.nsi.venicles.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "VenicleCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.venicles.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})

		.state('app.main.nsi.venicles.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/venicles/venicleInfo.html",
			controller: "VenicleInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.venicles.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "ТС - Общее",
				description: "Детализация ТС. Общее."
			}
		})

		.state('app.main.nsi.venicles.detail.history', {
			url: "/history",
			templateUrl: "templates/views/pbk/nsi/venicles/venicle-history.html",
			controller: "VenicleHistoryCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.venicles.detail',
				label: '{{itemCrumb}} - История'
			},
			data: {
				name: "ТС - История",
				description: "Детализация ТС. История."
			}
		})

		// модели ТС
		.state('app.main.nsi.ts-models', {
			abstract: true,
			url: "/ts-models",
			resolve: {
				authorizeTsModels: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'TS_MODELS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['TS_MODELS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				parent: 'app.main.nsi.other',
				label: 'Модели ТС',
				skip: true
			}
		})

		.state('app.main.nsi.ts-models.list', {
			url: "",
			templateUrl: "templates/views/pbk/nsi/ts-model/tsModels.html",
			controller: "TSModelsCtrl",
			ncyBreadcrumb: {
				label: 'Модели ТС'
			}
		})

		.state('app.main.nsi.ts-models.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "TSModelCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.ts-models.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})

		.state('app.main.nsi.ts-models.detail.info', {
			url: "/info",
			templateUrl: "templates/views/pbk/nsi/ts-model/tsModelInfo.html",
			controller: "TSModelInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.nsi.ts-models.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Модель ТС - Общее",
				description: "Детализация модели ТС. Общее."
			}
		})

		.state('app.main.nsi.calendar', {
			url: "/calendar",
			templateUrl: "templates/views/pbk/nsi/calendar/calendar.html",
			controller: "CalendarCtrl",
			resolve: {
				authorizecalendar: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'CALENDAR']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['CALENDAR_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			ncyBreadcrumb: {
				label: 'Календарь',
				parent: 'app.main.nsi.plan'
			}
		})

		.state('app.main.report', {
			abstract: false,
			url: "^/report",
			templateUrl: "templates/views/pbk/report/reportsMain.html",
			// template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'Отчётность'
			}
		})

		.state('app.main.report.askp', {
			url: "/askp",
			templateUrl: "templates/tabs.html",
			controller: "AskpReportCtrl",
			ncyBreadcrumb: {
				label: 'Пассажиропоток по остановке'
			}
		})

		.state('app.main.report.askp.stops', {
			url: "/stops",
			templateUrl: "templates/views/pbk/report/askp/stops.html",
			controller: "AskpReportByStopCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.report.askp',
				label: 'Пассажиропоток по остановке'
			}
		})

		.state('app.main.report.unionanalysisondate', {
			url: "/union-analysis-on-date",
			templateUrl: "templates/tabs.html",
			controller: "UnionAnalysisOnDateCtrl",
			ncyBreadcrumb: {
				label: 'Сравнительный анализ ПП на дату'
			}
		})

		.state('app.main.report.unionanalysisondate.move-routes', {
			url: "/move-routes",
			templateUrl: "templates/views/pbk/report/unionanalysis/move-route.html",
			controller: "UnionAnalysisByMoveRouteCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.report.unionanalysisondate',
				label: 'Аналитика по маршрутам и выходам'
			}
		})

		.state('app.main.report.unionanalysisondate.trip-move-routes', {
			url: "/trip-move-routes",
			templateUrl: "templates/views/pbk/report/unionanalysis/trip-move-route.html",
			controller: "UnionAnalysisByTripMoveRouteCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.report.unionanalysisondate',
				label: 'Аналитика по рейсам маршрута-выхода'
			},
			params: {
				workDate: null,
				routeId: null,
				grafic: null,
				routeName: null
			}
		})

		.state('app.main.report.unionanalysisondate.trip-stops', {
			url: "/trip-stops",
			templateUrl: "templates/views/pbk/report/unionanalysis/trip-stop.html",
			controller: "UnionAnalysisByTripStopCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.report.unionanalysisondate',
				label: 'Аналитика по остановкам рейса'
			},
			params: {
				workDate: null,
				routeId: null,
				grafic: null,
				routeName: null,
				tripId: null,
				tripNum: null
			}
		})

		.state('app.main.report.unionanalysis', {
			url: "/union-analysis",
			templateUrl: "templates/tabs.html",
			controller: "UnionAnalysisCtrl",
			ncyBreadcrumb: {
				label: 'Сводный сравнительный анализ ПП'
			}
		})

		.state('app.main.report.unionanalysis.routes', {
			url: "/routes",
			templateUrl: "templates/views/pbk/report/unionanalysis/routes.html",
			controller: "UnionAnalysisByRouteCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.report.unionanalysis',
				label: 'Аналитика по маршрутам'
			}
		})

		.state('app.main.report.unionanalysis.stops', {
			url: "/stops",
			templateUrl: "templates/views/pbk/report/unionanalysis/stops.html",
			controller: "UnionAnalysisByStopCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.report.unionanalysis',
				label: 'Аналитика по остановкам'
			},
			params: {
				fromDate: null,
				toDate: null,
				routeId: null,
				routeName: null
			}
		})

		.state('app.main.report.standard', {
			abstract: true,
			url: "/standard",
			template: '<ui-view/>',
			resolve: {
				canExport: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD1', 'REPORTS_STANDARD2', 'REPORTS_STANDARD3',
						'REPORTS_STANDARD4', 'REPORTS_STANDARD5', 'REPORTS_STANDARD6', 'REPORTS_STANDARD7',
						'REPORTS_STANDARD8', 'REPORTS_STANDARD9', 'REPORTS_STANDARD10', 'REPORTS_STANDARD11',
						'REPORTS_STANDARD12', 'REPORTS_STANDARD13', 'REPORTS_STANDARD14', 'REPORTS_STANDARD15', 'REPORTS_STANDARD16',
						'REPORTS_STANDARD17', 'REPORTS_STANDARD18', 'REPORTS_STANDARD19', 'REPORTS_STANDARD20', 'REPORTS_STANDARD21',
						'REPORTS_STANDARD22', 'REPORTS_STANDARD23']);
				}]
			},
			ncyBreadcrumb: {
				skip: true
			}
		})

		.state('app.main.report.standard.list', {
			url: "/list",
			templateUrl: "templates/views/pbk/report/standard/standardReports.html",
			controller: "StandardReportsCtrl",
			resolve: {
				authorizeReportsStandard: ['UserService', '$timeout', '$state', '$q', 'canExport', function (UserService, $timeout, $state, $q, canExport) {
					return canExport;
				}]
			},
			ncyBreadcrumb: {
				label: 'Стандартные отчеты'
			}
		})

		.state('app.main.report.standard.so-1', {
			url: "/so-1",
			templateUrl: "templates/views/pbk/report/standard/so-1.html",
			controller: "So1Ctrl",
			resolve: {
				canExportReport1: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD1']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'График работы контролеров по территориальному подразделению'
			}
		})

		.state('app.main.report.standard.so-2', {
			url: "/so-2",
			templateUrl: "templates/views/pbk/report/standard/so-2.html",
			controller: "So2Ctrl",
			resolve: {
				canExportReport2: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD2']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Табель фактически отработанного времени'
			}
		})

		.state('app.main.report.standard.so-3', {
			url: "/so-3",
			templateUrl: "templates/views/pbk/report/standard/so-3.html",
			controller: "So3Ctrl",
			resolve: {
				canExportReport3: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD3']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Количество бригад по местам встречи'
			}
		})

		.state('app.main.report.standard.so-4', {
			url: "/so-4",
			templateUrl: "templates/views/pbk/report/standard/so-4.html",
			controller: "So4Ctrl",
			resolve: {
				canExportReport4: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD4']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Ежедневная посменная численность контролёров ГУП "Мосгортранс" по территориальному подразделению'
			}
		})

		.state('app.main.report.standard.so-5', {
			url: "/so-5",
			templateUrl: "templates/views/pbk/report/standard/so-5.html",
			controller: "So5Ctrl",
			resolve: {
				canExportReport5: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD5']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Посменная численность контролёров ГУП "Мосгортранс" и среднее значение численности за период'
			}
		})

		.state('app.main.report.standard.so-6', {
			url: "/so-6",
			templateUrl: "templates/views/pbk/report/standard/so-6.html",
			controller: "So6Ctrl",
			resolve: {
				canExportReport6: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD6']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Результаты контроля (мотивация)'
			}
		})

		.state('app.main.report.standard.so-7', {
			url: "/so-7",
			templateUrl: "templates/views/pbk/report/standard/so-7.html",
			controller: "So7Ctrl",
			resolve: {
				canExportReport7: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD7']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Количество перевезенных пассажиров по маршрутам'
			}
		})

		.state('app.main.report.standard.so-8', {
			url: "/so-8",
			templateUrl: "templates/views/pbk/report/standard/so-8.html",
			controller: "So8Ctrl",
			resolve: {
				canExportReport8: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD8']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Работа контролеров на маршруте'
			}
		})

		.state('app.main.report.standard.so-9', {
			url: "/so-9",
			templateUrl: "templates/views/pbk/report/standard/so-9.html",
			controller: "So9Ctrl",
			resolve: {
				canExportReport9: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD9']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Сводные данные по работе контролеров по подразделениям'
			}
		})

		.state('app.main.report.standard.so-10', {
			url: "/so-10",
			templateUrl: "templates/views/pbk/report/standard/so-10.html",
			controller: "So10Ctrl",
			resolve: {
				canExportReport10: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD10']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Сводная форма по эффективности работы контролеров'
			}
		})

		.state('app.main.report.standard.so-11', {
			url: "/so-11",
			templateUrl: "templates/views/pbk/report/standard/so-11.html",
			controller: "So11Ctrl",
			resolve: {
				canExportReport11: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD11']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Работа контролеров'
			}
		})

		.state('app.main.report.standard.so-12', {
			url: "/so-12",
			templateUrl: "templates/views/pbk/report/standard/so-12.html",
			controller: "So12Ctrl",
			resolve: {
				canExportReport12: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD12']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Совместный график по местам встреч'
			}
		})

		.state('app.main.report.standard.so-13', {
			url: "/so-13",
			templateUrl: "templates/views/pbk/report/standard/so-13.html",
			controller: "So13Ctrl",
			resolve: {
				canExportReport13: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD13']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Маршруты и выходы'
			}
		})

		.state('app.main.report.standard.so-14', {
			url: "/so-14",
			templateUrl: "templates/views/pbk/report/standard/so-14.html",
			controller: "So14Ctrl",
			resolve: {
				canExportReport14: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD14']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Результаты ПБК за период'
			}
		})

		.state('app.main.report.standard.so-15', {
			url: "/so-15",
			templateUrl: "templates/views/pbk/report/standard/so-15.html",
			controller: "So15Ctrl",
			resolve: {
				canExportReport15: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD15']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Итоги ПБК по контролеру по данным АСУ ПБК'
			}
		})

		.state('app.main.report.standard.so-16', {
			url: "/so-16",
			templateUrl: "templates/views/pbk/report/standard/so-16.html",
			controller: "So16Ctrl",
			resolve: {
				canExportReport16: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD16']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП) поостановочно'
			}
		})

		.state('app.main.report.standard.so-17', {
			url: "/so-17",
			templateUrl: "templates/views/pbk/report/standard/so-17.html",
			controller: "So17Ctrl",
			resolve: {
				canExportReport17: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD17']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Сводный сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП)'
			}
		})

		.state('app.main.report.standard.so-18', {
			url: "/so-18",
			templateUrl: "templates/views/pbk/report/standard/so-18.html",
			controller: "So18Ctrl",
			resolve: {
				canExportReport18: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD18']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Сопоставительный анализ данных по наряд-заданию и из АСКП'
			}
		})

		.state('app.main.report.standard.so-19', {
			url: "/so-19",
			templateUrl: "templates/views/pbk/report/standard/so-19.html",
			controller: "So19Ctrl",
			resolve: {
				canExportReport19: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD19']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Количество совместных бригад с ГКУ ОП'
			}
		})

		.state('app.main.report.standard.so-20', {
			url: "/so-20",
			templateUrl: "templates/views/pbk/report/standard/so-20.html",
			controller: "So20Ctrl",
			resolve: {
				canExportReport20: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD20']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Статистика проверок маршрута'
			}
		})
		
		.state('app.main.report.standard.so-21', {
			url: "/so-21",
			templateUrl: "templates/views/pbk/report/standard/so-21.html",
			controller: "So21Ctrl",
			resolve: {
				canExportReport21: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD21']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Проходы по БСК контролера'
			}
		})
		
		.state('app.main.report.standard.so-22', {
			url: "/so-22",
			templateUrl: "templates/views/pbk/report/standard/so-22.html",
			controller: "So22Ctrl",
			resolve: {
				canExportReport22: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD22']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Сверка с ГКУ ОП'
			}
		})
		
		.state('app.main.report.standard.so-23', {
			url: "/so-23",
			templateUrl: "templates/views/pbk/report/standard/so-23.html",
			controller: "So23Ctrl",
			resolve: {
				canExportReport23: ['UserService', 'canExport', function (UserService, canExport) {
					return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD23']);
				}]
			},
			ncyBreadcrumb: {
				parent: 'app.main.report.standard.list',
				label: 'Сводные данные по работе контролеров за период'
			}
		})

        .state('app.main.report.standard.so-24', {
            url: "/so-24",
            templateUrl: "templates/views/pbk/report/standard/so-24.html",
            controller: "So24Ctrl",
            resolve: {
                canExportReport24: ['UserService', 'canExport', function (UserService, canExport) {
                    return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD24']);
                }]
            },
            ncyBreadcrumb: {
                parent: 'app.main.report.standard.list',
                label: 'Сводные данные по наряд заданиям'
            }
        })

        .state('app.main.report.standard.so-25', {
            url: "/so-25",
            templateUrl: "templates/views/pbk/report/standard/so-25.html",
            controller: "So25Ctrl",
            resolve: {
                canExportReport24: ['UserService', 'canExport', function (UserService, canExport) {
                    return UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'REPORTS_STANDARD24']);
                }]
            },
            ncyBreadcrumb: {
                parent: 'app.main.report.standard.list',
                label: 'Cписок маршрутов АСМ-ПП'
            }
        })

		.state('app.main.vis', {
			abstract: false,
			url: "^/vis",
			templateUrl: "templates/views/vis/visMain.html",
			// template: '<ui-view/>',
			ncyBreadcrumb: {
				label: 'ВИС',
				parent: 'app.main.admin'
			}
		})
		.state('app.main.vis.askp-pusk-check-bind', {
			abstract: true,
			url: "/askp-pusk-check-bind",
			template: '<ui-view/>',
			ncyBreadcrumb: {
				skip: true
			},
			resolve: {
				authorizePuskBind: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'ASKP_PUSK_CHECKS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}]
			}
		})
		.state('app.main.vis.askp-pusk-check-bind.list', {
			url: "",
			templateUrl: "templates/views/vis/askp/puskCheckBind.html",
			controller: "AskpPuskCheckBindsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.vis.viss',
				label: 'АСКП: проверки ПУсК'
			}
		})
		.state('app.main.vis.viss', {
			abstract: true,
			url: "/vis",
			resolve: {
				authorizeVis: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'VIS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['VIS_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				skip: true
			},
		})
		.state('app.main.vis.viss.list', {
			url: "",
			templateUrl: "templates/views/vis/viss/viss.html",
			controller: "VissCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.vis.viss',
				label: 'ВИС'
			}
		})
		.state('app.main.vis.viss.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "VisCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.vis.viss.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.vis.viss.detail.info', {
			url: "/info",
			templateUrl: "templates/views/vis/viss/visInfo.html",
			controller: "VisInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.vis.viss.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "ВИС - Общее",
				description: "Детализация ВИС. Общее."
			}
		})
		.state('app.main.vis.exchange-objects', {
			abstract: true,
			resolve: {
				authorizeVisExcanges: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'VIS_EXCHANGES_OBJ']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['VIS_EXCHANGES_OBJ_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			url: "/exchange-objects",
			template: '<ui-view/>',
			ncyBreadcrumb: {
				skip: true
			},
		})
		.state('app.main.vis.exchange-objects.list', {
			url: "",
			templateUrl: "templates/views/vis/exchange-objects/exchange-objects.html",
			controller: "ExchangeObjectsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.vis.exchange-objects',
				label: 'Объекты обмена с ВИС'
			}
		})
		.state('app.main.vis.exchange-objects.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "ExchangeObjectCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.vis.exchange-objects.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})
		.state('app.main.vis.exchange-objects.detail.info', {
			url: "/info",
			templateUrl: "templates/views/vis/exchange-objects/exchange-objectInfo.html",
			controller: "ExchangeObjectInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.vis.exchange-objects.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Объекты обмена с ВИС - Общее",
				description: "Детализация объекта обмена с ВИС. Общее."
			}
		})

		.state('app.main.vis.exchange-configs', {
			abstract: true,
			url: "/exchange-configs",
			resolve: {
				authorizeVisExcangesConf: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'VIS_EXCHANGES_CONF']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['VIS_EXCHANGES_CONF_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				skip: true
			},
		})

		.state('app.main.vis.exchange-configs.list', {
			url: "",
			templateUrl: "templates/views/vis/exchange-config/exchange-configs.html",
			controller: "ExchangeConfigsCtrl",
			ncyBreadcrumb: {
				label: 'Конфигурации взаимодействия'
			}
		})

		.state('app.main.vis.exchange-configs.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "ExchangeConfigCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.vis.exchange-configs.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})

		.state('app.main.vis.exchange-configs.detail.info', {
			url: "/info",
			templateUrl: "templates/views/vis/exchange-config/exchangeConfigInfo.html",
			controller: "ExchangeConfigInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.vis.exchange-configs.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Конфигурации взаимодействия - Общее",
				description: "Детализация конфигурации взаимодействия. Общее."
			}
		})

		.state('app.main.vis.exchanges', {
			abstract: true,
			url: "/exchanges",
			resolve: {
				authorizeVisExcangesObj: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'VIS_EXCHANGES']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				isNotReadOnlyUser: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['VIS_EXCHANGES_EDIT', 'DEBUG_TO_REPLACE']);
				}]
			},
			template: '<ui-view/>',
			ncyBreadcrumb: {
				skip: true
			}
		})

		.state('app.main.vis.exchanges.list', {
			url: "",
			templateUrl: "templates/views/vis/exchange/exchanges.html",
			controller: "ExchangesCtrl",
			ncyBreadcrumb: {
				label: 'Журнал взаимодействий'
			}
		})

		.state('app.main.vis.exchanges.detail', {
			url: "/:itemId",
			templateUrl: "templates/tabs.html",
			controller: "ExchangeCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.vis.exchanges.list',
				label: '{{itemCrumb}}',
				skip: true
			}
		})

		.state('app.main.vis.exchanges.detail.info', {
			url: "/info",
			templateUrl: "templates/views/vis/exchange/exchangeInfo.html",
			controller: "ExchangeInfoCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.vis.exchanges.detail',
				label: '{{itemCrumb}} - Общее'
			},
			data: {
				name: "Взаимодействие - Общее",
				description: "Детализация взаимодействия. Общее."
			}
		})

		.state('app.main.vis.exchanges.detail.send', {
			url: "/info",
			templateUrl: "templates/views/vis/exchange/exchangeAttempts.html",
			controller: "ExchangeAttemptsCtrl",
			ncyBreadcrumb: {
				parent: 'app.main.vis.exchanges.detail',
				label: '{{itemCrumb}} - Взаимодействия'
			},
			data: {
				name: "Взаимодействие - Взаимодействия",
				description: "Детализация взаимодействия. Взаимодействия."
			}
		})
		.state('app.main.askp', {
			abstract: true,
			url: "^/askp",
			template: '<ui-view/>',
			resolve: {
				authorizeTelematics: ['UserService', '$timeout', '$state', '$q', 'authorizeMain', function (UserService, $timeout, $state, $q, authorizeMain) {
					var hasRole = UserService.hasAnyRole(['DEBUG_TO_REPLACE', 'TELEMATICS']);
					return createPromise(hasRole, $state, $timeout, $q);
				}],
				canExport: ['UserService', 'authorizeMain', function (UserService, authorizeMain) {
					return UserService.hasAnyRole(['TELEMATICS', 'DEBUG_TO_REPLACE']);
				}]
			},
			ncyBreadcrumb: {
				label: 'Анализ пассажиропотока',
				skip: true,
			},
		})
		.state('app.main.askp.checks', {
			url: "/checks",
			templateUrl: "templates/tabs.html",
			controller: "AskpChecksCtrl",
			ncyBreadcrumb: {
				label: 'АСКП (данные по проходам)',
				parent: 'app.main.askp'
			}
		})
		.state('app.main.askp.checks.moves', {
			url: "/moves",
			templateUrl: "templates/views/pbk/nsi/askp/askpCehcksByMoves.html",
			controller: "AskpChecksByMovesCtrl",
			ncyBreadcrumb: {
				label: 'АСКП по выходам',
				parent: 'app.main.askp.checks'
			}
		})
		.state('app.main.askp.checks.hours', {
			url: "/hours",
			templateUrl: "templates/views/pbk/nsi/askp/askpChecksTable.html",
			controller: "AskpChecksByHoursCtrl",
			ncyBreadcrumb: {
				label: 'АСКП по часам',
				parent: 'app.main.askp.checks'
			}
		})
		.state('app.main.askp.checks.stops', {
			url: "/stops",
			templateUrl: "templates/views/pbk/nsi/askp/askpChecksTable.html",
			controller: "AskpChecksByStopsCtrl",
			params: {
				date: null,
				routeId: null,
				moveCode: null,
				routeName: null
			},
			ncyBreadcrumb: {
				label: 'АСКП по остановкам',
				parent: 'app.main.askp.checks'
			}
		})
		.state('app.main.askp.analysis', {
			url: "/analysis",
			templateUrl: "templates/tabs.html",
			controller: "AskpAnalysisCtrl",
			ncyBreadcrumb: {
				label: 'Сравнительный анализ ПП',
				parent: 'app.main.askp'
			}
		})
		.state('app.main.askp.analysis.routes', {
			url: "/routes",
			templateUrl: "templates/views/pbk/nsi/askp/askpAnalysisByRoutes.html",
			controller: "AskpAnalysisByRoutesCtrl",
			ncyBreadcrumb: {
				label: 'Сравнительный анализ по маршрутам',
				parent: 'app.main.askp.analysis'
			}
		});

	function createPromise(hasAccess, $state, $timeout, $q) {
		var def = $q.defer();
		if (!hasAccess) {
			$timeout(function () {
				$state.go("forbidden", {
					errorStatusCode: 403
				}, {
					location: false
				});
			}, 0);
			def.reject();
		} else {
			def.resolve()
		}
		return def.promise;
	}

	RestangularProvider.addResponseInterceptor(function (data, operation, what, url, response, deferred) {
		var isRestCall = url.indexOf('api') != -1;
		return isRestCall ? data.result : data;
	});

}).run(function ($rootScope, $state, $modal, $location, $cookieStore, Restangular, UserService) {

	$rootScope.hasRole = function (role) {
		return UserService.hasRole(role);
	};

	$rootScope.getCurrentYear = function () {
		return new Date().getFullYear();
	};

	$rootScope.requestCounter = 0;
	Restangular.addRequestInterceptor(function (element, operation) {
		if (operation == "post" || operation == "delete" || operation == "put") {
			$("#requestSpinner").css('visibility', 'visible');
			++$rootScope.requestCounter;
		}
		$rootScope.xhr = true;
		return element;
	});
	Restangular.addResponseInterceptor(function (data, operation, what, url, response, deferred) {
		if (response != null && (operation == "post" || operation == "delete" || operation == "put")) {
			if (--$rootScope.requestCounter == 0) {
				$("#requestSpinner").css('visibility', 'hidden');
			}
		}
		$rootScope.xhr = false;
		return data;
	});

	Restangular.setErrorInterceptor(function (response, deferred, responseHandler) {
		var status = response.status;
		var config = response.config;
		var method = config.method;
		var url = config.url;

		if (method == "POST" || method == "DELETE" || method == "PUT") {
			if (--$rootScope.requestCounter == 0) {
				$("#requestSpinner").css('visibility', 'hidden');
			}
		}

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
				templateUrl: "templates/dialogs/serverErrorDialog.html",
				windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
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
		}
		if (response.status === 403) {
			$state.go('forbidden');
			return false; // error handled
		}
		if (response.status == 401) {
			UserService.clearReportsAuth();
			$state.go("login");
			return true;
		}
		if (response.status == 502) {
			UserService.clearReportsAuth();
			$state.go("bad-gateway");
			return true;
		} else {
			$rootScope.error = method + " on " + url + " failed with status " + status;
		}
		return true;
	});

	Restangular.setFullRequestInterceptor(function (element, operation, route, url, headers, params, httpConfig) {
		var isRestCall = url.indexOf('api') != -1;
		if (isRestCall && angular.isDefined($rootScope.authToken)) {
			headers['X-Auth-Token'] = $rootScope.authToken;
		}
		return {
			element: element,
			params: params,
			headers: headers,
			httpConfig: httpConfig
		};
	});

	$rootScope.logout = function () {
		delete $rootScope.user;
		delete $rootScope.authToken;
		UserService.logout();
		$cookieStore.remove('authToken');
		UserService.clearReportsAuth();
		$state.go("login");
	};

	// $state.go("app.main");
	$rootScope.initialized = true;

});
