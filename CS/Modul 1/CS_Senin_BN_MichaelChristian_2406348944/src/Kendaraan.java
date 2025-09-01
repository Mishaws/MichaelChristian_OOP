public class Kendaraan {
    public String brand;
    public int year;
    public VehicleType type;
    public float harga;

    public Kendaraan(String brand, int year, VehicleType type, float harga) {
        this.brand = brand;
        this.year = year;
        this.type = type;
        this.harga = harga;
    }

    public void showDetail() {
        System.out.println("Brand: " + brand);
        System.out.println("Year: " + year);
        System.out.println("Type: " + type);
        System.out.println("Harga: " + harga);
    }
}
