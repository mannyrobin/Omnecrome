angular.module('armada')
    .controller('BsoTrashCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'UserService', 'DepartmentsService', 'BsosService', 'EmployeesService', 'Restangular', 'UrlService', 'UtilService', 'Notification',
        function ($controller, $scope, $modalInstance, data, UserService, DepartmentsService, BsosService, EmployeesService, Restangular, UrlService, UtilService, Notification) {
            var serviceUrl = UrlService.getPrefix() + 'nsi/bsos';

            var now = new Date();

            /* Поля ввода */
            $scope.fields = [
                {
                    name: "issueDate",
                    type: "text",
                    label: "Дата выдачи",
                    required: true,
                    readonly: true
                }, {
                    name: "endDate",
                    type: "date",
                    label: "Дата списания",
                    defval: now,
                    required: true
                }, {
                    name: "reason",
                    type: "text",
                    label: "Причина списания",
                    required: true
                }, {
                    name: "employeeOffId",
                    type: "select",
                    load: function () {
                        return EmployeesService.getList([{
                            name: "isNotFire",
                            value: 1,
                            type: "id"
                        }]);
                    },
                    label: "Ф.И.О. Списавшего",
                    required: true
                }, {
                    name: "login",
                    type: "text",
                    label: "Логин",
                    defval: UserService.getCurrUsrLogin(),
                    required: true,
                    readonly: true
                }, {
                    name: "actNumber",
                    type: "text",
                    label: "Номер акта",
                    //defval: data.firstBso.bsoNumber,
                    required: true
                }];

            $scope.trash = function () {
                var trashBso = {
                    bsoId: data.firstBso.id,
                    issueDate: $scope.fields[0].value,
                    endDate: $scope.fields[1].value,
                    reason: $scope.fields[2].value,
                    employeeOffId: $scope.fields[3].value.id,
                    actNumber: $scope.fields[5].value
                };
                BsosService.trashBso(trashBso).then(function (result) {
                    $modalInstance.close();
                }).catch(function (response) {
                    var errors = UtilService.getFormErrors(response);
                    //var errMsg = errors.errMessages[0];
                    //Notification.error(errMsg);
                    $scope.errTitle = errors.errTitle;
                    $scope.errMessages = errors.errMessages;
                });
            };

            /* Инициализация */
            $controller('BaseEditCtrl', {$scope: $scope});
            $scope.init(data, $scope.fields, BsosService, $modalInstance);

            BsosService.getBindDateById(data.firstBso.id).then(function (response) {
                $scope.fields[0].value = response;
            });
        }]);
