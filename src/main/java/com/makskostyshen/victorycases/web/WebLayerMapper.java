package com.makskostyshen.victorycases.web;

import com.makskostyshen.victorycases.data.entity.CaseEntity;
import com.makskostyshen.victorycases.web.dto.CaseDetailsDto;
import com.makskostyshen.victorycases.web.dto.CaseDto;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class WebLayerMapper {
    public static WebLayerMapper I = Mappers.getMapper(WebLayerMapper.class);

    private final String EMPTY_FIELD_VALUE = "";

    private final DateTimeFormatter dateTimeDisplayFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm");
    private final DateTimeFormatter dateDisplayFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final DateTimeFormatter standardSystemDateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    private final DateTimeFormatter standardSystemTimeFormatter = DateTimeFormatter.ISO_TIME;

    public CaseDto map(final CaseEntity entity) {
        return map(entity, createBlankCaseDto());
    }

    public CaseDetailsDto mapToDetails(CaseEntity entity) {
        return mapToDetails(entity, createBlankCaseDetailsDto());
    }

    @Mapping(
            target = "currentStageDeadline",
            expression = "java(mapDeadline(entity.getCurrentStageDeadlineDate(), entity.getCurrentStageDeadlineTime()))")
    public abstract CaseDto map(final CaseEntity entity, @MappingTarget CaseDto dto);

    public abstract CaseEntity map(final CaseDetailsDto detailsDto);

    public abstract CaseDetailsDto mapToDetails(CaseEntity entity, @MappingTarget CaseDetailsDto dto);

    protected LocalDate mapToLocalDate(final String dateValue) {
        return LocalDate.parse(dateValue, standardSystemDateFormatter);
    }

    protected LocalTime mapToLocalTime(final String timeValue) {
        return LocalTime.parse(timeValue, standardSystemTimeFormatter);
    }

    protected String mapDeadline(final LocalDate date, final LocalTime time) {
        if (date == null) {
            return EMPTY_FIELD_VALUE;
        } else if (time == null) {
            return dateDisplayFormatter.format(date);
        } else {
            return dateTimeDisplayFormatter.format(LocalDateTime.of(date, time));
        }
    }

    protected String mapLocalDate(final LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(standardSystemDateFormatter);
    }

    protected String mapLocalTime(final LocalTime time) {
        if (time == null) {
            return null;
        }
        return time.format(standardSystemTimeFormatter);
    }

    protected String map(final String stringField) {
        return stringField;
    }

    @Condition
    protected boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    private CaseDto createBlankCaseDto() {
        return CaseDto.builder()
                .id(EMPTY_FIELD_VALUE)
                .client(EMPTY_FIELD_VALUE)
                .opponent(EMPTY_FIELD_VALUE)
                .subject(EMPTY_FIELD_VALUE)
                .price(EMPTY_FIELD_VALUE)
                .number(EMPTY_FIELD_VALUE)
                .judge(EMPTY_FIELD_VALUE)
                .doneWork(EMPTY_FIELD_VALUE)
                .currentStage(EMPTY_FIELD_VALUE)
                .currentStageDeadline(EMPTY_FIELD_VALUE)
                .orderStatus(EMPTY_FIELD_VALUE)
                .note(EMPTY_FIELD_VALUE)
                .build();
    }

    private CaseDetailsDto createBlankCaseDetailsDto() {
        return CaseDetailsDto.builder()
                .id(EMPTY_FIELD_VALUE)
                .client(EMPTY_FIELD_VALUE)
                .opponent(EMPTY_FIELD_VALUE)
                .subject(EMPTY_FIELD_VALUE)
                .price(EMPTY_FIELD_VALUE)
                .number(EMPTY_FIELD_VALUE)
                .judge(EMPTY_FIELD_VALUE)
                .doneWork(EMPTY_FIELD_VALUE)
                .currentStage(EMPTY_FIELD_VALUE)
                .currentStageDeadlineDate(EMPTY_FIELD_VALUE)
                .currentStageDeadlineTime(EMPTY_FIELD_VALUE)
                .orderStatus(EMPTY_FIELD_VALUE)
                .note(EMPTY_FIELD_VALUE)
                .build();
    }
}
