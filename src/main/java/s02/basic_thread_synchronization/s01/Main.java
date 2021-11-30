package s02.basic_thread_synchronization.s01;

public class Main {

    public static void main(String[] args) {
        var parkingCash = new ParkingCash();
        var parkingStats = new ParkingStats(parkingCash);

        System.out.println("Simulation begins.");

        var threads = new Thread[BasicThreadSynchronization.SENSORS];
        for (int i = 0; i < BasicThreadSynchronization.SENSORS; ++i) {
            var sensor = new Sensor(parkingStats);
            var thread = new Thread(sensor);
            thread.start();
            threads[i] = thread;
        }
        for (var thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(parkingStats);

        int numberOfVehiclesPerEachRound = 3;
        int expectedCash = BasicThreadSynchronization.SENSORS
                * BasicThreadSynchronization.ROUNDS
                * BasicThreadSynchronization.COST
                * numberOfVehiclesPerEachRound;
        System.out.printf("Cash expected: %d, actual: %d\n", expectedCash, parkingCash.getCash());
        System.out.printf("Cars expected: %d, actual: %d\n", 0, parkingStats.getNumberOfCars());
        System.out.printf("Motorcycles expected: %d, actual: %d\n", 0, parkingStats.getNumberOfMotorcycles());
    }

}
