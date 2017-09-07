package com.whyn.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.ProjectConfigurationException

class InjectManifest implements Plugin<Project> {

    void apply(Project project) {
        dependencies(project);
        project.extensions.create('manifestPath', ManifestConfigExt, project)

        project.afterEvaluate {
            Map<String, String> arguments = new HashMap<String, String>()
//            arguments.put("AndroidManifestPath", project.android.sourceSets.main.manifest.srcFile.absolutePath)
            arguments.put("AndroidManifestPath",project.manifestPath.originManifestPath);
            project.android.defaultConfig.javaCompileOptions.annotationProcessorOptions.arguments = arguments
            print "path============="
            println "${project.android.defaultConfig.javaCompileOptions.annotationProcessorOptions.arguments["AndroidManifestPath"]}"

//            def variants = determineVariants(project)
//
//            project.android[variants].all { variant ->
//                configureVariant(project, variant)
//            }


        }
    }

    private static String determineVariants(Project project) {
        if (project.plugins.findPlugin('com.android.application')) {
            return 'applicationVariants';
        } else if (project.plugins.findPlugin('com.android.library')) {
            return 'libraryVariants';
        } else {
            throw new ProjectConfigurationException('The com.android.application or com.android.library plugin must be applied to the project', null)
        }
    }


    private static void configureVariant(Project project, def variant) {
        def javaCompile = variant.hasProperty('javaCompiler') ? variant.javaCompiler : variant.javaCompile
        javaCompile.doLast {
            println "detected: originManifestPath:${project.manifestPath.originManifestPath}"
            println "detected: genManifestPath:${project.manifestPath.genManifestPath}"

            def genManifest = new File(project.manifestPath.genManifestPath)
            def originManifest = new File(project.manifestPath.originManifestPath);
            if (genManifest.exists()) {
                if (originManifest.exists())
                    originManifest.delete()
                if (genManifest.renameTo(originManifest)) {
                    // Success!!1 Files moved.
                    println 'Inject AndroidManifest successfully.'
                } else {
                    println 'Inject AndriodManifest failed.'
                }
            }
        }
    }

    private static void dependencies(Project project) {
        project.dependencies {
//            compile 'com.whyn:threaddispatcher:1.1.1'
        }
    }

}
//
class ManifestConfigExt {
    def String originManifestPath
    def String genManifestPath

    ManifestConfigExt(Project project) {
        originManifestPath = "$project.projectDir/src/main/AndroidManifest.xml"
        genManifestPath = "$project.buildDir/generated/source/apt/debug/AndroidManifest.xml"
    }

}


