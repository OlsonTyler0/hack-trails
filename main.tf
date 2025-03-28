# Configure the Linode provider
terraform {
  required_providers {
    linode = {
      source  = "linode/linode"
      version = "~> 2.0"
    }
  }
}

# Prompt for the Linode API token
variable "linode_api_token" {
  type        = string
  description = "Linode API Token"
  sensitive   = true
}

variable "root_password" {
  description = "Root password for the Linode server"
  type        = string
  sensitive   = true
}

# Configure the Linode provider with API token
provider "linode" {
  token = var.linode_api_token
}

# Read the SSH public key
data "local_file" "ssh_key" {
  filename = "/home/tyler/.ssh/ansible_key.pub"
}

# Create a Linode instance
resource "linode_instance" "ubuntu_server" {
  # Basic server configuration
  label     = "nginx-mysql-server"
  type      = "g6-standard-1"  # 2 vCPU, 4GB RAM for web and database
  region    = "us-east"        # Choose your preferred region
  image     = "linode/ubuntu24.04"
  root_pass = var.root_password

  # Add the SSH key
  authorized_keys = [
    chomp(data.local_file.ssh_key.content)
  ]
}

# Output the server's IP address
output "server_ip" {
  value = linode_instance.ubuntu_server.ip_address
}
