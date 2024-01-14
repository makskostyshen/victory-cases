package com.makskostyshen.victorycases.data.impl;

import com.makskostyshen.victorycases.data.api.CaseRepository;
import com.makskostyshen.victorycases.data.entity.CaseEntity;
import com.makskostyshen.victorycases.exception.CaseNotFoundException;
import com.makskostyshen.victorycases.exception.CasePersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CaseRepositoryImpl implements CaseRepository {

    private final CSVReader reader;

    private final CSVWriter writer;

    private static final Comparator<CaseEntity> DATE_COMPARATOR =
            Comparator.comparing(CaseEntity::getCurrentStageDeadlineDate, Comparator.nullsLast(Comparator.naturalOrder()))
                    .thenComparing(CaseEntity::getCurrentStageDeadlineTime, Comparator.nullsLast(Comparator.naturalOrder()));
    @Override
    public List<CaseEntity> findAll() {
        return getAllEntitiesStream()
                .toList();
    }

    @Override
    public void save(final CaseEntity caseEntity) {
        if (caseEntity.getId() == null || caseEntity.getId().isEmpty()) {
            saveNewCase(caseEntity);
        } else {
            saveUpdatedCase(caseEntity);
        }
    }

    @Override
    public Optional<CaseEntity> findById(final String id) {
        List<CaseEntity> sameIdCases = getAllEntitiesStream()
                .filter(caseEntity -> caseEntity.getId().equals(id))
                .toList();

        if (sameIdCases.size() > 1) {
            throw new CasePersistenceException("There is a problem with case ids");
        }
        return sameIdCases.isEmpty()
                ? Optional.empty()
                : Optional.of(sameIdCases.get(0));
    }

    @Override
    public void deleteById(final String id) {
        List<CaseEntity> sameIdCases = new ArrayList<>();
        List<CaseEntity> differentIdCases = new ArrayList<>();

        getAllEntitiesStream().forEach(entity -> {
            if (entity.getId().equals(id)) {
                sameIdCases.add(entity);
            } else {
                differentIdCases.add(entity);
            }
        });

        if (sameIdCases.size() > 1) {
            throw new CasePersistenceException("There is a problem with case ids");
        }

        if (sameIdCases.isEmpty()) {
            throw new CaseNotFoundException();
        }
        differentIdCases.sort(DATE_COMPARATOR);
        writer.write(differentIdCases.stream().map(DataLayerMapper.I::map).toList());
    }

    private void saveNewCase(final CaseEntity caseEntity) {
        List<CaseEntity> allCases = getAllEntitiesStream().collect(Collectors.toCollection(ArrayList::new));
        caseEntity.setId(UUID.randomUUID().toString());
        allCases.add(caseEntity);
        allCases.sort(DATE_COMPARATOR);
        writer.write(allCases.stream().map(DataLayerMapper.I::map).toList());
    }

    private void saveUpdatedCase(final CaseEntity caseEntity) {
        List<CaseEntity> sameIdCases = new ArrayList<>();
        List<CaseEntity> differentIdCases = new ArrayList<>();

        getAllEntitiesStream().forEach(entity -> {
            if (entity.getId().equals(caseEntity.getId())) {
                sameIdCases.add(entity);
            } else {
                differentIdCases.add(entity);
            }
        });

        if (sameIdCases.size() > 1) {
            throw new CasePersistenceException("There is a problem with case ids");
        }

        differentIdCases.add(caseEntity);
        differentIdCases.sort(DATE_COMPARATOR);
        writer.write(differentIdCases.stream().map(DataLayerMapper.I::map).toList());
    }

    private Stream<CaseEntity> getAllEntitiesStream() {
        return reader.read().stream().map(DataLayerMapper.I::map);
    }
}
