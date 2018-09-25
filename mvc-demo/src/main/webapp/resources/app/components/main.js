webModule.component("jiMain",{
    templateUrl: 'resources/app/partials/main.html',
    bindings:{$router:'<'},
    transclude:true,
    $routeConfig: [
        {path: "/haerbin", name: "Haerbin", component: "demoHaerbin",useAsDefault: true},
        {path: "/hangzhou", name: "Hangzhou", component: "demoHangzhou"}
    ],
    controller: function($scope) {
      var $ctrl  = this;
      console.log("main.js")



      $ctrl.lastName = "ken";

      //服务列表
      $ctrl.areaList = [];
      $ctrl.areaList.push({"id":1,"name":"哈尔滨"});
      $ctrl.areaList.push({"id":2,"name":"南京"});
      $ctrl.areaList.push({"id":3,"name":"杭州"});
      $ctrl.areaList.push({"id":4,"name":"上海"});

      //跳转杭州页面
      $ctrl.toHang = function(){
          //$ctrl.$router.navigate(['Hangzhou']);
          window.location.href="#/hangzhou";
      };
      //跳转哈尔滨页面
      $ctrl.toHa = function(){
          //$ctrl.$router.navigate(['Hangzhou']);
          window.location.href="#/haerbin";
      }

    }
});


