public class RadixSort {

    public static void radixSort(int[] arr) {
        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    private static void countingSort(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int count[] = new int[5];

        for (int i = 0; i < arr.length; i++) {
            int index = (arr[i] / exp) % 10;
            count[index]++;
        }

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = arr.length - 1; i >= 0; i--) {
            int index = (arr[i] / exp) % 10;
            output[count[index] - 1] = arr[i]; 
            count[index]--;
        }

        for (int i = 0; i < arr.length; i++) arr[i] = output[i];
    }

    public static void main(String[] args) {
        int[] data = {170, 45, 75, 90, 802, 24, 2, 66};
        radixSort(data);
        for (int n : data) System.out.print(n + " ");
    }
}