(function() {
    'use strict';

    angular.module('app').directive('somenteNumeros', function() {
        return {
            require: 'ngModel',
            link: function(scope, element, attrs, ctrl) {

                ctrl.$parsers.push(function(inputValue) {
                    var output = inputValue ? inputValue.replace(/[^\d,]/g,'') : null;

                    if (output != inputValue) {
                        ctrl.$setViewValue(output);
                        ctrl.$render();
                    }

                    return output;
                });
            }
        };
    });
})();