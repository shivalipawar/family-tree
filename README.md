**Cricket App**
Install [gradle](https://gradle.org/install/) and make sure you have JDK installed on box.

*Tasks*

 1. Test
```sh
./gradlew.bat clean compile
```
This will run all Unit tests belong to this appication.
	
 2. Build App
```sh
./gradlew.bat clean build
```
This will bundle executable jar under build/libs directory of your app folder

 3. Run App
```sh
java -jar build/libs/CricketMatch.jar
```
This task will run executable jar(Main class of Game)
