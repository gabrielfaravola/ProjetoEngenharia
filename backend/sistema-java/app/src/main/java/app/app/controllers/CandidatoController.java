package app.app.controllers;


import app.app.domain.Candidato.Candidato;
import app.app.services.CandidatoDetalhe.CandidatoDetalheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidatoController {

    @Autowired
    private CandidatoDetalheService candidatoDetalheService;

    @GetMapping("/candidato/{id}")
    public ResponseEntity<Candidato> getCandidato(@PathVariable Long id){
        Candidato candidato = candidatoDetalheService.getCandidato(id);
        return ResponseEntity.ok(candidato);
    }

    //PADRONIZAR DTO PARA TODAS AS RESPOSTAS, ADICIONAR REGRAS DE NEGÓCIO E DE DOMÍNIO
    //ADICIONAR EXCECOES NOS SERVICES E MAPPERS, PADRONIZAR DEVOLUCAO CORRETA DE CODIGOS NOS CONTROLLERS
}
