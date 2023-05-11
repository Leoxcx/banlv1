// 视频播放方法
function doVideo(src){
    var player = $(".player");
    // 清理播放器
    player.empty();
    var video3d = $("<div id=\"video3d\"></div>");
    player.append(video3d);

    vr = new VR({ 'id': 'video3d' });
    vr.vrbox.radius = 600;
    vr.init(function () {
        //console.log("视频初始化完成");
    });
    vr.play(src, vr.resType.video);
    vr.video.setAttribute("loop", "loop");
    vr.video.crossOrigin = "Anonymous";
    startLog();
    initPanel();
}

// 开启陀螺仪
function opengyroUnfreeze() {
    vr.controls.gyroUnfreeze();
}

// 视频静音
function mute(){
    vr.video.muted=true;
}

// 视频跳转方法
function changeVideo(videoId) {
    console.log("视频跳转:" + videoId);
    // 查询用户是否有权限
    // 获取视频地址
    // 切换视频地址并播放
    if (videoId == 1) {
        doVideo("../image/test4~MPEG-4.mp4");
    }else {
        // 跳转至视频播放错误界面
        location.href = "image.html";
    }
}