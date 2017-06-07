package be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.caching;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.models.cameraDetail.CameraDetail;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.CameraDetailsCache;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CameraDetailsCacheImpl implements CameraDetailsCache {
    private static final Logger logger = Logger.getLogger(CameraDetailsCacheImpl.class);
    private List<CameraDetail> cameras = Collections.synchronizedList(new ArrayList<>());
    private int interval;

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
        LocalDateTime edge = LocalDateTime.now().minusMinutes(interval);
        new ArrayList<>(cameras).stream()
                .filter(c->c.getRetrievingTimestamp().isBefore(edge))
                .forEach(c->cameras.remove(c));
        logger.info("Scheduled task completed: Cache cleanup");
    }

    @Override
    public void setIntervalMinutes(int interval) {
        this.interval = interval;
    }
}
