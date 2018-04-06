# Historia

### Descripción

El sistema podrá identificar si un paquete escrito en hexadecimal es de tipo Probe Request. (3 hs)

### Criterios de Aceptación

#### Input 

1) Beacon Frame 1 recuperado de archivo "Paquetes de prueba.txt"

2) Probe Request Frame 1 recuperado de archivo "Paquetes de prueba.txt"

3) Paquete null

#### Output

1) El paquete no es de tipo Probe Request

2) El paquete es de tipo Probe Request

3) EL paquete no es de tipo Probe Request

### Tareas

| Número | Descripción | Horas |
| ------ | ------ | :------: |
| 0 | Leer el byte de frame control del paquete provisto en hexa | 2 |
| 1 | Comparar el byte obtenido con el byte perteneciente al frame control de beacon | 1 |
