# CommonProject
基类项目

使用: 

1.在项目根build.gradle加入: 

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
2. 在使用的模块加入:

```
dependencies {
    
    //必导
	 implementation 'com.github.roujiaomo:CommonProject:v1.1'
   //以下的根据需求导入
   implementation 'com.squareup.retrofit2:retrofit:2.3.0'
   implementation 'io.reactivex.rxjava2:rxandroid:2.0.2'
   implementation 'io.reactivex.rxjava2:rxjava:2.x.y'
   implementation 'com.google.code.gson:gson:2.8.5'
   implementation 'com.jakewharton.rxbinding2:rxbinding:2.0.0' 
   implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
   implementahtion 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
   implementation 'com.github.bumptech.glide:glide:4.6.1'
   annotationProcessor 'com.github.bumptech.glide:compiler:4.6.1'
   implementation 'android.arch.lifecycle:extensions:1.1.1'
   implementation 'androidx.recyclerview:recyclerview:1.1.0'
}


```
