package s03.thread_synchronization_utilities.s06;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

import java.util.function.Supplier;

@RequiredArgsConstructor
class Step2 implements Supplier<Integer> {

    private final int step1Result;

    @Override
    public Integer get() {
        System.out.println(this.getClass().getSimpleName() + " START");
        ThreadUtil.sleepSeconds(step1Result);
        System.out.println(this.getClass().getSimpleName() + " END");
        return step1Result * 2;
    }

}
