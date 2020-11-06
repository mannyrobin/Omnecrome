angular.module('armada')
	.controller('DvrRecordPlayCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'DvrRecordsService',
		function ($controller, $scope, $modalInstance, data, DvrRecordsService) {
			$scope.title = data.title;
			$scope.modalInstance = $modalInstance;

            setTimeout(function(){
                document.getElementById('video').addEventListener('error', function(event) {
                    $("#error-video-message").removeClass("hide");
                    $("#spinner-video-loader").addClass("hide");
                }, false);
                document.getElementById('video').addEventListener('canplay', function(event) {
                    $("#error-video-message").addClass("hide");
                    $("#spinner-video-loader").addClass("hide");
                }, false);
            },300);

			$scope.close = function () {
				if ($scope.modalInstance) {
					$scope.modalInstance.close();
				}
			}

			$scope.play = function () {
				return DvrRecordsService.play(data.url);
			}

		}]);