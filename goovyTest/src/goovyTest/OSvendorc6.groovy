output = '''Name        : basesystem
Version     : 10.0
Release     : 7.el7.centos
Architecture: noarch
Install Date: Fri 08 Jun 2018 01:29:25 AM CST
Group       : System Environment/Base
Size        : 0
License     : Public Domain
Signature   : RSA/SHA256, Fri 04 Jul 2014 08:46:57 AM CST, Key ID 24c6a8a7f4a80eb5
Source RPM  : basesystem-10.0-7.el7.centos.src.rpm
Build Date  : Fri 27 Jun 2014 06:37:10 PM CST
Build Host  : worker1.bsys.centos.org
Relocations : (not relocatable)
Packager    : CentOS BuildSystem <http://bugs.centos.org>
Vendor      : CentOS
Summary     : The skeleton package which defines a simple CentOS Linux system
Description :
Basesystem defines the components of a basic CentOS Linux
system (for example, the package installation order to use during
bootstrapping). Basesystem should be in every installation of a system,
and it should never be removed.'''
String vendor = null;
String[] lineList = output.split('\\R');
for (String item : lineList) {
	if(!item.contains(":") || !item.contains("Vendor")){
		continue;
	}
    String [] itemArr = item.split(":");
    if (itemArr.size() == 2) {
        vendor = itemArr[1].trim();
        break;
    }
}
return vendor;
