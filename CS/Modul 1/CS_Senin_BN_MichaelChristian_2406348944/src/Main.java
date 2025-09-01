public class Main {
    public static void main(String [] args) {
        Kendaraan kendaraanSupraBapak = new Kendaraan("Honda Supra", 1998, VehicleType.Motor, 3000);
        Kendaraan kendaraanKalcer = new Kendaraan("VW Beetle", 1998, VehicleType.Mobil, 200000);
        Kendaraan kendaraanGuede = new Kendaraan("Isuzu Giga", 2011, VehicleType.Truck, 300000);

        Customer customer1 = new Customer("Bapak Joko", kendaraanSupraBapak);
        Customer customer2 = new Customer("Ibu Siti", kendaraanKalcer);
        Customer customer3 = new Customer("Bapak Budi", kendaraanGuede);

        customer1.showDetail();
        customer2.showDetail();
        customer3.showDetail();


    }
}