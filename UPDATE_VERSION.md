## 下方各个版本说明，可以当做简单的 wiki 使用~，效果可参考 DEMO。

### 1.0.5
* 升级 supportLibraryVersion，lib 中取消 support Library 的引用

### 1.0.4
* 升级部分开源库版本
* 将无需进行 base 类封装的第三方开源库依赖移入 App simple 的 module 中，减少 BaseAndroidDev 库的体积，减少无必要的依赖，同时尊重开源库原作的成果。

### 1.0.3
* 重构底部 Tab 的基础 Activity，增加更多设置 Tab 风格的方法

```java
addItem(new HomeFragment(), R.drawable.ic_tab_home_white_24dp, R.string.tab1, R.color.colorPrimary);
setNavBarStyle(BottomNavigationBar.MODE_FIXED, BottomNavigationBar.BACKGROUND_STYLE_STATIC);
initialise(R.id.ll_content);
```

### 1.0.1
* 增加了 BaseRecyclerActivity 和 BaseRecyclerFragment。

### 1.0.0
* 封装了 BaseActivity、BaseTabBottomActivity、BaseTabLayActivity、BaseFragment 及部分工具类和自定义 view。
