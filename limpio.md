This file is a merged representation of a subset of the codebase, containing specifically included files, combined into a single document by Repomix.

<file_summary>
This section contains a summary of this file.

<purpose>
This file contains a packed representation of a subset of the repository's contents that is considered the most important context.
It is designed to be easily consumable by AI systems for analysis, code review,
or other automated processes.
</purpose>

<file_format>
The content is organized as follows:
1. This summary section
2. Repository information
3. Directory structure
4. Repository files (if enabled)
5. Multiple file entries, each consisting of:
  - File path as an attribute
  - Full contents of the file
</file_format>

<usage_guidelines>
- This file should be treated as read-only. Any changes should be made to the
  original repository files, not this packed version.
- When processing this file, use the file path to distinguish
  between different files in the repository.
- Be aware that this file may contain sensitive information. Handle it with
  the same level of security as you would the original repository.
</usage_guidelines>

<notes>
- Some files may have been excluded based on .gitignore rules and Repomix's configuration
- Binary files are not included in this packed representation. Please refer to the Repository Structure section for a complete list of file paths, including binary files
- Only files matching these patterns are included: src/**/*.java, pom.xml, build.gradle
- Files matching patterns in .gitignore are excluded
- Files matching default ignore patterns are excluded
- Files are sorted by Git change count (files with more changes are at the bottom)
</notes>

</file_summary>

<directory_structure>
build.gradle
</directory_structure>

<files>
This section contains the contents of the repository's files.

<file path="build.gradle">
buildscript {
  repositories {
    mavenCentral()
    maven { url 'https://s01.oss.sonatype.org' }
    gradlePluginPortal()
    mavenLocal()
    google()
    maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }
    maven { url 'https://s01.oss.sonatype.org/content/repositories/snapshots/' }
  }
  dependencies {


  }
}

allprojects {
  apply plugin: 'eclipse'
  apply plugin: 'idea'

  // This allows you to "Build and run using IntelliJ IDEA", an option in IDEA's Settings.
  idea {
    module {
      outputDir file('build/classes/java/main')
      testOutputDir file('build/classes/java/test')
    }
  }
}

configure(subprojects) {
  apply plugin: 'java-library'
  sourceCompatibility = 11

  // From https://lyze.dev/2021/04/29/libGDX-Internal-Assets-List/
  // The article can be helpful when using assets.txt in your project.
  tasks.register('generateAssetList') {
    inputs.dir("${project.rootDir}/assets/")
    // projectFolder/assets
    File assetsFolder = new File("${project.rootDir}/assets/")
    // projectFolder/assets/assets.txt
    File assetsFile = new File(assetsFolder, "assets.txt")
    // delete that file in case we've already created it
    assetsFile.delete()

    // iterate through all files inside that folder
    // convert it to a relative path
    // and append it to the file assets.txt
    fileTree(assetsFolder).collect { assetsFolder.relativePath(it) }.each {
      assetsFile.append(it + "\n")
    }
  }
  processResources.dependsOn 'generateAssetList'

  compileJava {
    options.incremental = true
  }
}

subprojects {
  version = '$projectVersion'
  ext.appName = 'ProyectoBlockBreaker'
  repositories {
    mavenCentral()
    maven { url 'https://s01.oss.sonatype.org' }
    // You may want to remove the following line if you have errors downloading dependencies.
    mavenLocal()
    maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }
    maven { url 'https://s01.oss.sonatype.org/content/repositories/snapshots/' }
    maven { url 'https://jitpack.io' }
  }
}

eclipse.project.name = 'ProyectoBlockBreaker' + '-parent'
</file>

</files>
