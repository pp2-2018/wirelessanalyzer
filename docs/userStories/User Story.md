# Historia 01

### Descripción

Con una interfaz de internet wireless configurada en modo monitor, capturar todos los paquetes en formato pcap que sean detectados por la antena (Llámese 
antena a la interfaz wireless configurada en modo monitor)

### Criterios de Aceptación
#### 1. La interfaz wireless capturará los siguientes paquetes: 

Paquete 1:

| Direccion | Datos Hexadecimal | Datos Ascii | 
| ------ | ------ | ------ |
|0000  | f4 e3 fb 6d 4c 2b f8 34 41 ba da f1 08 00 45 00 |  ...mL+.4A.....E.|
|0010  | 00 34 8d 4d 40 00 40 06 f1 91 c0 a8 01 24 c0 10 | .4.M@.@......$..|
|0020  | 3a 08 ad 5a 00 50 c1 6a f7 88 2d 86 20 08 80 11 | :..Z.P.j..-. ...|
|0030  | 01 0a 3d 09 00 00 01 01 08 0a fb 97 88 ba b2 f5 | ..=.............|
|0040  |  91 4f                                          | .O              |

Paquete 2:

| Direccion | Datos Hexadecimal | Datos Ascii | 
| ------ | ------ | ------ |
|0000  | f8 34 41 ba da f1 f4 e3 fb 6d 4c 2b 08 00 45 00  |.4A......mL+..E.|
|0010  | 00 34 13 77 40 00 3a 06 71 68 c0 10 3a 08 c0 a8  |.4.w@.:.qh..:...|
|0020  | 01 24 00 50 ad 5a 2d 86 20 08 c1 6a f7 89 80 11  |.$.P.Z-. ..j....|
|0030  | 01 22 3b 26 00 00 01 01 08 0a b2 f5 93 19 fb 97  |.";&............|
|0040  | 88 ba                                            |..              |

Paquete 3:

| Direccion | Datos Hexadecimal | Datos Ascii | 
| ------ | ------ | ------ |
|0000 |  f4 e3 fb 6d 4c 2b f8 34 41 ba da f1 08 00 45 00 | ...mL+.4A.....E.|
|0010 |  00 34 8d 4e 40 00 40 06 f1 90 c0 a8 01 24 c0 10 | .4.N@.@......$..|
|0020 |  3a 08 ad 5a 00 50 c1 6a f7 89 2d 86 20 09 80 10 | :..Z.P.j..-. ...|
|0030 |  01 0a 3a df 00 00 01 01 08 0a fb 97 89 19 b2 f5 | ..:.............|
|0040 |  93 19                                           | ..              |

y almacenará:

- Paquete TCP FIN,ACK proveniente de 192.168.1.36 con destino 192.16.58.8
- Paquete TCP FIN,ACK proveniente de 192.16.58.8 con destino 192.168.1.36
- Paquete TCP ACK proveniente de 192.168.1.36 con destino 192.16.58.8

### Tareas

| Número | Descripción | Horas | 
| ------ | ------ | :------: |
| 0 | Configurar la antena wi-fi en modo monitor | 2 |
| 1 | Capturar los paquetes "raw" provenientes de la antena| 4 |
| 2 | Parsear los paquetes raw a formato pcap | 2 |
| 3 | Escribir los paquetes en un OutputStream | 1 |

Aclaraciones: Dispositivos cercanos no es medible.
C.A -> debe ser completo y riguroso (Ver todos los comportamientos posibles. RESOLVER EN SERIO UNA USER STORY). Quiere decir, para el alcance que se acurra
xlo más amplio posible.

Mediator 

