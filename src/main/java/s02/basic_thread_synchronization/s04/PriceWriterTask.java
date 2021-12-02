package s02.basic_thread_synchronization.s04;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

import java.util.Date;

@RequiredArgsConstructor
public class PriceWriterTask implements Runnable {

    private final PriceInfo priceInfo;

    @Override
    public void run() {
        for (int i = 0; i < 3; ++i) {
            System.out.printf("%s: Writer: try to update the prices\n", new Date());
            priceInfo.setPrices(priceInfo.getPrice1() * 2, priceInfo.getPrice2() * 2);
            System.out.printf("%s: Writer: prices updated\n", new Date());
            ThreadUtil.sleepMillis(1);
        }
    }

}
