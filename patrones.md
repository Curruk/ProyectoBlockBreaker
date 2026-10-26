

## Singleton

Se aplicó el patrón de diseño creacional Singleton para centralizar el estado de la partida en una única instancia inmutable. Este patrón garantiza que toda la arquitectura del juego consuma la misma referencia de memoria, manteniendo la consistencia de los puntos y las vidas independientemente de los eventos que ocurran en el renderizado.

```mermaid
classDiagram
direction LR

class BlockBreakerGame {
    -GameManager gestor
    +create() void
    +render() void
    +crearBloques() void
}

class GameManager {
    <<Singleton>>
    -GameManager instancia
    -int vidas
    -int puntaje
    -int nivel
    -GameManager()
    +getInstancia() GameManager
    +getVidas() int
    +getPuntaje() int
    +getNivel() int
    +perderVida() void
    +sumarPunto() void
    +siguienteNivel() void
    +reiniciarPartida() void
}

BlockBreakerGame --> GameManager : getInstancia()
```

## Strategy

Se requería gestionar un incremento progresivo de dificultad sin contaminar la clase principal con múltiples sentencias condicionales. Para resolverlo se encapsuló el algoritmo de movimiento utilizando el patrón Strategy,. La pelota PingBall no calcula su propia aceleración, sino que delega este cálculo a una interfaz inyectable. Al cambiar de nivel el gestor central inyecta una nueva estrategia matemática concreta, alterando el comportamiento físico de la pelota en tiempo de ejecución de manera limpia.

```mermaid
classDiagram
direction TB

class BallMovement {
    <<interface>>
    +calcularDesplazamiento(float velocidad) float
}

class NormalMovement {
    +calcularDesplazamiento(float velocidad) float
}

class MediumMovement {
    +calcularDesplazamiento(float velocidad) float
}

class FastMovement {
    +calcularDesplazamiento(float velocidad) float
}

class PingBall {
    -float xSpeed
    -float ySpeed
    -BallMovement estrategia
    +setEstrategia(BallMovement estrategia) void
    +update() void
}

class BlockBreakerGame {
    -PingBall ball
    -aplicarVelocidadPorNivel() void
    +render() void
}

BallMovement <|.. NormalMovement
BallMovement <|.. MediumMovement
BallMovement <|.. FastMovement

PingBall --> BallMovement : estrategia actual
BlockBreakerGame --> PingBall : cambia estrategia
```

## Template Method

La creación de los distintos niveles repetía el mismo procedimiento, variando principalmente el tipo de bloque generado. El juego posee EasyLevel, MediumLevel y HardLevel, los cuales comparten la misma estructura de generación pero tienen comportamientos distintos. Como solución se implementó Level como clase abstracta. Su método final crearNivel() define el algoritmo general, mientras crearBloque() es implementado por cada subclase según la dificultad.



```mermaid
classDiagram
direction TB

class Level {
    <<abstract>>
    +crearNivel(int ancho, int alto, LevelConfiguration config) ArrayList~Block~
    #crearBloque(int fila, int x, int y, int ancho, int alto) Block
}

class EasyLevel {
    #crearBloque(int fila, int x, int y, int ancho, int alto) Block
}

class MediumLevel {
    #crearBloque(int fila, int x, int y, int ancho, int alto) Block
}

class HardLevel {
    #crearBloque(int fila, int x, int y, int ancho, int alto) Block
}

class LevelConfiguration {
    -int filas
    -int filasResistentes
    -int anchoBloque
    -int altoBloque
}

class Block {
    <<abstract>>
}

class NormalBlock
class HardBlock

Level <|-- EasyLevel
Level <|-- MediumLevel
Level <|-- HardLevel

Level --> LevelConfiguration : utiliza
Level ..> Block : crea

EasyLevel ..> NormalBlock : crea
MediumLevel ..> NormalBlock : crea
MediumLevel ..> HardBlock : crea
HardLevel ..> HardBlock : crea

Block <|-- NormalBlock
Block <|-- HardBlock
```

## Builder

Los niveles necesitan varios parámetros de configuración, lo que hacía poco clara su construcción directa. Según el nivel se modifican la cantidad de filas, filas resistentes y dimensiones de los bloques. Como solución se creó LevelConfigurationBuilder, que permite configurar estos valores paso a paso y mediante build() genera un LevelConfiguration validado, utilizado posteriormente por Level.L

```mermaid
classDiagram
direction LR

class BlockBreakerGame {
    +crearBloques() void
}

class LevelConfigurationBuilder {
    <<Builder>>
    -int filas
    -int filasResistentes
    -int anchoBloque
    -int altoBloque
    +filas(int filas) LevelConfigurationBuilder
    +filasResistentes(int filas) LevelConfigurationBuilder
    +anchoBloque(int anchoBloque) LevelConfigurationBuilder
    +altoBloque(int altoBloque) LevelConfigurationBuilder
    +build() LevelConfiguration
}

class LevelConfiguration {
    -int filas
    -int filasResistentes
    -int anchoBloque
    -int altoBloque
    +getFilas() int
    +getFilasResistentes() int
    +getAnchoBloque() int
    +getAltoBloque() int
}

class Level {
    <<abstract>>
    +crearNivel(int ancho, int alto, LevelConfiguration config) ArrayList~Block~
}

BlockBreakerGame ..> LevelConfigurationBuilder : configura
LevelConfigurationBuilder ..> LevelConfiguration : build
BlockBreakerGame ..> LevelConfiguration : obtiene
BlockBreakerGame ..> Level : entrega configuracion
Level --> LevelConfiguration : utiliza
```
