# Diagrama de Componente



---

## Diagrama de Componentes

O diagrama de componentes mostra **os grandes módulos do sistema** e como os pacotes estão organizados dentro deles:

- **View** – contém o pacote `view` 
- **Interfaces (Inbound Adapters)** – contém o pacote `interfaces`.  
- **Application (Use Cases)** – contém o pacote `application`.  
- **Domain Core** – contém os pacotes `entities` e `ports`, representando o núcleo do domínio.  
- **Infrastructure (Outbound Adapters)** – contém o pacote `infrastructure`, implementando as portas definidas no domínio.

---

## Codificação do Diagrama
```plantuml
@startuml


skinparam packageStyle rectangle
skinparam linetype ortho
left to right direction

component "View" {

  package "view"

}


component "Interfaces\n(Inbound Adapters)" {

  package "interfaces"

}

component "Application\n(Use Cases)" {

  package "application"

}

component "Domain Core" {

  package "entities"
  package "ports"

}

component "Infrastructure\n(Outbound Adapters)" {

  package "infrastructure"

}

"View" --> "Interfaces\n(Inbound Adapters)"
"Interfaces\n(Inbound Adapters)" --> "Application\n(Use Cases)"
"Application\n(Use Cases)" --> "Domain Core"
"Domain Core" --> "Infrastructure\n(Outbound Adapters)"
@enduml
```
