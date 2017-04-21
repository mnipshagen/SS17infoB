package Blatt02.Ex02;

/**
 * A Stack that holds Strings. Works after the LIFO (Last in first out)
 * principle.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 */
public class StringStack {

   public StringStack() {
      this.first = null;
   }

    /**
     * creates a deep copy of the given StringStack
     * It iterates through all objects inside the StringStack {@param s}
     * and creates copies of it to have independent instances
     * @param s the string stack to copy
     */
   public StringStack(StringStack s) {
       this();

       StringStack tmp = new StringStack();
       while(!s.empty()) {
           tmp.push(s.pop());
       }
       while(!tmp.empty()) {
            this.push(tmp.peek());
            s.push(new String(tmp.pop())); // actual string copy with new String()!
       }
   }
   
   private StringStackEntry first;

   /**
    * Tests, whether this StringStack is empty or not.
    * 
    * @return true if this StringStack is empty, else false
    */
   public boolean empty() {
      return this.first == null;
   }

   /**
    * Returns the first element in the stack. Throws Exception when stack is
    * empty
    * 
    * @return First element or null if stack is empty
    * @throws RuntimeException
    *            if stack is empty.
    */
   public String peek() {
      if (!this.empty()) {
         return first.getString();
      } else {
         throw new RuntimeException("Stack is empty");
      }
   }

   /**
    * Deletes the first element in the stack and returns it. Throws Exception
    * when stack is empty
    * 
    * @return first element in the stack
    * @throws RuntimeException
    *            if stack is empty.
    */
   public String pop() {
      if (!this.empty()) {
         String ret = first.getString();
         this.first = this.first.getNext();
         return ret;
      } else {
         throw new RuntimeException("Stack is empty");
      }
   }

   /**
    * Puts the String s on the stack.
    * 
    * @param s
    *           String to be added.
    */
   public void push(String s) {
      if (this.empty()) {
         first = new StringStackEntry(s, null);
      } else {
         first = new StringStackEntry(s, first);
      }
   }

}
