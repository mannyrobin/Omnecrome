angular.module('armada')
    .controller('BsoTrashInfoCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'UserService', 'DepartmentsService', 'BsosService', 'EmployeesService', 'Restangular', 'UrlService', 'UtilService', 'Notification',
        function ($controller, $scope, $modalInstance, data, UserService, DepartmentsService, BsosService, EmployeesService, Restangular, UrlService, UtilService, Notification) {
            var serviceUrl = UrlService.getPrefix() + 'nsi/bsos';

            /* Поля ввода */
            $scope.fields = [
                {
                    name: "issueDate",
                    type: "text",
                    label: "Дата выдачи",
                    defval: "",
                    readonly: true
                }, {
                    name: "endDate",
                    type: "text",
                    label: "Дата списания",
                    defval: "",
                    readonly: true
                }, {
                    name: "reason",
                    type: "text",
                    label: "Причина списания",
                    defval: "",
                    readonly: true
                }, {
                    name: "employeeOffId",
                    type: "text",
                    label: "Ф.И.О. Списавшего",
                    defval: "",
                    readonly: true
                }, {
                    name: "login",
                    type: "text",
                    label: "Логин",
                    defval: "",
                    readonly: true
                }, {
                    name: "actNumber",
                    type: "text",
                    label: "Номер акта",
                    defval: "",
                    readonly: true
                }];

            /* Инициализация */
            $controller('BaseEditCtrl', {$scope: $scope});
            $scope.init(data, $scope.fields, BsosService, $modalInstance);

            BsosService.getTrashBsoByBsoId(data.firstBsoId).then(function (response) {
                if (response !== undefined) {
                    $scope.fields[0].value = response.issueDate;
                    $scope.fields[1].value = moment(response.endDate).format("DD.MM.YYYY");
                    $scope.fields[2].value = response.reason;
                    $scope.fields[3].value = response.employeeOffName;
                    $scope.fields[4].value = response.createUserLogin;
                    $scope.fields[5].value = response.actNumber;
                }
            });
        }]);
