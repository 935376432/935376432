output = '''f10002000008abb0 tcp4       0      0  *.13               *.*                LISTEN'''
netstatusall = [];
netstatusall.add(output);


def cmds =[];
              for(String str : netstatusall) {
                String[] list = str.split("[ \t]+");
                if (list[1].contains("tcp")){
                  cmds.add("echo '" + str + "' ;"+"rmsock " + list[0] + " tcpcb");
                  println("echo '" + str + "' ;"+"rmsock " + list[0] + " tcpcb")
                } else if(list[1].contains("udp")){
                  cmds.add("echo '" + str + "' ;"+"rmsock " + list[0] + " inpcb");
                }
              }
              return cmds;




