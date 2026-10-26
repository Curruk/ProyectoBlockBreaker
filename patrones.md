

## Singleton

Problema: Necesitábamos controlar las vidas, el puntaje y el nivel del jugador sin crear varios administradores con información diferente.
Contexto: Durante la partida, BlockBreakerGame consulta y actualiza estos datos cuando el jugador pierde una vida, destruye bloques o avanza de nivel.
Solución: Implementamos Singleton mediante GameManager. Su constructor es privado y getInstancia() permite acceder a una única instancia que administra el estado de la partida.Clases involucradas: GameManager y BlockBreakerGame.


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

Problema: Queríamos permitir distintos movimientos de la pelota sin tener que cambiar su lógica cada vez que modificáramos la velocidad.
Contexto: En BlockBreaker, el jugador puede seleccionar el movimiento normal con la tecla 1 o el rápido con la tecla 2.
Solución: Creamos la interfaz BallMovement y dos implementaciones: NormalMovement y FastMovement. PingBall utiliza una estrategia para calcular su desplazamiento y puede cambiarla mediante setEstrategia().Clases involucradas: BallMovement, NormalMovement, FastMovement, PingBall y BlockBreakerGame.

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

Problema: Necesitábamos generar niveles con diferentes dificultades sin repetir el algoritmo de creación de bloques.
Contexto: El juego tiene niveles fáciles y difíciles. Ambos utilizan el mismo procedimiento para distribuir los bloques, pero pueden personalizar sus características.
Solución: Creamos la clase abstracta Level, que define el procedimiento mediante crearNivel(). Las clases EasyLevel y HardLevel heredan de ella y personalizan la creación de bloques normales, incluyendo sus colores.Clases involucradas: Level, EasyLevel, HardLevel, Block, NormalBlock y HardBlock.



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

Problema: Necesitábamos configurar diferentes niveles sin escribir directamente todos sus parámetros cada vez que los creábamos.
Contexto: Cada nivel de BlockBreaker puede tener distinta cantidad de filas, filas resistentes y dimensiones de los bloques.
Solución: Implementamos ConfiguracionNivelBuilder, que permite definir estas características paso a paso. El método build() valida los valores y construye un objeto ConfiguracionNivel, que después utiliza Level para generar los bloques.Clases involucradas: ConfiguracionNivelBuilder, ConfiguracionNivel, Level y BlockBreakerGame.

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
