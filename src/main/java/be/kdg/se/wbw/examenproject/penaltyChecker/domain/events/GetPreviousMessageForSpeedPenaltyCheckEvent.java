package be.kdg.se.wbw.examenproject.penaltyChecker.domain.events;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.events.base.Event;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.CameraMessage;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;

import java.time.LocalDateTime;

public class GetPreviousMessageForSpeedPenaltyCheckEvent implements Event<CameraDetail> {
    private final LocalDateTime timestamp;
    private final CameraDetail firstCameraDetail;
    private final CameraDetail secondCameraDetail;
    private final CameraMessage newestMessage;

    public GetPreviousMessageForSpeedPenaltyCheckEvent(CameraDetail first, CameraDetail second, CameraMessage newestMessage) {
        firstCameraDetail = first;
        secondCameraDetail = second;
        this.newestMessage = newestMessage;
        timestamp = LocalDateTime.now();
    }

    @Override
    public CameraDetail getEventDetails() {
        return getSecondCameraDetail();
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timestamp;
    }

    public CameraDetail getFirstCameraDetail() {
        return firstCameraDetail;
    }

    public CameraDetail getSecondCameraDetail() {
        return secondCameraDetail;
    }

    public CameraMessage getSecondMessage() {
        return newestMessage;
    }
}
