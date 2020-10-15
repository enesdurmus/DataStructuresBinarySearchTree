/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kelimearamavealakaduzeyi;

import java.io.IOException;

/**
 * @File KelimeAramaVeAlakaDuzeyi
 * @description: yazdığımız ağaç yapısı oluşturuyoruz bize gelen ekrandan okumak
 * istediğimix dosyaların bulunduğu klasörü seçiyoruz ve sorgu metodunu
 * kullanarak istediğimiz hangi dosyada olduğunu alaka düzeyine göre sırayla
 * görüyoruz.
 * @assignment 2. Ödev
 * @date 26.05.2020
 * @author Enes Durmuş -- enes.durmus@stu.fsm.edu.tr
 */
public class Test {

    public static void main(String[] args) throws IOException {
        BinarySearchTree bst = new BinarySearchTree();
        bst.sorgu("data");
        bst.sorgu("data structures");
        bst.sorgu("binary data");
    }
}
