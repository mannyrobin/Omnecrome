'use strict';

/**
 * @ngdoc function
 * @name armada.filter:to_kb
 * @description
 *
 * ������ ��� �������� ������ � ���������
 */
angular.module('armada')
	.filter('to_kb', function () {
		return function (text) {
			return Math.floor(parseInt(text) / 1024);
		};
	});
