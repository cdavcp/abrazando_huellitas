define(["bootbox","googleCharts"], function (bootbox) {
    var constructor = {};
    constructor.init = function (module) {
        module.controller("EstadisticasProductorIndexCtrl", ["$state", "$stateParams", "$filter", "$scope", "$rootScope", "EstadisticasProductorService", "googleChartApiPromise", function ($state, $stateParams, $filter, $scope, $rootScope, service, googlePromise) {

            $scope.TIPO_BARRAS_HORIZONTALES = "BarChart";
            $scope.TIPO_BARRAS_VERTICALES = "ColumnChart";
            $scope.TIPO_TORTA = "PieChart";
            $scope.TIPO_LINEAS = "LineChart";
            $scope.TIPO_AREA = "AreaChart";
            $scope.TIPO_TABLE = "Table";
            $scope.FECHA_DESDE = new Date(2014, 0, 01);
            $scope.ID_USUARIO_ADMINISTRADOR = 1;

            $scope.ANIOS = [2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015];
            $scope.MESES = [{id:1, nombre: "Enero"},{id:2, nombre: "Febrero"},{id:3, nombre: "Marzo"},{id:4, nombre: "Abril"},{id:5, nombre: "Mayo"},{id:6, nombre: "Junio"},{id:7, nombre: "Julio"},{id:8, nombre: "Agosto"},{id:9, nombre: "Septiembre"},{id:10, nombre: "Octubre"},{id:11, nombre: "Noviembre"},{id:12, nombre: "Diciembre"}];

            $scope.init = function () {
                $scope.data = {};
                service.getFiltros().success(function(data){
                    $scope.data.listaFiltros = data;

                    //INICIALIZO EL FILTRO CON EL PRIMERO - POLIZAS
                    $scope.data.filtroPoliza = {};
                    $scope.data.filtroPoliza.combo = $scope.data.listaFiltros[0];
                    $scope.data.filtroPoliza.fechaDesde = $scope.FECHA_DESDE;
                    $scope.data.filtroPoliza.fechaHasta = new Date();
                    $scope.buscarDatosEstadisticasPolizas();
                });
            };

            //************************************************
            //*******************POLIZAS**********************
            //************************************************

            $scope.buscarDatosEstadisticasPolizas = function(){
                if(!$scope.data.filtroPoliza.fechaDesde){
                    $scope.data.filtroPoliza.fechaDesde = $scope.FECHA_DESDE;
                    $scope.data.filtroPoliza.fechaHasta = new Date();
                }
                else{
                    if($scope.data.filtroPoliza.fechaDesde > $scope.data.filtroPoliza.fechaHasta){
                        showErrorFechas();
                    }
                    else{
                        service.inicializarEstadisticasPolizas($scope.data.filtroPoliza.combo.id, $scope.data.filtroPoliza.fechaDesde, $scope.data.filtroPoliza.fechaHasta, $rootScope.userProfile.id).success(function (data) {
                            $scope.data.graficosPolizas = {};
                            $scope.data.graficosPolizas.items = data.items;
                            $scope.generarEstadisticasPolizas();
                        });
                    }
                }
            };

            $scope.generarEstadisticasPolizas = function generarEstadisticasPolizas() {
                //GENERAR ROWS
                $scope.data.graficosPolizas.rows = [];
                angular.forEach($scope.data.graficosPolizas.items, function (item) {
                    $scope.data.graficosPolizas.rows.push({
                        c: [
                            {v: item.nombre},
                            {v: item.cantidad}
                        ]
                    });
                });
                //GENERAR COLS
                $scope.data.graficosPolizas.cols = [
                    {id: "t", label: $scope.data.filtroPoliza.combo.nombre, type: "string"},
                    {id: "s", label: "Pólizas", type: "number"}
                ];

                //GENERAR GRAFICOS
                generarPolizasBarras();
                generarPolizasTorta();
                generarPolizasTabla();
            };

            function generarPolizasBarras(){
                $scope.data.graficosPolizas.barras = {};
                $scope.data.graficosPolizas.barras.data = {
                    "cols": $scope.data.graficosPolizas.cols,
                    "rows": $scope.data.graficosPolizas.rows
                };
                $scope.data.graficosPolizas.barras.type = $scope.TIPO_BARRAS_VERTICALES;
                $scope.data.graficosPolizas.barras.options = {
                    'title': 'Pólizas vendidas por aseguradora',
                    vAxis: {minValue: 0},
                    'height':400
                };
            };

            function generarPolizasTorta(){
                $scope.data.graficosPolizas.torta = {};
                $scope.data.graficosPolizas.torta.data = {
                    "cols":$scope.data.graficosPolizas.cols,
                    "rows": $scope.data.graficosPolizas.rows
                };
                $scope.data.graficosPolizas.torta.type = $scope.TIPO_TORTA;
                $scope.data.graficosPolizas.torta.options = {
                    'title': 'Porcentajes',
                    'height':400
                };
            };

            function generarPolizasTabla(){
                $scope.data.graficosPolizas.tabla = {};

                $scope.data.graficosPolizas.tabla.data = {
                    "cols":$scope.data.graficosPolizas.cols,
                    "rows": $scope.data.graficosPolizas.rows
                };

                $scope.data.graficosPolizas.tabla.type = $scope.TIPO_TABLE;
                $scope.data.graficosPolizas.tabla.options = {
                    'title': 'Detalle',
                    'width': '100%'
                };
            };

            //************************************************
            //*******************VENDEDORES*******************
            //************************************************

            $scope.buscarDatosEstadisticasVendedores = function(){
                if(!$scope.data.filtroVendedores){
                    $scope.data.filtroVendedores = {};
                    $scope.data.filtroVendedores.anio = new Date().getFullYear();
                }
                service.inicializarEstadisticasVendedores($scope.data.filtroVendedores.anio, $rootScope.userProfile.id).success(function (data) {
                    $scope.data.graficosVendedores = {};
                    $scope.data.graficosVendedores.meses = data.meses;
                    $scope.data.graficosVendedores.items = data.items;
                    generarEstadisticasVendedores();
                });
            };

            function generarEstadisticasVendedores(){

                $scope.data.graficosVendedores.rows = [];
                $scope.data.graficosVendedores.cols = [
                    {id: "t", label: "Topping", type: "string"}
                ];

                //RELLENO COLS
                angular.forEach($scope.data.graficosVendedores.items, function(item){
                    $scope.data.graficosVendedores.cols.push(
                        {id: "s", label: item.nombre, type: "number"}
                    )
                });

                for(i=0;i<$scope.data.graficosVendedores.meses.length;i++){
                    var itemRow = {
                        c: [{v: $scope.data.graficosVendedores.meses[i].nombre}]
                    };
                    angular.forEach($scope.data.graficosVendedores.meses[i].lista, function(cantidad){
                        itemRow.c.push({v: cantidad});
                    });
                    $scope.data.graficosVendedores.rows.push(itemRow);
                }

                //GENERAR GRAFICOS
                generarVendedoresLineas();
                generarVendedoresBarras();
            };

            function generarVendedoresLineas(){
                $scope.data.graficosVendedores.lineas = {};
                $scope.data.graficosVendedores.lineas.data = {
                    "cols":$scope.data.graficosVendedores.cols,
                    "rows": $scope.data.graficosVendedores.rows
                };
                $scope.data.graficosVendedores.lineas.type = $scope.TIPO_AREA;
                $scope.data.graficosVendedores.lineas.options = {
                    'title': 'Comparación de ventas de vendedores por mes',
                    'height':400
                };
            };

            function generarVendedoresBarras(){
                $scope.data.graficosVendedores.barras = {};
                $scope.data.graficosVendedores.barras.data = {
                    "cols":$scope.data.graficosVendedores.cols,
                    "rows": $scope.data.graficosVendedores.rows
                };
                $scope.data.graficosVendedores.barras.type = $scope.TIPO_BARRAS_VERTICALES;
                $scope.data.graficosVendedores.barras.options = {
                    'title': 'Pólizas vendidas por mes',
                    "isStacked": "true",
                    'height':400,
                    vAxis: {minValue: 0}
                };
            };

            //************************************************
            //*******************SOLICITUDES*******************
            //************************************************

            $scope.buscarDatosEstadisticasSolicitudes = function(){
                if(!$scope.data.filtroSolicitudes){
                    $scope.data.filtroSolicitudes = {};
                    $scope.data.filtroSolicitudes.anio = new Date().getFullYear();
                }
                service.inicializarEstadisticasSolicitudes($scope.data.filtroSolicitudes.anio, $rootScope.userProfile.id).success(function (data) {
                    $scope.data.graficosSolicitudes = {};
                    $scope.data.graficosSolicitudes.meses = data.meses;
                    $scope.data.graficosSolicitudes.items = data.items;
                    generarEstadisticasSolicitudes();
                });
            };

            function generarEstadisticasSolicitudes(){

                $scope.data.graficosSolicitudes.rows = [];
                $scope.data.graficosSolicitudes.cols = [
                    {id: "t", label: "Topping", type: "string"}
                ];

                //RELLENO COLS
                angular.forEach($scope.data.graficosSolicitudes.items, function(item){
                    $scope.data.graficosSolicitudes.cols.push(
                        {id: "s", label: item.nombre, type: "number"}
                    )
                });

                for(i=0;i<$scope.data.graficosSolicitudes.meses.length;i++){
                    var itemRow = {
                        c: [{v: $scope.data.graficosSolicitudes.meses[i].nombre}]
                    };
                    angular.forEach($scope.data.graficosSolicitudes.meses[i].lista, function(cantidad){
                        itemRow.c.push({v: cantidad});
                    });
                    $scope.data.graficosSolicitudes.rows.push(itemRow);
                }

                //GENERAR GRAFICOS
                generarSolicitudesLineas();
                generarSolicitudesBarras();
            };

            function generarSolicitudesLineas(){
                $scope.data.graficosSolicitudes.lineas = {};
                $scope.data.graficosSolicitudes.lineas.data = {
                    "cols":$scope.data.graficosSolicitudes.cols,
                    "rows": $scope.data.graficosSolicitudes.rows
                };
                $scope.data.graficosSolicitudes.lineas.type = $scope.TIPO_AREA;
                $scope.data.graficosSolicitudes.lineas.options = {
                    'title': 'Comparación de solicitudes por mes y estado',
                    'height':400
                };
            };

            function generarSolicitudesBarras(){
                $scope.data.graficosSolicitudes.barras = {};
                $scope.data.graficosSolicitudes.barras.data = {
                    "cols":$scope.data.graficosSolicitudes.cols,
                    "rows": $scope.data.graficosSolicitudes.rows
                };
                $scope.data.graficosSolicitudes.barras.type = $scope.TIPO_BARRAS_VERTICALES;
                $scope.data.graficosSolicitudes.barras.options = {
                    'title': 'Solicitudes por mes',
                    "isStacked": "true",
                    'height':400,
                    vAxis: {minValue: 0}
                };
            };


            //************************************************
            //*******************RENTABILIDAD*****************
            //************************************************
            $scope.initEstadisticasRentabilidad = function(){
                //INICIALIZO EL FILTRO CON EL PRIMERO - RENTABILIDAD
                $scope.data.filtroRentabilidad = {};
                $scope.data.filtroRentabilidad.combo = $scope.data.listaFiltros[0];
                $scope.data.filtroRentabilidad.fechaDesde = $scope.FECHA_DESDE;
                $scope.data.filtroRentabilidad.fechaHasta = new Date();
                $scope.buscarDatosEstadisticasRentabilidad();
            };

            $scope.buscarDatosEstadisticasRentabilidad = function(){
                if(!$scope.data.filtroRentabilidad.fechaDesde){
                    $scope.data.filtroRentabilidad.fechaDesde = $scope.FECHA_DESDE;
                    $scope.data.filtroRentabilidad.fechaHasta = new Date();
                }
                else{
                    if($scope.data.filtroRentabilidad.fechaDesde > $scope.data.filtroRentabilidad.fechaHasta){
                        showErrorFechas();
                    }
                    else{
                        service.inicializarEstadisticasRentabilidad($scope.data.filtroRentabilidad.combo.id, $scope.data.filtroRentabilidad.fechaDesde, $scope.data.filtroRentabilidad.fechaHasta, $rootScope.userProfile.id).success(function (data) {
                            $scope.data.graficosRentabilidad = {};
                            $scope.data.graficosRentabilidad.items = data.items;
                            generarEstadisticasRentabilidad();
                        });
                    }
                }
            };

            function generarEstadisticasRentabilidad() {
                //GENERAR ROWS
                $scope.data.graficosRentabilidad.rows = [];
                angular.forEach($scope.data.graficosRentabilidad.items, function (item) {
                    $scope.data.graficosRentabilidad.rows.push({
                        c: [
                            {v: item.nombre},
                            {v: item.cantidad}
                        ]
                    });
                });

                //GENERAR COLS
                $scope.data.graficosRentabilidad.cols = [
                    {id: "t", label: $scope.data.filtroRentabilidad.combo.nombre, type: "string"},
                    {id: "s", label: "Rentabilidad", type: "number"}
                ];

                //GENERAR GRAFICOS
                generarRentabilidadBarras();
                generarRentabilidadTorta();
                generarRentabilidadTabla();
            };

            function generarRentabilidadBarras(){
                $scope.data.graficosRentabilidad.barras = {};
                $scope.data.graficosRentabilidad.barras.data = {
                    "cols": $scope.data.graficosRentabilidad.cols,
                    "rows": $scope.data.graficosRentabilidad.rows
                };
                formatCurrency($scope.data.graficosRentabilidad.barras.data);
                $scope.data.graficosRentabilidad.barras.type = $scope.TIPO_BARRAS_VERTICALES;
                $scope.data.graficosRentabilidad.barras.options = {
                    'title': 'Rentabilidad',
                    'height':400,
                    vAxis: {minValue: 0}
                };
            };

            function generarRentabilidadTorta(){
                $scope.data.graficosRentabilidad.torta = {};
                $scope.data.graficosRentabilidad.torta.data = {
                    "cols":$scope.data.graficosRentabilidad.cols,
                    "rows": $scope.data.graficosRentabilidad.rows
                };
                $scope.data.graficosRentabilidad.torta.type = $scope.TIPO_TORTA;
                $scope.data.graficosRentabilidad.torta.options = {
                    'title': 'Porcentajes',
                    'height':400
                };
            };

            function generarRentabilidadTabla(){
                $scope.data.graficosRentabilidad.tabla = {};
                $scope.data.graficosRentabilidad.tabla.data = {
                    "cols":$scope.data.graficosRentabilidad.cols,
                    "rows": $scope.data.graficosRentabilidad.rows
                };
                $scope.data.graficosRentabilidad.tabla.type = $scope.TIPO_TABLE;
                $scope.data.graficosRentabilidad.tabla.options = {
                    'title': 'Detalle',
                    'width':'100%'
                };
            };

            //************************************************
            //*********RENTABILIDAD MULTILINEA****************
            //************************************************
            $scope.initEstadisticasRentabilidadMultilinea = function(){
                //INICIALIZO EL FILTRO CON EL PRIMERO - RENTABILIDAD
                $scope.data.filtroRentabilidadMultilinea = {};
                $scope.data.filtroRentabilidadMultilinea.combo = $scope.data.listaFiltros[0];
                $scope.data.filtroRentabilidadMultilinea.anio = new Date().getFullYear();
                $scope.buscarDatosEstadisticasRentabilidadMultilinea();
            };

            $scope.buscarDatosEstadisticasRentabilidadMultilinea = function(){
                if(!$scope.data.filtroRentabilidadMultilinea.anio){
                    $scope.data.filtroRentabilidadMultilinea.anio = new Date().getFullYear();
                }
                service.inicializarEstadisticasRentabilidadMultilinea($scope.data.filtroRentabilidadMultilinea.combo.id, $scope.data.filtroRentabilidadMultilinea.anio, $rootScope.userProfile.id).success(function (data) {
                    $scope.data.graficosRentabilidadMultilinea = {};
                    $scope.data.graficosRentabilidadMultilinea.meses = data.meses;
                    $scope.data.graficosRentabilidadMultilinea.items = data.items;
                    if($scope.data.graficosRentabilidadMultilinea.items){
                        generarEstadisticasRentabilidadMultilinea();
                    }
                });
            };

            function generarEstadisticasRentabilidadMultilinea(){
                $scope.data.graficosRentabilidadMultilinea.rows = [];
                $scope.data.graficosRentabilidadMultilinea.cols = [
                    {id: "t", label: "Topping", type: "string"}
                ];

                //RELLENO COLS
                angular.forEach($scope.data.graficosRentabilidadMultilinea.items, function(item){
                    $scope.data.graficosRentabilidadMultilinea.cols.push(
                        {id: "s", label: item.nombre, type: "number"}
                    )
                });

                for(i=0;i<$scope.data.graficosRentabilidadMultilinea.meses.length;i++){
                    var itemRow = {
                        c: [{v: $scope.data.graficosRentabilidadMultilinea.meses[i].nombre}]
                    };
                    angular.forEach($scope.data.graficosRentabilidadMultilinea.meses[i].lista, function(cantidad){
                        itemRow.c.push({v: cantidad});
                    });
                    $scope.data.graficosRentabilidadMultilinea.rows.push(itemRow);
                }
                generarRentabilidadMultilineaLineas();
                generarRentabilidadMultilineaBarras();
            }

            function generarRentabilidadMultilineaLineas(){
                $scope.data.graficosRentabilidadMultilinea.lineas = {};
                $scope.data.graficosRentabilidadMultilinea.lineas.data = {
                    "cols":$scope.data.graficosRentabilidadMultilinea.cols,
                    "rows": $scope.data.graficosRentabilidadMultilinea.rows
                };
                formatCurrencyMultilinea($scope.data.graficosRentabilidadMultilinea.lineas.data);
                $scope.data.graficosRentabilidadMultilinea.lineas.type = $scope.TIPO_AREA;
                $scope.data.graficosRentabilidadMultilinea.lineas.options = {
                    'title': 'Rentabilidad mensual',
                    'height':400
                };
            };

            function generarRentabilidadMultilineaBarras(){
                $scope.data.graficosRentabilidadMultilinea.barras = {};
                $scope.data.graficosRentabilidadMultilinea.barras.data = {
                    "cols":$scope.data.graficosRentabilidadMultilinea.cols,
                    "rows": $scope.data.graficosRentabilidadMultilinea.rows
                };
                $scope.data.graficosRentabilidadMultilinea.barras.type = $scope.TIPO_BARRAS_VERTICALES;
                $scope.data.graficosRentabilidadMultilinea.barras.options = {
                    'title': 'Rentabilidad mensual',
                    "isStacked": "true",
                    'height':400,
                    vAxis: {minValue: 0}
                };
            };


            //************************************************
            //*******************COMISIONES*******************
            //************************************************

            $scope.initEstadisticasComisiones = function(){
                $scope.data.filtroComisiones = {};
                var today = new Date();
                $scope.data.filtroComisiones.combo = $scope.MESES[today.getMonth()];
                $scope.data.filtroComisiones.anio = today.getFullYear();
                $scope.buscarDatosEstadisticasComisiones();
            };

            $scope.buscarDatosEstadisticasComisiones = function(){
                if(!$scope.data.filtroComisiones.anio){
                    $scope.data.filtroComisiones.anio = new Date().getFullYear();
                }
                else{
                    service.inicializarEstadisticasComisionesProductor($scope.data.filtroComisiones.combo.id, $scope.data.filtroComisiones.anio, $rootScope.userProfile.id).success(function (data) {
                        $scope.data.graficosComisionesProductor = {};
                        $scope.data.graficosComisionesProductor.items = data.items;
                        generarEstadisticasComisionesProductor();
                    });
                    service.inicializarEstadisticasComisionesVendedor($scope.data.filtroComisiones.combo.id, $scope.data.filtroComisiones.anio, $rootScope.userProfile.id).success(function (data) {
                        $scope.data.graficosComisionesVendedor = {};
                        $scope.data.graficosComisionesVendedor.items = data.items;
                        generarEstadisticasComisionesVendedor();
                    });
                }
            };

            function generarEstadisticasComisionesProductor() {
                //GENERAR ROWS
                $scope.data.graficosComisionesProductor.rows = [];
                angular.forEach($scope.data.graficosComisionesProductor.items, function (item) {
                    $scope.data.graficosComisionesProductor.rows.push({
                        c: [
                            {v: item.nombre},
                            {v: item.cantidad}
                        ]
                    });
                });

                //GENERAR COLS
                $scope.data.graficosComisionesProductor.cols = [
                    {id: "t", label: $scope.data.filtroComisiones.combo.nombre, type: "string"},
                    {id: "s", label: "Comisiones", type: "number"}
                ];

                //GENERAR GRAFICOS
                generarRentabilidadTablaProductor();

            };

            function generarEstadisticasComisionesVendedor() {
                //GENERAR ROWS
                $scope.data.graficosComisionesVendedor.rows = [];
                angular.forEach($scope.data.graficosComisionesVendedor.items, function (item) {
                    $scope.data.graficosComisionesVendedor.rows.push({
                        c: [
                            {v: item.nombre},
                            {v: item.cantidad}
                        ]
                    });
                });

                //GENERAR COLS
                $scope.data.graficosComisionesVendedor.cols = [
                    {id: "t", label: $scope.data.filtroComisiones.combo.nombre, type: "string"},
                    {id: "s", label: "Comisiones", type: "number"}
                ];

                //GENERAR GRAFICOS
                generarRentabilidadTablaVendedor();
            };

            function generarRentabilidadTablaProductor(){
                $scope.data.graficosComisionesProductor.tabla = {};
                $scope.data.graficosComisionesProductor.tabla.data = {
                    "cols":$scope.data.graficosComisionesProductor.cols,
                    "rows": $scope.data.graficosComisionesProductor.rows
                };
                formatCurrency($scope.data.graficosComisionesProductor.tabla.data);
                $scope.data.graficosComisionesProductor.tabla.type = $scope.TIPO_TABLE;
                $scope.data.graficosComisionesProductor.tabla.options = {
                    'title': 'Detalle',
                    'width':700
                };
            };

            function generarRentabilidadTablaVendedor(){
                $scope.data.graficosComisionesVendedor.tabla = {};
                $scope.data.graficosComisionesVendedor.tabla.data = {
                    "cols":$scope.data.graficosComisionesVendedor.cols,
                    "rows": $scope.data.graficosComisionesVendedor.rows
                };
                formatCurrency($scope.data.graficosComisionesVendedor.tabla.data);
                $scope.data.graficosComisionesVendedor.tabla.type = $scope.TIPO_TABLE;
                $scope.data.graficosComisionesVendedor.tabla.options = {
                    'title': 'Detalle',
                    'width':700
                };
            };


            //************************************************
            //*******************PRODUCTORES******************
            //************************************************

            $scope.buscarDatosEstadisticasProductores = function(){
                if(!$scope.data.filtroProductores){
                    $scope.data.filtroProductores = {};
                    $scope.data.filtroProductores.anio = new Date().getFullYear();
                }
                service.inicializarEstadisticasProductores($scope.data.filtroProductores.anio, $rootScope.userProfile.id).success(function (data) {
                    $scope.data.graficosProductores = {};
                    $scope.data.graficosProductores.meses = data.meses;
                    $scope.data.graficosProductores.items = data.items;
                    generarEstadisticasProductores();
                });
            };

            function generarEstadisticasProductores(){

                $scope.data.graficosProductores.rows = [];
                $scope.data.graficosProductores.cols = [
                    {id: "t", label: "Topping", type: "string"}
                ];

                //RELLENO COLS
                angular.forEach($scope.data.graficosProductores.items, function(item){
                    $scope.data.graficosProductores.cols.push(
                        {id: "s", label: item.nombre, type: "number"}
                    )
                });

                for(i=0;i<$scope.data.graficosProductores.meses.length;i++){
                    var itemRow = {
                        c: [{v: $scope.data.graficosProductores.meses[i].nombre}]
                    };
                    angular.forEach($scope.data.graficosProductores.meses[i].lista, function(cantidad){
                        itemRow.c.push({v: cantidad});
                    });
                    $scope.data.graficosProductores.rows.push(itemRow);
                }

                //GENERAR GRAFICOS
                generarProductoresLineas();
                generarProductoresBarras();
            };

            function generarProductoresLineas(){
                $scope.data.graficosProductores.lineas = {};
                $scope.data.graficosProductores.lineas.data = {
                    "cols":$scope.data.graficosProductores.cols,
                    "rows": $scope.data.graficosProductores.rows
                };
                $scope.data.graficosProductores.lineas.type = $scope.TIPO_AREA;
                $scope.data.graficosProductores.lineas.options = {
                    'title': 'Comparación de pólizas de productores por mes',
                    'height':400
                };
            };

            function generarProductoresBarras(){
                $scope.data.graficosProductores.barras = {};
                $scope.data.graficosProductores.barras.data = {
                    "cols":$scope.data.graficosProductores.cols,
                    "rows": $scope.data.graficosProductores.rows
                };
                $scope.data.graficosProductores.barras.type = $scope.TIPO_BARRAS_VERTICALES;
                $scope.data.graficosProductores.barras.options = {
                    'title': 'Pólizas de productores por mes',
                    "isStacked": "true",
                    'height':400,
                    vAxis: {minValue: 0}
                };
            };

            //FUNCIONALIDAD MENU DE REPORTES

            $scope.$on("$stateChangeSuccess", function () {
                angular.element(document).ready(loadCharts);
            });

            function loadCharts() {
                $('a.tab-link').on('click', function (e) {
                    e.preventDefault();
                    $('#dashboard_tabs').find('div[id^=dashboard]').each(function () {
                        $(this).css('visibility', 'hidden').css('position', 'absolute');
                    });
                    var attr = $(this).attr('id');
                    $('#' + 'dashboard-' + attr).css('visibility', 'visible').css('position', 'relative');
                    $(this).closest('.nav').find('li').removeClass('active');
                    $(this).closest('li').addClass('active');
                    if ( $(this).attr("callback") ) {
                        $scope[ $(this).attr("callback") ]();
                        $scope.$root.$emit('resizeMsg');
                    }
                });
            };

            function showErrorFechas(){
                error("La fecha desde debe ser anterior a la fecha hasta.");
            }

            function error(msj) {
                angular.element('#spinner').hide();
                bootbox.dialog({
                    message: msj, title: "Advertencia", buttons: {
                        success: {label: "OK", className: "btn-primary"}
                    }
                });
            };

            function formatCurrency(data){
                var temp = new google.visualization.DataTable(data);
                var formatter = new google.visualization.NumberFormat({prefix: '$'});
                formatter.format(temp, 1);
            }

            function formatCurrencyMultilinea(data){
                var temp = new google.visualization.DataTable(data);
                var formatter = new google.visualization.NumberFormat({prefix: '$'});
                for(i = 1; i < data.rows[0].c.length; i++){
                    formatter.format(temp, i);
                }
            }

            //google api ready
            googlePromise.then(function() {
                $scope.init();
            });
        }]);
    };
    return constructor;
});
