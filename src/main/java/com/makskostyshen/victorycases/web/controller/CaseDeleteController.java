package com.makskostyshen.victorycases.web.controller;

import com.makskostyshen.victorycases.data.api.CaseRepository;
import com.makskostyshen.victorycases.data.entity.CaseEntity;
import com.makskostyshen.victorycases.exception.CaseNotFoundException;
import com.makskostyshen.victorycases.web.WebLayerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cases/delete")
@RequiredArgsConstructor
public class CaseDeleteController {
    private final CaseRepository repository;

    @GetMapping("/{id}")
    public String get(final Model model, @PathVariable final String id) {

        CaseEntity caseEntity = repository.findById(id).orElseThrow(CaseNotFoundException::new);
        model.addAttribute("details", WebLayerMapper.I.mapToDetails(caseEntity));
        return "cases/caseConfirmDelete";
    }

    @PostMapping("/{id}")
    public String post(final Model model, @PathVariable final String id) {
        repository.deleteById(id);
        return "redirect:" + CasesListingController.PATH;
    }
}
