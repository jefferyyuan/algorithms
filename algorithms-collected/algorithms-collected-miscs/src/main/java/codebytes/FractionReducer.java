package codebytes;
import java.util.Scanner;

public class FractionReducer{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter numerator: ");
        int num = scan.nextInt();
        System.out.println("Enter denominator: ");
        int den = scan.nextInt();

        int smaller = num < den ? num : den;
        int HCF = -1;
        for (int i = smaller; i > 0; --i) {
            if (num%i==0&&den%i==0) {
                HCF = i;
                System.out.println("Reduced form: "+(num/HCF)+"/"+(den/HCF));
                break;
            }
        }
    }
}