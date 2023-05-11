var SceniczonePanelState = false;// 景区信息界面 默认未打开

// 初始化面板
function initPanel(resource_id){
    // initChoosePanel(resource_id);
    // initVideoInfoPanel(resource_id);
    initMenuPanel(resource_id);
}

// 初始化菜单界面
function initMenuPanel(resource_id){

    var MenuPanel = $("<div class=\"menuPanel\"></div>");
    // 显示进度条
    // MenuPanel.click( function () { $("._toolBarArea").css("display",'block') });
    if(!initjudge){
        var vrSlider = $(".vrSlider");
        var _toolBarBtn = $("._toolBarBtn");
        var _toolBarplay = $("._toolBarPlay");
        MenuPanel.append(vrSlider);
        MenuPanel.append(_toolBarBtn);
        MenuPanel.append(_toolBarplay);
    }


    var _toolBarArea = $("._toolBarArea");
    _toolBarArea.remove();

    var body = $("body");
    body.append(MenuPanel);

    var leftTopMenu = initLeftTopMenu();
    var rightTopMenu = initRightTopMenu();
    var rightBottomMenu = initRightBottomMenu();
    var scenicSpotMenu = initScenicSpotMenu();

    MenuPanel.append(leftTopMenu);
    MenuPanel.append(rightTopMenu);
    MenuPanel.append(rightBottomMenu);
    MenuPanel.append(scenicSpotMenu);
    var scenicSpotMenuswiper = new Swiper(".scenicSpotMenu", {
        slidesPerView: 3,
        spaceBetween: 30,
        freeMode: true,
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        },
    });
}

// 初始化左上方界面
function initLeftTopMenu(){
    var leftTopMenu = $("<div class=\"leftTopMenu\"></div>");
    var logo = $("<img class=\"logo\" src=\"../image/logo.png\"></img>");
    // var map = $("<img class=\"playButton\" src=\"../image/icon/地图.png\"></img>");
    leftTopMenu.append(logo);
    // leftTopMenu.append(map);
    return leftTopMenu;
}

// 初始化右上方界面
function initRightTopMenu(){
    var rightTopMenu = $("<div class=\"rightTopMenu\"></div>");
    var tuoluoyi = $("<img id=\"tuoluoyi\"class=\"playButton\" src=\"../image/icon/陀螺仪.png\"></img>");
    tuoluoyi.click(function () {
        if(freeze) {
            closegyroFreeze();
            freeze = false;
        }
        else {
            opengyroUnfreeze();
            freeze = true;
        }
    });
    var chenjin = $("<img class=\"playButton\" src=\"../image/icon/沉浸.png\"></img>");
    chenjin.click(function () {$(".menuPanel").fadeOut("slow");});
    $("#video3d").click(function () {$(".menuPanel").fadeIn("slow");});
    var map = $("<img class=\"playButton\" src=\"../image/icon/地图.png\"></img>");
    rightTopMenu.append(tuoluoyi);
    rightTopMenu.append(chenjin);
    rightTopMenu.append(map);
    return rightTopMenu;
}

// 初始化右下方界面
function initRightBottomMenu(){
    var rightBottomMenu = $("<div class=\"rightBottomMenu\"></div>");
    var dianzan = $("<img class=\"playButton\" src=\"../image/icon/点赞.png\"></img>");
    var shoucang = $("<img class=\"playButton\" src=\"../image/icon/收藏.png\"></img>");
    var pinglun = $("<img class=\"playButton\" src=\"../image/icon/评论.png\"></img>");
    var guanzhu = $("<img class=\"playButton\" src=\"../image/icon/关注.png\"></img>");
    var jieshuo = $("<img class=\"playButton\" src=\"../image/icon/解说.png\"></img>");
    var beijinyinyue = $("<img class=\"playButton\" src=\"../image/icon/背景音乐.png\"></img>");
    rightBottomMenu.append(dianzan);
    rightBottomMenu.append(shoucang);
    rightBottomMenu.append(pinglun);
    rightBottomMenu.append(guanzhu);
    rightBottomMenu.append(jieshuo);
    rightBottomMenu.append(beijinyinyue);
    return rightBottomMenu;
}

// 初始化推荐界面
function initScenicSpotMenu(){
    var scenicSpotMenu = $("<div class=\"swiper scenicSpotMenu\" style='background: rgba(59,8,13,0.18)'></div>");
    var swiperwrapper = $("<div class=\"swiper-wrapper\">");
    // 获取视频关联信息
    $.ajax({
        type : "post",
        url : "../../getresourcesbyresourceid",
        data : {
            "resource_id":resource_id
        },
        async : false,
        success : function(res){
            var resources = res.resources;
            for(var i = 0 ; i < resources.length ;i++) {
                var swiperslide = $("<div class=\"swiper-slide\"></div>");
                var videoChangeCube = $("<div class=\"videoChangeCube\"></div>");
                if(resources[i].resource_thumbnail != null){
                    var img = $("<img class=\"videoImage\" src=\"" + resources[i].resource_thumbnail + "\"/>");
                    videoChangeCube.append(img);
                }
                let resourceId = resources[i].resource_id;
                img.click(function () {changeVideo(resourceId);});
                var videoNameText = $("<text class=\"videoNameText\">" + resources[i].resource_name +"</text>");

                videoChangeCube.append(videoNameText);

                swiperslide.append(videoChangeCube);
                swiperwrapper.append(swiperslide);
            }
            var swiperpagination = $("<div class=\"swiper-pagination\"></div>");
            scenicSpotMenu.append(swiperwrapper);
            scenicSpotMenu.append(swiperpagination);
        }
    });
    return scenicSpotMenu;
}

// 初始化视频面板
function initChoosePanel(resource_id){
    // 获取工具栏
    var showUI = $(".showUI");
    // 获取滑动窗口
    var choosePanel = this.getchoosePanel(resource_id);
    choosePanel.appendTo(showUI);
    var resourceswiper = new Swiper(".resourceSwiper", {
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
    var body = $("body");
    // 获取滑动窗口
    var videoInfoPanel = this.getvideoInfoPanel(videoId);
    body.append(videoInfoPanel);

    // 景区下拉界面滑动窗口
    var sceniczoneswiper = new Swiper(".sceniczoneswiper", {
        direction: "vertical",
        slidesPerView: "auto",
        freeMode: true,
        scrollbar: {
            el: ".swiper-scrollbar",
        },
        mousewheel: true,
    });
}
// 定义视频推荐面板
function getchoosePanel(resource_id){
    var choosePanel = $("<div id=\"choosePanel\" style='background: #a3c6e2' class=\"swiper resourceSwiper\"></div>");
    var swiperwrapper = $("<div class=\"swiper-wrapper\">");
    // 获取视频关联信息
    $.ajax({
        type : "post",
        url : "../../getresourcesbyresourceid",
        data : {
            "resource_id":resource_id
        },
        async : false,
        success : function(res){
            var resources = res.resources;
            for(var i = 0 ; i < resources.length ;i++) {
                var swiperslide = $("<div class=\"swiper-slide\"></div>");
                var videoChangeCube = $("<div class=\"videoChangeCube\"></div>");
                if(resources[i].resource_thumbnail != null){
                    var img = $("<img class=\"videoImage\" src=\"" + resources[i].resource_thumbnail + "\"/>");
                    videoChangeCube.append(img);
                }
                let resourceId = resources[i].resource_id;
                // 收藏
                var starImage = $("<img class=\"starImage\" style=\"width: 100px;\" src=\"../image/Star-Light.png\"/>");
                videoChangeCube.append(starImage);
                // 判断用户是否有权限
                // 是否可观看
                if(judgeUserResource(user_id,resourceId)){
                    img.click(function () {changeVideo(resourceId);});
                }else {
                    var hideEyeImage = $("<img class=\"hideEyeImage\" src=\"../image/Hide-Light.png\"/>");
                    videoChangeCube.append(hideEyeImage);
                }
                var videoNameText = $("<text class=\"videoNameText\">" + resources[i].resource_name +"</text>");

                videoChangeCube.append(videoNameText);

                swiperslide.append(videoChangeCube);
                swiperwrapper.append(swiperslide);
            }
            var swiperpagination = $("<div class=\"swiper-pagination\"></div>");
            choosePanel.append(swiperwrapper);
            choosePanel.append(swiperpagination);
        }
    });
    return choosePanel;
}
// 定义视频面板
function getvideoInfoPanel(videoId){
    var videoInfoPanel = $("<div class=\"videoInfoPanel\"></div>");
    var logo = $("<img class=\"logo\" src=\"../image/logo.png\"></img>");
    var SceniczonePanel = getSceniczonePanel();
    videoInfoPanel.append(SceniczonePanel);
    SceniczonePanel.fadeOut("slow");
    // img点击后显示详细信息界面
    logo.click( function () {
        if(SceniczonePanelState){
            $(".sceniczonePanel").fadeOut("slow");
            SceniczonePanelState = false;
        }else {
            $(".sceniczonePanel").fadeIn("slow");
            SceniczonePanelState = true;
        }
    });
    videoInfoPanel.append(logo);

    return videoInfoPanel;
}
// 定义景区信息界面
function getSceniczonePanel(){
    var SceniczonePanel = $("<div class=\"sceniczonePanel\"></div>");
    var sceniczoneswiper = $("<div class=\"swiper sceniczoneswiper\" id='sceniczone-swiper'></div>");
    var swiper_wrapper = $("<div class=\"swiper-wrapper\"></div>");
    var swiper_scrollbar = $("<div class=\"swiper-scrollbar\"></div>");
    var swiper_slide = $("<div class=\"swiper-slide\" id='sciencezone-swiper-slide'></div>");
    var p1 = $("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In luctus,\n" +
        "            ex eu sagittis faucibus, ligula ipsum sagittis magna, et imperdiet\n" +
        "            dolor lectus eu libero. Vestibulum venenatis eget turpis sed\n" +
        "            faucibus. Maecenas in ullamcorper orci, eu ullamcorper sem. Etiam\n" +
        "            elit ante, luctus non ante sit amet, sodales vulputate odio. Aenean\n" +
        "            tristique nisl tellus, sit amet fringilla nisl volutpat cursus.\n" +
        "            Quisque dignissim lectus ac nunc consectetur mattis. Proin vel\n" +
        "            hendrerit ipsum, et lobortis dolor. Vestibulum convallis, nibh et\n" +
        "            tincidunt tristique, nisl risus facilisis lectus, ut interdum orci\n" +
        "            nisl ac nunc. Cras et aliquam felis. Quisque vel ipsum at elit\n" +
        "            sodales posuere eget non est. Fusce convallis vestibulum dolor non\n" +
        "            volutpat. Vivamus vestibulum quam ut ultricies pretium.</p>");
    var p2 = $("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In luctus,\n" +
        "            ex eu sagittis faucibus, ligula ipsum sagittis magna, et imperdiet\n" +
        "            dolor lectus eu libero. Vestibulum venenatis eget turpis sed\n" +
        "            faucibus. Maecenas in ullamcorper orci, eu ullamcorper sem. Etiam\n" +
        "            elit ante, luctus non ante sit amet, sodales vulputate odio. Aenean\n" +
        "            tristique nisl tellus, sit amet fringilla nisl volutpat cursus.\n" +
        "            Quisque dignissim lectus ac nunc consectetur mattis. Proin vel\n" +
        "            hendrerit ipsum, et lobortis dolor. Vestibulum convallis, nibh et\n" +
        "            tincidunt tristique, nisl risus facilisis lectus, ut interdum orci\n" +
        "            nisl ac nunc. Cras et aliquam felis. Quisque vel ipsum at elit\n" +
        "            sodales posuere eget non est. Fusce convallis vestibulum dolor non\n" +
        "            volutpat. Vivamus vestibulum quam ut ultricies pretium.</p>");
    var p3 = $("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In luctus,\n" +
        "            ex eu sagittis faucibus, ligula ipsum sagittis magna, et imperdiet\n" +
        "            dolor lectus eu libero. Vestibulum venenatis eget turpis sed\n" +
        "            faucibus. Maecenas in ullamcorper orci, eu ullamcorper sem. Etiam\n" +
        "            elit ante, luctus non ante sit amet, sodales vulputate odio. Aenean\n" +
        "            tristique nisl tellus, sit amet fringilla nisl volutpat cursus.\n" +
        "            Quisque dignissim lectus ac nunc consectetur mattis. Proin vel\n" +
        "            hendrerit ipsum, et lobortis dolor. Vestibulum convallis, nibh et\n" +
        "            tincidunt tristique, nisl risus facilisis lectus, ut interdum orci\n" +
        "            nisl ac nunc. Cras et aliquam felis. Quisque vel ipsum at elit\n" +
        "            sodales posuere eget non est. Fusce convallis vestibulum dolor non\n" +
        "            volutpat. Vivamus vestibulum quam ut ultricies pretium.</p>");
    var p4 = $("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In luctus,\n" +
        "            ex eu sagittis faucibus, ligula ipsum sagittis magna, et imperdiet\n" +
        "            dolor lectus eu libero. Vestibulum venenatis eget turpis sed\n" +
        "            faucibus. Maecenas in ullamcorper orci, eu ullamcorper sem. Etiam\n" +
        "            elit ante, luctus non ante sit amet, sodales vulputate odio. Aenean\n" +
        "            tristique nisl tellus, sit amet fringilla nisl volutpat cursus.\n" +
        "            Quisque dignissim lectus ac nunc consectetur mattis. Proin vel\n" +
        "            hendrerit ipsum, et lobortis dolor. Vestibulum convallis, nibh et\n" +
        "            tincidunt tristique, nisl risus facilisis lectus, ut interdum orci\n" +
        "            nisl ac nunc. Cras et aliquam felis. Quisque vel ipsum at elit\n" +
        "            sodales posuere eget non est. Fusce convallis vestibulum dolor non\n" +
        "            volutpat. Vivamus vestibulum quam ut ultricies pretium.</p>");
    var phone = $("<text id=\"phone\" style=\"color: white\">phone </text>");
    var text1 = $("<text id=\"alpha\" style=\"color: white\">alpha </text>");
    var text2 = $("<text id=\"beta\" style=\"color: white\">beta </text>");
    var text3 = $("<text id=\"gamma\" style=\"color: white\">gamma </text>");
    SceniczonePanel.append(sceniczoneswiper);
    sceniczoneswiper.append(swiper_wrapper);
    sceniczoneswiper.append(swiper_scrollbar);
    swiper_wrapper.append(swiper_slide);
    swiper_slide.append(phone);
    swiper_slide.append(text1);
    swiper_slide.append(text2);
    swiper_slide.append(text3);
    swiper_slide.append(p1);
    swiper_slide.append(p2);
    swiper_slide.append(p3);
    swiper_slide.append(p4);
    return SceniczonePanel;
}
// 关闭 景区信息界面
function closeSceniczonePanel(){
    $(".sceniczonePanel").fadeOut("slow");
    SceniczonePanelState = false;
}
// 判断用户是否有权限打开视频
function judgeUserResource(user_id,resource_id){
    var judge = false;
    $.ajax({
        type: "post",
        url: "../../isabletoplaybyid",
        data: {
            "resource_id": resource_id,
            "user_id":user_id
        },
        async: false,
        success: function (res) {
            if(res.msg)judge = true;
        }
    });
    return judge;
}