var windowswidth = window.screen.width;
// 右边是代表屏幕宽度,设置一个变量为屏幕宽度. $(window).width()是窗口的宽度
var windowsheight = window.screen.height;
// 右边是代表屏幕高度,设置一个变量为屏幕高度.
//定义两个初始化的随机变量（0的意思就是初始化）,创建雪花
function createSnow() {
    var top = 0;
    var left = 0;

    // 创建雪花
    var top_random = Math.random() * windowsheight;
    var left_random = Math.random() * windowswidth;
    // 定义变量的随机的长度和宽度为屏幕的长度和宽度
    var div = document.createElement(div);
    var div = new Image;
    div.src = "img/心电.png";
    // 定义变量为div,document.createElement()是在对象中创建一个对象
    //div.className = "snow";
    div.style.height = "80px";
    div.style.position = "absolute";

    // className 属性设置或返回元素的class 属性,对div变量属性进行设置
    div.style.transform = "scale(" + (Math.random()) + ")"
    //1、transform 属性向元素应用 2D 或 3D 转换。该属性允许您对元素进行旋转、缩放、移动或倾斜。
    //返回 transform 属性：object.style.transform
    //设置 transform 属性：object.style.transform="none|transform-functions|initial|inherit"
    //2、scale(sx, sy)scale() 方法为画布的当前变换矩阵添加一个缩放变换。缩放通过独立的水平和垂直缩放因子来完成。
    //例如，传递一个值 2.0 和 0.5 将会导致绘图路径宽度变为原来的两倍，而高度变为原来的 1/2。
    document.body.appendChild(div);
    //将上面创建的div元素加入到BODY的尾部

    //雪花飘落
    //setinterval(函数，运行周期（以毫秒计）) setInterval(function, milliseconds)
    setInterval(function () {
        div.style.left = left_random + left + "px";
        // 变量名.style.属性名是固定格式,往左边移动  右边为左边长度变量加上往左雪花变量加上"px"像素单位,不加单位无法识别
        // 对变量div设置属性
        div.style.top = top_random + top + "px";
        left += 1;  //   +=中间不能空格
        top += 1;

        //如果雪花跑到屏幕外面了,让雪花重新返回屏幕顶部
        if (left_random + left >= windowswidth) {
            left_random = Math.random();
            left = 0;
        }

        if (top_random + top >= windowsheight) {
            top_random = Math.random();
            top = 0;
        }
    }, 20)
}

for (var i = 0; i < 50; i++) {
    createSnow()
}