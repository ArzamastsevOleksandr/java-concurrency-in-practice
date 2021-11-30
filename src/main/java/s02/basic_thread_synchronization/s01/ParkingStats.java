package s02.basic_thread_synchronization.s01;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class ParkingStats {

    private final Object carsMonitor = new Object(), motorcyclesMonitor = new Object();

    @ToString.Include
    private final ParkingCash parkingCash;

    @ToString.Include
    private int numberOfCars;
    @ToString.Include
    private int numberOfMotorcycles;

    public void newCarArrived() {
        synchronized (carsMonitor) {
            ++numberOfCars;
        }
    }

    public void carLeft() {
        synchronized (carsMonitor) {
            --numberOfCars;
        }
        parkingCash.collectCash();
    }

    public void newMotorcycleArrived() {
        synchronized (motorcyclesMonitor) {
            ++numberOfMotorcycles;
        }
    }

    public void motorcycleLeft() {
        synchronized (motorcyclesMonitor) {
            --numberOfMotorcycles;
        }
        parkingCash.collectCash();
    }

    public int getNumberOfCars() {
        synchronized (carsMonitor) {
            return numberOfCars;
        }
    }

    public int getNumberOfMotorcycles() {
        synchronized (motorcyclesMonitor) {
            return numberOfMotorcycles;
        }
    }

}
