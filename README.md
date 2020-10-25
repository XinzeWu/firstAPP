# firstAPP
第一次写APP，利用原生态的安卓studio
采用的按钮事件控制页面跳转
CSDN链接：https://blog.csdn.net/qq_44647796/article/details/109255970
## 写一个简易手机手机APP

<font color=#999AAA >闲来无事，想算算姻缘，所以自己写了一个周易算卦的APP，下面我们就开始吧！
</font>相关代码已上传到了：
@[TOC](文章目录)


<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">

# 前言

<font color=#999AAA >借助工具Android Studio</font>
话不多说，先来几张效果图

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201024110519164.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ0NjQ3Nzk2,size_16,color_FFFFFF,t_70#pic_center)
输入想要算卦的内容，然后点击开始算卦就可以算卦了
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201024110621806.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ0NjQ3Nzk2,size_16,color_FFFFFF,t_70#pic_center)
最后的结果，卦象，变爻，都是玄学大师来解读的事情了
但是**代码 = 玄学**
我写代码，所以：**我 = 玄学大师**
解读这一卦就是不大行的意思，所以单身快乐！！！


<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">

正文时间到：

# 一、Android Studio的安装
前人之述备矣，直接上一个大神的链接：
[超详细超多图安装Android Studio](https://blog.csdn.net/qq_41976613/article/details/91432304)

安装成功就是这个样子的啦，看我的界面是不是觉得神清气爽。。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201024112625176.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ0NjQ3Nzk2,size_16,color_FFFFFF,t_70#pic_center)




# 二、图像化编程
## 重磅：xml界面可以不用代码！

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201024112957305.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ0NjQ3Nzk2,size_16,color_FFFFFF,t_70#pic_center)
看这个界面，所有的位置可以直接拖动，文字可以直接加在这里，当然也可以定义在字符串里面

这个字符串的定义我会在后面讲
# 三.js文件也超级简单
先导入一堆包
<font color=#999AAA >代码如下（示例）：

```java
package com.example.myfirstapp;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

```
然后启动一个开始界面，打开activate_main.xml

```java
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
```
随后我定义了一个函数和按钮事件对接

```java
@RequiresApi(api = Build.VERSION_CODES.O)
    public void sendMessage(View view) {
        //EditText editText = findViewById(R.id.editText);
        change();
        setContentView(R.layout.activity_display_message);
        TextView textView1 = findViewById(R.id.gua_name);
        textView1.setText(data.guaName);

        TextView textView2 = findViewById(R.id.yao_name);
        textView2.setText(data.changeYao);

        TextView textView3 = findViewById(R.id.yao_web);
        textView3.setText(data.guaExp);


    }
```
数据的传输，我直接定义了一个类，这个类是在包内可以共享的，但还是不要定义成public比较好
养成好的代码习惯

```java
package com.example.myfirstapp;

class data {
    static String guaName;
    static String changeYao;//包括可变的爻以及对应解释的卦
    static String guaExp;
}

```
返回按钮的事件

```java
@RequiresApi(api = Build.VERSION_CODES.O)
    public void getBack(View view){
        setContentView(R.layout.activity_main);
    }
```
至于xml上的字符串，直接输入也没有问题，但是编程可复用、易更改的特性让我本能想定义在string.xml文件里

```handlebars
<resources>
    <string name="app_name">周易算卦</string>
    <string name="edit_message">输入您想占卜的对象</string>
    <string name="button_send">开始算卦</string>
</resources>

```
其实 so easy！
# 四. 遇到的问题
这句话疯狂报错，我已经确实把editText定义在xml文件里面了，但是加上这句话就会崩溃，哭唧唧。
```java
EditText editText = findViewById(R.id.editText);
```
有大神会解决这个问题吗？老规矩，请你喝秋天的奶茶！

<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">

# 总结
我编写的安卓APP，终于迭代出了第二个版本了，欣慰

感谢一直以来**YZY**师姐的鼓励

感谢我的好兄弟**XJH**的帮助，以及诸多好兄弟做的内测

