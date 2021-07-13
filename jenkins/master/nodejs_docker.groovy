job('NodeJS Docker example') {
    scm {
        git('git://github.com/eitzhaki/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName(eyali/test)
            tag('${GIT_REVISION,length=9}')
            registryCredentials('eyali-repo')
            buildContext('./jenkins/master/nodejs.groovy')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

