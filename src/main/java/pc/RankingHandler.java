package pc;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

import pc.DTOs.Actor;
import pc.utils.ActorUtil;

public class RankingHandler implements Callable<String>{
    private final Queue<Actor> actorsQueue;
    private Actor[] ranking;
    AtomicBoolean allActoresProcessed;
    private int MAX_ACTORS_TO_READ;

    RankingHandler(Queue<Actor> actorsQueue, AtomicBoolean allActoresProcessed, int MAX_ACTORS_TO_READ){
        this.actorsQueue = actorsQueue;
        this.ranking = new Actor[10];
        this.allActoresProcessed = allActoresProcessed;
        this.MAX_ACTORS_TO_READ = MAX_ACTORS_TO_READ;
    }

    @Override
    public String call(){
    	int cont = 0;
        while (!(allActoresProcessed.get() && actorsQueue.isEmpty())) {
            if (!actorsQueue.isEmpty()) {
                Actor actor = actorsQueue.remove();
                
                if (ranking[9] == null || actor.getRating() > ranking[9].getRating()) {
                    ActorUtil.sortInsertion(ranking, actor);
                }
                System.out.println("atores processados: " + (++cont) + "/ " + MAX_ACTORS_TO_READ);
            }
        }
        
        return this.rankingToString();
    }

    private String rankingToString(){
        String result = "";
        result += "\n--------------------------------------------------\n";
        for (Actor actor : ranking) {
            result += actor + "\n";
        }
        return result;
    }
}