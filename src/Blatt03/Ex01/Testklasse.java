package Blatt03.Ex01;

/**
 * Created by tobi on 06.05.17.
 */
public class Testklasse {

    public static void main(String[] args) {

        // create new instances of Person and Student
        Person  p1 = new Person("Homer"),
                p2 = new Person("Homer"),
                p3 = new Person("Marge");
        Student s1 = new Student("Bart", 1),
                s2 = new Student("Bart", 1),
                s3 = new Student("Lisa", 2),
                s4 = new Student("Homer", 3)

        // Test criteria for equals
        // 1. null-check
        assert p1.equals(null) == false;
        // 2. reflexiveness
        assert p1.equals(p1);
        // 3. consistency
        assert p1.equals(p2) && !p1.equals(p3);
        // 4. transitivity


        // possible issues
        System.out.println(s4 instanceof Person);
        assert p1.equals(s4);

        // Test criteria for hashCode:
        // 1. consistency (provided equal-information is not changed)
        // 2. ...
    }
}
