pipeline {
  agent any
  tools { maven 'Maven-home' }   // ← sama nimi kuin Toolsissa

  options { timestamps() }

  /*************** ENV ******************/
  environment {
    // vaihda halutessa
    DOCKER_IMAGE = 'musass/fx-tempconv'
    DOCKER_TAG   = 'latest'
  }

  stages {

    stage('Checkout') {
      steps {
        // Jos ajat "Pipeline script from SCM", voit käyttää myös: checkout scm
        git branch: 'main', url: 'https://github.com/musas888888/javafx-celsius-converter.git'
      }
    }

    stage('Build (Maven)') {
      steps {
        script {
          if (isUnix()) { sh  'mvn -B -DskipTests package' }
          else          { bat 'mvn -B -DskipTests package' }
        }
      }
    }

    stage('Unit tests') {
      steps {
        script {
          if (isUnix()) { sh  'mvn -B test' }
          else          { bat 'mvn -B test' }
        }
      }
      post {
        always {
          // julkaise testiraportit Stage View / Test Result -näkymään
          junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
        }
      }
    }

stage('Docker push') {
  steps {
    script {
      // varmista että image on olemassa
      if (isUnix()) { sh "docker image inspect ${DOCKER_IMAGE}:${DOCKER_TAG} >/dev/null" }
      else          { bat "docker image inspect ${DOCKER_IMAGE}:${DOCKER_TAG} >nul 2>&1 || exit /b 1" }

      docker.withRegistry('https://index.docker.io/v1/', 'Docker_Hub') {
        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push()
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
      // talleta jarit buildin artefakteiksi
      archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: false
    }
  }
}


