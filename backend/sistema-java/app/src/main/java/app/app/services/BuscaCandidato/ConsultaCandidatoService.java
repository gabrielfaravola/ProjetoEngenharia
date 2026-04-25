package app.app.services.BuscaCandidato;

import app.app.domain.Candidato.Candidato;
import app.app.domain.Candidato.Cargo;
import app.app.repository.CandidatoJpaRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaCandidatoService {

    private final CandidatoJpaRepository candidatoRepository;

    public ConsultaCandidatoService(CandidatoJpaRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    //tratar melhor condicoes de erro e regras de negocio
    public List<Candidato> listarComCriteria(String nome, Integer ano, String partido, Cargo cargo, String estado, String cidade){
        Specification<Candidato> spec = Specification.where(null);

        if(nome != null && !nome.isBlank()){
            spec = spec.and(CandidatoSearchCriteria.nome(nome));
        }
        if(ano != null){
            spec = spec.and(CandidatoSearchCriteria.ano(ano));
        }
        if(partido != null && !partido.isBlank()){
            spec = spec.and(CandidatoSearchCriteria.partido(partido));
        }
        if(cargo != null){
            spec = spec.and(CandidatoSearchCriteria.cargo(cargo));
        }
        if(estado != null){
            spec = spec.and(CandidatoSearchCriteria.estado(estado));
        }
        if(cidade != null){
            spec = spec.and(CandidatoSearchCriteria.cidade(cidade));
        }

        return this.candidatoRepository.findAll(spec);
    }

    public List<Integer> listarAnos(){
        return candidatoRepository.getAnosDistintos();
    }

    public List<String> listarPartidos(){
        return candidatoRepository.getPartidosDistintos();
    }

    public List<String> listarEstados(){
        return candidatoRepository.getEstadosDistintos();
    }

    public List<String> listarCidades(){
       return candidatoRepository.getCidadesDistintos();
    }

}
