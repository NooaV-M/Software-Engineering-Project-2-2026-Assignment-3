pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        DOCKERHUB_REPO = 'nooav/software-engineering-project-2-2026-assignment-1'
        DOCKER_IMAGE_TAG = 'v1'
    }



    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/NooaV-M/Software-Engineering-Project-2-2026-Assignment-1.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Generate Report') {
            steps {
                bat 'mvn jacoco:report'
            }
        }


        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Credential Preflight') {
            steps {
                echo "Preflight: attempting to bind credential ID '${env.DOCKERHUB_CREDENTIALS_ID}'"
                script {
                    try {
                        withCredentials([usernamePassword(
                            credentialsId: "${env.DOCKERHUB_CREDENTIALS_ID}",
                            usernameVariable: 'DOCKER_USER',
                            passwordVariable: 'DOCKER_TOKEN'
                        )]) {
                            echo 'Preflight: credentials bound, validating Docker login'
                            bat '''
                            @echo off
                            docker --version
                            if not defined DOCKER_USER (
                              echo DOCKER_USER is missing
                              exit /b 1
                            )
                            if not defined DOCKER_TOKEN (
                              echo DOCKER_TOKEN is missing
                              exit /b 1
                            )
                            powershell -NoProfile -Command "$env:DOCKER_TOKEN | docker login -u $env:DOCKER_USER --password-stdin"
                            if errorlevel 1 exit /b 1
                            docker logout
                            '''
                            echo 'Preflight: login/logout succeeded'
                        }
                    } catch (err) {
                        echo "Preflight failure: ${err.getClass().getName()}: ${err.getMessage()}"
                        throw err
                    }
                }
            }
        }

        stage('test') {
            steps {
                echo 'Testing the pipeline...'
            }
}

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }



    }
}