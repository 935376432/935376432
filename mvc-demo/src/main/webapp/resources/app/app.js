var webModule = angular.module('webModule', ["ngComponentRouter","restangular"]);
webModule.value("$routerRootComponent","jiMain").controller("indexMain", function($scope) {
      var $ctrl  = this;
      var testaa = "testaa";
      $ctrl.testbb = "testbb";
      $scope.testcc = "testcc";
      var user = {};
      $ctrl.test = function(){
          alert("test");
      }
      //window.location.href="#/testaa";
      console.log("testaaa");

});

