(function() {
	'use strict';

	angular.module('app').factory('FormService', FormService);

	FormService.$inject = ['$injector'];

	function FormService($injector) {

		var Restangular = $injector.get('Restangular');

		var metodosPublicos = {};

		metodosPublicos.gerarObservacoes = _gerarObservacoes;
		metodosPublicos.getCusto = _getCusto;
		metodosPublicos.getTiposVeiculos = _getTiposVeiculos;

		function _gerarObservacoes() {
			return Restangular.one('faturas/observacoes').get();
		}

		function _getCusto(params) {
			return Restangular.one('custos').get(params);
		}

		function _getTiposVeiculos() {
			return Restangular.all('tipos-veiculos').getList();
		}

		return metodosPublicos;
	}
})();