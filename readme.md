# Android Aspect model 引入说明

1. 工程引入aspectplugin插件
2. Android Studio Gradle窗口中，双击 工程名-aspectplugin-Tasks-publishing-publish，然后在工程根目录下会生成插件的repo目录（建议将repo目录添加到.gitignore）
3. 在工程根目录build.gradle中添加如下内容：
```
buildscript {
    repositories {
	//...
	// aspect-01.配置插件路径
	maven {
        url('repo')
    }
	//...
	
buildscript {
    dependencies {
	    //...
	    // aspect-02.配置插件
        classpath "com.ain.ipc.aspectplugin:AspectJPlugin:1.0.0"
        classpath "org.aspectj:aspectjrt:1.8.14"
		//...
		

allprojects {
    repositories {
        //...
	    // aspect-03.配置插件路径
        maven {
            url('repo')
        }

```
4. 在app目录build.gradle中添加如下内容：
```
apply plugin: 'com.android.application'

// aspect-04.引入插件
import com.ain.ipc.aspectplugin.AspectJPlugin
apply plugin: AspectJPlugin

//...
dependencies {
//...

    // aspect-05.引入插件依赖库
    implementation 'org.aspectj:aspectjrt:1.8.14'
}
```
5. 之后就可以在app目录下引用aspectj了