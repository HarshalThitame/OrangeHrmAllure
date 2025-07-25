pipeline {
  agent any

  tools {
    maven 'Maven_3'       // Setup via Global Tools config
    jdk 'JDK_17'          // Setup via Global Tools config
  }

  environment {
    ALLURE_RESULTS = "target\\allure-results"
    ALLURE_REPORT = "allure-report"
  }

    parameters {
        choice(name: 'env', choices: ['dev', 'qa', 'stage'], description: 'Select the environment')
        choice(name: 'browser', choices: ['Chrome', 'Firefox', 'Edge'], description: 'Choose browser')
    }

  stages {
    stage('Checkout') {
      steps {
        git 'https://github.com/HarshalThitame/OrangeHrmAllure.git'
      }
    }

    stage('Start Selenium Grid via Docker') {
      steps {
        bat 'docker-compose -f selenium-grid-docker\\docker-compose.yml up -d'
      }
    }

    stage('Build Project') {
      steps {
        bat 'mvn clean compile'
      }
    }

    stage('Run Tests') {
      steps {
        bat 'mvn test -Dbrowser=${params.browser} -Denv=${params.env}'
      }
    }

    stage('Generate Allure Report') {
      steps {
        bat 'allure generate target\\allure-results --clean -o allure-report'
      }
    }

    stage('Publish Allure Report') {
      steps {
        allure includeProperties: false, jdk: '', commandline: 'Allure', results: [[path: 'target/allure-results']]
      }
    }
  }

  post {
    always {
      echo "Cleaning up containers..."
      bat 'docker-compose -f selenium-grid-docker\\docker-compose.yml down'
    }
    success {
      echo "Build and tests successful!"
    }
    failure {
      echo "Build failed!"
    }
  }
}