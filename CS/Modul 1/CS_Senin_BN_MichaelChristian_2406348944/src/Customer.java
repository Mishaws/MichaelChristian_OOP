public class Customer {
    public String nama;
    public Kendaraan kendaraan;

    public Customer(String nama, Kendaraan kendaraan) {
        this.nama = nama;
        this.kendaraan = kendaraan;
    }

    public double getTotalPrice(){
        return kendaraan.harga;
    }

    public void showDetail(){
        System.out.println("Customer name: " + nama);
        kendaraan.showDetail();
        System.out.println("--------------------------");
        System.out.println("--------------------------");
    }
}
