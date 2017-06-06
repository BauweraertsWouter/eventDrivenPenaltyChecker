package be.kdg.se.wbw.examenproject.penaltyChecker.shared.api;

/**
 * The Subscriber can subscribe to a Publisher to get notified when a new CameraMessageDto is available.
 */
public interface Subscriber<In> {
    /**
     * When a new CameraMessageDto is available in the Publisher, the Publisher will trigger the notify method.
     * @param message CameraMessageDto that was recently received by the Publisher
     */
    void notify(In message);
}
