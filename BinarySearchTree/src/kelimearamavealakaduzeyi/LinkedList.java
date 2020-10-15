/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kelimearamavealakaduzeyi;

/**
 * @File KelimeAramaVeAlakaDuzeyi
 * @description: Bu sınıf kelimelerin hangi dosyada ve kaç tane olduğunu tutmak
 * için tasarlandı.
 * @assignment 2. Ödev
 * @date 26.05.2020
 * @author Enes Durmuş -- enes.durmus@stu.fsm.edu.tr
 */
public class LinkedList {

    public NodeLinkedList head;

    //Ağacın içinde bu metodu çağırıyoruz uygun kontroller yapıldıktan sonra frekanslar artırılıyor yada yeni düğüm ekleniyor.
    void add(NodeLinkedList newNode) {
        if (isEmpty()) {
            head = newNode;
        } else {
            NodeLinkedList temp = head;
            // Bütün bağlantılı listeyi dönüyoruz eğer dışardan gelen data bizim datamıza eşit ise frekansı artırıyoruz.
            // Değilse while den çıkıyoruz son bir kontrol daha yapıyoruz oraya da girmez ise yeni düğümümüzü ekliyoruz.
            while (temp.nextNode != null) {

                if (temp.dosyaIsmı.equals(newNode.dosyaIsmı)) {

                    temp.frekans += newNode.frekans;
                    return;
                }

                temp = temp.nextNode;
            }

            if (temp.dosyaIsmı.equals(newNode.dosyaIsmı)) {
                temp.frekans += newNode.frekans;
                return;
            }

            temp.nextNode = newNode;
        }
    }

    void add(String newData) {
        add(new NodeLinkedList(newData));
    }

    void print() {
        NodeLinkedList temp = head;

        while (temp != null) {
            System.out.print(temp.dosyaIsmı + ": " + temp.frekans + " -> ");
            temp = temp.nextNode;
        }

        System.out.println("null");
    }

    boolean isEmpty() {
        return head == null;
    }

    int size() {
        NodeLinkedList temp = head;
        int count = 0;

        while (temp != null) {
            count++;
            temp = temp.nextNode;
        }

        return count;
    }
}
