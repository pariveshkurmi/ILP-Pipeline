def CONTAINER_NAME="integratedlearningproject_jenkins"
def CONTAINER_TAG="latest"
def DOCKER_HUB_USER="pariveshdocker"
def HTTP_PORT="8080"

node {
    
	    stage('Initialize'){
	    
	        def dockerHome = tool 'myDocker'
	        def mavenHome  = tool 'myMaven'
	        env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
	    }
	
	    stage('Checkout') {
	        checkout scm
	    }
	
	    stage('Build and deploy to Repository'){
	        sh "mvn clean deploy"
	    }
	    
		stage('Sonar'){
	        try {
	            sh "mvn sonar:sonar"
	        } catch(error){
	            echo "The sonar server could not be reached ${error}"
	        }
	     }
	     
	     stage("Image Prune"){
	        imagePrune(CONTAINER_NAME)
	    }
	
	    stage('Image Build'){
	        imageBuild(CONTAINER_NAME, CONTAINER_TAG)
	    }
	
	    stage('Push to Docker Registry'){
	    
	    withDockerRegistry(registry: [credentialsId: 'dockerHubAccount', url: ''], toolName: 'myDocker') {
   			pushToImage(CONTAINER_NAME, CONTAINER_TAG, DOCKER_HUB_USER)
		}
	    
	   }
		
		stage('Run App'){
			removeExistingContaier(CONTAINER_NAME)
	        runApp(CONTAINER_NAME, CONTAINER_TAG, DOCKER_HUB_USER, HTTP_PORT)
	    }
	    
	    stage('Build Result'){
	    	mail bcc: '', body: 'Test Success', cc: '', from: '', replyTo: '', subject: 'The Pipeline Success :-)', to: 'pariveshkurmi.mit@gmail.com'
	    }
}

def imagePrune(containerName){
    try {
        sh "docker image prune -f"
        sh "docker stop $containerName"
    } catch(error){}
}

def removeExistingContaier(containerName){
	try {
    	sh "docker rm -f $containerName"
    	echo "Remove Container complete"
   } catch(error){}
}

def imageBuild(containerName, tag){
    sh "docker build -t $containerName:$tag  -t $containerName --pull --no-cache ."
    echo "Image build complete"
}

def pushToImage(containerName, tag, dockerHubUser){
    sh "docker push $dockerHubUser/$containerName:$tag"
    echo "Image push complete"
}

def runApp(containerName, tag, dockerHubUser, httpPort){
    sh "docker pull $dockerHubUser/$containerName"
    sh "docker run -d --rm -p $httpPort:$httpPort --name $containerName $containerName:$tag"
    echo "Application started on port: ${httpPort} (http)"
}
