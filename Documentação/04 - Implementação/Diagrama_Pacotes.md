# Diagrama de Pacotes

[![](https://www.plantuml.com/plantuml/png/TLF1ZXCn3BtdAwAzS2gVWBQq2FM0L2tS40TluhRMPkmeSHQj43_4R_1ZPBHjPZBiN4OJl-VuFJk-pmf9on1CVYAEa62mWHZr9QALf1SnAj6gs4THbS5wIkYKX6S9-G9UVXAVxGb2HcDC1FS4PxHtn8hf10xpdVrbZBKkGCxsOyaETi2UFAZiX3L929WQeM5xL0WNd57Cxzdz46CW1wEWha3bvn9qgl4LqpCvR8HZ09RFyeo9_yEs385ohOa9sH4iyTtufHCvS22VSaIdIPQa1ynbaADaSYGC0t8JqLlmCW3n-ugJqg9FJNwlUuzNvItyJI8vgU0irjS-biTGAVCEAHOlNyWJjEY1FRd9UOkto6kX6aSkGzMdU9P4SEl2KG9f9RnfCKhIcx_sC2QF3nWbaqfwwV3Hr0hMRAyociqL-B2zpIbfhvdub21hAavBmkLKbeeFGmpTZDQmfdYLqIjUmatnraFKwpgOTtQF4Td__JFEA5jaJMWZE57CnanRQ3UR3tQsD6RsRpTrYtaaB9waMPpjvlk5l4V-yPfGC-OJxkzSWlF3LB7ljhb7zcK8_m00)

---
## Diagrama de Pacotes

O diagrama de pacotes mostra a **organização do código por pacotes**, evidenciando **dependências entre eles**:

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

interfaces --> application
application -down--> domain.entities
domain.entities -[hidden]down---> domain.ports
application --> domain.ports
domain.ports -down--> infrastructure

@enduml
```
