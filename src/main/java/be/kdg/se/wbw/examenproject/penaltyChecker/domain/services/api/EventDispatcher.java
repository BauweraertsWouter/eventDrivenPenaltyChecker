package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api;

/**
 * Created by Wouter on 6/06/2017.
 */
public interface EventDispatcher {
    void addListener(EventListener listener);
}
