package com.hexagonal.tasks.domain.ports.outputs;

import com.hexagonal.tasks.domain.models.AdditionalTaskInfo;

public interface ExternalServicesPort {
    AdditionalTaskInfo getAdditionalTaskInfo(Long id);

}
