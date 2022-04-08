# Crypto-App

Android project that consumes json data from multiple data sources. Built with MVVM, Kotlin Flow, Clean
architecture.

## Prerequisite

To build this project, you require:

- Android Studio
- Gradle 7.4
- Kotlin 1.6.10
- Android Gradle Plugin 7.1.2

## Architecture

In approaching this project, I focused on the architecture of the data layer. I leveraged moshi
and Kotlin coroutines to parse JSON data that simulated multiple REST endpoints and used Room to
cache the data.

On the UI I used MVI Architecture + Kotlin Flows to build a unidirectional flow of data from the UI to
the data source and back.

The application follows clean architecture in order to take advantage of its inherent benefits such
as scalability, maintainability and testability. It enforces separation of concerns and dependency
inversion, where higher and lower level layers all depend on abstractions.

#### Data

The app merges data from 3 data sources all simulated via JSON files:
- A [currencies]() endpoint
- A [wallets]() endpoint
- A [live exchange rates]() endpoint

I made a data module for each entity (currency-data-lib, rates-data-lib, wallet-data-lib) and each
module contained logic for fetching, caching and manipulating data into a usable state.

- Remote - a package that encapsulates the parser logic and exposes a class `[XXX]DataSource` that
  other packages can consume.
- Cache - a package that encapsulates the caching logic and exposes a class `[XXX]Store` that
  other packages can consume.
- Data - depends on the remote module and cache modules to retrieve the data and defines the business
  logic for switching between the cache and remote data sources.

For dependency injection and asynchronous programming, the project uses Dagger Hilt and Coroutines
with Flow. Dagger Hilt is a fine abstraction over the vanilla dagger boilerplate, and is easy to
setup. Coroutines and Flow brings kotlin's conciseness to asynchronous programming, along with a
fine suite of operators that make it a robust solution.

#### Domain

The domain layer contains the app business logic. It defines contracts for data operations and
domain models to be used in the app. Usecases which represent a single unit of business logic are
also defined in the domain layer, and are consumed by the presentation layer.

Writing mappers and models can take a lot of effort and result in boilerplate, but they make the
codebase much more maintainable and robust by separating concerns.

The domain layer in this project is contained in the consuming feature module:

- wallet
- wallet-details

## Testing

The app contains unit tests in the `xxx-data-lib` and `feature` modules. You can run
all tests by executing the gradle task `runTests`

## UI
On the UI layer, I used MVI to model states for individual components of the app; the approach is
based on ideas borrowed from the following resources:
- [Netflix Tech Blog](https://netflixtechblog.com/making-our-android-studio-apps-reactive-with-ui-components-redux-5e37aac3b244)
- [julianomoraes/componentizationArch](https://github.com/julianomoraes/componentizationArch)
- [NETFLIX'S REACTIVE ANDROID COMPONENTS - THE REDUX REVENGE.](https://www.droidcon.com/2019/08/07/netflixs-reactive-android-components-the-redux-revenge/?video=362740979)
- [Ezike/StarWarsSearch-MVI](https://github.com/Ezike/StarWarsSearch-MVI)
- [aliumujib/Artic](https://github.com/aliumujib/Artic)