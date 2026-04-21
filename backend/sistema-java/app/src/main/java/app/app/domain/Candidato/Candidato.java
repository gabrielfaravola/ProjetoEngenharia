package app.app.domain.Candidato;

import GraficoCategoria.Categoria;
import app.app.domain.Plano.PlanoDeGoverno;
import app.app.domain.Posicionamento.PosicionamentoPublico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "candidato")
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String partido;
    private String linkFoto;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    private Float indiceDeCoerencia;
    private Integer anoDisputado;

    //VER COMO FAZER RELACOES COM ENUM (CATEGORIA PRECISA DE CANDIDATO, INDICEDE APARIÇÃO)
    //Lista de Categorias

    @OneToOne(mappedBy = "candidato")
    private PlanoDeGoverno planoDeGoverno;

    @Enumerated(EnumType.STRING)
    private CategoriaEspectroPolitico categoriaEspectroPolitico;

    @OneToMany(mappedBy = "candidato")
    private List<PosicionamentoPublico> posicionamentoPublicoList;

}
