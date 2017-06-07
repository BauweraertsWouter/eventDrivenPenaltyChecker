package be.kdg.se.wbw.examenproject.penaltyChecker.domain.models;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;

public class SpeedCheckData {
    private final CameraDetail firstCamera;
    private final CameraDetail secondCamera;
    private final CameraMessage firstMessage;
    private final CameraMessage secondMessage;

    public SpeedCheckData(CameraDetail firstCamera, CameraDetail secondCamera, CameraMessage firstMessage, CameraMessage secondMessage) {
        this.firstCamera=firstCamera;
        this.secondCamera=secondCamera;
        this.firstMessage=firstMessage;
        this.secondMessage =secondMessage;
    }

    public CameraDetail getFirstCamera() {
        return firstCamera;
    }

    public CameraDetail getSecondCamera() {
        return secondCamera;
    }

    public CameraMessage getFirstMessage() {
        return firstMessage;
    }

    public CameraMessage getSecondMessage() {
        return secondMessage;
    }
}
