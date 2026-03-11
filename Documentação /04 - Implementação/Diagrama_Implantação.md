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
