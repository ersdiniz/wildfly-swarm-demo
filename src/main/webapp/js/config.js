(function(){
	'use strict';

	var module = angular.module('app');

    moment.locale('pt-br');

    module.config(['RestangularProvider', function(RestangularProvider) {
            RestangularProvider.setBaseUrl('http://localhost:8080/api/');
        }
    ]);
})();