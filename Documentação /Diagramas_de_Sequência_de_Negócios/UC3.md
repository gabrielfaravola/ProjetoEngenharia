# Consultar Resumos de Interação na Mídia

[![](https://img.plantuml.biz/plantuml/svg/VP71IiD048RlWRp3iBsqXwBd8gMnY1GKH50Fuc6oEqq6azsOsLJVnuCdNy1hNipPzbGLJmkp_Dzy--zA8lPnQ9iyasVs7VROWjJemYlx2hRO21rTqSj0tj8rYM15HSEL1qi-KfzdUOOsXXxCcXswyJqOG84xeHucLrpLyRmPbAQao9Oxz17C9aMtQ0ccHUHT2XBSZ5zbmnPd1y069fTraFXGSwJzcY46FxIbWirPkNmyVJ9flDyqNwR00WgR505LUVnG5p2_-MPlp3kCf245R62UyecpW8itBWaQref0Gxlnqr4G8EZKHH_l1MneAA93oJD7HwWVDkkq3gW5Sw_JMvAXrNQc1-P90ihiNvcYwqcqPqoDi2FMe-lPfnvPr6zBtl7V9YkzqY_-1W00)](https://editor.plantuml.com/uml/VP71IiD048RlWRp3iBsqXwBd8gMnY1GKH50Fuc6oEqq6azsOsLJVnuCdNy1hNipPzbGLJmkp_Dzy--zA8lPnQ9iyasVs7VROWjJemYlx2hRO21rTqSj0tj8rYM15HSEL1qi-KfzdUOOsXXxCcXswyJqOG84xeHucLrpLyRmPbAQao9Oxz17C9aMtQ0ccHUHT2XBSZ5zbmnPd1y069fTraFXGSwJzcY46FxIbWirPkNmyVJ9flDyqNwR00WgR505LUVnG5p2_-MPlp3kCf245R62UyecpW8itBWaQref0Gxlnqr4G8EZKHH_l1MneAA93oJD7HwWVDkkq3gW5Sw_JMvAXrNQc1-P90ihiNvcYwqcqPqoDi2FMe-lPfnvPr6zBtl7V9YkzqY_-1W00)

---
# Codificação de Diagrama
```plantuml
@startuml
skinparam shadowing false
skinparam sequenceMessageAlign center

actor "Cidadão" as User #LightBlue
participant "Interface (Atividade Pública)" as UI #GhostWhite

autonumber "<b>[0]"

User -> UI : Acessa a seção "Atividade Pública"
activate UI
UI --> User : Exibe lista de vídeos e postagens coletadas
deactivate UI

User -> UI : Clica em "Ver Resumo" (vídeo/posts)
activate UI
note right of UI #FFFFFF: Processa síntese textual
UI --> User : Apresenta as ideias principais defendidas
deactivate UI

@enduml
```
