#JVM

  1.java 字节码，Java class加载，JVM 内存结构，JVM内存模型

  2.GC各种算法，各种GC以及各种GC的组合，优缺点，吞吐量/低延迟？？

3.JVM调优（根据自身情况目前不涉及），GC日志解读。


#java命令工具：
  
  jps/jinfo 查看 java 进程
  
  jstat 查看 JVM 内部 gc 相关信息
  
  jmap 查看 heap 或类占用空间统计
  
  jstack 查看线程信息
  
  jcmd 执行 JVM 相关分析命令（整合命令） 
  
  jrunscript/jjs 执行 js 命令
  
#常用GC组合

  1. Serial+Serial Old 实现单线程的低延迟垃圾回收机制

  2. ParNew+CMS 实现多线程的低延迟垃圾回收机制

  3. Parallel Scavenge和Parallel Old，实现多线程的高吞吐量垃圾回收机制
  
  
  #CMS GC六个阶段：
  
  阶段 1: Initial Mark（初始标记）STW(Stop the world)
  
  阶段 2: Concurrent Mark（并发标记）
  
  阶段 3: Concurrent Preclean（并发预清理） 
  
  阶段 4: Final Remark（最终标记）STW(Stop the world)
  
  阶段 5: Concurrent Sweep（并发清除） 
  
  阶段 6: Concurrent Reset（并发重置）
  
  
  #JVM调优浅谈：
  
  1. GC打日志：java -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
  
  2. 堆内存小了不好，大了也不好？why->堆太小，容易造成OOM。堆太大，GC的时间过长，吞吐量不好，系统暂停时间过长 。一般情况下，建议不影响系统性能的情况下，堆内存尽可能的小。(节省资源，GC的效率最大化)。
  
  3. GC调优中.GC导致的应用暂停时间影响系统响应速度，GC处理线程的CPU使用率影响系统吞吐量。因此，并行GC的吞吐量是最好的。
  
  
  Note:
  
    Non-Heap本质上也是堆，只是不归GC管理.
    
    堆也叫GC堆，存放对象实例以及数组。
    
    metaspace以前叫持久代（方法区，永久代，permanent generation），存储JVM加载的类信息,常量，静态变量，是各个线程共享的内存区域。
    
    常量池是方法去的一部分，主要内容来自JVM对class的加载。
