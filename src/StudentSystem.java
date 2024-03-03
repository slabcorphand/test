import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class StudentSystem {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        loop:
        while (true) {
            System.out.println("-----------欢迎来到学生管理系统-----------");
            System.out.println("1添加学生");
            System.out.println("2删除学生");
            System.out.println("3修改学生");
            System.out.println("4查询学生");
            System.out.println("5退出");
            System.out.println("请输入你的选择");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose) {
                case "1" -> addStudent(list);
                case "2" -> deleteStudent(list);
                case "3" -> updateStudent(list);
                case "4" -> queryStudent(list);
                case "5" -> {
                    System.out.println("退出");
                    //break loop;
                    System.exit(0);
                }
                default -> System.out.println("没有这个选项");

            }
        }
    }

    private static void queryStudent(ArrayList<Student> list) {
        if (list.size() == 0) {
            System.out.println("当前无学生信息，请添加后在查询");
            return;
        }
        System.out.println("id\t\t姓名\t年龄\t家庭住址");
        list.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            System.out.println(student.getId() + "\t\t" + student.getName() + "\t" + student.getAge() + "\t" + student.getAddress());
        }
    }

    private static void updateStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("输入学生id来修改以下的学生信息");
            queryStudent(list);
            String id = sc.next();
            int index = contain(list, id);
            if (index==-1){
                System.out.println("修改失败，所选择的id:"+id+"，并不存在，请重新输入");
            }else {
                //根据index来修改学生信息
                System.out.println("请输入学生的姓名");
                String name = sc.next();
                System.out.println("请输入学生的年龄");
                int age = sc.nextInt();
                System.out.println("请输入学生的地址");
                String addres = sc.next();
                Student student = new Student(id, name, age, addres);
                list.set(index,student);
                System.out.println("修改成功");
                break;
            }
        }
    }

    private static void deleteStudent(ArrayList<Student> list) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("输入学生id来删除以下的学生信息");
            queryStudent(list);
            String id = scanner.next();
            int index = contain(list, id);
            if (index==-1){
                System.out.println("删除失败，所选择的id:"+id+"，并不存在，请重新输入");
            }else {
                //根据index来删除学生信息
                list.remove(index);
                System.out.println("删除成功");
                break;
            }
        }
    }

    private static void addStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        String id=null;
        while (true) {
            System.out.println("请输入学生的id");
            String sid = sc.next();
            //判断该id是否存在
            int b = contain(list, sid);
            if (b>=0) {
                //如果存在，请重新录入
                System.out.println("该id:"+sid+"已存在，请重新输入id");
            } else {
                //如果不存在，可以使用
                id=sid;
                break;
            }
        }
        System.out.println("请输入学生的姓名");
        String name = sc.next();
        System.out.println("请输入学生的年龄");
        int age = sc.nextInt();
        System.out.println("请输入学生的地址");
        String addres = sc.next();
        Student student = new Student(id, name, age, addres);
        list.add(student);
        System.out.println("学生信息添加成功");
    }
    /**
     * 根据id查询学生对象的index,如果存在就返回该对象的index，没有就返回-1
     */
    private static int contain(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            String studentId = student.getId();
            if (studentId.equals(id)){
                return i;
            }
        }
        return -1;
    }
}



