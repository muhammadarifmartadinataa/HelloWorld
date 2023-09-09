import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChalangeChapter1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<String, Integer> pesanan = new HashMap<>();
        int totalHarga = 0;

        while (true) {
            tampilkanMenuUtama();
            int pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    pesanMakanan("Nasi Goreng", 15000, pesanan, input);
                    break;
                case 2:
                    pesanMakanan("Mie Goreng", 13000, pesanan, input);
                    break;
                case 3:
                    pesanMakanan("Nasi + Ayam", 18000, pesanan, input);
                    break;
                case 4:
                    pesanMakanan("Es Teh Manis", 3000, pesanan, input);
                    break;
                case 5:
                    pesanMakanan("Es Jeruk", 5000, pesanan, input);
                    break;
                case 99:
                    if (!pesanan.isEmpty()) {
                        tampilkanKonfirmasi(pesanan);
                        totalHarga = hitungTotalHarga(pesanan);
                        int menuPembayaran = menuPembayaran(input, totalHarga);
                        if (menuPembayaran == 1) {
                            buatStruk(pesanan, totalHarga);
                            System.out.println("Simpan struk ini sebagai bukti pembayaran");
                        } else if (menuPembayaran == 0) {
                            System.out.println("Terima kasih. Selamat tinggal!");
                            System.exit(0);
                        }
                    } else {
                        System.out.println("Anda belum memesan apa-apa.");
                    }
                    break;
                case 0:
                    System.out.println("Terima kasih. Selamat tinggal!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                    break;
            }
        }
    }

    private static void tampilkanMenuUtama() {
        System.out.println("====================");
        System.out.println("Selamat Datang di BinarFud");
        System.out.println("====================");
        System.out.println("1. Nasi Goreng | 15.000");
        System.out.println("2. Mie Goreng |  13.000");
        System.out.println("3. Nasi + Ayam |  18.000");
        System.out.println("4. Es Teh Manis | 3.000");
        System.out.println("5. Es Jeruk | 5.000");
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar Aplikasi");
        System.out.print("=> ");
    }

    private static void pesanMakanan(String namaMakanan, int harga, Map<String, Integer> pesanan, Scanner input) {
        System.out.println("====================");
        System.out.println("Berapa pesanan " + namaMakanan + " anda?");
        System.out.println("====================");
        System.out.println("1. " + namaMakanan + " | " + harga);
        System.out.println("0. Kembali");
        System.out.print("qty => ");
        int qty = input.nextInt();
        if (qty > 0) {
            pesanan.put(namaMakanan, qty);
        }
    }

    private static void tampilkanKonfirmasi(Map<String, Integer> pesanan) {
        System.out.println("====================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("====================");
        int totalHarga = 0;
        for (Map.Entry<String, Integer> entry : pesanan.entrySet()) {
            String namaMakanan = entry.getKey();
            int qty = entry.getValue();
            int harga = qty * hitungHargaMakanan(namaMakanan);
            System.out.println(namaMakanan + "  " + qty + "  " + harga);
            totalHarga += harga;
        }
        System.out.println("---------------------- +");
        System.out.println("Total       " + pesanan.size() + "      " + totalHarga);
    }

    private static int hitungHargaMakanan(String namaMakanan) {
        int harga = 0;
        switch (namaMakanan) {
            case "Nasi Goreng":
                harga = 15000;
                break;
            case "Mie Goreng":
                harga = 13000;
                break;
            case "Nasi + Ayam":
                harga = 18000;
                break;
            case "Es Teh Manis":
                harga = 3000;
                break;
            case "Es Jeruk":
                harga = 5000;
                break;
        }
        return harga;
    }

    private static int hitungTotalHarga(Map<String, Integer> pesanan) {
        int totalHarga = 0;
        for (Map.Entry<String, Integer> entry : pesanan.entrySet()) {
            String namaMakanan = entry.getKey();
            totalHarga += entry.getValue() * hitungHargaMakanan(namaMakanan);
        }
        return totalHarga;
    }

    private static int menuPembayaran(Scanner input, int totalHarga) {
        while (true) {
            System.out.println("====================");
            System.out.println("1. Konfirmasi dan Bayar");
            System.out.println("2. Kembali ke Menu Utama");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("=> ");
            int menuPembayaran = input.nextInt();
            switch (menuPembayaran) {
                case 1:
                    System.out.println("Pembayaran: Binar Cash");
                    return 1;
                case 2:
                    return 2;
                case 0:
                    return 0;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }

    private static void buatStruk(Map<String, Integer> pesanan, int totalHarga) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("struk_pembayaran.txt"));
            writer.println("====================");
            writer.println("BinarFud");
            writer.println("====================");
            writer.println("Terima kasih sudah memesan di BinarFud");
            writer.println("Dibawah ini adalah pesanan anda");
            for (Map.Entry<String, Integer> entry : pesanan.entrySet()) {
                String namaMakanan = entry.getKey();
                int qty = entry.getValue();
                int harga = qty * hitungHargaMakanan(namaMakanan);
                writer.println(namaMakanan + "  " + qty + "  " + harga);
            }
            writer.println("---------------------- +");
            writer.println("Total       " + pesanan.size() + "      " + totalHarga);
            writer.println("Pembayaran : Binar Cash");
            writer.println("===================");
            writer.println("Simpan struk ini sebagai bukti pembayaran");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
