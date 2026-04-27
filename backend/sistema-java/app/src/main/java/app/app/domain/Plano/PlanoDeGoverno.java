package app.app.domain.Plano;

import app.app.domain.Candidato.Candidato;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "plano_governo")
@NoArgsConstructor
@AllArgsConstructor
public class PlanoDeGoverno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;
    private String resumo;

    @OneToMany(mappedBy = "plano")
    private List<TopicoPlano> topicoPlanoList;

}
