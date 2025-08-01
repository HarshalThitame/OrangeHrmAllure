pipeline {
  agent any

  tools {
    maven 'Maven_3'       // Defined in Jenkins Global Tools config
    jdk 'JDK_17'          // Defined in Jenkins Global Tools config
  }

  environment {
    ALLURE_RESULTS = "target/allure-results"
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
        bat 'docker-compose -f selenium-grid-docker/docker-compose.yml up -d'
      }
    }

    stage('Build Project') {
      steps {
        bat 'mvn clean compile'
      }
    }

    stage('Run Tests') {
      steps {
        bat "mvn test -Dbrowser=${params.browser} -Denv=${params.env}"
      }
    }

    stage('Generate Allure Report') {
      steps {
        bat "allure generate ${env.ALLURE_RESULTS} --clean -o ${env.ALLURE_REPORT}"
      }
    }

    stage('Publish Allure Report') {
      steps {
        allure includeProperties: false,
               jdk: '',
               commandline: 'Allure', // Tool name as defined in Jenkins Global Tools
               results: [[path: "${env.ALLURE_RESULTS}"]]
      }
    }
  }

  post {
    always {
      echo "🧹 Cleaning up Selenium Grid containers..."
      bat 'docker-compose -f selenium-grid-docker/docker-compose.yml down'
    }

    success {
      echo "✅ Build and tests successful!"
    }

    failure {
      echo "❌ Build failed!"
    }
  }
}
