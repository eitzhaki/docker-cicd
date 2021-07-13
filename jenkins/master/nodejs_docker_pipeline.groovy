pipelineJob('boilerplate-pipeline') {
    definition{
        cpsScm {
            scm{
                git('git://github.com/eitzhaki/docker-cicd.git', 'master') {  node -> // is hudson.plugins.git.GitSCM
                }
                scriptPath('./basics/misc/Jenkinsfile.v2')
                }
            }
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
