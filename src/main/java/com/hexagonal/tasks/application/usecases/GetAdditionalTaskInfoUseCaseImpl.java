package com.hexagonal.tasks.application.usecases;

import com.hexagonal.tasks.domain.models.AdditionalTaskInfo;
import com.hexagonal.tasks.domain.ports.inputs.GetAdditionalTaskUseCase;
import com.hexagonal.tasks.domain.ports.outputs.ExternalServicesPort;

public class GetAdditionalTaskInfoUseCaseImpl implements GetAdditionalTaskUseCase {
    private final ExternalServicesPort externalServicesPort;

    public GetAdditionalTaskInfoUseCaseImpl(ExternalServicesPort externalServicesPort) {
        this.externalServicesPort = externalServicesPort;
    }

    @Override
    public AdditionalTaskInfo getAdditionalTaskInfo(Long id) {
        return externalServicesPort.getAdditionalTaskInfo(id);
    }


}
