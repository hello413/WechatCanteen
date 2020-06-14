## 健康食堂预约小程序

##### **目的描述**

​	为了疫情安全，本微信小程序减少学校食堂学生吃饭排队的时间

---

##### **实现功能**

* 学生端

  * 订餐

  * 分流

    > 食堂人数流量达到某一上限时就禁止订单，并提示食堂人数过多，请稍后过来
  
* 食堂大妈端
  
  * 查看订单

---

**项目源码链接地址**：<a href="https://github.com/hello413/WechatCanteen.git">https://github.com/hello413/WechatCanteen.git</a>

---

##### 页面分析

---

**index页面**		

<img src="Image\login.png" alt="1589730019016" style="zoom:80%;" />

**需求分析**

> * 区分用户身份（学生点击立即点餐，食堂大妈点管理员模式）

**学生端**

* 登录页面

  <img src="Image\visitorlogin.png" alt="1589730089062" style="zoom:80%;" />

  **需求分析**

  > * 学生登录验证
  > * 没有账户就注册

  

  **接口分析**

  

  > **/visitor/login** 		——学生登录
  >
  > 
  >
  > | 当前状态 | 属性名         | 测试值             | 作用             |
  > | -------- | -------------- | :----------------- | ---------------- |
  > | request  | user_telephone | 13093944163        | 输入的测试手机号 |
  > |          | user_password  | 12345              | 输入的测试密码   |
  > | response | flag           | false              | 测试结果         |
  > |          | errorMsg       | "用户名或密码错误" | 提示信息         |

  

* 注册

  <img src="Image\visitorregist.png" alt="1589730089062" style="zoom:80%;" />

  **需求分析**

  > * 正则表达式规范格式
  > * 实现手机短信验证码功能
  > * 实现注册验证功能

  **接口分析**

  

  > **getCode** 		——短信发送平台
  >
  > 
  >
  > | 当前状态 | 属性名    | 测试值      | 作用           |
  > | -------- | --------- | :---------- | -------------- |
  > | request  | appId     | ***         | 应用id         |
  > |          | appSecret | ***         | 应用密匙       |
  > |          | messageId | 438         | 短信的唯一标识 |
  > |          | number    | 13093944163 | 接受方手机号   |
  > | response | code      | 1740        | 短信验证码     |
  >
  > **/visitor/regist**		——学生注册
  >
  > 
  >
  > | 当前状态 | 属性名         | 值                                        | 作用       |
  > | -------- | -------------- | ----------------------------------------- | ---------- |
  > | request  | user_name      | “张飞”                                    | 测试用户名 |
  > |          | user_telephone | 13093944163                               | 测试手机号 |
  > |          | user_password  | 12345                                     | 测试密码   |
  > | response | flag           | flase                                     | 测试结果   |
  > |          | errorMsg       | "注册失败!已经有人用这个用户名或手机号了" | 提示信息   |
  >

  

* 学生首页     ——当前食堂窗口信息	

<img src="Image\visitorindex.png" alt="visitorindex" style="zoom:80%;" />

**需求分析**

> * 轮播图
> * 查询楼层窗口信息展示和剩余可订餐人数展示



**接口分析**

> **/visitor/windows** 		——得到当前楼层窗口信息以及人数
>
> 
>
> | 当前状态 | 属性名  | 测试值                                                       | 作用           |
> | -------- | ------- | :----------------------------------------------------------- | -------------- |
> | request  | canteen | “新食堂”                                                     | 给出选择食堂   |
> |          | tier    | "一楼"                                                       | 给出选择楼层   |
> |          | time    | 1                                                            | 给出选择时间段 |
> | response | false   | true                                                         | 测试结果       |
> |          | object  | <font size=1rpx>[{window_id=1, window_name='赵氏卤肉饭', window_canteen='新食堂', window_tier=一楼, window_num=1, window_picture='/Images/new/one/1.jpg'}, {window_id=2, window_name='马氏炒饭', window_canteen='新食堂', window_tier=一楼, window_num=2, window_picture='/Images/new/one/2.jpg'}]</font> | 返回窗口       |

* 窗口菜单页面      ——当前窗口菜单信息

<img src="Image\windowmenu.png" alt="windowmenu" style="zoom:80%;" />

**需求分析**

> * 当前窗口菜单展示
> * 订餐

​	**接口分析**



> **/visitor/menu** 		——得到当前窗口菜单
>
> 
>
> | 当前状态 | 属性名    | 测试值                                                       | 作用         |
> | -------- | --------- | :----------------------------------------------------------- | ------------ |
> | request  | window_id | 1                                                            | 测试窗口id   |
> | response | flag      | true                                                         | 测试结果     |
> |          | data      | "赵氏卤肉饭"                                                 | 当前窗口店名 |
> |          | object    | <font size=1px>[{food_id: "6", food_name: "米饭", food_picture: "/Images/new/one/one/rich.jpg", food_price: "1.00", food_style: "0"},{food_id: "14", food_name: "黄瓜", food_picture: "/Images/new/one/one/cuke.jpg", food_price: "1.00", food_style: "0"},...]</font> | 菜品信息     |
>
> **/visitor/submit** 		——提交订单
>
> 
>
> | 当前状态 | 属性名    | 测试值                                                       | 作用         |
> | -------- | --------- | :----------------------------------------------------------- | ------------ |
> | request  | window_id | 1                                                            | 测试窗口id   |
> |          | price     | 22                                                           | 测试结果     |
> |          | object    | [{good_food_id:"3",good_num:"2"},{good_food_id:"4",good_num:"20"},{good_food_id:"6",good_num:"8"}] | 当前窗口店名 |
> |          | about     | ' '                                                          | 备注         |
> |          | time_id   | 1                                                            | 时间段编号   |
> | response | flag      | false                                                        | 测试结果     |
> |          | data      | "该楼层订餐人数过多，去别的楼层吧"                           | 提示信息     |



* 订单页面      ——当前订单信息

  <img src="Image\visitororder.png" alt="visitororder" style="zoom:80%;" />

  **需求分析**

  > * 订单展示
  > * 实现点击收货功能

  ​	**接口分析**

  

  > **/visitor/nofinishOrder** 		——得到未收货订单
  >
  > 
  >
  > | 当前状态 | 属性名 | 测试值                                                       | 作用       |
  > | -------- | ------ | :----------------------------------------------------------- | ---------- |
  > | request  |        |                                                              | 测试窗口id |
  > | response | flag   | true                                                         | 测试结果   |
  > |          | object | <font size=2px>[{order_id=1, window_name='赵氏卤肉饭', create_time='2020-06-13 22:32:51', style='待收货', order_price='2.0'},{order_id=3, window_name='赵氏卤肉饭', create_time='2020-06-13 22:57:59', style='待收货', order_price='5.0'}...]</font> | 菜品信息   |
  >
  > **/visitor/nofinishOrder** 		——得到未收货订单
  >
  > 
  >
  > | 当前状态 | 属性名 | 测试值                                                       | 作用       |
  > | -------- | ------ | :----------------------------------------------------------- | ---------- |
  > | request  |        |                                                              | 测试窗口id |
  > | response | flag   | true                                                         | 测试结果   |
  > |          | object | <font size=2px>[{order_id=1, window_name='赵氏卤肉饭', create_time='2020-06-13 22:32:51', style='待收货', order_price='2.0'},{order_id=3, window_name='赵氏卤肉饭', create_time='2020-06-13 22:57:59', style='待收货', order_price='5.0'}...]</font> | 菜品信息   |
  >
  > **/visitor/orderfinish** 		——点击完成订单
  >
  > 
  >
  > | 当前状态 | 属性名   | 测试值 | 作用       |
  > | -------- | -------- | :----- | ---------- |
  > | request  | order_id | 1      | 测试窗口id |
  > | response | flag     | true   | 测试结果   |

* 订单详情

  <img src="Image\orderfood.png" alt="orderfood" style="zoom:80%;" />

  **需求分析**

  > * 订单详情展示

  **接口分析**

  

  > **/visitor/orderfood**		——得到订单详情
  >
  > 
  >
  > | 当前状态 | 属性名   | 测试值                                                       | 作用       |
  > | -------- | -------- | :----------------------------------------------------------- | ---------- |
  > | request  | order_id | 1                                                            | 测试窗口id |
  > | response | flag     | true                                                         | 测试结果   |
  > |          | data     | "赵氏卤肉饭"                                                 | 窗口名     |
  > |          | object   | <font size=2px>[{food_name='黄瓜', food_picture='/Images/new/one/one/cuke.jpg', food_price='1.00', food_num='1'}，{food_name='汽水', food_picture='/Images/new/one/one/codewater.jpg', food_price='4.00', food_num='1'}...]</font> | 菜品信息   |

* 其他服务 	   ——针对开发人员

  <img src="Image\otherserice.png" alt="otherserice" style="zoom: 80%;" />

  **接口分析**

  

  > **/visitor/tosay**		——提交意见
  >
  > 
  >
  > | 当前状态 | 属性名 | 测试值       | 作用     |
  > | -------- | ------ | :----------- | -------- |
  > | request  | word   | "我想要福利" | 提交意见 |
  > | response |        |              |          |

---

**管理员端**

* 登录页面

  <img src="Image\adminilogin.png" alt="1589730089062" style="zoom:80%;" />
  
  **需求分析**
  
  > * 管理员登录验证
  
  **接口分析**
  
  
  
  > **/admini/login** 		——管理员登录
  >
  > 
  >
  > | 当前状态 | 属性名           | 测试值             | 作用             |
  > | -------- | ---------------- | :----------------- | ---------------- |
  > | request  | admini_telephone | 13093944163        | 输入的测试手机号 |
  > |          | admini_password  | 12345              | 输入的测试密码   |
  > | response | flag             | false              | 测试结果         |
  > |          | errorMsg         | "用户名或密码错误" | 提示信息         |
  
* 管理员首页

  <img src="Image\adminiorder.png" alt="adminiorder" style="zoom:80%;" />

  **需求分析**

  > * 订单展示

  **接口分析**

  

  > **/admini/nofinishOrder** 		——得到未收货订单
  >
  > 
  >
  > | 当前状态 | 属性名 | 测试值                                                       | 作用       |
  > | -------- | ------ | :----------------------------------------------------------- | ---------- |
  > | request  |        |                                                              | 测试窗口id |
  > | response | flag   | true                                                         | 测试结果   |
  > |          | object | <font size=2px>[{order_id=1, window_name='赵氏卤肉饭', create_time='2020-06-13 22:32:51', style='待收货', order_price='2.0'},{order_id=3, window_name='赵氏卤肉饭', create_time='2020-06-13 22:57:59', style='待收货', order_price='5.0'}...]</font> | 菜品信息   |
  >
  > **/admini/nofinishOrder** 		——得到未收货订单
  >
  > 
  >
  > | 当前状态 | 属性名 | 测试值                                                       | 作用       |
  > | -------- | ------ | :----------------------------------------------------------- | ---------- |
  > | request  |        |                                                              | 测试窗口id |
  > | response | flag   | true                                                         | 测试结果   |
  > |          | object | <font size=2px>[{order_id=2, window_name='赵氏卤肉饭', create_time='2020-06-13 22:32:51', style='待收货', order_price='2.0'},{order_id=4, window_name='赵氏卤肉饭', create_time='2020-06-13 22:57:59', style='待收货', order_price='5.0'}...]</font> | 菜品信息   |

* 订单详情

  <img src="Image\adorderfood.png" alt="adorderfood" style="zoom:80%;" />

**需求分析**

> * 订单详情展示

