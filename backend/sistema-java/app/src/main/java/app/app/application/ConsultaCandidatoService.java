package app.app.application;

import app.app.domain.Candidato.Candidato;
import app.app.application.port.CandidatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaCandidatoService {

    private final CandidatoRepository candidatoRepository;

    public ConsultaCandidatoService(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    //tratar melhor condicoes de erro e regras de negocio
    public List<Candidato> listarCandidatos(){
        return candidatoRepository.listar();
    }

    //metodo de listar com base em filtros
}
