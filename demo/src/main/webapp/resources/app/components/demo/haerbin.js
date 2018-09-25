webModule.component("demoHaerbin",{
    templateUrl: 'resources/app/partials/demo/haerbin.html',
    bindings:{$router:'<'},
    controller: function($scope,Restangular) {
      var $ctrl  = this;
      console.log("DemoHaerbin")

      //按钮点击事件
      $ctrl.btn = function(){
          console.log(" $ctrl.btn()");
          var obj = {};
          obj.name = "test";
          Restangular.all("demo/test/addTest").post(obj).then(function(result){
              alert(result);
          });

          /*Restangular.one("demo/test/addTest").get().then(function(result){
              alert(result);
          });*/
      };


    }
});