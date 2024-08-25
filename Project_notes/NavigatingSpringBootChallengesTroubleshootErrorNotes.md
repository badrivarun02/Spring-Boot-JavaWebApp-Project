# Navigating Spring Boot Challenges & Troubleshoot Error Notes 🌱


### Changing the Default Port for a Spring Boot Application

When running a Spring Boot application using `java -jar`, you can specify a custom port using the `--server.port` option.

Example command:
```bash
java -jar your-application.jar --server.port=8015
```

>Replace `your-application.jar` with your actual JAR file name and `8015` with your desired port number.

### Error: Resolving Permission Issues during `mvn clean install`

If you encounter permission errors while executing `mvn clean`, it’s likely related to file/folder ownership. Follow these steps to resolve it:

1. Use `ls -lts` to list owners and permissions.
2. Verify access visually using `namei -m /path/to/really/long/directory/with/file/in`.
3. To change permissions, use `sudo chmod 755 file`.
4. To change ownership from root to a specific user, use:
   ```bash
   sudo chown -R user:group /path/to/directory
   ```
   
>Replace `user` with the desired username and `group` with the group name (usually the same as the username).


## Challenge 1: **Incorporating Static Website Content in Spring Boot 🌐**

After cloning a project from another public GitHub repository, I decided to incorporate static website content. I researched and found a suitable website template on free-css.com.

1. **Downloaded the Website Template**:
   - I downloaded a simple website template from free-css.com.
   - The template included static folders/files such as Img, CSS, and JavaScript.

In these challenges, the first thing I needed to address was how to add static website files. I wasn’t sure where to place them within my Java project, especially since I have limited experience with Java projects. By leveraging **Copilot AI**, I gained insights into the Java project file structure and successfully organized the files and folders according to their respective locations.
### Solution:
### Understanding the Java Project Structure

After researching and using AI tools like **Copilot AI** and **Gemini AI**, I discovered the Java project structure:

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── RockStart
│   │           ├── MyController.java
│   │           └── WebConfig.java
│   └── resources
│       ├── static
│       │   ├── css
│       │   ├── js
│       │   └── img
│       └── templates
│           └── index.html
└── ...
```

- The `static` directory is where we place our static website files (CSS, JS, images).
- The `templates` directory is for dynamic templates (e.g., Thymeleaf templates, HTML).


When adding static website files to your Spring Boot project, it's essential to align the HTML content with the Java project structure. 
Additionally, incorporating Java code in the main files allows you to utilize images, CSS, and other resources effectively. Let's break down the steps like

Create two separate files:

1. **MyController.java** (for your controller class):
   - This file will access the `index.html` file.
   - Implement your controller logic here.

2. **WebConfig.java** (for your configuration class):
   - This file will handle image, CSS, and JS resources.
   - Configure any necessary settings.

3. **Execution**

   - After copying the website content and adding the Java code, execute your Spring Boot project. Verify that the static website files are being utilized as expected.

## Error's: 

 **Initial Path Configuration:**
   - Initially, I specified the image paths like this:
     ```html
     <img th:src="@{../static/imag/logo.png}" alt="" width="100px">
     ```
   - However, this approach caused the images to fail to load. Spring Boot already checks the `static` folder by default, 
       so explicitly mentioning it in the path led to faulty results.
### Solution:
   1. **Correcting the Paths in HTML:**
      - To resolve the issue, I adjusted the image paths in my HTML code:
       ```html
       <img th:src="@{/imag/logo.png}" alt="" width="100px">
       ```
      - Similarly, I updated the paths for CSS and JS files:
       ```html
       <link th:href="@{/css/cssfile.css}" rel="stylesheet">
       <script th:src="@{/js/jssfile.js}"></script>
       ```
  2. **Understanding `WebConfig.java`:**
     - I revisited my `WebConfig.java` file, where I had previously configured static resources:
      ```java
      registry.addResourceHandler("/static/**")
             .addResourceLocations("classpath:/static/");
      ```
     - This line specifies that resources within the `static` folder are accessible via the `/static/` URL path.


---

### 1. **Error/Issue**: 
     Maven (mvn) is not found in Jenkins, causing build failures.

```
+ mvn -v
/var/lib/jenkins/workspace/sample@tmp/durable-4063436a/script.sh.copy: 1: mvn: not found
ERROR: script returned exit code 127
Finished: FAILURE
```

### Solution:

1. **Create a Symbolic Link for Maven**:
   - Open your terminal (as root or with sudo privileges).
     ```  
     cd /usr/bin
     ```
   - Run the following command to create the symbolic link:
     ```
     ln -s /opt/mvn-3.9.6/bin/mvn mvn
     ```
   - This link allows you to run `mvn` from any directory.

2. **Verify the Link**:
   - Confirm that the symbolic link (`mvn`) points to the correct Maven binary:
     ```
     ls -l mvn
     ```
   - You should see an arrow (`->`) indicating the link.

3. **Test Maven**:
   - Run `mvn -v` to verify that Maven is accessible.

4. **Unlink the Symbolic Link (if needed)**:
   - If you intentionally want to remove the symlink, use the following command:
     ```
     sudo unlink mvn
     ```
   - This will unlink the `mvn` symbolic link.

### Additional Tips:
- If you accidentally unlink the symbolic link, you can still run `mvn -v` directly from the Maven installation path.


### 2. **Error:**
+ docker build -t springbt:2 .
ERROR: permission denied while trying to connect to the Docker daemon socket at unix:///var/run/docker.sock: 
Head "http://%2Fvar%2Frun%2Fdocker.sock/_ping": dial unix /var/run/docker.sock: connect: permission denied

### Solution: 
When Jenkins tries to execute Docker commands, it may encounter a “permission denied” error related to the Docker daemon socket. This happens because Jenkins runs as a separate user (usually “jenkins”) and lacks the necessary permissions to access the Docker socket.
To resolve this, you can add the Jenkins user to the Docker group. This grants the Jenkins user the necessary privileges to interact with Docker. 
Run the following command in your VPS server terminal:
``` 
sudo usermod -aG docker jenkins
```
Afterward, restart your Jenkins server to refresh the group membership


### 3. **Error:**
there's a synchronization issue between your local Git repository and the remote repository (e.g., GitHub, GitLab). 


* Your local branch (the tip, which refers to the latest commit) is **behind** its remote counterpart. This means other developers might have pushed newer commits to the same branch on the remote repository.
* When you try to push your local changes (commits), Git rejects them because it avoids overwriting other developers' work.

### Solution: 

* The message suggests using `git pull ...` to integrate the remote changes before pushing again.

**Steps to Fix:**

1. **Pull the Remote Changes:**
   - Open your terminal or command prompt and navigate to your project directory.
   - Run the command `git pull`. This will fetch the latest changes from the remote repository and attempt to merge them with your local branch.

   - There are two scenarios after running `git pull`:
     * **Clean Merge:** If there are no conflicts (changes made by you and someone else on the same lines of code), Git will merge the changes seamlessly, and you can proceed with pushing your local commits.
     * **Merge Conflict:** If there are conflicts, Git will pause the process and highlight the conflicting sections in your code. You'll need to manually resolve these conflicts by editing the files and marking the resolved sections. Once resolved, you can commit the changes again and try pushing.

2. **Push Your Local Changes (After Successful Pull):**
   - Once you've successfully pulled the remote changes and resolved any conflicts (if applicable), you can push your local commits to the remote repository using `git push`.

**Additional Tips:**

* It's a good practice to pull frequently (before pushing) to stay updated with the latest changes on the remote branch and avoid merge conflicts.
* You can use `git pull --rebase` instead of `git pull` for a cleaner integration, but this rewrites history, so use it with caution, especially if others have already pulled your branch.

By following these steps, you should be able to synchronize your local branch with the remote repository and successfully push your changes.

---

### Challenge 2: **YAML Image Tag Modifier**: 
Emphasizes the specific task of modifying image tags in YAML files.
here its jenkinsfile:

```groovy
stage('Update YAML') {
steps {
script {
        def filePath = 'Manifests/deployment.yml'
        def JOB = env.JOB_NAME.toLowerCase()
        sh "cd filePath"  // Corrected the path variable
        
     // Update the Docker image tag in your YAML file
        def newImageTag = "${DOCKER_USERNAME}/${JOB}:v${BUILD_NUMBER}"
        def yamlFile = readFile filePath  read the content of the deployment.yml file using readFile.//
        echo "Before update:\n${yamlFile}"

        // Define the old image tag pattern and The logic for identifying the old image tag pattern seems fine. It checks for either a specific version (v\\d+) or the latest tag.
        def oldImageTagPattern 
        if (yamlFile =~ "${DOCKER_USERNAME}/${JOB}:v\\d+") {
            oldImageTagPattern = "${DOCKER_USERNAME}/${JOB}:v\\d+"
        } else if (yamlFile =~ "${DOCKER_USERNAME}/${JOB}:latest") {
            oldImageTagPattern = "${DOCKER_USERNAME}/${JOB}:latest"
        } else {
            oldImageTagPattern = "${DOCKER_USERNAME}/${JOB}"
        }

        def updatedYamlFile = yamlFile.replaceAll(oldImageTagPattern, newImageTag)//replacing the old image tag pattern with the new image tag using replaceAll.
        writeFile file: filePath, text: updatedYamlFile //writing the modified content back to the same file using writeFile.
        echo "After update:\n${readFile filePath}" //echo statement displays the updated content of the deployment.yml file
    }
   }
}
```

## Error 1: Unable to Change Directory to Manifests/deployment.yml

**Issue:**
When attempting to change the directory to `Manifests/deployment.yml`, the following error occurs:
```
/var/lib/jenkins/workspace/springdemo@tmp/durable-fa97c32a/script.sh.copy: 1: cd: can't cd to Manifests/deployment.yml
```

**Explanation:**
YAML files won't work correctly if the `cd` (change directory) command is used.

**Solution:**
1. Modify the value of the `filepath` variable from `Manifests/deployment.yml` to simply `Manifests`.
2. Update all instances where the variable name is used, ensuring that it includes the file name (`deployment.yml`). For example, change `filepath` to `filepath/deployment.yml`.

**Result:**
After making these changes, I re-ran the pipeline, and it executed successfully.

**Context:** Using the `sh` Command
I utilized the `sh` command to change directories and read/write YAML files.

**Action Taken:**
Adjusted the `filepath` variable value from `Manifests/deployment.yml`.

## Error 2:
Despite resolving the previous error, the Docker image tag name is still not updating correctly in the YAML file.

### Solution:
1. **Identify the Variable Direction:**
   - After extensive troubleshooting, including checking logs and console output, I realized that the issue lies in how we define the variable.
   - Explanation: We need to use `${}` to reference the variable correctly. Don't forget to include the quotes around the variable name.

2. **Update Variable References:**
   - Replace all instances of the variable name `filepath` with `${filepath}` wherever it is used.
   - Example: Change `def yamlFile = readFile filePath/deployment.yml` to `def yamlFile = readFile "${filepath}/deployment.yml"`.

3. **Final Check:**
   - After making this adjustment, run your pipeline again and verify that the content is correctly updated in the `deployment.yml` file.

---
after resolve above errors and finally modified scripts:
Updated jenkins file:
```groovy
stage('Update YAML ') {
steps {
script {
        def filePath = 'Manifests'
        def JOB = env.JOB_NAME.toLowerCase()
        sh "cd ${filePath}"  // Corrected the path variable
        
     // Update the Docker image tag in your YAML file
        def newImageTag = "${DOCKER_USERNAME}/${JOB}:v${BUILD_NUMBER}"
        def yamlFile = readFile "${filePath}/deployment.yml"  // read the content of the deployment.yml file using readFile.
        echo "Before update:\n${yamlFile}"

        // Define the old image tag pattern and The logic for identifying the old image tag pattern seems fine. It checks for either a specific version (v\\d+) or the latest tag.
        def oldImageTagPattern 
        if (yamlFile =~ "${DOCKER_USERNAME}/${JOB}:v\\d+") {
            oldImageTagPattern = "${DOCKER_USERNAME}/${JOB}:v\\d+"
        } else if (yamlFile =~ "${DOCKER_USERNAME}/${JOB}:latest") {
            oldImageTagPattern = "${DOCKER_USERNAME}/${JOB}:latest"
        } else {
            oldImageTagPattern = "${DOCKER_USERNAME}/${JOB}"
        }

        def updatedYamlFile = yamlFile.replaceAll(oldImageTagPattern, newImageTag)//replacing the old image tag pattern with the new image tag using replaceAll.
        writeFile file: "${filePath}/deployment.yml", text: updatedYamlFile //writing the modified content back to the same file using writeFile.
        echo "After update:\n${readFile "${filePath}/deployment.yml"}" //echo statement displays the updated content of the deployment.yml file
    }
}
}
```
---

### Challenge 3: Enhancing the Script

1. **Objective**:
   - Modify the existing script to update the Docker image tag within the YAML file and  Pushing to the Same Repo.
   - After updating the tag, push the changes to the same repository for Kubernetes deployment using ArgoCD.

2. **Observation**:
   - I noticed that there's no need to change directories because the relevant files are in the same directory.
   - Therefore, I directly specify the path in the `def` variable.

### Modified Script:

```groovy
stage('Update YAML') {
    steps {
        script {
            def filePath = 'Manifests/deployment.yml' // Updated from filepath='Manifests' to filepath='Manifests/deployment.yml'
            def JOB = env.JOB_NAME.toLowerCase()

            // Removed the unnecessary 'cd' statement (path correction)

            // Update the Docker image tag in your YAML file
            def newImageTag = "${DOCKER_USERNAME}/${JOB}:v${BUILD_NUMBER}"
            def yamlFile = readFile "${filePath}" // Read the content of deployment.yml using readFile.
            echo "Before update:\n${yamlFile}" // Display the before updated content of deployment.yml

            // Define the old image tag pattern
            def oldImageTagPattern
            if (yamlFile =~ "${DOCKER_USERNAME}/${JOB}:v\\d+") {
                oldImageTagPattern = "${DOCKER_USERNAME}/${JOB}:v\\d+"
            } else if (yamlFile =~ "${DOCKER_USERNAME}/${JOB}:latest") {
                oldImageTagPattern = "${DOCKER_USERNAME}/${JOB}:latest"
            } else {
                oldImageTagPattern = "${DOCKER_USERNAME}/${JOB}"
            }

            // Replace the old image tag pattern with the new image tag
            def updatedYamlFile = yamlFile.replaceAll(oldImageTagPattern, newImageTag)
            writeFile file: "${filePath}", text: updatedYamlFile // Write the modified content back to the same file.
            echo "After update:\n${readFile "${filePath}"}" // Display the updated content of deployment.yml
            sh "cat ${filePath}" // Verify whether changes are updated or not.

            // Push changes to the same repo
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
```

---

## Challenge 4: Handling Different Image Tag Names

### Problem:
When executing the script to update Docker image tags within the YAML file, I encountered an issue. Specifically, if the previous image tag name differs from the current one (e.g., based on the job name), the script fails to write the correct image name.

### Solution Attempt 1
- **Gemini AI Suggestion**: Update the JOB expression to `/~ /\w+/`.
- **Explanation**: This expression captures capital letters, small letters, underscores, and numbers.
- **Example**: `${DOCKER_USERNAME}/~ /\w+/:v\\d+`

### Solution Attempt 2
- **Gemini AI Suggestion**: Use `(?:/~/\w+)?`.
- **Explanation**: This captures the DOCKER_USERNAME followed by an optional group with tilde (~), a slash (/), and one or more alphanumeric characters (\w+).
- **Example**: `${DOCKER_USERNAME}(?:/~/\w+)`

## Ongoing Research
Despite trying the above solutions, I haven't found a satisfactory answer yet. I'll continue researching to find a better approach.

Exploration:
I sought assistance from both **Copilot AI** and **Gemini AI**. Unfortunately, none of the suggested scripts fully met my requirements. 
I experimented with regular expressions, but the prompt kept changing, making it challenging to find the optimal solution.

### Addressing Alphanumeric Characters in Regular Expressions:
To capture alphanumeric characters within Jenkins scripts, I asked Gemini AI. Here's the relevant information:

- Regular Expression: `\w+`
  - Explanation:
    - `\w` matches any single alphanumeric character (a-z, A-Z, 0-9, and underscore `_`).
    - `+` indicates that one or more occurrences of the preceding character (`\w`) must be present.

### Updated Script Line:
I modified the line from:
```
${DOCKER_USERNAME}(?:/~/\w+)?:v\\d+
```
to:
```
"${DOCKER_USERNAME}/(\\w+):v\\d+"
```

### Note:
- Choose the appropriate regular expression based on your YAML content structure.
- Remember to use double backslashes (`\\`) for literal backslash characters within the regular expressions.

Finally Modified script:
### Modified Script:
```groovy
        stage('Update YAML and Push to Git') {
         steps {
          script {
            def filePath = 'Manifests/deployment.yml'
            def JOB = env.JOB_NAME.toLowerCase()
        
            // Update the Docker image tag in your YAML file
            def newImageTag = "${DOCKER_USERNAME}/${JOB}:v${BUILD_NUMBER}"
            def yamlFile = readFile "${filePath}"

            echo "Before update:\n${yamlFile}"

            // Define the old image tag pattern
            def oldImageTagPattern
          
            if (yamlFile =~ "${DOCKER_USERNAME}/(\\w+):latest"){
              oldImageTagPattern = "${DOCKER_USERNAME}/(\\w+):latest"
            } else if (yamlFile =~ "${DOCKER_USERNAME}/(\\w+):v\\d+"){
              oldImageTagPattern = "${DOCKER_USERNAME}/(\\w+):v\\d+"
            } else{
              oldImageTagPattern = "${DOCKER_USERNAME}/${JOB}"
            }

            def updatedYamlFile = yamlFile.replaceAll(oldImageTagPattern, newImageTag)
            writeFile file: "${filePath}", text: updatedYamlFile
            echo "After update:\n${readFile "${filePath}"}"
            sh "cat ${filePath}"
            
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
```

---
## Troubleshooting Kubernetes Node Exporter Mount Issue 🛠️

### Error Message


failed to start container 'node-exporter': Error response from daemon: path /host-root is mounted on / but it is not a shared or slave mount


### Cause

When running Kubernetes on Docker Desktop, mounting the entire host filesystem (`/`) with `mountPropagation: HostToContainer` is not supported. This setting attempts to propagate mount points from the host to the container, but Docker Desktop doesn't allow it for the root directory.

### Solution

1. **Remove `mountPropagation`**:

   - In your YAML file, remove the `mountPropagation: HostToContainer` line from both the `sys` and `root` volume mounts.
   
   - Before updat YAML snippet:
     ```yaml
     volumeMounts:
      - name: sys
        mountPath: /home/sys
        mountPropagation: HostToContainer
      - name: root
        mountPath: /home/nroot
        mountPropagation: HostToContainer
     ```

   - Here's after updated YAML snippet:
     ```yaml
     volumeMounts:
      - name: sys
        mountPath: /home/sys
      - name: root
        mountPath: /home/nroot
     ```

2. **Restart DaemonSet**:
   - After removing `mountPropagation`, apply the updated YAML file to your Kubernetes cluster using:

     ```bash
     kubectl apply -f <your-yaml-file>.yaml
     ```

### Explanation

By removing `mountPropagation`, the Node Exporter container will access the host's `/sys` and `/root` directories without attempting to propagate mount points. This allows Node Exporter to function correctly within Docker Desktop.

---
## Dashboard Error:

```
Grafana - Failed to upgrade legacy queries Datasource prometheus was not found
```

### Solution:
1. **Remove UID from Dashboard JSON:**
   - In dashboard JSON file, remove the UID associated with the datasource.
   - This action should resolve the issue.

>***Note:*** I've simplified the solution to focus on the key steps. If you need more detailed instructions, refer to the original forum post.
[Grafana Community Forum - Solution](https://community.grafana.com/t/templating-failed-to-upgrade-legacy-queries-datasource-was-not-found/109351/16)
---
## Troubleshooting and Solution: Resolving Connection Issues in Kubernetes

### Error Description
During my project, I encountered an error related to probe logs. The issue prevented successful communication between pods. Here are the relevant logs:

```plaintext
ts=2024-08-20T07:29:33.20592693Z caller=main.go:190 module=http_endpoint target=http://localhost:8012 level=info msg="Beginning probe" probe=http timeout_seconds=5
...
ts=2024-08-20T07:29:33.254700105Z caller=handler.go:119 module=http_endpoint target=http://localhost:8012 level=error msg="Error for HTTP request" err="Get \"http://127.0.0.1:8012\": dial tcp 127.0.0.1:8012: connect: connection refused"
...
ts=2024-08-20T07:29:33.255803793Z caller=main.go:190 module=http_endpoint target=http://localhost:8012 level=error msg="Probe failed" duration_seconds=0.049457694
```

### Solution
After extensive research, including blogs and YouTube videos, I spent nearly 10 days troubleshooting. Initially, I suspected pod-to-pod communication issues and installed Weave Net CNI. However, I realized that the root cause was different:

- **Actual Issue**: When using `127.0.0.1` (localhost) in a request, it refers to the loopback address within the same pod.
- **Solution**: To access external or remote hosts, use their actual IP addresses or domain names. Replace `127.0.0.1` with the IP address of the remote host you want to access. For example:
  ```
  wget -qO- --timeout=2 http://<REMOTE_HOST_IP>:8055
  ```

### How to Check Remote Host IP on Ubuntu
If you're already logged into a machine, you can get the internal IP address using the following command:
```bash
hostname -I
```

This straightforward solution resolved my connectivity issue. Remember to replace `<REMOTE_HOST_IP>` with the actual IP address you need to reach. 🚀

