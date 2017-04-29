define(["factorizator"], function(factorizator) {
	var constructor = {};
	/* registerModule(name, [options])
		* name: nombre del modulo
		* options (optional): objeto con la configuracion del modulo. Posibles propiedades:
			- customStates: en caso de no seguir la convencion de los futureStates indicar "true" (default: "false")
			- files: en caso de no seguir la convencion, indicar donde estan los archivos (debe ser un array)
			- dependencies: si tiene dependencias externas indicar como array
			- reconfig: si deberia lanzarse el reconfig del modulo o no
			- rerun: si deberia lanzarse el rerun del modulo o no
	*/

    factorizator.registerModule("Vendedor");
    factorizator.registerModule("Productor");
    factorizator.registerModule("Usuario");
    factorizator.registerModule("Artistas");
    factorizator.registerModule("Aseguradora");
    factorizator.registerModule("Cobertura");
    factorizator.registerModule("Cliente");
    factorizator.registerModule("Riesgo");
    factorizator.registerModule("Poliza");
    factorizator.registerModule("Rubro");
    factorizator.registerModule("Comision");
    factorizator.registerModule("BandejaAsignacion");
    factorizator.registerModule("BandejaSolicitud");
    factorizator.registerModule("EstadisticasProductor");
    factorizator.registerModule("Lugar");
    factorizator.registerModule("Solicitud");
    factorizator.registerModule("Reserva");
    factorizator.registerModule("Notificaciones");


	constructor.init = function($lazyLoad) {
		factorizator.ready($lazyLoad);
	};
	return constructor;
});