package com.makskostyshen.victorycases.data.impl;

import com.makskostyshen.victorycases.exception.CSVWriteException;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class CSVWriter {

    @Value("${app.cases.fileName}")
    private String fileName;

    public void write(final List<CaseCsvModel> cases) {

        try {
            Writer writer = new FileWriter(fileName, StandardCharsets.UTF_8);
            StatefulBeanToCsv<CaseCsvModel> beanToCsv = new StatefulBeanToCsvBuilder<CaseCsvModel>(writer).build();
            beanToCsv.write(cases);
            writer.close();
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            throw new CSVWriteException(e);
        }
    }
}
