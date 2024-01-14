package com.makskostyshen.victorycases.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CaseEntity {
    private String id;

    private String client;

    private String opponent;

    private String subject;

    private String price;

    private String number;

    private String judge;

    private String doneWork;

    private CaseStage currentStage;

    private LocalDate currentStageDeadlineDate;

    private LocalTime currentStageDeadlineTime;

    private CaseOrderStatus orderStatus;

    private String note;
}
