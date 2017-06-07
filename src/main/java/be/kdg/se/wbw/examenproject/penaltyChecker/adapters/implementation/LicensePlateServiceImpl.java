package be.kdg.se.wbw.examenproject.penaltyChecker.adapters.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.adapters.api.LicensePlateServiceProxyAdapter;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.LicensePlateDetailDto;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.exceptions.CommunicationException;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.exceptions.LicensePlateNotFoundException;
import be.kdg.se3.services.LicensePlateServiceProxy;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LicensePlateServiceImpl implements LicensePlateServiceProxyAdapter {
    private static final Logger logger = Logger.getLogger(LicensePlateServiceImpl.class);

    private final LicensePlateServiceProxy proxy;
    private final TypeMapper<JSONObject, LicensePlateDetailDto> licensePlateDetailMapper;

    @Autowired
    public LicensePlateServiceImpl(LicensePlateServiceProxy proxy, TypeMapper<JSONObject, LicensePlateDetailDto> mapper) {
        this.proxy = proxy;
        this.licensePlateDetailMapper = mapper;
    }

    @Override
    public LicensePlateDetailDto get(String licensePlate) {
        logger.debug("Querying LicensePlateServiceProxy for licensePlate " + licensePlate);
        try {
            JSONObject response = new JSONObject(proxy.get("www.services4se3.com/license-plate/" + licensePlate));
            if (response.has("error"))
                throw new LicensePlateNotFoundException((String) response.get("description"));
            return licensePlateDetailMapper.map(response);
        } catch (IOException e) {
            throw new CommunicationException("Something went wrong while retrieving info from the LicensePlateServiceProxy");
        }
    }
}
