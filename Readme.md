# Upload to JCenter/Bintray

### Specs
[ ![Download](https://api.bintray.com/packages/nisrulz/maven/awesomelib/images/download.svg) ](https://bintray.com/nisrulz/maven/awesomelib/_latestVersion)

### Show some :heart:
[![GitHub followers](https://img.shields.io/github/followers/nisrulz.svg?style=social&label=Follow)](https://github.com/nisrulz) [![Twitter Follow](https://img.shields.io/twitter/follow/nisrulz.svg?style=social)](https://twitter.com/nisrulz) 

Base repository to demonstrate the process of uploading an [`aar`](https://sites.google.com/a/android.com/tools/tech-docs/new-build-system/aar-format)/[`jar`](https://en.wikipedia.org/wiki/JAR_(file_format)) to [JCenter/Bintray](https://bintray.com/).

**Blog Post :** [Guide to publishing your Android Library via Jcenter/Bintray](http://crushingcode.co/publish-your-android-library-via-jcenter/) 

The process is as follows

1. Create an Android project or open an existing one in [Android Studio](https://en.wikipedia.org/wiki/Android_Studio)

1. Init the project with git and also create a repo on Github for the same. Each step here onwards represent a commit and should be pushed to github.

1. Create and add a new module and choose `Android Library`.
   > Goto `File>New>New Module..` and select `Android Library`.
   
   ![newmodule](https://github.com/nisrulz/UploadToBintray/raw/master/img/newmodule.jpeg)
   
   ![newlib](https://github.com/nisrulz/UploadToBintray/raw/master/img/newlib.jpeg)
   
   ![newlibinfo](https://github.com/nisrulz/UploadToBintray/raw/master/img/newlibinfo.jpeg)

1. Implement your library code inside the library module you created in the last step.

1. Next add the library module as a dependency to the app module.
   > 1. Goto `File>Project Structure..`
   > 1. Select `app` module in the sidebar
   > 1. Select the `Dependencies` tab
   > 1. At the bottom is a `+` icon, click that and select `Module dependency` and select your `library` module.
   > 1. Press `apply` or `ok`.
   
   ![project](https://github.com/nisrulz/UploadToBintray/raw/master/img/project.jpeg)
   
   ![prjstruct](https://github.com/nisrulz/UploadToBintray/raw/master/img/prjstruct.jpeg)
   
   ![addmodule](https://github.com/nisrulz/UploadToBintray/raw/master/img/addmodule.jpeg)
   
1. Once project is synced, add the required plugins to classpath in build.gradle file at root project level, as shown below
  ```gradle
   dependencies {
      classpath 'com.android.tools.build:gradle:2.1.2'
      ..
      ..
      // Required plugins added to classpath to facilitate pushing to Jcenter/Bintray
      classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.4'
      classpath 'com.github.dcendents:android-maven-gradle-plugin:1.3'
      ..
  ```
  
1. Next, apply the `bintray` and `install` plugins at the bottom of build.gradle file at library module level. Also add the ext variable with required information as shown below
  ```gradle
   apply plugin: 'com.android.library'
   
   ext {
     bintrayRepo = 'maven'
     bintrayName = 'awesomelib'   // Has to be same as your library module name
   
     publishedGroupId = 'com.github.nisrulz'
     libraryName = 'AwesomeLib'
     artifact = 'awesomelib'     // Has to be same as your library module name
   
     libraryDescription = 'Android Library to make any text into Toast with Awesome prepended to the text'
   
     // Your github repo link
     siteUrl = 'https://github.com/nisrulz/UploadToBintray'
     gitUrl = 'https://github.com/nisrulz/UploadToBintray.git'
   
     libraryVersion = '1.0'
   
     developerId = 'nisrulz'
     developerName = 'Nishant Srivastava'
     developerEmail = 'nisrulz@gmail.com'
   
     licenseName = 'The Apache Software License, Version 2.0'
     licenseUrl = 'http://www.apache.org/licenses/LICENSE-2.0.txt'
     allLicenses = ["Apache-2.0"]
   }
   
   ..
   ..
   
   // Place it at the end of the file
   apply from: 'https://raw.githubusercontent.com/nisrulz/JCenter/master/installv1.gradle'
   apply from: 'https://raw.githubusercontent.com/nisrulz/JCenter/master/bintrayv1.gradle'
   
  ```  

1. Edit your `local.properties`
  ```
  bintray.user=<your_bintray_username>
  bintray.apikey=<your_bintray_apikey>
  ```
  
1. Now lets setup Bintray before we can push our artifact to it.
  + Register for an account on [bintray.com](https://bintray.com/) and click the activation email they send you.
  + Add a new Maven repository and click **Create New Package**
  + You should now have a maven repository. For instance:
    `https://bintray.com/nisrulz/maven`
  + Now once you have your maven repo setup , click on **Edit**
  
    ![edit](https://github.com/nisrulz/UploadToBintray/raw/master/img/edit.jpeg)
    
    and see that you have selected the option `GPG sign uploaded files using Bintray's public/private key pair.` and then click **Update**.
    
    ![gpg](https://github.com/nisrulz/UploadToBintray/raw/master/img/gpg.jpeg)
    
  
1. Once everything is configured, run the below in your terminal in your root of the project
  ```gradle
  ./gradlew install bintrayUpload
  ```

1. Now once your project is up on bintray, simply hit **Add to Jcenter** button to sync with JCenter.

  ![addtojcenter](https://github.com/nisrulz/UploadToBintray/raw/master/img/addtojcenter.jpeg)

1. Your code is available 
  + through the private repo at bintray
    ```gradle
    repositories { 
       jcenter()
       maven { url 'https://dl.bintray.com/<bintray_username>/maven' }
    }
    dependencies {
      compile 'com.github.<bintray_username>:<library_module>:1.0'
    }
    ```
    
    i.e for the sample lib in this repo , `awesomelib`
    ```gradle
    repositories { 
       jcenter()
       maven { url 'https://dl.bintray.com/nisrulz/maven' }
    }
    dependencies {
      compile 'com.github.nisrulz:awesomelib:1.0'
    }
    ```
    
  + through JCenter if you have received the mail with confirmation

      ![finalmail](https://github.com/nisrulz/UploadToBintray/raw/master/img/finalmail.jpeg)

    Few things to note when you received the final email.
      + Goto your maven repo at bintray and verify that you have Jcenter under the **Linked to** section

        ![linked](https://github.com/nisrulz/UploadToBintray/raw/master/img/linked.jpeg)

      + Now you would also want to sync the artifact to [MavenCentral](https://search.maven.org/), for that you need to hit the **Maven Central** tab and sync

        ![synctomaven](https://github.com/nisrulz/UploadToBintray/raw/master/img/synctomaven.jpeg)

      + Once you hit sync you would see as below. Wait for few hours for the sync to occur.

        ![syncstatus](https://github.com/nisrulz/UploadToBintray/raw/master/img/syncstatus.jpeg)
    
    You can use the lib now as follows
    
    ```gradle
    dependencies {
        compile 'com.github.<bintray_username>:<library_module>:1.0'
      }
    ```
    i.e for the sample lib in this repo , `awesomelib`
    ```gradle
    dependencies {
          compile 'com.github.nisrulz:awesomelib:1.0'
      }
    ```

1. Let the world know of your **AwesomeLib** :smile:
  > + Add a readme that explains how to integrate and use your Awesome library
  > + Add a license block as in this repo
  > + Also include the Bintray badge provided by Bintray in your readme
  >
  >   ![badge](https://github.com/nisrulz/UploadToBintray/raw/master/img/badge.jpeg)
  > + Promote your lib on social media so that others can know about it.
  > + Always add a working sample app in your project that demonstrates your library in use.
  > + Add screenshots if possible in your readme.


License
=======

    Copyright 2016 Nishant Srivastava

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
