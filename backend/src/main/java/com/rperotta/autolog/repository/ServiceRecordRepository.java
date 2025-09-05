package com.rperotta.autolog.repository;

import com.rperotta.autolog.entity.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRecordRepository extends JpaRepository <ServiceRecord, Long>{
}
