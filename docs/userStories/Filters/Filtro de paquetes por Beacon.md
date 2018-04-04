#Historia

### Descripción

El sistema podrá identificar si un paquete escrito en hexadecimal es de tipo Beacon. (8 hs)

### Criterios de Aceptación

#### Input 

1) Paquete 1 - Beacon Frame recuperado de archivo "Paquetes de prueba.txt"

2) Paquete 2 - Probe Request Frame recuperado de archivo "Paquetes de prueba.txt"

3) Paquete null

#### Output

1) True

2) False

3) False

### Tareas

| Número | Descripción | Horas | 
| ------ | ------ | :------: |

| 0 | Leer el byte de frame control del paquete provisto en hexa | 6 |
| 1 | Comparar el byte obtenido con el byte perteneciente al frame control de beacon | 2 |

