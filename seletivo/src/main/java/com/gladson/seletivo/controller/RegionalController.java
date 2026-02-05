package com.gladson.seletivo.controller;

import com.gladson.seletivo.dto.RegionalDTO;
import com.gladson.seletivo.model.Regional;
import com.gladson.seletivo.service.RegionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/regionais") // versionamento
public class RegionalController {

    @Autowired
    private RegionalService regionalService;


    // Endpoint para sincronização manual
    @PostMapping("/sync")
    public String sincronizarRegionais() {
        regionalService.syncRegionais();
        return "Sincronização realizada com sucesso!";
    }

    // Endpoint para listar regionais
    @GetMapping
    public List<RegionalDTO> listarRegionais() {
        List<Regional> regionais = regionalService.getRegionais(true);
        return regionais.stream()
                .map(r -> new RegionalDTO(r.getId(), r.getNome()))
                .collect(Collectors.toList());
    }
}
