package sdk;

public class Obj {

	static {
		System.load(Native.lib.path());
	}

    public static void main(String[] args) {
        Obj instance = new Obj();
        UserData newUser = instance.createUser("John Doe", 450.67);
        instance.printUserData(newUser);
    }
    
    public native UserData createUser(String name, double balance);
    
    public native String printUserData(UserData user);

}
