# Gym Rental App

A small native Android demonstration of a gym-equipment rental flow. It addresses the basic customer journey—finding available equipment, building a cart, entering delivery details, and reviewing previous orders—without requiring a remote service. The current catalog, authentication, cart, and order history are all local to the app.

## Verified features

- Splash and login flow with an optional “remember me” session
- Two-column equipment catalog populated with 20 sample products
- Name search and an available-only filter
- Cart item quantities and calculated totals
- Checkout validation for name, phone, and address
- Local order history
- Persistent cart and order data through `SharedPreferences` and Gson

No backend, payment processor, inventory service, or real delivery integration is present.

## Technology

- Java 11
- Android SDK: min 29, target/compile 35
- Android Gradle Plugin 8.9.1 and Gradle 8.11.1
- AndroidX AppCompat 1.7.0, Activity 1.8.0, and ConstraintLayout 2.1.4
- Material Components 1.12.0
- Gson 2.10.1
- JUnit 4.13.2 and AndroidX Espresso 3.6.1 test dependencies

## Structure

```text
app/
├── src/main/java/com/example/gymrentalapp/
│   ├── *Activity.java          # Screens and navigation
│   ├── *Adapter.java           # RecyclerView adapters
│   ├── Equipment.java          # Catalog model
│   ├── CartItem.java           # Cart model
│   └── SharedPrefManager.java  # Local cart and order persistence
├── src/main/res/layout/        # XML screen and row layouts
├── src/main/res/values/        # Strings, colors, and themes
└── src/test, src/androidTest/  # Generated example tests
```

The app uses an activity-based UI. `HomeActivity` owns the in-memory sample catalog, while `SharedPrefManager` serializes carts and order history to device preferences.

## Build and run

### Requirements

- Android Studio with JDK 17 (the source compatibility level is Java 11)
- Android SDK 35
- An emulator or device running Android 10 (API 29) or newer

Open the repository root in Android Studio, allow Gradle to sync, select the `app` run configuration, and run it on a device or emulator.

From a terminal at the repository root:

```powershell
.\gradlew.bat assembleDebug
.\gradlew.bat test
```

The debug APK is produced under `app\build\outputs\apk\debug\`.

## Demo usage

The following credentials are hardcoded for this local demonstration only:

```text
Username: mohammed
Password: 1221175
```

After login, search or filter the catalog, add available equipment to the cart, adjust quantities, and complete checkout. Confirmed orders move from the cart into local order history. Clearing app data resets all stored state.

## Configuration and security notes

- Authentication is not production authentication: the username and password are embedded in `LoginActivity`.
- “Remember me” stores both values as plaintext in `SharedPreferences`. Do not reuse the demo password for any real account.
- Cart, order, delivery, and login data remain unencrypted on the device and may be included in Android backups because backups are enabled.
- Prices, availability, and equipment data are hardcoded; there is no server-side source of truth or concurrency control.
- Checkout records an order locally but does not charge a payment method or submit data to a business.
- Release minification is disabled.
- Several `.idea` files are tracked. Team-specific IDE metadata should be reviewed and generally excluded; the Gradle wrapper JAR is expected wrapper infrastructure and should remain tracked.

## Testing status

Only the Android Studio template unit and instrumentation tests are checked in; they do not cover application behavior. The Gradle commands above build and execute those existing test targets.

## Realistic next steps

1. Replace hardcoded login with a backend or platform authentication provider and encrypted session storage.
2. Move catalog, availability, pricing, and orders to a validated API.
3. Add ViewModels/repositories and separate UI state from activities.
4. Add tests for filtering, cart totals, persistence, checkout validation, and navigation.
5. Define backup exclusions for sensitive local data and enable release shrinking after verification.
