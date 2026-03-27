# Visualizar Resumo de Plano de Governo
![](https://www.plantuml.com/plantuml/png/ZLHDRnen4BtxLup8eQ0AGQEUeWY5a3QYpGTAMkNIox2UWbjZRss3abRzCL4F_I7yiOwzU84DXt90k_9xRzxCFE-P3-Z2Sg65_wvCYGuNuCEJ9lvtgcYUq8ybcOAkoNkSqL2hcO62J22tWvgmeYfKYIPCK4fbPl2sV_ZyMfa79SCStkmXpkrZPXxtXS0YM0UjaP8ertzi2z33PyyrnTG-Gkkzi-p0o1OSiLuOESA5ABT5jWXueEaNqnwXaQmLx8JSLwKdt4edYIQSY40MtgYFJM0K5kIXFJPJk-JJuMJCXAFBkVNXOQu2xPUhzIyee9xJY5MTrPfSgZ8AJZUA3CjIgmA3iWQky2a2ZtYw1PthPKDyej7OIxiYP-mzkHM3akhckI4yiIuauxc9wFiALumYTq3uZahh5O_xAOd6LpWzLPa-6EnpniCxyik5ZMKIOJnim6ixhw3RxS88dQCPBiW42ygm46gG2ENwUQOCHeo8guNkeDe6d42qxT7L66cXo1EEsL0h3BJPNSNfCZkgC9aUrPIWTfHh2adxp6pmZYEkl5h_vQ35IYxr5v_AE1-G1BDg-0UzUgjPFw1h4fj6izD1YWCpBzzzWXwMgbVtvdk_bFpTI_JkfcWloUwefJ29-CzQEGGiDoE7xdPvllG5kcf9xLfvB3krGXqSaScia_P-0jF4vkMEjip3AeaX4gsxvoEbwe8sWQt7dXa2TSYZ-hP-ZkloWGJmhxQpdT5zSF6RqAxwwGZIdZ9RcOfhclH136KQI3s8DD9EWcKx34hlus6ZWLppngvo6Ygcf5rkesQUe4UzGfSBaf52_4TVafhRoIlUYTfnlm-t7-4rVBY_lM5A8n8lRe9BTrxzn6OqNzw5CuRob_yV)

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
participant "Frontend Web\n(CandidatoPerfilPage)" as Front
end box

box "Interfaces (Inbound API)" #GhostWhite
participant "CandidatoDetalheController" as Ctrl
end box

box "Application Layer" #AliceBlue
participant "PlanoGovernoService" as Service
end box

box "Ports (Interfaces)" #Lavender
participant "PlanoRepository" as Repo <<interface>>
participant "IAResumoPort" as IA <<interface>>
end box

' --- Carregamento inicial da página ---
User -> Front : acessar página do candidato
activate Front
Front --> User : exibe interface inicial
deactivate Front

' --- Requisição do resumo do plano de governo ---
User -> Front : solicitar resumo do plano
activate Front

Front -> Ctrl : GET /api/candidatos/{id}/plano-governo/resumo
activate Ctrl

Ctrl -> Service : gerarOuBuscarResumo(candidatoId)
activate Service

Service -> Repo : buscarPorCandidatoId(candidatoId)
activate Repo
Repo --> Service : PlanoDeGoverno
deactivate Repo

alt resumo já existe
    Service --> Ctrl : String (Resumo)
else resumo inexistente
    Service -> IA : gerarResumo(plano)
    activate IA
    IA --> Service : String (Novo resumo)
    deactivate IA

    Service -> Repo : salvar(plano)
end

Service --> Ctrl : String (Resumo)
deactivate Service

Ctrl --> Front : 200 OK + JSON
deactivate Ctrl

Front --> User : renderiza resumo do plano
deactivate Front

@enduml
```
