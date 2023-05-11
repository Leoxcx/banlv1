// 视频播放方法
function doVideo(resource){
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

    if(resource.resource_id > 5){
        vr.play(resource.resource_url);
    }
    vr.play(resource.resource_url, vr.resType.video);
    vr.video.setAttribute("loop", "loop");
    vr.video.crossOrigin = "Anonymous";
    var util = new VRUtils(vr);
    util.markIcon("textures/forward.png", new THREE.Vector3(-10, 20, 10), 'btn1', 'title1', 1, 1);
    util.markIcon("textures/left.png", new THREE.Vector3(10, 0, 10), 'btn2', 'title2', 1, 1);
    animate();
    function animate() {
        requestAnimationFrame(animate);
        if (AVR.isCrossScreen()) {
            cameraEvent.updatePosition();
        } else {
            util.markIconInViews();
            if (vr.markIconGroup) {
                for (var i = 0; i < vr.markIconGroup.children.length; i++) {
                    vr.markIconGroup.children[i].lookAt(vr.vr.camera.position);
                }
            }
        }
    }

    vr.controls.gyroUnfreeze();
    freeze = true;
    initjudge = false;
    startLog();
    initPanel(resource.resource_id);
    recordplay();
}

// 开启陀螺仪
function opengyroUnfreeze() {
    vr.controls.gyroUnfreeze();
}

function closegyroFreeze(){
    vr.controls.gyroFreeze();
}

// 视频静音
function mute(){
    vr.video.muted=true;
}

// 视频跳转方法
function changeVideo(resource_id) {
    console.log("视频跳转:" + resource_id);
    // 查询用户是否有权限
    // 获取视频地址
    $.ajax({
        type : "post",
        url : "../../resourcetotalsearchservlet",
        data : {
            "resource_id":resource_id
        },
        async : false,
        success : function(data){
            // 跳转至视频播放错误界面
            if(data.resources.length == 0) location.href = "image.html";
            // 切换视频地址并播放
            doVideo(data.resources[0]);
        }
    });
}

// 记录播放次数
function recordplay(){
    $.ajax({
        type : "post",
        url : "../../saveplayrecord",
        data : {
            "resource_id":resource_id
        },
        async : false,
        success : function(data){
            // 跳转至视频播放错误界面
            if(data.msg) console.log("播放次数++");
            else console.log("更新播放次数失败");
        }
    });
}