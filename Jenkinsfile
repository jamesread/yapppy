node {
	stage("Build") {
		deleteDir()
		checkout scm
		sh "mvn install"
	}
}
