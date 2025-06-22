pipeline {
  agent any

  tools {
    maven 'Maven_3'       // Setup via Global Tools config
    jdk 'JDK_17'          // Setup via Global Tools config
  }

  environment {
    ALLURE_RESULTS = "target/allure-results"
    ALLURE_REPORT = "allure-report"
  }

  stages {
    stage('Checkout') {
      steps {
        git 'https://github.com/HarshalThitame/OrangeHrmAllure.git'
      }
    }

    stage('Start Selenium Grid via Docker') {
      steps {
        script {
          sh 'docker-compose -f selenium-grid-docker/docker-compose.yml up -d'
        }
      }
    }

    stage('Build Project') {
      steps {
        sh 'mvn clean compile'
      }
    }

    stage('Run Tests') {
      steps {
        sh 'mvn test -Dbrowser=chrome -Denv=remote'
      }
    }

    stage('Generate Allure Report') {
      steps {
        sh 'allure generate target/allure-results --clean -o allure-report'
      }
    }

    stage('Publish Allure Report') {
      steps {
        allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
      }
    }
  }

  post {
    always {
      echo "Cleaning up containers..."
      sh 'docker-compose -f selenium-grid-docker/docker-compose.yml down'
    }
    success {
      echo "Build and tests successful!"
    }
    failure {
      echo "Build failed!"
    }
  }
}
