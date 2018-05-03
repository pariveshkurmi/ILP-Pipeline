def CONTAINER_NAME="integratedlearningproject_jenkins"
def CONTAINER_TAG="latest"
def DOCKER_HUB_USER="pariveshdocker"
def DOCKER_HUB_PASSWORD="docker@123"
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

    stage("Image Prune"){
        imagePrune(CONTAINER_NAME)
    }

    stage('Image Build'){
        imageBuild(CONTAINER_NAME, CONTAINER_TAG)
    }

    stage('Push to Docker Registry'){
        withDockerRegistry([ credentialsId: "dockerHubAccount", url: "" ]) {
          sh "docker push pariveshdocker/integratedlearningproject_jenkins:latest"
        }
    }
    stage('Email sent'){
        mail bcc: '', body: 'Test Success', cc: '', from: '', replyTo: '', subject: 'Test', to: 'pariveshkurmi.mit@gmail.com'
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

def pushToImage(containerName, tag, dockerUser, dockerPassword){
    sh "docker login -u $dockerUser -p $dockerPassword"
    sh "docker tag $containerName:$tag $dockerUser/$containerName:$tag"
    sh "docker push $dockerUser/$containerName:$tag"
    echo "Image push complete"
}
