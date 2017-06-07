package be.kdg.se.wbw.examenproject.penaltyChecker;

import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.api.CameraDetailsCache;
import be.kdg.se.wbw.examenproject.penaltyChecker.domain.services.implementation.CameraDetailsCacheImpl;
import be.kdg.se3.services.CameraServiceProxy;
import be.kdg.se3.services.LicensePlateServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Bean
    @Primary
    public CameraDetailsCache getCameraDetailsCache(){
        CameraDetailsCache cache = new CameraDetailsCacheImpl();
        cache.setIntervalMinutes(30);
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

    @Scheduled(fixedRate = 1800000L)
    private void cleanCameraCache(){
        getCameraDetailsCache().clear();
    }
}
