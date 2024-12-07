# 🧑‍💻 [Usage](docs/usage.md)

To use **Pajama Framework**, follow the guide below:

1. **Create a Game Loop Class**:
    - This class must be annotated with `@GameLoop` and implement `Runnable`.

   Example:
   ```java
   @GameLoop
   public class MyGame implements Runnable {
       public void run() {
           // Game loop logic here
       }
   }
   ```

2. **Inject Dependencies**:
    - Use `@Inject` to automatically inject dependencies into your components.

   Example:
   ```java
   @EngineComponent
   public class Player {
       @Inject
       private PhysicsEngine physicsEngine;
   }
   ```

3. **Start the Engine**:
    - Call the `PajamaApplication.run()` method to initialize the engine and start the game loop.

   Example:
   ```java
   PajamaApplication.run(MyGame.class);
   ```
