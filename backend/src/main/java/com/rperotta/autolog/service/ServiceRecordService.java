package com.rperotta.autolog.service;

import com.rperotta.autolog.entity.ServiceRecord;
import com.rperotta.autolog.repository.ServiceRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

        @Service
        public class ServiceRecordService {
            private final ServiceRecordRepository serviceRecordRepository;

            public ServiceRecordService(ServiceRecordRepository serviceRecordRepository) {
                this.serviceRecordRepository = serviceRecordRepository;
            }

            public ServiceRecord addServiceRecord(ServiceRecord serviceRecord) {
                return serviceRecordRepository.save(serviceRecord);
            }

            public Optional<ServiceRecord> getServiceRecordById(Long id) {
                return serviceRecordRepository.findById(id);
    }

    public void deleteServiceRecord(Long id) {
        serviceRecordRepository.deleteById(id);
    }

    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordRepository.findAll();
    }
}

