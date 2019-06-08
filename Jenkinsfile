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
                sh './gradlew clean build -x test -x checkstyleMain -x checkstyleTest -x findbugsMain -x compileQuerydslJava -x checkstyleQuerydsl'
                sh 'cp build/libs/6et-skeleton-api.jar docker/'
            }
        }
        }
        }
