pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                script {
                    if (env.BRANCH_NAME.startsWith("feature/")) {
                        echo "Running Smoke Suite for feature branch..."
                        sh 'mvn test -Psmoke'
                    } else if (env.BRANCH_NAME == "main") {
                        echo "Running Regression Suite for main branch..."
                        sh 'mvn test -Pregression'
                    } else if (env.BRANCH_NAME == "develop") {
                        echo "Running Parallel Suite for develop branch..."
                        sh 'mvn test -Pparallel'
                    } else {
                        echo "Defaulting to Smoke Suite..."
                        sh 'mvn test -Psmoke'
                    }
                }
            }
        }

        stage('Report') {
            steps {
                sh 'mvn allure:report'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
    }
}
