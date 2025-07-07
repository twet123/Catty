import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.xcode
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2025.03"

project {

    vcsRoot(HttpsGithubComPipelinestestaccSimplePublicRepository)

    buildType(BuildXCodeApp)
}

object BuildXCodeApp : BuildType({
    name = "Build XCode App"

    vcs {
        root(DslContext.settingsRoot)
        root(HttpsGithubComPipelinestestaccSimplePublicRepository)
    }

    steps {
        xcode {
            name = "Build Catty"
            id = "Build_Catty"
            projectPath = "src/Catty.xcodeproj"
            buildType = schemeBased {
                scheme = "Catty (DEBUG)"
                outputDirectory = default()
            }
        }
    }
})

object HttpsGithubComPipelinestestaccSimplePublicRepository : GitVcsRoot({
    name = "https://github.com/pipelinestestacc/simple-public-repository"
    url = "https://github.com/pipelinestestacc/simple-public-repository"
    branch = "refs/heads/main"
})
