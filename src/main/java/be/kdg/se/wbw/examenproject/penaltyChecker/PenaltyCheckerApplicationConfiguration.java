package be.kdg.se.wbw.examenproject.penaltyChecker;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.CameraDetailsCache;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.CameraMessageCache;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.caching.CameraDetailsCacheImpl;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.caching.CameraMessageCacheImpl;
import be.kdg.se3.services.CameraServiceProxy;
import be.kdg.se3.services.LicensePlateServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@ComponentScan
public class PenaltyCheckerApplicationConfiguration {

    private static final int MINUTES = 30;
    private static final long MILLISECONDS = MINUTES * 60000;

    @Bean
    @Primary
    public CameraDetailsCache getCameraDetailsCache(){
        CameraDetailsCache cache = new CameraDetailsCacheImpl();
        cache.setIntervalMinutes(MINUTES);
        return cache;
    }

    @Bean
    public CameraMessageCache getCameraMessageBuffer(){
        CameraMessageCache cache = new CameraMessageCacheImpl();
        cache.setCleanIntervalMinutes(MINUTES);
        return cache;
    }

    @Bean
    public CameraServiceProxy getCameraProxy(){
        return new CameraServiceProxy();
    }

    @Bean
    public LicensePlateServiceProxy getLicensePlateProxy(){
        return new LicensePlateServiceProxy();
    }

    @Scheduled(fixedRate = MILLISECONDS)
    private void cleanCameraCache(){
        getCameraDetailsCache().clear();
    }

    @Scheduled(fixedRate = MILLISECONDS)
    private void cleanCameraMessageCache(){getCameraMessageBuffer().cleanUp();}
}
