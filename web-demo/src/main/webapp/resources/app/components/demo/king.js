webModule.component("demoKing",{
    templateUrl: 'resources/app/partials/demo/king.html',
    bindings:{$router:'<'},

    controller: function($scope) {
      var $ctrl  = this;
      console.log("demoKing")


      //跳转first页面
      $ctrl.toFirst = function(){
          console.log("first");
          window.location.href="#/king/first";
      }

      $ctrl.isActive = function(path){
          var aa = $ctrl.$route._currentInstrucion.component.urlPath ==
              $ctrl.$router.generate(path).child.child.component.urlPath;
          console.log(aa);
          return aa;
      }


    },
    $routeConfig: [
        {path: "/first", name: "First", component: "demoFirst",useAsDefault: true},
        {path: "/second", name: "Second", component: "demoSecond"}
    ]
});