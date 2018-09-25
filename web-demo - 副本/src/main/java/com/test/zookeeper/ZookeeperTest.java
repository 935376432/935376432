package com.test.zookeeper;
import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * 节点测试
 */
public class ZookeeperTest {

    // 会话超时时间，设置为与系统默认时间一致
    private static final int SESSION_TIMEOUT = 30 * 1000;

    // 创建 ZooKeeper 实例
    private ZooKeeper zk;

    // 创建 Watcher 实例
    private Watcher wh = new Watcher() {
        /**
         * Watched事件
         */
        @Override
        public void process(WatchedEvent event) {
            System.out.println("WatchedEvent >>> " + event.toString());
        }
    };

    // 初始化 ZooKeeper 实例
    private void createZKInstance() throws IOException {
        // 连接到ZK服务，多个可以用逗号分割写
        zk = new ZooKeeper("10.10.66.47:2181,10.10.66.47:2182,10.10.66.47:2183", ZookeeperTest.SESSION_TIMEOUT, this.wh);

    }

    private void ZKOperations() throws IOException, InterruptedException, KeeperException {
        System.out.println("\n1. 创建 ZooKeeper 节点 (znode ： zoo2, 数据： myData2 ，权限： OPEN_ACL_UNSAFE ，节点类型： Persistent");
        zk.create("/zoo2", "myData2".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println("\n2. 查看是否创建成功： ");
        System.out.println(new String(zk.getData("/zoo2", this.wh, null)));// 添加Watch

        // 前面一行我们添加了对/zoo2节点的监视，所以这里对/zoo2进行修改的时候，会触发Watch事件。
        System.out.println("\n3. 修改节点数据 ");
        zk.setData("/zoo2", "shanhy20160310".getBytes(), -1);

        // 这里再次进行修改，则不会触发Watch事件，这就是我们验证ZK的一个特性“一次性触发”，也就是说设置一次监视，只会对下次操作起一次作用。
        System.out.println("\n3-1. 再次修改节点数据 ");
        zk.setData("/zoo2", "shanhy20160310-ABCD".getBytes(), -1);

        System.out.println("\n4. 查看是否修改成功： ");
        System.out.println(new String(zk.getData("/zoo2", false, null)));

        System.out.println("\n5. 删除节点 ");
        zk.delete("/zoo2", -1);

        System.out.println("\n6. 查看节点是否被删除： ");
        System.out.println(" 节点状态： [" + zk.exists("/zoo2", false) + "]");
    }

    private void ZKClose() throws InterruptedException {
        zk.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZookeeperTest dm = new ZookeeperTest();
        dm.createZKInstance();
        dm.ZKOperations();
        dm.ZKClose();
    }














    /* 输出结果：
    SLF4J: Class path contains multiple SLF4J bindings.
    SLF4J: Found binding in [jar:file:/C:/Users/congzhe/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-classic/1.2.3/7c4f3c474fb2c041d8028740440937705ebb473a/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
    SLF4J: Found binding in [jar:file:/C:/Users/congzhe/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-log4j12/1.7.25/110cefe2df103412849d72ef7a67e4e91e4266b4/slf4j-log4j12-1.7.25.jar!/org/slf4j/impl/StaticLoggerBinder.class]
    SLF4J: Found binding in [jar:file:/E:/eclipse473/workspace/web-demo/libs/slf4j-log4j12-1.7.7.jar!/org/slf4j/impl/StaticLoggerBinder.class]
    SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
    SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
    17:38:58.384 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:zookeeper.version=3.4.12--1, built on 03/27/2018 04:49 GMT
    17:38:58.387 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:host.name=DESKTOP-A7PIVA9
    17:38:58.387 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:java.version=1.8.0_131
    17:38:58.387 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:java.vendor=Oracle Corporation
    17:38:58.387 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:java.home=E:\jdk\jre
    17:38:58.387 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:java.class.path=E:\jdk\jre\lib\resources.jar;E:\jdk\jre\lib\rt.jar;E:\jdk\jre\lib\jsse.jar;E:\jdk\jre\lib\jce.jar;E:\jdk\jre\lib\charsets.jar;E:\jdk\jre\lib\jfr.jar;E:\jdk\jre\lib\ext\access-bridge-64.jar;E:\jdk\jre\lib\ext\cldrdata.jar;E:\jdk\jre\lib\ext\dnsns.jar;E:\jdk\jre\lib\ext\jaccess.jar;E:\jdk\jre\lib\ext\jfxrt.jar;E:\jdk\jre\lib\ext\localedata.jar;E:\jdk\jre\lib\ext\nashorn.jar;E:\jdk\jre\lib\ext\sunec.jar;E:\jdk\jre\lib\ext\sunjce_provider.jar;E:\jdk\jre\lib\ext\sunmscapi.jar;E:\jdk\jre\lib\ext\sunpkcs11.jar;E:\jdk\jre\lib\ext\zipfs.jar;E:\eclipse473\workspace\web-demo\build\bin;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework\spring-context\4.3.16.RELEASE\b56bbfb12b8c388443589fc5d4f2f0f1774dc44d\spring-context-4.3.16.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework\spring-webmvc\4.3.16.RELEASE\2a171b1d701e450860bd5544a87a727048639a45\spring-webmvc-4.3.16.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework.security\spring-security-config\4.2.5.RELEASE\6c7c96ffa951d50eca516c4ca5a39d18a6d3c9fd\spring-security-config-4.2.5.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework.security\spring-security-web\4.2.5.RELEASE\66c07732d421568c8e4ab3f3693b6f87c13c4efd\spring-security-web-4.2.5.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework.security\spring-security-ldap\4.2.3.RELEASE\90256301d6147e1bc51bb89583f637c8cc39ae6c\spring-security-ldap-4.2.3.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.postgresql\postgresql\42.2.2\7ebd60d15eec1f9e796d68212121d92e3dd566b2\postgresql-42.2.2.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\com.sun.mail\javax.mail\1.6.1\757ba4630692bb2a1ec2d9d57a616b63f15e947e\javax.mail-1.6.1.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\javax.annotation\javax.annotation-api\1.3.2\934c04d3cfef185a8008e7bf34331b79730a9d43\javax.annotation-api-1.3.2.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework.data\spring-data-jpa\1.11.11.RELEASE\ac76e9ecc10850ac324f762ecb947d67d1670c82\spring-data-jpa-1.11.11.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.hibernate\hibernate-validator\5.4.2.Final\80d76bfdf5243c2e70ef16839708ca2d522ec21e\hibernate-validator-5.4.2.Final.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.hibernate\hibernate-entitymanager\4.3.11.Final\27a119fcc2b91c50e5285dd11158fac2c38c9d1b\hibernate-entitymanager-4.3.11.Final.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\commons-io\commons-io\2.5\2852e6e05fbb95076fc091f6d1780f1f8fe35e0f\commons-io-2.5.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\commons-net\commons-net\3.6\b71de00508dcb078d2b24b5fa7e538636de9b3da\commons-net-3.6.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.liquibase\liquibase-core\3.6.0\863e3b4d6777cb3c1127c8a68114f694fe86eeb8\liquibase-core-3.6.0.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.apache.commons\commons-dbcp2\2.2.0\88763be2c54a37dd0b2d5d735d268bd6a202a207\commons-dbcp2-2.2.0.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.apache.zookeeper\zookeeper\3.4.12\cc9c95b358202be355af8abddeb6105f089b1a8c\zookeeper-3.4.12.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\javax.servlet\javax.servlet-api\3.1.0\3cd63d075497751784b2fa84be59432f4905bf7c\javax.servlet-api-3.1.0.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework\spring-aop\4.3.16.RELEASE\2c74e87bbf99e7cb6903046e19345a04f843d817\spring-aop-4.3.16.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework\spring-beans\4.3.16.RELEASE\2a0cb663069001e0b210d509f7b1904888557e63\spring-beans-4.3.16.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework\spring-core\4.3.16.RELEASE\fe3f4186562c0dfa70a09510f886cc11a483ddde\spring-core-4.3.16.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework\spring-expression\4.3.16.RELEASE\f69b403faa1dd97df212217aece1ad3497dfdf2c\spring-expression-4.3.16.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework\spring-web\4.3.16.RELEASE\9321f7c56e8c4561c92aa62f48a0cefe0a667315\spring-web-4.3.16.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\aopalliance\aopalliance\1.0\235ba8b489512805ac13a8f9ea77a1ca5ebe3e8\aopalliance-1.0.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework.security\spring-security-core\4.2.5.RELEASE\37dde461931879627d8ca312dc2b698dc1e9d329\spring-security-core-4.2.5.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework.ldap\spring-ldap-core\2.2.0.RELEASE\45f2838f37854bbdb868323918ba6783dd3c30cf\spring-ldap-core-2.2.0.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\javax.activation\activation\1.1\e6cb541461c2834bdea3eb920f1884d1eb508b50\activation-1.1.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework.data\spring-data-commons\1.13.11.RELEASE\481434bd66c1cf6ff72902a89ad778156e924382\spring-data-commons-1.13.11.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework\spring-orm\4.3.15.RELEASE\3ea352c23e7d6e18f7795db2a3872c4fe97c42d\spring-orm-4.3.15.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.aspectj\aspectjrt\1.8.13\bfc5a877fd80648e5467239d370735654aaf7a92\aspectjrt-1.8.13.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.slf4j\slf4j-api\1.7.25\da76ca59f6a57ee3102f8f9bd9cee742973efa8a\slf4j-api-1.7.25.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.slf4j\jcl-over-slf4j\1.7.25\f8c32b13ff142a513eeb5b6330b1588dcb2c0461\jcl-over-slf4j-1.7.25.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\javax.validation\validation-api\1.1.0.Final\8613ae82954779d518631e05daa73a6a954817d5\validation-api-1.1.0.Final.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.jboss.logging\jboss-logging\3.3.0.Final\3616bb87707910296e2c195dc016287080bba5af\jboss-logging-3.3.0.Final.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\com.fasterxml\classmate\1.3.1\2ad2fd09dcf5607ca96f8ef432096a96986c40a\classmate-1.3.1.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.jboss.logging\jboss-logging-annotations\1.2.0.Beta1\2f437f37bb265d9f8f1392823dbca12d2bec06d6\jboss-logging-annotations-1.2.0.Beta1.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.hibernate\hibernate-core\4.3.11.Final\536ac0021240d97db99c7d2983067cef1a6f3af5\hibernate-core-4.3.11.Final.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\dom4j\dom4j\1.6.1\5d3ccc056b6f056dbf0dddfdf43894b9065a8f94\dom4j-1.6.1.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.hibernate.common\hibernate-commons-annotations\4.0.5.Final\2a581b9edb8168e45060d8bad8b7f46712d2c52c\hibernate-commons-annotations-4.0.5.Final.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.hibernate.javax.persistence\hibernate-jpa-2.1-api\1.0.0.Final\5e731d961297e5a07290bfaf3db1fbc8bbbf405a\hibernate-jpa-2.1-api-1.0.0.Final.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.jboss.spec.javax.transaction\jboss-transaction-api_1.2_spec\1.0.0.Final\1f9fef7a9fcbb41cc390fc370a291cf30729e094\jboss-transaction-api_1.2_spec-1.0.0.Final.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.javassist\javassist\3.18.1-GA\d9a09f7732226af26bf99f19e2cffe0ae219db5b\javassist-3.18.1-GA.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.yaml\snakeyaml\1.18\e4a441249ade301985cb8d009d4e4a72b85bf68e\snakeyaml-1.18.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\ch.qos.logback\logback-classic\1.2.3\7c4f3c474fb2c041d8028740440937705ebb473a\logback-classic-1.2.3.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.apache.commons\commons-pool2\2.5.0\cb7d05e6308ad795decc4a12ede671113c11dd98\commons-pool2-2.5.0.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\commons-logging\commons-logging\1.2\4bfc12adfe4842bf07b657f0369c4cb522955686\commons-logging-1.2.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.slf4j\slf4j-log4j12\1.7.25\110cefe2df103412849d72ef7a67e4e91e4266b4\slf4j-log4j12-1.7.25.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\log4j\log4j\1.2.17\5af35056b4d257e4b64b9e8069c0746e8b08629f\log4j-1.2.17.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\jline\jline\0.9.94\99a18e9a44834afdebc467294e1138364c207402\jline-0.9.94.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.apache.yetus\audience-annotations\0.5.0\55762d3191a8d6610ef46d11e8cb70c7667342a3\audience-annotations-0.5.0.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\io.netty\netty\3.10.6.Final\18ed04a0e502896552854926e908509db2987a00\netty-3.10.6.Final.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework\spring-jdbc\4.3.15.RELEASE\58c63357ee9721954764a38a88df92d24e79316d\spring-jdbc-4.3.15.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\antlr\antlr\2.7.7\83cd2cd674a217ade95a4bb83a8a14f351f48bd0\antlr-2.7.7.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.jboss\jandex\1.1.0.Final\e84a2122e76f0b6503be78094ddf2108057ac15f\jandex-1.1.0.Final.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\xml-apis\xml-apis\1.0.b2\3136ca936f64c9d68529f048c2618bd356bf85c9\xml-apis-1.0.b2.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\ch.qos.logback\logback-core\1.2.3\864344400c3d4d92dfeb0a305dc87d953677c03c\logback-core-1.2.3.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework\spring-tx\4.3.15.RELEASE\5d30e4dbc2ac49aa5b672e1bd63d034a5ea8e33e\spring-tx-4.3.15.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.slf4j\slf4j-api\1.8.0-beta0\f90ef27de5a997d30c169cb706835e32d724ca96\slf4j-api-1.8.0-beta0.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.apache.taglibs\taglibs-standard-spec\1.2.5\c3bb98c30f75fef1e229d1d03cf8457de22f1ba0\taglibs-standard-spec-1.2.5.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.apache.taglibs\taglibs-standard-impl\1.2.5\9b9783ccb2a323383e6e20e36d368f8997b71967\taglibs-standard-impl-1.2.5.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.webjars\webjars-locator\0.32-1\89f6c5d22d001aff4aaa4853bb9617207127ea62\webjars-locator-0.32-1.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.webjars\jquery\2.2.4\c3dc40b1b5f24c56afa36fd9a463bb9f378ac4ab\jquery-2.2.4.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.webjars\bootstrap\3.3.7-1\990657dc94f7921d46aae4e54d46ab76a4d1c3b0\bootstrap-3.3.7-1.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.webjars\dropzone\4.3.0-2\2c576794d3cc46565676f26f02cee55ac94c0f41\dropzone-4.3.0-2.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\com.h2database\h2\1.4.197\bb391050048ca8ae3e32451b5a3714ecd3596a46\h2-1.4.197.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.webjars.bower\bootstrap3-dialog\1.35.3\5eacead0e7ff160125df63d63ed53855f8421a04\bootstrap3-dialog-1.35.3.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.webjars\webjars-locator-core\0.32\4e45393c4f366a5bf3e63ec72b726fd57942ca47\webjars-locator-core-0.32.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\com.fasterxml.jackson.core\jackson-databind\2.3.3\63b77400b5f1cf83a81823562c48d3120ef5518e\jackson-databind-2.3.3.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.apache.commons\commons-lang3\3.1\905075e6c80f206bbe6cf1e809d2caa69f420c76\commons-lang3-3.1.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.apache.commons\commons-compress\1.9\cc18955ff1e36d5abd39a14bfe82b19154330a34\commons-compress-1.9.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\com.fasterxml.jackson.core\jackson-core\2.7.3\1499b854ae9f370409792db5af1b552dc7d9682f\jackson-core-2.7.3.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\com.fasterxml.jackson.core\jackson-annotations\2.3.0\f5e853a20b60758922453d56f9ae1e64af5cb3da\jackson-annotations-2.3.0.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.mockito\mockito-core\1.10.19\e8546f5bef4e061d8dd73895b4e8f40e3fe6effe\mockito-core-1.10.19.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\junit\junit\4.12\2973d150c0dc1fefe998f834810d68f278ea58ec\junit-4.12.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.springframework\spring-test\4.3.16.RELEASE\adb21bf985a998da78de96f6deefe85f058457eb\spring-test-4.3.16.RELEASE.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.objenesis\objenesis\2.1\87c0ea803b69252868d09308b4618f766f135a96\objenesis-2.1.jar;C:\Users\congzhe\.gradle\caches\modules-2\files-2.1\org.hamcrest\hamcrest-core\1.3\42a25dc3219429f0e5d060061f71acb49bf010a0\hamcrest-core-1.3.jar;E:\eclipse473\workspace\web-demo\libs\apache-log4j.jar;E:\eclipse473\workspace\web-demo\libs\datepicker.jar;E:\eclipse473\workspace\web-demo\libs\ET299jni.jar;E:\eclipse473\workspace\web-demo\libs\gnujaxp.jar;E:\eclipse473\workspace\web-demo\libs\jcommon-1.0.23.jar;E:\eclipse473\workspace\web-demo\libs\jdatepicker-1.3.4-javadoc.jar;E:\eclipse473\workspace\web-demo\libs\jdatepicker-1.3.4.jar;E:\eclipse473\workspace\web-demo\libs\jfreechart-1.0.19.jar;E:\eclipse473\workspace\web-demo\libs\kafka_2.10-0.9.0.0-sources.jar;E:\eclipse473\workspace\web-demo\libs\kafka_2.10-0.9.0.0.jar;E:\eclipse473\workspace\web-demo\libs\kafka-0.6.jar;E:\eclipse473\workspace\web-demo\libs\kafka-0.7.0.jar;E:\eclipse473\workspace\web-demo\libs\log4j-1.2-api-2.7.jar;E:\eclipse473\workspace\web-demo\libs\log4j-1.2.17.jar;E:\eclipse473\workspace\web-demo\libs\mysql-connector-java-5.0.4-bin.jar;E:\eclipse473\workspace\web-demo\libs\netty-all-4.1.22.Final-sources.jar;E:\eclipse473\workspace\web-demo\libs\netty-all-4.1.22.Final.jar;E:\eclipse473\workspace\web-demo\libs\org.apache.log4j_1.2.15.v201005080500.jar;E:\eclipse473\workspace\web-demo\libs\quartz-2.2.3-sources.jar;E:\eclipse473\workspace\web-demo\libs\quartz-2.2.3.jar;E:\eclipse473\workspace\web-demo\libs\quartz-jobs-2.2.3.jar;E:\eclipse473\workspace\web-demo\libs\slf4j-api-1.7.7.jar;E:\eclipse473\workspace\web-demo\libs\slf4j-log4j12-1.7.7.jar;E:\eclipse473\workspace\web-demo\libs\snmp4j-2.5.0-javadoc.jar;E:\eclipse473\workspace\web-demo\libs\snmp4j-2.5.0.jar;E:\eclipse473\workspace\web-demo\libs\swingx-core-1.6.2.jar;E:\eclipse473\workspace\web-demo\libs\syslog4j.jar
    17:38:58.389 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:java.library.path=E:\jdk\bin;C:\windows\Sun\Java\bin;C:\windows\system32;C:\windows;E:/jdk/jre/bin/server;E:/jdk/jre/bin;E:/jdk/jre/lib/amd64;C:\Program Files (x86)\Intel\iCLS Client\;C:\ProgramData\Oracle\Java\javapath;C:\Program Files\Intel\iCLS Client\;C:\windows\system32;C:\windows;C:\windows\System32\Wbem;C:\windows\System32\WindowsPowerShell\v1.0\;C:\Program Files\Intel\WiFi\bin\;E:\gradle-3.4.1\bin;C:\Program Files\Common Files\Intel\WirelessCommon\;C:\Program Files (x86)\Intel\Intel(R) Management Engine Components\DAL;C:\Program Files\Intel\Intel(R) Management Engine Components\DAL;C:\Program Files (x86)\Intel\Intel(R) Management Engine Components\IPT;C:\Program Files\Intel\Intel(R) Management Engine Components\IPT;E:\Python\Python36-32\Scripts\;E:\Python\Python36-32\;E:\jdk\bin;C:\Users\congzhe\AppData\Local\Microsoft\WindowsApps;;E:\eclipse473\eclipse;;.
    17:38:58.389 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:java.io.tmpdir=C:\Users\congzhe\AppData\Local\Temp\
    17:38:58.389 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:java.compiler=<NA>
    17:38:58.389 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:os.name=Windows 10
    17:38:58.389 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:os.arch=amd64
    17:38:58.389 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:os.version=10.0
    17:38:58.389 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:user.name=congzhe
    17:38:58.389 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:user.home=C:\Users\congzhe
    17:38:58.389 [main] INFO org.apache.zookeeper.ZooKeeper - Client environment:user.dir=E:\eclipse473\workspace\web-demo
    17:38:58.390 [main] INFO org.apache.zookeeper.ZooKeeper - Initiating client connection, connectString=10.10.66.47:2181,10.10.66.47:2182,10.10.66.47:2183 sessionTimeout=30000 watcher=com.test.zookeeper.ZookeeperTest$1@1e81f4dc
    17:38:58.393 [main] DEBUG org.apache.zookeeper.ClientCnxn - zookeeper.disableAutoWatchReset is false

    1. 创建 ZooKeeper 节点 (znode ： zoo2, 数据： myData2 ，权限： OPEN_ACL_UNSAFE ，节点类型： Persistent
    17:38:58.523 [main-SendThread(10.10.66.47:2181)] INFO org.apache.zookeeper.ClientCnxn - Opening socket connection to server 10.10.66.47/10.10.66.47:2181. Will not attempt to authenticate using SASL (unknown error)
    17:38:58.526 [main-SendThread(10.10.66.47:2181)] INFO org.apache.zookeeper.ClientCnxn - Socket connection established to 10.10.66.47/10.10.66.47:2181, initiating session
    17:38:58.526 [main-SendThread(10.10.66.47:2181)] DEBUG org.apache.zookeeper.ClientCnxn - Session establishment request sent on 10.10.66.47/10.10.66.47:2181
    17:38:58.545 [main-SendThread(10.10.66.47:2181)] INFO org.apache.zookeeper.ClientCnxn - Session establishment complete on server 10.10.66.47/10.10.66.47:2181, sessionid = 0x100356b8b230001, negotiated timeout = 30000
    WatchedEvent >>> WatchedEvent state:SyncConnected type:None path:null
    17:38:58.563 [main-SendThread(10.10.66.47:2181)] DEBUG org.apache.zookeeper.ClientCnxn - Reading reply sessionid:0x100356b8b230001, packet:: clientPath:null serverPath:null finished:false header:: 1,1  replyHeader:: 1,4294967299,0  request:: '/zoo2,#6d794461746132,v{s{31,s{'world,'anyone}}},0  response:: '/zoo2

    2. 查看是否创建成功：
    17:38:58.570 [main-SendThread(10.10.66.47:2181)] DEBUG org.apache.zookeeper.ClientCnxn - Reading reply sessionid:0x100356b8b230001, packet:: clientPath:null serverPath:null finished:false header:: 2,4  replyHeader:: 2,4294967299,0  request:: '/zoo2,T  response:: #6d794461746132,s{4294967299,4294967299,1526463547579,1526463547579,0,0,0,0,7,0,4294967299}
    myData2

    3. 修改节点数据
    17:38:58.576 [main-SendThread(10.10.66.47:2181)] DEBUG org.apache.zookeeper.ClientCnxn - Got notification sessionid:0x100356b8b230001
    17:38:58.577 [main-SendThread(10.10.66.47:2181)] DEBUG org.apache.zookeeper.ClientCnxn - Got WatchedEvent state:SyncConnected type:NodeDataChanged path:/zoo2 for sessionid 0x100356b8b230001
    WatchedEvent >>> WatchedEvent state:SyncConnected type:NodeDataChanged path:/zoo2
    17:38:58.578 [main-SendThread(10.10.66.47:2181)] DEBUG org.apache.zookeeper.ClientCnxn - Reading reply sessionid:0x100356b8b230001, packet:: clientPath:null serverPath:null finished:false header:: 3,5  replyHeader:: 3,4294967300,0  request:: '/zoo2,#7368616e68793230313630333130,-1  response:: s{4294967299,4294967300,1526463547579,1526463547602,1,0,0,0,14,0,4294967299}

    3-1. 再次修改节点数据
    17:38:58.583 [main-SendThread(10.10.66.47:2181)] DEBUG org.apache.zookeeper.ClientCnxn - Reading reply sessionid:0x100356b8b230001, packet:: clientPath:null serverPath:null finished:false header:: 4,5  replyHeader:: 4,4294967301,0  request:: '/zoo2,#7368616e687932303136303331302d41424344,-1  response:: s{4294967299,4294967301,1526463547579,1526463547608,2,0,0,0,19,0,4294967299}

    4. 查看是否修改成功：
    17:38:58.585 [main-SendThread(10.10.66.47:2181)] DEBUG org.apache.zookeeper.ClientCnxn - Reading reply sessionid:0x100356b8b230001, packet:: clientPath:null serverPath:null finished:false header:: 5,4  replyHeader:: 5,4294967301,0  request:: '/zoo2,F  response:: #7368616e687932303136303331302d41424344,s{4294967299,4294967301,1526463547579,1526463547608,2,0,0,0,19,0,4294967299}
    shanhy20160310-ABCD

    5. 删除节点
    17:38:58.590 [main-SendThread(10.10.66.47:2181)] DEBUG org.apache.zookeeper.ClientCnxn - Reading reply sessionid:0x100356b8b230001, packet:: clientPath:null serverPath:null finished:false header:: 6,2  replyHeader:: 6,4294967302,0  request:: '/zoo2,-1  response:: null

    6. 查看节点是否被删除：
    17:38:58.592 [main-SendThread(10.10.66.47:2181)] DEBUG org.apache.zookeeper.ClientCnxn - Reading reply sessionid:0x100356b8b230001, packet:: clientPath:null serverPath:null finished:false header:: 7,3  replyHeader:: 7,4294967302,-101  request:: '/zoo2,F  response::
     节点状态： [null]
    17:38:58.593 [main] DEBUG org.apache.zookeeper.ZooKeeper - Closing session: 0x100356b8b230001
    17:38:58.594 [main] DEBUG org.apache.zookeeper.ClientCnxn - Closing client for session: 0x100356b8b230001
    17:38:58.598 [main-SendThread(10.10.66.47:2181)] DEBUG org.apache.zookeeper.ClientCnxn - Reading reply sessionid:0x100356b8b230001, packet:: clientPath:null serverPath:null finished:false header:: 8,-11  replyHeader:: 8,4294967303,0  request:: null response:: null
    17:38:58.598 [main] DEBUG org.apache.zookeeper.ClientCnxn - Disconnecting client for session: 0x100356b8b230001
    17:38:58.598 [main] INFO org.apache.zookeeper.ZooKeeper - Session: 0x100356b8b230001 closed*/








}