package com.ain.ipc.aspectplugin

import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

class AspectJPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def log = project.logger
        project.dependencies {
            implementation 'org.aspectj:aspectjrt:1.8.14'
        }
        project.android.applicationVariants.all { variant ->
            //def javaCompile = variant.javaCompile
            JavaCompile javaCompile
            if (variant.hasProperty('javaCompileProvider')) {
                javaCompile = variant.javaCompileProvider.get()
            } else {
                javaCompile = variant.hasProperty('javaCompiler') ? variant.javaCompiler : variant.javaCompile
            }
            javaCompile.doLast {
                String[] args = ["-showWeaveInfo",
                                 "-1.8",
                                 "-inpath", javaCompile.destinationDir.toString(),
                                 "-aspectpath", javaCompile.classpath.asPath,
                                 "-d", javaCompile.destinationDir.toString(),
                                 "-classpath", javaCompile.classpath.asPath,
                                 "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]

                MessageHandler handler = new MessageHandler(true);
                new Main().run(args, handler);
                for (IMessage message : handler.getMessages(null, true)) {
                    switch (message.getKind()) {
                        case IMessage.ABORT:
                        case IMessage.ERROR:
                        case IMessage.FAIL:
                            log.error message.message, message.thrown
                            break;
                        case IMessage.WARNING:
                            log.warn message.message, message.thrown
                            break;
                        case IMessage.INFO:
                            log.info message.message, message.thrown
                            break;
                        case IMessage.DEBUG:
                            log.debug message.message, message.thrown
                            break;
                    }
                }
            }
        }
    }
}
