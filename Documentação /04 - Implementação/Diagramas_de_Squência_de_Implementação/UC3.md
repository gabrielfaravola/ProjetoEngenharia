# Consultar Resumos de Interação na Mídia

[![](https://img.plantuml.biz/plantuml/svg/TLJDQjj04BuBz0w33Qrpo0iOOwAw40HE20cbbrmcksDxwNf7sLsP-6qAFVSf_6ATNLkk52Sw28cTxsU-6UacHFIntTgo2B-CQz3Z5aBSMvAxD-hYY5vRSehkA0HSKsNDse4Y5ycNHLcWYknXj30QzU4FZm03_0Z7inT-Wr6TAbUeAC2aTY_SEdqrWl7jXaFykJ6HoWBa4h5eb6dGHM53fuKmydUAQ3Vqx1RiecThoMU5HVHYafm6qJXBLKrZZS9esC4IzwbsB7uLVRFjHp8F5XtVyewyusVtH7udjPd_z7mfyS0-1lW2jLjvbBnQoSiBPhp4dTIclY-x4U_KQdwaXeEHkFPP8xt2R6QwVERp3x1rzKYXtNBIpQYwUe_fMKp1m_KynmDJaCQEw3kZ3KvKbsgjh_BWp0uZdR8iYwzmpv6cCZsfO-0k193vWpWFhO4qni1m-9ryA7OhzblP6sf11DQoBWYQG5aCWK9PP1_YfsEQmfeyVceAUgxE4oYB3Yr4ERCfM9EMM38vByfdpIL0MMJOzS38KYXctMIo5ObIqplWG5nQ7ZGmoJOwZj2NhQkoaFAXPFgyt1ecJuU_0Wa4MkPfD1bCs9vsGlUK_m-qOpZgGwItsA8TWdl17UCNUDwBALID5raNyQGAZT2bJJD1nhQYd60Oi1sNuqQEvBVm3m00)](https://editor.plantuml.com/uml/TLJDQjj04BuBz0w33Qrpo0iOOwAw40HE20cbbrmcksDxwNf7sLsP-6qAFVSf_6ATNLkk52Sw28cTxsU-6UacHFIntTgo2B-CQz3Z5aBSMvAxD-hYY5vRSehkA0HSKsNDse4Y5ycNHLcWYknXj30QzU4FZm03_0Z7inT-Wr6TAbUeAC2aTY_SEdqrWl7jXaFykJ6HoWBa4h5eb6dGHM53fuKmydUAQ3Vqx1RiecThoMU5HVHYafm6qJXBLKrZZS9esC4IzwbsB7uLVRFjHp8F5XtVyewyusVtH7udjPd_z7mfyS0-1lW2jLjvbBnQoSiBPhp4dTIclY-x4U_KQdwaXeEHkFPP8xt2R6QwVERp3x1rzKYXtNBIpQYwUe_fMKp1m_KynmDJaCQEw3kZ3KvKbsgjh_BWp0uZdR8iYwzmpv6cCZsfO-0k193vWpWFhO4qni1m-9ryA7OhzblP6sf11DQoBWYQG5aCWK9PP1_YfsEQmfeyVceAUgxE4oYB3Yr4ERCfM9EMM38vByfdpIL0MMJOzS38KYXctMIo5ObIqplWG5nQ7ZGmoJOwZj2NhQkoaFAXPFgyt1ecJuU_0Wa4MkPfD1bCs9vsGlUK_m-qOpZgGwItsA8TWdl17UCNUDwBALID5raNyQGAZT2bJJD1nhQYd60Oi1sNuqQEvBVm3m00)

---
## Codificação do Diagrama
```plantuml
@startuml
skinparam style strictuml
skinparam sequenceMessageAlign center

actor "Cidadão" as User

box "Interfaces (Inbound)" #GhostWhite
    participant "CandidatoDetalhe\nController" as Ctrl
end box

box "Application Layer" #AliceBlue
    participant "PlanoGoverno\nService" as Service
end box

box "Ports & Infrastructure" #Lavender
    participant "ConteudoRepository" as Repo <<interface>>
    participant "IAResumoPort" as IA <<interface>>
end box

User -> Ctrl : verResumoMidia(candidatoId)
activate Ctrl

' Note: Usando o Service que possui acesso à IA conforme seu diagrama de classes
Ctrl -> Service : gerarResumoMidia(candidatoId) 
activate Service

Service -> Repo : listarPorCandidato(candidatoId)
activate Repo
Repo --> Service : List<ConteudoMidia>
deactivate Repo

Service -> IA : gerarResumo(listaConteudos)
activate IA
IA --> Service : string (Síntese das ideias)
deactivate IA

Service --> Ctrl : string (Resumo textual)
deactivate Service

Ctrl --> User : Apresenta ideias principais defendidas
deactivate Ctrl
@enduml
```
