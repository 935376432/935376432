output = '''rpmdb: Thread/process 18362/3077879584 failed: Thread died in Berkeley DB library
error: db3 error(-30974) from dbenv->failchk: DB_RUNRECOVERY: Fatal error, run database recovery
error: cannot open Packages index using db3 -  (-30974)
error: cannot open Packages database in /var/lib/rpm
jdk1.8.0_131:1.8.0_131:Oracle Corporation:1497516142
lrzsz:0.12.20:CentOS:1495779916
vsftpd:2.2.2:CentOS:1447659966
xterm:253:CentOS:1447659018
libXaw:1.0.11:CentOS:1447659018
libXpm:3.5.10:CentOS:1447659014
tigervnc-server:1.1.0:CentOS:1447656421
telnet:0.17:CentOS:1447645216
telnet-server:0.17:CentOS:1447645174
xinetd:2.3.14:CentOS:1447645173
gpg-pubkey:c105b9de:(none):1447645168
words:3.0:CentOS:1446428668
man-pages:3.22:CentOS:1446428668
rt73usb-firmware:1.8:CentOS:1446428665
rootfiles:8.1:CentOS:1446428665
'''


rtnList = [];
if (output == null || output.trim() == "") {
    return rtnList;
}
String[] lines = output.split("\\R");
for (String str : lines) {
    if (str.contains("Fileset")) {
        continue;
    }
    String [] infos = str.split(":");
    if (infos == null || infos.size() != 4) {
        continue;
    }
    def timeNum = infos[3];
    if (!timeNum.isInteger()) {
        continue;
    }
    map = [:];
    map.put("name", infos[0]);
    map.put("version", infos[1]);
    map.put("vendor", infos[2]);
    map.put("installTime", infos[3]);
    rtnList.add(map);
    println(map);
}
return rtnList;




