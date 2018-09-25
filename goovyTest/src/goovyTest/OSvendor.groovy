output = ''''''
output2='''LSB Version: :core-4.1-amd64:core-4.1-noarch
Distributor ID: CentOS
Description:    CentOS Linux release 7.5.1804 (Core)
Release:    7.5.1804
Codename:   Core
'''
if (output != null && !"".equals(output)) {
  return output;
}
String version = null;
String [] lines = output2.split("\\R");
if (lines.length == 0) {
  return null;
}
for (String str : lines) {
  if (str.startsWith("Description")) {
    String [] dis = str.split(":");
    version = dis[1].trim();
  }

}
println(version)
return version;
