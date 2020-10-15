/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kelimearamavealakaduzeyi;

/**
 * @File KelimeAramaVeAlakaDuzeyi
 * @description: Bağlantılı listeyi oluşturmak için gerekli olan düğüm
 * sınıfımız.
 * @assignment 2. Ödev
 * @date 26.05.2020
 * @author Enes Durmuş -- enes.durmus@stu.fsm.edu.tr
 */
public class NodeLinkedList {

    String dosyaIsmı;
    int frekans = 1;
    NodeLinkedList nextNode;

    public NodeLinkedList(String dosyaIsmı) {
        this.dosyaIsmı = dosyaIsmı;
    }

}
