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
	 * switch(����){
		case ����ֵ1:
		    //;
		    break;
		case ����ֵ2:
		    //...;
		    break;
		  ...
		case default:
		    //...;
		    break;
		}
	 */

}


