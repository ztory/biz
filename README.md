# biz
You cant spell business without biz..!

## What is biz?
A collection of classes and interfaces that will help you to structure and work with business rules and implementations!

## Enough! I want to use it, tell me how!

#### Step 1
In your base `gradle.build` file (the one in project root), add this:
```
maven { url "https://github.com/ztory/biz/raw/master/maven-repository/" }
```
So that it will look something like this:
```
allprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven { url "https://github.com/ztory/biz/raw/master/maven-repository/" }
    }
}
```

#### Step 2
In your module `build.gradle` add this:
```
compile 'com.ztory.lib.biz:biz_module:1.0.1'
```

## What else?

If you have something that would make a great addition to the library, well then just FORK and PR I'd say!

If you have any problems or suggestions just create one of those [GitHub Issues](https://github.com/ztory/biz/issues)!

Happy coding! =]
