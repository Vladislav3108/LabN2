import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) {
        int n = in.nextInt();
        out.println("Value | Square");
        out.println("------+--------");
        for (int i=1;i<2*n;i+=2){
            if (i/10>0 && i/10<10){
                if ((i*i*i)/100>0 && (i*i*i)/100<10)
                    out.println(" "+i+"   |   "+(i*i*i));
                if ((i*i*i)/1000>0 && (i*i*i)/1000<10)
                    out.println(" "+i+"   |   "+(i*i));
            }
            if (i/10>0 && i/10<10){
                if ((i*i*i)/1000>0 && (i*i*i)/1000<10)
                    out.println(" "+i+"   |  "+(i*i*i));
                if ((i*i*i)/1000>0 && (i*i*i)/1000<10)
                    out.println(" "+i+"   |  "+(i*i*i));

            }
        }
    }
}