define([], function() {
    var constructor = {};
    angular.module("GlobalModule").directive("exportToExcel", directiveFn);
    function directiveFn() {
        var directiveConf = {
            restrict: "A",
            require: "?exportToExcel",
            controller: directiveCtrlFn,
            controllerAs: "ctrl"
        };

        directiveConf.scope = {
            target: "@",
            sheetName: "@",
            source: "=",
            repeatProp: "@",
            fromChart: "@"
        };

        directiveCtrlFn.$inject = ["$scope", "$compile"];
        function directiveCtrlFn($scope, $compile) {
            this.exportExcel = _export;

            function _export(e) {
                var htmlTemplate = angular.element(document.getElementById($scope.target)).not("td.grid-button").clone();
                if (!$scope.fromChart) {
                    var _scope = $scope.$new();
                    _scope.data = {};
                    _scope.data[$scope.repeatProp] = $scope.source;

                    var maxCols = htmlTemplate.find("th").length;
                    var fake = $("<table>")
                                    .append( $("<thead>").append( htmlTemplate.find("thead th").not(":nth-child(".concat(maxCols).concat(")") ) ) )
                                    .append( $("<tbody>").append( htmlTemplate.find("tbody tr:first") ) );

                    var compiled = $compile(fake)(_scope);
                    _scope.$apply();
                }
                var uri = 'data:application/vnd.ms-excel;base64,'
                , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
                , base64 = function(source) { return window.btoa(unescape(encodeURIComponent(source))) }
                , format = function(source, ctx) { return source.replace(/{(\w+)}/g, function(m, p) { return ctx[p]; }) }

                var ctx = { worksheet: $scope.sheetName || 'Hoja 1', table: $scope.fromChart ? htmlTemplate.html() : compiled[0].innerHTML };
                window.location.href = uri + base64(format(template, ctx));
            };
        };

        directiveConf.link = directiveLinkFn;
        function directiveLinkFn(scope, el, attrs, ctrl) {
            angular.element(el).click(ctrl.exportExcel);
        };

        return directiveConf;
    };
    return constructor;
});