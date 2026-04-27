package app.app.services.CandidatoDetalhe;

import app.app.domain.Candidato.Candidato;
import app.app.domain.Plano.PlanoDeGoverno;
import app.app.infra.config.S3Config;
import app.app.repository.CandidatoJpaRepository;
import app.app.repository.ResumoJpaRepository;
import app.app.utils.exeptions.CandidatoNotFoundException;
import app.app.utils.exeptions.ResumoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;

import java.util.Optional;

@Service
public class CandidatoDetalheService {

    @Autowired
    private S3Client s3Client;

    @Autowired
    private CandidatoJpaRepository candidatoRepository;

    @Autowired
    private ResumoJpaRepository resumoJpaRepository;


    //pegar o resumo tambem
    public Candidato getCandidato(Long id){
        Optional<Candidato> result = candidatoRepository.findById(id);
        return result.orElseThrow(() -> new CandidatoNotFoundException("ID não foi encontrado."));
    }

    //Tentar ver de salvar text do resumo e os topicos aqui
    public void salvarResumo(Long candidatoId, String linkBucket){
        Candidato c = candidatoRepository.getReferenceById(candidatoId);
        PlanoDeGoverno p = PlanoDeGoverno.builder().candidato(c).resumo(linkBucket).build();
        resumoJpaRepository.save(p);
    }

    public String getResumo(Long candidatoId){
        Optional<PlanoDeGoverno> result = resumoJpaRepository.findByCandidatoId(candidatoId);
        PlanoDeGoverno resumo = result.orElseThrow(()-> new ResumoNotFoundException("Resumo do candidato de ID " + candidatoId + " não foi encontrado"));
        return resumo.getResumo();
    }

    //Metodo de pegar topicos
}
