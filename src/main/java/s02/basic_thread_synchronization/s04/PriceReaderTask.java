package s02.basic_thread_synchronization.s04;

import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class PriceReaderTask implements Runnable {

    private final PriceInfo priceInfo;

    @Override
    public void run() {
        for (int i = 0; i < 15; ++i) {
            System.out.printf("%s: %s: price1: %f\n", new Date(), Thread.currentThread().getName(), priceInfo.getPrice1());
            System.out.printf("%s: %s: price2: %f\n", new Date(), Thread.currentThread().getName(), priceInfo.getPrice2());
        }
    }

}
