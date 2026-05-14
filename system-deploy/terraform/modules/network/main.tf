resource "aws_vpc" "main_project" {
  cidr_block =
  tags = {
      Name =
  }
}

resource "aws_subnet" "public_subnet" {
    vpc_id =
    cidr_block =
    tags = {
        Name =
    }
}

resource "aws_subnet" "private_subnet" {
    vpc_id =
    cidr_block =
    tags = {
        Name =
    }
}

resource "aws_subnet" "subnet_database" {
    vpc_id =
    cidr_block =
    tags = {
        Name =
    }
}

resource "aws_lb" "alb"{
    name =
    internal = false
    load_balancer_type = "application"
    security_groups = []
    subnets = []
}

resource "aws_lb_target_group" "target_group_backend" {
  name     =
  port     =
  protocol = "HTTP"
  vpc_id   = aws_vpc.main_project.id
}

resource "aws_lb_target_group" "target_group_frontend" {
  name     =
  port     =
  protocol = "HTTP"
  vpc_id   = aws_vpc.main_project.id
}

//Configurar listener e listener rule, considerando subdominio
//Configurar o ASG