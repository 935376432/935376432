output="-rwxr-xr-x    1 bin      bin           18656 Sep 26 2007  /usr/bin/X11/xhost"

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
	String one = output.substring(1, 4);
	String two = output.substring(4, 7);
	String three = output.substring(7, 10);
	print  getLength(one) + getLength(two) + getLength(three);