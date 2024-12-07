# 🏗️ [Architecture]

The **Pajama Framework** uses several key components:

1. **EngineContext**:
    - Manages the components and their dependencies.
    - Scans for classes annotated with `@EngineComponent` or `@GameLoop`.

2. **Annotations**:
    - `@GameLoop`: Marks the main class that will run the game loop.
    - `@EngineComponent`: Marks classes that should be treated as components to be injected.
    - `@Inject`: Injects dependencies automatically.
