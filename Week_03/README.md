1. 服务器接收消息分为两个阶段：

a. 等待数据到达；

b. 将数据从内核空间copy到用户空间

![图片](https://uploader.shimo.im/f/g6HQOuQimwM2R7PE.jpg!thumbnail?fileGuid=TTj3pKpXpchVr6RV)

服务器通信过程中，存在两种类型操作：

-CPU 计算/业务处理

-IO 操作与等待/网络、磁盘、数据 库

![图片](https://uploader.shimo.im/f/1iErcJRgOzy0ujY9.png!thumbnail?fileGuid=TTj3pKpXpchVr6RV)

CPU大部分时间是空闲状态

同步、异步；阻塞、非阻塞：

同步，异步是通信模式；主要从消息通知的角度来说的，描述的是被调用者。

阻塞，非阻塞是线程处理模式；主要从等待消息通知时的状态角度来说的，描述的是调用者。

**异步一定不会阻塞线程**。

五种线程IO模型

refer 第四课PPT。

Netty，需要在总结一下，，后续update

