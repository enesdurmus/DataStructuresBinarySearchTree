/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kelimearamavealakaduzeyi;

/**
 * @File KelimeAramaVeAlakaDuzeyi
 * @description: İkili arama ağacını oluşturmak için gerekli olan düğüm
 * sınıfımızı oluşturuyoruz.
 * @assignment 2. Ödev
 * @date 26.05.2020
 * @author Enes Durmuş -- enes.durmus@stu.fsm.edu.tr
 */
public class NodeTree {

    String data;
    NodeTree leftChild;
    NodeTree rightChild;
    LinkedList linkedList;

    public NodeTree(String data) {
        this.data = data;
        linkedList = new LinkedList();
    }

    @Override
    public String toString() {
        return data + "";
    }
}
