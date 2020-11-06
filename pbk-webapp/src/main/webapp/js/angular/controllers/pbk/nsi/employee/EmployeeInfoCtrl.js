angular.module('armada')
    .controller('EmployeeInfoCtrl', ['$controller', '$scope', '$stateParams', 'EmployeesService', 'UtilService', '$modal',
        'DepartmentsService', 'UsersService', 'UserService', 'ContactlessCardsService', 'OfficialCardsService', 'PusksService', 'SexesService',
        'CountiesService', 'DvrsService', 'isNotReadOnlyUser', 'isDelete', '$window',
        function ($controller, $scope, $stateParams, EmployeesService, UtilService, $modal,
                  DepartmentsService, UsersService, UserService, ContactlessCardsService, OfficialCardsService, PusksService, SexesService,
                  CountiesService, DvrsService, isNotReadOnlyUser, isDelete, $window) {
            $scope.isNotReadOnlyUser = isNotReadOnlyUser;
            $scope.readonly = !isNotReadOnlyUser || isDelete;
            $scope.refreshAfterLoad = true;
            /* Поля ввода */
            $scope.fields = [
                {
                    name: "surname",
                    type: "text",
                    label: "Фамилия",
                    readonly: true,
                    required: true
                }, {
                    name: "name",
                    type: "text",
                    label: "Имя",
                    readonly: true,
                    required: true
                }, {
                    name: "patronumic",
                    type: "text",
                    label: "Отчество",
                    readonly: true,
                    required: false
                }, {
                    name: "sexId",
                    type: "select",
                    load: SexesService.getList,
                    label: "Пол",
                    readonly: $scope.readonly,
                    required: true
                }, {
                    name: "easuFhdId",
                    type: "text",
                    label: "ID в ЕАСУ ФХД",
                    readonly: true,
                    required: true
                }, {
                    name: "personalNumber",
                    type: "text",
                    label: "Табельный номер",
                    readonly: true,
                    required: true
                }, {
                    name: "licenceDetails",
                    type: "text",
                    label: "Реквизиты служебного удостоверения",
                    readonly: $scope.readonly,
                    required: false
                }, {
                    name: "departmentId",
                    type: "select",
                    load: function () {
                        return DepartmentsService.getList([{
                            name: "currentId",
                            value: $scope.fields[7].value,
                            type: "select"
                        }
                        ]);
                    },
                    label: "Подразделение",
                    readonly: true,
                    required: true
                }, {
                    name: "periodStartDate",
                    type: "date",
                    label: "Дата начала работы в подразделении",
                    readonly: true
                }, {
                    name: "periodEndDate",
                    type: "date",
                    label: "Дата конца работы в подразделении",
                    readonly: true
                }, {
                    name: "forPlanUse",
                    type: "yesno",
                    label: "Участвует в планировании",
                    readonly: $scope.readonly,
                    required: true
                }, {
                    name: "dvrId",
                    type: "select",
                    load: function () {
                        return DvrsService.getListForEmployee([{
                            name: "dvrId",
                            value: $scope.fields[11].value,
                            type: "select"
                        }
                        ]);
                    },
                    label: "Видеорегистратор",
                    readonly: false,
                    required: false
                }, {
                    name: "contCardId",
                    type: "select",
                    load: function () {
                        return ContactlessCardsService.getListForEmployee([{
                            name: "contCardId",
                            value: $scope.fields[12].value,
                            type: "select"
                        }
                        ]);
                    },
                    label: "БСК",
                    readonly: false,
                    required: false
                }, {
                    name: "offCardId",
                    type: "select",
                    load: function () {
                        return OfficialCardsService.getListForEmployee([{
                            name: "offCardId",
                            value: $scope.fields[13].value,
                            type: "select"
                        }
                        ]);
                    },
                    label: "СКК",
                    readonly: false,
                    required: false
                }, {
                    name: "puskId",
                    type: "select",
                    load: function () {
                        return PusksService.getListForEmployee([{
                            name: "puskId",
                            value: $scope.fields[14].value,
                            type: "select"
                        }
                        ]);
                    },
                    label: "ПУсК",
                    readonly: false,
                    required: false
                }, {
                    name: "userId",
                    type: "select",
                    load: function () {
                        return UsersService.getListForEmployee([{
                            name: "userId",
                            value: $scope.fields[15].value,
                            type: "select"
                        }
                        ]);
                    },
                    label: "Пользователь",
                    readonly: $scope.readonly,
                    required: false
                }, {
                    name: "positionName",
                    type: "text",
                    label: "Должность",
                    readonly: true,
                    required: false
                }, {
                    name: "phone",
                    type: "text",
                    label: "Телефон",
                    readonly: $scope.readonly,
                    required: false
                }, {
                    name: "fireDate",
                    type: "date",
                    label: "Дата увольнения",
                    readonly: false
                }, {
                    name: "description",
                    type: "textarea",
                    label: "Описание",
                    readonly: $scope.readonly,
                    required: false
                }, {
                    name: "countyId",
                    type: "select",
                    load: function () {
                        return CountiesService.getList([{
                            name: "deptCountyId",
                            value: $scope.fields[7].value,
                            type: "select"
                        }]);
                    },
                    label: "Округ сотрудника",
                    readonly: $scope.readonly,
                    required: false
                }, {
                    name: "licenseEndDate",
                    type: "date",
                    label: "Срок действия",
                    readonly: $scope.readonly,
                    required: false
                }];

            EmployeesService.getPhoto($stateParams.itemId).then(
                function(val){
                    $scope.myImage = val;
                }
            );

            $scope.hasUserEditAdminRole = UserService.hasAnyRole(['ADMIN_USER_EDIT']);

            $scope.deletePhoto = function() {
                EmployeesService.deletePhoto($stateParams.itemId);
                $window.location.reload();
            };

            $scope.addPhoto = function() {

                $modal.open({
                    templateUrl: 'templates/dialogs/imageCropper.html',
                    controller: 'imageCropperController',
                    backdrop: 'static',
                    resolve: {
                        cropType: function () {
                            return 'square';
                        }
                    }
                }).result.then(function (val) {
                    // var blob = dataURItoBlob(val, 'image/png');
                    //
                    // $scope.myCroppedImage = URL.createObjectURL(blob);

                    $scope.myImage = val;

                    EmployeesService.addPhoto($stateParams.itemId, val);
                });

            };

            $scope.createDvr = function () {
                var modalInstance = $modal.open({
                    templateUrl: "templates/dialogs/pbk/nsi/dvrs/DvrEditDlg.html",
                    windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
                    controller: "DvrEditCtrl",
                    size: "md",
                    resolve: {
                        data: function () {
                            return {
                                title: "Создание видеорегистратора"
                            }
                        }
                    }
                });
                modalInstance.result.then(function (i) {
                    setNewValue(11);
                });
            };

            $scope.createBsk = function () {
                var modalInstance = $modal.open({
                    templateUrl: "templates/dialogs/pbk/nsi/contactless-cards/ContactlessCardEditDlg.html",
                    windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
                    controller: "ContactlessCardEditCtrl",
                    size: "md",
                    resolve: {
                        data: function () {
                            return {
                                title: "Создание БСК"
                            }
                        }
                    }
                });
                modalInstance.result.then(function () {
                    setNewValue(12);
                });
            };

            $scope.createSkk = function () {
                var modalInstance = $modal.open({
                    templateUrl: "templates/dialogs/pbk/nsi/official-cards/official-cardEditDlg.html",
                    windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
                    controller: "OfficialCardEditCtrl",
                    size: "md",
                    resolve: {
                        data: function () {
                            return {
                                title: "Создание СКК"
                            }
                        }
                    }
                });
                modalInstance.result.then(function () {
                    setNewValue(13);
                });
            };

            $scope.createPusk = function () {
                var modalInstance = $modal.open({
                    templateUrl: "templates/dialogs/pbk/nsi/pusks/PuskEditDlg.html",
                    windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
                    controller: "PuskEditCtrl",
                    size: "md",
                    resolve: {
                        data: function () {
                            return {
                                title: "Создание ПУсК"
                            }
                        }
                    }
                });
                modalInstance.result.then(function () {
                    setNewValue(14);
                });
            };

            $scope.isFire = function () {
                return !isNaN($scope.fields[18].value.getTime());
            }

            $scope.callbackResetAction = function () {
                if ($scope.isFire()) {
                    $scope.fields[3].readonly = true;
                    $scope.fields[6].readonly = true;
                    $scope.fields[10].readonly = true;
                    //$scope.fields[11].readonly = true;
                    //$scope.fields[12].readonly = true;
                    //$scope.fields[13].readonly = true;
                    //$scope.fields[14].readonly = true;
                    $scope.fields[15].readonly = true;
                    $scope.fields[17].readonly = true;
                    //$scope.fields[18].readonly = true;
                    $scope.fields[19].readonly = true;
                    $scope.fields[20].readonly = true;
                    $scope.readonly = true;
                }
            }

            function setNewValue(fieldId) {
                if ($scope.fields[fieldId].load != null) {
                    $scope.fields[fieldId].oldList = $scope.fields[fieldId].list;
                    $scope.fields[fieldId].load().then(function (response) {
                        $scope.fields[fieldId].list = response.slice();
                        for (var i = 0; i < $scope.fields[fieldId].list.length; i++) {
                            var found = false;
                            for (var j = 0; j < $scope.fields[fieldId].oldList.length && !found; j++) {
                                if ($scope.fields[fieldId].list[i].id == $scope.fields[fieldId].oldList[j].id) {
                                    found = true;
                                }
                            }
                            if (!found) {
                                $scope.fields[fieldId].value = $scope.fields[fieldId].list[i];
                                break;
                            }
                        }
                    });
                }
            }

            /* Инициализация */
            $controller('BaseEditCtrl', {$scope: $scope});
            $scope.init({id: $stateParams.itemId}, $scope.fields, EmployeesService);
        }]);