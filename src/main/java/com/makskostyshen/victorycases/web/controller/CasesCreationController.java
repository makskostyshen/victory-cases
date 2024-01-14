package com.makskostyshen.victorycases.web.controller;

import com.makskostyshen.victorycases.data.api.CaseRepository;
import com.makskostyshen.victorycases.web.WebLayerMapper;
import com.makskostyshen.victorycases.web.dto.CaseDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cases/create")
@RequiredArgsConstructor
public class CasesCreationController {
    private final CaseRepository repository;

    @GetMapping
    public String get(final Model model) {
        return "cases/caseCreate";
    }

    @PostMapping
    public String post(final Model model, final CaseDetailsDto caseDetailsDto) {
        repository.save(WebLayerMapper.I.map(caseDetailsDto));
        return "redirect:" + "/cases";
    }
}
