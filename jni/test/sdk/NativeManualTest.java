package sdk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NativeManualTest {
    
	static {
		System.load(Native.lib.path());
	}
	
    @Test
    public void whenNativeHelloWorld_thenOutputIsAsExpected() {
        Say helloWorld = new Say();
        String helloFromNative = helloWorld.sayHello();
        assertTrue(!helloFromNative.isEmpty() && helloFromNative.equals("Hello from C++ !!"));
    }
    
    @Test
    public void whenSumNative_thenResultIsArithmeticallyCorrect() {
        Param parametersNativeMethods = new Param();
        assertTrue(parametersNativeMethods.sumIntegers(200, 400) == 600L);
    }
    
    @Test
    public void whenSayingNativeHelloToMe_thenResultIsAsExpected() {
        Param parametersNativeMethods = new Param();
        assertEquals(parametersNativeMethods.sayHelloToMe("Orange", true), "Ms. Orange");
    }

    @Test
    public void whenCreatingNativeObject_thenObjectIsNotNullAndHasCorrectData() {
        String name = "Iker Casillas";
        double balance = 2378.78;
        Obj objectsNativeMethods = new Obj();
        UserData userFromNative = objectsNativeMethods.createUser(name, balance);
        assertNotNull(userFromNative);
        assertEquals(userFromNative.name, name);
        assertTrue(userFromNative.balance == balance);
    }
    
    @Test
    public void whenNativeCallingObjectMethod_thenResultIsAsExpected() {
        String name = "Sergio Ramos";
        double balance = 666.77;
        Obj objectsNativeMethods = new Obj();
        UserData userData = new UserData();
        userData.name = name;
        userData.balance = balance;
        assertEquals(objectsNativeMethods.printUserData(userData), "[name]=" + name + ", [balance]=" + balance);
    }
    
}
