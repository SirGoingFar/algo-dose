package interviews.amex;

/*
* Print numbers from 1 to 100 with the following exceptions:
* - If divisible by 3, print 'Fizz' instead of the number
* - If divisible by 5, print 'Buzz' instead of the number
* - If divisible by 3 and 5, print 'FizzBuzz' instead of the number
* */
public class Amex {

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println(i + " - FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println(i + " - Fizz");
            } else if (i % 5 == 0) {
                System.out.println(i + " - Buzz");
            } else {
                System.out.println(i);
            }
        }
    }

}
