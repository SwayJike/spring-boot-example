# 数据库文档

**数据库名：** test

**文档版本：** 1.0.0

**文档描述：** 数据库文档生成

| 表名                  | 说明       |
| :---: | :---: |
| [sys_captcha](#sys_captcha) | 系统验证码 |
| [t_order](#t_order) | 订单表 |
| [user](#user) | 用户表 |

**表名：** <a id="sys_captcha">sys_captcha</a>

**说明：** 系统验证码

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | uuid |   char   | 36 |   0    |    N     |  Y   |       | uuid  |
|  2   | captcha |   varchar   | 6 |   0    |    N     |  N   |       | 验证码  |
|  3   | expire_time |   datetime   | 19 |   0    |    Y     |  N   |       | 过期时间  |

**表名：** <a id="t_order">t_order</a>

**说明：** 订单表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   bigint   | 20 |   0    |    N     |  Y   |       | 主键  |
|  2   | order_id |   varchar   | 32 |   0    |    N     |  N   |       | 订单ID  |
|  3   | amount |   decimal   | 10 |   2    |    N     |  N   |       | 订单金额  |
|  4   | payment_time |   datetime   | 19 |   0    |    N     |  N   |       | 支付时间  |
|  5   | order_status |   tinyint   | 4 |   0    |    N     |  N   |   0    | 订单状态,0:处理中,1:支付成功,2:支付失败  |
|  6   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  7   | update_time |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |

**表名：** <a id="user">user</a>

**说明：** 用户表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | user_id |   bigint   | 20 |   0    |    N     |  Y   |       | 用户id  |
|  2   | username |   varchar   | 64 |   0    |    Y     |  N   |       | 用户名  |
|  3   | password |   varchar   | 64 |   0    |    Y     |  N   |       | 密码  |
|  4   | mobile_phone_number |   char   | 11 |   0    |    Y     |  N   |       | 手机号码  |
|  5   | email |   varchar   | 64 |   0    |    Y     |  N   |       | 邮箱  |
|  6   | delete_state |   tinyint   | 3 |   0    |    Y     |  N   |   0    | 用户状态，0表示未删除，1表示删除  |
|  7   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  8   | update_time |   datetime   | 19 |   0    |    Y     |  N   |       | 更新时间  |
