package lambdatest;

public class MianTest{
	
	/*public static void main(String[] args) {
		
		User user = new User();
		user.setId(1L);
		user.setName("lilei");
		
		TestInterface<User> testIn = () -> test007(user);
		
		
		
		
	}*/
	
	public static void main(String[] args) {
		TestInterface<User> testIn = (user) -> { 
			user.setAge(11);
			user.setName("qq");
		};
		
		TestInterface<User> testIn3 = MianTest::tt;
		
		TestInterface<User> testIn2 = new TestInterface<User>() {
			@Override
			public void test007(User user) {
				user.setAge(11);
				user.setName("qq");
			}
		};

		User user = new User();
		user.setId(1L);
		user.setName("lilei");
        
		testIn.test007(user);
        
		System.out.println(user.toString());
    }
	
	public static void tt(User t){
		t.setName("a");
	}

    @FunctionalInterface
    public interface TestInterface<T> {
    	
    	void test007(T t);

    }
	
}

