# Diagrama de Classe de Implementação


---

### Descrição 

O sistema é organizado em camadas:

#### 1. Interfaces (Inbound Adapters)

  - Controllers expõem endpoints para busca de candidatos e detalhes de seus planos, espectro político e coerência.

  - Exemplo: BuscaCandidatoController delega chamadas para ConsultaCandidatoService.

#### 2. Application Layer

  - Contém services que implementam a lógica de aplicação, coordenando a comunicação entre os repositórios, adaptadores e entidades de domínio.

  - Exemplo: PlanoGovernoService busca planos e gera resumos via IA (IAResumoPort).

#### 3. Domain Layer

  - Define as entidades centrais (Candidato, PlanoDeGoverno, TopicoPlano, etc.) e enums (CategoriaEspectroPolitico), representando o núcleo do negócio.

  - As entidades mantêm relacionamentos importantes, como cada candidato possuindo um plano de governo, índice de coerência e categoria política.

#### 4. Ports (Interfaces)

  - Interfaces que abstraem dependências externas, como repositórios de dados e adaptadores de IA.

  - Permitem que a Application Layer não dependa diretamente da implementação de infraestrutura.

#### 5. Infrastructure (Outbound Adapters)

  - Implementações concretas das interfaces de portas (RepositoryImpl, IAResumoAdapter) que interagem com bancos de dados ou serviços externos.

#### 6. View

 - Classes que representam a interface gráfica do usuário

---

## Codificação do Diagrama
```plantuml
@startuml

skinparam packageStyle rectangle
skinparam linetype ortho
left to right direction

package "View" {

class CandidatoListView {
        controller: BuscaCandidatoController
        
        exibirListaCandidatos()
        selecionarCandidadto(id: int)
    }

    class CandidatoPerfilView {
        controller: CandidatoDetalheController
        
        exibirPlano(candidatoId: int)
        exibirEspectro(candidatoId: int)
        exibirCoerencia(candidatoId: int)
        exibirResumoPosicionamentos(candidatoId: int)
    }

}

package "Interfaces (Inbound Adapters)" {

class BuscaCandidatoController {
 consultaCandidatoService: ConsultaCandidatoService

 buscarCandidato(id: int)
 listarCandidatos()
}

class CandidatoDetalheController {
 planoService: PlanoGovernoService
 espectroService: ClassificacaoEspectroService
 coerenciaService: AnaliseCoerenciaService

 verPlano(candidatoId: int)
 verEspectro(candidatoId: int)
 verCoerencia(candidatoId: int)
 verResumoPosicionamentos(candidatoId: int)
}

}

package "Application Layer" {

class ConsultaCandidatoService {
 candidatoRepository: CandidatoRepository

 buscarCandidato(id: int): Candidato
 listarCandidatos(): List<Candidato>
}

class PlanoGovernoService {
 planoRepository: PlanoRepository
 iaResumoPort: IAResumoPort

 buscarPlano(candidatoId: int): PlanoDeGoverno
 gerarOuBuscarResumo(candidatoId: int): String
}

class AnaliseCoerenciaService {
 topicoPlanoRepository: TopicoPlanoRepository
 conteudoRepository: ConteudoRepository

 calcularIndice(candidatoId: int): IndiceCoerencia
}

class ClassificacaoEspectroService {
 conteudoRepository: ConteudoRepository

 classificar(candidatoId: int): CategoriaEspectroPolitico
}

class ResumoPosicionamentoService {
 conteudoRepository: ConteudoRepository
 
 buscarResumosPosicionamentos (candidatoId: int)
}

}

package "Domain Layer" {

package "Entities" {

class Candidato {
 id: int
 nome: String
 partido: String
 biografia: String
 anoEleicao: int
 indiceCoerencia : float
}

class PlanoDeGoverno {
 id: int
 titulo: String
 resumo: String
 dataPublicacao: Date
}

class TopicoPlano {
 id: int
 titulo: String
 descricao: String
 resumo: String
}

class PosicionamentoPublico {
 id: int
 tema: String
 descricao: String
 data: Date
 fonte: String
}

class CoerenciaTopico {
 id: int
 topico: String
 indice: float
}

enum CategoriaEspectroPolitico {
 EXTREMA_ESQUERDA
 ESQUERDA
 CENTRO_ESQUERDA
 CENTRO
 CENTRO_DIREITA
 DIREITA
 EXTREMA_DIREITA
}
}
}

package "Ports (Interfaces)" {

interface CandidatoRepository {
 buscarPorId(id: int): Candidato
 listar(): List<Candidato>
}

interface PlanoRepository {
 buscarPorCandidatoId(candidatoId: int): PlanoDeGoverno
 salvar(plano: PlanoDeGoverno)
}

interface TopicoPlanoRepository {
 listarPorPlano(planoId: int): List<TopicoPlano>
}

interface ConteudoRepository {
 listarPorCandidato(candidatoId: int): List<PosicionamentoPublico>
}

interface IAResumoPort {
 gerarResumo(plano: PlanoDeGoverno): String
}

}

package "Infrastructure (Outbound Adapters)" {

class CandidatoRepositoryImpl
class PlanoRepositoryImpl
class TopicoPlanoRepositoryImpl
class ConteudoRepositoryImpl
class IAResumoAdapter

}

' Inbound → Application

BuscaCandidatoController --> ConsultaCandidatoService

CandidatoDetalheController --> PlanoGovernoService
CandidatoDetalheController --> ClassificacaoEspectroService
CandidatoDetalheController --> AnaliseCoerenciaService
CandidatoDetalheController --> ResumoPosicionamentoService

' Application → Ports

ConsultaCandidatoService --> CandidatoRepository

PlanoGovernoService --> PlanoRepository
PlanoGovernoService --> IAResumoPort
PlanoGovernoService --> TopicoPlanoRepository

AnaliseCoerenciaService --> TopicoPlanoRepository
AnaliseCoerenciaService --> ConteudoRepository

ClassificacaoEspectroService --> ConteudoRepository
ResumoPosicionamentoService --> ConteudoRepository
ResumoPosicionamentoService --> IAResumoPort


' Ports → Infrastructure

CandidatoRepository <|.. CandidatoRepositoryImpl
PlanoRepository <|.. PlanoRepositoryImpl
TopicoPlanoRepository <|.. TopicoPlanoRepositoryImpl
ConteudoRepository <|.. ConteudoRepositoryImpl
IAResumoPort <|.. IAResumoAdapter

' Domain relationships

Candidato "1" -- "1" CategoriaEspectroPolitico : possui >
Candidato "1" -- "1" PlanoDeGoverno : possui >
Candidato "1" -- "*" PosicionamentoPublico : possui >

PlanoDeGoverno "1" *-- "*" TopicoPlano : contém

TopicoPlano "1" *-- "*" CoerenciaTopico : contém
PosicionamentoPublico "1" *-- "*" CoerenciaTopico : contém

' View Relations

CandidatoListView --> BuscaCandidatoController
CandidatoPerfilView --> CandidatoDetalheController

'Layer Relation 
"Application Layer" -up-> "Entities"

@enduml
```
