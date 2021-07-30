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
    return "Esteban " + frigerio;
  }
}
```

