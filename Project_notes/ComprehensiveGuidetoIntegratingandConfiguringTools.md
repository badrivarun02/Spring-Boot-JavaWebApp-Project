# **Comprehensive Guide to Integrating and Configuring Tools**

## **Installing Required Jenkins Plugins:**

1. Log in to Jenkins server.
2. Navigate to **Manage Jenkins** > **Manage Plugins**.
3. Click on the **Available** tab.
4. Search for the following plugins and install them:
   - :mag_right: **OWASP Dependency-Check**
   - :whale: **Docker**
   - :whale: **Docker Pipeline**
   - :mag_right: **SonarQube Scanner**
   - :mag_right: **Prometheus metrics**

>***Important:*** After installing the SonarQube & Prometheus plugin, a Jenkins restart is required.

---

## **GitHub Webhook Integration with Jenkins 🚀**

### ***Prerequisites 🛠️***

Before start, make sure you have the following:

1. An existing GitHub account and a project repository.
2. Jenkins installed and running on system.

>***Note:*** The Git plugins come pre-installed with Jenkins, simplifying the setup process.

### ***Configuration Steps 🔧***

Follow these steps to configure the GitHub webhook with Jenkins:

1. **Copy the Jenkins URL**:
   - For example, `http://localhost:9012` or 'http://<anypublichost_jenkinsserver>' 
     >***Note:*** In this example, a custom port is used instead of the default Jenkins port 8080.

2. **Navigate to project repository on GitHub**:
   - Click on **Settings** at the repository level.

3. **Go to Webhooks & services**:
   - Click on **Add webhook**.

4. **Fill in the required fields**:
   - **Payload URL**: Enter Jenkins URL followed by `/github-webhook`. For example, `<JenkinsURL>/github-webhook`.
   - **Content type**: Select `application/json`.
   - Leave the remaining fields as they are unless there are specific requirements for project.

### **Making Localhost to Publichost Using loophole_cloud ☁️**
Git webhook integrations typically require a publicly accessible URL with a hostname and port number. Localhost alone (e.g., localhost:8080) won't work for this purpose because it's not accessible from the external internet.

**Loophole_cloud** is a tool that helps you create a secure tunnel and expose local machine to the public internet through a temporary hostname. Here's how to install, set up, and use loophole_cloud:


1. **Download and Install loophole_cloud**:
   - Visit the loophole_cloud download page [downloads page](https://loophole.cloud/download).
   - Download the appropriate package for your operating system. For Linux, you can use the direct download link:  [direct link to download the executable](https://github.com/loophole/cli/releases/download/1.0.0-beta.15/loophole-cli_1.0.0-beta.15_linux_64bit.tar.gz).

2. **Unzip and Rename loophole_cloud package**:

   - Extract it and Rename the extracted folder to a simpler name using below command:
      ``` 
      tar -xvf loophole-cli_1.0.0-beta.15_linux_64bit.tar.gz && mv loophole-cli_1.0.0-beta.15_linux_64bit loophole-cloud
      ```
    
3. **Login and Setup**:
   - In the terminal, run the following command to log in (you may need to register for an account on the first run):
      ```sh
      cd loophole-cloud
      ./loophole account login
      ```
     You'll be prompted to open a link in your browser and enter a provided code for device authentication.
4. **Exposing your local host:**
   - Navigate back to the loophole_cloud directory: cd loophole-cloud
   - Run the following command, replacing `<portNum>` with the actual port your local service runs on (e.g., 9012 for a web server): ./loophole http `<portNum>`
      ```
      ./loophole http 9012
      ```
   - Loophole will display outputs indicating successful registration, proxy server startup, and tunnel initialization.
   - You'll see a final message with a public URL like https://`<random-string>`.loophole.site that forwards traffic to your local http://127.0.0.1: 
     `<portNum>`.
   
     >For example: https://48f287ad2a1f2b93d239fcb2a788f508.loophole.site
     
5. **Using a custom hostname (Optional):**
   - You can optionally assign a custom hostname to public URL for better memorability. Run the following command, replacing `<customHostname>` with your desired name and `<portNum>` with your local service port:
     ```
     ./loophole --hostname <customHostname> http <portNum>
     ```
   - Loophole will again display success messages and provide the final public URL with your custom hostname (e.g., https://`<customHostname>`.loophole.site)

🌐 Now localhost is accessible from the public internet using loophole_cloud!

### **Making Jenkins URL Public 🌐**

To make Jenkins URL public, follow these steps:

1. **Navigate to the loophole-cloud directory**:
   - Run the command: 
      ```
      ./loophole --hostname jenkinserver http 9012
      ```
   - This will generate a public URL, for example: [https://jenkinserver.loophole.site](https://jenkinserver.loophole.site).

---
## **Maven and Java Configuration Process 🛠️**

### ***Prerequisites 🚀***
To verify the home locations of Maven and Java, open system’s command line and run:
```bash
mvn -v
```

The output will provide details about the installed versions of Maven and Java, including their home locations:
```
Apache Maven 3.9.6 (bc0240f3c744dd6b6ec2920b3cd08dcc295161ae)
Maven home: /opt/mvn-3.9.6
Java version: 11.0.24, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "5.15.153.1-microsoft-standard-wsl2", arch: "amd64", family: "unix"
```

> **Note**: The Maven and Java plugins are pre-installed with Jenkins, which simplifies the setup process.

### ***Configuration Steps in Jenkins Settings 🔧***

To configure Maven and Java in Jenkins, follow these steps:

1. Go to **Manage Jenkins** > **Global Tool Configuration**.

#### **Maven Configuration 📦**

Navigate to the **Maven** section:
1. Click on **Maven installations...** > **Add Maven**.
2. Fill in the required fields:
   - **Name**: Enter a name for this Maven installation (e.g., Maven).
   - **MAVEN_HOME**: Enter the path where Maven is installed on system (e.g., `/opt/mvn-3.9.6`).

#### **Java Configuration ☕**

Navigate to the **JDK** section:
1. Click on **JDK installations...** > **Add JDK**.
2. Fill in the required fields:
   - **Name**: Enter a name for this JDK installation (e.g., Java17 or Java11).
   - **JAVA_HOME**: Enter the path where Java is installed on system (e.g., `/usr/lib/jvm/java-11-openjdk-amd64`).

>***Note***: If encounter an error such as 'mvn not found,' please refer to the troubleshooting section in the accompanying Error-notes for resolution.

---

## **Integrating OWASP Dependency-Check with Jenkins🚀**

**Follow these steps to integrate OWASP Dependency-Check with Jenkins:**

1. Navigate to **Manage Jenkins > Tools**.
2. Scroll down to the **Dependency-Check Installations** section.
3. Click on **Add Dependency-Check**.
4. Enter a descriptive name for the installation (e.g., `Dp-check`).
5. Check the box for **Install automatically**.
    - Click on the **Add Installer** option:
        - Choose one of the following:
            - Extract `*.zip`/`*.tar.gz`
            - Install from `github.com`
            - Run Batch Command
            - Run Shell Command
        - For now, we choose **Install from github.com**, select the version (e.g., `dependency-check 8.4.3`).
6. Click on **Save & Apply**.

>**Note**: For versions below 9.0.0, there's no need for an NVD API key.

---

### **Optional: Configuring NVD API Key (versions 9.0.0 and above)🔑**

1. Visit [NVD API Key Request](https://nvd.nist.gov/developers/request-an-api-key) and generate an API token following the instructions.
2. Provide details such as organization name, email address, and organization type.
    - For personal use, use a sample organization name (e.g., `<SampleOrgName>`).
3. Store the generated token in Jenkins credentials:

    - Go to **Jenkins Dashboard > Manage Jenkins > Manage Credentials**
    - Click on `(global)` under **Stores scoped to Jenkins**
    - Click on **Add Credentials** on the left side.
    - Select **Secret text** as the Kind.
    - Paste the API token in the **Secret text** field.
    - Enter a unique ID for this credential (e.g., `Dp-check`) in the ID field.
    - Click **OK** to save the credential.

---

### **Using OWASP Dependency-Check in Pipelines🛠️**

You can utilize the `dependencyCheck` step in Jenkins pipelines with the following syntax:

```groovy
dependencyCheck additionalArguments: '', nvdCredentialsId: 'Dp-check', odcInstallation: 'Dp-check'
```

>**Note**: You can generate this syntax using the Jenkins pipeline syntax snippet generator.

---

## **Integrating SonarQube with Jenkins on Ubuntu 🚀**

### ***Prerequisites: SonarQube Setup and Token Generation 🔑***

1. **Install SonarQube Using Ansible Scripts**:
   Follow the instructions in this GitHub repository to install SonarQube using Ansible scripts.

2. **Login to SonarQube and Change Default Credentials**:
   - Access SonarQube using the default credentials:
     - Username: `admin`
     - Password: `admin`
   - For security reasons, change the default password to a custom one.

3. **Generate a Token**:
   - After logging in, navigate to **My Account > Security**.
   - Scroll down to **Generate Tokens**.
   - Give Name of token (for example: generaluse)
   - Choose the type of token (either Global Analysis Token or User Token).
   - Set the token expiration (e.g., 30 days).
   - Click **Generate** and copy the generated code.
   - Save this token for future use, such as configuring it in Jenkins.

### **Setting Up SonarQube Integration in Jenkins 🛠️**

1. **Configure SonarQube in Jenkins**:
   - Log into the Jenkins server.
   - Navigate to **Manage Jenkins > Configure System**.
   - Scroll down to the **SonarQube configuration** section.
   - Click **Add SonarQube** and provide the required values:
     - SonarQube server URL
     - Server authentication token (create it as a Secret Text credential).

   **Credentials Setup**:
   1. Create a secret text credential in Jenkins:
      - Go to **Jenkins Dashboard > Manage Jenkins > Manage Credentials**.
      - Click on `(global)` under **Stores scoped to Jenkins**.
      - Click on **Add Credentials** on the left side.
      - In the **Kind** dropdown, select **Secret text**.
      - Paste the token code in this field (copy the earlier created token).
      - Enter a unique ID for this credential (e.g., `sonarsc`) in the ID field.
      - Click **OK** to save the credential.

2. **Create a Jenkins Pipeline Project**:
   - Set up Jenkins pipeline project (either Freestyle or Pipeline).
   - Define build steps, including the SonarQube analysis step.
   - In pipeline script, use the SonarScanner to trigger the analysis. For example, in a Jenkinsfile:

        ```groovy
         stage('SonarQube Analysis') {
         steps {
             script {
                 withSonarQubeEnv('SonarQubeServer') {
                     sh "mvn sonar:sonar"
                     }
                  }
                }
             }
        ```

3. **View Analysis Results**:
   - After the Jenkins job completes, a badge and widget will appear on the job page.
   - Click the link to the SonarQube dashboard to view detailed analysis results.

---
## **Integrating Docker with Jenkins🐳**

### ***Prerequisites 🚀***

Before start, make sure you have the following:

1. **Docker**: Installed Docker on local system. In this context, Docker Desktop is installed on Windows 10.

### Plugins 🛠️

You'll need the following Jenkins plugins:

1. **Docker Pipeline**: This plugin allows to build and use Docker containers from pipelines.
2. **Docker Plugin**: Integrates Jenkins with Docker.

### **Installing the Docker Plugins on Jenkins:**

1. Log in to Jenkins dashboard.
2. Navigate to **Manage Jenkins** > **Manage Plugins**.
3. Search for the **Docker Pipeline** and **Docker** plugins and install them.

### Credentials Setup 🔑

1. Go to **Jenkins Dashboard** > **Manage Jenkins** > **Manage Credentials**.
2. Click on **(global)** under **Stores scoped to Jenkins**.
3. Click on **Add Credentials** on the left side.
4. In the **Kind** dropdown, select **Username with password**.
5. In the **Username** field, enter Docker username, and in the **Password** field, enter Docker token (instead of a password).
6. In the **ID** field, enter a unique ID for this credential (e.g., `dockerpwd`).
7. Click **OK** to save the credential.

After setting up everything, verify it using a pipeline, and make sure both Jenkins and Docker are running.

## Jenkinsfile Example 🚀

```groovy
pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
        stage("Verify Docker") {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerpwd', passwordVariable: 'dockerp', usernameVariable: 'dockeruser')]) {
                        sh """
                        	docker login -u ${dockeruser} -p ${dockerp}
                        	docker version
                        	docker ps
                        	docker images
                        """
                    }
                }
            }
        }
    }
}
```

---

## **GitHub Integration with Jenkins 🚀**

### ***Prerequisites***

Before start, ensure that you have the following:

1. An existing GitHub account and a generated token 🔑
   >**Note:** The Git plugins come pre-installed with Jenkins, which simplifies the setup process.

### Credentials Setup

1. Go to **Jenkins Dashboard > Manage Jenkins > Manage Credentials**.
2. Click on `(global)` under **Stores scoped to Jenkins**.
3. Click on **Add Credentials** on the left side.
4. In the **Kind** dropdown, select **Username with password**.
5. In the **Username** field, enter GitHub username, and in the **Password** field, enter GitHub token (instead of a password).
6. In the **ID** field, enter a unique ID for this credential (e.g., `githubcred`).
7. Click **OK** to save the credential.

### Verification Steps

After setting up everything, verify it using a pipeline.

## Jenkinsfile Example

```groovy
pipeline {
    agent any

    stages {
        stage('github') {
            steps {
                withCredentials([gitUsernamePassword(credentialsId: 'githubcred', gitToolName: 'Default')]) {
                    sh """
                        git add "${filePath}"
                        git commit -m "Update Docker image tag"
                        git push origin main
                    """
                }
            }
        }
    }
}
```

---

## **Integrating Gmail Notifications with Jenkins📬🔔**

### ***Prerequisites 🛠️***

Before start, make sure to enable SMTP access in Gmail settings to allow Jenkins to send emails using Gmail account. Here's how:

1. Navigate to **Manage Google Account** > **Security**.
2. Click on **2-Step Verification**.
3. Scroll down to **App Password** and click on it.
4. Follow the instructions displayed to create an App password. Remember to save this password as it will be displayed only once. For example, App password might look something like this: `inbz lqez dwfx dhxh`.

### Plugin 🧩

The **Email Extension Plugin** is included by default in recent versions of Jenkins, which simplifies the process of setting up email notifications for builds.

### Credentials Setup 🔑

To set up the credentials in Jenkins:

1. Go to **Jenkins Dashboard** > **Manage Jenkins** > **Manage Credentials**.
2. Click on **(global)** under **Stores scoped to Jenkins**.
3. Click on **Add Credentials** on the left side.
4. In the **Kind** dropdown, select **Username with password**.
5. In the **Username** field, enter Gmail username. In the **Password** field, enter App password (not Gmail password).
6. In the **ID** field, enter a unique ID for this credential (e.g., `gmail`).
7. Click **OK** to save the credential.


### **Extended E-mail Notification Setup 📧🔔**

To configure the extended email notification:

1. Go to **Manage Jenkins** > **Configure System**.
2. Find the **Extended E-mail Notification** tab and fill in the details:
   - **SMTP server name**: smtp.gmail.com
   - **SMTP Port**: 465
   - In the **Advanced** tab, fill in the following details:
     - In the **Credentials** tab, upload/add the Gmail credentials that you created in the credential setup.
     - **Use SSL**: Checked
     - **Default user e-mail suffix**: @gmail.com

### (Optional) For verification: 
- **To configure the email notification:**

1. Go to **Manage Jenkins** > **Configure System**.
2. Find the **Email Notification** tab and fill in the details:
   - **SMTP server name**: smtp.gmail.com
   - **Default user e-mail suffix**: @gmail.com
   - Click on `Advance tab`
     - [x] Use SMTP Authentication:
       - **User name**: <user_email_id@gmail.com>
       - **Password**: `<App_Password>`
     - [x] Use SSL
   - **SMTP Port**: 465
  
You can verify the email notification functionality by ticking the checkbox next to the
- [x] Test configuration by sending Test e-mail recipient option.
    -  Enter a valid email ID : <user_email_id@gmail.com>
    -  click the **Test configuration** button to check whether the email ID is valid or not.

## Jenkinsfile Example 🚀

```groovy
pipeline {
    agent any
    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
    }
    post {
        always {
            script {
                env.BUILD_TIMESTAMP = new Date(currentBuild.startTimeInMillis).format('MMMM dd, yyy | hh:mm:ss aaa | z')
            }
            emailext (
                subject: "${currentBuild.currentResult} Build Report as of ${BUILD_TIMESTAMP} — ${env.JOB_NAME}",
                body: """The Build report for ${env.JOB_NAME} executed via Jenkins has finished its latest run.

- Job Name: ${env.JOB_NAME}
- Job Status: ${currentBuild.currentResult}
- Job Number: ${env.BUILD_NUMBER}
- Job URL: ${env.BUILD_URL}

Please refer to the build information above for additional details.

This email is generated automatically by the system.
Thanks""",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']],
                to: 'username@gmail.com',
                attachLog: true
            )
        }
    }
}
```

---

## Integrating ArgoCD with GitHub Repo 🚀

### ***Prerequisites***

1. **Install ArgoCD using Ansible playbook**:
   Follow the instructions in this GitHub repository to install ArgoCD using Ansible scripts.

### Integration Setup Steps

1. **Access ArgoCD External UI**:
   - Open a browser and visit the ArgoCD external UI using the IP or hostname.
   - Use the following credentials:
     - Username: `admin`
     - Password: `<o8ucRTQkvJJ4oiLb>` (This password is autogenerated during each new installation and Replace it.)
   - Immediately change the password via the `User Info` after logging in for security reasons.

2. **Create a New Application**:
   - Log in to ArgoCD.
   - Click the “+ NEW APP” button or select “CREATE APPLICATION.”
   - Configure the application:
     - Name: `guestbook`
     - Project: `Default`
     - Sync Policy: Manual and Automatic (select Automatic)
     - Options: 
       - [x] PRUNE RESOURCES, 
       - [x] SELF HEAL
     - Next Sync options: 
       - [x] AUTO-CREATE NAMESPACE
     - Next, configure the source:
       - Repository URL: Set to GitHub repo URL
       - Revision: Leave as HEAD
       - Path: Set to the path within repo (e.g., `Manifests`)
     - Next, configure the destination:
       - Cluster URL: Default cluster (or use a custom cluster URL)
       - Namespace: Specify a name or let it auto-create based on Sync options
     - Keep the remaining options as-is and click “CREATE.”
---

## Configuration and Dashboard Setup:

1. **Install Grafana Using Ansible Scripts:**
   - Follow the instructions in this GitHub repository to install Grafana using Ansible scripts.

2. **Login to Grafana and Change Default Credentials:**
   - Access Grafana using the default credentials:
     - Username: `admin`
     - Password: `admin`
   - For security reasons, change the default password to a custom one.

3. Add the `Data Source` section, and for this guide, use **Prometheus** as the data source.

4. **Grafana Dashboards:**
   - Jenkins Dashboard (ID: 9964):
     - Monitors Jenkins performance.
   - Node Exporter Dashboard (ID: 1860):
     - Collects node-level metrics.
   - Kube-state-metrics:
     - [Copy or upload the JSON file in Grafana Dashboard.](16520_kube-state-metrics.json)
    
      
   - BlackBox Exportoer Dashboard (ID: 7587):
     - used for endpoint monitoring and can help generate meaningful uptime and availability metrics. 

## Prometheus Integration with Jenkins:
After installing the plugin:

1. Log into the Jenkins server.
2. Navigate to **Manage Jenkins > Configure System**.
3. Scroll down to the Prometheus configuration section.
4. Verify that the default settings are correct.
5. Click on **Save & Apply**.

---








