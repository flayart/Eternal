# Eternal

```java
public final class Esteban extends JavaPlugin {
  private String frigerio;
  
  @Override
  public void onEnable() {
    frigerio = "Frigerio";
    
    System.out.println(get());
  }
  
  public String get() {
    return getClass().getName() + frigerio;
  }
}
```

![download](https://user-images.githubusercontent.com/60828349/127686057-da22a2b4-b40a-4fc1-b263-11f446ed79d4.jpg)


