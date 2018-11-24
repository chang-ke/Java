import pers.core.io.Tokenizer;
import pers.core.proxy.DynamicProxy;
import pers.core.proxy.StaticProxy;
import pers.core.proxy.UserManager;
import pers.core.proxy.UserManagerImpl;
import pers.core.thread.MRunnable;
import pers.core.thread.MThread;


public class Application {

    public void runThread() {
        Runnable m = new MRunnable();
        Thread t = new Thread(m);
        Thread t1 = new Thread(m);
        MThread a = new MThread();
        Thread t2 = new Thread(a);
        Thread t3 = new Thread(a);
        t2.start();
        t3.start();
        // t.start();
        // t1.start();
    }

    private static void runCompiler() {
        new Tokenizer(".\\src\\resources\\main.ks");
    }

    private static void runProxy() {
        UserManager staticManager = new StaticProxy(new UserManagerImpl());
        UserManager dynamicManager = (UserManager) new DynamicProxy().newProxyInstance(new UserManagerImpl());
        System.out.printf("staticProxy result: %s", staticManager.findUser("5"));
        System.out.printf("dynamicProxy result: %s", dynamicManager.findUser("6"));
    }

    public static void main(String args[]) {
        runProxy();
        // runCompiler();
    }
}
