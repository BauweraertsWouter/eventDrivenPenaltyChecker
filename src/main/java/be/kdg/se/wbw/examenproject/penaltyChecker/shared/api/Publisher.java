package be.kdg.se.wbw.examenproject.penaltyChecker.shared.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.CameraMessageDto;

public interface Publisher {
    void addSubscriber(Subscriber subscriber);
    void notifySubscribers(CameraMessageDto cameraMessageDto);
}
