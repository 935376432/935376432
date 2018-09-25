output = '''������:     ��������
����������: Intel(R) PRO/1000 MT Network Connection
�����ַ:   00-0C-29-9F-1E-36
��������:   DeviceTcpip_{559C9172-62EC-4B27-B897-9671C86713D3}

������:     �������� 2
����������: TAP-Win32 Adapter V9
�����ַ:   00-FF-F9-78-2D-3F
��������:   ý���ѶϿ�����

�ӿ� "�������� 2" ������
    DHCP ������:                          ��
    InterfaceMetric:                      30

�ӿ� "��������" ������
    DHCP ������:                          ��
    IP ��ַ:                           10.10.16.113
    ����ǰ׺:                        10.10.16.0/20 (���� 255.255.240.0)
    Ĭ������:                         10.10.16.1
    ����Ծ����:                       256
    InterfaceMetric:                      10

�ӿ� "Loopback Pseudo-Interface 1" ������
    DHCP ������:                          ��
    IP ��ַ:                           127.0.0.1
    ����ǰ׺:                        127.0.0.0/8 (���� 255.0.0.0)
    InterfaceMetric:                      50'''


rtnList = [];
if (output == null || "".equals(output)) {
    return null;
}
String [] lines = output.split("\\R");
if (lines.length == 0) {
    return null;
}
rtnList = [];
macMap = [:];
for (String st : lines) {
    if (st.startsWith("������")) {
        String [] strName  = st.split(":")
        macMap.put("name", strName[1].trim());
    }
    if (st.startsWith("�����ַ")) {
        String [] strMac  = st.split(":")
        String mac = strMac[1] == null ? strMac[1] : strMac[1].trim();
        if(mac != null && mac.contains("-")){
            mac = mac.replace("-",":")
        }
        macMap.put("macAdd", mac);
        rtnList.add(macMap);
        macMap = [:];
    }
    if (st.startsWith("�ӿ�")) {
        break;
    }
}
for (Map<String, Object> ma : rtnList) {
    boolean isAddOut2 = false;
    for (int i = 0; i < lines.length; i++) {
        String st = lines[i];
        if(isAddOut2 && st.startsWith("�ӿ�")){
            break;
        }
        if (st.startsWith("�ӿ�") && ma.get("name") != null && st.contains('"' + ma.get("name").toString() + '"')) {
            isAddOut2 = true;
        }
        if(isAddOut2) {

            //println(st)
        }
        if (isAddOut2 && st.contains("IP")) {
            String[] ipStrli = st.split(":");
            ma.put("ipAdd", ipStrli[1].trim());
        }
        if (isAddOut2 && st.contains("����")) {
            String[] maskStrli = st.split(":");
            if(maskStrli[1].trim().contains("(") ){
                int indexOne = maskStrli[1].trim().lastIndexOf("(");
                int indexTwo = maskStrli[1].trim().lastIndexOf(")");
                String masub = maskStrli[1].trim().substring(indexOne, indexTwo);
                String[] maList = masub.split("[ \t]+");
                ma.put("maskAdd", maList[1]);
            }else{
                ma.put("maskAdd", maskStrli[1].trim());
            }
        }
    }
}
    println(rtnList)
return rtnList;







