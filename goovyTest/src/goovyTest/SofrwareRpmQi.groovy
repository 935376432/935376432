import java.text.SimpleDateFormat

output='''Name        : httpd
Version     : 2.4.6
Release     : 80.el7.centos
Architecture: x86_64
Install Date: Sat 09 Jun 2018 05:18:53 PM CST
Group       : System Environment/Daemons
Size        : 9817285
License     : ASL 2.0
Signature   : RSA/SHA256, Wed 25 Apr 2018 07:04:41 PM CST, Key ID 24c6a8a7f4a80eb5
Source RPM  : httpd-2.4.6-80.el7.centos.src.rpm
Build Date  : Sat 21 Apr 2018 02:12:22 AM CST
Build Host  : x86-01.bsys.centos.org
Relocations : (not relocatable)
Packager    : CentOS BuildSystem <http://bugs.centos.org>
Vendor      : CentOS
URL         : http://httpd.apache.org/
Summary     : Apache HTTP Server
Description :
The Apache HTTP Server is a powerful, efficient, and extensible
web server.
'''
nginx = '''Name        : nginx
Epoch       : 1
Version     : 1.12.2
Release     : 2.el7
Architecture: x86_64
Install Date: Sun 01 Jul 2018 04:10:53 PM CST
Group       : System Environment/Daemons
Size        : 1574949
License     : BSD
Signature   : RSA/SHA256, Tue 06 Mar 2018 05:44:06 PM CST, Key ID 6a2faea2352c64e5
Source RPM  : nginx-1.12.2-2.el7.src.rpm
Build Date  : Tue 06 Mar 2018 05:27:44 PM CST
Build Host  : buildhw-02.phx2.fedoraproject.org
Relocations : (not relocatable)
Packager    : Fedora Project
Vendor      : Fedora Project
URL         : http://nginx.org/
Bug URL     : https://bugz.fedoraproject.org/nginx
Summary     : A high performance web server and reverse proxy server
Description :
Nginx is a web server and a reverse proxy server for HTTP, SMTP, POP3 and
IMAP protocols, with a strong focus on high concurrency, performance and low
memory usage.
'''
tomcat='''Name        : tomcat
Epoch       : 0
Version     : 8.0.52
Release     : 1
Architecture: noarch
Install Date: Sat 07 Jul 2018 05:07:42 PM CST
Group       : System Environment/Daemons
Size        : 323338
License     : ASL 2.0
Signature   : RSA/SHA1, Thu 21 Jun 2018 02:28:59 PM CST, Key ID be67ce877b95ec27
Source RPM  : tomcat-8.0.52-1.src.rpm
Build Date  : Thu 21 Jun 2018 02:27:44 PM CST
Build Host  : repos
Relocations : (not relocatable)
URL         : http://tomcat.apache.org/
Summary     : Apache Servlet/JSP Engine, RI for Servlet 3.1/JSP 2.3 API
Description :
Tomcat is the servlet container that is used in the official Reference
Implementation for the Java Servlet and JavaServer Pages technologies.
The Java Servlet and JavaServer Pages specifications are developed by
Sun under the Java Community Process.

Tomcat is developed in an open and participatory environment and
released under the Apache Software License version 2.0. Tomcat is intended
to be a collaboration of the best-of-breed developers from around the world.
'''
tomcat='''package tomcat is not installed'''
nginx='''package tomcat is not installed'''
rtnList = [];
strList = [];
if (output != null && output.trim() != "") {
    strList.add(output);
}
if (tomcat != null && tomcat.trim() != "") {
    strList.add(tomcat);
}
if (nginx != null && nginx.trim() != "") {
    strList.add(nginx);
}
if (strList.size() > 0) {
    for (String strInfo : strList) {
        String [] lines = strInfo.split("\\R");
        map = [:];
        for (String str : lines) {
            if (str.startsWith("Name")) {
                String [] nameArr = str.split(":");
                release = nameArr[1].trim();
                map.put("name",nameArr[1].trim());
            }
            if (str.startsWith("Version")) {
                String [] versionArr = str.split(":");
                map.put("version",versionArr[1].trim());
            }
            if (str.startsWith("Install Date")) {
                int index = str.indexOf(":")
                    String time = str.substring(index + 1,str.size()).trim();
                SimpleDateFormat sdf = new SimpleDateFormat ("EEE dd MMM yyyy HH:mm:ss a Z", Locale.ENGLISH);
                Date date = sdf.parse(time);
                long timgLong = date.getTime();
                map.put("installTime",timgLong);
            }
            if (str.startsWith("Vendor")) {
                String [] vendorArr = str.split(":");
                map.put("vendor",vendorArr[1].trim());
            }
        }
        //rtnList.add(map)
        if (map.containsKey("name") && map.get("name") != null) {
            rtnList.add(map);
        }
    }
}
println(rtnList)
return rtnList;
