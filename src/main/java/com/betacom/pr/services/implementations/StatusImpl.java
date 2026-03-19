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
    public StatusDTO getById(Integer id) {
        log.debug("get status by id: {}", id);
        return statusR.findById(id)
                .map(s -> this.convertToDTO(s))
                .orElse(null);
    }
      
    @Override
    public List<StatusDTO> getAll() {
        log.debug("list all statuses");
        return statusR.findAll().stream()
                .map(s -> this.convertToDTO(s))
                .toList();
    }

    private StatusDTO convertToDTO(Status s) {
        StatusDTO dto = new StatusDTO();
        dto.setId(s.getId());
        dto.setDescription(s.getDescription());
        return dto;
    }

}
