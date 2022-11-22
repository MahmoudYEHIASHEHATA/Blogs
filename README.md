# MVI-Clean-Architecture
This is a sample app & basic code that demonstrate how to build an Android application using the Uncle Bob's Clean Architecture approach.

The trick of the project is to demonstrate best practices, provide a set of guidelines, and present modern Android Application Architecture that is modular, scalable, maintainable and testable, suitable for bigger teams and long application lifecycle management.

<img src="https://miro.medium.com/max/4800/1*D1EvAeK74Gry46JMZM4oOQ.png" width="500">

### Flow 
This app uses [_**MVI (Model View Intent)**_](https://proandroiddev.com/mvi-architecture-with-kotlin-flows-and-channels-d36820b2028d) architecture.
 
<img src="https://miro.medium.com/max/1400/1*3u5JnmqONR4UnwRE6tEV3Q.png" width="500">


**Coroutines VS RXJava**
-------------------
They're different tools with different strengths. Like a tank and a cannon, they have a lot of overlap but are more or less desirable under different circumstances.
        - Coroutines Is light wight threads for asynchronous programming.
        - RX-Kotlin/RX-Java is functional reactive programming, its core pattern relay on
        observer design pattern, so you can use it to handle user interaction with UI while you
        still using coroutines as main core for background work.

**How does Coroutines concept work ?**
------------
 - Kotlin coroutine is a way of doing things asynchronously in a sequential manner. Creating a coroutine is a lot cheaper vs creating a thread.


**When I can choose Coroutines or RX-Kotlin to do some behaviour ?**
--------------------------
 - Coroutines : *When we have concurrent tasks , like you would fetch data from Remote connections
 , database , any background processes , sure you can use RX in such cases too, but it looks like
  you use a tank to kill ant.*
 - RX-Kotlin : *When you would to handle stream of UI actions like : user scrolling , clicks ,
 update UI upon some events .....ect .*


**What is the Coroutines benefits?**
-----------------------------

 - Writing an asynchronous code is sequential manner.
 - Costing of create coroutines are much cheaper to crate threads.
 - Don't be over engineered to use observable pattern, when no need to use it.
 - parent coroutine can automatically manage the life cycle of its child coroutines for you.
 
 # Features 🎨
- list of the author's name and image.
- Display the description, posts and detail of the author that the user select.

### Modules 🚧
Modules are the collection of source files and build settings that allow you to divide your project into discrete units of functionality.

- **App Module**

  `:app` module is an [com.android.application](https://developer.android.com/studio/projects/android-library), which is needed to create the app bundle. It contains dependency graph and UI related classes. It presents data to screen and handle user interactions.

- **Base Module**

  `:base` module contains only framework related base classes that is used in other modules

- **Common Module**

  `:common` module contains code and resources which are shared between other modules

- **Data Module**

  `:data` module contains implementation of repository and local - remote repository interface adapt
  
- **Domain Module**

  `:domain` module contains use cases and repository interface adapt
  
- **Local Module**

  `:local` module contains local data source related classes
  
 - **Remote Module**
 
	`:remote` module contains remote data source related classes
	  
 - **Presentation Module**
 
	  `:presentation` module contains business logic

Each module has its own test.

### Tech Stack 🔨
- [Kotlin](https://kotlinlang.org)
- [Jetpack](https://developer.android.com/jetpack)
	* [Android KTX](https://developer.android.com/kotlin/ktx)
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
    * [Data Binding](https://developer.android.com/topic/libraries/data-binding)
    * [View Binding](https://developer.android.com/topic/libraries/view-binding)
    *  [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
    * [Room](https://developer.android.com/training/data-storage/room)
- [Coroutines - Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
  - [State Flow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
  -   [Shared Flow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
  -  [Channels](https://kotlinlang.org/docs/channels.html#channel-basics)
- [Dagger Hilt](https://dagger.dev/hilt/)
- [Retrofit](https://square.github.io/retrofit/)
- [OkHttp](https://github.com/square/okhttp)
- [KotlinX](https://github.com/Kotlin/kotlinx.serialization)
- [KotlinX Serialization Converter](https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter)
- [LeakCanary](https://square.github.io/leakcanary/)
- [Testing](https://developer.android.com/training/testing/fundamentals)
    *  [MockK](https://mockk.io/)
    * [Junit4](https://junit.org/junit4/)
    * [Truth](https://github.com/google/truth)
    * [Turbine](https://github.com/cashapp/turbine)
    * [Fragment Testing](https://developer.android.com/guide/fragments/test)
    * [Navigation Testing](https://developer.android.com/guide/navigation/navigation-testing)
    * [Coroutine Test](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test)
    * [Barista](https://github.com/AdevintaSpain/Barista)
    * [Dagger Hilt Testing](https://developer.android.com/training/dependency-injection/hilt-testing)
