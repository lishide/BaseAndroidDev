# BaseAndroidDev
一个基本的开发框架，便于自己及团队快速开发，包含各种实用的 BaseActivity 、BaseFragment 、framework 、utils  等，工（lao）程（si）师（ji）必备。

##一、Base
BaseActivity、BaseFragment、BaseAdapter

## 二、Net framework \ Volley
关于更多 volley 的介绍和使用方法，请移步至本人的另一 repo：[MyVolley：Volley 的使用及其工具类的封装][1] 查看更多。

## 三、自定义控件
收集整理一些实用的自定义控件，会持续更新。

|widget|描述|
|---|---|
|com.base.adev.view.MarqueeTextView|文字跑马灯功能（自定义跑马灯控件）|
|com.base.adev.view.NoticeView|广告条控件|
|com.base.adev.view.LedTextView|LED文字控件|
|com.base.adev.view.coverflow.CoverFlowView|CoverFlow自定义控件|
|com.base.adev.view.ActionSheetDialog|仿ios的底部弹出dialog|
|com.base.adev.view.IosAlertDialog|仿ios的中间弹出dialog|
|com.base.adev.view.ToastView|自定义ToastView控件|

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
|MyGlideLoadUtil|Glide加载图片工具类|
|MyBitmapImageViewTarget|Glide加载图片设置合适ScaleType的工具类|
|StatusBarUtils|状态栏设置工具类|
|GetFolderSizeManager|获取文件夹大小|
|DataCleanManager|本应用数据清除管理器|

## 五、用到的开源库

|dependencies|描述|
|---|---|
|compile 'com.mcxiaoke.volley:library:1.0.19'|Volley--网络请求框架|
|compile 'com.google.code.gson:gson:2.7'|Gson--比较常用的JSON解析序列化库|
|compile 'com.github.bumptech.glide:glide:3.7.0'|图片加载库Glide|
|compile 'com.ashokvarma.android:bottom-navigation-bar:1.3.0'|BottomNavigation--Bottom Navigation Bar|
|compile 'com.bigkoo:svprogresshud:1.0.6'|SVProgressHUD For Android 精仿iOS的提示库|
|compile 'com.yanzhenjie:recyclerview-swipe:1.0.2'|SwipeRecyclerView--RecyclerView侧滑菜单，长按拖拽，滑动删除，加载更多下拉刷新等|
|compile 'liji.library.dev:citypickerview:0.9.0'|省市区三级联动|
|compile 'cn.finalteam.rxgalleryfinal:library:0.0.3'|图片/视频文件选择器|
|compile 'me.drakeet.materialdialog:library:1.3.1'|Material Design 风格的自定义Dialog|

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

**本人会坚持在这个项目上实践最新的技术，逐渐完善，让它越来越完美！ Ps：本项目中使用的许多好用的轮子均根据网上各位大牛的开源项目参考和修改而来，首先向各位致谢，如果有什么使用不当的地方，请联系我。代码开源在 GitHub：[BaseAndroidDev][2]，您在使用过程中，发现 bug 或有好的建议欢迎 issue 、email (lishidezy@gmail.com)，如果感觉对你有帮助也欢迎点个 star，留下点印记吧。**



  [1]: https://github.com/lishide/MyVolley
  [2]: https://github.com/lishide/BaseAndroidDev
