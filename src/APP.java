import java.util.ArrayList;
import java.util.Scanner;

public class APP {

    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎来到黑马程序员管理系统");
            System.out.print("请选择操作：1，登录 2，注册 3，忘记密码 4，退出");
            String choose = sc.next();
            switch (choose) {
                case "1" -> login(list);
                case "2" -> register(list);
                case "3" -> forgetPassword(list);
                case "4" -> {
                    System.out.println("谢谢使用");
                    System.exit(0);
                }
                default -> System.out.println("没有这个选项");
            }
        }

    }

    /**
     * 忘记密码
     *
     * @param list
     */
    private static void forgetPassword(ArrayList<User> list) {

    }

    /**
     * 注册
     *
     * @param list
     */
    private static void register(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);

        //用户名
        String userName = null;
        //判断用户名是否符合格式和唯一性
        while (true) {
            System.out.println("请输入用户名");
            userName = sc.next();
            boolean judgment = checkUserName(userName);
            if (!judgment) {
                System.out.println("用户名格式有误，请重新输入");
            } else break;
            //判断用户名唯一性
            boolean contains = contains(list, userName);
            if (contains){
                System.out.println("该用户名已存在，请重新输入");
            }else break;
        }
        //密码
        String password =null;
        while (true) {
            System.out.println("请输入密码");
            password= sc.next();
            System.out.println("请再次输入密码");
            String AagainPassword = sc.next();
            if (!password.equals(AagainPassword)) {
                System.out.println("两次输入的密码不一致，请重新输入");
            } else {
                break;
            }
        }
        //身份证
        String passport=null;
        while (true) {
            System.out.println("请输入身份证号码");
             passport = sc.next();
            //判断身份证格式是否正确
            boolean judgment = checkPassport(passport);
            if (!judgment) {
                System.out.println("所输入的身份证号码格式有误，请重新输入");
            } else break;
        }
        //电话号码
        String phoneNumber=null;
        while (true) {
            System.out.println("请输入电话号码");
             phoneNumber = sc.next();
            boolean judgment = checkPhoneNumber(phoneNumber);
            if (!judgment){
                System.out.println("电话号码格式不正确，请重新输入");
            }else break;
        }
        //新增用户
        User user = new User(userName,password,passport,phoneNumber);
        list.add(user);
        System.out.println("注册成功");
        printList(list);
    }

    private static void printList(ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            System.out.println("用户名为"+user.getUserName()+"，密码为"+user.getPassword()+"，身份证为"+user.getPassport()+"，电话号码为"+user.getPhoneNumber());
        }
    }

    private static boolean checkPhoneNumber(String phoneNumber) {
        //长度为11
        if (phoneNumber.length() != 11) return false;
       //不以0开头
        if (phoneNumber.startsWith("0")) return false;
        //全部都是数字
        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            if (!(c >= '0' && c <= '9')) return false;
        }
        return true;
    }

    private static boolean checkPassport(String passport) {
        //长度为18
        if (passport.length() != 18) return false;
        //开头不能为0
        if (passport.startsWith("0")) return false;
        for (int i = 0; i < passport.length() - 1; i++) {
            char c = passport.charAt(i);
            //前17位只能为数字
            if (!(c >= '0' && c <= '9')) return false;
        }
        //最后一位为数字，大小写x
        char endChar = passport.charAt(passport.length() - 1);
        if (!((endChar >= '0' && endChar <= '9') || endChar == 'x' || endChar == 'X')) return false;
        return true;
    }

    private static boolean contains(ArrayList<User> list, String userName) {
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                User user1 = list.get(i);
                String listName = user1.getUserName();
                if (listName.equals(userName)) return true;
            }
        }
        return false;
    }

    /**
     * 验证用户名是否正确
     *
     * @param userName
     * @return
     */
    private static boolean checkUserName(String userName) {
        int length = userName.length();
        if (length <= 3 || length >= 15) return false;
        for (int i = 0; i < userName.length(); i++) {
            char c = userName.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                return false;
            }
        }
        //计算字符串中找到至少一个字母
        int count = 0;
        for (int i = 0; i < userName.length(); i++) {
            char c = userName.charAt(i);
            if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                count++;
                break;
                //当找到后就立刻停止结束循环，提高效率
            }
        }
        return count > 0;
    }

    /**
     * 登录
     *
     * @param list
     */
    private static void login(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String userName= sc.next();
        System.out.println("请输入密码");
        String passWord = sc.next();
        //生成验证码
        String newCode=createCode();
        System.out.println("验证码为");
        System.out.println("请输入验证码");
        String code = sc.next();


        //用户名唯一性判断
        boolean b2 = contains(list, userName);
        if (!b2){
            System.out.println("该用户名不存在，请重新输入或请注册");
        }






            for (int i = 0; i < list.size(); i++) {
                String listUsername = list.get(i).getUserName();
                String listPassword = list.get(i).getPassword();
                if (listUsername.equals(userName)&&listPassword.equals(passWord)){
                    new StudentSystem();
                    break;
                }else {
                    System.out.println("该用户密码错误，请重新输入");
                }
            }

    }

    private static String createCode() {
        int a=0;
        return null;
    }


}
