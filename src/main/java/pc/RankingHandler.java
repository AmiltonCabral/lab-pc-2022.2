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

    RankingHandler(Queue<Actor> actorsQueue, AtomicBoolean allActoresProcessed){
        this.actorsQueue = actorsQueue;
        this.ranking = new Actor[10];
        this.allActoresProcessed = allActoresProcessed;
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
                System.out.println("Actors processed: " + ++cont);
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