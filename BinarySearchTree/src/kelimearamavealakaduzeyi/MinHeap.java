/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kelimearamavealakaduzeyi;

/**
 * @File KelimeAramaVeAlakaDuzeyi
 * @description: Bu sınıf kelimeleri alaka düzeyine göre sıralayıp ekrana
 * yazdırmak için tasarlandı.
 * @assignment 2. Ödev
 * @date 26.05.2020
 * @author Enes Durmuş -- enes.durmus@stu.fsm.edu.tr
 */
public class MinHeap {

    private int size;
    private NodeLinkedList[] heap;

    public MinHeap(int capacity) {
        this.heap = new NodeLinkedList[capacity];

    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void swap(int i, int j) {
        NodeLinkedList temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Ağacın içinden bize gelen düğümü heap yapımıza ekliyoruz.
    void insert(NodeLinkedList newNode) {
        if (size < heap.length) {
            heap[size] = newNode;
            int current = size++;

            while (heap[current].frekans - heap[parent(current)].frekans < 0) {
                swap(current, parent(current));
                current = parent(current);
            }

        } else {
            System.out.println("heap is full !");
        }
    }

    void heapify() {
        int lastIndex = size - 1;

        for (int i = parent(lastIndex); i >= 0; i--) {
            minHeap(i);
        }
    }

    private void minHeap(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int min = i;

        if (left < size && heap[min].frekans - (heap[left].frekans) > 0) {
            min = left;
        }
        if (right < size && heap[min].frekans - (heap[right].frekans) > 0) {
            min = right;
        }

        if (min != i) {
            swap(min, i);
            minHeap(min);
        }
    }

    // Ağacın içinde bu metodu çağırarak alaka düzeylerine göre sıralanmış olarak ekrana dosya isimlerini yazdırıyoruz.
    void printArray() {
        sıralama();
        for (NodeLinkedList element : heap) {
            System.out.print(element.dosyaIsmı + "(" + element.frekans + "), ");
        }
        System.out.println();
    }

    // TODO: print parent nodes with their left child and right child
    // 0. Seviye    Parent: 4       Left Child: 8       Right Child: 12
    // 1. Seviye    Parent: 8       Left Child: 48      Right Child: 16
    // 1. Seviye    Parent: 12      Left Child: 24      Right Child: 32
    // 2. Seviye    Parent: 48      Left Child: 54      Right Child: 72
    // 2. Seviye    Parent: 16      Left Child: 20      Right Child: -
    void print() {
        for (int i = 0; i <= parent(size - 1); i++) {
            System.out.println(i + ". Seviye    Parent: " + heap[i] + "    Left Child: " + heap[2 * i + 1] + "    Right Child: " + heap[2 * i + 2]);
        }
    }

    // heap'i frekansımıza göre düzenliyoruz.
    void sıralama() {
        int n = size;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (heap[j].frekans < heap[j + 1].frekans) {
                    swap(j, j + 1);
                }
            }
        }
    }

}
