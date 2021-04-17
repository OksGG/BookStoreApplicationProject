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
                sh 'mvn clean test -Dtest=Runner.java'
            }
        }
    }
  post {
          always {
              allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
              cleanWs(cleanWhenNotBuilt: false,
                                      deleteDirs: true,
                                      disableDeferredWipeout: true,
                                      notFailBuild: true,
                                      patterns: [[pattern: '.gitignore', type: 'INCLUDE'],
                                                 [pattern: '.propsfile', type: 'EXCLUDE']])
                          }
          }
}
