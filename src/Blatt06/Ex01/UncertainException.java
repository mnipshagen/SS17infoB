package Blatt06.Ex01;

import java.io.IOException;

public class UncertainException {

    private int i = 0;

    public static void uncertain(int number) {
        UncertainException uncertain = new UncertainException();
        int result = 0;

        try {
            System.out.println("uncertain" + number + "()");
            switch (number) {
                case 1:
                    result = uncertain.uncertain1();
                    break;
                case 2:
                    result = uncertain.uncertain2();
                    break;
                case 3:
                    result = uncertain.uncertain3();
                    break;
                case 4:
                    result = uncertain.uncertain4();
                    break;
                case 5:
                    result = uncertain.uncertain5();
                    break;
                case 6:
                    result = uncertain.uncertain6();
                    break;
                case 7:
                    result = uncertain.uncertain7();
                    break;
                case 8:
                    result = uncertain.uncertain8();
                    break;
                case 9:
                    result = uncertain.uncertain9();
                    break;
                case 10:
                    result = uncertain.uncertain10();
                    break;
            }
            System.out.println("result = " + result + ", i = " + uncertain.i);
        } catch (Exception e) {
            System.out.println("i = " + uncertain.i + " Exception (" + e.getClass()
                    .getName() + ")");
        } finally {
        }
    }

    public static void main(String args[]) {
        for (int i = 1; i <= 10; ++i) {
            uncertain(i);
        }
    }

    public int uncertain1(){
        try {
            throw new RuntimeException(); // unchecked...
        } catch (RuntimeException e) {    // but caught
            i++;                            // i = 1
            throw new ClassCastException(); // unchecked (does not have to be declared using throws)
        } finally { // we always go in here...
            i++;                            // i = 2
            throw new NumberFormatException(); // unchecked, caught in uncertain
        }
        // does not complain about missing return because
        // it is guaranteed to throw something instead:
        // both NumberFormatException and ClassCastException
    }

    public int uncertain2() {
        for (; ; ) { // infinite loop
            try {
                break; // does not throw anything
            } catch (RuntimeException e) { // hence we don't go here
                i++;
            } finally { // even though the break we go here
                i++; // i = 1
                throw new RuntimeException(); // unchecked, caught in uncertain
            }
        }
        /* return i++; // unreachable statement*/

        // does not complain about missing return because
        // it is guaranteed to throw something instead:
        // RuntimeException (from the finally)
    }

    public int uncertain3() {
        do {
            try {
                throw new RuntimeException();
            } catch (RuntimeException e) { // guaranteed to be entered
                i++; // i = 1
                continue; // jump to the loop footer (condition)
            } finally { // but first do the stuff in here..
                i++; // i = 2
            }
        } while (false); // break after first loop
        return i++; // result = 2 because post-increment, i = 3
    }

    public int uncertain4() {
        try {
            return i++; // i = 2, result = 0
        } catch (RuntimeException e) { // won't go in here
            i++;
        } finally { // but does go here before return
            i++; // i = 1
        }
        return i++; // will not be reached because of return in try
    }

    public int uncertain5() {
        try {
            return i; // i = 0
        } finally {
            throw new RuntimeException(); // throws unchecked, return does not exit, no result
        }
    }

    public int uncertain6() {
        try {
            throw new RuntimeException(); // return is first exit, so no exception
        } finally {
            return ++i; // i = 1 = result (pre-increment)
        }
    }

    public int uncertain7() {
        try {
            throw new java.io.IOException(); // checked exception, not thrown because of return in finally
        } catch (RuntimeException e) { // not entered
            i++;
        } finally {
            return i++; // result = 0, i = 1
        }
    }

    public int uncertain8() {
        try {
            throw new NumberFormatException(); // subclass of RuntimeException
        } catch (RuntimeException e) { // therefore caught here
            i++; // i = 1
            throw new RuntimeException(); // throw unchecked, no result
        } finally {
            i++; // i = 2
        }

        // does not complain about missing return because
        // it is guaranteed to throw something instead:
        // RuntimeException (from the catch)
    }

    public int uncertain9() {
        try {
            throw new ClassCastException(); // subclass of RuntimeException
        } catch (RuntimeException e) { // therefore caught here
            return i++; // i = 1, this return is overridden by the next one
        } finally {
            return i++; // result = 1 (post-increment), i = 2
        }
    }

    public int uncertain10() {
        try {
            throw new java.io.IOException(); // not subclass of RuntimeError, checked!
        } catch (RuntimeException e) { // hence: unhandled exception compiler error
        } catch (IOException ignored) { } // had to be added (alternatively throws IOException)
        return 1; // result = 1, i = 0
    }
}