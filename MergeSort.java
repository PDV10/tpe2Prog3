package tpe2;

import java.util.ArrayList;

public class MergeSort {

    public ArrayList<Arco<Integer>> numbers;
    public ArrayList<Arco<Integer>> helper;
    public int size;

    public MergeSort(ArrayList<Arco<Integer>>arr) {
        this.numbers = arr;
        this.size = 0;
        sort(arr);
    }

    public void sort(ArrayList<Arco<Integer>> values) {
        this.numbers = values;
        size = values.size();
        this.helper = new ArrayList<Arco<Integer>>(values);
        mergesort(0, size - 1);
    }

    private void mergesort(int low, int high) {
        if (low < high) {
            int middle = (low + high) / 2;
            mergesort(low, middle);
            mergesort(middle + 1, high);
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            helper.set(i,numbers.get(i));
        }
        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high) {
            if (helper.get(i).getEtiqueta() <= helper.get(j).getEtiqueta()) {
                numbers.set(k,helper.get(i));
                i++;
            } else {
                numbers.set(k,helper.get(j));
                j++;
            }
            k++;
        }

        while (i <= middle) {
            numbers.set(k,helper.get(i));
            k++;
            i++;
        }
        while (j <= high) {
            numbers.set(k,helper.get(j));
            k++;
            j++;
        }

    }

}