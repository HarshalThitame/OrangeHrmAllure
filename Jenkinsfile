pipeline {
  agent any

  tools {
    maven 'Maven_3'
    jdk 'JDK_17'
  }

  environment {
    ALLURE_RESULTS = 'target\\allure-results'
    ALLURE_REPORT = 'allure-report'
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
      parallel {
        stage('Chrome') {
          steps {
            bat 'mvn test -Dbrowser=chrome -Denv=remote -Dallure.results.directory=target/allure-results-chrome'
          }
        }
        stage('Firefox') {
          steps {
            bat 'mvn test -Dbrowser=firefox -Denv=remote -Dallure.results.directory=target/allure-results-firefox'
          }
        }
        stage('Edge') {
          steps {
            bat 'mvn test -Dbrowser=edge -Denv=remote -Dallure.results.directory=target/allure-results-edge'
          }
        }
      }
    }

    stage('Generate Allure Reports') {
      steps {
        bat 'allure generate target\\allure-results-chrome --clean -o allure-report-chrome'
        bat 'allure generate target\\allure-results-firefox --clean -o allure-report-firefox'
        bat 'allure generate target\\allure-results-edge --clean -o allure-report-edge'
      }
    }

    stage('Archive Reports') {
      steps {
        archiveArtifacts artifacts: 'allure-report-chrome/**', fingerprint: true
        archiveArtifacts artifacts: 'allure-report-firefox/**', fingerprint: true
        archiveArtifacts artifacts: 'allure-report-edge/**', fingerprint: true
      }
    }


//     stage('Publish Allure Report') {
//       steps {
//         script {
//           allure([
//             includeProperties: false,
//             jdk: '',
//             commandline: 'Allure',
//             results: [[path: 'target/allure-results']]
//           ])
//         }
//       }
//     }
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
