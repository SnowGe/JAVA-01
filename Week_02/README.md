1. **串行GC(Serial GC)**

-XX:UseSerialGC       年轻代使用mark-copy(标记-复制)算法；

老年代使用mark-sweep-compact(标记-清除-整理)算法。

单线程。两者都会STW(Stop The World),停止所有线程

CPU利用率高，暂停时间长，适合单核CPU,几百MB堆内存的JVM。

1. **并行GC(Parallel GC)**-------吞吐量是最好的。

-XX:UseParallelGC    年轻代标记-复制算法； 老年代标记-清除-整理算法。

年轻代和老年代都会STW。

适用于多核服务器，主要目标增加吞吐量，**在GC期间，所有CPU都在并行清理垃圾。**

-XX:ParallelGCTHreads = N，来指定GC线程数。 default为CPU核心数。

非GC期间，没有GC线程在运行。

1. **并发GC(CMS GC)**--------一般处理老年代，低延迟。

-XX:UseConcMarkSweepGC    年轻代采用并行STW方式的mark-copy(标记-复制)算法-       ParaNew,老年代使用并发mark-sweep(标记-清除)算法。

CMS GC设计的目标是避免在老年代垃圾收集时出现长时间的卡顿。不对老年代整理，而是使用空闲列表(free-list)来管理内存空间的回收。在**标记-清除阶段大部分工作和应用线程一起并发执行**。

默认CMS使用的并发线程数等于CPU核心数的1/4。

避免长时间停顿，吞吐量比并行GC低。

缺点：其中最大的问题就是老年代内存碎片问题（因为 不压缩），在某些情况下GC会造成不可预测的暂停时间，特别是堆内存较大的情况下。

1. **G1 GC(Garbage-First)-**-----垃圾优先，哪一块垃圾最多，就优先清理它。

设计目标：将STW停顿时间和分布变成预期并且可配置的；不在划分年轻代和老年代，而是划分多个(通常是2048个)可以存放对象的小块堆区域。

-XX:UseG1GC   -XX:MaxGCPauseMillis=50->GC最大停顿时间50ms.

1. **ZGC**

特性： GC最大停顿时间不超过10ms.

堆内存支持范围广，小至几百MB的堆空间，大至4TB的超大堆内存（JDK13升至16TB）.

与G1相比，应用吞吐量下降不超过15%.

当前只支持Linux/X64位平台，预期JDK14后支持MacOS和Windows系统。

1. **Shenandoah GC.**

More details, refer the pdf file.


CMS GC六个阶段：

阶段 1: Initial Mark（初始标记）STW(Stop the world)

阶段 2: Concurrent Mark（并发标记）

阶段 3: Concurrent Preclean（并发预清理）-JVM通过card方式将发生改变的区域标记位“脏”区。这些脏对象会被统计出来，他们所引用的对象也会被标记。

阶段 4: Final Remark（最终标记）STW(Stop the world)

阶段 5: Concurrent Sweep（并发清除）

阶段 6: Concurrent Reset（并发重置）

G1 GC六个阶段：

阶段 1: Initial Mark（初始标记）G1里面通常是在转移暂停的同时处理这些事。开销比CMS第一阶段小。

阶段 2: Root Region Scan(Root区扫描)

阶段 3: Concurrent Mark（并发标记）

阶段 4: Remark(再次标记)，和CMS类似，这是STW。

阶段 5: Cleanup(清理)

阶段 6: 转移暂停：混合模式(Evacuation Pause (mixed))

![GC 对比].https://github.com/SnowGe/JAVA-01/blob/main/Week_01/JVM%E5%86%85%E5%AD%98%E7%BB%93%E6%9E%84-%E6%A0%88.png

常用GC组合

1. Serial+Serial Old 实现单线程的低延迟垃圾回收机制
2. ParNew+CMS 实现多线程的低延迟垃圾回收机制
3. Parallel Scavenge和Parallel Old，实现多线程的高吞吐量垃圾回收机制

选择正确的 GC 算法，唯一可行的方式就是去尝试，一般性的指导原则：

1. 如果系统考虑吞吐优先，CPU 资源都用来最大程度处理业务，用 Parallel GC；
1. 如果系统考虑低延迟有限，每次 GC 时间尽量短，用 CMS GC；
1. 如果系统内存堆较大，同时希望整体来看平均 GC 时间可控，使用 G1 GC。

对于内存大小的考量：

1. 一般 4G 以上，算是比较大，用 G1 的性价比较高。
1. 一般超过 8G，比如 16G-64G 内存，非常推荐使用 G1 GC。

JDK8 默认GC是并行GC

JDK9-15默认是G1GC




