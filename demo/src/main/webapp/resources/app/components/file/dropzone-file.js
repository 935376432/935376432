// 文件上传开始 ----------------
/*$(document).ready(function() {

    $(".file-dropzone").on('dragover', handleDragEnter);
    $(".file-dropzone").on('dragleave', handleDragLeave);
    $(".file-dropzone").on('drop', handleDragLeave);

    function handleDragEnter(e) {
        this.classList.add('drag-over');
    }

    function handleDragLeave(e) {
        this.classList.remove('drag-over');
    }

    // "dropzoneForm" is the camel-case version of the form id
    // "dropzone-form"
    Dropzone.options.dropzoneForm = {

        url : "upload",
        autoProcessQueue : false,
        uploadMultiple : true,
        maxFilesize : 1024, // MB
        parallelUploads : 100,
        maxFiles : 100,
        addRemoveLinks : true,
        previewsContainer : ".dropzone-previews",

        // The setting up of the dropzone
        init : function() {

            var myDropzone = this;

            // first set autoProcessQueue = false
            $('#upload-button').on("click", function(e) {
                myDropzone.processQueue();
            });

            // customizing the default progress bar
            this.on("uploadprogress", function(file, progress) {
                progress = parseFloat(progress).toFixed(0);
                var progressBar = file.previewElement
                        .getElementsByClassName("dz-upload")[0];
                progressBar.innerHTML = progress + "%";
            });

            // displaying the uploaded files information in a Bootstrap
            // dialog
            this.on("successmultiple", function(files, serverResponse) {
                showInformationDialog(files, serverResponse);
            });
        }
    }

    function showInformationDialog(files, objectArray) {

        var responseContent = "";

        for (var i = 0; i < objectArray.length; i++) {

            var infoObject = objectArray[i];

            for ( var infoKey in infoObject) {
                if (infoObject.hasOwnProperty(infoKey)) {
                    responseContent = responseContent + " " + infoKey
                            + " -> " + infoObject[infoKey] + "<br>";
                }
            }
            responseContent = responseContent + "<hr>";
        }

        // from the library bootstrap-dialog.min.js
        BootstrapDialog.show({
            title : '<b>Server Response</b>',
            message : responseContent
        });
    }

});*/
//文件上传结束 ----------------
/*<!-- <div class="container" style="    width: 300px;" ng-show = "false">
    <div class="panel panel-default">
      <div class="panel-heading text-center">
        <h3>服务</h3>
      </div>
      <div class="panel-body">
        <div>
          <form id="dropzone-form" class="dropzone" enctype="multipart/form-data">

            this is were the previews should be shown.
            <div class="dropzone-previews"></div>
          </form>
          <hr>
          <a class="btn btn-link" href="list">
              <span class="glyphicon glyphicon-eye-open"></span> 查看我上传的文件
          </a>
          <button id="upload-button" class="btn btn-primary pull-right">
            <span class="glyphicon glyphicon-upload"></span> 上传
          </button>
        </div>
      </div>
    </div>
  </div> -->*/