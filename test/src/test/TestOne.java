package test;

import java.util.UUID;

public class TestOne {

	public static void main(String[] args) {

		UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        System.out.println(uuidStr);
	}

	/*
	 * switch(变量){
		case 变量值1:
		    //;
		    break;
		case 变量值2:
		    //...;
		    break;
		  ...
		case default:
		    //...;
		    break;
		}
	 */

}


