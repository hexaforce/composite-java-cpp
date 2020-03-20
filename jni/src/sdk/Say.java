package sdk;

public class Say {

	static {
		System.load(Native.lib.path());
	}

	public static void main(String[] args) {
		new Say().sayHello();
	}

	public native String sayHello();

}
