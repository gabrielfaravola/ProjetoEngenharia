# Diagrama de Pacotes


---
## Diagrama de Pacotes

O diagrama de pacotes mostra a **organização do código por pacotes**, evidenciando **dependências entre eles**:

- **view** - contém as clsses referentes a Interface Gráfica do Usuário (GUI).
- **interfaces (Inbound Adapters)** – contém os controllers que recebem as requisições do usuário.  
- **application (Use Cases)** – implementa os casos de uso, orquestrando a lógica de negócio.  
- **domain.entities** – contém as entidades do domínio que representam o core da aplicação.  
- **domain.ports** – interfaces que definem contratos que serão implementados pela infraestrutura.  
- **infrastructure** – implementações técnicas de repositórios e integrações externas (adapters).
  

---

## Codificação do Diagrama
```plantuml
@startuml


skinparam linetype ortho
top to bottom direction
skinparam shadowing false

package "view" {
 class CandidatoListView
 class CandidatoPerfilView
}
package "interfaces" {

  class BuscaCandidatoController
  class CandidatoDetalheController

}

package "application" {

  class ConsultaCandidatoService
  class PlanoGovernoService
  class AnaliseCoerenciaService
  class ClassificacaoEspectroService
  class ResumoPosicionamentoService

}

package "domain.entities" {

  class Candidato
  class PlanoDeGoverno
  class TopicoPlano
  class PosicionamentoPublico
  class ConteudoMidia
  class IndiceCoerencia
  class CoerenciaTopico
  enum CategoriaEspectroPolitico

}

package "domain.ports" {

  interface CandidatoRepository
  interface PlanoRepository
  interface TopicoPlanoRepository
  interface ConteudoRepository
  interface IAResumoPort

}

package "infrastructure" {

  class CandidatoRepositoryImpl
  class PlanoRepositoryImpl
  class TopicoPlanoRepositoryImpl
  class ConteudoRepositoryImpl
  class IAResumoAdapter

}

' Dependências entre pacotes

view --> interfaces
interfaces --> application
domain.ports --> infrastructure
application --> domain.entities
application --> domain.ports

@enduml
```
