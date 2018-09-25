package database
output = '''Oracle Database 12c                                                  12.2.0.1.0
SID:
lorcl12c
PID:
5606
PROCESS_NAME:
/u01/app/oracle/product/12.2.0/db_1/bin/tnslsnr
LISTEN_PORT:
:::1521
:::5500
'''

//id oracle >/dev/null 2>&1 && if [ $? -eq 0 ]; then su - oracle -c "`su - oracle -c \"env | grep ORACLE_HOME|cut -d '=' -f 2\"`/OPatch/opatch lsinventory 2>/dev/null|grep -i 'Oracle Database'" 2>&1 && if [ $? -eq 0 ]; then echo "SID:" ;ps auxf|grep ora_db|grep -v 'grep'|awk -F"_dbw0_" '{print $2}' |awk '{print $NF}' | grep -v '^$' ; echo "PID:" ;ps auxf|grep tnslsnr|grep -v 'grep'|awk '{print $2}';  echo "PROCESS_NAME:" ;ps auxf|grep tnslsnr|grep -v 'grep'|awk '{print $11}'; echo "LISTEN_PORT:" ;netstat -tlnup 2>/dev/null |grep `ps auxf|grep tnslsnr|grep -v 'grep'|awk '{print $2}'`|awk '{print $4}';fi ; fi
if (output == null || "".equals(output)) {
    return null;
}
rtnList = [];
String[] rows = output.split("\\R");
map=[:];
if (rows.size() > 0) {
    for (int i = 0;i < rows.size();i++) {
        if (i == 0) {
            String [] rowArr = rows[i].split("[ \t]+");
            map.put("version", rowArr[rowArr.size() - 1]);
            map.put("name", rows[i].replace(rowArr[rowArr.size() - 1], "").trim());
            map.put("vendor", "Oracle");
        }
        if ("SID:".equals(rows[i]) && (i + 1) < (rows.size() -1) ) {
            map.put("sid", rows[i + 1]);
        }
        if ("PID:".equals(rows[i]) && (i + 1) < (rows.size() -1) ) {
            map.put("pid", rows[i + 1]);
        }
        if ("PROCESS_NAME:".equals(rows[i]) && (i + 1) < (rows.size() -1) ) {
            map.put("processName", rows[i + 1]);
        }
        if ("LISTEN_PORT:".equals(rows[i]) && (i + 1) < (rows.size() - 1) && (i + 2) <= (rows.size() - 1) ) {
            map.put("listenPort", rows[i + 1] + " " + rows[i + 2]);
        }
    }
}
println(map)
return rtnList;




