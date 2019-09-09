var danger = {
    fileId: [],
    urlSearch: biyue.urlSearch(),
    update: function () {
        var _self = this,
            type = _self.urlSearch.type,
            url="/riskOperation/add";
        if (type === "edit") {
            url="/riskOperation/update";
        }
        var category = $("#category").val(),//类别
            title = $("#title").val(),// 标题
            content = $("#content").val(),  // 备注
            id = _self.urlSearch.id,
            files = $('#file').fileinput('getFileStack');//得到文件

        var formData = new FormData($("#form_data")[0]);
        formData.append("category", category);
        formData.append("title", title);
        formData.append("content", content);
        for (var i = 0; i < files.length; i++) {
            formData.append("files", files[i]);
        }
        //编辑时上传图片
        if (type === "edit") {
            var filePathList = [];
            if (!_self.fileId) _self.fileId = [];
            //获取现有图片id
            $(".file-drop-zone .a_image").each(function () {
                var id = $(this).attr("data-id");
                if (id) {
                    for (var i = 0; i < _self.fileId.length; i++) {
                        if (_self.fileId[i] === id) {
                            _self.fileId[i] = false;
                        }
                    }
                }
            });
            //获取删除图片id
            for (var j = 0; j < _self.fileId.length; j++) {
                if (_self.fileId[j]) {
                    filePathList.push(_self.fileId[j]);
                }
            }
            //上传图片
            formData.append("filePathList", filePathList);
            formData.append("id", id);
        }
        formData.append("constructionId",$("#dropdownMenu4").attr("constructionid"))
        biyue.ajax({
            url: url,
            data: formData,
            processData: false,
            contentType: false,
            fun: function (data) {
                var $title = "提交成功!";
                if (type === "edit") {
                    $title = "编辑成功!"
                }
                layer.alert($title, {
                    cancel: function () {
                        biyue.layui_close();
                    }
                }, function () {
                    biyue.layui_close();
                });
            }
        });
    },
    createFile: function (initialPreview) {
        $('#file').fileinput('destroy');
        // 设置图片
        $("#file").fileinput({
            language: 'zh',
            theme: 'fa',
            uploadUrl: '#',
            showUpload: false,
            maxFileSize: 51200,
            minFileSize: 1,
            browseIcon: '',
            removeIcon: '',
            allowedFileExtensions: ["bmp", "jpg", "jpeg", "png", "gif"],
            fileActionSettings: {
                showUpload: false,
                showZoom: false,
                showDrag: false
            },
            initialPreview: initialPreview
        });
        var fileCon = $(".file-preview-thumbnails").html(), bool = true;
        $("#file").on('fileimageloaded', function (event, previewId) {
            if (bool) {
                $(".file-preview-thumbnails").prepend(fileCon);
                bool = !bool;
            }
            imgDel();
        });
        $("#file").on('filecleared', function (event, previewId) {
            fileCon = "";
        });

        function imgDel() {
            $(".a_image").parents(".file-preview-frame").find(".kv-file-remove").click(function () {
                $(this).parents(".file-preview-frame").remove();
                fileCon = "";
                $(".a_image").each(function () {
                    fileCon += $(this).parents(".file-preview-frame")[0].outerHTML;
                })
            })
        }

        imgDel();
    },
    getData: function () {
        $(".area button").attr('disabled', true);
        $(".site button").attr('disabled', true);
        var id = biyue.urlSearch().id, _self = this;
        biyue.ajax({
            url:'/riskOperation/view',
            data: {
                id: id
            },
            fun: function (data) {
                if (data.value) {
                    var initialPreview = [], fileId = [];
                    data = data.value;
                    $("#category").val(data.category);
                    $("#content").val(data.content);
                    $("#title").val(data.title);
                    $("#dropdownMenu3").html(data.areaName + '<span class="caret"></span>').attr("areaId",123); //区域工地无需上传,所以写死
                    $("#dropdownMenu4").html(data.constructionName + '<span class="caret"></span>').attr("constructionid",data.constructionId);
                    if (data.picList) {
                        for (var i = 0; i < data.picList.length; i++) {
                            fileId.push(data.picList[i].id);
                            initialPreview.push('<img class="a_image" style="width: 100%;height: 100%;" data-id="' + data.picList[i].id + '" src="' + data.picList[i].name + '" alt="">')
                        }
                    }
                    _self.fileId = fileId;
                    _self.createFile(initialPreview)
                }
            }
        })
    },
    default: function () {
        var _self = this;
        if (_self.urlSearch.type === "edit") {
            _self.getData();
        }else {
            init();
        }
        $("#form_data").kk_tableForm("#submit", function () {
            _self.update();
        });

        $("#file").fileinput({
            language: 'zh',
            theme: 'fa'
        })

    }
};


