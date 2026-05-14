variable "vpc_cidr" {
    type = string
}

variable "vpc_name"{
    type = string
}

variable "public_subnet_cidr" {
    type = string
}

variable "public_subnet_name"{
    type = string
}

variable "private_subnet_cidr" {
    type = string
}

variable "private_subnet_name"{
    type = string
}

variable "private_subnet_database_cidr" {
    type = string
}

variable "private_subnet_database_name"{
    type = string
}

variable "alb_name" {
    type = string
}

variable "alb_target_group_backend_name"{
    type = string
}

variable "alb_target_group_front_name"{
    type = string
}

variable "alb_target_group_backend_port"{
    type = integer
}

variable "alb_target_group_front_port"{
    type = integer
}
