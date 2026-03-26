# Visualizar Espectro Político
![](https://www.plantuml.com/plantuml/png/TPFFJXH14CRl_HILvI34R9OOJuPiM5Sago0Jq73nKZjTB1LxkyRkdWreV1ZZmPClu7LVp9fX_i0W5x9al-zNrVLzUnWJXbIkdObVs1SOS0qntJhIlu7puJVqjIIVqod5Y2kQELvvoCad2lTKcH8vvm9zoj1QzYjukMyCvaa2ZEPiqMv_oGWmmgUeJhEK6nYz3Q8SRqUmSye-pGFXsXGzgbV09Iq_-zqvUgki91c5AtQPBhHNGskTgOGARkWBdrI5EKNONVYbbFhjB5keOUVuMcAwlEP43yTr_3UKq5tJNAb1dADGJvcdu0P3PaNXECV4uk44RoaeNM-KqsjN3k4EO-GhLUSeHx6WNDaN53Qghl7Dvy64J4BI5_IlgHvmWXjLKHXC41MLLiwfaCX9mctDhVw5WmDk2TDfF-8PZCTZc6C8jC8r-IJ0Ne7em28KsvyhzbXfJ9KRZATtfuPNKAqICNGQIr3KgLIfymOJDQdS6SPghH3gf1jU4dJhj0EDfO6psUvSAyYHjx-rGjKOYrOYM05gZlZV_Q8unIOCiE5OekDls11wut3LTjTf7RL2Zey-mWGBdkHjC-BaEziVayU4kXoczgc_oL8HUHTxsEqe2xlNERlSJMjIVvMPcXt7X44hq3Np2KQbDwPsZH-CFr72GIPHRo7-Bk6iNEffPAe77_Yzw4VPKEXscddK7H0aGl404ZNulpKd6YlGRGK4bXA5TTK9lRkPAtubWR6jVAR19FNL9TI9zn_UN_vfcwNXvTgptql_nVu-dBs7v_3kuko3MWOnFUfag7zEsf6-L8MuxPzcug0dXwekr-uV)

---
## Codificação do Diagrama
```plantuml
@startuml
skinparam style strictuml
skinparam sequenceMessageAlign center
skinparam ParticipantPadding 40

actor "Cidadão" as User

box "Frontend" #MintCream
participant "Frontend Web\n(CandidatoPerfilPage)" as Front
end box

box "Interfaces (Inbound API)" #GhostWhite
participant "CandidatoDetalheController" as Ctrl
end box

box "Application Layer" #AliceBlue
participant "ClassificacaoEspectroService" as Service
end box

box "Ports (Interfaces)" #Lavender
participant "ConteudoRepository" as Repo <<interface>>
end box

' --- Carregamento inicial da página ---
User -> Front : acessar página de perfil
activate Front
Front --> User : exibe interface inicial
deactivate Front

' --- Requisição de dados do espectro ---
User -> Front : solicitar visualização do espectro
activate Front

Front -> Ctrl : GET /api/candidatos/{id}/espectro
activate Ctrl

Ctrl -> Service : classificar(candidatoId)
activate Service

Service -> Repo : listarPorCandidato(candidatoId)
activate Repo

Repo --> Service : List<PosicionamentoPublico>
deactivate Repo

note over Service
Analisa os posicionamentos públicos
do candidato e determina sua
CategoriaEspectroPolitico
end note

Service --> Ctrl : CategoriaEspectroPolitico
deactivate Service

Ctrl --> Front : 200 OK + JSON
deactivate Ctrl

Front --> User : renderiza espectro político
deactivate Front

@enduml
```
