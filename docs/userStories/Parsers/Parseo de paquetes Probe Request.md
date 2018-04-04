# Historia 05

### Descripción

El sistema podrá parsear informacion “raw” en paquetes de tipo Probe Request. (12hs)

### Criterios de Aceptación

#### Input 

1) Paquete 2 - Probe Request Frame extraído del archivo "Paquetes de prueba.txt"

2) Null

#### Output

1) 

2) Lanza excepción

### Tareas

| Número | Descripción | Horas | 
| ------ | ------ | :------: |

| 0 | Identificar las posiciones de los bloques de información de la cadena hexadecimal | 8 |
| 1 | Extraer MacAddress de destino y timestamp  del paquete | 2 |
| 2 | Convertir a información obtenida de Hexa a UTF-8 | 1 |
| 3 | Crear un objeto en memoria que contenga la información recuperada | 1 | 
 
