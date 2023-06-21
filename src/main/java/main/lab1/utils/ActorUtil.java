package main.lab1.utils;

import main.lab1.DTOs.Actor;

public class ActorUtil {
    public static void sortInsertion(Actor[] Actors, Actor newActor) {
        Actors[9] = newActor;

        int j = Actors.length - 1;

        while (j > 0 && (Actors[j - 1] == null || Actors[j].getRating() > Actors[j - 1].getRating())) {
            Actor aux = Actors[j];
            Actors[j] = Actors[j - 1];
            Actors[j - 1] = aux;
            j -= 1;
        }
    }
}
