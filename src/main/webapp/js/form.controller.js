(function() {
	'use strict';

    angular.module('app').controller('FormCtrl', FormCtrl);

    FormCtrl.$inject = ['$injector'];

    function FormCtrl($injector) {
        var vm = this;

        var FormService = $injector.get('FormService');

        vm.tiposVeiculos = [];
        vm.pesos = [];

        var propriedadesPublicas = {
            calcular: _calcular,
            isFormValid: _isFormValid,
            gerarObservacoes: _gerarObservacoes,
            limpar: _limpar
        };

        _.extend(vm, propriedadesPublicas);

        init();

        function init() {
            initTiposVeiculos();
            initPesos();

            function initTiposVeiculos() {
                FormService.getTiposVeiculos().then(function(response) {
                    vm.tiposVeiculos = response;
                });
            }

            function initPesos() {
                for (var i = 1; i <= 50; i++) {
                    vm.pesos.push(i);
                }
            }
        }

        function _calcular(params) {
            params.percursoPavimentado = params.percursoPavimentado || 0.0;
            params.percursoNaoPavimentado = params.percursoNaoPavimentado || 0.0;

            return FormService.getCusto(params).then(function(response) {
                vm.custoTotal = response;
            });
        }

        function _gerarObservacoes() {
            return FormService.gerarObservacoes().then(function(response) {
                vm.notasMultiplasComValor = response.notasMultiplasComValor;
                vm.notasMultiplasSemValor = response.notasMultiplasSemValor;
                vm.notaUnicaComValor = response.notaUnicaComValor;
                vm.notaUnicaSemValor = response.notaUnicaSemValor;
            });
        }

        function _isFormValid() {
            return Boolean(vm.custo && vm.custo.tipoVeiculo && vm.custo.peso && (vm.custo.percursoPavimentado || vm.custo.percursoNaoPavimentado) && (vm.custo.percursoPavimentado > 0 || vm.custo.percursoNaoPavimentado > 0));
        }

        function _limpar() {
            if (!vm.custo) return;
            vm.custo.percursoPavimentado = undefined;
            vm.custo.percursoNaoPavimentado = undefined;
            vm.custo.tipoVeiculo = undefined;
            vm.custo.peso = undefined;
            vm.custoTotal = undefined;
        }
    };
})();