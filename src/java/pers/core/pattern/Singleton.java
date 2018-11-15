package pers.core.pattern;

/**
 * @description: 单例模式
 * @author: Mr.Dang 315764194@qq.com
 * @Date: 2018-11-08 20:11
 **/
public class Singleton {
    // 防止外部创建对象, 保证单例
    private Singleton() {
    }

    private static volatile Singleton instance;

    /**
     * 线程安全
     *
     * @return Singleton
     */
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.print(Singleton.getInstance() == Singleton.getInstance());
    }
}
