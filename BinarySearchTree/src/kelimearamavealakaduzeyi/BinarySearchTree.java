/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kelimearamavealakaduzeyi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 * @File KelimeAramaVeAlakaDuzeyi
 * @description: Bu sınıfta kullanıca bir ekran göstertip oradan bir klasör
 * seçtirtiyoruz içinde bulunan dosyalardan bütün kelimeleri okuyup ikili arama
 * ağacımıza ekliyoruz. İçerisinde yazdığımız sorgu metodu bize sıralanmış bir
 * şekilde girilen kelimelerin hangi dosyada kaç kere geçtiğini gösteriyor.
 * @assignment 2. Ödev
 * @date 26.05.2020
 * @author Enes Durmuş -- enes.durmus@stu.fsm.edu.tr
 */
public class BinarySearchTree {

    private NodeTree root;

    public BinarySearchTree() throws IOException {

        // Dosya yolunu kullanıcıya seçtirtiyoruz.
        JButton open = new JButton();
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(""));
        fc.setDialogTitle("Dosya Yolunu Seçiniz ...");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {

        }

        // Seçtiğiniz klasörün bilgisayarınızdaki yolunu buluyoruz ve bu yoldaki dosyayı file yapıp Klasor adlı değişkene atıyoruz.
        File Klasor = new File(fc.getSelectedFile().getAbsolutePath());
        // Klasor içindeki bütün dosyaları dosyalar adlı listeye atıyoruz.
        File[] dosyalar = Klasor.listFiles();

        try {
            // Her dosya için yazmış olduğum readFile metodunu çağırıyoruz.
            for (int i = 0; i < dosyalar.length; i++) {

                readFile(dosyalar[i], dosyalar[i].getName());
            }

        } catch (IOException e) {

        }
    }

    // Göndermiş olduğumuz dosyadaki bütün harfleri tek tek okuyor ve bunları kelimelere çeviriyor
    // Kelimeye çevirdikten sonra binarySearchTree' nin insert metodunu çağırarak bst ye ekliyoruz.
    void readFile(File file, String dosyaIsmı) throws FileNotFoundException, IOException {
        FileReader inputStream = new FileReader(file);
        int character;

        String kelime = "";
        while ((character = inputStream.read()) != -1) {
            // burada karakterin harf olup olmadığını buluyoruz büyük harfede duyarlı,
            if ((character >= 97 && character <= 122) || (character >= 65 && character <= 90)) {
                // kelime isimle stringe karakterleri tek tek ekliyoruz.
                kelime += (char) character;
            }
            // boşluk, virgül ve nokta işareti geldiyse kelimemiz bitmiş demektir. insert metodunu çağırmamız lazım.
            if (character == 32 || character == 44 || character == 46) {
                // eğer virgülden veya noktadan sonra boşluk gelirse kelimemiz sadece 1 karakter oluyor bunu engellemek için bu kontrolü yapıyoruz.
                if (!(kelime.equalsIgnoreCase("") || kelime.equalsIgnoreCase(",") || kelime.equalsIgnoreCase("."))) {// Ard arda boşluk kelime veya virgül gelirse kelimemiz 
                    // insert metodunu çağırıyoruz kelimenin lower case halini ve dosya ismini gönderiyoruz.
                    insert(kelime.toLowerCase(), dosyaIsmı);
                    // kelimeyi sıfırlıyoruz.
                    kelime = "";
                }
            }
        }
    }

    // Labda yazılmış olan insert metodunun üzerine yazılmıştır.
    // Gelen kelimeleri kontrol ediyoruz eğer daha önce eklenmediyse ağaçta nereye ekleyeceğimizi buluyoruz.
    // Daha önce eklendiyse sadece frekansı artıcak. Bütün kelimeler için kelimelerin linkedList sınıfından "add" metodunu çağırıyoruz gerekli kontroller 
    // add metodunun içinde yazılmış durumda.
    void insert(String newData, String dosyaIsmı) {
        // yeni düğümümüzü oluşturuyoruz.
        NodeTree newNode = new NodeTree(newData);

        // ağaç boş ise direk köke ekliyoruz.
        if (isEmpty()) {
            newNode.linkedList.add(dosyaIsmı);
            root = newNode;
        } else {
            //Ağaç boş değil ise ağacın dallarındaki data ile yeni gelen datamızı karşılaştırarak uygun yere ekleme yapıyoruz.
            NodeTree temp = root;

            while (temp != null) {

                if (newData.compareTo(temp.data) == 0) {
                    temp.linkedList.add(dosyaIsmı);
                    return;
                } else if (newData.compareTo(temp.data) < 0) {
                    if (temp.leftChild == null) {
                        newNode.linkedList.add(dosyaIsmı);
                        temp.leftChild = newNode;
                        return;
                    }
                    temp = temp.leftChild;
                } else if (newData.compareTo(temp.data) > 0) {
                    if (temp.rightChild == null) {
                        newNode.linkedList.add(dosyaIsmı);
                        temp.rightChild = newNode;
                        return;
                    }
                    temp = temp.rightChild;
                } else {
                    return;
                }
            }
        }
    }

    // Kullanıcının girmiş olduğu kelimeleri uygun kontrolleri yaparak hangi dosyada kaç kere geçtiğini ekrana yazdırıyoruz.
    // Hangi dosyada en çok yazıldıysa ilk onu yazdırıyoruz ve böyle sıralanarak gidiyor.
    void sorgu(String kelimeler) {
        // Bağlantılı bir liste oluşturuyoruz buraya girilen her kelimenin linkedList özelliğini kopyalıyacağız.
        LinkedList liste = new LinkedList();

        //Kelimeleri ayırıyoruz.
        for (String kelime : kelimeler.split(" ")) {
            NodeTree temp = root;

            // Bütün ağacı tarıyoruz ve ayırdığımız kelimenin nerede olduğunu arıyoruz.
            while (temp != null) {
                if (kelime.compareTo(temp.data) > 0) {
                    temp = temp.rightChild;
                } else if (kelime.compareTo(temp.data) < 0) {
                    temp = temp.leftChild;
                } else if (kelime.compareTo(temp.data) == 0) {
                    // kelime data ile eşleştiğinde bir düğüm oluşturup kelimenin linkedListini bu düğüme aktarıyoruz.
                    NodeLinkedList head = temp.linkedList.head;
                    // kelimenin bütün bilgilerini oluşturduğumuz düğüme ekliyoruz sonra ise bu düğümü en yukarda oluşturduğumuz linkedListe' sine ekliyoruz.
                    while (head != null) {

                        NodeLinkedList node = new NodeLinkedList(head.dosyaIsmı);
                        node.frekans = head.frekans;
                        liste.add(node);
                        head = head.nextNode;
                    }
                    break;
                }

            }

        }

        // Oluşturduğumuz liste boyutunda heap yapısını oluşturuyoruz.
        MinHeap heap = new MinHeap(liste.size());
        // Listemizi temp bir düğüme ekliyoruz bu düğümü dönerek bütün elemanları heap yapısına atacağız.
        NodeLinkedList temp = liste.head;
        // temp' in bütün özelliklerine yeni bir düğüme atayıp onu heap yapısına ekliyoruz.
        while (temp != null) {
            NodeLinkedList newNode = new NodeLinkedList(temp.dosyaIsmı);
            newNode.frekans = temp.frekans;
            heap.insert(newNode);
            temp = temp.nextNode;
        }
        // Oluşturduğumuz heap yapısından metodu çağırıp ekrana yazdırıyoruz.
        heap.printArray();
    }

//-------------------------------------------------------------------------//
// recursive insert method
    private NodeTree insertRecursive(NodeTree node, String newData) {
        if (node == null) {
            return new NodeTree(newData);
        } else {
            if (newData.compareTo(node.data) > 0) {
                node.rightChild = insertRecursive(node.rightChild, newData);
            } else if (newData.compareTo(node.data) < 0) {
                node.leftChild = insertRecursive(node.leftChild, newData);
            }

            return node;
        }
    }

    // iterative search method
    boolean search(String searchData) {
        if (isEmpty()) {
            System.out.println("BinarySearchTree is empty !");
        } else {
            NodeTree temp = root;

            while (temp != null) {
                if (searchData.compareTo(temp.data) > 0) {
                    temp = temp.rightChild;
                } else if (searchData.compareTo(temp.data) < 0) {
                    temp = temp.leftChild;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    void preorder() {
        System.out.print("preorder : ");
        preorder(root);
        System.out.println();
    }

    private void preorder(NodeTree node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorder(node.leftChild);
            preorder(node.rightChild);
        }
    }

    void inorder() {
        System.out.print("inorder : ");
        inorder(root);
        System.out.println();
    }

    private void inorder(NodeTree node) {
        if (node != null) {
            inorder(node.leftChild);
            System.out.print(node.data + " ");
            inorder(node.rightChild);
        }
    }

    void postorder() {
        System.out.print("postorder : ");
        postorder(root);
        System.out.println();
    }

    private void postorder(NodeTree node) {
        if (node != null) {
            postorder(node.leftChild);
            postorder(node.rightChild);
            System.out.print(node.data + " ");
        }
    }

    private boolean isEmpty() {
        return root == null;
    }

    // TODO: int sumRecursive
    // TODO: int sizeRecursive
    int sizeRecursive() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(NodeTree node) {
        // implement recursive method to find size
        if (node == null) {
            return 0;
        } else {
            return 1 + sizeRecursive(node.leftChild) + sizeRecursive(node.rightChild);
        }
    }

    // TODO: int fullNodeCountRecursive (node with both left and right child)
    int fullNodeCountRecursive() {
        return fullNodeCountRecursive(root);
    }

    private int fullNodeCountRecursive(NodeTree node) {
        // implement recursive method to find full node count
        if (node == null) {
            return 0;
        } else {
            if (node.leftChild != null && node.rightChild != null) {
                return 1 + fullNodeCountRecursive(node.leftChild) + fullNodeCountRecursive(node.rightChild);
            } else {
                return 0 + fullNodeCountRecursive(node.leftChild) + fullNodeCountRecursive(node.rightChild);
            }
        }
    }

    String findMaxRecursive() {
        return findMaxRecursive(root);
    }

    // BST, max element located at right most side of the tree
    private String findMaxRecursive(NodeTree node) {
        if (node.rightChild != null) {
            return findMaxRecursive(node.rightChild);
        }

        return node.data;
    }

    int height() {
        return height(root);
    }

    // find height of the tree, recursive
    private int height(NodeTree node) {
        if (node == null) {
            return -1;
        } else {
            return 1 + Math.max(height(node.leftChild), height(node.rightChild));
        }
    }
}
