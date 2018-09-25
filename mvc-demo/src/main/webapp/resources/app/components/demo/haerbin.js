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
          /*Restangular.all("demo/test/addTest").post(obj).then(function(result){
              alert(result);
          });*/

          var obj = {};
          obj.size = 10;
          obj.page = 0;
          Restangular.one("mvc-demo/test/aa").get().then(function(result){
              alert(result);
          });
      };

      //按钮点击事件
      $ctrl.btn1 = function(){
          var obj = {};
          obj.size = 10;
          obj.page = 0;
          Restangular.one("mvc-demo/test/bb").get(obj).then(function(result){
              alert(result);
          });
      };

      //按钮点击事件
      $ctrl.btn2 = function(){
          var obj = {};
          obj.size = 10;
          obj.page = 0;
          Restangular.one("mvc-demo/test/cc").get(obj).then(function(result){
              alert(result);
          });
      };


    }
});