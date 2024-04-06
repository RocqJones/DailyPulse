# DailyPulse - Android & iOS App
Demonstrates KMP 101

## Clean Architecture
- Framework/ UI (Native iOS & Android)
- Presentation Layer
- Data Layer (Shared Module)
- Domain/App Layer (Shared Module)

![image](https://github.com/RocqJones/DailyPulse/assets/32324500/058bd240-db17-4a58-90d8-41ab24b77f2a)

### MVI - Architecture
![image](https://github.com/RocqJones/DailyPulse/assets/32324500/42a521be-b442-4aa2-ba9b-51ce7b8cab9b)

### Coroutines & Structured Concurrency recap.
- `suspend` keyword in the Kt function makes the IO (Input/Output) thread suspended until we get results.
- suspend functions can only be called from a Coroutine.
- For a coroutine to exist we must have a **scope** and when its scope is canceled it cancels every work within the coroutine. It's good for memory management and avoiding memory leaks.
![image](https://github.com/RocqJones/DailyPulse/assets/32324500/d7d96968-d0c4-4532-bb8e-ffc9a61f9031)
