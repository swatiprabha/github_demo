
# Android - Clean Architecture - Kotlin
The purpose of this repo is to follow up Clean Architecture principles by bringing them to Android. The repo contains a Sample Game Application which shows Games from https://www.freetogame.com/api/ API.

## Clean Architecture

Clean architecture promotes separation of concerns, making the code loosely coupled. This results in a more testable and flexible code. This approach divides the project into 3 modules: presentation, data, and domain.

* __Presentation__: Layer with the Android Framework, the MVVM pattern and the DI module. Depends on the domain to access the use cases and on di, to inject dependencies.
* __Domain__: Layer with the business logic. Contains the use cases, in charge of calling the correct repository or data member.
* __Data__: Layer with the responsibility of selecting the proper data source for the domain layer. It contains the implementations of  the repositories declared in the domain layer. It may, for example, check if the data in a database is up to date, and retrieve it from service if it’s not.



There are a three core components described:
* **State** - data class that holds the state content of the corresponding screen e.g. list of `User`, loading status etc. The state is exposed as a Compose runtime `MutableState` object from that perfectly matches the use-case of receiving continuous updates with initial value.

* **Event** - plain object that is sent through callbacks from the UI to the presentation layer. Events should reflect UI events caused by the user. Event updates are exposed as a `MutableSharedFlow` type which is similar to `StateFlow` and that behaves as in the absence of a subscriber, any posted event will be immediately dropped.

* **Effect** - plain object that signals one-time side-effect actions that should impact the UI e.g. triggering a navigation action, showing a Toast, SnackBar etc. Effects are exposed as `ChannelFlow` which behave as in each event is delivered to a single subscriber. An attempt to post an event without subscribers will suspend as soon as the channel buffer becomes full, waiting for a subscriber to appear.

Every screen/flow defines its own contract class that states all corresponding core components described above: state content, events and effects.
