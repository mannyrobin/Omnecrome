angular.module('armada')
    .controller('imageCropperController', ['$scope', '$modalInstance', '$log', '$timeout', 'cropType', function($scope, $modalInstance, $log, $timeout, cropType) {

        $scope.submit = function () {
            $log.debug("modal delete submit");

            $modalInstance.close($scope.myImage);
        };

        $scope.myImage='';
        $scope.myCroppedImage='';
        $scope.cropType=cropType;

        var handleFileSelect=function(evt) {
            var file=evt.currentTarget.files[0];
            var reader = new FileReader();
            reader.onload = function (evt) {
                $scope.$apply(function($scope){
                    $scope.myImage=evt.target.result;
                });
            };
            reader.readAsDataURL(file);
        };

        $modalInstance.opened.then(
            $timeout(function() {
                angular.element(document.querySelector('#fileInput')).on('change',handleFileSelect);
            }, 100));

        $scope.cancel = function () {
            $log.debug("modal delete scancel");

            $modalInstance.dismiss(false);
        };

    }]);