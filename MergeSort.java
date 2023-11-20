// LÓGICA:
// Se a lista tiver apenas um elemento, ela já está ordenada. Retorne a lista;
// Divide a lista ao meio;
// Ordena cada metade usando o próprio Merge Sort;
// Uma vez que as duas metades estão ordenadas, combina (ou "mescla") para formar uma única lista ordenada.

package com.mycompany.performance;

public class MergeSort {
    private long trocas = 0;
    private long iteracoes = 0;

    // Método sort agora recebe o tamanho do array como um parâmetro adicional
    public void sort(int[] array, int left, int right, int tamanho) {
        if (left < right) {
            int middle = (left + right) / 2;
            
            // Ordena a primeira metade. O tamanho é middle - left + 1
            sort(array, left, middle, middle - left + 1);
            // Ordena a segunda metade. O tamanho é right - middle
            sort(array, middle + 1, right, right - middle);
            
            // Combina as metades ordenadas
            merge(array, left, middle, right);
        }
    }

    // Método merge não precisa mais dos tamanhos dos subarrays, eles são calculados dentro do sort
    private void merge(int[] array, int left, int middle, int right) {
        // Calcula o tamanho dos dois subarrays para serem combinados
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Cria arrays temporários
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copia dados para esses arrays temporários
        for (int i = 0; i < n1; ++i)
            leftArray[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            rightArray[j] = array[middle + 1 + j];

        // Os índices iniciais das duas subseções do array
        int i = 0, j = 0;

        // O índice inicial da subseção combinada do array
        int k = left;
        while (i < n1 && j < n2) {
            iteracoes++;
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
                trocas++;
            }
            k++;
        }

        // Copia os elementos restantes de L[], se houver algum
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
            iteracoes++;
        }

        // Copia os elementos restantes de R[], se houver algum
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
            iteracoes++;
        }
    }

    // Getters para trocas e iterações
    public long getTrocas() {
        return trocas;
    }

    public long getIteracoes() {
        return iteracoes;
    }
}