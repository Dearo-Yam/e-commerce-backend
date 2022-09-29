pipeline {
    agent { dockerfile true }
    stages {
        stage('Build, Pack and Clean up') {
            steps {
                echo 'Build and pack into docker images'
                echo 'Clean workspace'
                cleanWs()
            }
        }
    }
}
