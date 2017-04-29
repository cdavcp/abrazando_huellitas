define([], function() {
    var constructor = {};
    angular.module("GlobalModule").directive("sigepPagination", paginatorFn);
    function paginatorFn() {
        var directiveConf = {
            restrict: "E",
            require: "?sigepPagination",
            controller: paginatorCtrlFn,
            templateUrl: GLOBAL_DIR + "/directives/paginationTemplate.html",
            controllerAs: "sigepPaginationCtrl"
        };
        directiveConf.scope = {
            for: "=", //the collection to paginate
            as: "=", //the name of the obj that will populate the paginated collection
            filter: "=", //the object or property that will filter the collection
            page: "@", //which page initiate
            perPage: "@" //how many items/registries per page
        };

        paginatorCtrlFn.$inject = ["$scope", "$filter"];
        function paginatorCtrlFn($scope, $filter) {
            this.conf = { currentPage: parseInt($scope.page || 1), morePages: false};
            this.filter = "";
            this.doPaginate = doPaginate.bind(this); this.paginateNext = paginateNext.bind(this); this.paginatePrev = paginatePrev.bind(this);
            this.reset = reset;
            function doPaginate() {
                var whoIsMyCollection;
                this.conf.hasResults = angular.isDefined($scope.for) && $scope.for.length > 0;
                if (!this.conf.hasResults) { 
                    $scope.as = [];
                    this.conf.morePages = false;
                    return; 
                }
                if (this.filter) {
                    whoIsMyCollection = $filter("filter")($scope.for, this.filter) || [];
                    $scope.as = whoIsMyCollection;
                } else {
                    whoIsMyCollection = $scope.for;
                    $scope.as = whoIsMyCollection.slice((this.conf.currentPage - 1) * parseInt($scope.perPage), this.conf.currentPage * parseInt($scope.perPage));
                    this.conf.morePages = whoIsMyCollection.length / parseInt($scope.perPage) > this.conf.currentPage ? true : false;
                    this.conf.hasResults = whoIsMyCollection.length > 0;
                }
            };        
            function paginateNext() { this.conf.currentPage++; this.doPaginate(); };
            function paginatePrev() { this.conf.currentPage--; this.doPaginate(); };
            function reset() { this.conf.currentPage = 1; this.conf.morePages = false };
        };

        directiveConf.link = directiveLinkFn;
        function directiveLinkFn(scope, el, attrs, ctrl) {
            scope.forWatch = scope.$watchCollection("for", onForWatchFn);
            function onForWatchFn(newVal, oldVal) {
                ctrl.reset();
                if (angular.isDefined(newVal) ) {
                    ctrl.doPaginate();                    
                    if (!scope.filterWatch) {
                        scope.filterWatch = scope.$watch("filter", onFilterWatchFn);
                    } 
                }
            };
            function onFilterWatchFn(newVal, oldVal) {
                //TODO: firstCall (?)
                if (!ctrl.firstCall || !angular.isDefined(newVal) ) {
                    ctrl.firstCall = true;
                } else {
                    ctrl.filter = newVal;
                    ctrl.doPaginate();
                }
            };
        };

        return directiveConf;
    };
    return constructor;
});