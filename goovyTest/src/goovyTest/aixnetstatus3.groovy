output = '''f10002000008abb0 tcp4       0      0  *.13               *.*                LISTEN The socket 0x8a808 is being held by proccess 213100 (inetd).
'''
rtnList = [];
if (output == null || "".equals(output)) {
    return rtnList;
}
for (String str : output) {
    map = [:]
    String[] linAll = str.split("\\R");
    String[] linOne = linAll[0].split("[ \t]+");
    println(linOne)
    if (linOne[1].contains("tcp")) {
        map.put("serStatus", linOne[6] == null ? null : linOne[6]);
    }
    map.put("proto", linOne[1] == null ? null : linOne[1]);

    int localIndex = linOne[4].lastIndexOf(".");
    map.put("localAdd", linOne[4].substring(0, localIndex));
    map.put("localPort", linOne[4].substring(localIndex + 1, linOne[4].length()));
    int externalIndex = linOne[5].lastIndexOf(".");
    map.put("externalAdd", linOne[5].substring(0, externalIndex));
    map.put("externalPort", linOne[5].substring(0, externalIndex));
    if (linAll[1].contains("proccess")) {
        String[] linTwo = linAll[1].split("[ \t]+");
        map.put("pid", linTwo[linTwo.length - 2] == null ? null : linTwo[linTwo.length - 2]);
        map.put("program", linTwo[linTwo.length - 1]);
    }
    rtnList.add(map);
}
return rtnList ;




