# Buscar Candidatos
![](https://www.plantuml.com/plantuml/png/VP91JXmz48NtFaLfMFoWNoqcAAi8ZO1MWaWWZ20HcsngsfMc5BVTiTq8SfieYno4YwLiy3JJ83Qj6TLxttjs-I14z77eZ0e_sFReiOCGxmp9rtCpdT3FWMn3PnG2jdHekBNGa8tadwYMGkI6UxHnYLgpRU7j_ENv6Tihrl4QtcmeZjnjSUxDbS8cEW-pcZNg-pzk1XZWQv1CjNAtCFlWdJImUWPRmekr9-nKFuQC2hYYrJUxNQFLmehkb8DKR6ad8xDA9PbW7zadulFViQ40soTsvGQP7YvFnB1rVEr2lBhcI9jXHqDeS1rH2zCxOyZdZ3fwCuaux7l33KPs5axnBWctv6OREZB312smC9WuqY_9tuWoenz_J-XBvwDq7y-Hgf_YZQZ8J-Y5Ua6z2noTlylWz1VszxaW5einup-egWfgz9vQxCX61so5Y0OqGd__ksMBIQFImg1QFDmol8FK9Q1VQpH1Kmg4j7A-mKYFItamLM9F677JBQy8rfLAgDAqwImDB-JbSk3xl_9-KZF38KuoNsmPd15mHF_WQAbxlMXfkiYR5i3n-o-mYptllk396gNILnnbWMBAMRvUExPtHazPiofoSUOD5TiJSHweFAqs0jB3tr_p5qhJfcM4bvEyvYdWSfGdzxStdyFv9_WVFbwUVnRBvEZFzkhpk-HV20rw7IQN_Mox1o8VElCF)

---
## Codificação do Diagrama
```plantuml
@startuml
skinparam style strictuml
skinparam sequenceMessageAlign center
skinparam ParticipantPadding 40
skinparam ParticipantMinWidth 140
skinparam BoxPadding 20

actor "Cidadão" as User

box "Frontend" #MintCream
participant "Frontend Web\n(CandidatoListPage)" as Front
end box

box "Interfaces (Inbound API)" #GhostWhite
participant "BuscaCandidatoController" as Ctrl
end box

box "Application Layer" #AliceBlue
participant "ConsultaCandidatoService" as Service
end box

box "Ports (Interfaces)" #Lavender
participant "CandidatoRepository" as Repo <<interface>>
end box

' --- Carregamento inicial da página ---
User -> Front : acessar página de candidatos
activate Front
Front --> User : exibe interface inicial
deactivate Front

' --- Requisição da lista de candidatos ---
User -> Front : solicitar listagem de candidatos
activate Front

Front -> Ctrl : GET /api/candidatos
activate Ctrl

Ctrl -> Service : listarCandidatos()
activate Service

Service -> Repo : listar()
activate Repo
Repo --> Service : List<Candidato>
deactivate Repo

Service --> Ctrl : List<Candidato>
deactivate Service

Ctrl --> Front : 200 OK + JSON
deactivate Ctrl

Front --> User : renderiza cards de candidatos
deactivate Front

@enduml
```
