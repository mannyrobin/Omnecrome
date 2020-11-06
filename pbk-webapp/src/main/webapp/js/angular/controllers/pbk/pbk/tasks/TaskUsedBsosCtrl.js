angular.module('armada')
	.controller('TaskUsedBsosCtrl', ['$scope', 'GridService', 'BsosService', '$state', 'BsoTypesService', '$stateParams', 'isNotReadOnlyUser',
		'TaskUsedBsosService',
		function ($scope, GridService, BsosService, $state, BsoTypesService, $stateParams, isNotReadOnlyUser, TaskUsedBsosService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = TaskUsedBsosService;

			/* Права */
			$scope.gridScope.perms = {
				use: isNotReadOnlyUser,
				disuse: isNotReadOnlyUser,
				//trash: isNotReadOnlyUser,
				//trash_fix: isNotReadOnlyUser
			};

			var taskId = $stateParams.taskId != null ? $stateParams.taskId : $stateParams.itemId;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Использованные БСО"
				},
				buttons: [{
					tip: "Использовать",
					name: "use",
					action: BsosService.useBsos($scope.gridScope, taskId),
					icon: "star",
					activeWhenItemsSelected: true
				}, {
					tip: "Вернуть",
					name: "disuse",
					action: BsosService.disuseBsos($scope.gridScope, taskId),
					icon: "star-empty",
					activeWhenItemsSelected: true
				}/*, {
					tip: "Забраковать",
					name: "trash",
					action: BsosService.trashBsos($scope.gridScope, taskId),
					icon: "trash",
					activeWhenItemsSelected: true
				}, {
					tip: "Исправить",
					name: "trash_fix",
					action: BsosService.fixBsos($scope.gridScope),
					icon: "wrench",
					activeWhenItemsSelected: true
				}*/],
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "bsoTypeName",
				label: "Тип БСО",
				class: "col-md-4"
			}, {
				id: "bsoNumber",
				label: "Номер БСО",
				class: "col-md-3"
			}, {
				id: "isBound",
				icon: "link",
				iconTip: "Закреплен",
				label: "",
				class: "col-md-2"
			}, {
				id: "isUsed",
				icon: "asterisk",
				iconTip: "Использован",
				label: "",
				class: "col-md-1"
			}, {
				id: "isTrashed",
				icon: "trash",
				iconTip: "Брак",
				label: "",
				class: "col-md-1"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "bsoNumber",
				type: "text",
				placeholder: "Номер БСО"
			}, {
				name: "bsoStateId",
				type: "select",
				placeholder: "Состояние БСО",
				load: BsosService.getStates
			}, {
				name: "bsoTypeId",
				type: "text",
				defval: "2", // Акт изъятия проездных документов
				hide: true
			}, {
				name: "taskId",
				type: "text",
				defval: taskId,
				hide: true
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти использованные БСО",
				gridScope: $scope.gridScope
			};

			/* Кнопки */
			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "bsoTypeName",
				gridName: $state.current.name + '.taskUsedBsosGrid',
				load: $scope.gridScope.service.getPage
			});

			$scope.gridScope.addSelItemColumn = true;

		}]);
