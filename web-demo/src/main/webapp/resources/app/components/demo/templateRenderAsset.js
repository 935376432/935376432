webModule.directive('templateRenderAsset', function($compile) {
    return {
        restrict: 'A',
        scope: {
            "templateRenderAsset": "=",
            "item": "=templateData",
            "name": "=templateName",
            "params": "=templateParams"
        },
        link: function($scope, elem, attr, ctrl) {
            $scope.field = $scope.templateRenderAsset;
            $scope.value = _.propertyOf($scope.item)($scope.field.field);
            if ($scope.value == null) {
                $scope.value = $scope.item['value'];
            }
            if ($scope.field.template) {
                var el = angular.element($scope.field.template);
                $compile(el)($scope);
                elem.html(el);
            } else {
                elem.text($scope.value);
                /*
                if (Array.isArray($scope.value) && $scope.field.field != "targetDev"){
                    elem.text($scope.value.length);
                }
                //上线时间单独处理
                else if ($scope.field.field == "onlinetime"){
                    if ($scope.value != null){
                        //elem.text(moment(Number($scope.value)).format('YYYY-MM-DD hh:mm:ss'));
                        $scope.value = moment(Number($scope.value)).format('YYYY-MM-DD hh:mm:ss');
                        var el = angular.element("<span title='可编辑'>{{value}}</span>&nbsp;&nbsp;<span class='glyphicon glyphicon-pencil' style='cursor: pointer;z-index: 1;' title='可编辑'></span>");
                        $compile(el)($scope);
                        elem.html(el);
                    } else {
                        elem.text($scope.value);
                    }
                }
                //处理可编辑项，加点样式
                else if ($scope.field.edit){
                    var el = angular.element("<span title='可编辑'>{{value}}</span>&nbsp;&nbsp;<span class='glyphicon glyphicon-pencil' style='cursor: pointer;z-index: 1;' title='可编辑'></span>");
                    $compile(el)($scope);
                    elem.html(el);
                } else if (typeof($scope.value) == "string"){
                    if ($scope.value.length > 50 && $scope.field.field != "osversion.osversion.kernelupdate"){
                        var info = $scope.value.substring(0,50);
                        var el = angular.element("<span title='{{value}}'>{{value.substring(0,50)}}</span>");
                        $compile(el)($scope);
                        elem.html(el);
                        //elem.text(info);
                    } else {
                        elem.text($scope.value);
                    }
                } else {
                    elem.text($scope.value);
                }
            */}
        }
    };
});
