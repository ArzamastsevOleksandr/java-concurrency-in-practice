package s05.fork_join_framework.s01;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Data
@AllArgsConstructor
class Product {

    String name;
    double price;

    static List<Product> generate(int n) {
        return IntStream.range(0, n)
                .mapToObj(i -> new Product("P " + i, 10))
                .collect(toList());
    }

}
