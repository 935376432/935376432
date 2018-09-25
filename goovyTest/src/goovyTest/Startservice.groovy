output = '''连接名:     本地连接
网络适配器: Intel(R) PRO/1000 MT Network Connection
物理地址:   00-0C-29-9F-1E-36
传输名称:   DeviceTcpip_{559C9172-62EC-4B27-B897-9671C86713D3}

连接名:     本地连接 2
网络适配器: TAP-Win32 Adapter V9
物理地址:   00-FF-F9-78-2D-3F
传输名称:   媒体已断开连接

接口 "本地连接 2" 的配置
    DHCP 已启用:                          是
    InterfaceMetric:                      30

接口 "本地连接" 的配置
    DHCP 已启用:                          否
    IP 地址:                           10.10.16.113
    子网前缀:                        10.10.16.0/20 (掩码 255.255.240.0)
    默认网关:                         10.10.16.1
    网关跃点数:                       256
    InterfaceMetric:                      10

接口 "Loopback Pseudo-Interface 1" 的配置
    DHCP 已启用:                          否
    IP 地址:                           127.0.0.1
    子网前缀:                        127.0.0.0/8 (掩码 255.0.0.0)
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
    if (st.startsWith("连接名")) {
        String [] strName  = st.split(":")
        macMap.put("name", strName[1].trim());
    }
    if (st.startsWith("物理地址")) {
        String [] strMac  = st.split(":")
        String mac = strMac[1] == null ? strMac[1] : strMac[1].trim();
        if(mac != null && mac.contains("-")){
            mac = mac.replace("-",":")
        }
        macMap.put("macAdd", mac);
        rtnList.add(macMap);
        macMap = [:];
    }
    if (st.startsWith("接口")) {
        break;
    }
}
for (Map<String, Object> ma : rtnList) {
    boolean isAddOut2 = false;
    for (int i = 0; i < lines.length; i++) {
        String st = lines[i];
        if(isAddOut2 && st.startsWith("接口")){
            break;
        }
        if (st.startsWith("接口") && ma.get("name") != null && st.contains('"' + ma.get("name").toString() + '"')) {
            isAddOut2 = true;
        }
        if(isAddOut2) {

            //println(st)
        }
        if (isAddOut2 && st.contains("IP")) {
            String[] ipStrli = st.split(":");
            ma.put("ipAdd", ipStrli[1].trim());
        }
        if (isAddOut2 && st.contains("子网")) {
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







