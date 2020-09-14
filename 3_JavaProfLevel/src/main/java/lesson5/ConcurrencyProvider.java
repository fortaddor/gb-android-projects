package lesson5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

import static lesson5.RaceConsts.*;

public class ConcurrencyProvider
{
    private final CyclicBarrier cyclicBarrier;
    private final List<CountDownLatch> countDownLatches;
    private final Semaphore semaphore;

    public ConcurrencyProvider(int latchesCount)
    {
        this.cyclicBarrier = new CyclicBarrier(CARS_COUNT);
        this.countDownLatches = createCountDownLatches(latchesCount);
        this.semaphore = new Semaphore(TUNNEL_CARS_COUNT);
    }

    private static List<CountDownLatch> createCountDownLatches(int count)
    {
        List<CountDownLatch> latches = new ArrayList<>();

        for (int i = 0; i < count; i++)
        {
            latches.add(new CountDownLatch(CARS_COUNT));
        }

        return latches;
    }

    public CyclicBarrier getCyclicBarrier()
    {
        return this.cyclicBarrier;
    }

    public List<CountDownLatch> getCountDownLatches()
    {
        return this.countDownLatches;
    }

    public CountDownLatch getRaceStartCountDownLatches()
    {
        return this.getCountDownLatches().get(LATCH_RACE_START);
    }

    public CountDownLatch getRaceFinishCountDownLatches()
    {
        return this.getCountDownLatches().get(LATCH_RACE_FINISH);
    }

    public Semaphore getSemaphore()
    {
        return this.semaphore;
    }
}
