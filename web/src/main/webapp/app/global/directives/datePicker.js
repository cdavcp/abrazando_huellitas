define(["moment"], function(moment) {
    var constructor = {};
    angular.module("GlobalModule").directive("sigepDatepicker", directiveFn);
    function directiveFn() {
        var directiveConf = {
            restrict: "E",
            replace: true,
            require: ["?sigepDatepicker"],
            controller: directiveCtrlFn,
            templateUrl: GLOBAL_DIR + "/directives/datePickerTemplate.html",
            controllerAs: "dpCtrl"
        };
        directiveConf.scope = {
            cssClass: "@", //custom css classes for button
            displayText: "@", //the header for display
            closeText: "@", //the text for close button
            clearText: "@", //the text for clear button
            isOpen: "@",
            model: "="
        };

        directiveCtrlFn.$inject = ["$scope"];
        function directiveCtrlFn($scope) {
            this.data = { 
                format: "dd/MM/yyyy",
                dateFormat: "DD/MM/YYYY",
                isOpen: $scope.isOpen || false,
                closeText: $scope.closeText || "Cerrar",
                clearText: $scope.clearText || "Borrar",
                displayText: $scope.displayText || "Seleccione una fecha" ,
                cssClass: $scope.cssClass || "btn btn-default"
            };
            this.open = onOpenEvent;
            function onOpenEvent($event) {
                $event.preventDefault(); $event.stopPropagation(); this.data.isOpen = true;
            };
        };

        directiveConf.link = directiveLinkFn;
        function directiveLinkFn(scope, el, attrs, ctrls) {
            var dpCtrl = ctrls[0];
            var ngModelCtrl = angular.element(el).find(".sigepDatePicker").controller("ngModel");
            ngModelCtrl.$parsers.push(onDateChange);
            function onDateChange(value) {
                scope.model = moment(value).format(dpCtrl.data.dateFormat);
            };
        };

        return directiveConf;
    };
    return constructor;
});