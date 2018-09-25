import java.awt.CheckboxMenuItem.AccessibleAWTCheckboxMenuItem

output = '''default:
	        admin = false
	        login = true
	        su = true
	        daemon = true
	        rlogin = true
	        sugroups = ALL
	        admgroups =
	        ttys = ALL
	        auth1 = SYSTEM
	        auth2 = NONE
	        tpath = nosak
	        umask = 022
	        expires = 0
	        SYSTEM = "compat"
	        logintimes =
	        pwdwarntime = 0
	        account_locked = false
	        loginretries = 0
	        histexpire = 0
	        histsize = 0
	        minage = 0
	        maxage = 0
	        maxexpired = -1
	        minalpha =6
	        minother =0
	        minlen = 6
	        mindiff = 0
	        maxrepeats = 8
	        dictionlist =
	        pwdchecks =
	        default_roles =
	root:
	        admin = true
	        SYSTEM = "compat"
	        registry = files
	        loginretries = 0
	        account_locked = false
	        maxage = 1
	daemon:
	        admin = true
	        expires = 0101000070
	bin:
	        admin = true
	        expires = 0101000070
	sys:
	        admin = true
	        expires = 0101000070
	adm:
	        admin = true
	uucp:
	        admin = true
	        login = false
	        rlogin = false
	        su = true
	guest:
	nobody:
	        admin = true
	        expires = 0101000070
	lpd:
	        admin = true
	        expires = 0101000070
	invscout:
	        admin = true
	snapp:
	        admin = false
	        rlogin = false
	        su = false
	        SYSTEM = "NONE"
	        login = true
	        ttys = /dev/tty0
	        registry = files
	        dce_export = false
	ipsec:
	        admin = false
	nuucp:
	        admin = false
	pconsole:
	        admin = true
	        login = false
	        rcmds = deny
	        su = false
	esaadmin:
	        admin = true
	        login = false
	        rlogin = false
	        umask = 22
	        default_roles = SysConfig
	        admin = false
	admin:
	        admin = false
	zhangdj:
	        admin = false
	suzl01:
	        admin = false
	xulq:
	        admin = true
	        maxage = 1
	sshd:
	        admin = false
	        account_locked = true
	        login = false
	        rlogin = false
	masl:
	        admin = false
	natsu:
	        admin = false
	zhangxj:
	        admin = false
	        account_locked = true
	ll:
	        admin = false
	u01:
	        admin = false
	u02:
	        admin = false
	u03:
	        admin = false
	u04:
	        admin = false
	zhangqq:
	        admin = false'''
ret = [:];
currentData = null;
for (line in output.split('\\R')) {
	if (line.endsWith(":")) {
		currentData = [:]
		ret[line.substring(0, line.length() - 1).trim()] = currentData;
		continue;
	} else {
		sps = line.split("=")
		if (sps.length == 2) {
			currentData[sps[0].trim()] = sps[1].trim()
		} else if (sps.length > 2) {
			currentData[sps[0].trim()] = sps[1].trim()
		}
	}
}
for (item in ret) {
	println (item.getKey() + " => " + item.getValue())
}
println ret['u02']['admin']

String getLength(String str){
	int num = 0;
	if (str.contains("r")){
		num = num + 4;
	}
	if(str.contains("w")){
		num = num + 1;
	}
	if(str.contains("x")){
		num = num + 1;
	}
	return String.valueOf(num);
}

out = "-r-xr-xr-x    1 bin      bin            1825 Apr 17 09:25 /etc/profile";
String one = out.substring(1, 4);
String two = out.substring(4, 7);
String three = out.substring(7, 10);
println getLength(one) + getLength(two) + getLength(three);