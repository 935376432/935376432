output ='''en0: flags=5e080863,c0<UP,BROADCAST,NOTRAILERS,RUNNING,SIMPLEX,MULTICAST,GROUPRT,64BIT,CHECKSUM_OFFLOAD(ACTIVE),PSEG,LARGESEND,CHAIN>
        inet 192.168.9.230 netmask 0xfffffe00 broadcast 192.168.5.255
         tcp_sendspace 131072 tcp_recvspace 65536 rfc1323 0
lo0: flags=e08084b<UP,BROADCAST,LOOPBACK,RUNNING,SIMPLEX,MULTICAST,GROUPRT,64BIT>
        inet 127.0.0.1 netmask 0xff000000 broadcast 127.255.255.255
        inet6 ::1/0
         tcp_sendspace 131072 tcp_recvspace 131072 rfc1323 1
'''

if (output == null || "".equals(output)) {
                    return null;
                }
                list = [];
                String [] lines = output.split("\\R");
                StringBuffer sb = new StringBuffer();
                for (int i = 0;i < lines.length; i++) {
                    String st = lines[i];
                    if (i != 0 && !st.startsWith(" ") && !"".equals(st.trim())) {
                        sb.append("&&&");
                    }
                    sb.append(st);
                }
                String[] newStr = sb.toString().split("&&&");
                for (String sst : newStr) {
                    String [] sstList = sst.split("[ \t]+");
                    maps = [:];
                    maps.put("name", sstList[0]);
                    for (int j = 0;j < sstList.length ;j++) {
                        String ssj = sstList[j];
                        if (ssj.equals("broadcast")) {
                            maps.put("broadcastAdd", sstList[j + 1]);
                        }
                        if (ssj.equals("inet")) {
                            String ipAddress = sstList[j + 1];
                            maps.put("ipAdd", ipAddress);
                        }
                        if (ssj.equals("netmask")) {
                            String maskStr = sstList[j + 1];
                            if (maskStr.length() == 10) {
                                //8位16进制数转ip
                                maskStr = maskStr.substring(2);
                                StringBuffer newMask = new StringBuffer("");
                                newMask.append(Integer.parseInt(maskStr.substring(0, 2), 16));
                                newMask.append(".");
                                newMask.append(Integer.parseInt(maskStr.substring(2, 4), 16));
                                newMask.append(".");
                                newMask.append(Integer.parseInt(maskStr.substring(4, 6), 16));
                                newMask.append(".");
                                newMask.append(Integer.parseInt(maskStr.substring(6), 16));
                                maps.put("maskAdd", newMask);
                            } else {
                                maps.put("maskAdd", sstList[j + 1]);
                            }
                        }
                    }
                    list.add(maps);
					println(maps)
                }
				println(list)
                return list;












				//8位16进制数转ip
				maskStr = maskStr.substring(2);
				String newMask = "";
				newMask = Integer.parseInt(maskStr.substring(0, 2), 16);
				newMask = newMask + ".";
				newMask = newMask +  Integer.parseInt(maskStr.substring(2, 4), 16);
				newMask = newMask + ".";
				newMask = newMask + Integer.parseInt(maskStr.substring(4, 6), 16);
				newMask = newMask + ".";
				newMask = newMask +  Integer.parseInt(maskStr.substring(6), 16);
				maps.put("maskAdd", newMask);