output = '''Name        : basesystem                   Relocations: (not relocatable)
Version     : 10.0                              Vendor: CentOS
Release     : 4.el6                         Build Date: 2010年11月11日 星期四 09时12分57秒
Install Date: 2015年11月02日 星期一 09时36分04秒      Build Host: c5b2.bsys.dev.centos.org
Group       : System Environment/Base       Source RPM: basesystem-10.0-4.el6.src.rpm
Size        : 0                                License: Public Domain
Signature   : RSA/8, 2011年07月03日 星期日 12时00分48秒, Key ID 0946fca2c105b9de
Packager    : CentOS BuildSystem <http://bugs.centos.org>
Summary     : The skeleton package which defines a simple Red Hat Enterprise Linux system
Description :
Basesystem defines the components of a basic Red Hat Enterprise Linux
system (for example, the package installation order to use during
bootstrapping). Basesystem should be in every installation of a system,
and it should never be removed.
'''


/*String vendor = null;
String[] lineList = output.split('\\R');
for (String item : lineList) {
  if(!item.contains("Vendor:")){
    continue;
  }
    String [] itemArr = item.split("Vendor:");
    if (itemArr.size() == 2) {
        vendor = itemArr[1].trim();
        break;
    }
}
println(vendor[0..3]);
if (vendor != null && vendor.size() > 4) {
    println(vendor[0..3]);
}

return vendor;
*/

String vendor = null;
String[] lineList = output.split('\\R');
for (String item : lineList) {
  if(!item.contains("Vendor:")){
  continue;
  }
  String [] itemArr = item.split("Vendor:");
  if (itemArr.size() == 2) {
    vendor = itemArr[1].trim();
    break;
  }
}
println(vendor)
return vendor;




