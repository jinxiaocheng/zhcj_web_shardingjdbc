/*
* 用于创建pdf页面
* */
function showPdf(url) {
    PDFJS.workerSrc = ctx+'/resources/static/plugins/pdf/pdf.worker.js';
    PDFJS.getDocument(url).then(function getPdfHelloWorld(pdf) {
        var pop=document.getElementById('pop');
        console.log(pop)
        for(var i=1,l=pdf.numPages;i<=l;i++){
            pdf.getPage(i).then(function (page) {
                var scale = 1;
                var oDiv=document.createElement('canvas');
                var viewport = page.getViewport(scale);
                var canvas = oDiv;
                var context = canvas.getContext('2d');
                canvas.height = viewport.height;
                canvas.width = viewport.width;
                var renderContext = {
                    canvasContext: context,
                    viewport: viewport
                };
                pop.appendChild(oDiv);
                page.render(renderContext);
            });
        }
    });
    openPage();
}

window.showPdf=showPdf;
function openPage() {
    my_open=layer.open({
        type: 1,
        anim:5,
        title:'检查项及结果',
        maxmin: true,
        content: $('#popPage'),
        area: ['80%','80%'],
    });
}

