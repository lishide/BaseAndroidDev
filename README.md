# BaseAndroidDev
一个基本的开发框架，便于自己及团队快速开发，包含各种实用的 BaseActivity 、BaseFragment 、framework 、utils  等，工（lao）程（si）师（ji）必备。

[![](https://jitpack.io/v/lishide/BaseAndroidDev.svg)](https://jitpack.io/#lishide/BaseAndroidDev)

## 使用
#### JitPack 引入方法
##### 1. 在 Project 下的 build.gradle 添加
```java
allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```

##### 2. 在 Module 下的 build.gradle 添加

```java
dependencies {
            compile 'com.github.lishide:BaseAndroidDev:v+latest version'
            //latest version 见上方 JitPack 图标所示，如：
            compile 'com.github.lishide:BaseAndroidDev:v1.0.7'
    }
```

**Ps：在使用的时候请看清是以 `com.base.adev` 开头的包名。**

---

## 文档 Wiki

### [--- 版本更新说明 - 入口](https://github.com/lishide/BaseAndroidDev/blob/master/UPDATE_VERSION.md)

---

## 一、Base
### 1. BaseActivity

基础 Activity

### 2. BaseRecyclerActivity

只有列表显示的界面的基础 Activity

### 3. BaseTabBottomActivity

底部 Tab 的基础 Activity

### 4. BaseTabLayActivity

顶部 TabLayout 的基础 Activity

### 5. BaseFragment

基础 Fragment

### 6. BaseRecyclerFragment

只有列表显示的界面的基础 Fragment

### 7. BaseAdapter

基础 Adapter

## 二、Net framework

#### 1. Volley
关于更多 volley 的介绍和使用方法，请移步至本人的另一 repo：[MyVolley：Volley 的使用及其工具类的封装][1] 查看更多。
（本人已放弃使用，仍然可用。不过强烈推荐使用 **2. NoHttp** ！！！）

#### 2. NoHttp
NoHttp —— 一个有情怀的网络框架 ，让你的网络请求更简单。
基于 NoHttp 的封装：[NoHttpConnecter][3]

## 三、自定义控件
收集整理一些实用的自定义控件，会持续更新。

|widget|描述|
|---|---|
|com.base.adev.view.MarqueeTextView|文字跑马灯功能（自定义跑马灯控件）|
|com.base.adev.view.NoticeView|广告条控件|
|com.base.adev.view.LedTextView|LED 文字控件|
|com.base.adev.view.coverflow.CoverFlowView|CoverFlow 自定义控件|
|com.base.adev.view.ActionSheetDialog|仿 iOS 的底部弹出 dialog|
|com.base.adev.view.IosAlertDialog|仿 iOS 的中间弹出 dialog|
|com.base.adev.view.ToastView|自定义 ToastView 控件|

## 四、工具类
收集整理一些实用的工具类，会持续更新。

|utils|描述|
|---|---|
|CommonUtils|通用工具类（字符合法性等常见工具类）|
|DateUtil|日期时间帮助类|
|ScreenUtil|获取屏幕的大小|
|ToastUtils|吐司相关工具类|
|ClickUtil|点击事件工具类|
|SystemBarTintManager|沉浸状态栏使用|
|MyGlideLoadUtil|Glide 加载图片工具类|
|MyBitmapImageViewTarget|Glide 加载图片设置合适 ScaleType 的工具类|
|StatusBarUtils|状态栏设置工具类|
|GetFolderSizeManager|获取文件夹大小|
|DataCleanManager|本应用数据清除管理器|

## 五、用到的开源库

#### - [Gson](https://github.com/google/gson)
比较常用的 JSON 解析序列化库

**`compile 'com.google.code.gson:gson:2.8.2'`**

#### - [Glide](https://github.com/bumptech/glide)
图片加载库

An image loading and caching library for Android focused on smooth scrolling

**`compile 'com.github.bumptech.glide:glide:3.7.0'`**

#### - [Bottom Navigation Bar](https://github.com/Ashok-Varma/BottomNavigation)
This Library helps users to use Bottom Navigation Bar (A new pattern from google) with ease and allows ton of customizations

**`compile 'com.ashokvarma.android:bottom-navigation-bar:2.0.2'`**

#### - [SVProgressHUD](https://github.com/saiwu-bigkoo/Android-SVProgressHUD)
精仿 iOS 的提示库 SVProgressHUD

**`compile 'com.bigkoo:svprogresshud:1.0.6'`**

#### - [SwipeRecyclerView](https://github.com/yanzhenjie/SwipeRecyclerView)
RecyclerView 侧滑菜单，Item拖拽，滑动删除 Item，自动加载更多，和 ViewPager、DrawerLayout 结合使用，和任何下拉刷新框架结合使用。

**`compile 'com.yanzhenjie:recyclerview-swipe:1.0.4'`**

#### - [省市区三级联动](https://github.com/crazyandcoder/citypicker)
citypicker 高仿 iOS 滚轮实现 省市区 城市选择三级联动

**`compile 'liji.library.dev:citypickerview:0.9.0'`**

#### - [RxGalleryFinal](https://github.com/FinalTeam/RxGalleryFinal)
图片/视频文件选择器

**`compile 'cn.finalteam.rxgalleryfinal:library:0.0.9'`**

#### - [MaterialDialog](https://github.com/drakeet/MaterialDialog)
Material Design 风格的自定义 Dialog

**`compile 'me.drakeet.materialdialog:library:1.3.1'`**

#### - [CircleImageView](https://github.com/hdodenhof/CircleImageView)
A circular ImageView for Android

**`compile 'de.hdodenhof:circleimageview:2.1.0'`**

#### - [PhotoView](https://github.com/bm-x/PhotoView)
图片浏览缩放控件

**`compile 'com.bm.photoview:library:1.4.1'`**

#### - [Banner](https://github.com/saiwu-bigkoo/Android-ConvenientBanner)
广告栏控件

**`compile 'com.bigkoo:convenientbanner:2.0.5'`**

#### - [翻页效果](https://github.com/saiwu-bigkoo/Android-ConvenientBanner)
各种翻页效果

**`compile 'com.ToxicBakery.viewpager.transforms:view-pager-transforms:1.2.32@aar'`**

#### - [GSYVideoPlayer 视频播放器](https://github.com/CarGuo/GSYVideoPlayer)
视频播放器（IJKplayer），HTTPS支持，支持弹幕，支持基本的拖动，声音、亮度调节，支持边播边缓存，支持视频本身自带rotation的旋转（90,270之类），重力旋转与手动旋转的同步支持，支持列表播放 ，直接添加控件为封面，列表全屏动画，视频加载速度，列表小窗口支持拖动，5.0的过场效果，调整比例，多分辨率切换，支持切换播放器，进度条小窗口预览，其他一些小动画效果。

**`compile 'com.shuyu:GSYVideoPlayer:2.0.8'`**

#### - [AndroidVideoCache](https://github.com/danikula/AndroidVideoCache)
Cache support for any video player with help of single line

**`compile 'com.danikula:videocache:2.6.4'`**

#### - [BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)
RecyclerView 的强大的 BaseAdapter

**`compile 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.3'`**

#### - [Volley](https://github.com/lishide/MyVolley)
网络请求框架

**`compile 'com.mcxiaoke.volley:library:1.0.19'`**

#### - [NoHttp](https://github.com/yanzhenjie/NoHttp)
Android实现Http标准协议框架，支持缓存（提供五种缓存模式）、代理、重定向，底层可动态切换OkHttp、URLConnection，与RxJava完美结合，比Retrofit更简单易用。 http://www.nohttp.net

**`compile 'com.yanzhenjie.nohttp:okhttp:1.1.2'`**

#### - [AndPermission](https://github.com/yanzhenjie/AndPermission)
Android 6.0运行时权限管理

**`compile 'com.yanzhenjie:permission:1.1.0'`**

#### - [Butter Knife](https://github.com/JakeWharton/butterknife)
Bind Android views and callbacks to fields and methods.

#### - [NoHttpConnecter](https://github.com/lishide/NoHttpConnecter)
小编简单封装的 NoHttp

**`compile 'com.github.lishide:NoHttpConnecter:v1.0.3'`**

#### - [ImgCoverFlow](https://github.com/lishide/ImgCoverFlow)
CoverFlow 列表

**`compile 'com.github.lishide:ImgCoverFlow:v1.0.1'`**

#### - [MarqueeViewLibrary](https://github.com/gongwen/MarqueeViewLibrary)
通过MarqueeFactory来提供各种样式的跑马灯View，支持自定义跑马灯ItemView

**`compile 'compile 'com.gongwen:marqueelibrary:1.0.4'`**

#### - [EventBus](https://github.com/greenrobot/EventBus)
事件总线

**`compile 'org.greenrobot:eventbus:3.0.0'`**

**Ps：以上开源库版本可能不是最新的，建议使用时查看原作 GitHub 使用最新版。**

## 六、Material Design

|widget|描述|
|---|---|
|android.support.v7.widget.Toolbar|Toolbar|
|android.support.design.widget.TabLayout|TabLayout|
|android.support.design.widget.CoordinatorLayout|CoordinatorLayout|
|android.support.design.widget.AppBarLayout|AppBarLayout|
|android.support.design.widget.CollapsingToolbarLayout|CollapsingToolbarLayout|
|android.support.v4.widget.NestedScrollView|NestedScrollView|
|android.support.design.widget.FloatingActionButton|FloatingActionButton：浮动按钮|
|Snackbar.make(view, "", Snackbar.LENGTH_SHORT).show();|Snackbar：消息的提示和动作反馈|


---

**本人会坚持在这个项目上实践最新的技术，逐渐完善，让它越来越完美！ Ps：本项目中使用的许多好用的轮子均根据网上各位大牛的开源项目参考和修改而来，首先向各位致谢，如果有什么使用不当的地方，请联系我。代码开源在 GitHub：[BaseAndroidDev][2]，您在使用过程中，发现 bug 或有好的建议欢迎 issue 、email (lishidezy@gmail.com)，如果感觉对你有帮助也欢迎点个 star，留下点印记吧。**



  [1]: https://github.com/lishide/MyVolley
  [2]: https://github.com/lishide/BaseAndroidDev
  [3]: https://github.com/lishide/NoHttpConnecter
