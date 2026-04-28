package app.app.services.CandidatoDetalhe;

import app.app.domain.Candidato.Candidato;
import app.app.domain.Plano.PlanoDeGoverno;
import app.app.domain.Plano.TopicoPlano;
import app.app.repository.CandidatoJpaRepository;
import app.app.repository.ResumoJpaRepository;
import app.app.utils.exeptions.CandidatoNotFoundException;
import app.app.utils.exeptions.ResumoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@Service
public class CandidatoDetalheService {

    @Autowired
    private S3Client s3Client;

    @Autowired
    private CandidatoJpaRepository candidatoRepository;

    @Autowired
    private ResumoJpaRepository resumoJpaRepository;


    //Chamada Eager para chamar tudo de Candidato (plano, categorias, mídias)
    public Candidato getCandidato(Long id){
        Optional<Candidato> result = candidatoRepository.findById(id);
        return result.orElseThrow(() -> new CandidatoNotFoundException("ID não foi encontrado."));
    }

    //Ler, pegar os topicos, salvar os dois
    public void salvarResumo(Long candidatoId, String bucketName, String bucketKey){
        String resumo;
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(bucketKey)
                .build();

        try(ResponseInputStream<GetObjectResponse> arquivo = s3Client.getObject(request);
            BufferedReader r = new BufferedReader(new InputStreamReader(arquivo))){


        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    public String getResumo(Long candidatoId){
        Optional<PlanoDeGoverno> result = resumoJpaRepository.findByCandidatoId(candidatoId);
        PlanoDeGoverno resumo = result.orElseThrow(()-> new ResumoNotFoundException("Resumo do candidato de ID " + candidatoId + " não foi encontrado"));
        return resumo.getResumo();
    }
}
