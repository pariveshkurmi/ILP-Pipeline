def CONTAINER_NAME="integratedlearningproject_jenkins"
def CONTAINER_TAG="latest"
def DOCKER_HUB_USER="pariveshdocker"
node {
    
    stage('Initialize'){
        def dockerHome = tool 'myDocker'
        def mavenHome  = tool 'myMaven'
        env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
        echo env.PATH
        echo dockerHome
        echo mavenHome
    }

    stage('Checkout') {
        checkout scm
    }

    stage('Build'){
        sh "mvn clean compile"
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
        docker.withRegistry('https://registry.hub.docker.com', 'dockerHubAccount') {
            pushToImage(CONTAINER_NAME, CONTAINER_TAG, DOCKER_HUB_USER)
        }
    }

}

def imagePrune(containerName){
    try {
        sh "docker --version"
        sh "docker ps"
        sh "docker image prune -f"
        sh "docker stop $containerName"
    } catch(error){}
}

def imageBuild(containerName, tag){
    sh "docker build -t $containerName:$tag  -t $containerName --pull --no-cache ."
    echo "Image build complete"
}

def pushToImage(containerName, tag, dockerUser){
    sh "docker tag demo_registry:$tag $dockerUser/demo_registry:$tag"
    sh "docker push $dockerUser/demo_registry:$tag"
    echo "Image push complete"
}
