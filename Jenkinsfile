pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                git 'https://github.com/OksGG/BookStoreApplicationProject'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn clean compile
            }
        }
    }
  post {
          always {
              allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]

             }
          }
}
