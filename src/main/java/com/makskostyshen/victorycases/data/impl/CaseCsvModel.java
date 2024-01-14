package com.makskostyshen.victorycases.data.impl;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CaseCsvModel {
    @CsvBindByName(column = "id")
    private String id;

    @CsvBindByName(column = "client")
    private String client;

    @CsvBindByName(column = "opponent")
    private String opponent;

    @CsvBindByName(column = "subject")
    private String subject;

    @CsvBindByName(column = "price")
    private String price;

    @CsvBindByName(column = "number")
    private String number;

    @CsvBindByName(column = "judge")
    private String judge;

    @CsvBindByName(column = "done_work")
    private String doneWork;

    @CsvBindByName(column = "current_stage")
    private String currentStage;

    @CsvBindByName(column = "current_stage_deadline_date")
    private String currentStageDeadlineDate;

    @CsvBindByName(column = "current_stage_deadline_time")
    private String currentStageDeadlineTime;

    @CsvBindByName(column = "order_status")
    private String orderStatus;

    @CsvBindByName(column = "note")
    private String note;
}
