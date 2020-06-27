# Kotlin-Repositories
android app with Kotlin

<div style="text-align: center">
    <table>
        <tr>
            <td style="text-align: center">
                    <img src="https://github.com/Hugo-Coutinho/Kotlin-Repositories/blob/master/app/src/main/java/com/example/kotlinrepositories/core/readmeFiles/opening.gif?raw=true" width="200" height="350"/>
                </a>
            </td>            
            <td style="text-align: center">
                    <img src="https://github.com/Hugo-Coutinho/Kotlin-Repositories/blob/master/app/src/main/java/com/example/kotlinrepositories/core/readmeFiles/scrolling.gif?raw=true" width="200" height="350"/>
                </a>
            </td>            
            <td style="text-align: center">
                    <img src="https://github.com/Hugo-Coutinho/Kotlin-Repositories/blob/master/app/src/main/java/com/example/kotlinrepositories/core/readmeFiles/error.gif?raw=true" width="200" height="350"/>
                </a>
            </td>            
        </tr>
        <tr>         
            <td style="text-align: center">
                    <img src="https://github.com/Hugo-Coutinho/Kotlin-Repositories/blob/master/app/src/main/java/com/example/kotlinrepositories/core/readmeFiles/webView.gif?raw=true" width="200" height="350"/>
                </a>
            </td>            
            <td style="text-align: center">
                    <img src="https://github.com/Hugo-Coutinho/Kotlin-Repositories/blob/master/app/src/main/java/com/example/kotlinrepositories/core/readmeFiles/pull_request.gif?raw=true" width="200" height="350"/>
                </a>
            </td>            
                        <td style="text-align: center">
                    <img src="https://github.com/Hugo-Coutinho/Kotlin-Repositories/blob/master/app/src/main/java/com/example/kotlinrepositories/core/readmeFiles/empty.gif?raw=true" width="200" height="350"/>
                </a>
            </td>            
        </tr>
    </table>
</div>

## About this Project

The idea of the App is:

" *Listing github kotlin repositories and seeing their pull requests* ".

## Why?

This project is part of my personal portfolio, so, I'll be happy if you could provide me any feedback about the project, code, structure or anything that you can report that could make me a better developer!

Email-me: hugocoutinho2011@gmail.com

Connect with me at [LinkedIn](https://www.linkedin.com/in/hugo-coutinho-aaa3b0114/).

## File structure

Clean architecture was used.
[put image here]

## ViewModel Architecture Components

I wanted something to manage the state of my app. Working with ViewModel/fragments, I can separate the state of my app with fragment and managing using ViewModel/LiveData.

**With liveData i can holder the current state of the app, and later update it.**
```kotlin
class HomeViewModel(private val useCase: HomeUseCase): ViewModel()
var currentState: MutableLiveData<HomeState> = MutableLiveData()
```

**Using rxJava, i subscribe it the repositories and upate the app current state.**
```kotlin
    private fun didInitGetKotlinRepositories() {
        Logger.i("requesting repositories by page 1")
        useCase.getKotlinRepositories(1)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .subscribe({
                Logger.i("success parse for entity with ${it.count()} repositories")
                this.currentState.postValue(HomeSuccessState(it))
            }, {
                Logger.wtf("request fails ${it.localizedMessage}")
                this.currentState.postValue(HomeErrorState(Constant.HomeErrorMessage))
            })
    }
```

**In my activity, i observe the state and replace the fragment for the next.**
```kotlin
    private fun observeHomeState() {
        this.viewModel.currentState.observe(this, Observer {
            if (it is HomeSuccessState) {
                fragmentManager.replace(this, R.id.home_fragment_container, HomeListingRepositoriesFragment(this.viewModel, it.items))
            } else if (it is HomeErrorState) {
                fragmentManager.replace(this, R.id.home_fragment_container, ErrorFragment(it.message))
            }
        })
    }
```

## Built With

- [Mockito](https://site.mockito.org) - Mocking framework that tastes really good. It lets you write beautiful tests with a clean & simple API.
- [Junit](https://junit.org/junit5/) - To create an up-to-date foundation for developer-side testing on the JVM.
- [Espresso](https://developer.android.com/training/testing/espresso) - Use Espresso to write concise, beautiful, and reliable Android UI tests.
- [Koin](https://github.com/InsertKoinIO/koin) - A pragmatic lightweight dependency injection framework for Kotlin developers.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [RxJava3](https://github.com/ReactiveX/RxJava) - a library for composing asynchronous and event-based programs using observable sequences for the Java VM.
- [Logger](https://github.com/orhanobut/logger) - Simple, pretty and powerful logger for android.
- [okHttp](https://github.com/square/okhttp) - Square’s meticulous HTTP client for Java and Kotlin.
- [Picasso](https://github.com/square/picasso) - A powerful image downloading and caching library for Android.
- [Lottie](https://github.com/airbnb/lottie/blob/master/android.md) - Easily add high-quality animation to any native app.
- [CircleImageView](https://github.com/hdodenhof/CircleImageView) - A circular ImageView for Android.


## Contributing

You can send how many PR's do you want, I'll be glad to analyse and accept them! And if you have any question about the project...

Email-me: hugocoutinho2011@gmail.com

Connect with me at [LinkedIn](https://www.linkedin.com/in/hugo-coutinho-aaa3b0114/)

Check my development techniques: [My personal study annotations](http://bloghugocoutinho.wordpress.com)

Thank you!
