define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.service("EstadisticasProductorService", ["$http", "$resource", function($http, $resource) {
			var service = {};
            var url = REST_DIR + '/estadisticasProductor';

            service.inicializarEstadisticasPolizas = function(filtroId, fechaDesde, fechaHasta, usuarioId){
                angular.element('#spinner').show();
                return $http.post(url + "/inicializarEstadisticasPolizas", {idFiltro: filtroId, fechaDesde: fechaDesde, fechaHasta: fechaHasta, idUsuario: usuarioId}).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.inicializarEstadisticasVendedores = function(anio, usuarioId){
                angular.element('#spinner').show();
                return $http.post(url + "/inicializarEstadisticasVendedores", {anio: anio, idUsuario: usuarioId}).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.inicializarEstadisticasSolicitudes = function(anio, usuarioId){
                angular.element('#spinner').show();
                return $http.post(url + "/inicializarEstadisticasSolicitudes", {anio: anio, idUsuario: usuarioId}).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.inicializarEstadisticasRentabilidad = function(filtroId, fechaDesde, fechaHasta, usuarioId){
                angular.element('#spinner').show();
                return $http.post(url + "/inicializarEstadisticasRentabilidad", {idFiltro: filtroId, fechaDesde: fechaDesde, fechaHasta: fechaHasta, idUsuario: usuarioId}).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.inicializarEstadisticasRentabilidadMultilinea = function(filtroId, anio, usuarioId){
                angular.element('#spinner').show();
                return $http.post(url + "/inicializarEstadisticasRentabilidadMultilinea", {idFiltro: filtroId, anio:anio, idUsuario: usuarioId}).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.inicializarEstadisticasComisionesProductor = function(mes, anio, usuarioId){
                angular.element('#spinner').show();
                return $http.post(url + "/inicializarEstadisticasComisionesProductor", {mes: mes, anio:anio, idUsuario: usuarioId}).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.inicializarEstadisticasComisionesVendedor = function(mes, anio, usuarioId){
                angular.element('#spinner').show();
                return $http.post(url + "/inicializarEstadisticasComisionesVendedor", {mes: mes, anio:anio, idUsuario: usuarioId}).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.inicializarEstadisticasProductores = function(anio, usuarioId){
                angular.element('#spinner').show();
                return $http.post(url + "/inicializarEstadisticasProductores", {anio: anio, idUsuario: usuarioId}).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.getFiltros = function(){
                angular.element('#spinner').show();
                return $http.get(url + "/getFiltros").success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

			return service;
		}]);
	};
	return constructor;
});