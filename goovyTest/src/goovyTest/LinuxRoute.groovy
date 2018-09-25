output='''-r-xr-xr-x    1 bin      bin            1825 Apr 17 09:25 /etc/profile'''

if(output == null){
    return null;
  }
			String one = output.substring(1, 4);
			int num1 = 0;
			if (one.contains("r")){
	num1 = num1 + 4;
}
if(one.contains("w")){
	num1 = num1 + 2;
}
if(one.contains("x")){
	num1 = num1 + 1;
}
String oneLength = String.valueOf(num1);
String two = output.substring(4, 7);
int num2 = 0;
if (two.contains("r")){
	num2 = num2 + 4;
}
if(two.contains("w")){
	num2 = num2 + 2;
}
if(two.contains("x")){
	num2 = num2 + 1;
}
String twoLength = String.valueOf(num2);
String three = output.substring(7, 10);
int num3 = 0;
if (three.contains("r")){
	num3 = num3 + 4;
}
if(three.contains("w")){
	num3 = num3 + 2;
}
if(three.contains("x")){
	num3 = num3 + 1;
}
String threeLength =  String.valueOf(num3);
println(oneLength + twoLength + threeLength)
return  oneLength + twoLength + threeLength;