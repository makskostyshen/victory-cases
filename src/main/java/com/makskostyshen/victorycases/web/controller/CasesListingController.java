package com.makskostyshen.victorycases.web.controller;

import com.makskostyshen.victorycases.data.api.CaseRepository;
import com.makskostyshen.victorycases.web.WebLayerMapper;
import com.makskostyshen.victorycases.web.dto.CaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(CasesListingController.PATH)
@RequiredArgsConstructor
public class CasesListingController {
    static final String PATH = "/cases";

    private final CaseRepository repository;
    @GetMapping
    public String get(final Model model) {
        List<CaseDto> cases = repository.findAll()
                .stream()
                .map(WebLayerMapper.I::map)
                .toList();

        model.addAttribute("cases", cases);
        return "cases/casesListing";
    }
}
