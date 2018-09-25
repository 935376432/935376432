webModule.component("demoHaerbin",{
    templateUrl: 'resources/app/partials/demo/haerbin.html',
    bindings:{$router:'<'},
    controller: function($scope,Restangular) {
      var $ctrl  = this;
      console.log("DemoHaerbin")

      $ctrl.$onInit = function(){
          var obj = {};
          obj.size = 10;
          obj.page = 0;
          Restangular.one("user/userPage").get(obj).then(function(result){
              $ctrl.userList = result;
          });


      };

      //增加用户摸模态窗
      $ctrl.openAddUser = function(){
          $ctrl.user = {};
      };
      $ctrl.getUser = function(){
          $ctrl.userList = undefined;
          var obj = {};
          obj.size = 10;
          obj.page = 0;
          Restangular.one("user/userPage").get(obj).then(function(result){
              $ctrl.userList = result;
          });
      }

      //保存用户信息
      $ctrl.addUser = function(user){
          if (user.id){
              Restangular.all("user").customPUT(user).then(function(result){
                  alert("成功")
              },function(err){
                  alert("失败")
              });
          } else {
              Restangular.all("user").customPOST(user).then(function(result){
                  alert("成功")
              },function(err){
                  alert("失败")
              });
          }
      };
      //编辑用户
      $ctrl.editUser = function(user){
          $ctrl.user = user;
      };
      //删除用户
      $ctrl.deleteUser = function(user){
          $.confirm({
              title: '确认',
              content: '确认删除？',
              type: 'blue',
              icon: 'glyphicon glyphicon-question-sign',
              buttons: {
                  ok: {
                      text: '确认',
                      btnClass: 'btn-primary',
                      action: function() {
                          Restangular.one("user",user.id).remove().then(function(result){
                              alert("成功")
                          },function(err){
                              alert("失败")
                          });
                      }
                  },
                  cancel: {
                      text: '取消',
                      btnClass: 'btn-primary'
                  }
              }
          });
      };


    }
});