[![XXXXXX](https://img.shields.io/badge/-Android-3DDC84?style=for-the-badge&&logo=Android&logoColor=white)](#) [![XXXXXX](https://img.shields.io/badge/-Kotlin-0095D5?style=for-the-badge&&logo=Kotlin&logoColor=white)](#)

### Основний стек:

- Kotlin
- Jetpack Compose
- Firebase
- Hilt
- Coroutines
- Retrofit2
- OkHttp3
- Room

#### Архітектура:

- MVI
- Clean Architecture
- MultiModule

##### Модулі:

+ app
  + Містить основні компоненти (Activity, Application, MainViewModel тощо) та базову навігацію.
+ core
	+ Містить основні бібліотеки, такі як network, localization, common, designsystem, testing тощо.
+ buildSrc
	+ Містить gradle convention plugins. Ми використовуємо плагіни gradle convention для створення ланцюжків залежностей. Блоки залежностей повторюються в різних частинах проекту; плагіни gradle convention можуть впоратися з цим. 
    По суті, у нас є одне місце, де ми вказуємо версії java, залежності модулів і так далі.
    Посилання на статтю -> https://docs.gradle.org/current/samples/sample_convention_plugins.html
+ features
	+ Містить реалізацію кожної фічі.
+ shared
	+ Містить спільні елементи, які можна використовувати у фіча модулях.

### Back-end:

Для Back-end  використовується Firebase та API від TMDB:
