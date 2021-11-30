package s02.basic_thread_synchronization.s01;

import lombok.ToString;

@ToString
public class ParkingCash {

    private int cash;

    public synchronized void collectCash() {
        cash += BasicThreadSynchronization.COST;
    }

    public synchronized int getCash() {
        return cash;
    }

}
