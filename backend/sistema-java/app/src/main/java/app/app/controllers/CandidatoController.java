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


    //Dar um jeito de pegar as outras infos que vem junto -> as listas
    //Lista de categorias
    //Plano
    //Mídias
    @GetMapping("/candidato/{id}")
    public ResponseEntity<Candidato> getCandidato(@PathVariable Long id){
        Candidato candidato = candidatoDetalheService.getCandidato(id);
        return ResponseEntity.ok(candidato);
    }

    @GetMapping("/resumo")
    public void salvarOrigemResumo(){

    }

    @GetMapping("/candidato/{id}/plano")
    public ResponseEntity<String> getResumoCandidato(){
        return ResponseEntity.ok("");
    }
    //FAZER OS DTOs, MAPPERS
}
