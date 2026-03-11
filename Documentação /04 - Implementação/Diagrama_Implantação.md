# Diagrama de Implantação



---

## Descrição

O diagrama representa a arquitetura de implantação do sistema de democratização da informação política na AWS:

- O **usuário** acessa a aplicação via **ELB (Elastic Load Balancer)**, que distribui requisições para a **EC2 principal** na **subnet privada**.
- Na EC2, a aplicação roda em **containers Docker**:
  - **Spring Boot API** com seus componentes (autenticação, lógica de negócios, etc.).
- A aplicação interage com o **RDS PostgreSQL** para persistência de dados e com o **S3** para armazenamento de vídeos e PDFs.
- A **EC2 principal** aciona a **Lambda Function** quando é necessário gerar planos de governo, que acessa IA e banco de dados.
- A separação entre **subnets públicas e privadas** garante segurança, mantendo recursos sensíveis isolados da internet.

---
## Codificação do Diagrama
```plantuml
@startuml
skinparam shadowing true
skinparam rectangle {
  RoundCorner 15
}

'--- Usuário ---
node "Usuário" as User <<device>> {
}


'--- AWS Cloud ---
package "AWS Cloud" {

    ' Public Subnet
    package "Public Subnet" as PublicSubnet {
        node "ELB" as ELB <<load_balancer>>
    }

    ' Private Subnet
    package "Private Subnet" as PrivateSubnet {
        node "EC2 - Aplicação Principal" as EC2App <<server>>{
           node "Docker Container - App" as DockerApp <<artifact>> {
                component "Infrastructure\n(Outbound Adapters)"
                component "Application\n(Use Cases)"
                component "Domain Core"
                component "Interfaces\n(Inbound Adapters)"
                component "View"
            }
        }
        node "EC2 - Serviço IA" as EC2IA <<server>>
        node "RDS PostgreSQL" as RDS <<database>>
    }

    ' Serviços gerenciados fora da VPC
    node "Lambda - Geração de Planos" as Lambda <<lambda>>
    node "S3 - Vídeos/PDFs" as S3 <<storage>>
}

'--- Fluxos simplificados e claros ---
User --> ELB : HTTP/HTTPS
ELB --> EC2App : Requisições da aplicação

EC2App --> EC2IA : Processamento IA
EC2App --> RDS : CRUD de dados
EC2App --> S3 : Upload/Download conteúdos
EC2App --> Lambda : Aciona geração de planos


Lambda --> RDS : Grava dados

@enduml
```
