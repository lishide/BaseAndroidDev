# BaseAndroidDev
一个基本的开发框架，便于自己及团队快速开发，包含各种实用的BaseActivity、BaseFragment、framework、utils等，工(lao)程(si)师(ji)必备。

##一、Base
BaseActivity、BaseFragment、BaseAdapter

## 二、Net framework\Volley
关于更多volley的介绍和使用方法，请移步至本人的另一repo：[MyVolley：Volley的使用及其工具类的封装][1] 查看更多。
### 1. VolleyRequestUtil
**用GET方式请求网络资源：**

        VolleyRequestUtil.RequestGet(this, url, "tag", 
            new VolleyListenerInterface(this, VolleyListenerInterface.mListener,
                                        VolleyListenerInterface.mErrorListener) {
            // Volley请求成功时调用的函数
            @Override
            public void onMySuccess(String result) {
                Toast.makeText(this, s, Toast.LENGTH_LONG).show();
            }
        
            // Volley请求失败时调用的函数
            @Override
            public void onMyError(VolleyError error) {
                // ...
            }
        });
        
**用POST方式请求网络资源：**

        VolleyRequestUtil.RequestPOST(this, url, "tag", 
            new VolleyListenerInterface(this, VolleyListenerInterface.mListener,
                                        VolleyListenerInterface.mErrorListener) {
            // Volley请求成功时调用的函数
            @Override
            public void onMySuccess(String result) {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            }
        
            // Volley请求失败时调用的函数
            @Override
            public void onMyError(VolleyError error) {
                // ...
            }
        });

### 2. PostUploadRequest
用于上传文件的框架，封装于Volley。

       /**
         * 上传文件分两步：
         * 1.调用此工具类，将文件传输到服务器（从本地选择文件的过程未列出）
         * 2.调用修改文件名的接口，修改数据库中相应的字段，完成上传文件操作
         */
        private void uploadFile() {
            String upLoadServerUri = "";
            HashMap<String, String[]> map = new HashMap<>();
            map.put("uploadedfile", new String[]{filename, cutnameString});
            MyApplication.getQueue().add(new PostUploadRequest(upLoadServerUri,
                    map, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    updateShopPic();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(mContext, "文件上传失败", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> map = new HashMap<String, String>();
                        //map.put("token", "");
                    return map;
                }
            });
        }
        
        private void updateShopPic() {
            Map<String, String> params = new HashMap<>();
            params.put("id", "1");
            params.put("pic", cutnameString);
            VolleyRequestUtil.RequestPost(this, "updateshoppic.php", "updateShopPic", params,
                    new VolleyListenerInterface(this, VolleyListenerInterface.mListener,
                            VolleyListenerInterface.mErrorListener) {
                        // Volley请求成功时调用的函数
                        @Override
                        public void onMySuccess(String response) {
                        
                        }
                        // Volley请求失败时调用的函数
                        @Override
                        public void onMyError(VolleyError error) {
    
                        }
                    });
        }

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

> 本人会坚持在这个项目上实践最新的技术，逐渐完善，让它越来越完美！ Ps：本项目中使用的许多好用的轮子均根据网上各位大牛的开源项目参考和修改而来，首先向各位致谢，如果有什么使用不当的地方，请联系我。您在使用过程中，发现 bug 或有好的建议欢迎issue、email(lishidezy@gmail.com)，如果感觉对你有帮助也欢迎点个 star，留下点印记吧。



  [1]: https://github.com/lishide/MyVolley
