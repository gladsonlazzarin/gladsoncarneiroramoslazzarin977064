package com.gladson.seletivo.service;
import com.gladson.seletivo.dto.RegionalDTO;
import com.gladson.seletivo.model.Regional;
import com.gladson.seletivo.respository.RegionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegionalService {

    @Autowired
    private RegionalRepository regionalRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Método principal de sincronização
    public void syncRegionais() {
        RegionalDTO[] externos = restTemplate.getForObject(
                "https://integrador-argus-api.geia.vip/v1/regionais",
                RegionalDTO[].class
        );

        if (externos == null || externos.length == 0) return;

        List<Regional> banco = regionalRepository.findAll();
        Map<Integer, Regional> bancoMap = banco.stream()
                .collect(Collectors.toMap(Regional::getId, r -> r));

        // Inserir novos e criar versão para alterados
        for (RegionalDTO externo : externos) {
            Regional existente = bancoMap.get(externo.id());
            if (existente == null) {
                regionalRepository.save(new Regional(externo.id(), externo.nome(), true));
            } else if (!existente.getNome().equals(externo.nome())) {
                existente.setAtivo(false);
                regionalRepository.save(existente);

                regionalRepository.save(new Regional(externo.id(), externo.nome(), true));
            }
        }

        // Inativar ausentes
        Set<Integer> externosIds = Arrays.stream(externos)
                .map(RegionalDTO::id)
                .collect(Collectors.toSet());

        for (Regional r : banco) {
            if (!externosIds.contains(r.getId()) && r.getAtivo()) {
                r.setAtivo(false);
                regionalRepository.save(r);
            }
        }
    }

    // Agendamento automático a cada 12 horas
    @Scheduled(cron = "0 0 0,12 * * ?")
    public void syncRegionaisAgendado() {
        syncRegionais();
    }

    // Listagem de regionais
    public List<Regional> getRegionais(boolean apenasAtivos) {
        return regionalRepository.findByAtivoTrue();
    }
}
