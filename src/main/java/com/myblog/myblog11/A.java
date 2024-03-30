package com.myblog.myblog11;

//public class TestClass1 {
       //   public static void main(String[] args) {
//               List<String> names = Arrays.asList("Alice", "Bob", "Anna", "Alex", "Charlie");
//
//             // Use filter to keep names that do not start with 'A'
//              List<String> filteredNames = names.stream()
//                       .filter(name -> !name.startsWith("A"))
//                      .collect(Collectors.toList());
//
//               // Print the filtered names
//               filteredNames.forEach(System.out::println);
//            }

              //2nd program

//
//              List<Integer> numbers = Arrays.asList(10, 20, 6, 5, 7, 8, 9);
//              List<Integer> data = numbers.stream().filter(n1 -> n1 % 2 == 0).map(n2 -> n2 * n2).collect(Collectors.toList());
//              System.out.println(data);
//
//          }

              //3rd pgm
//              List<Employee> employees = Arrays.asList(
//                      new Employee("mike", 30, "chennai"),
//                      new Employee("adam", 25, "chennai"),
//                      new Employee("stallin", 34, "pune"),
//                      new Employee("sam", 34, "Banglore")
//              );
//
//
//              Map<String, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(e -> e.getCity()));
//              System.out.println(collect);
//
//          }

              //4th pgm

              // Map<Integer, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(e -> e.getAge()));
              // System.out.println(collect);


              //  }

              //5th

              // Map<Integer, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(e -> e.getAge(30)));
              //System.out.println(collect.get(30));
              //  }

              //6th
              //  Map<Integer, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(e -> e.getAge()));
//             for ( Employee employee :employees){
//                 System.out.println(collect.get(employee.getAge()));
//             }
              // }

              //7th
//
//            for ( Employee employee: employees){
//
              // List<Employee>  e =  collect.get(employee.getAge());
//             for (Employee x:e){
//                 System.out.println(x.getName());
//                 System.out.println(x.getCity());
//                 System.out.println(x.getAge());
//             }
//            }
              //8th

              //
              // Map<Integer, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(e -> e.getAge()));
              //(  System.out.println(collect); )
//              for (Employee employee:employees) {
//                  List<Employee>  e =  collect.get(employee.getAge());
//                  for(Employee x:e){
//                      System.out.println(x.getName());
//                      System.out.println(x.getCity());
//                      System.out.println(x.getAge());
//
//                  }
//           }
              //9th  List of keys on map

//              Map<Integer, List<Employee>> collect = employees.stream()
//                      .collect(Collectors.groupingBy(Employee::getAge));
//
//              for (Map.Entry<Integer, List<Employee>> entry : collect.entrySet()) {
//                  int age = entry.getKey();
//                  List<Employee> employeesWithAge = entry.getValue();
//                  System.out.println("Employees with age " + age + ": " + employeesWithAge);
//              }
              //10 th
//              Map<Integer, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(e -> e.getAge()));
//              for (Map.Entry<Integer,List<Employee>> entry: collect.entrySet()){
//                  int age = entry.getKey();
//                  List<Employee> employeesWithAge = entry.getValue();
//                  System.out.println("age:"+age+"---");
//                  for (Employee e : employeesWithAge){
//                      System.out.println(e.getCity());
//                      System.out.println(e.getName());
//                  }
//
//              }
//
//          }
              //11th

//
public class A{
public static void main(String[] args) {
    new A().test().example();

}
public A test(){
    return new A();
              }

      public void example() {
          System.out.println("hello");

      }
}