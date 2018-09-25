output = '''PID  PPID USER     %MEM %CPU  STARTED     TIME STAT CMD
    1     0 root      1.3  0.0   Jun 01 00:09:49 Ss   /usr/lib/systemd/systemd --system --deserialize 24
  482     1 root      0.5  0.0   Jun 01 00:19:26 Ss   /usr/lib/systemd/systemd-journald
  503     1 root      0.0  0.0   Jun 01 00:00:00 Ss   /usr/sbin/lvmetad -f
  651     1 root      0.0  0.0   Jun 01 00:00:03 S<sl /sbin/auditd
  669     1 dbus      0.0  0.0   Jun 01 00:00:38 Ss   /usr/bin/dbus-daemon --system --address=systemd: --nofork --nopidfile --systemd-activation
  671     1 root      0.0  0.0   Jun 01 00:00:13 Ss   /usr/lib/systemd/systemd-logind
  672     1 polkitd   0.0  0.0   Jun 01 00:00:07 Ssl  /usr/lib/polkit-1/polkitd --no-debug
  675     1 root      0.0  0.0   Jun 01 00:00:00 Ss   /usr/sbin/smartd -n -q never
  694     1 chrony    0.0  0.0   Jun 01 00:00:01 S    /usr/sbin/chronyd
  695     1 root      0.0  0.0   Jun 01 00:01:15 S    /bin/bash /usr/sbin/ksmtuned
  697     1 root      0.0  0.0   Jun 01 00:00:02 Ssl  /usr/sbin/gssproxy -D
  712     1 root      0.0  0.0   Jun 01 00:01:35 Ssl  /usr/sbin/NetworkManager --no-daemon
 1041     1 root      0.0  0.0   Jun 01 00:04:42 Ssl  /usr/bin/python -Es /usr/sbin/tuned -l -P
 1047     1 redis     0.0  0.1   Jun 01 00:45:11 Rsl  /usr/bin/redis-server 0.0.0.0:6379
 1060     1 root      0.4  0.0   Jun 01 00:09:06 Ssl  /usr/sbin/rsyslogd -n
 1065     1 root      0.0  0.0   Jun 01 00:00:00 Ssl  /usr/sbin/libvirtd
 1167     1 root      0.0  0.0   Jun 01 00:00:00 Ss   /usr/sbin/atd -f
 1180     1 root      0.0  0.0   Jun 01 00:00:05 Ss   /usr/sbin/crond -n
 1276     1 root      0.0  0.0   Jun 26 00:00:00 Ss   sshd: root@pts/0
 1286  1276 root      0.0  0.0   Jun 26 00:00:00 Ss+  -bash
 1508     1 rabbitmq  0.0  0.0   Jun 01 00:00:19 S    /usr/lib64/erlang/erts-5.10.4/bin/epmd -daemon
 1592     1 root      0.0  0.0   Jun 01 00:00:00 S    bin/rscw
 1623  1592 root      0.0  0.0   Jun 01 00:00:00 S    bin/rscd
 1624  1592 root      0.0  0.0   Jun 01 00:00:00 S    bin/rscd
 1656     1 root      0.0  0.0   Jun 01 00:00:07 Ss   /usr/libexec/postfix/master -w
 1658  1656 postfix   0.0  0.0   Jun 01 00:00:02 S    qmgr -l -t unix -u
 1783     1 nobody    0.0  0.0   Jun 01 00:00:12 S    /usr/sbin/dnsmasq --conf-file=/var/lib/libvirt/dnsmasq/default.conf --leasefile-ro --dhcp-script=/usr/libexec/libvirt_leaseshelper
 2102  1042 rabbitmq  0.0  0.0   Jun 01 00:00:02 Ss   inet_gethost 4
 2103  2102 rabbitmq  0.0  0.0   Jun 01 00:00:05 S    inet_gethost 4
 2121     1 root      1.6  0.0   Jun 01 00:37:21 Sl   /usr/bin/java -server -Xmx256m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/var/log/shterm -classpath lib/amqp-client.jar:lib/aopalliance.jar:lib/bcpkix-jdk15on.jar:lib/bcprov-jdk15on.jar:lib/commons-io.jar:lib/commons-lang3.jar:lib/commons-logging.jar:lib/commons-pool2.jar:lib/curator-client.jar:lib/curator-framework.jar:lib/curator-recipes.jar:lib/guava.jar:lib/jackson-annotations.jar:lib/jackson-core.jar:lib/jackson-databind.jar:lib/jedis.jar:lib/log4j-api.jar:lib/log4j-core.jar:lib/log4j-jcl.jar:lib/nmap4j.jar:lib/shterm-common.jar:lib/slf4j-api.jar:lib/spring-amqp.jar:lib/spring-aop.jar:lib/spring-beans.jar:lib/spring-context.jar:lib/spring-core.jar:lib/spring-data-commons.jar:lib/spring-data-keyvalue.jar:lib/spring-data-redis.jar:lib/spring-expression.jar:lib/spring-messaging.jar:lib/spring-rabbit.jar:lib/spring-retry.jar:lib/spring-tx.jar:lib/zookeeper.jar -Djava.io.tmpdir=/tmp -Dlog4j.configurationFile=/usr/share/shterm/conf/shterm-common-log4j2.xml com.shterm.common.CommonMain start
 2275     1 root      0.0  0.0   Jun 01 00:00:00 Ss+  /sbin/agetty --noclear tty1 linux
 3128  4183 root      0.0  0.0 19:46:32 00:00:00 Ss   sshd: root@pts/3
 3139  3128 root      0.0  0.0 19:46:38 00:00:00 Ss+  -bash
 4183     1 root      0.0  0.0   Jun 26 00:00:00 Ss   /usr/sbin/sshd -D
 5605 32559 root      0.0  0.0   Jun 28 00:00:00 T    systemctl list-units
 5606  5605 root      0.0  0.0   Jun 28 00:00:00 T    less
 5673 32559 root      0.0  0.0   Jun 28 00:00:00 T    systemctl list-unit-files
 5674  5673 root      0.0  0.0   Jun 28 00:00:00 T    less
 5727     1 root      0.0  0.0   Jun 20 00:00:01 Ss   sshd: root@pts/1
 5779  5727 root      0.0  0.0   Jun 20 00:00:00 Ss+  -bash
 7414 26638 root      0.0  0.0   Jun 27 00:00:00 S+   vim log4j2.xml
 8366  4183 root      0.0  0.0   Jun 28 00:00:00 Ss   sshd: root@pts/14,pts/23
 8382  8366 root      0.0  0.0   Jun 28 00:00:00 Ss   -bash
 8435     1 root      0.0  0.0 01:31:38 00:00:00 Ss   nginx: master process /usr/sbin/nginx
 8437  8435 nginx     0.4  0.0 01:31:38 00:00:00 S    nginx: worker process
 8438  8435 nginx     0.0  0.0 01:31:38 00:00:00 S    nginx: cache manager process
 8801     1 shterm   17.2  1.3 01:33:27 00:06:57 Ssl  /usr/bin/java -server -Xmx1536m -XX:MaxPermSize=256m -Djava.security.egd=file:/dev/./urandom -Djava.awt.headless=true -DCATALINA_PID=/var/run/tomcat.pid -Dcom.sun.management.jmxremote -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/var/log/tomcat -classpath /usr/share/tomcat/bin/bootstrap.jar:/usr/share/tomcat/bin/tomcat-juli.jar: -Dcatalina.base=/usr/share/tomcat -Dcatalina.home=/usr/share/tomcat -Djava.endorsed.dirs= -Djava.io.tmpdir=/var/cache/tomcat/temp -Dlog4j.configurationFile=/etc/tomcat/log4j2.xml -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager org.apache.catalina.startup.Bootstrap start
 8864     1 root      0.0  0.0   Jun 15 00:00:02 Ss   sshd: root@pts/8
 8867  8864 root      0.0  0.0   Jun 15 00:00:00 Ss+  -bash
 8923 17652 postgres  0.2  0.0 01:33:48 00:00:00 Ss   postgres: shterm crm 127.0.0.1(56804) idle
 8957 17652 postgres  0.1  0.0 01:34:12 00:00:00 Ss   postgres: shterm crm 127.0.0.1(56806) idle
 8958 17652 postgres  0.2  0.0 01:34:12 00:00:01 Ss   postgres: shterm crm 127.0.0.1(56808) idle
 9085 17652 postgres  0.1  0.0 01:35:14 00:00:00 Ss   postgres: shterm shterm 127.0.0.1(56824) idle
 9122 17652 postgres  0.1  0.0 01:35:27 00:00:02 Ss   postgres: shterm shterm 127.0.0.1(56832) idle
 9123 17652 postgres  0.1  0.0 01:35:27 00:00:02 Ss   postgres: shterm shterm 127.0.0.1(56834) idle
 9263  4183 root      0.0  0.0   Jun 27 00:00:00 Ss   sshd: root@notty
 9265  9263 root      0.0  0.0   Jun 27 00:00:00 Ss   /usr/libexec/openssh/sftp-server
 9316  9263 root      0.0  0.0   Jun 27 00:00:00 Ss   /usr/libexec/openssh/sftp-server
 9807  4183 root      0.0  0.0   Jun 28 00:00:00 Ss   sshd: root@pts/15
 9810  9807 root      0.0  0.0   Jun 28 00:00:00 Ss+  -bash
 9941     1 shterm    2.4  0.2 01:40:18 00:01:09 Sl   /usr/bin/java -server -Xmx1536m -Dcom.sun.management.jmxremote -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/var/log/shterm -classpath lib/aa.pcap:lib/amqp-client.jar:lib/aopalliance.jar:lib/a.pcap:lib/bcpkix-jdk15on.jar:lib/bcprov-jdk15on.jar:lib/commons-compress.jar:lib/commons-io.jar:lib/commons-lang3.jar:lib/commons-lang.jar:lib/commons-logging.jar:lib/commons-net.jar:lib/compress-lzf.jar:lib/elasticsearch.jar:lib/guava.jar:lib/hoami:lib/hppc.jar:lib/jackson-annotations.jar:lib/jackson-core.jar:lib/jackson-databind.jar:lib/jackson-dataformat-smile.jar:lib/jline.jar:lib/joda-time.jar:lib/jpinyin.jar:lib/jsr166e.jar:lib/log4j-api.jar:lib/log4j-core.jar:lib/log4j-jcl.jar:lib/log4j-slf4j-impl.jar:lib/lucene-analyzers-common.jar:lib/lucene-core.jar:lib/lucene-queryparser.jar:lib/lucene-suggest.jar:lib/netty.jar:lib/proguard.map:lib/shterm-text.jar:lib/shterm-text.jar.bk:lib/slf4j-api.jar:lib/spring-amqp.jar:lib/spring-aop.jar:lib/spring-beans.jar:lib/spring-context.jar:lib/spring-core.jar:lib/spring-data-commons.jar:lib/spring-data-elasticsearch.jar:lib/spring-expression.jar:lib/spring-messaging.jar:lib/spring-rabbit.jar:lib/spring-retry.jar:lib/spring-tx.jar:lib/sshd-core.jar:lib/sshd-core.jar.bk:lib/t-digest.jar -Dsshd.port=22 -Dsshd.hostKeyFile=/etc/shterm/sshdkey -Djava.io.tmpdir=/tmp -Dlog4j.configurationFile=/usr/share/shterm-text/conf/shterm-text-log4j2.xml com.shterm.text.TextMain start
10733 32559 root      0.0  0.0   Jun 25 00:00:00 T    vim test.py
11587 27748 root      0.0  0.0   Jun 28 00:00:00 Ss+  -bash
11985 27748 root      0.0  0.0   Jun 28 00:00:00 Ss+  -bash
12308 17652 postgres  0.1  0.0 01:59:59 00:00:01 Ss   postgres: shterm crm 127.0.0.1(57662) idle
12309 17652 postgres  0.1  0.0 01:59:59 00:00:00 Ss   postgres: shterm crm 127.0.0.1(57664) idle
12795     1 root      0.0  0.0 02:03:29 00:00:00 Ss   /usr/sbin/xinetd -stayalive -pidfile /var/run/xinetd.pid
12797     1 shterm    0.3  0.0 02:03:29 00:00:00 Ss   /usr/bin/python27 /usr/libexec/shterm/rdpextsrv
12798     1 shterm    0.2  0.0 02:03:29 00:00:08 Ssl  /usr/bin/python27 /usr/libexec/shterm/gui-sessmgr
12828     1 shterm    0.2  0.0 02:03:30 00:00:05 Ss   /usr/bin/python27 /usr/libexec/shterm/shterm-dbaudit-mssql
12829     1 shterm    0.2  0.0 02:03:30 00:00:05 Ss   /usr/bin/python27 /usr/libexec/shterm/shterm-dbaudit-mysql
12830     1 shterm    0.2  0.0 02:03:30 00:00:05 Ss   /usr/bin/python27 /usr/libexec/shterm/shterm-dbaudit-oracle
13060     1 shterm    0.2  0.0 02:04:51 00:00:09 Ssl  /usr/bin/python27 /opt/pyenv/bin/shterm2-arm
13061 13060 shterm    0.1  0.0 02:04:51 00:00:02 S    /usr/bin/python27 /opt/pyenv/bin/shterm2-arm
14988  8382 root      0.0  0.0   Jun 28 00:00:00 S+   screen
14994 14988 root      0.0  0.0   Jun 28 00:00:00 Ss   SCREEN
14996 14994 root      0.0  0.0   Jun 28 00:00:00 Ss+  /bin/bash
16520  4183 root      0.0  0.0   Jun 26 00:00:00 Ss   sshd: root@pts/5
16523 16520 root      0.0  0.0   Jun 26 00:00:00 Ss+  -bash
17652     1 postgres  0.0  0.0   Jun 06 00:02:31 S    /usr/pgsql-9.6/bin/postgres -D /var/lib/pgsql/9.6/data/
17653 17652 postgres  0.0  0.0   Jun 06 00:00:00 Ss   postgres: logger process
17655 17652 postgres  0.3  0.0   Jun 06 00:00:17 Ss   postgres: checkpointer process
17656 17652 postgres  0.0  0.0   Jun 06 00:00:24 Ss   postgres: writer process
17657 17652 postgres  0.0  0.0   Jun 06 00:00:54 Ss   postgres: wal writer process
17658 17652 postgres  0.0  0.0   Jun 06 00:01:32 Ss   postgres: autovacuum launcher process
17659 17652 postgres  0.0  0.0   Jun 06 00:03:27 Ss   postgres: stats collector process
21162  1656 postfix   0.0  0.0 09:40:18 00:00:00 S    pickup -l -t unix -u
21341 17652 postgres  0.1  0.0 09:42:18 00:00:00 Ss   postgres: shterm shterm 127.0.0.1(59074) idle
21672     1 root      0.0  0.0   Jun 14 00:00:02 Ss   sshd: root@pts/4
21676 21672 root      0.0  0.0   Jun 14 00:00:00 Ss+  -bash
21697     1 arangodb  5.7  0.3   Jun 12 01:33:10 Ssl  /usr/sbin/arangod --uid arangodb --gid arangodb --pid-file /var/run/arangodb3/arangod.pid --temp.path /var/tmp/arangodb3 --log.foreground-tty true
21704  8366 root      0.0  0.0 17:57:23 00:00:00 Ss+  -bash
22415 17652 postgres  0.0  0.0 09:54:01 00:00:00 Ss   postgres: shterm shterm 127.0.0.1(59326) idle
22669  4183 root      0.0  0.0   Jun 28 00:00:00 Ss   sshd: root@pts/18
22674 22669 root      0.0  0.0   Jun 28 00:00:00 Ss   -bash
22720 22674 root      0.0  0.0   Jun 28 00:00:00 S+   tail -f /var/log/tomcat/tomcat.log
23795   695 root      0.0  0.0 10:09:27 00:00:00 S    sleep 60
23815 29677 root      0.0  0.0 10:09:45 00:00:00 R+   ps axo pid,ppid,user,%mem,%cpu,start,time,stat,cmd
26638 27748 root      0.0  0.0   Jun 27 00:00:00 Ss   -bash
27146     1 root      0.0  0.0   Jun 12 00:00:00 Ss   /usr/lib/systemd/systemd-udevd
27407     1 elastic+  5.5  0.5   Jun 12 02:08:45 Ssl  /bin/java -Xms256m -Xmx1g -Djava.awt.headless=true -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:+HeapDumpOnOutOfMemoryError -XX:+DisableExplicitGC -Dfile.encoding=UTF-8 -Djna.nosys=true -Des.path.home=/usr/share/elasticsearch -cp /usr/share/elasticsearch/lib/elasticsearch-2.4.6.jar:/usr/share/elasticsearch/lib/* org.elasticsearch.bootstrap.Elasticsearch start -Des.pidfile=/var/run/elasticsearch/elasticsearch.pid -Des.default.path.home=/usr/share/elasticsearch -Des.default.path.logs=/var/log/elasticsearch -Des.default.path.data=/var/lib/elasticsearch -Des.default.path.conf=/etc/elasticsearch
27748     1 root      0.0  0.0   Jun 26 00:00:00 Ss   sshd: root@pts/6,pts/11,pts/10,pts/13
27760 27748 root      0.0  0.0   Jun 26 00:00:00 Ss   -bash
27811 27760 root      0.0  0.0   Jun 26 00:00:00 S    su - postgres
27812 27811 postgres  0.0  0.0   Jun 26 00:00:00 S    -bash
27857 27812 postgres  0.0  0.0   Jun 26 00:00:00 S+   psql shterm
29190  4183 root      0.0  0.0   Jun 27 00:00:02 Ss   sshd: root@pts/12
29204 29190 root      0.0  0.0   Jun 27 00:00:00 Ss+  -bash
29665  4183 root      0.0  0.0   Jun 26 00:00:07 Ss   sshd: root@pts/7
29677 29665 root      0.0  0.0   Jun 26 00:00:00 Ss   -bash
31576  4183 root      0.0  0.0   Jun 28 00:00:00 Ss   sshd: root@pts/19
31580 31576 root      0.0  0.0   Jun 28 00:00:00 Ss+  -bash
32550     1 root      0.0  0.0   Jun 12 00:00:02 Ss   sshd: root@pts/9
32559 32550 root      0.0  0.0   Jun 12 00:00:00 Ss+  -bash
32607 32559 root      0.0  0.0   Jun 12 00:00:00 T    psql -U shterm -h 10.10.16.201
32614 17652 postgres  0.0  0.0   Jun 12 00:00:00 Ss   postgres: shterm shterm 10.10.16.201(35370) idle
'''
if (output == null || "".equals(output)) {
    return null;
}
rtnList = [];
String[] rows = output.split("\\R");
int lastIndex = rows[0].indexOf("CMD");
for (int i = 0;i < rows.size();i++) {
    String row = rows[i].trim();
    int size = rows.size();
    if (row.length() <= 0 || row.contains("CMD")) {
        continue;
    }
    String str1 = rows[i].substring(0, lastIndex).trim();
    String str2 = rows[i].substring(lastIndex, rows[i].length());
    String [] rowArr = str1.split("[ \t]+");
    map = [:];
    if (rowArr.size() == 9) {
        map.put("pid", rowArr[0]);
        map.put("ppid", rowArr[1]);
        map.put("user", rowArr[2]);
        map.put("mem", rowArr[3]);
        map.put("cpu", rowArr[4]);
        map.put("started", rowArr[5] + " " + rowArr[6]);
        map.put("time", rowArr[7]);
        map.put("stat", rowArr[8]);
        map.put("cmd", str2);
    } else if (rowArr.size() == 8) {
        map = [:];
        map.put("pid", rowArr[0]);
        map.put("ppid", rowArr[1]);
        map.put("user", rowArr[2]);
        map.put("mem", rowArr[3]);
        map.put("cpu", rowArr[4]);
        map.put("started", rowArr[5]);
        map.put("time", rowArr[6]);
        map.put("stat", rowArr[7]);
        map.put("cmd", str2);
    } else {

    }
    rtnList.add(map);
}
return rtnList;




