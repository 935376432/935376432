package lambdatest;

public class FunctionlInterfaceDmo01 {

	 public static void main(String[] args) {
	        final int num = 1;
	        Converter<Integer, String> stringConverter =
	                (from) -> String.valueOf(from + num);
	        String str=stringConverter.convert(2);
	        System.out.println(str);//输出3
	        //final关键字可以去掉，但是默认是存在的，所以，对num变量不能再次赋值修改
	    }

	    @FunctionalInterface
	    interface Converter<F, T> {
	        T convert(F from);
	    }
	
	
}
