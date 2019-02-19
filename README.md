# PayCode
一个基于支付宝、微信、QQ 付款码的收款码聚合项目

# 采用技术栈

- springboot
- springdata jpa
- h2
- FastJson
- Zxing
- Gradle

# 流程

![](https://ws1.sinaimg.cn/large/0072Lfvtly1g0amo3rwauj31l213wjug.jpg)



截止目前所有功能均以实现，因为登录操作太麻烦，已经有人不想登录注册，所有目前去除用户模块，可以直接进入网页，上传二维码合成二维码，以及进行相应的操作



# 待实现

目前由于前端部分能力有限，保存二维码操作没有完成，目前只能手动截图保存收款码。



# 使用说明

1. 安装 Java 环境（网上关于 Windows/Linux 教程较多，请自行百度）

2. 在控制台输入以下指令

   ```shell
   nohup java -jar payment-code-0.1.1-SNAPSHOT.jar &
   ```

3. 在浏览器上输入 **http://IP_SERVERR:10001 /qr**,就可以正常使用了

# 效果演示

![](https://ws1.sinaimg.cn/large/0072Lfvtly1g0bk0lqwrtj31xs14u44v.jpg)

![](https://ws1.sinaimg.cn/large/0072Lfvtly1g0bk0wiqolj31ye14qdrb.jpg)

![](https://ws1.sinaimg.cn/large/0072Lfvtly1g0bk1xchiej32ag1be7f6.jpg)

**最终效果**

![](https://ws1.sinaimg.cn/large/0072Lfvtly1g0bk1dhy1pj322m172gud.jpg)



## [演示网站](https://qr.gclmit.club/qr)

## 如果有对这个项目感兴趣的朋友，欢迎一起加入到该项目中来。

