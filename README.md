# Proyecto_Estructuras
Grupo 5
Integrantes:
Kendal Lopez Corrales
Steven Mora Ortiz
Santiago Barrantes Salazar
Matías Barrantes Benavides

Módulo 1.0: Configuración 	Estructuras de Datos 
Este módulo cumple con la finalidad de configurar el programa. 	1.	Utilizará como medio de almacenamiento persistente un archivo config.json. Este mecanismo será su base de datos. 
2.	Este módulo solo se ejecuta la primera vez que el programa entra en ejecución posterior a esto el programa carga la configuración dada por el usuario almacenada en el config.json. Si este archivo se borra, deberá volver a ingresar la información. 
3.	Este módulo permite ingresar el nombre de la terminal, así como la cantidad de buses para la atención de clientes.  
    Entre los buses siempre debe contar con un único bus preferencial, la cual atiende a personas con condiciones preferenciales como discapacidades, embarazo, empresariales, adultos mayores. 
    De igual forma, entre los buses siempre debe contar con un único bus directo para viajes rápidos no preferenciales (una sola parada)  
4.	Los demás buses son normales aplican para dos o más paradas no preferenciales. 
5.	El número total de buses puede cambiar por cada ejecución no debe ser configurado de forma fija dentro del código. Debe poder ser dinámico sin afectar la funcionalidad del programa (lista de colas). 
6.	Lo anterior debe verse reflejado (serializado) en el archivo config.json . 
7.	Los usuarios y contraseñas de los usuarios serán almacenados en este archivo. No deben quedar quemados en el código (ideal un usuario por miembro del grupo).  

Módulo 1.2: Atención de tiquetes
Estructuras de Datos

Este módulo cumple con la atención de tiquetes

La atención de tiquetes se divide en dos funciones:

La primera es al momento de crear un tiquete si se asigna a una cola vacía, inmediatamente este pasará a ser atendido por el inspector del bus, siempre y cuando este esté desocupado (no se encuentra atendiendo un tiquete).
La segunda función es cuando la fila se encuentra llena, en este escenario, el programa debe contar con una funcionalidad que se “active”, con la cual el tiquete es atendido por el inspector. Una vez atendido debe poderse marcar el tiquete como “Atendido” y dejar libre al inspector para la siguiente persona.

Cada vez que un tiquete pasa a ser atendido debe cambiar la hora de atención por la del sistema y además debe mandarlo a guardar en la base de datos, en este caso un archivo llamado atendidos.json

Se debe guardar en base de datos en cual bus se subió y de cual terminal compró el tiquete.

El sistema debe poder mostrar por pantalla esta información con una opción de menú.

Cada bus tiene un inspector y función permitir la entrada al bus. Si cambia a estado “Atendido” se provoca que subió al bus.

Los tipos de servicio de los tiquetes son al menos:

VIP: paga 100$ adicionales, con derecho a comer y beber.
Regular: cobra normal de 20$.
Carga: paga 20$ más 10$ por cada lb de carga.
Ejecutivo: paga 100$ adicionales, con derecho a manejar el bus.

Los servicios se cobrarán al subir al bus por el inspector, se debe preguntar al usuario, si no paga apagar es sacado de la fila de ese bus y tiene que iniciar el proceso de atención de nuevo.

El cálculo requiere el consumo en línea del web service del Banco Central.

Módulo 1.3: Llenado de las colas
Estructuras de Datos

Este módulo contempla las técnicas de llenado de las colas de los buses

Los buses se llenarán conforme las personas soliciten tiquetes, además, debe considerar el tamaño actual de la cola para el bus, es decir:

Bus 1 tiene 5 personas en fila
Bus 5 tiene 3 personas en fila
Bus 7 tiene 4 personas en fila

En caso de un tiquete normal nuevo, se asignará a la fila del bus 5 porque tiene menos personas en espera a subir, en caso de empate, se puede asignar a cualquiera.
Cabe resaltar que para los tiquetes de Bus Directo o Bus Preferencial serán asignados conforme se crean ya que estos tiquetes no se atienden por otros buses (solo hay uno directo y uno preferencial).

Lo anterior debe verse reflejado (realizado) en el archivo colas.json, y cargarse cuando inicia el sistema si existen registros pendientes de atender.

Clase Auth

Gestiona la autenticación de usuarios.
Guarda un arreglo de usuarios (Usuario[]).
Permite verificar si un usuario y contraseña son válidos con login().
Controla el proceso de inicio de sesión con validarLogin(), que limita los intentos a 3 y muestra mensajes con JOptionPane.
Retorna true si el login es exitoso y false si no.
No permite usuarios nulos.
Está enfocada en interfaces gráficas para entrada y mensajes.

Clase Bus

Representa un bus con un identificador y tipo (P, D, o N).
Mantiene una cola (Cola) para manejar tickets o pasajeros.
Tiene un estado booleano para saber si el inspector está ocupado.
Ofrece métodos para obtener su id, tipo, cola y estado del inspector.
Sirve para administrar el flujo de pasajeros y estado interno del bus.

Clase Cola

Implementa una cola simple con nodos (Nodo) enlazados.
Permite encolar (agregar) datos al final y desencolar (quitar) del frente.
Verifica si la cola está vacía.
Permite consultar el dato en el frente sin removerlo.
Incluye un método para mostrar la cola por consola.
Lanza excepción si se intenta desencolar o consultar el frente de una cola vacía.

Clase ListaBuses

Lista enlazada simple que almacena objetos Bus.
Permite agregar un bus al final con agregarBus().
Lleva un contador de la cantidad de buses en la lista.
Ofrece acceso a la cantidad con getCantidad().
Se usa para gestionar y mantener ordenados los buses disponibles.

Clase ListaUsuarios

Lista enlazada simple que almacena objetos Usuario.
Permite agregar usuarios con agregarUsuario().
Puede validar si un usuario y contraseña existen con validarLogin().
Proporciona acceso al nodo cabeza para recorrer la lista.
Usada para controlar y validar accesos de usuarios en el sistema.

Clase Nodo

Elemento básico de las listas enlazadas.
Guarda un dato genérico (Object) y una referencia al siguiente nodo.
Permite obtener y modificar el siguiente nodo y acceder al dato almacenado.
Sirve como estructura fundamental para las listas de usuarios y buses.

Clase Proyecto_BusNova

Clase principal con método main que inicia el sistema.
Configura la terminal, cantidad de buses y usuarios si no hay configuración previa.
Muestra un menú para agregar buses, cambiar el nombre de la terminal o salir.
Interactúa con el usuario mediante ventanas JOptionPane.
Controla la lógica de ejecución y flujo principal del programa.

Clase Sistema

Clase central que maneja la terminal: nombre, listas de buses y usuarios.
Permite crear buses, agregar usuarios, cambiar nombre y guardar/cargar configuración en archivo.
Almacena la configuración básica en config.json.
Ofrece métodos para manipular y acceder a las listas de buses y usuarios.
Sirve como puente entre la lógica del negocio y la interfaz de usuario.

Clase Usuario

Representa un usuario con su nombre de usuario y contraseña.
Guarda el username y password como cadenas de texto privadas.
Proporciona métodos para obtener el nombre de usuario y la contraseña (getUsername(), getPassword()).
Se utiliza para autenticar y manejar usuarios en el sistema.
Es un objeto simple que encapsula los datos de acceso sin lógica adicional.

--

# Guía de Uso – BusNova

## Descripción
BusNova es un sistema en Java con interfaz `JOptionPane` para administrar una terminal de buses.  
Permite configurar la terminal, administrar buses y usuarios, gestionar tiquetes y controlar colas con persistencia en archivos JSON.

---

## Archivos Utilizados
- `config.json`: Configuración general del sistema  
- `tiquetes.json`: Tiquetes registrados  
- `colas.json`: Colas pendientes de cada bus  
- `atendidos.json`: Historial de tiquetes atendidos  

---

## Inicio del Sistema

### Si existe `config.json`
El sistema carga automáticamente:
- Configuración
- Tiquetes
- Colas

### Si no existe `config.json`
Solicita configuración inicial:
1. Nombre de la terminal  
2. Cantidad de buses (mínimo 2)  
3. Registro de usuarios  

**Reglas de creación de buses:**
- Bus 1 = Preferencial  
- Bus 2 = Directo  
- Restantes = Normales  

Si no se registra ningún usuario válido:
- Se crea automáticamente `admin / admin`

---

## Login
El sistema solicita:
- Usuario  
- Contraseña  

**Reglas:**
- Máximo 3 intentos  
- Si falla o se cancela, el sistema finaliza  

---

## Menú Principal

1. Configuración del sistema  
2. Administrar buses  
3. Administrar usuarios  
4. Gestión de tiquetes  
5. Atención de tiquetes  
6. Consulta de colas  
7. Rutas y grafo  
8. Consulta BCCR  
9. Reportes  
10. Salir  

---

## Configuración del Sistema
Permite:
- Ver configuración actual  
- Cambiar nombre de terminal  
- Guardar configuración  

---

## Administrar Buses
Permite:
- Ver buses registrados  
- Agregar buses normales  
- Ver cantidad total de buses  

---

## Administrar Usuarios
Permite:
- Ver usuarios registrados  
- Agregar usuarios nuevos  

**Validación:**  
No permite usernames duplicados.

---

## Gestión de Tiquetes

### Crear Tiquete
Solicita:
- ID  
- Tipo de servicio (`VIP`, `REGULAR`, `CARGA`, `EJECUTIVO`)  

Si es `CARGA`:
- Solicita peso de carga  

### Asignación Automática
- `VIP` → Bus Preferencial  
- `CARGA` → Bus Directo  
- `REGULAR / EJECUTIVO` → Bus Normal con menor cola  

---

## Atención de Tiquetes

### Abordar Tiquete
Solicita:
- ID del bus  

**Proceso:**
1. Toma el primer tiquete de la cola  
2. Calcula precio según servicio  
3. Solicita confirmación de pago  

### Si paga
- Cambia estado a `ATENDIDO`  
- Guarda en `atendidos.json`  

### Si no paga
- Cambia estado a `RECHAZADO`  
- Se elimina de la cola  

---

## Consulta de Colas
Muestra:
- Todos los buses  
- Tipo de bus  
- IDs de tiquetes en espera  

---

## Reportes
Muestra la lista completa de tiquetes registrados con:
- ID  
- Bus asignado  
- Tipo  
- Estado  
- Hora de creación  
- Hora de atención  

---

## Opciones en Desarrollo
Actualmente no implementadas:
- Rutas y grafo  
- Consulta BCCR  

---

## Salida del Sistema
Al salir se guardan automáticamente:
- Configuración  
- Tiquetes  
- Colas  

---

## Flujo Recomendado de Uso
1. Configurar sistema  
2. Iniciar sesión  
3. Administrar buses/usuarios si es necesario  
4. Crear tiquetes  
5. Consultar colas  
6. Atender tiquetes  
7. Revisar reportes  
8. Salir para guardar cambios  
