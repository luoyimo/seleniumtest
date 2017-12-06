package com.huqi;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author huqi
 * @create 2017-12-06 10:27
 **/
public class StreamTest {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<Student>() {
            {
                add(new Student(20160001, "孔明", 20, 1, "土木工程", "武汉大学"));
                add(new Student(20160002, "伯约", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20160003, "玄德", 22, 3, "经济管理", "武汉大学"));
                add(new Student(20160004, "云长", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20161001, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
                add(new Student(20161002, "元直", 23, 4, "土木工程", "华中科技大学"));
                add(new Student(20161003, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
                add(new Student(20162001, "仲谋", 22, 3, "土木工程", "浙江大学"));
                add(new Student(20162002, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
                add(new Student(20163001, "丁奉", 24, 5, "土木工程", "南京大学"));
            }
        };

        Map<String, String> wuhanStudentMap = students.stream().filter(student -> "武汉大学".equals(student.getSchool())).collect(Collectors.toMap(Student::getName, Student::getSchool));
        System.out.println("wuhanMap --->" + wuhanStudentMap);

        wuhanStudentMap.compute("伯约", (k, v) -> v == null ? "s" : v.concat("s"));
        System.out.println("wuhanMap --->" + wuhanStudentMap);

        wuhanStudentMap.computeIfPresent("丁", (k, v) -> v == null ? "s" : v.toLowerCase().replace("武汉", "s"));
        System.out.println("wuhanMap --->" + wuhanStudentMap);


        System.out.println(wuhanStudentMap.computeIfPresent("丁奉", (k, v) -> "s"));
        System.out.println(wuhanStudentMap.computeIfAbsent("丁", v -> "s"));
        System.out.println("wuhanMap --->" + wuhanStudentMap);

        List<Student> wuhanStudentList = students.stream().filter(student -> "武汉大学".equals(student.getSchool())).collect(Collectors.toList());
        System.out.println("wuhanList--->" + wuhanStudentList);

        Set<Student> wuhanStudentSet = students.stream().filter(student -> "武汉大学".equals(student.getSchool())).collect(Collectors.toSet());
        System.out.println("wuhanSet-->" + wuhanStudentSet);

        Set<Student> wuhanStudentSet1 = students.parallelStream().collect(Collectors.toSet());
        System.out.println("wuhanSet1-->" + wuhanStudentSet1);

        List<String> studentsSchool = students.stream().map(student -> student.getSchool()).distinct().collect(Collectors.toList());
        System.out.println("studentSchool-->" + studentsSchool);


        Set<String> studentsSchool1 = students.stream().map(student -> student.getSchool()).collect(Collectors.toSet());
        System.out.println("studentSchool-->" + studentsSchool1);


        Optional<String> studentsSchool2 = students.stream().map(student -> student.getSchool()).reduce((s1, s2) -> s1 + "+" + s2);
        studentsSchool2.ifPresent(System.out::println);


        FunctionInterface functionInterface = new FunctionInterface() {
            @Override
            public double caclulate(int a) {
                return a * 100;
            }
        };

        System.out.println("匿名内部类乘法-----》" + functionInterface.caclulate(11));
        System.out.println("jdk8默认方法----》" + functionInterface.sqrt(100));

        FunctionInterface functionInterface1 = (a) -> a * 100;
        System.out.println("lambda表达式---->" + functionInterface1.caclulate(1));

        CovertInterface<String, Integer> covertInterface = (from) -> Integer.valueOf(from);
        System.out.println("lamda表达式实现接口---->" + covertInterface.convert("100"));

        FunctionInterface functionInterface2 = Math::sqrt;
        System.out.println("方法与构造函数引用---->" + functionInterface2.caclulate(100));

        CovertInterface<String, String> covertInterface1 = string -> string.toLowerCase();
        System.out.println("----->" + covertInterface1.convert("IPCD"));

//       CovertInterface<String,String> covertInterface2 = String::startsWith;


        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car, Car.create(Car::new));

        cars.forEach(Car::repair);

        cars.forEach(Car::collide);
    }

}
