# Diagrama de Classes de Negócios
[![](https://img.plantuml.biz/plantuml/svg/ZP8n3iCW34LtJX5bh9IXDuYKLbM7IXbw0GeUa8WTWTDUloYGA526Jk3FuvjlqNkMZfV92g6izBuP96gZ9LEgHokHBd2ZDpZSn2VDHj5wj67aZJA4SW9a6fUN3S1cISYmQ7eORMJIxg6BWe700QfTlVeP53iQoHe-6YGizXLYVsJJdjkcq_J1RjqMhytB20Vmr6O9Ihj8r1DLrUErF6p5EYm_CUjOEQEYZcXgMWpe42yRKONz8r5XtWFgy6E-)](https://editor.plantuml.com/uml/ZP8n3iCW34LtJX5bh9IXDuYKLbM7IXbw0GeUa8WTWTDUloYGA526Jk3FuvjlqNkMZfV92g6izBuP96gZ9LEgHokHBd2ZDpZSn2VDHj5wj67aZJA4SW9a6fUN3S1cISYmQ7eORMJIxg6BWe700QfTlVeP53iQoHe-6YGizXLYVsJJdjkcq_J1RjqMhytB20Vmr6O9Ihj8r1DLrUErF6p5EYm_CUjOEQEYZcXgMWpe42yRKONz8r5XtWFgy6E-)

---
## Codificação do Diagrama
```plantuml
@startuml

class Candidato
class PlanoDeGoverno
class TopicoPlano
class PosicionamentoPublico
class ConteudoMidia
class IndiceCoerencia
class EspectroPolitico
class CoerenciaTopico

Candidato "1" -down- "1" PlanoDeGoverno
PlanoDeGoverno "1" -- "*" TopicoPlano

Candidato "1" -- "*" PosicionamentoPublico
PosicionamentoPublico "1" -- "*" ConteudoMidia

Candidato "1" -- "1" EspectroPolitico

Candidato "1" -- "1" IndiceCoerencia
IndiceCoerencia "1" - "*" CoerenciaTopico

TopicoPlano "1" -down- "*" CoerenciaTopico
PosicionamentoPublico "1" -- "*" CoerenciaTopico

@enduml
```
