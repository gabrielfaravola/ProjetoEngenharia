package app.app.adapters.in_web;

import app.app.application.ConsultaCandidatoService;
import app.app.domain.Candidato.Candidato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuscaCandidatoController {

    @Autowired
    private ConsultaCandidatoService consultaCandidatoService;


    //APLICAR PAGINATION EM AMBAS AS FORMAS DE BUSCA
    //APLICAR PADRONIZACAO DE RETORNO COM CODIGO

    @GetMapping("/candidatos/buscar")
    public Candidato buscarCandidato(@RequestParam(required = false) String nome,
                                @RequestParam(required = false) String cargo,
                                @RequestParam(required = false) String partido){

        return new Candidato();
    }

    @GetMapping("/candidatos")
    public ResponseEntity<List<Candidato>> listarAll(){
        List<Candidato> candidatoList = consultaCandidatoService.listarCandidatos();
        return ResponseEntity.ok(candidatoList);
    }
}
