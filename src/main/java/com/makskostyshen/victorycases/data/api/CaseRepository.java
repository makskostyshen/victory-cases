package com.makskostyshen.victorycases.data.api;

import com.makskostyshen.victorycases.data.entity.CaseEntity;

import java.util.List;
import java.util.Optional;

public interface CaseRepository {
    List<CaseEntity> findAll();

    void save(CaseEntity caseEntity);

    Optional<CaseEntity> findById(String id);

    void deleteById(String id);
}
