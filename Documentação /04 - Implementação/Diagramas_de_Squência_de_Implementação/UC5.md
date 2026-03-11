# Buscar Candidatos

[![](https://img.plantuml.biz/plantuml/svg/XPB1QW8n48Rl2_iEmmf53xw0YAXBAOA5qb9wwMLCHWsDoJR9YZvF7wKlrabqjsujxHwMX9b_lld_tQaFw4AzqtdctvIfqE4EV3XguhTJugf4xpKPGVVaFMvefjN6W20JoELPdg48ra5HAedoyyCMW1wU_QcsiWSe5h5pZO8yz1TcPMiZ1mNqxhRMXvUj2fHdm0_3WXAgGXEWcDTUO8b6yjHWNqrfJN1MQt9fVXaShqX60XDQqAogj18Ob3Mmn6FixV6sWkQw_Wt2GtsjmoNdYToU1GboFbzp7gmB7cvWOTOEERAQ8tEiwIrnpxtH-ZMhOJnIPRtYo8u94gym7giceycamuj1md2I3CC8j8hVhftb-uEKlzfZe7CeUPPwMNFU_pzPQpFF6WMBqrwDijCVAtcMwiCEPSczunR2FYJz45qGlZtz9RlOxUIAPIcI4TmUr8f0e9CUP3mqvZe3JfbCEL7-gRy0)](https://editor.plantuml.com/uml/XPB1QW8n48Rl2_iEmmf53xw0YAXBAOA5qb9wwMLCHWsDoJR9YZvF7wKlrabqjsujxHwMX9b_lld_tQaFw4AzqtdctvIfqE4EV3XguhTJugf4xpKPGVVaFMvefjN6W20JoELPdg48ra5HAedoyyCMW1wU_QcsiWSe5h5pZO8yz1TcPMiZ1mNqxhRMXvUj2fHdm0_3WXAgGXEWcDTUO8b6yjHWNqrfJN1MQt9fVXaShqX60XDQqAogj18Ob3Mmn6FixV6sWkQw_Wt2GtsjmoNdYToU1GboFbzp7gmB7cvWOTOEERAQ8tEiwIrnpxtH-ZMhOJnIPRtYo8u94gym7giceycamuj1md2I3CC8j8hVhftb-uEKlzfZe7CeUPPwMNFU_pzPQpFF6WMBqrwDijCVAtcMwiCEPSczunR2FYJz45qGlZtz9RlOxUIAPIcI4TmUr8f0e9CUP3mqvZe3JfbCEL7-gRy0)

---
## Codificação De Diagrama
```plantuml
@startuml
skinparam style strictuml
skinparam sequenceMessageAlign center

actor "Cidadão" as User

box "Interfaces (Inbound)" #GhostWhite
    participant "BuscaCandidato\nController" as Ctrl
end box

box "Application Layer" #AliceBlue
    participant "ConsultaCandidato\nService" as Service
end box

box "Ports & Infrastructure" #Lavender
    participant "CandidatoRepository" as Repo <<interface>>
end box

User -> Ctrl : listarCandidatos()
activate Ctrl

Ctrl -> Service : listarCandidatos()
activate Service

Service -> Repo : listar()
activate Repo
Repo --> Service : List<Candidato>
deactivate Repo

Service --> Ctrl : List<Candidato>
deactivate Service

Ctrl --> User : Exibe cards de candidatos
deactivate Ctrl
@enduml
```
