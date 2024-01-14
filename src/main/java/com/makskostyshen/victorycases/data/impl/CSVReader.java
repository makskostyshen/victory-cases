package com.makskostyshen.victorycases.data.impl;

import com.makskostyshen.victorycases.exception.CSVReadException;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class CSVReader {

    @Setter
    @Value("${app.cases.fileName}")
    private String fileName;

    public List<CaseCsvModel> read() {
        try {
            return new CsvToBeanBuilder<CaseCsvModel>(new FileReader(fileName, StandardCharsets.UTF_8))
                    .withType(CaseCsvModel.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new CSVReadException(e);
        }
    }
}
