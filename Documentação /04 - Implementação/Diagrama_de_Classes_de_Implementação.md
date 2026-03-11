# Diagrama de Classe de Implementação
<img width="1181" height="767" alt="dLLBQnin4BuR_1yMdzeQ7ter8QGu9WHQC4tfNPR6Pa2hMIHjC7tyzqhxy4hZLI3rnUmylldqpMZquBomlgtrSh5SI2sSgtR2A5J2K_LxkQZ2RuDgMw7nmvUX6hRLYxTepeEf2HYeY5cFI6ShJYYONHZQQq0fA85Tdy4VT70zmXEzWZNquUEsoYr993cKI4RKO3ov6FeLdR_BxOVsg57I_P" src="https://github.com/user-attachments/assets/bd10a751-1e9f-4a4c-827d-947f00a8e210" />

---
## Codificação do Diagrama
```plantuml

@startuml

skinparam packageStyle rectangle
skinparam linetype ortho
left to right direction

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

}

package "Domain Layer" {

package "Entities" {

class Candidato {
 id: int
 nome: String
 partido: String
 biografia: String
 anoEleicao: int
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

class ConteudoMidia {
 id: int
 titulo: String
 url: String
 tipo: String
 dataPublicacao: Date
}

class IndiceCoerencia {
 id: int
 indiceGeral: float
 dataCalculo: Date
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
 listarPorCandidato(candidatoId: int): List<ConteudoMidia>
}

interface IAResumoPort {
 gerarResumo(plano: PlanoDeGoverno): String
}

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

' Application → Ports

ConsultaCandidatoService --> CandidatoRepository

PlanoGovernoService --> PlanoRepository
PlanoGovernoService --> IAResumoPort

AnaliseCoerenciaService --> TopicoPlanoRepository
AnaliseCoerenciaService --> ConteudoRepository

ClassificacaoEspectroService --> ConteudoRepository

' Ports → Infrastructure

CandidatoRepository <|.. CandidatoRepositoryImpl
PlanoRepository <|.. PlanoRepositoryImpl
TopicoPlanoRepository <|.. TopicoPlanoRepositoryImpl
ConteudoRepository <|.. ConteudoRepositoryImpl
IAResumoPort <|.. IAResumoAdapter

' Domain relationships

Candidato "1" -- "1" PlanoDeGoverno
Candidato "1" -- "1" IndiceCoerencia
Candidato "1" -- "1" CategoriaEspectroPolitico

PlanoDeGoverno "1" -- "*" TopicoPlano

Candidato "1" -- "*" PosicionamentoPublico
PosicionamentoPublico "1" -- "*" ConteudoMidia

IndiceCoerencia "1" -- "*" CoerenciaTopico
TopicoPlano "1" -- "*" CoerenciaTopico

@enduml
```
