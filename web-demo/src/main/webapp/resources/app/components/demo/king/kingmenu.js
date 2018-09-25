webModule.component("demoKingMenu",{
    templateUrl: 'resources/app/partials/demo/king/kingmenu.html',
    bindings:{$router:'<'},
    controller: function($scope) {
      var $ctrl  = this;
      console.log("demoSecond")

      /*$ctrl.isActive = function(path){
          var aa = $ctrl.$router._currentInstruction.component.urlPath ==
              $ctrl.$router.generate(path).child.child.component.urlPath;
          console.log(aa);
          return aa;
      }*/


    }
});