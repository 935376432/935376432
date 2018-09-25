output = '''LSB Version:    :core-4.1-amd64:core-4.1-noarch:cxx-4.1-amd64:cxx-4.1-noarch:desktop-4.1-amd64:desktop-4.1-noarch:languages-4.1-amd64:languages-4.1-noarch:printing-4.1-amd64:printing-4.1-noarch
Distributor ID: CentOS
Description:    CentOS Linux release 7.5.1804 (Core)
Release:    7.5.1804
Codename:   Core'''
if (output == null || "".equals(output)) {
    return null;
}
String [] lines = output.split("\\R");
if (lines.length == 0) {
    return null;
}
String distributor = null;
String release = null;
for (String str : lines) {
    if (str.startsWith("Distributor ID")) {
        String [] dis = str.split(":");
        distributor = dis[1].trim();
    }
    if (str.startsWith("Release")) {
        String [] rel = str.split(":");
        release = rel[1].trim();
    }
}
return distributor + " " + release[0..0];
