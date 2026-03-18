package com.betacom.pr.services.interfaces;

import java.util.List;
import com.betacom.pr.dto.outputs.StatusDTO;

public interface IStatusServices {

    List<StatusDTO> getAll();
    
    StatusDTO getById(Integer id);

}