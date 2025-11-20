import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RadixSort {

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        int max = getMax(arr);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max) max = arr[i];
        return max;
    }

    private static void countingSort(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int[] count = new int[10];

        for (int value : arr) {
            int index = (value / exp) % 10;
            count[index]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            int index = (arr[i] / exp) % 10;
            output[count[index] - 1] = arr[i];
            count[index]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    private static int[] readNumbersFromFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        ArrayList<Integer> numbers = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) {
            line = line.replace(",", " ");
            String[] parts = line.trim().split("\\s+");
            for (String p : parts) {
                if (!p.isEmpty()) numbers.add(Integer.parseInt(p));
            }
        }
        br.close();

        int[] arr = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) arr[i] = numbers.get(i);

        return arr;
    }

    private static void printArray(int[] arr) {
        for (int v : arr) System.out.print(v + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        String ruta = "C://archivos//numeros.txt";

        try {
            int[] data = readNumbersFromFile(ruta);

            System.out.println("Datos originales:");
            printArray(data);

            radixSort(data);

            System.out.println("\nDatos ordenados (ascendente):");
            printArray(data);

        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
    }
}
