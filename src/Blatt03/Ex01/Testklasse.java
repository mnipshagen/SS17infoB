package Blatt03.Ex01;

/**
 * authors: mnipshagen, toludwig
 */
public class Testklasse {

    public static void main(String[] args) {

        // create new instances of Person and Student
        Person  p1 = new Person("Homer"),
                p2 = new Person("Homer"),
                p3 = new Person("Homer"),
                p4 = new Person("Marge");
        Student s1 = new Student("Bart", 1),
                s2 = new Student("Bart", 1),
                s3 = new Student("Bart", 1),
                s4 = new Student("Lisa", 2),
                s5 = new Student("Homer", 3);

        // Test criteria for equals in both classes
        // 1. null-check
        assert p1.equals(null) == false : "Person null check failed";
        assert s1.equals(null) == false : "Student null check failed";
        // 2. reflexiveness
        assert p1.equals(p1) : "Person non-reflexive";
        assert s1.equals(s1) : "Student non-reflexive";
        // 3. consistency
        assert p1.equals(p2) && !p1.equals(p4) : "Person inconsistent";
        assert s1.equals(p2) && !s1.equals(s4) : "Student inconsistent";
        // 4. transitivity
        assert p1.equals(p2) && p2.equals(p3) && p1.equals(p3) : "Person intransitive";
        assert s1.equals(s2) && s2.equals(s3) && s2.equals(s3) : "Person intransitive";


        // possible issues:
        // The instanceof check does not distinguish subclasses,
        // therefore s1 instanceof Person will yield true and
        // hence we can compare Students with Persons although
        // they have different properties which is weird.
        //
        // possible solution:
        // Use the check p.getClass() == Person in the Person.equals()
        // so that subclasses of Person do not match here.
        System.out.println("true, iff a Student is instanceof Person: " + (s4 instanceof Person));
        assert !p1.equals(s5) : "a Person equals a Student only because of equal names";

        // Test criteria for hashCode:
        // If two objects are equal according to the equals(Object) method,
        // then calling the hashCode method on each of the two objects must produce the same integer result.
        assert p1.hashCode() == p2.hashCode() : "Person hashCode fail";
        assert s1.hashCode() == s2.hashCode() : "Student hashCode fail";
    }
}
