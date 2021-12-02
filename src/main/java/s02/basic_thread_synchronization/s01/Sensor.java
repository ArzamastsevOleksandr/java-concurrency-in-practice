package s02.basic_thread_synchronization.s01;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

@RequiredArgsConstructor
public class Sensor implements Runnable {

    private final ParkingStats parkingStats;

    @Override
    public void run() {
        for (int i = 0; i < BasicThreadSynchronization.ROUNDS; ++i) {
            parkingStats.newCarArrived();
            parkingStats.newCarArrived();
            ThreadUtil.sleepMillis(50);

            parkingStats.newMotorcycleArrived();
            ThreadUtil.sleepMillis(50);

            parkingStats.carLeft();
            parkingStats.carLeft();
            parkingStats.motorcycleLeft();
        }
    }

}
