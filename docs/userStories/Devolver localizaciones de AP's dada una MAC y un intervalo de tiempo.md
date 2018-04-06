# Historia (9hs)

### Descripción

Dada una MAC address y un intervalo de tiempo, devolver una lista de geolocalizaciones apróximadas en donde estuvo esa MAC Address  y el tiempo exacto que se identificó

### Criterios de Aceptación

Aclaración: todos los nombres de las MAC Addresses son ficticios. Encontrará sus respectivas referencias en el documento "Capturas de Prueba.txt"

### Input

1) [addra]

2) [addra]

3) [addrc]

4) [addrd]

### Output

### Tareas

| Número | Descripción | Horas |
| ------ | ------ | :------: |
| 0 | Asociar la MAC Address especificada con la localización de los Access Points por la cual fue registrada en el período de tiempo indicado | 1 |
| 1 | Identificar la MAC Address fue registrada por dos Access Points al mismo tiempo* | 2 |
| 2 | Si el punto anterior se cumple, calcular la intersección del radio de los Access Point | 4 |
| 3 | Ubicar el punto geográfico aproximado entre las intersecciones de los Access Points | 2 |

* Se deberá definir una constante que indique al programa cuanto tiempo puede pasar entre recepcion de paquete de los Access Point para considerar que una MAC Address fue identificada al mismo tiempo. Esta aclaración tiene sentido al asumir que no todos los paquetes llegan en el mismo instante a Access Points distintos.
