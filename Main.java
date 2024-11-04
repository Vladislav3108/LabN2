import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) {


        //Ввод размеров массива
        out.print("Введите N и M: ");
        int N = in.nextInt();
        int M = in.nextInt();


        //создание вспомогательных переменных
        int ind;
        int sum = 0;
        int min;
        int max;
        double norm;
        double temp;
        double disp = 0;


        //создание основного и вспомогательных массивов
        int[][] a = new int[N][M];
        int[] gist = new int [Math.abs(N-M)/10+2];
        double[][] sorted = new double[N][M];
        double [] med = new double [N*M];
        double[][] sr = new double[3][M];


        //Ввод элементов массива
        out.println("Введите элементы массива: ");
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                a[i][j] = in.nextInt();


        //Заполнение вспомогательного массива данными о номере столбца и среднем арифметическом значении чисел в нем
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++)
                sum += a[i][j];
            sr[0][j] = (double) sum / N;
            sr[2][j] = j;
            sum = 0;
        }


        //Добавление информации во вспомогательный массив о дисперсии в каждом столбце исходного массива
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++)
                disp += Math.pow(a[i][j] - sr[0][j], 2);
            sr[1][j] = disp / N;
            disp = 0;
        }


        //Сортировка массива sr по возрастанию значений в первой строке(средние арифметические столбцов исходного массива)
        //При их совпадении, сортировка по возрастанию значений во второй строке(дисперсия столбцов исходного массива)
        for (int i = 0; i < M - 1; i++) {
            for (int j = 0; j < M - 1 - i; j++) {
                if (sr[0][j] > sr[0][j + 1]) {
                    temp = sr[0][j];
                    sr[0][j] = sr[0][j + 1];
                    sr[0][j + 1] = temp;
                    temp = sr[1][j];
                    sr[1][j] = sr[1][j + 1];
                    sr[1][j + 1] = temp;
                    temp = sr[2][j];
                    sr[2][j] = sr[2][j + 1];
                    sr[2][j + 1] = temp;
                }
                if (sr[0][j] == sr[0][j + 1] && sr[1][j] > sr[1][j + 1]) {
                    temp = sr[0][j];
                    sr[0][j] = sr[0][j + 1];
                    sr[0][j + 1] = temp;
                    temp = sr[1][j];
                    sr[1][j] = sr[1][j + 1];
                    sr[1][j + 1] = temp;
                    temp = sr[2][j];
                    sr[2][j] = sr[2][j + 1];
                    sr[2][j + 1] = temp;
                }
            }
        }


        //Сортировка столбцов введенного массива по возрастанию среднего арифметического значения в столбце. Если средние значения совпадают, сортировка
        //по возрастанию дисперсии элементов в столбце. Выполняется на основе отсортированного вспомогательного массива
        for (int j = 0; j < M; j++) {
            ind = (int)sr[2][j];
            for (int i = 0; i < N; i++) {
                sorted[i][j] = a[i][ind];
            }
        }


        //Заполнения вспомогательного массива med всеми элементами исходного массива для нахождения медианы
        for (int i = 0, inde=0; i < N; i++)
            for (int j = 0; j < M; j++) {
                med[inde] = sorted[i][j];
                inde++;
            }


        //Сортировка массива med по возрастанию элементов
        for (int i = N*M-1; i >=1; i--){
            for (int j = 0; j < i; j++){
                if(med[j]>med[j+1]){
                    temp = med[j];
                    med[j] = med[j + 1];
                    med[j + 1] = temp;
                }
            }
        }


        //нахождение медианы элементов исходного массива на основе отсортированного массива med
        int help = N*M/2;
        if (N*M%2 != 0)
            out.print("Медиана последовательности = "+ med[help+1]);
        else
            out.print("Медиана последовательности = "+ (med[help+1]+med[help])/2);
        out.println();


        //Распределение чисел исходного массива по интервалам и нахождение количества элементов в каждом интервале
        for (int i = 0; i < gist.length; i++)
            gist[i]=0;
        min=(int)med[0]/10-1;
        max=(int)med[N*M-1]/10+1;
        for (int i = min,ind1 = 0; i < max; i++, ind1++){
            for (int j = 0; j < N*M; j++){
                if (med[j] >= 10*i && med[j] <= 10*(i+1))
                    gist[ind1] += 1;
            }
        }
        for (int i = min; i < max; i++){
            out.print("от " + i*10 + " до " + (i+1)*10 + "  ");
        }
        out.println();
        for (int i = 0; i < gist.length; i++)
            out.format("%7d    ", gist[i]);


        //нормализация и вывод исходного массива
        if (med[N*M-1]==0)
            norm=1.0;
        else
            norm=1/med[N*M-1];
        for (int j = 0; j < M; j++)
            for (int i = 0; i < N; i++)
                sorted[i][j] = sorted[i][j]*norm;
        out.println();
        out.println("Нормализованный массив:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                out.printf("%.3f", sorted[i][j]);
                out.print(" ");
            }
            out.println();
        }
    }
}
