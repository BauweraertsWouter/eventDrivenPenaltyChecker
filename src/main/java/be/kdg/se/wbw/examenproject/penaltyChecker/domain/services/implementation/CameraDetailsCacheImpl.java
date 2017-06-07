package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.CameraDetailsCache;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CameraDetailsCacheImpl implements CameraDetailsCache {
    private static final Logger logger = Logger.getLogger(CameraDetailsCacheImpl.class);
    private List<CameraDetail> cameras = new ArrayList<>();

    @Override
    public Optional<CameraDetail> findCamera(int cameraId) {
        logger.info("CameraDetailsCache: searching for camera " + cameraId);
        return cameras.stream()
                .filter(c->c.getCameraId()==cameraId)
                .findFirst();
    }

    @Override
    public Optional<CameraDetail> findPreviousCamera(int cameraId) {
        logger.info("CameraDetailsCache: searching for the camera on the same segment as camera " + cameraId);
        return cameras.stream()
                .filter(c->c.getSegment().getConnectedCameraId()==cameraId)
                .findFirst();
    }

    @Override
    public void addCamera(CameraDetail cameraDetail) {
        if (!findCamera(cameraDetail.getCameraId()).isPresent())
            cameras.add(cameraDetail);
        logger.info("CameraDetailsCache: camera " + cameraDetail.getCameraId() + " added to cache");

    }

    @Override
    public void clear() {
        cameras.clear();
        logger.info("Scheduled task completed: Cache clearen");
    }
}
