package pers.core.proxy;

/**
 * @description: 静态代理
 * @author: Mr.Dang 315764194@qq.com
 * @Date: 2018-11-04 21:53
 **/
public class StaticProxy implements UserManager {
    private UserManager userManager;

    public StaticProxy(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public String findUser(String userId) {
        String result = null;
        try {
            result = this.userManager.findUser("12345");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
