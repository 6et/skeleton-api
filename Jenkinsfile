pipeline {
    agent any

    triggers {
        pollSCM('H * * * *')
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
        disableConcurrentBuilds()
    }

    stages {

        stage('Build') {
            steps {
                sh './gradlew clean build'
                sh 'cp build/libs/6et-skeleton-api.jar docker/'
            }
        }
        }
        }
