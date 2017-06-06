package be.kdg.se.wbw.examenproject.penaltyChecker.adapters.api;

import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.LicensePlateDetailDto;
/**
 * @author Wouter Bauweraerts
 * @version 1.0
 * Adapter interface to connect with external LicensePlateProxy.
 */
public interface LicensePlateServiceProxyAdapter {
    LicensePlateDetailDto get(String licensePlate);
}
