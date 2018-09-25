package database

import java.text.SimpleDateFormat

output = '''
SQL*Plus: Release 12.2.0.1.0 Production on Mon Jul 9 16:51:31 2018

Copyright (c) 1982, 2016, Oracle.  All rights reserved.


???:
Oracle Database 12c Enterprise Edition Release 12.2.0.1.0 - 64bit Production

SQL>
BANNER
--------------------------------------------------------------------------------
Oracle Database 12c Enterprise Edition Release 12.2.0.1.0 - 64bit Production
PL/SQL Release 12.2.0.1.0 - Production
CORE    12.2.0.1.0  Production
TNS for Linux: Version 12.2.0.1.0 - Production
NLSRTL Version 12.2.0.1.0 - Production

SQL> ? Oracle Database 12c Enterprise Edition Release 12.2.0.1.0 - 64bit Production ??
上一次登录：一 7月  9 16:51:31 CST 2018pts/7 上
filetime
1490164493
'''

mysql='''mysql  Ver 14.14 Distrib 5.7.18, for Linux (x86_64) using  EditLine wrapper
1489824331
'''

db2='''
Install Path                       Level   Fix Pack   Special Install Number   Install Date                  Installer UID
---------------------------------------------------------------------------------------------------------------------
/opt/ibm/db2/V9.7                 9.7.0.0        0                            Mon Mar 27 17:48:33 2017 CST             0
'''
db2='''bash: db2ls: command not found'''
mysql='''bash: mysql: command not found\nwhich: no mysql in (/usr/lib64/qt-3.3/bin:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin)\nstat: 缺少操作数\n请尝试执行\"stat --help\"来获取更多信息。'''
output=null;
rtnList = [];
//oracle
if (output != null) {
    String [] linesOracle = output.split("\\R");
    if (linesOracle != null && linesOracle.size() > 0) {
        oracleMap = [:];
        for (int i = 0;i < linesOracle.size();i++) {
            if (linesOracle[i].startsWith("-----")) {
                String info = linesOracle[i + 1];
                String [] infoArr = info.split("Release");
                oracleMap.put("name",infoArr[0].trim());
                String [] versionArr = infoArr[1].trim().split("[ \t]+");
                oracleMap.put("vendor","Oracle");
                oracleMap.put("version",versionArr[0]);
            }
            if (linesOracle[i].startsWith("filetime")) {
                def installtime = linesOracle[i + 1].trim();
                if (installtime.isNumber()) {
                    long installTime = Long.parseLong(installtime.toString()) * 1000;
                    oracleMap.put("installTime",installTime);
                }
            }
        }
        rtnList.add(oracleMap);
    }
}
//mysql
if (mysql != null) {
    String [] linesMysql = mysql.split("\\R");
    if (linesMysql != null && linesMysql.size() > 0 && linesMysql.size() == 2) {
        mysqlMap = [:];
        String mysqlVersion = null;
        if (linesMysql[0].contains("Distrib")) {
            String [] infoArr = linesMysql[0].trim().split("[ \t]+");
            if (infoArr != null && infoArr.size() > 0) {
                for (int j = 0;j < infoArr.size();j++) {
                    if (infoArr[j].contains("Distrib")) {
                        mysqlVersion = infoArr[j + 1];
                        if (mysqlVersion.contains(",")) {
                            mysqlVersion = mysqlVersion.replace(",", "");
                        }
                    }
                }
            }
        }
        if (mysqlVersion != null) {
            mysqlMap.put("name", "mysql");
            mysqlMap.put("vendor","Oracle");
            mysqlMap.put("version",mysqlVersion);
            mysqlMap.put("type",2);
        }
        def installtime = linesMysql[1];
        if (installtime.isNumber()) {
            long installTime = Long.parseLong(installtime.toString()) * 1000;
            mysqlMap.put("installTime",installTime);
        }
        rtnList.add(mysqlMap);
    }
}
//db2
if (db2 != null) {
    String[] linesDb2 = db2.split("\\R");
    for (int k = 0;k < linesDb2.size();k++) {
        if (linesDb2[k].startsWith("Install Path")) {
            String [] infoArr = linesDb2[k + 2].trim().split("[ \t]+");
            String level = infoArr[1];
            String time = infoArr[infoArr.size() - 7] + " " + infoArr[infoArr.size() - 6] + " " + infoArr[infoArr.size() - 5] + " " + infoArr[infoArr.size() - 4] + " " + infoArr[infoArr.size() - 3] + " " + infoArr[infoArr.size() - 2];
            SimpleDateFormat sdf = new SimpleDateFormat ("EEE MMM dd HH:mm:ss yyyy Z", Locale.UK);
            Date date = sdf.parse(time);
            long timgLong = date.getTime()
                db2Map = [:];
            db2Map.put("name", "db2");
            db2Map.put("vendor","IBM");
            db2Map.put("version",level);
            db2Map.put("installTime",timgLong);
            rtnList.add(db2Map);
        }
    }
}
println(rtnList.size())
return rtnList;




