# Trail Search Web Application Deployment

This repository contains Ansible playbooks and roles for deploying a Java-based trail search web application using Apache, Tomcat 10, and Java servlets.

This was created during the USITCC "Hackathon" competition in the 2025 Year.

## Infrastructure

- Web Server: Apache2 with mod_jk
- Application Server: Tomcat 10
- Java Version: OpenJDK 21
- SSL: Managed by Certbot

## Prerequisites

- Ansible installed on the control machine
- Target Ubuntu server
- SSH access to the target server

## Configuration

### Inventory

Create an inventory file based on `inventory.ini.sample`:

```ini
[website]
your-server-ip ansible_user=root ansible_ssh_private_key_file=/path/to/ssh/key

[website:vars]
mysql_username=your_username
mysql_password=your_password
mysql_database=trails
mysql_host=localhost
```

##  Project Structure
- playbooks/ - Contains main playbook configurations
- roles/ - Ansible roles including:
    - apache - Apache web server configuration
    - certbot - SSL certificate management
    - geerlingguy.mysql - MySQL database setup

## Features
- Automated deployment of Java web application
- SSL certificate installation and configuration
- Apache and Tomcat integration
- MySQL database setup
- Java servlet compilation and deployment

## Deployment
- To deploy the application:

`ansible-playbook -i inventory.ini playbooks/apache.yml`

## Components
- Frontend static files served from /var/www/html/
- Java servlets deployed to Tomcat at /var/lib/tomcat10/webapps/
- SSL certificates managed by Certbot
- Database management through geerlingguy.mysql role
