package test;

public class Test {

	public static void main(String[] args) {

		String output = "-rw-r--r--    1 root     system           82 Aug  9 08:13 /.profile";
		String one = output.substring(1, 4);
		int num1 = 0;
		if (one.contains("r")){
			num1 = num1 + 4;
		}
		if(one.contains("w")){
			num1 = num1 + 1;
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
			num2 = num2 + 1;
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
			num3 = num3 + 1;
		}
		if(three.contains("x")){
			num3 = num3 + 1;
		}
		String threeLength =  String.valueOf(num3);
		System.out.println(  oneLength + twoLength + threeLength);
		/*-
		r-x
		r-x
		r-x
		*/
		/*String one = out.substring(1, 4);
		String two = out.substring(4, 7);
		String three = out.substring(7, 10);

		System.out.println(getLength(one) + getLength(two) + getLength(three));
		System.out.println(one);
		System.out.println(two);
		System.out.println(three);*/


		/*String sss = " minalpha =6";
		String[] count= sss.split("=");
		System.out.println(count[1]);
		if(count.length != 0){
			System.out.println();
		}*/
		String s = "sss     fff";
		s = s.replace(" ","");
		System.out.println(s);
		String[] strs = s.split("[ \t]+");
		System.out.println(strs.length);


	}

	public static String getLength(String str){
		int in = 0;
		if(str.contains("r")){
			in = in + 4;
		}
		if(str.contains("w")){
			in = in + 1;
		}
		if(str.contains("x")){
			in = in + 1;
		}
		return String.valueOf(in);
	}

}
