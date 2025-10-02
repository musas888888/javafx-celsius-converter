pipeline {
  agent any

  tools {
    maven 'Maven3'
  }

  options { timestamps() }

  environment {
    DOCKER_IMAGE = 'musass/fx-tempconv'
    DOCKER_TAG   = 'latest'
  }

  stages {
    stage('Checkout') {
      steps {
        // vaihda haaran nimi jos ei ole 'main'
        git branch: 'main', url: 'https://github.com/musas888888/javafx-celsius-converter.git'
      }
    }

    stage('Build (Maven)') {
      steps {
        script {
          if (isUnix()) {
            sh  'mvn -B -DskipTests package'
          } else {
            bat 'mvn -B -DskipTests package'
          }
        }
      }
    }

    stage('Unit tests') {
      steps {
        script {
          if (isUnix()) {
            sh  'mvn -B test'
          } else {
            bat 'mvn -B test'
          }
        }
      }
      post {
        always {
          junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
        }
      }
    }

    stage('Docker build') {
      steps {
        script {
          // Varmista että Dockerfile kopioi shaded JARin:
          //   COPY target/*-shaded.jar /app/app.jar
          if (isUnix()) {
            sh  "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
          } else {
            bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
          }
        }
      }
    }

    stage('Docker push') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'Docker_Hub', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
          script {
            if (isUnix()) {
              sh """
                echo "\$DH_PASS" | docker login -u "\$DH_USER" --password-stdin
                docker push ${DOCKER_IMAGE}:${DOCKER_TAG}
                docker logout
              """
            } else {
              bat """
                echo %DH_PASS% | docker login -u %DH_USER% --password-stdin
                docker push ${DOCKER_IMAGE}:${DOCKER_TAG}
                docker logout
              """
            }
          }
        }
      }
    }
  }

  post {
    always {
      archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: false
    }
  }
}


