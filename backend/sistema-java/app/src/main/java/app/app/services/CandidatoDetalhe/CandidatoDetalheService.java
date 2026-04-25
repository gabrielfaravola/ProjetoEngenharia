package app.app.services.CandidatoDetalhe;

import app.app.domain.Candidato.Candidato;
import app.app.repository.CandidatoJpaRepository;
import app.app.utils.exeptions.CandidatoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidatoDetalheService {

    @Autowired
    private final CandidatoJpaRepository candidatoRepository;


    public CandidatoDetalheService(CandidatoJpaRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    public Candidato getCandidato(Long id){
        Optional<Candidato> result = candidatoRepository.findById(id);
        return result.orElseThrow(() -> new CandidatoNotFoundException("ID não foi encontrado."));
    }
}
