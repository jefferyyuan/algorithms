package codebytes;

public class DivisionWithout {

    public static void main(String[] args) {
        double running = 0, dividend = 4.25, divisor = 2.1;
        /*
        Another test case
        
        double running = 0, dividend = 6.06, divisor = 3;
        */
        int decimalDigits = 10;
        int count = 0;
        boolean flag = true; 
        
        for (int i = 0; i < decimalDigits+1; ++i) {
            while ((running + divisor) <= dividend){
                count++;
                running += divisor;
            } 
            System.out.print(count);

            if (running == dividend) break;

            if (flag) {
                System.out.print(".");
                flag = false;
            } 
            dividend = (dividend - running) * 10; 
            
            count = 0;
            running = 0; 
        }        
    }
}