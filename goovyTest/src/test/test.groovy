package test

output = '''OS Name:                   Microsoft(R) Windows(R) Server 2003 Enterprise x64 Ed'''

if (output == null || "".equals(output)) {
    return null;
}
output = output.trim();
if (output.startsWith("OS Name")) {
    String [] infos = output.split("[ \t]+");
    println("W" + infos[5][0..3])
    return "W" + infos[5][0..3];
}
return null;



