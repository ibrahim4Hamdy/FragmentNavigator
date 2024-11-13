[![](https://jitpack.io/v/ibrahim4Hamdy/FragmentNavigator.svg)](https://jitpack.io/#ibrahim4Hamdy/FragmentNavigator)
![GitHub License](https://img.shields.io/github/license/ibrahim4Hamdy/FragmentNavigator?style=flat)


# FragmentNavigator

`FragmentNavigator` is a lightweight Android library designed to simplify navigation between fragments in your Android applications. It provides an easy-to-use API for managing fragment transitions, handling the fragment back stack, and optimizing performance by loading fragments only when necessary.

## 🚀 Why FragmentNavigator?

In Android, managing navigation between multiple screens (Fragments) can become complex, especially when handling configurations and managing the fragment back stack. Some common challenges include:

- Overlapping navigation or losing control over the navigation stack.
- Difficulty returning to specific screens or managing directed navigation.
- Complexity in managing the state of each fragment correctly.

## 🛠️ How FragmentNavigator Solves This

FragmentNavigator simplifies the navigation process by providing an easy-to-use interface for managing fragment transitions. With this library, you can:

- Control navigation between screens.
- Manage fragment back stacks and remove unwanted fragments.
- Use a flexible API for navigating within the application in just a few simple steps.

## 📥 Installation

To add the library to your project, ensure that you add JitPack to the repositories section in your `settings.gradle` file as shown below:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```
## 🔗 Add the dependency in `build.gradle`

Then, add the library dependency in the `dependencies` section of your `build.gradle` file:
```kotlin
dependencies {
    implementation("com.github.ibrahim4Hamdy:FragmentNavigator:Tag")
}
```
## ⚙️ Usage

### Basic Steps to Use FragmentNavigator

1. **Initialize FragmentNavigator**:  
   Set up the library at the entry point in your app (such as the main Activity).

2. **Perform Navigation**:  
   Use `FragmentNavigator` to navigate between screens by fragment name or custom identifiers.

3. **Back Stack Management**:  
   `FragmentNavigator` offers functions to manage the back stack easily, allowing you to return to specific screens or end navigation sequences in an organized way.

## Example Code

Here’s a simple example of how to initialize and use `FragmentNavigator`:

```java
BottomNavigationView navigationView; 
// NavigatorX navigator;
Fragment home = new HomeFragment();
Fragment offers = new OffersFragment();
Fragment saved = new SavedFragment();
Fragment cart = new CartFragment();
Fragment profile = new ProfileFragment();

NavigatorX navigator;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    navigationView = findViewById(R.id.BottomBar);

    navigationView.setOnNavigationItemSelectedListener(this);
    navigator = new NavigatorX(getSupportFragmentManager());

    navigator.addFragment(R.id.ic_home, home, false);
    navigator.addFragment(R.id.ic_offers, offers, false);
    navigator.addFragment(R.id.ic_saved, saved, true);
    navigator.addFragment(R.id.ic_cart, cart, false);
    navigator.addFragment(R.id.ic_profile, profile, true);
    navigator.setCurrentFragment(R.id.ic_home, home, R.id.frame_container);
}

@Override
public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    return navigator.navigateToFragment(item.getItemId(), R.id.frame_container);
}
```

