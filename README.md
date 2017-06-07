# music
花了一个星期时间做的的基于SSH一个小型WEB项目，界面仿的是桌面版的网易云音乐，采用前后端分离，使用iframe将多个浏览界面整合到一个网页上了，主页和iframe里的网页之间的交互postMessage通过跨文档请求结合sessionStorage来实现，消息实时通知功能是通过webSocket来实现的。前端界面元素使用了bootstrap,设计的时候并没有进行响应式布局处理，主要用到了bootstrap里的栅格化布局和按钮，模态框，表格等元素。同时也用到了angularJs，angularJs的ng-repeat非常好用，我主要用它来控制歌曲列表和评论列表之类的显示，这比使用jquery直接操作dom要方便多了。

## 这个项目我在自己的云服务器上已经部署:
    访问链接http://www.myfirstday.cn/music
### 主要的技术:
前端：html5,css3,javascript,jquery,bootstrap,angularJs  
后端：java,struts2,hibernate4,spring4,webscoket
图标：阿里巴巴图标库，bootstrap自带图标


### 用例图:
![image](https://github.com/1471880107/pic/blob/master/music_1.png)
![image](https://github.com/1471880107/pic/blob/master/music_2.png)

### 数据库类图：
![image](https://github.com/1471880107/pic/blob/master/music_3.jpg)
