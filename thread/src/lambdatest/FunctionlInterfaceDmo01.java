package lambdatest;

public class FunctionlInterfaceDmo01 {

	 public static void main(String[] args) {
	        final int num = 1;
	        Converter<Integer, String> stringConverter =
	                (from) -> String.valueOf(from + num);
	        String str=stringConverter.convert(2);
	        System.out.println(str);//���3
	        //final�ؼ��ֿ���ȥ��������Ĭ���Ǵ��ڵģ����ԣ���num���������ٴθ�ֵ�޸�
	    }

	    @FunctionalInterface
	    interface Converter<F, T> {
	        T convert(F from);
	    }
	
	
}
