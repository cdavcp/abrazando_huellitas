define([], function() {
	var constructor = {};
	constructor.init = function(module) {
		module.service("ComisionTemplateService", ["$http", "$resource" ,"Upload", function($http, $resource, Upload) {
			var service = {};
            var url = REST_DIR + '/comision';

            service.inicializarCreateArchivo = function(idUsuario){
                angular.element('#spinner').show();
                return $http.post(url + "/initArchivo", idUsuario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.inicializarCreateManual = function(idUsuario){
                angular.element('#spinner').show();
                return $http.post(url + "/initManual", idUsuario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.inicializarEdit = function(idLote){
                angular.element('#spinner').show();
                return $http.post(url + "/initEdit", idLote).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.inicializarView = function(idLote){
                angular.element('#spinner').show();
                return $http.post(url + "/initView", idLote).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.inicializarIndex = function(idUsuario){
                angular.element('#spinner').show();
                return $http.post(url + "/initIndex", idUsuario).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.findPolizas = function(aseguradoraId, productorId){
                angular.element('#spinner').show();
                return $http.post(url + "/findPolizas", {aseguradoraId: aseguradoraId, productorId: productorId}).success(function (data) {
                    angular.element('#spinner').hide();
                    return data;
                })
            };

            service.delete = function(loteId){
                angular.element('#spinner').show();
                return $http.post(url + "/delete", loteId);
            };

            service.procesarManual = function(lote, listaNuevasComisiones){
                angular.element('#spinner').show();
                return $http.post(url + "/procesarManual", {lote: lote, listaNuevasComisiones: listaNuevasComisiones});
            };

            service.procesarActualizacion = function(lote, listaNuevasComisiones, listaComisionesBorradas){
                angular.element('#spinner').show();
                return $http.post(url + "/procesarActualizacion", {lote: lote, listaNuevasComisiones: listaNuevasComisiones, listaComisionesBorradas:listaComisionesBorradas});
            };

            service.procesarArchivo = function(lote, file){
                angular.element('#spinner').show();
                return Upload.upload({
                    url: url,
                    fields: {lote:lote},
                    file: file
                }).success(function(data){
                    return data;
                });
            };
			return service;
		}]);
	};
	return constructor;
});