webModule.component("demoTestTable", {
    templateUrl: genVersionUrl("resources/partials/business/assetsmgr/data/hardware/asset-virtual-host.html"),
    bindings: {$router: '<'},
    controller: function ($scope,$timeout,Restangular,UI,SysParam, FormHelper,User,$rootRouter,$location) {


        $scope.$L = $L;
        var $ctrl = this;
        $ctrl.FormHelper = FormHelper;
        $ctrl.assetType = "VM";

        //表格选中后的信息
        $ctrl.selectedDatas = [];
        $ctrl.selecteds= [];
        //初始每页显示的条数
        $ctrl.numberOfPage = 10;
        //列数组
        $ctrl.fields = [];
        $ctrl.fieldsCopy = [];
        //初始化方法
        $ctrl.$onInit = function() {
            $ctrl.getColumnAndData();
        };

        //表格样式---start----------------------------------------------------
        $ctrl.getColumnAndData = function(){
            //获取列信息
            Restangular.one("assetColumns",$ctrl.assetType).get().then(function(result){
                $ctrl.fields = [];
                $ctrl.fieldsCopy = angular.copy(result);
                var dataSort = result.sort(compare("order"));
                $ctrl.fields.push({title: $L('assets.code'),  field: "code",order:1});
                $ctrl.fields = $ctrl.fields.concat(dataSort);
                $ctrl.tableWidthStyleChange();
                //调用刷新方法，资产信息
                $ctrl.refresh();
            });
        };

        //底部按钮
        $ctrl.buttons = {
            footerButtons : [ {
                name : $L('device.common.batchEdit'),
                onClick : function($ctrl) {
                    $ctrl.openBatchFun();
                }
            }, {
                name : $L('device.common.exportSel'),
                onClick : function($ctrl) {
                    $ctrl.exportSelect();
                }
            }, {
                name : $L('device.common.exportAll'),
                onClick : function($ctrl) {
                    $ctrl.exportAll();
                }
            }, ]
        };

        $ctrl.getFilterAndOrderParams = function() {
            var params = {};
            if ($ctrl.orderable && $ctrl.orderable.field) {
                params.sort = $ctrl.orderable.field + "," + $ctrl.orderable.type;
            }
            angular.forEach($ctrl._filters, function(value, key) {
                if (value.value && value.value.length > 0) {
                    params[value.attr + value.oper] = value.format ? value.format(value.value) : value.value;
                }
            });
            return params;
        };
        //刷新方法
        $ctrl.refresh = function(_params) {
            $timeout(function() {
                var params = $ctrl.getFilterAndOrderParams();
                params.page = ($ctrl.data && $ctrl.data.number) || 0;
                params.size = $ctrl.numberOfPage;
                angular.forEach(_params, function(value, key) {
                    if(value != null){
                        params[key] = value;
                    }
                });
                if ($ctrl.search != null && $ctrl.search != ""){
                    params.hwcode = $ctrl.search;
                }
                params.hwtype = $ctrl.assetType;
                Restangular.all("assetInfo").getList(params).then(function(data){
                    $ctrl.data = data;
                    $timeout(function(){
                        tableRelative();
                    },50);
                });
            });
        };
        //打开编辑模态窗方法
        $ctrl.openeditTable = function(field,item){
            if (!field.edit){
                return;
            }
            $ctrl.editTable = {};
            $ctrl.editTable.item = item;
            $ctrl.editTable.field = field;
            $ctrl.editTable.value = item[field.field];
            FormHelper.reset($scope.editTableForm);
            $ctrl.openEditTableModal();
        };
        //保存表格编辑方法
        $ctrl.saveEditTable = function(){
            var obj = {};
            obj.itemKey = $ctrl.editTable.field.field;
            obj.devId = $ctrl.editTable.item.id;
            obj.value = $ctrl.editTable.value;

            UI.toast_loading();
            Restangular.one("assetInfo","updateTableAttr").customPUT(obj).then(function(result){
                if (result == "success"){
                    UI.toast_successed();
                    $ctrl.closeeEditTableModal();
                    $ctrl.refresh({page: $ctrl.data.current_page});
                } else {
                    UI.toast_failed();
                }
            });
        };

        //表格样式---end----------------------------------------------------

        //---------过滤方法start--------------
        //高级过滤方法
        $ctrl.moreSearch = function(){
            var params = {};
            angular.forEach($ctrl.fieldsCopy,function(data,index){
                if (data.filter != null && data.filter != ""){
                    params[data.field.replace(".","_").replace(".","_")] = data.filter;
                }
            });
            $ctrl.refresh(params);
        };
        //清空高级过滤
        $ctrl.clearMoreSearch = function(){
            angular.forEach($ctrl.fieldsCopy,function(data,index){
                if (data.filter != null && data.filter != ""){
                    data.filter = undefined;
                }
            });
            $ctrl.refresh();
        };
        //过滤查询
        $ctrl.applySearchFilter = function(){
            if($ctrl._filters == null){
                $ctrl._filters = {};
            }
            if($ctrl.filters == null){
                $ctrl.filters = {};
            }
            if($ctrl.search != null  && $ctrl.search.trim() != ""){
                $ctrl.refresh({page: 0,hwcode:$ctrl.search});
            }else{
                $ctrl.refresh({page: 0});
            }
        };
        $ctrl.confuseQuery = function(){
            if ($ctrl.autoFilterTimeout) {
                $timeout.cancel($ctrl.autoFilterTimeout);
            }
            $ctrl.autoFilterTimeout = $timeout(function() {
                $ctrl.applySearchFilter();
            }, 1000);
        };
        $ctrl.clearSearch = function(){
            $ctrl.search = undefined;
            $ctrl.confuseQuery();
        };
        //---------过滤方法end--------------

        //增加列方法-----start------------------

        //增加列事件
        $ctrl.fieldsAdd = function(){
            //$ctrl.fields.push({title: "aaa",  field: "aaaaa"});
            //$ctrl.tableWidthStyleChange();
            $ctrl.openChooseColumnModal();
            $ctrl.sortFieldsCopy();
        };

        //选择列-新增属性方法 -- 打开模态窗
        $ctrl.addColumnFun = function(){
            $ctrl.openAddColumnModal();
            $ctrl.addColumn = {};
            FormHelper.reset($scope.addColumnForm);
        };
        //编辑属性方法
        $ctrl.editColumnFun = function(field){
            $ctrl.openAddColumnModal();
            $ctrl.addColumn = field;
            FormHelper.reset($scope.addColumnForm);
        }
        //增加新属性到属性列
        $ctrl.addColumnToFildsList = function(form){
            if(form.$invalid){
                return;
            }
            if ($ctrl.addColumn != null && $ctrl.addColumn.id == null){
                $ctrl.addColumn.show = true;
                $ctrl.addColumn.edit = true;
                $ctrl.addColumn.order = Number($ctrl.fieldsCopy.length + 1);
                $ctrl.addColumn.type = $ctrl.assetType;
                $ctrl.addColumn.field = "extra." + $ctrl.addColumn.order;
                $ctrl.addColumn.api = "extra." + $ctrl.addColumn.order;
                $ctrl.addColumn.group = 0;
                $ctrl.addColumn.source = 1;
                $ctrl.fieldsCopy.push($ctrl.addColumn);
            }
            $ctrl.closeAddColumnModal();
        };

        //保存新增的属性列
        $ctrl.savaColumn = function (){
          Restangular.one("assetColumns").customPUT($ctrl.fieldsCopy).then(function(){
              UI.toast_successed();
              $ctrl.closeChooseColumnModal();
              $ctrl.getColumnAndData();
          });
        };

        //字段向上按钮方法
        $ctrl.upField = function(field,oldOrder){
            angular.forEach($ctrl.fieldsCopy, function(data,index){
                if (data.field == field.field) {
                    data.order = data.order - 1;
                }
            });
            angular.forEach($ctrl.fieldsCopy, function(data,index){
                if (data.order == (oldOrder - 1) && data.field != field.field) {
                    data.order = data.order + 1;
                }
            });
            $ctrl.sortFieldsCopy();
        };
        //字段向下按钮方法
        $ctrl.downField = function(field,oldOrder){
            angular.forEach($ctrl.fieldsCopy, function(data,index){
                if (data.field == field.field) {
                    data.order = data.order + 1;
                }
            });
            angular.forEach($ctrl.fieldsCopy, function(data,index){
                if (data.order == (oldOrder + 1) && data.field != field.field) {
                    data.order = data.order - 1;
                }
            });
            $ctrl.sortFieldsCopy();
        };
        //排序
        $ctrl.sortFieldsCopy = function(){
            $ctrl.fieldsCopy = $ctrl.fieldsCopy.sort(compare("order"));
        };


        //增加列方法-----end------------------


        //跳转其他页面方法-----------------------如果是操作系统菜单跳转方法
        $ctrl.toOperSystem = function(field,item) {
            if (field.field == "oscode"){
                //{path: "/hardware/server/:hwtype/:srvcode/:swtype/:oscode/", name: "AssetsHwOperSystem", component: "qzAssetsHwOperSystem"},
                //跳转到操作系统页面
                window.location.href = "#/business/assets/hardware/server/" + "VM" + "/" + item.code + "/" + "OS" + "/" + item.oscode;
            }
            if (Array.isArray(item[field.field])){

                //跳转到操作系统页面
                window.location.href = "#/business/assets/item/" + "VM" + "/" + item.code + "/"  + item.id + "/" +field.field;
            }

        };
        //------------------------


        //批量编辑--start----------------------------------------------
        //打开批量编辑模态窗 -vm
        $ctrl.openBatchFun = function(){
            if ($ctrl.selecteds == null || $ctrl.selecteds.length < 1){
                UI.alert("请选择资产");
                return;
            }
            var isEdit = false;
            _.forEach($ctrl.fieldsCopy,function(data,index){
                if (data.edit){
                    isEdit = true;
                }
            });
            if (!isEdit){
                UI.alert("没有可编辑的项");
                return;
            }
            FormHelper.reset($scope.batchEditForm);
            angular.forEach($ctrl.fieldsCopy,function(data,index){
                data.value = undefined;
            });
            $ctrl.openBatchEditModal();
        };
        //保存批量编辑  VM
        $ctrl.saveBatchEdit = function(){
            $ctrl.closeBatchEditModal();
            var obj = {};
            angular.forEach($ctrl.fieldsCopy,function(data,index){
                if (data.value != null && data.value != ""){
                    obj[data.field] = data.value;
                }
            });
            $ctrl.idLength = $ctrl.selectedDatas.length;
            $ctrl.batchResults = [];
            angular.forEach($ctrl.selectedDatas,function(data,index){
                var obj2 = {};
                obj2.devId = data.id;
                _.defaults(obj2,obj);
                Restangular.one("assetColumns","batchEdit").customPUT(obj2).then(function(result){
                    var resObj = {};
                    resObj.code = data.code;
                    resObj.id = data.id;
                    resObj.result = result;
                    $timeout(function(){
                        $ctrl.batchResults.push(resObj);
                    },1000);
                });
            });

            $ctrl.openEditBatchResultModal();
            $ctrl.not_checkd_all($ctrl.data);
        };


        //批量编辑--end------------------------------------------------

        //导出方法--start-------------------------
        //导出全部
        $ctrl.exportAll = function(){
            var obj = {};
            obj.assetType = $ctrl.assetType;
            UI.toast_loading();
            Restangular.one("assetColumns/exportSelectAllByType").customPUT(obj).then(function(url){
                UI.toast_successed();
                window.location.href = url;
            },function(){
                UI.toast_failed($L('common.exportCommitFailed'));
            });
        };
        //导出选中
        $ctrl.exportSelect = function(){
            if ($ctrl.selecteds == null || $ctrl.selecteds.length < 1){
                UI.alert("请选择资产");
                return;
            }
            var obj = {};
            obj.allId = "['" + $ctrl.selecteds.join("','") + "']";
            obj._exportFormat="EXCEL";
            obj.assetType = $ctrl.assetType;
            UI.toast_loading();
            Restangular.one("assetColumns/exportSelectByType").customPUT(obj).then(function(url){
                UI.toast_successed();
                window.location.href = url;
                $ctrl.not_checkd_all($ctrl.data);
            },function(){
                UI.toast_failed($L('common.exportCommitFailed'));
            });
        };



        //导出方法--end---------------------------

        //获取路由参数 ----  接收从其他页面跳转来的方法
        $ctrl.$routerOnActivate = function(next, previous) {
            $ctrl.search = next.params.hwcode;
            //$ctrl.refresh({page: 0,hwcode:$ctrl.search});
            //debugger;
        }







        //表格特殊方法----start----------------------
        //页面是否显示跳转样式判断
        $ctrl.isJump = function(field,item){
            if (field.field == "oscode"){
                return true;
            }
            if (Array.isArray(item[field.field])){
                if (item[field.field].length > 0){
                    return true;
                }
            }
        };
        //表格列头的监听事件
        $ctrl.tableWidthStyleChange = function(){
            $ctrl.tableWidthStyle = {"width" : + $ctrl.fields.length * 200 + "px !important"};
            var fieldSize = 0;
            angular.forEach($ctrl.fields,function(data,index){
                if (data.show){
                    fieldSize = fieldSize + 1;
                }
            });
            document.getElementById("table-asset").style.width = fieldSize * 200 + 30 + "px";
        };

        //表格表头复选框选中事件
        $ctrl.is_checked = function(item) {
            var result = false;
            _.some($ctrl.selecteds, function(id) {
                if (item.id == id) {
                    result = true;
                    return true;
                }
            });
            return result;
        };
        //选中事件
        $ctrl.toggle_selected = function(item) {
            if ($ctrl.is_checked(item)) {
                $ctrl.selecteds = _.remove($ctrl.selecteds, function(o) {
                    return o != item.id;
                });
                $ctrl.selectedDatas = _.remove($ctrl.selectedDatas, function(o) {
                    return o.id != item.id;
                });
            } else {
                $ctrl.selectedDatas.push(item);
                $ctrl.selecteds.push(item.id);
            }
        };
        //选中单个
        $ctrl.toggle_selectedOne = function(item){
            if($ctrl.selecteds.length > 0){
                var haveEqu = false;
                angular.forEach($ctrl.selecteds,function(s){
                    if(s == item.id){
                        haveEqu = true;
                    }
                });
                if(haveEqu){
                    $ctrl.selectedDatas= [];
                    $ctrl.selecteds = [];
                }else{
                    $ctrl.selecteds = _.remove($ctrl.selecteds, function(o) {
                        return o == item.id;
                    });
                    $ctrl.selectedDatas = _.remove($ctrl.selectedDatas, function(o) {
                        return o.id == item.id;
                    });
                    $ctrl.selectedDatas.push(item);
                    $ctrl.selecteds.push(item.id);
                }

            }else{
                $ctrl.selectedDatas.push(item);
                $ctrl.selecteds.push(item.id);
            }
        };
        //选中全部
        $ctrl.is_checked_all = function(list) {
            var result = true;
            _.forEach(list, function(item) {
                if ($ctrl.is_checked(item) === false) {
                    result = false;
                }
            });
            return result;
        };
        //选中全部
        $ctrl.toggle_checked_all = function(list) {
            if ($ctrl.is_checked_all(list)) {
                _.forEach(list, function(item) {
                    $ctrl.selecteds = _.remove($ctrl.selecteds, function(o) {return o != item.id;});
                    $ctrl.selectedDatas = _.remove($ctrl.selectedDatas, function(o) {return o.id != item.id;});
                    if ($ctrl.selectedDatas.length == 0 && $ctrl.showSelected == true) {
                        $ctrl.refresh({page: 0});
                    }
                });
            } else {
                _.forEach(list, function(item) {
                    if (!$ctrl.is_checked(item)) {
                        $ctrl.selecteds.push(item.id);
                        $ctrl.selectedDatas.push(item);
                    }
                });
            }
        };
        //取消选中
        $ctrl.not_checkd_all = function(list){
            _.forEach(list, function(item) {
                $ctrl.selecteds = _.remove($ctrl.selecteds, function(o) {return o != item.id;});
                $ctrl.selectedDatas = _.remove($ctrl.selectedDatas, function(o) {return o.id != item.id;});
                if ($ctrl.selectedDatas.length == 0 && $ctrl.showSelected == true) {
                    $ctrl.refresh({page: 0});
                }
            });
        }
        //选中的数量计算方法
        $ctrl.selected_Selectcount = function(){
            var selectCount = 0;
            _.forEach($ctrl.data,function(value){
                _.forEach($ctrl.selecteds,function(selectValue){
                    if(value.id != null && value.id == selectValue){
                        selectCount ++ ;
                    }
                })
            })
            return selectCount;
        };
        //上一页按钮方法
        $ctrl.upPage = function (){
            if ($ctrl.showSelected) {
                $ctrl.selectDatafu($ctrl.data.current_page - 1);
            } else {
                $ctrl.refresh({page: $ctrl.data.current_page - 1});
            }
        }

        $scope.$watch("$ctrl.data.current_page", function(newValue) {
            if ($ctrl.current_page_mirror != newValue + 1) {
                $ctrl.current_page_mirror = newValue + 1;
            }
        });
        $scope.$watch("$ctrl.current_page_mirror", function(newValue) {
            if ($ctrl.data && $ctrl.data.current_page != newValue - 1) {
                $ctrl.data.current_page = newValue - 1;
            }
        });
        //下一页按钮方法
        $ctrl.downPage = function (){
            if ($ctrl.showSelected) {
                $ctrl.selectDatafu($ctrl.data.current_page + 1);
            } else {
                $ctrl.refresh({page: $ctrl.data.current_page + 1});
            }
        }
        //每页显示方法变化
        $ctrl.pageSizeChange = function(n) {
            localStorage.tablePageSize = n;
            $ctrl.numberOfPage = n;
            $ctrl.refresh({page: 0,size : n});
        }

        //toStyle
        $ctrl.toStyle = function(styles1, styles2) {
            return jQuery.extend({}, styles2, styles1);
        };


         function tableRelative() {// 给table外面的div滚动事件绑定一个函数
            var left = $("#asset-tablediv").scrollLeft();// 获取滚动的距离
            var trs = $("#asset-tablediv table tr");// 获取表格的所有tr
            // 对每一个tr（每一行）进行处理
            trs.each(function(i) {
                // 获得每一行下面的所有的td，然后选中下标为0的，即第一列，设置position为相对定位
                // 相对于父div左边的距离为滑动的距离，然后设置个背景颜色，覆盖住后面几列数据滑动到第一列下面的情况
                // 如果有必要也可以设置一个z-index属性
                $(this).children().eq(0).css({
                    "position" : "relative",
                    "z-index": 2,
                    "top" : "0px",
                    "left" : left,
                    "background-color" : "white",
                    "border": "none",
                    "width":"50px  !important"
                });
                $(this).children().eq(1).css({
                    "position" : "relative",
                    "z-index": 2,
                    "top" : "0px",
                    "left" : left,
                    "background-color" : "#E0E3EA",
                    "border": "1px solid white"
                });
            });
        };
        //表格冻裂
        $("#asset-tablediv").scroll(
            tableRelative
        );

        //表格特殊方法-----end-----------------------









        //字段属性排序
        function compare(property){
            return function(a,b){
                var value1 = a[property];
                var value2 = b[property];
                return value1 - value2;
            }
        }





    }
});
