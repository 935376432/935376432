output = '''LSB Version:    :core-3.1-ia32:core-3.1-noarch:graphics-3.1-ia32:graphics-3.1-noarch
Distributor ID: RedHatEnterpriseServer
Description:    Red Hat Enterprise Linux Server release 5.2 (Tikanga)
Release:    5.2
Codename:   Tikanga
'''
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
println(distributor + " " + release[0..0]);
return distributor + " " + release[0..0];
