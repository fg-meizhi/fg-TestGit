$(function () {

    init();
});

function init() {
    $.get(path + '/br', {param: "getJobList"}, function (data) {
        var div = $('dl.history');
        var json = $.parseJSON(data);
        //console.log(json.jobLogs);

        $.each(json, function(date, logFiles){
            //console.log(k+", "+v);
            var dateHTML = '<dt><a href="javascript:void(0)">'+date+'</a></dt>';
            div.append(dateHTML);
            $.each(logFiles, function(i){
                var logHTML = "<dd>"
                        +'<em class="time">'+logFiles[i].time+'</em>'
                        +'<a class="fileName" href="javascript:void(0)" id="' + logFiles[i].name.split('.')[0] + '" fileName="' + logFiles[i].name + '">' + logFiles[i].name + "</a>"
                    +"</dd>";
                div.append(logHTML);
            });
        });

    }).complete(function () {
        $(".fileName").on('click', function () {
            var _t = $(this);

            var logfileName = _t.attr('id');
            //console.log(_t.attr('id'));

            var logContentDivId = logfileName + "_content";
            $.get(path + '/br', {param: "readLogFile", fileName: _t.attr('fileName')}, function (data) {
                var json = $.parseJSON(data)
                    , _contentDiv = $('<div class="log-detail" id="'+logContentDivId+'"></div>')
                    , jobLog;

                jobLog = json.content;
                for(var i=0; i<jobLog.lines.length; i++){
                    var line = jobLog.lines[i], lineObj=$('<p></p>')
                        , timePart=$('<span></span>'), contentPart=$('<span></span>');

                    timePart.addClass('timemark')
                        //.append('[')
                        .append(line.date)
                        .append('&nbsp;')
                        .append(line.time);
                        //.append(']');
                    contentPart.append(line.level)
                        .append(line.clazz)
                        .append(line.content);

                    if(line.error){
                        timePart.addClass('error');
                        contentPart.addClass('error');
                    }

                    lineObj.append(timePart);
                    lineObj.append(contentPart);
                    _contentDiv.append(lineObj);
                }

                _t.after(_contentDiv);

                _t.after('&nbsp;&nbsp;&nbsp;<a class="foldMarker" id="fold_' + logContentDivId + '" href="javascript:void(0)">收起日志内容</a>');

                register();
            });
        });
    });
}

function register() {
    $('a.foldMarker').on('click', function () {
        var _t = $(this), relatedDivId='', foldMarkerId, array;

        foldMarkerId = _t.attr('id');
        array = foldMarkerId.split('_');
        for(var i=1; i<array.length; i++) {
            relatedDivId += array[i]+"_";
        }

        relatedDivId = relatedDivId.substring(0, relatedDivId.length-1);

        $('#' + relatedDivId).remove();
        _t.remove();
    })
}