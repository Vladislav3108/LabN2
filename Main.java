import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    // Объявляем объект класса Scanner для ввода данных
    public static Scanner in = new Scanner(System.in);
    // Объявляем объект класса PrintStream для вывода данных
    public static PrintStream out = System.out;
    public static void main(String[] args) {


        //Ввод размеров массива
        out.print("Введите N и M: ");
        //Считываем число строк
        int N = in.nextInt();
        //Считываем число столбцов
        int M = in.nextInt();
        //Проверяем входные данные, если
        //ввели пустой массив сообщаем об этом и завершаем программу.
        if (N==0 || M==0){
            out.print("Вы ввели пустой массив");
            System.exit(0);
        }


        //создание вспомогательных переменных
        int ind;
        double sum = 0;
        int min;
        int max;
        double norm;
        double temp;
        double disp = 0;


        //создание основного и вспомогательных массивов
        double[][] a = new double[N][M];
        int[] gist = new int [Math.abs(N-M)/10+2];
        double[][] sorted = new double[N][M];
        double [] med = new double [N*M];
        double[][] sr = new double[3][M];


        //Ввод элементов массива
        out.println("Введите элементы массива: ");
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                a[i][j] = in.nextDouble();


        //Заполнение вспомогательного массива данными о номере столбца и среднем арифметическом значении чисел в нем
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++)
                //Считаем сумму элементов в столбце
                sum += a[i][j];
            //Записываем значение среднего арифметического в столбце
            sr[0][j] = sum / N;
            sr[2][j] = j;
            sum = 0;
        }


        //Добавление информации во вспомогательный массив о дисперсии в каждом столбце исходного массива
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++)
                //Считаем дисперсию столбца
                disp += Math.pow(a[i][j] - sr[0][j], 2);
            //Записываем значение дисперсии в столбце
            sr[1][j] = disp / N;
            disp = 0;
        }


        //Сортировка массива sr по возрастанию значений в первой строке(средние арифметические столбцов исходного массива)
        //При их совпадении, сортировка по возрастанию значений во второй строке(дисперсия столбцов исходного массива)
        for (int i = 0; i < M - 1; i++) {
            for (int j = 0; j < M - 1 - i; j++) {
                //Если среднее арифметическое в одном столбце больше,
                // чем в другом, переставляем элементы массива местами
                if (sr[0][j] > sr[0][j + 1]) {
                    //Переставляем два элемента массива местами в первой строке
                    temp = sr[0][j];
                    sr[0][j] = sr[0][j + 1];
                    sr[0][j + 1] = temp;
                    //Переставляем два элемента массива местами во второй строке
                    temp = sr[1][j];
                    sr[1][j] = sr[1][j + 1];
                    sr[1][j + 1] = temp;
                    //Переставляем два элемента массива местами в третьей строке
                    temp = sr[2][j];
                    sr[2][j] = sr[2][j + 1];
                    sr[2][j + 1] = temp;
                }
                //Если среднее арифметическое в одном столбце равно среднему
                // арифметическому в другом и дисперсия в одном больше,
                // чем в другом, переставляем элементы массива местами
                if (sr[0][j] == sr[0][j + 1] && sr[1][j] > sr[1][j + 1]) {
                    //Переставляем два элемента массива местами в первой строке
                    temp = sr[0][j];
                    sr[0][j] = sr[0][j + 1];
                    sr[0][j + 1] = temp;
                    //Переставляем два элемента массива местами во второй строке
                    temp = sr[1][j];
                    sr[1][j] = sr[1][j + 1];
                    sr[1][j + 1] = temp;
                    //Переставляем два элемента массива местами в третьей строке
                    temp = sr[2][j];
                    sr[2][j] = sr[2][j + 1];
                    sr[2][j + 1] = temp;
                }
            }
        }


        //Сортировка столбцов введенного массива по возрастанию среднего арифметического значения в столбце.
        // Если средние значения совпадают, сортировка по возрастанию дисперсии элементов в столбце.
        // Выполняется на основе отсортированного вспомогательного массива
        for (int j = 0; j < M; j++) {
            ind = (int)sr[2][j];
            for (int i = 0; i < N; i++) {
                sorted[i][j] = a[i][ind];
            }
        }


        //Заполнения вспомогательного массива med всеми элементами
        //исходного массива для нахождения медианы
        for (int i = 0, inde=0; i < N; i++)
            for (int j = 0; j < M; j++) {
                med[inde] = sorted[i][j];
                inde++;
            }


        //Сортировка массива med по возрастанию элементов
        for (int i = N*M-1; i >=1; i--){
            for (int j = 0; j < i; j++){
                //Если один элемент массива больше другого,
                //меняем их местами
                if(med[j]>med[j+1]){
                    temp = med[j];
                    med[j] = med[j + 1];
                    med[j + 1] = temp;
                }
            }
        }


        //нахождение медианы элементов исходного массива на основе отсортированного массива med
        int help = N*M/2;
        //Если количество элементов в массиве нечетное, то выводим средний элемент
        if (N*M%2 != 0)
            out.print("Медиана последовательности = "+ med[help]);
        else
            //ЕСли четное, то выводим среднее арифметическое двух центральных элементов
            out.print("Медиана последовательности = "+ (med[help+1]+med[help])/2);
        out.println();


        //Распределение чисел исходного массива по интервалам и нахождение количества элементов в каждом интервале
        for (int i = 0; i < gist.length; i++)
            gist[i]=0;
        //Нижняя граница общего интервала
        min=(int)med[0]/10-1;
        //Верхняя граница общего интервала
        max=(int)med[N*M-1]/10+1;
        for (int i = min,ind1 = 0; i < max; i++, ind1++){
            for (int j = 0; j < N*M; j++){
                //Подсчет количества элементов исходного массива в каждом интервале
                if (med[j] >= 10*i && med[j] < 10*(i+1))
                    gist[ind1] += 1;
            }
        }
        //Вывод данных гистрограммы. Сначала интервал, а под ним количество элементов в интервале
        for (int i = min; i < max; i++){
            out.print("от " + i*10 + " до " + (i+1)*10 + "  ");
        }
        out.println();
        for (int i = 0; i < gist.length; i++)
            out.format("%7d    ", gist[i]);


        //нормализация и вывод отсортированного исходного массива
        //Задаём нормализующий элемент в зависимости от максимального
        //элемента исходного массива
        if (med[N*M-1]==0)
            norm=1.0;
        else
            norm=1/med[N*M-1];
        for (int j = 0; j < M; j++)
            for (int i = 0; i < N; i++)
                //Приводим отсортированный исходный массив к
                //интервалу от 0 до 1
                sorted[i][j] = sorted[i][j]*norm;
        out.println();
        out.println("Нормализованный массив:");
        //Выводим нормализованный массив
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                out.printf("%.3f", sorted[i][j]);
                out.print(" ");
            }
            out.println();
        }
    }
}
