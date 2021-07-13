pipelineJob('boilerplate-pipeline') {
    scm {
             scriptPath('./basic/misc/Jenkinsfile.v2')
        
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('eyali/test')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('eyali-repo')
            buildContext('basics')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
