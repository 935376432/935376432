win64 = '''{"DisplayName    REG_SZ    Microsoft .NET Framework 3.5 SP1 语言包 - 简体中文\r\n    DisplayName    REG_SZ    Microsoft .NET Framework 3.5 SP1\r\n    DisplayName    REG_SZ    WinPcap 4.0\r\n    DisplayName    REG_SZ    OpcAgent\r\n    DisplayName    REG_SZ    Microsoft .NET Framework 4.5.2\r\n    DisplayName    REG_SZ    Microsoft .NET Framework 3.5 Language Pack SP1 - chs\r\n    DisplayName    REG_SZ    Microsoft .NET Framework 4.5.2\r\n    DisplayName    REG_SZ    Update for Microsoft .NET Framework 4.5.2 (KB4014514)\r\n    DisplayName    REG_SZ    Microsoft .NET Framework 3.5 SP1\r\n    DisplayName    REG_SZ    Security Update for Microsoft .NET Framework 3.5 SP1 (KB2604111)\r\n    DisplayName    REG_SZ    Security Update for Microsoft .NET Framework 3.5 SP1 (KB2736416)\r\n    DisplayName    REG_SZ    Security Update for Microsoft .NET Framework 3.5 SP1 (KB2840629)\r\n    DisplayName    REG_SZ    Security Update for Microsoft .NET Framework 3.5 SP1 (KB2861697)\r\n    DisplayName    REG_SZ    Hotfix for Microsoft .NET Framework 3.5 SP1 (KB953595)\r\n    DisplayName    REG_SZ    Hotfix for Microsoft .NET Framework 3.5 SP1 (KB958484)\r\n    DisplayName    REG_SZ    Update for Microsoft .NET Framework 3.5 SP1 (KB963707)"}'''
install = new HashSet();
if (win64 != null) {
	String[] lines = win64.split("\\R");lines.length
	for(line in lines) {
		 String[] datas = line.split("REG_SZ");
		 install.add(datas[1].trim());
	}
}

//println( install);
println(Math.abs(1.3-5.6))
