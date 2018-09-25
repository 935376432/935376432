// app/components/common/table.js
webModule.component("qzTable", {
    templateUrl: genVersionUrl("resources/app/partials/demo/table.html"),
    transclude: {tbody: "?qzTbody"},
    bindings: {"id": "@",
                "class": "@",
                "checkbox": "@",
                "dataone": "@",
                "pagination": "@",
                "fields": "=?tableFields",
                "fieldCtrl": "@",
                "fieldChanged": "=?",
                "orderable": "=?orderable",
                "data": "=?tableData",
                "buttons": "=?buttons",
                "selecteds": "=?",
                "showSelected": "=?",
                "selectedDatas": "=?",
                "numberOfPage": "=?",
                "dynamicClass": "=?",
                "nameField": "@",
                "getter": "=?",
                "setter": "=?",
                "refresh": "=?",
                "titleShow": "=?",
                "addSelectData":"=?",
                "refreshBtnClick":"=?",
                "esType":"@",
                "lineClick": "=?"},
    controller: function($element, $scope, $compile, $timeout, Restangular) {
        $scope.$L = $L;
        var $table = this;
        if (!$table.fieldCtrl) {
            $table.fieldCtrl = 'disabled';
        }
        $table.esType = $table.esType || "false";
        $table.selecteds = $table.selecteds || [];
        $table.selectedDatas = $table.selectedDatas || [];
        $table.toStyle = function(styles1, styles2) {
            return jQuery.extend({}, styles2, styles1);
        };
        $table.getter = function(key) {
            return $table[key];
        };
        $table.setter = function(key, value) {
            $table[key] = value;
            if (key == "selecteds" && value.length === 0) {
                $table["selectedDatas"] = [];
            }
        };
        $table.removeSelectedItem = function(item, e) {
            $table.toggle_selected(item);
            e.stopPropagation();
        };
        $table.selected_count = function() {
            return $table.selecteds.length;
        };
        $table.is_checked = function(item) {
            var result = false;
            _.some($table.selecteds, function(id) {
                if (item.id == id) {
                    result = true;
                    return true;
                }
            });
            return result;
        };
        $table.toggle_selected = function(item) {
            if ($table.is_checked(item)) {
                $table.selecteds = _.remove($table.selecteds, function(o) {
                    return o != item.id;
                });
                $table.selectedDatas = _.remove($table.selectedDatas, function(o) {
                    return o.id != item.id;
                });
            } else {
                $table.selectedDatas.push(item);
                $table.selecteds.push(item.id);
            }
        };
        $table.toggle_selectedOne = function(item){
            if($table.selecteds.length > 0){
                var haveEqu = false;
                angular.forEach($table.selecteds,function(s){
                    if(s == item.id){
                        haveEqu = true;
                    }
                });
                if(haveEqu){
                    $table.selectedDatas= [];
                    $table.selecteds = [];
                }else{
                    $table.selecteds = _.remove($table.selecteds, function(o) {
                        return o == item.id;
                    });
                    $table.selectedDatas = _.remove($table.selectedDatas, function(o) {
                        return o.id == item.id;
                    });
                    $table.selectedDatas.push(item);
                    $table.selecteds.push(item.id);
                }

            }else{
                $table.selectedDatas.push(item);
                $table.selecteds.push(item.id);
            }
        };
        $table.is_checked_all = function(list) {
            var result = true;
            _.forEach(list, function(item) {
                if ($table.is_checked(item) === false) {
                    result = false;
                }
            });
            return result;
        };
        $table.uncheck_all = function() {
            $table.selecteds = [];
            $table.selectedDatas = [];
        };
        $table.toggle_checked_all = function(list) {
            if ($table.is_checked_all(list)) {
                _.forEach(list, function(item) {
                    $table.selecteds = _.remove($table.selecteds, function(o) {return o != item.id;});
                    $table.selectedDatas = _.remove($table.selectedDatas, function(o) {return o.id != item.id;});
                    if ($table.selectedDatas.length == 0 && $table.showSelected == true) {
                        $table.refresh({page: 0});
                    }
                });
            } else {
                _.forEach(list, function(item) {
                    if (!$table.is_checked(item)) {
                        $table.selecteds.push(item.id);
                        $table.selectedDatas.push(item);
                    }
                });
            }
        };
        // 选中单个资源，增加易用性
        $table.addSelectData = function(item){
            if (!$table.is_checked(item)){
                $table.selecteds.push(item.id);
                $table.selectedDatas.push(item);
            } else {
                angular.forEach($table.selectedDatas, function(devItem){
                    if (devItem.id == item.id){
                        devItem.account = item.account;
                    }
                });
            }
        };

        $table.switch_orderable = function(field) {
            if ($table.orderable && $table.orderable.field == field) {
                if ($table.orderable.type == "asc") {
                    $table.orderable.type = "desc";
                } else if ($table.orderable.type == "desc") {
                    $table.orderable = {};
                }
            } else {
                if (!$table.orderable) {
                    $table.orderable = {field: field, type: "asc"};
                } else {
                    $table.orderable.field = field;
                    $table.orderable.type = "asc";
                }
            }
            if ($table.refresh) {
                $table.refresh();
                if ($table.showSelected != undefined){
                    $table.showSelected = false;
                }
            }
        };
        $table.haveCtrlButton = function() {
            return $table.buttons &&
                    (($table.buttons.ctrlButtons && $table.buttons.ctrlButtons.length > 0) ||
                     ($table.buttons.moreButtons && $table.buttons.moreButtons.length > 0));
        };
        $table.haveMoreButton = function(item, index) {
            if ($table.buttons &&
                    ($table.buttons.moreButtons && $table.buttons.moreButtons.length > 0)) {
                for (var i = 0; i < $table.buttons.moreButtons.length; i++) {
                    if (!$table.buttons.moreButtons[i].perpare || $table.buttons.moreButtons[i].perpare(item, index) === true) {
                        return true;
                    }
                }
            }
            return false;
        };
        $table.getFooterCtrlCol = function() {
            var cols = $table.fields ? $table.fields.length : 0;
            if ($table.haveCtrlButton()) {
                cols += 1;
            }
            return cols + 1;
        };
        $scope.$watch("$ctrl.data", function(newValue) {
            if ($table.data && $table.data.length === 0 && $table.data.current_page > 0) {
                if ($table.refresh) {
                    $table.refresh({page: $table.data.current_page - 1});
                }
            }
            if ($table.data && $table.data.length > 0 && $table.showSelected != undefined){
                _.forEach($table.data,function(data){
                    _.forEach($table.selectedDatas,function( data1, index){
                        if (data1 != null && data.id == data1.id) {
                            $table.selectedDatas[index] = data;
                        }
                    })
                })
            }
            if ($table.data && $table.showSelected != undefined && $table.data.showSelect == undefined) {
                $table.showSelected = false;
            }
        });

        $scope.$watch("$ctrl.data.current_page", function(newValue) {
            if ($table.current_page_mirror != newValue + 1) {
                $table.current_page_mirror = newValue + 1;
            }
        });
        $scope.$watch("$ctrl.current_page_mirror", function(newValue) {
            if ($table.data && $table.data.current_page != newValue - 1) {
                $table.data.current_page = newValue - 1;
            }
        });

        $table.toggleMoreButtons = function(e) {
            // var menu = $(e.target).parent().find(".dropdown-menu");
            // menu.css("left" , $(e.target).offset().left - 15);
            // menu.css("top" , $(e.target).offset().top + 20);
        };
        $table.toggleColPanel = function(e) {
            // var menu = $(e.target).parent().find(".dropdown-menu");
            // menu.css("left" , $(e.target).offset().left - 180);
            // menu.css("top" , $(e.target).offset().top + 34);
        };
        $table.jumpTo = function() {
            $timeout(function() {
                var page = $table.data.current_page || 0;
                if (page < 0) {
                    page = 0;
                }
                if (page > $table.data.total_pages - 1) {
                    page = $table.data.total_pages - 1;
                }
                if ($table.showSelected){
                    $ctrl.dataNotNull = true;
                    $table.selectData(page);
                } else {
                    $table.refresh({page: page});
                }

            });
        };

        $table.cleanSelect = function(){
            $table.selecteds = [];
            $table.selectedDatas = [];
            $table.refresh({page: 0});
        }
        $table.selectData = function(data) {
            var params = {};
            var numberpage = 0;
            if ($table.numberOfPage === undefined || $table.numberOfPage === null){
                numberpage = 10;
            } else {
                numberpage = $table.numberOfPage;
            }
            if (((data+1)*$table.numberOfPage)/($table.selectedDatas.length) >= 2){
                params.page=0;
            } else {
                params.page = data;
            }

            params.numberOfPage = numberpage;
            params.devs = $table.selectedDatas;
            params.order = $table.orderable;
            if ($table.showSelected){
                Restangular.all("dev/findSelectDev").post(params).then(function(devs){
                    var extractedData;
                    extractedData = devs.content;
                    extractedData.first = devs.first;
                    extractedData.last = devs.last;
                    extractedData.showSelect = true;
                    if (devs.number != null) {
                        if ($table.numberOfPage === undefined || $table.numberOfPage === null){
                            extractedData.number = devs.number;
                        }
                    } else {
                        if ($table.numberOfPage === undefined || $table.numberOfPage === null){
                            extractedData.number = devs.pageindex;
                        }
                    }
                    if (devs.numberOfElements != null) {
                        extractedData.number_of_elements = devs.numberOfElements;
                    } else {
                        extractedData.number_of_elements = devs.content.length;
                    }

                    if (devs.size != null) {
                        extractedData.size = devs.size;
                    } else {
                        extractedData.size = devs.pagesize;
                    }
                    extractedData.sort = devs.sort;
                    if (devs.total != null) {
                        extractedData.total_elements = devs.total;
                    } else {
                        extractedData.total_elements = devs.totalElements;
                    }
                    //extractedData.total_elements = data.totalElements;
                    if (devs.number != null) {
                        extractedData.current_page = devs.number;
                    } else {
                        extractedData.current_page = devs.pageindex;
                    }
                    if (devs.totalPages != null) {
                        extractedData.total_pages = devs.totalPages;
                    } else {
                        extractedData.total_pages = devs.pagetotal;
                    }
                    $table.data =  extractedData;
                });
            } else {
                $table.refresh({page: 0});
            }
        }
        $table.selectDatafu = function(data){
            $table.selectData(data);
        }

        $table.selected_Selectcount = function(){
            var selectCount = 0;
            _.forEach($table.data,function(value){
                _.forEach($table.selecteds,function(selectValue){
                    if(value.id != null && value.id == selectValue){
                        selectCount ++ ;
                    }
                })
            })
            return selectCount;
        }

        $table.upPage = function (){
            if ($table.showSelected) {
                $table.selectDatafu($table.data.current_page - 1);
            } else {
                $table.refresh({page: $table.data.current_page - 1});
            }
        }

        $table.downPage = function (){
            if ($table.showSelected) {
                $table.selectDatafu($table.data.current_page + 1);
            } else {
                $table.refresh({page: $table.data.current_page + 1});
            }
        }
    }
});