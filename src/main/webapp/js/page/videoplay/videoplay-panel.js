// 初始化面板
function initPanel(videoId){
    initChoosePanel(videoId);
    initVideoInfoPanel(videoId);
}

// 初始化视频面板
function initChoosePanel(videoId){
    // 获取工具栏
    var showUI = $(".showUI");
    // 获取滑动窗口
    var choosePanel = this.choosePanel(videoId);
    choosePanel.appendTo(showUI);
    var swiper = new Swiper(".mySwiper", {
        effect: "coverflow",
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: "auto",
        coverflowEffect: {
            rotate: 50,
            stretch: 0,
            depth: 100,
            modifier: 1,
            slideShadows: true,
        },
        pagination: {
            el: ".swiper-pagination",
        },
    });
}
// 初始化信息面板
function initVideoInfoPanel(videoId){
    // 获取工具栏
    var toolBarArea = $("._toolBarArea");
    console.log(toolBarArea);
    // 获取滑动窗口
    var videoInfoPanel = this.videoInfoPanel(videoId);
    toolBarArea.append(videoInfoPanel);
}

// 定义视频面板
function choosePanel(videoId){
    var choosePanel = $("<div id=\"choosePanel\" style='background: #a3c6e2' class=\"swiper mySwiper\"></div>");
    var swiperwrapper = $("<div class=\"swiper-wrapper\">");
    let num = 9;
    for(var i = 0 ; i < num ;i++) {
        var swiperslide = $("<div class=\"swiper-slide\"></div>");
        var videoChangeCube = $("<div class=\"videoChangeCube\"></div>");
        var img = $("<img class=\"videoImage\" src=\"../image/nature-"+ Number(i + 1) +".jpg\" onclick=\"changeVideo("+ i +")\" />" );
        var starImage = $("<img class=\"starImage\" style=\"width: 100px;\" src=\"../image/Star-Light.png\"/>");
        var hideEyeImage = $("<img class=\"hideEyeImage\" src=\"../image/Hide-Light.png\"/>");
        var videoNameText = $("<text class=\"videoNameText\"> 视频一</text>");
        videoChangeCube.append(img);
        videoChangeCube.append(starImage);
        videoChangeCube.append(hideEyeImage);
        videoChangeCube.append(videoNameText);
        swiperslide.append(videoChangeCube);
        swiperwrapper.append(swiperslide);
    }
    var swiperpagination = $("<div class=\"swiper-pagination\"></div>");
    choosePanel.append(swiperwrapper);
    choosePanel.append(swiperpagination);
    return choosePanel;
}

// 定义视频面板
function videoInfoPanel(videoId){
    //var videoInfoPanel = $("<div class=\"videoInfoPanel _toolBarArea\"></div>");
    // var img = $("<img src=\"../image/4.jpg\">按钮</img>");
    // videoInfoPanel.append(img);
    return videoInfoPanel;
}