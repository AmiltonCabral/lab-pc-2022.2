package pc;

import java.util.Queue;
import java.util.concurrent.CountDownLatch;

import pc.DTOs.Actor;
import pc.DTOs.Movie;
import pc.services.CineLsdDatabaseService;

public class ActorHandler implements Runnable{
    private final Queue<Actor> actorsQueue;
    private String actorId;
    private final CountDownLatch latch;
    
    ActorHandler(String actorId, Queue<Actor> actorsQueue, CountDownLatch latch) {
        this.actorId = actorId;
        this.actorsQueue = actorsQueue;
        this.latch = latch;
    }

    @Override
    public void run(){
        Actor actor = CineLsdDatabaseService.requestActor(this.actorId);
        if (actor != null && actor.getMovies().size() > 0) {
        	float totalRating = 0;

            for (String movieId : actor.getMovies()) {
            Movie movie = CineLsdDatabaseService.requestMovie(movieId);

            if (movie != null) {
              totalRating += movie.getAverageRating();
            }
            
            actor.setRating(totalRating / actor.getMovies().size());
            actorsQueue.add(actor);
        }
        latch.countDown();
      }      
    }
}
