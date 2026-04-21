package app.app.application.port;

import app.app.domain.Candidato.Candidato;

import java.util.List;

public interface CandidatoRepository {
    List<Candidato> listar();
}
