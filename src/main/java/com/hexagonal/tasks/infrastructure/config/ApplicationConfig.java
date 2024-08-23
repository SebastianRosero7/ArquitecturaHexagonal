package com.hexagonal.tasks.infrastructure.config;

import com.hexagonal.tasks.application.services.TaskServices;
import com.hexagonal.tasks.application.usecases.*;
import com.hexagonal.tasks.domain.ports.inputs.CreateTaskUseCase;
import com.hexagonal.tasks.domain.ports.inputs.GetAdditionalTaskUseCase;
import com.hexagonal.tasks.domain.ports.outputs.ExternalServicesPort;
import com.hexagonal.tasks.domain.ports.outputs.TaskRepositoryPort;
import com.hexagonal.tasks.infrastructure.adapters.ExternalServiceAdapter;
import com.hexagonal.tasks.infrastructure.repositories.JpaTaskRepository;
import com.hexagonal.tasks.infrastructure.repositories.JpaTaskRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ApplicationConfig {

    @Bean
    public TaskServices taskServices(TaskRepositoryPort taskRepositoryPort, GetAdditionalTaskUseCase getAdditionalTaskUseCase) {
        return new TaskServices(
                new CreateTaskUseCaseImpl(taskRepositoryPort),
                new RetrieveTaskUseCaseImpl(taskRepositoryPort),
                new DeleteTaskUseCaseImpl(taskRepositoryPort),
                new UpdateTaskUseCaseImpl(taskRepositoryPort),
                getAdditionalTaskUseCase
        );
    }

    @Bean
    public TaskRepositoryPort taskRepositoryPort(JpaTaskRepositoryAdapter jpaTaskRepositoryAdapter) {
        return jpaTaskRepositoryAdapter;
    }

    @Bean
    public GetAdditionalTaskUseCase getAdditionalTaskUseCase(ExternalServicesPort externalServicesPort) {
        return new GetAdditionalTaskInfoUseCaseImpl(externalServicesPort);
    }

    @Bean
    public ExternalServicesPort externalServicesPort() {
        return new ExternalServiceAdapter();
    }
}
