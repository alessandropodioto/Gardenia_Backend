package com.betacom.pr.services.implementations;

import java.util.List;
import org.springframework.stereotype.Service;
import com.betacom.pr.dto.outputs.StatusDTO;
import com.betacom.pr.models.Status;
import com.betacom.pr.repositories.IStatusRepository;
import com.betacom.pr.services.interfaces.IStatusServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class StatusImpl implements IStatusServices {

    private final IStatusRepository statusR;

    @Override
    public StatusDTO getById(Integer id) throws Exception {
        log.debug("get status by id: {}", id);

        Status s = statusR.findById(id)
        .orElseThrow(() -> new Exception("Status not found"));
        return StatusDTO.builder()
                        .id(s.getId())
                        .description(s.getDescription())
                        .build();
    }
      
    @Override
    public List<StatusDTO> getAll() {
        log.debug("list all statuses");
        return statusR.findAll().stream()
                .map(s -> StatusDTO.builder()
                        .id(s.getId())
                        .description(s.getDescription())
                        .build()
                ).toList();
    }

}
