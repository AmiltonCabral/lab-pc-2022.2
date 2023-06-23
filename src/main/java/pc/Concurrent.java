package pc;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

import pc.DTOs.Actor;
import pc.utils.FileIOUtil;

public class Concurrent {
    private static String ACTORS_DATA_PATH = "./data/actors.txt";
    private static int NUMBER_OF_ACTORS = 10000;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        ArrayList<String> actorIds = FileIOUtil.readFile(ACTORS_DATA_PATH, NUMBER_OF_ACTORS);

        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_ACTORS);
        AtomicBoolean allActoresProcessed = new AtomicBoolean(false);

        Queue<Actor> actorsQueue = new ConcurrentLinkedQueue<>();
        Callable<String> rankingHandler = new RankingHandler(actorsQueue, allActoresProcessed, NUMBER_OF_ACTORS);
        Future<String> ranking = executor.submit(rankingHandler);

        for (String actorId : actorIds) {
            Runnable actorHandler = new ActorHandler(actorId, actorsQueue, latch);
            executor.execute(actorHandler);
        }

        latch.await();
        allActoresProcessed.set(true);

        System.out.println(ranking.get());
        executor.shutdown();
        System.out.println("\n--------------------------------------------------\n" +
                "\nTotal execution time in millis: " + ((System.nanoTime() - startTime) / 1000000));
    }
}
