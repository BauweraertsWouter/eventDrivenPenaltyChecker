package be.kdg.se.wbw.examenproject.penaltyChecker.adapters.implementation;

import be.kdg.se.wbw.examenproject.penaltyChecker.adapters.api.CameraDetailsServiceProxyAdapter;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.api.TypeMapper;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto.CameraDetailDto;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.exceptions.CameraNotFoundException;
import be.kdg.se.wbw.examenproject.penaltyChecker.shared.exceptions.CommunicationException;
import be.kdg.se3.services.CameraServiceProxy;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CameraDetailsServiceProxyAdapterImpl implements CameraDetailsServiceProxyAdapter {
    private static final Logger logger = Logger.getLogger(CameraDetailsServiceProxyAdapterImpl.class);

    private CameraServiceProxy proxy;
    private TypeMapper<JSONObject, CameraDetailDto> cameraDetailMapper;

    @Autowired
    public CameraDetailsServiceProxyAdapterImpl(CameraServiceProxy proxy, TypeMapper<JSONObject, CameraDetailDto> mapper) {
        this.proxy = proxy;
        this.cameraDetailMapper = mapper;
    }

    @Override
    public CameraDetailDto get(int cameraId) {
        logger.debug("Contacting external CameraServiceProxy for details of camera " + cameraId);

        try {
            JSONObject response = new JSONObject(proxy.get("www.services4se3.com/camera/" + cameraId));
            if (response.has("error"))
                throw new CameraNotFoundException((String) response.get("description"));
            return cameraDetailMapper.map(response);
        } catch (IOException e) {
            throw new CommunicationException("Something went wrong while retrieving info from CameraServiceProxy");
        }
    }
}
