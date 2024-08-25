# **DevSecOps CI/CD Pipeline for Spring Boot Web Application  🚀**

## ***Overview***
A comprehensive DevSecOps Continuous Integration/Continuous Deployment (CI/CD) pipeline designed to streamline the development, testing, packaging, and deployment of a Spring Boot web application. By integrating various tools and automating the workflow, we ensure efficient, secure, and reliable software delivery.

## ***Key Components***
### Spring Boot Web Application 🌱
- A sample static content website built using Spring Boot.
- Source code is managed in a Git repository.

### Build and Test 🛠️
- Maven is utilized for building the project and generating a JAR file.
- Unit tests are executed during the build process to verify application functionality.

### Dependency Check 🔍
- A dependency check is performed on the JAR file to identify potential security vulnerabilities or outdated libraries.

### Code Quality Analysis 📊
- SonarQube is employed for code quality, security, and maintainability analysis.
- Code coverage metrics are generated to assess test effectiveness.

### Containerization 🐳
- A Docker image is created to encapsulate the Spring Boot application.
- A Dockerfile defines the necessary steps for image creation.

### Image Security Scanning 🛡️
- Trivy scans the Docker image for vulnerabilities to identify potential security risks.

### Image Registry 📦
- The Docker image is pushed to a container registry (e.g., Docker Hub) for storage and distribution.

### Deployment 🚢
- ArgoCD is integrated to manage and deploy the application to a Kubernetes cluster.
- The deployment YAML file is updated with the latest image tag to trigger deployments.

### Monitoring and Alerting 📈🔔
- Prometheus collects metrics from the deployed application.
- Grafana visualizes the collected metrics through interactive dashboards.
- Alertmanager generates alerts based on metric thresholds and notifies the team via Gmail.

## ***Workflow 🔄***
#### *The CI/CD pipeline seamlessly integrates the aforementioned components to create a cohesive workflow:*

1. ***Code Commit***: Developers commit code changes to the Git repository, triggering the pipeline.
2. ***Build and Test***: Maven builds the project, runs unit tests, and generates a JAR file.
3. ***Dependency Check***: The JAR file is scanned for vulnerabilities.
4. ***Code Analysis***: SonarQube analyzes the codebase for quality and security issues.
5. ***Docker Image Creation***: A Docker image is built based on the Dockerfile.
6. ***Image Scanning***: Trivy scans the Docker image for vulnerabilities.
7. ***Image Push***: The Docker image is pushed to the container registry.
8. ***Deployment***: ArgoCD detects the image update and deploys the application to Kubernetes.
9. ***Monitoring***: Prometheus collects application metrics.
10. ***Visualization***: Grafana displays metrics through dashboards.
11. ***Alerting***: Alertmanager sends email notifications based on defined thresholds.

By following this end-to-end workflow, we achieve efficient development, thorough testing, robust security measures, and reliable deployment of the Spring Boot Web Application. 🌟😊

---

## ***Prerequisites 📋***
- ***Communication***: Gmail account for notifications (Slack integration optional).
- ***Infrastructure***:
  - Ansible for automation.
  - Docker and Kubernetes (Docker Desktop for Windows users).
  - Ubuntu operating system (WSL with Ubuntu for Windows users).
  - Minimum hardware: Core i5 quad-core or higher, 16 GB RAM.

## ***Software Installation 🚀***
#### *Make sure to install the following software components:*

1. **Build and Dependency Management**: 
   - ***Maven 3.9.6***
   - ***Java 11 or 17***

2. **Security**:
   - ***Trivy***
   - ***OWASP Dependency Check***

3. **Code Quality**:
   - ***SonarQube***

4. **Deployment**:
   - ***ArgoCD***
5. **Monitoring**:
   - ***Prometheus***
   - ***Grafana***

6. **Alerting**:
   - ***Alertmanager***
   - ***Gmail***

7. **CI/CD Orchestration**:
   - ***Jenkins***
---


## **DevOpsec Environment Setup 🚀**

1. **Clone the Git repository**:
   ```bash
   git clone https://github.com/badrivarun02/Spring-Boot-JavaWebApp-Project.git
   ```

2. **Install the Package using Ansible Playbook**:
   - Navigate to the `Installation_packages` folder:
     ```bash
     cd Spring-Boot-JavaWebApp-Project/Installation_packages
     ```
   - Run the Ansible playbook for installation:
     ```bash
     ansible-playbook -i inventory.ini Install_package.yaml
     ```
     Inside the playbook, I've specified role names, so it installs packages like:
     - `java_install`
     - `maven_install`
     - `jenkins_install`
     - `Trivy_install`
     - `argocd_install`

   - Launching the Applications:
     - Jenkins: Access Jenkins using the following URL: [http://localhost:9012](http://localhost:9012) 🛠️
     - ArgoCD: Use this URL to access ArgoCD: [http://localhost:8063](http://localhost:8063) 🚀
>***Note:*** The Ansible scripts display the Jenkins & ArgoCD password in the output during installation. Use it to log in to Jenkins & ArgoCD servers.

4. **Install Prometheus & Grafana, Alertmanager, and SonarQube Packages** using Ansible Playbook:
   - Prometheus & Grafana and Alertmanager:
      - Navigate to the **Spring-Boot-JavaWebApp-Project** folder:
     ```bash
     cd Installation_packages/Prometheus-Grafana-Alertmg
     ```
     Run the Ansible playbook:
     ```bash
     ansible-playbook -i inventory.ini Install_promeg.yaml
     ```
     Inside the playbook, I've specified role names, so it installs packages like:
     - `prometheus_install`
     - `grafana_install`
     - `alertmanager_install`

   - SonarQube:
      - Navigate to the **Spring-Boot-JavaWebApp-Project** folder:
     ```bash
     cd Installation_packages/SonarQube-Ansible
     ```
     Run the Ansible playbook:
     ```bash
     ansible-playbook --vault-password-file pss.txt maininstall.yaml -i inventory.ini
     ```

   - Launching the Applications:
     - Prometheus: Access Prometheus using the following URL: [http://localhost:9090](http://localhost:9090) 📈
     - Grafana: Use this URL to access Grafana: [http://localhost:3000](http://localhost:3000) 📊
     - SonarQube: Access SonarQube using the following URL: [http://localhost:9000/sonar](http://localhost:9000/sonar) 🔍
     - Alertmanager: Access Alertmanager at: [http://localhost:9093](http://localhost:9093) 🚨

 
5. **Integration Notes:**

   - Follow the detailed integration instructions provided in the [Comprehensive Guide to Integrating and Configuring Tools][integration-notes-link].

[integration-notes-link]: ./Project_notes/ComprehensiveGuidetoIntegratingandConfiguringToolsinJenkins.md

6. **Metrics Deployment on Kubernetes:**
 
   - Navigate to the **Spring-Boot-JavaWebApp-Project** folder:
      ```bash
      cd Metric_k8s
      ```

   - Deploy the Following Packages:
      ```bash
      kubectl create -f ./kubernetes-node-exporter/ -f ./kubernetes-blackbox-exporter/ -f ./kube-state-metrics/
      ```
   - Verification:
      - **Node-Exporter**: Access at [http://localhost:9100](http://localhost:9100)
      - **BlackBox-Exporter**: Access using the following URL: [http://localhost:9115](http://localhost:9115)
      - **Kube-state-metrics**: Access at [http://localhost:8080](http://localhost:8080)

7. **Prometheus & Altermanager Configuration Steps:**

    a. Alert Rules Configuration

     - Add the [alert_rules.yml][alert-rules-link] to the Prometheus directory.
     - This file contains specific rules for alerting.

      [alert-rules-link]: Installation_packages/Prometheus-Grafana-Alertmg/roles/configcopy/files/alert_rules.yml

    b. Prometheus Configuration

     - Edit the Prometheus configuration file [prometheus.yml][prometheus-config-link].
     - Add the Alertmanager URL.
     - Include the rules file (`alert_rules.yml`).
     - Define job names (e.g., `NodeExporter`, `BlackBox Exporter`, `Kube-State-Metrics`) under scrape configurations.
     - Ensure the website URL is configured for BlackBox Exporter.

      [prometheus-config-link]: Installation_packages/Prometheus-Grafana-Alertmg/roles/configcopy/files/prometheus.yml

    c. Alertmanager Configuration

     - In the [alertmanager.yml][alertmanager-config-link], update the Gmail user ID and password according to your specific use case.

      [alertmanager-config-link]: Installation_packages/Prometheus-Grafana-Alertmg/roles/configcopy/files/alertmanager.yml

    d. Deployment and Verification

      After editing these files, copy them to specific locations based on the instructions provided in the `tasks/main.yml` file under the `configcopy` role. By utilizing the `configcopy` role in your playbook, these changes will automatically propagate and trigger restarts of the relevant applications.

      To apply the changes, run the following command:

      ```bash
      cd Installation_packages/Prometheus-Grafana-Alertmg
      ```
       ansible-playbook -i inventory.ini configcopy.yaml
      

    Inside the playbook, I've specified role names, including `configcopy`.

Finally, verify that the rules and targets are correctly added/configured in the Prometheus tool.😊

---
## Step-by-Step Guide to Jenkins Pipeline Creation 🚀

### Step 1: Create a New Jenkins Job

1. Log in to Jenkins instance.
2. Click on "New Item" on the left-hand side.
3. Enter a name for your pipeline job and select "Pipeline" as the job type.
4. Click "OK".

### Step 2: Configure SCM

1. In the job configuration screen, under the "Build Triggers" section, tick:
   - [x] GitHub hook trigger for GITScm polling
2. You have two options:
   - **Pipeline script**: Write pipeline script directly in the Jenkins interface.
   - **Pipeline from SCM**: Retrieve the pipeline script from a version control system (e.g., Git, SVN).

   For this guide, we'll focus on the pipeline from SCM:
   - Choose your SCM (Git, SVN, etc.) from the dropdown.
   - Provide the necessary credentials for accessing repository.
   - Specify the repository URL.
   - Define the branch to use.
3. Specify Script Path:
   - Indicate the path to Jenkinsfile within the repository. This is typically named `Jenkinsfile` and placed at the root of project.


### Step 3: Save and Run the Pipeline

- Click "Save" to save the pipeline configuration.
- Click "Build Now" to trigger the pipeline execution.

### Step 4: Monitor Pipeline Execution

- You can view the pipeline's progress and logs in the build history.
---

  
## **DevSecOps Environment Cleanup**

1. **Uninstall the Package using Ansible Playbook**:
   - Navigate to the **Spring-Boot-JavaWebApp-Project** folder:
     ```bash
     cd Installation_packages
     ```
   - Run the Ansible playbook for installation:
     ```bash
     ansible-playbook -i inventory.ini Uninstall_package.yaml
     ```
     Inside the playbook, I've specified role names, so it uninstall packages like:
     - `java_uninstall`
     - `maven_uninstall`
     - `jenkins_uninstall`
     - `Trivy_uninstall`
     - `argocd_uninstall`

2. **Uninstall Prometheus & Grafana, Alertmanager, and SonarQube Packages** using Ansible Playbook:
   - Prometheus & Grafana and Alertmanager:
      - Navigate to the **Spring-Boot-JavaWebApp-Project** folder:
     ```bash
     cd Installation_packages/Prometheus-Grafana-Alertmg
     ```
     Run the Ansible playbook:
     ```bash
     ansible-playbook -i inventory.ini Uninstall_promeg.yaml
     ```
     Inside the playbook, I've specified role names, so it uninstall packages like:
     - `prometheus_uninstall`
     - `grafana_uninstall`
     - `alertmanager_uninstall`
      
   - SonarQube:
      - Navigate to the **Spring-Boot-JavaWebApp-Project** folder:
     ```bash
     cd Installation_packages/SonarQube-Ansible
     ```
     Run the Ansible playbook:
     ```bash
     ansible-playbook mainuninstall.yml -i inventory.ini
     ```
 3. Remove the metrics deployment packages on K8s:
       ```sh
       kubectl delete ns monitoring
       ```
       Additionals:
       ```
       kubectl delete clusterrolebinding kube-state-metrics && kubectl delete clusterrole kube-state-metrics
 4. Remove the web application on k8s:
      ```sh
      kubectl delete ns demo
      ```

---

## **Learning Resources 🚀**

1. **Integrate OWASP Dependency Check in Jenkins Pipeline** 🛡️
   - [Medium Article](https://sudheer-baraker.medium.com/integrate-owasp-dependency-check-in-jenkins-pipeline-748d8aefc2b7)

2. **Monitor Jenkins Metrics Using Prometheus and Grafana** 📊
   - [Medium Article](https://medium.com/geekculture/how-to-monitor-jenkins-metrics-using-prometheus-grafana-5901a6bcb789)
   - [Squadcast Question](https://www.squadcast.com/questions/how-to-use-jenkins-with-prometheus)
   - [Medium Article](https://medium.com/@sanskaragrawalla/monitor-your-build-server-jenkins-with-self-scripts-prometheus-6849bc863960)
   - [DevOpsArt Article](https://www.devopsart.com/2019/09/jenkins-monitoring-with-prometheus-and.html)

### Kubernetes Monitoring

1. **Kube State Metrics: Standard Examples** 📈
   - [GitHub Repository](https://github.com/kubernetes/kube-state-metrics/tree/main/examples/standard)
   - [Medium Article](https://medium.com/@seifeddinerajhi/monitoring-kubernetes-clusters-with-kube-state-metrics-2b9e73a67895)

3. **Kubernetes Monitoring with Prometheus and Sysdig** 🌐
   - [Sysdig Blog](https://sysdig.com/blog/kubernetes-monitoring-prometheus/)
   - [Opsramp Blog](https://www.opsramp.com/guides/prometheus-monitoring/prometheus-node-exporter/)

4. **Node Exporter for Kubernetes Monitoring** 🌐
   - [DevOpsCube Guide](https://devopscube.com/node-exporter-kubernetes/)

### Blackbox Exporter

1. **Monitoring Endpoints in Kubernetes with Blackbox Exporter** 🔍
   - [DevOpsCube Guide](https://devopscube.com/blackbox-exporter-on-kubernetes/)
   - [InfraCloud Blog](https://www.infracloud.io/blogs/monitoring-endpoints-kubernetes-blackbox-exporter/)
   - [GitHub Repository](https://github.com/prometheus/blackbox_exporter)

---
## **Acknowledgments: 🔗**

Huge thanks to the DevOps community. I’d like to express my gratitude to Aditya Jaiswal and Abhishek Veeramalla for their insightful content and contributions. Their videos and tutorials have been invaluable in my learning journey. I’m also thankful for the supportive teams at Copilot AI and Gemini AI. Your contributions have been invaluable!🙌😊

      
