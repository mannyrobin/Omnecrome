angular.module('armada')
	.controller('DvrRecordsCtrl', ['$scope', '$stateParams', '$modal', 'DvrRecordsService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, $stateParams, $modal, DvrRecordsService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = DvrRecordsService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Записи с видеорегистратора"
				},
				gridScope: $scope.gridScope
			};

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				export: isNotReadOnlyUser,
				remove: isNotReadOnlyUser
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "ownerName",
				label: "Пользователь",
				class: "col-md-3"
			}, {
				id: "dateRecord",
				label: "Дата и время",
				class: "col-md-3"
			}, {
				id: "duration",
				label: "Продолжительность",
				class: "col-md-3"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "dvrHeadId",
				type: "text",
				defval: $stateParams.itemId,
				hide: true
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти видеорегистратор",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				gridName: $state.current.name + '.dvrRecordgrid',
				load: $scope.gridScope.service.getPage
			});

			/* Кнопки */
			$scope.gridScope.buttons = {
				extraButtons: [{
					tip: "Скачать",
					action: function (row) {
                        if(!checkDate(row.dateRecord)) {
                            location.href = "/api/pbk/nsi/dvrs/records/load?url=" + row.url;
                        }
					},
					class: "glyphicon glyphicon-download download-v",
					show: function (row) {
						return row.url != null;
					}
				}, {
					tip: "Воспроизвести",
					action: function (row) {
                        if(!checkDate(row.dateRecord)) {
                            $modal.open({
                                templateUrl: "templates/dialogs/" + "pbk/nsi/dvrs/DvrVideo.html",
                                windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
                                controller: "DvrRecordPlayCtrl",
                                size: "md",
                                resolve: {
                                    data: function () {
                                        return {
                                            title: "Запись с видеорегистратора",
                                            url: row.url
                                        }
                                    }
                                }
                            });
                        }
					},
					class: "glyphicon glyphicon-facetime-video play-v",
					show: function (row) {
						return row.url != null;
					}
				}],
				gridScope: $scope.gridScope
			};

			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.delete == true ? 'remove-text-record' : null;
			};

            checkDate = function(rowDate){
                var start = moment(rowDate, "DD.MM.YYYY hh:mm");
                var now = moment();
                return now.diff(start,'month')>=2;
            };

			$scope.gridScope.getRowClassFunc = function (row) {
				if (!row) {
					return null;
				}
                var classNames =  checkDate(row.dateRecord)?"disable-view":"";
                classNames += row.delete ? 'remove-record' : "";
                return classNames;
			};
		}]);
