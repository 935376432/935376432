output = '''Active Internet connections (including servers)
PCB/ADDR         Proto Recv-Q Send-Q  Local Address      Foreign Address    (state)
f10002000008abb0 tcp4       0      0  *.13               *.*                LISTEN
f1000200000853b0 tcp        0      0  *.21               *.*                LISTEN
f100020000087bb0 tcp        0      0  *.23               *.*                LISTEN
f10002000120cbb0 tcp4       0      0  *.25               *.*                LISTEN
f10002000008a3b0 tcp4       0      0  *.37               *.*                LISTEN
f100020001228bb0 tcp4       0      0  *.111              *.*                LISTEN
f100020001c77bb0 tcp        0      0  *.199              *.*                LISTEN
f1000200000883b0 tcp        0      0  *.512              *.*                LISTEN
f100020000088bb0 tcp        0      0  *.513              *.*                LISTEN
f1000200000873b0 tcp        0      0  *.514              *.*                LISTEN
f100020001b7dbb0 tcp4       0      2  192.168.9.230.23   10.10.66.33.53599  ESTABLISHED
f100020000085bb0 tcp4       0      0  *.6000             *.*                LISTEN
f1000200000813b0 tcp6       0      0  *.6000             *.*                LISTEN
f10002000008cbb0 tcp4       0      0  *.6112             *.*                LISTEN
f10002000120c3b0 tcp4       0      0  *.32768            *.*                LISTEN
f10002000008c3b0 tcp4       0      0  *.32769            *.*                LISTEN
f1000200000923b0 tcp4       0      0  *.32770            *.*                LISTEN
f1000200012283b0 tcp4       0      0  *.32771            *.*                LISTEN
f100020001221bb0 tcp4       0      0  *.32772            *.*                LISTEN
f100020000283bb0 tcp4       0      0  *.32773            *.*                LISTEN
f1000200002843b0 tcp4       0      0  *.32775            *.*                LISTEN
f1000200020573b0 tcp        0      0  *.32777            *.*                LISTEN
f1000200002873b0 tcp4       0      0  192.168.9.230.3277 192.168.9.230.702  ESTABLISHED
f100020000287bb0 tcp4       0      0  192.168.9.230.702  192.168.9.230.3277 ESTABLISHED
f100020000284bb0 tcp4       0      0  192.168.9.230.3277 192.168.9.230.720  ESTABLISHED
f1000200002833b0 tcp4       0      0  192.168.9.230.720  192.168.9.230.3277 ESTABLISHED
f1000200002823b0 tcp4       0      0  192.168.9.230.3277 192.168.9.230.756  ESTABLISHED
f100020000282bb0 tcp4       0      0  192.168.9.230.756  192.168.9.230.3277 ESTABLISHED
f100020000092bb0 tcp4       0      0  *.9090             *.*                LISTEN
f1000200002813b0 tcp4       0      0  192.168.9.230.3277 192.168.9.230.1003 ESTABLISHED
f100020000281bb0 tcp4       0      0  192.168.9.230.1003 192.168.9.230.3277 ESTABLISHED
f100020001afabb0 tcp4       0      0  192.168.9.230.23   10.10.66.33.62659  ESTABLISHED
f1000200002883b0 tcp4       0      0  192.168.9.230.23   10.10.66.21.64385  ESTABLISHED
f100020001224bb0 tcp4       0      0  192.168.9.230.3277 192.168.9.230.3277 ESTABLISHED
f1000200012213b0 tcp4       0      0  192.168.9.230.3277 192.168.9.230.3277 ESTABLISHED
f100020001225bb0 tcp4       0      0  192.168.9.230.3277 192.168.9.230.3277 ESTABLISHED
f1000200012243b0 tcp4       0      0  192.168.9.230.3277 192.168.9.230.3277 ESTABLISHED
f10002000028a3b0 tcp4       0      0  192.168.9.230.4819 192.168.9.230.4819 ESTABLISHED
f1000200004bd3b0 tcp4       0      0  192.168.9.230.4819 192.168.9.230.4819 ESTABLISHED
f1000200002893b0 tcp4       0      0  *.48196            *.*                LISTEN
f100020000086400 udp4       0      0  *.13               *.*
f100020000086a00 udp4       0      0  *.37               *.*
f100020001227600 udp4       0      0  *.111              *.*
f100020001c6e600 udp        0      0  *.161              *.*
f1000200011fb200 udp4       0      0  *.177              *.*
f1000200011fe000 udp4       0      0  *.514              *.*
f100020000086c00 udp4       0      0  *.518              *.*
f100020000097e00 udp4       0      0  *.2279             *.*
f100020001227a00 udp4       0      0  *.32774            *.*
f10002000008ba00 udp4       0      0  *.32784            *.*
f1000200011fe600 udp4       0      0  *.32800            *.*
f100020000094e00 udp4       0      0  *.32852            *.*

Active UNIX domain sockets
SADR/PCB         Type   Recv-Q Send-Q      Inode            Conn             Refs           Nextref      Addr
f10002000123b008 stream      0      0 f10001002736a020                0                0                0 /tmp/dpi_socket
f100020001c67800
f100020000141008 dgram       0      0 f1000100257fc020                0 f100020001219580                0 /dev/log
f100020001219180
f1000200011f8008 dgram       0      0                0 f100020001219180                0                0
f1000200011fa180
f100020001204c08 dgram       0      0                0 f100020001219180                0 f1000200011fa180
f100020001219580
f100020000082008 stream      0      0 f100010025783c20                0                0                0 /tmp/.X11-unix/X0
f10002000006c900
'''


rtnList = [];
if (output == null || "".equals(output)) {
    return null;
}
String [] lines = output.split("\\R");
if (lines.length == 0) {
    return null;
}
boolean isAdd = false;
for (String st : lines) {
    if (st.startsWith("Active") && isAdd) {
        break;
    }
    if (st.startsWith("PCB/ADDR")) {
        isAdd = true;
        continue;
    }
    if ("".equals(st.trim())) {
        continue;
    }
    if (isAdd) {
        rtnList.add(st);
        println(st);
    }
}
return rtnList;




