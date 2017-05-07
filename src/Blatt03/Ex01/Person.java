package Blatt03.Ex01;

/**
 * Class representing a Blatt03.Ex01.Person.
 *
 * @author Mathias Menninghaus
 */
public class Person {

   /**
    * The name of this Blatt03.Ex01.Person.
    */
   private String name;

   /**
    * Constructor setting the name of this Blatt03.Ex01.Person.
    *
    * @param name the name of this Blatt03.Ex01.Person
    */
   public Person(String name) {
      this.name = name;
   }

   /**
    * Returns the name of this Blatt03.Ex01.Person.
    *
    * @return the name of this Blatt03.Ex01.Person
    */
   public String getName() {
      return this.name;
   }

   /**
    * A Blatt03.Ex01.Person can only be equal to another Blatt03.Ex01.Person with exactly the same name.
    *
    * @param o Object to be compared with
    * @return if this Blatt03.Ex01.Person is equal to o
    */
   public boolean equals(Object o) {

      // null-check
      if (o == null) {
         return false;
      }

      // reflexiveness
      if (o == this) {
         return true;
      }

      // transitive and symmetric because equals for Strings is such
      if (o instanceof Person) {
         return this.name.equals(((Person) o).getName());
      }

      return false;
   }

   /**
    * Produces the hash Code for this Blatt03.Ex01.Person. Which simply is the hashCode of
    * its name.
    *
    * @return hashCode of name
    */
   public int hashCode() {
      return this.getName().hashCode();
   }
}
