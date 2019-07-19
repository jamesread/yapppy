pipeline { 
	node {
		stage("Build") {
			deleteDir()
			checkout scm
			sh "mvn install"
			archiveArtifacts artifacts: 'target/**/*.jar'
			archiveArtifacts artifacts: 'target/**/*.hpi'
		}
	}
}
