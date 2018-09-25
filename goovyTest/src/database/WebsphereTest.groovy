package database

import java.text.SimpleDateFormat

output = '''WVER0010I: Copyright (c) IBM Corporation 2002, 2012; All rights reserved.
WVER0012I: VersionInfo reporter V1.15.1.48�����ڣ�2/8/12

--------------------------------------------------------------------------------
IBM WebSphere ��Ʒ��װ״̬����
--------------------------------------------------------------------------------

���ں�ʱ�� 2018��7��10�� ����09ʱ47��14�� ʱ�ı���

��װ
--------------------------------------------------------------------------------
��ƷĿ¼                     /opt/IBM/WebSphere/AppServer
�汾Ŀ¼                     /opt/IBM/WebSphere/AppServer/properties/version
DTD Ŀ¼                   /opt/IBM/WebSphere/AppServer/properties/version/dtd
��־Ŀ¼                     /var/ibm/InstallationManager/logs

��Ʒ�б�
--------------------------------------------------------------------------------
BASE                     �Ѱ�װ

�Ѱ�װ��Ʒ
--------------------------------------------------------------------------------
����         IBM WebSphere Application Server
�汾         8.5.5.0
��ʶ         BASE
��������       gm1319.01
��������       5/14/13
�����        com.ibm.websphere.DEVELOPERSILAN.v85_8.5.5000.20130514_1044
��ϵ�ṹ       x86-64 (64 bit)
�Ѱ�װ���ܲ���    ��� Java �� IBM 64 λ WebSphere SDK
           WebSphere Application Server Full Profile
           ���� EJB 3.0 ֮ǰ��ģ��� EJBDeploy ����
           ��Ƕ��ʽ EJB ����
           �����ݿͻ�������Դ������
��ѡ����       ��������

--------------------------------------------------------------------------------
������װ״̬����
--------------------------------------------------------------------------------
'''

weblogic='''<domain-version>9.2.2.0</domain-version>'''


rtnList = [];
if (output != null) {
    String [] lines = output.split("\\R");
    if (lines.size() > 0) {
        map = [:];
        for (String str : lines) {
            if (str.trim().startsWith("����")) {
                String name = str.replace("����", "").trim();
                map.put("name", name)
            }
            if (str.trim().startsWith("�汾")) {
                String version = str.replace("�汾", "").trim();
                map.put("version", version)
            }
            if (str.trim().startsWith("��������")) {
                String time = str.replace("��������", "").trim();
                SimpleDateFormat sdf = new SimpleDateFormat ("MM/dd/yy");
                Date date = sdf.parse(time);
                long timgLong = date.getTime();
                map.put("installTime", timgLong)
            }
            map.put("vendor", "IBM");
        }
        if (map.containsKey("name") && map.get("name") != null) {
            rtnList.add(map);
        }
    }
}
if (weblogic != null) {
    if (weblogic.contains("<domain-version>")) {
        String version = weblogic.replace("<domain-version>", "").replace("</domain-version>", "").trim();
        weblogicMap = [:];
        weblogicMap.put("name", "weblogic");
        weblogicMap.put("vendor", "Oracle");
        weblogicMap.put("version", version);
        rtnList.add(weblogicMap);
    }
}
return rtnList;


