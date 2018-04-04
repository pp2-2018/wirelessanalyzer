#Historia

### Descripción

El sistema podrá identificar si un paquete escrito en hexadecimal fue escrito en un determinado intervalo de tiempo provisto. (6hs)

### Criterios de Aceptación

#### Input 

1) Paquete 1 - Beacon Frame recuperado de archivo "Paquetes de prueba.txt"

2) Paquete 2 - Probe Request Frame recuperado de archivo "Paquetes de prueba.txt"

3) Paquete null

#### Output



### Tareas

| Número | Descripción | Horas | 
| ------ | ------ | :------: |

| 0 | Identificar el Global Header\* del paquete | 2 |
| 1 | Parsear el Global Header y extraer el timestamp de cuando el paquete fue recibido | 4 |

