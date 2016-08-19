package com.hujiang.gradle.plugin.hjbc

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * class description here
 * @author simon
 * @version 1.0.0
 * @since 2016-08-11
 */

class HJBCPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        if (!(project.plugins.hasPlugin(LibraryPlugin) || project.plugins.hasPlugin(AppPlugin))) {
            throw new IllegalStateException('hjbc plugin can only be applied to android projects')
        }

        project.dependencies {
            compile 'com.hujiang:hjbc-annotation:1.0.0-SNAPSHOT'
            apt 'com.hujiang:hjbc-compiler:1.0.0-SNAPSHOT'
        }
    }
}