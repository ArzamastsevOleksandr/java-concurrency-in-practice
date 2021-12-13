package s05.for_join_framework;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.stream.IntStream;

@RequiredArgsConstructor
class UpdatePricesTask extends RecursiveAction {

    final List<Product> products;
    final int first, last;
    final int factor;

    @Override
    protected void compute() {
        if (last - first < 10) {
            System.out.printf("%s: process [%d-%d]\n", Thread.currentThread().getName(), first, last);
            updatePrices();
        } else {
            int middle = (last + first) / 2;
            var task1 = new UpdatePricesTask(products, first, middle, factor);
            var task2 = new UpdatePricesTask(products, middle, last, factor);
            invokeAll(task1, task2);
        }
    }

    private void updatePrices() {
        IntStream.range(first, last)
                .forEach(i -> {
                    ThreadUtil.sleepSeconds(1);
                    Product product = products.get(i);
                    product.setPrice(product.getPrice() * factor);
                });
    }

}
