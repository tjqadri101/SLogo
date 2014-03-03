package parse;

import java.lang.reflect.Field;

public class test {

	private int myField;

	public test() {
		myField = 42;
	}

	public static void test() throws ClassNotFoundException {

		Class<?> squareClass = Class.forName("parse.Language");

		Field[] fields = squareClass.getDeclaredFields();
		for (Field f : fields) {
			System.out.println("field name = " + f.getName()); 
		}

	}

	public static void main(String[] args) throws ClassNotFoundException{
		test();

	}

}
