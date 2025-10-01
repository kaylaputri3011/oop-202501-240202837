class Mahasiswa {
    String nama;
    String nim; // Menggunakan String untuk NIM

    // Konstruktor dengan nama dan NIM
    Mahasiswa(String n, String ni){
        nama = n;
        nim = ni;
    }

    // Metode sapa
    void sapa(){
        System.out.println("Halo, nama saya " + nama + " dengan NIM " + nim + ".");
    }
}
public class helloOOP {
    public static void main(String[] args) {
        // Membuat objek Mahasiswa
        Mahasiswa m = new Mahasiswa("Kayla", "240202837"); // Mengubah 'kayla' menjadi 'Kayla' agar lebih formal

        // Memanggil metode sapa
        m.sapa();
    }
    
}
