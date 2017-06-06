package be.kdg.se.wbw.examenproject.penaltyChecker.shared.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.CameraMessageDto;

public interface Subscriber {
    void notify(CameraMessageDto message);
}
