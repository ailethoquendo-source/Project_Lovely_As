# <h1 align="center"> Project_Lovely_As </h1>
**Proyecto Final – Programación II**

## Integrantes
- **Alejandra Pinto Roqueme**
- **Aileth Oquendo Cantero**
- **Ana Cristina Sánchez Ávila**

## Link del Prototipo
[Prototipo en Figma](https://www.figma.com/design/gTPbvrr1wctdo8fazZZjs7/Prototype_Lovely_A-s?node-id=0-1&t=kpntvCPUVTR7e64j-1)

---

## Manual de Usuario

### 2.1. Inicio de Sesión

Para acceder a la aplicación, debe iniciar sesión con sus credenciales:

1. Abra la aplicación y se mostrará la pantalla de inicio de sesión.
2. En el campo **Usuario**, ingrese su correo electrónico registrado.
3. En el campo **Contraseña**, ingrese su contraseña.
4. Haga clic en el botón **Iniciar Sesión**.

**Validaciones:**
- Si ambos campos están vacíos, se mostrará un mensaje: "Por favor ingrese su usuario y contraseña."
- Si solo falta el usuario, se mostrará: "Por favor ingrese su usuario."
- Si solo falta la contraseña, se mostrará: "Por favor ingrese su contraseña."
- Si las credenciales son incorrectas, se mostrará un mensaje de error.
- Si el inicio de sesión es exitoso, se mostrará un mensaje: "Inicio de sesión exitoso. ¡Bienvenido!" y será redirigido a la pantalla principal.

**Imagen de referencia:**

<img width="1000" height="500" alt="image" src="https://github.com/user-attachments/assets/1b581025-7a9a-4a41-8589-5b031c968c29" />

---

### 2.2. Registro de Usuario

Si no tiene una cuenta, puede registrarse siguiendo estos pasos:

1. En la pantalla de inicio de sesión, haga clic en el botón de **Registrarse** o en el icono de configuración.
2. Complete todos los campos requeridos:
   - **Nombre completo**
   - **Identificación** (número de identificación)
   - **Correo electrónico**
   - **Contraseña**
3. Seleccione el tipo de usuario (Cliente o Administrador).
4. Haga clic en el botón **Registrarse**.

**Nota:** El correo electrónico debe ser único. Si ya existe un usuario con ese correo, se mostrará un mensaje de advertencia.

**Imagen de referencia:**

![Imagen de WhatsApp 2025-11-30 a las 18 12 51_cfc11bf8](https://github.com/user-attachments/assets/a60cb36c-d4fe-442a-9fc8-77fd5773a73f)

---

### 2.3. Navegación Principal

Una vez iniciada la sesión, accederá a la pantalla principal que contiene:

**Header (Barra superior):**
- **Icono de Carrito**: Accede al carrito de compras
- **Icono de Favoritos**: Accede a la lista de productos favoritos
- **Icono de Historial**: Accede al historial de compras (solo visible para administradores)
- **Icono de Cuenta**: Abre el menú de cuenta con opciones de cambio de contraseña y cerrar sesión
- **Icono de WhatsApp**: Abre el overlay para contactar por WhatsApp
- **Correo del usuario**: Muestra el correo electrónico del usuario actual

**Área de contenido:**
- Catálogo de productos en formato de tarjetas
- Scroll vertical para navegar por todos los productos disponibles

**Imagen de referencia:**

![Imagen de WhatsApp 2025-11-30 a las 18 19 37_b110ba9e](https://github.com/user-attachments/assets/13e3685a-128a-4714-bbc8-1901408c2f39)

---

### 2.4. Visualización de Productos

El catálogo de productos se muestra en la pantalla principal:

1. Cada producto se presenta en una tarjeta que muestra:
   - Imagen del producto
   - Marca del producto
   - Nombre del producto
   - Precio
   - Selector de tallas (ComboBox)
   - Botón "Agregar al Carrito"
   - Botón "Agregar a Favoritos"

2. **Tooltip informativo**: Al pasar el mouse sobre la imagen del producto, aparecerá un tooltip con el texto "Haz doble click para ver más información" indicando que puede hacer doble clic para ver los detalles completos del producto.

3. Use el scroll vertical para navegar por todos los productos disponibles.

**Imagen de referencia:**

<img width="1000" height="500" alt="image" src="https://github.com/user-attachments/assets/9971bc22-a6b4-4b62-a7d0-6d9e0541a3b7" />

---

### 2.5. Ver Detalles de Producto

Para ver información detallada de un producto:

1. **Haga doble clic** en la imagen del producto en la tarjeta del catálogo.
2. Se abrirá un panel de detalles que muestra:
   - **Imagen principal** del producto
   - **Miniaturas** de imágenes adicionales (si las hay) - puede hacer clic para cambiar la imagen principal
   - **Marca** del producto
   - **Nombre** del producto
   - **Precio** del producto
   - **Descripción** completa del producto
   - **Selector de tallas** (ComboBox)
   - **Botón "Agregar al Carrito"**
   - **Botón "Agregar a Favoritos"**
   - **Botón de retroceso** (flecha hacia atrás) para volver al catálogo

3. Para volver al catálogo, haga clic en el botón de retroceso o en cualquier botón del header (Carrito, Favoritos, etc.).

**Imagen de referencia:**

<img width="1000" height="500" alt="image" src="https://github.com/user-attachments/assets/bcf712e5-41c2-40b7-8634-ebc8561d6aac" />

---

### 2.6. Agregar al Carrito

Puede agregar productos al carrito de dos formas:

**Desde la tarjeta de producto:**
1. Seleccione una talla del ComboBox de tallas.
2. Haga clic en el botón **"Agregar al Carrito"**.
3. Si la talla no está seleccionada, se mostrará un mensaje de advertencia.
4. Si el producto se agrega exitosamente, se mostrará un mensaje de confirmación.

**Desde el panel de detalles:**
1. Abra el panel de detalles del producto (doble clic en la imagen).
2. Seleccione una talla del ComboBox de tallas.
3. Haga clic en el botón **"Agregar al Carrito"**.
4. El producto se agregará al carrito con la talla seleccionada.

**Nota:** Si el producto ya está en favoritos, se moverá automáticamente del panel de favoritos al carrito.

**Imagen de referencia:**

1: 
<img width="212" height="185" alt="image" src="https://github.com/user-attachments/assets/3cbdbb4b-7afb-4492-96bd-b3b8fb0f07c7" />

2:
<img width="414" height="282" alt="image" src="https://github.com/user-attachments/assets/3b77fa9c-fdcf-49be-a2c7-35c27e22f69a" />

3: 

---

### 2.7. Agregar a Favoritos

Puede agregar productos a favoritos de dos formas:

**Desde la tarjeta de producto:**
1. Seleccione una talla del ComboBox de tallas.
2. Haga clic en el botón **"Agregar a Favoritos"**.
3. Si la talla no está seleccionada, se mostrará un mensaje de advertencia.
4. Si el producto se agrega exitosamente, se mostrará un mensaje de confirmación.

**Desde el panel de detalles:**
1. Abra el panel de detalles del producto (doble clic en la imagen).
2. Seleccione una talla del ComboBox de tallas.
3. Haga clic en el botón **"Agregar a Favoritos"**.
4. El producto se agregará a favoritos con la talla seleccionada.

**Nota:** Si el producto ya está en el carrito, se moverá automáticamente del carrito a favoritos.

**Imagen de referencia:**

[Espacio reservado para imagen del proceso de agregar a favoritos]

---

### 2.8. Ver Carrito de Compras

Para acceder al carrito de compras:

1. Haga clic en el **icono de Carrito** en el header.
2. Se abrirá un panel lateral que muestra:
   - Lista de todos los productos en el carrito
   - Para cada producto se muestra:
     - Imagen del producto
     - Marca
     - Nombre
     - **Talla seleccionada** (ej: "TALLA: M")
     - Precio individual
     - Botón para eliminar del carrito
     - Botón para mover a favoritos
   - **Total a pagar** (suma de todos los productos)
   - **Botón "Pagar Todo"** para realizar la compra

3. Para cerrar el panel, haga clic nuevamente en el icono de carrito o en cualquier otra sección.

**Imagen de referencia:**

<img width="643" height="591" alt="image" src="https://github.com/user-attachments/assets/780787a3-367e-4505-9e37-3df62dec0014" />

---

### 2.9. Ver Favoritos

Para acceder a la lista de favoritos:

1. Haga clic en el **icono de Favoritos** en el header.
2. Se abrirá un panel lateral que muestra:
   - Lista de todos los productos en favoritos
   - Para cada producto se muestra:
     - Imagen del producto
     - Marca
     - Nombre
     - **Talla seleccionada** (ej: "TALLA: S")
     - Precio individual
     - Botón para eliminar de favoritos
     - Botón para mover al carrito

3. Para cerrar el panel, haga clic nuevamente en el icono de favoritos o en cualquier otra sección.

**Nota:** Solo puede estar abierto un panel a la vez (carrito o favoritos, no ambos simultáneamente).

**Imagen de referencia:**

<img width="650" height="600" alt="image" src="https://github.com/user-attachments/assets/41e94af8-3b96-493f-80f0-0f1bb179f281" />

---

### 2.10. Realizar Compra

Para realizar una compra:

1. Acceda al carrito de compras (ver sección 2.8).
2. Verifique que todos los productos y tallas sean correctos.
3. Revise el **total a pagar** mostrado en la parte inferior del panel.
4. Haga clic en el botón **"Pagar Todo"**.
5. Se procesará la compra y:
   - Los productos se moverán del carrito al historial de compras
   - Se guardará la fecha y hora de la compra
   - Se mostrará un mensaje de confirmación
   - El carrito quedará vacío

**Nota:** Solo los administradores pueden ver el historial completo de todas las compras. Los clientes pueden ver sus propias compras.

**Imagen de referencia:**

<img width="651" height="600" alt="image" src="https://github.com/user-attachments/assets/c2475e53-6f74-43d9-a031-d19f234b7ef4" />

---

### 2.11. Cambiar Contraseña

Para cambiar su contraseña:

1. Haga clic en el **icono de Cuenta** en el header.
2. Se abrirá un menú desplegable.
3. Haga clic en **"Cambiar Contraseña"**.
4. Se abrirá un panel en el centro de la pantalla con un formulario.
5. Complete los campos:
   - **Contraseña actual**
   - **Nueva contraseña**
   - **Confirmar nueva contraseña**
6. Haga clic en el botón **"Cambiar Contraseña"**.
7. Si las validaciones son correctas, se actualizará la contraseña y se mostrará un mensaje de confirmación.

**Imagen de referencia:**

<img width="465" height="464" alt="image" src="https://github.com/user-attachments/assets/06a7aa32-ada9-4ad8-9177-685d45b92355" />

---

### 2.12. Contactar por WhatsApp

Para contactar por WhatsApp:

1. Haga clic en el **icono de WhatsApp** en el header (tanto en la pantalla principal como en la de login).
2. Se abrirá un overlay en el centro de la pantalla con opciones de contacto.
3. Se mostrarán tres números de contacto disponibles, cada uno con:
   - Icono de WhatsApp
   - Número de teléfono
4. Haga clic en el número de contacto deseado.
5. Se abrirá WhatsApp Web con una conversación iniciada con ese número.
6. Para cerrar el overlay sin contactar, haga clic en el botón **"Cancelar"**.

**Imagen de referencia:**

<img width="378" height="333" alt="image" src="https://github.com/user-attachments/assets/77b89128-a6ef-48d4-aad3-3bf6951ad1ef" />

---

### 2.13. Ver Historial de Compras (Solo Administrador)

Esta funcionalidad está disponible únicamente para usuarios administradores:

1. Haga clic en el **icono de Historial** en el header (solo visible para administradores).
2. Se abrirá un panel en el centro de la pantalla con una tabla.
3. La tabla muestra todas las compras realizadas por todos los usuarios con las siguientes columnas:
   - **ID PRODUCTO**: Identificador único del producto
   - **MARCA**: Marca del producto
   - **NOMBRE**: Nombre del producto
   - **PRECIO**: Precio del producto
   - **FECHA**: Fecha y hora de la compra (formato: dd/MM/yyyy HH:mm)
   - **CORREO USUARIO**: Correo electrónico del usuario que realizó la compra

4. Use el scroll para navegar por todas las compras registradas.
5. Para cerrar el panel, haga clic fuera del panel o en cualquier botón del header.

**Imagen de referencia:**

<img width="909" height="605" alt="image" src="https://github.com/user-attachments/assets/f4c1e750-31ee-458f-8ba6-25df64e7c465" />

---

### 2.14. Cerrar Sesión

Para cerrar sesión:

1. Haga clic en el **icono de Cuenta** en el header.
2. Se abrirá un menú desplegable.
3. Haga clic en **"Cerrar Sesión"**.
4. Será redirigido a la pantalla de inicio de sesión.

**Confirmación al cerrar ventana:**
- Si intenta cerrar la ventana principal haciendo clic en la "X", se mostrará un diálogo de confirmación preguntando: "¿Desea cerrar sesión?"
- Si confirma, se cerrará la sesión y será redirigido al login.
- Si cancela, permanecerá en la aplicación.

**Imagen de referencia:**

<img width="712" height="275" alt="image" src="https://github.com/user-attachments/assets/3a5653c5-3346-4cb9-9561-38a1bebcfbd6" />

---

## Manual de Desarrollador

### 3.1. Arquitectura del Proyecto

El proyecto sigue una arquitectura **MVC (Modelo-Vista-Controlador)** con la siguiente estructura:

```
src/
├── Main/                    # Punto de entrada de la aplicación
├── Models/                  # Modelos de datos (Product, User, Admin, Client)
│   └── Nodes/              # Nodos para estructuras de datos
├── DataStructures/         # Estructuras de datos (Listas, Pilas)
├── Controllers/            # Controladores principales
│   └── ComponentControllers/  # Controladores de componentes
├── Views/                  # Archivos FXML (interfaz gráfica)
│   └── Components/        # Componentes reutilizables
├── StyleSheets/           # Archivos CSS para estilos
├── Files/                 # Archivos de persistencia de datos
└── Images/                # Recursos de imágenes
```

**Separación de responsabilidades:**
- **Models**: Representan las entidades del sistema (Product, User, etc.)
- **DataStructures**: Gestionan las estructuras de datos y la persistencia
- **Controllers**: Manejan la lógica de negocio y eventos de la interfaz
- **Views**: Definen la estructura visual de la interfaz (FXML)
- **StyleSheets**: Contienen los estilos CSS para personalizar la apariencia

---

### 3.2. Modelos (Models)

#### 3.2.1. Clase Product

**Ubicación:** `src/Models/Product.java`

**Propósito:** Representa un producto del catálogo con toda su información.

**Atributos principales:**
- `idProduct` (int): Identificador único del producto
- `name` (String): Nombre del producto
- `brand` (String): Marca del producto
- `sizes[]` (String[]): Array de tallas disponibles
- `price` (float): Precio del producto
- `description` (String): Descripción detallada del producto
- `email_customer` (String): Correo del cliente que tiene el producto (para carrito/favoritos/historial)
- `date_purchase` (LocalDateTime): Fecha y hora de compra (para historial)
- `selectedSize` (String): Talla seleccionada por el usuario

**Métodos principales:**
- Getters y setters estándar para todos los atributos
- Constructor con parámetros: `Product(int idProduct, String name, String brand, String[] sizes, float price, String description)`

---

#### 3.2.2. Clase User

**Ubicación:** `src/Models/User.java`

**Propósito:** Clase base abstracta para todos los usuarios del sistema.

**Atributos:**
- `identification` (int): Número de identificación
- `name` (String): Nombre completo
- `email` (String): Correo electrónico (usado como usuario)
- `password` (String): Contraseña

**Métodos principales:**
- Getters y setters para todos los atributos
- Constructor: `User(int identification, String name, String email, String password)`

---

#### 3.2.3. Clase Admin

**Ubicación:** `src/Models/Admin.java`

**Propósito:** Representa un usuario administrador del sistema. Extiende la clase `User`.

**Funcionalidades especiales:**
- Acceso al historial completo de compras de todos los usuarios
- El botón de historial solo es visible para usuarios de tipo Admin

**Constructor:**
- `Admin(int identification, String name, String email, String password)`

---

#### 3.2.4. Clase Client

**Ubicación:** `src/Models/Client.java`

**Propósito:** Representa un usuario cliente del sistema. Extiende la clase `User`.

**Constructor:**
- `Client(int identification, String name, String email, String password)`

---

#### 3.2.5. Clase Node_User

**Ubicación:** `src/Models/Nodes/Node_User.java`

**Propósito:** Nodo para la lista doblemente enlazada de usuarios.

**Atributos:**
- `user` (User): Referencia al objeto User almacenado
- `next` (Node_User): Referencia al siguiente nodo
- `former` (Node_User): Referencia al nodo anterior

**Métodos principales:**
- Getters y setters para todos los atributos
- Constructor: `Node_User(User user)`

---

#### 3.2.6. Clase Node_Product

**Ubicación:** `src/Models/Nodes/Node_Product.java`

**Propósito:** Nodo para estructuras de datos de productos (listas doblemente enlazadas).

**Atributos:**
- `product` (Product): Referencia al objeto Product almacenado
- `next` (Node_Product): Referencia al siguiente nodo
- `former` (Node_Product): Referencia al nodo anterior

**Métodos principales:**
- Getters y setters para todos los atributos
- Constructor: `Node_Product(Product product)`

---

### 3.3. Estructuras de Datos (DataStructures)

#### 3.3.1. Clase Stack_Of_Product

**Ubicación:** `src/DataStructures/Stack_Of_Product.java`

**Propósito:** Gestiona tres pilas de productos: carrito de compras, favoritos e historial de compras. Maneja la persistencia de datos en archivos de texto.

**Atributos:**
- `cartProducts` (Stack<Product>): Pila de productos en el carrito
- `historyProducts` (Stack<Product>): Pila de productos en el historial
- `favoriteProducts` (Stack<Product>): Pila de productos en favoritos

**Métodos importantes:**

##### `saveToFile(Stack<Product> stack, String fileName)`

**Propósito:** Guarda una pila de productos en un archivo de texto plano.

**Parámetros:**
- `stack`: La pila de productos a guardar
- `fileName`: Nombre del archivo donde se guardará (ej: "CartShop", "Favorites", "History")

**Formato de guardado:**
```
idProduct, email, datePurchase, selectedSize
```

**Detalles de implementación:**
- Ubicación: Líneas 176-205
- Usa `BufferedWriter` para escribir en el archivo
- Formatea fechas con `DateTimeFormatter` usando el patrón "dd/MM/yyyy HH:mm"
- Si `datePurchase` es null, escribe "NULL"
- Si `selectedSize` es null, escribe "NULL"
- Cada producto se guarda en una línea separada
- El archivo se guarda en `src/Files/[fileName]`

**Ejemplo de uso:**
```java
Stack_Of_Product stack = Data_Manager.getManager().getStack_Product();
stack.saveToFile(stack.getCartProducts(), "CartShop");
```

---

##### `loadFromFile(Stack<Product> stack, String fileName)`

**Propósito:** Carga productos desde un archivo de texto y los almacena en una pila.

**Parámetros:**
- `stack`: La pila donde se cargarán los productos
- `fileName`: Nombre del archivo a leer

**Formato esperado:**
```
idProduct, email, datePurchase, selectedSize
```

**Compatibilidad hacia atrás:**
- Si el archivo tiene 3 campos (formato antiguo): `idProduct, email, datePurchase`
- Si el archivo tiene 4 campos (formato nuevo): `idProduct, email, datePurchase, selectedSize`
- Si `datePurchase` es "NULL", se establece como null
- Si `selectedSize` es "NULL" o no existe, se establece como null

**Detalles de implementación:**
- Ubicación: Líneas 207-270
- Usa `BufferedReader` para leer el archivo
- Limpia la pila antes de cargar nuevos datos
- Parsea cada línea separando por ", "
- Carga la información completa del producto desde el catálogo usando `loadProductFromCatalog()`
- Establece el email del cliente, fecha de compra y talla seleccionada
- Maneja errores de formato y muestra mensajes en consola

**Ejemplo de uso:**
```java
Stack_Of_Product stack = Data_Manager.getManager().getStack_Product();
stack.loadFromFile(stack.getCartProducts(), "CartShop");
```

---

##### `addToCart(Product product, String email, String selectedSize)`

**Propósito:** Agrega un producto al carrito de compras con la talla seleccionada.

**Parámetros:**
- `product`: El producto a agregar
- `email`: Correo del usuario que agrega el producto
- `selectedSize`: Talla seleccionada por el usuario

**Retorno:** `boolean` - true si se agregó exitosamente, false si ya existe o hay error

**Validaciones:**
- Verifica que el producto no esté ya en el carrito del mismo usuario
- Si el producto está en favoritos, lo mueve automáticamente del panel de favoritos al carrito
- Crea una copia del producto para evitar referencias compartidas

**Persistencia:** Guarda automáticamente en el archivo "CartShop" después de agregar

**Ubicación:** Líneas 406-432

---

##### `addToFavorites(Product product, String email, String selectedSize)`

**Propósito:** Agrega un producto a favoritos con la talla seleccionada.

**Parámetros:**
- `product`: El producto a agregar
- `email`: Correo del usuario que agrega el producto
- `selectedSize`: Talla seleccionada por el usuario

**Retorno:** `boolean` - true si se agregó exitosamente, false si ya existe o hay error

**Validaciones:**
- Verifica que el producto no esté ya en favoritos del mismo usuario
- Si el producto está en el carrito, lo mueve automáticamente del carrito a favoritos
- Crea una copia del producto para evitar referencias compartidas

**Persistencia:** Guarda automáticamente en el archivo "Favorites" después de agregar

**Ubicación:** Líneas 434-460

---

##### `loadProductFromCatalog(int idProduct)`

**Propósito:** Carga la información completa de un producto desde su archivo de catálogo.

**Parámetros:**
- `idProduct`: ID del producto a cargar

**Retorno:** `Product` - Objeto Product con toda la información, o null si hay error

**Formato de archivo:** `src/Files/InfoProducts/XXXX - información.txt`

**Formato esperado en el archivo:**
```
Nombre: [nombre del producto]
Marca: [marca]
Precio: $[precio]
Tallas: [talla1] [talla2] [talla3] ...
Descripción: [descripción completa]
```

**Detalles de implementación:**
- Ubicación: Líneas 104-174
- Lee el archivo línea por línea
- Parsea cada campo según su prefijo
- Limpia el precio removiendo "$", espacios y separadores de miles
- Parsea las tallas separándolas por espacios o guiones
- Retorna un objeto Product completo con todos los datos

**Ejemplo de uso:**
```java
Product product = stack.loadProductFromCatalog(1);
```

---

##### Otros métodos importantes:

- `saveCartToFile()`: Guarda el carrito en "CartShop"
- `loadCartFromFile()`: Carga el carrito desde "CartShop"
- `saveFavoritesToFile()`: Guarda favoritos en "Favorites"
- `loadFavoritesFromFile()`: Carga favoritos desde "Favorites"
- `saveHistoryToFile()`: Guarda historial en "History"
- `loadHistoryFromFile()`: Carga historial desde "History"
- `loadAllFromFiles()`: Carga todas las pilas al iniciar la aplicación
- `getCartProductsByUser(String email)`: Obtiene productos del carrito de un usuario específico
- `getFavoriteProductsByUser(String email)`: Obtiene productos de favoritos de un usuario específico
- `getHistoryProductsByUser(String email)`: Obtiene productos del historial de un usuario específico
- `removeFromCart(int idProduct, String email)`: Elimina un producto del carrito
- `removeFromFavorites(int idProduct, String email)`: Elimina un producto de favoritos
- `moveFromFavoritesToCart(int idProduct, String email)`: Mueve producto de favoritos a carrito
- `moveFromCartToFavorites(int idProduct, String email)`: Mueve producto de carrito a favoritos
- `addToHistory(Product product, String email)`: Agrega producto al historial con fecha actual

---

#### 3.3.2. Clase List_Double_User

**Ubicación:** `src/DataStructures/List_Double_User.java`

**Propósito:** Implementa una lista doblemente enlazada para gestionar usuarios del sistema. Maneja la persistencia de usuarios en archivo de texto.

**Atributos:**
- `head` (Node_User): Referencia al primer nodo de la lista

**Métodos importantes:**

##### `save_data_file_users()`

**Propósito:** Guarda todos los usuarios de la lista en un archivo de texto.

**Formato de guardado:**
```
ROL, name, identification, email, password
```

Donde ROL puede ser "ADMIN" o "CLIENT".

**Detalles de implementación:**
- Ubicación: Líneas 181-207
- Usa `BufferedWriter` para escribir en el archivo
- Recorre la lista doblemente enlazada desde `head`
- Diferencia entre Admin y Client usando `instanceof`
- El archivo se guarda en `src/Files/Users`
- Cada usuario se guarda en una línea separada

**Ejemplo de uso:**
```java
List_Double_User userList = Data_Manager.getManager().getList_user();
userList.save_data_file_users();
```

---

##### `load_data_from_file_users()`

**Propósito:** Carga usuarios desde un archivo de texto y los almacena en la lista doblemente enlazada.

**Formato esperado:**
```
ROL, name, identification, email, password
```

**Validaciones:**
- Verifica que cada línea tenga exactamente 5 campos
- Valida que el ROL sea "ADMIN" o "CLIENT"
- Valida que la identificación sea un número válido
- Ignora líneas con formato incorrecto y muestra mensajes en consola

**Detalles de implementación:**
- Ubicación: Líneas 209-257
- Usa `BufferedReader` para leer el archivo
- Limpia la lista antes de cargar nuevos datos
- Parsea cada línea separando por ", "
- Crea instancias de `Admin` o `Client` según el ROL
- Agrega los usuarios a la lista usando `addUser()`

**Ejemplo de uso:**
```java
List_Double_User userList = Data_Manager.getManager().getList_user();
userList.load_data_from_file_users();
```

---

##### Otros métodos importantes:

- `search_email(String email)`: Busca un usuario por su correo electrónico. Retorna `Node_User` o null
- `addUser(String name, int identification, String email, String password, String roll)`: Agrega un nuevo usuario a la lista
- `addUser(TextField, TextField, TextField, PasswordField, String)`: Versión sobrecargada que toma campos de formulario
- `create_user(TextField, TextField, TextField, PasswordField, String)`: Crea un usuario validando campos del formulario
- `getUsers()`: Retorna una `ObservableList<Node_User>` para usar en componentes JavaFX
- `getLast()`: Obtiene el último nodo de la lista
- `clear_list()`: Limpia toda la lista

---

#### 3.3.3. Clase Data_Manager

**Ubicación:** `src/DataStructures/Data_Manager.java`

**Propósito:** Implementa el patrón **Singleton** para proporcionar acceso centralizado a las estructuras de datos principales.

**Atributos estáticos:**
- `manager` (Data_Manager): Instancia única del manager

**Métodos:**

- `getManager()`: Retorna la instancia única del Data_Manager (crea una nueva si no existe)
- `getList_user()`: Retorna la instancia de `List_Double_User`
- `getStack_Product()`: Retorna la instancia de `Stack_Of_Product`

**Uso:**
```java
Data_Manager manager = Data_Manager.getManager();
List_Double_User userList = manager.getList_user();
Stack_Of_Product productStack = manager.getStack_Product();
```

**Ventajas del patrón Singleton:**
- Garantiza una única instancia de las estructuras de datos
- Facilita el acceso global desde cualquier controlador
- Evita duplicación de datos en memoria

---

### 3.4. Controladores (Controllers)

#### 3.4.1. Controller_Main_View

**Ubicación:** `src/Controllers/Controller_Main_View.java`

**Propósito:** Controlador principal de la aplicación. Gestiona la vista principal, el catálogo de productos y todos los paneles (carrito, favoritos, historial, detalles).

**Funcionalidades principales:**
- Carga de productos desde el catálogo al iniciar
- Gestión de visibilidad de paneles (carrito, favoritos, historial, detalles de producto)
- Manejo de eventos de botones del header
- Confirmación de cierre de sesión al cerrar la ventana
- Carga inicial de datos desde archivos
- Gestión del overlay de WhatsApp

**Métodos clave:**
- `initialize()`: Inicializa la vista, carga datos y configura eventos
- `loadProducts()`: Carga productos desde el catálogo y los muestra en el FlowPane
- `handleCartClick()`: Muestra/oculta el panel del carrito
- `handleFavoriteClick()`: Muestra/oculta el panel de favoritos
- `handleHistoryClick()`: Muestra/oculta el panel de historial
- `showProductDetail(Product product)`: Muestra el panel de detalles de un producto
- `hideProductDetailDialog()`: Oculta el panel de detalles y muestra el catálogo

---

#### 3.4.2. Controller_Login_View

**Ubicación:** `src/Controllers/Controller_Login_View.java`

**Propósito:** Controlador de la vista de inicio de sesión.

**Funcionalidades:**
- Validación de credenciales de usuario
- Validación de campos vacíos con mensajes específicos
- Mensaje de éxito al iniciar sesión exitosamente
- Redirección a `Main_View` después del login
- Gestión del overlay de WhatsApp en la pantalla de login
- Animaciones y efectos visuales

**Métodos clave:**
- `onLoginClick(ActionEvent)`: Maneja el evento de clic en el botón de login
- `validateUser(String username, String password)`: Valida las credenciales contra la lista de usuarios
- `redirectToLogin(Stage)`: Redirige a la pantalla de login

---

#### 3.4.3. Controller_Signing_View

**Ubicación:** `src/Controllers/Controller_Signing_View.java`

**Propósito:** Controlador de la vista de registro de nuevos usuarios.

**Funcionalidades:**
- Validación de campos del formulario
- Creación de nuevos usuarios (Admin o Client)
- Persistencia de usuarios en archivo
- Redirección después del registro

---

#### 3.4.4. Controladores de Componentes

**Ubicación:** `src/Controllers/ComponentControllers/`

Estos controladores gestionan componentes reutilizables de la interfaz:

- **`Controller_Product_Component`**: Controla las tarjetas de producto en el catálogo
  - Maneja la selección de talla
  - Agrega productos al carrito y favoritos
  - Configura el tooltip de doble clic
  - Abre el panel de detalles al hacer doble clic

- **`Controller_Product_Detail_Component`**: Controla el panel de detalles de producto
  - Muestra información completa del producto
  - Navegación entre imágenes del producto
  - Agrega productos al carrito y favoritos desde el panel de detalles

- **`Controller_Cart_Item_Component`**: Controla cada item individual en el carrito
  - Muestra información del producto (incluyendo talla seleccionada)
  - Elimina productos del carrito
  - Mueve productos a favoritos

- **`Controller_Favorite_Item_Component`**: Controla cada item individual en favoritos
  - Muestra información del producto (incluyendo talla seleccionada)
  - Elimina productos de favoritos
  - Mueve productos al carrito

- **`Controller_History_Component`**: Controla la tabla de historial de compras
  - Configura las columnas de la tabla
  - Muestra el historial completo (solo para administradores)
  - Incluye la columna de correo del usuario

- **`Controller_Change_Password_Component`**: Controla el formulario de cambio de contraseña
  - Valida la contraseña actual
  - Valida que la nueva contraseña y confirmación coincidan
  - Actualiza la contraseña en el archivo de usuarios

- **`Controller_WhatsApp_Component`**: Controla el overlay de selección de WhatsApp
  - Muestra opciones de números de contacto
  - Abre WhatsApp Web con el número seleccionado
  - Maneja efectos hover en los iconos

---

### 3.5. Persistencia de Datos

#### 3.5.1. Archivos de Texto Utilizados

El sistema utiliza archivos de texto plano para persistir datos:

- **`src/Files/Users`**: Almacena todos los usuarios del sistema
- **`src/Files/CartShop`**: Almacena productos en el carrito de cada usuario
- **`src/Files/Favorites`**: Almacena productos en favoritos de cada usuario
- **`src/Files/History`**: Almacena el historial de compras de todos los usuarios
- **`src/Files/InfoProducts/XXXX - información.txt`**: Archivos individuales del catálogo de productos (donde XXXX es el ID del producto con formato 0001, 0002, etc.)

---

#### 3.5.2. Formato de Archivos

##### Formato Users:
```
ROL, name, identification, email, password
```

**Ejemplo:**
```
ADMIN, Juan Pérez, 123456789, admin@lovelyas.com, admin123
CLIENT, María García, 987654321, maria@email.com, password123
```

---

##### Formato CartShop/Favorites/History:
```
idProduct, email, datePurchase, selectedSize
```

**Ejemplo:**
```
1, cliente@email.com, 15/12/2024 14:30, M
2, cliente@email.com, 15/12/2024 14:35, S
```

**Nota:** Si `datePurchase` es null, se guarda como "NULL". Si `selectedSize` es null, se guarda como "NULL".

---

##### Formato InfoProducts (Catálogo):
```
Nombre: [nombre del producto]
Marca: [marca]
Precio: $[precio]
Tallas: [talla1] [talla2] [talla3] ...
Descripción: [descripción completa del producto]
```

**Ejemplo:**
```
Nombre: Vestido Floral Verano
Marca: Lovely A's
Precio: $89.900
Tallas: XS S M L XL
Descripción: Hermoso vestido floral perfecto para el verano. Fabricado en tela suave y cómoda.
```

---

### 3.6. Flujo de Datos

El flujo de datos en la aplicación sigue este patrón:

1. **Inicio de aplicación:**
   - `App.java` (punto de entrada) carga `Login_View.fxml`
   - Se inicializa `Controller_Login_View`

2. **Carga inicial de datos:**
   - `Data_Manager.getManager()` crea las instancias de `List_Double_User` y `Stack_Of_Product`
   - `Controller_Main_View.initialize()` llama a:
     - `userList.load_data_from_file_users()`: Carga usuarios desde archivo
     - `productStack.loadAllFromFiles()`: Carga carrito, favoritos e historial desde archivos

3. **Proceso de login:**
   - Usuario ingresa credenciales en `Controller_Login_View`
   - Se valida con `userList.search_email()` y verificación de contraseña
   - Si es válido, se carga `Main_View.fxml` y se inicializa `Controller_Main_View`
   - Se determina si el usuario es Admin o Client para mostrar/ocultar funcionalidades

4. **Operaciones del usuario:**
   - Usuario interactúa con la interfaz (clics, selecciones)
   - Los controladores capturan los eventos
   - Se realizan operaciones en las estructuras de datos (`Stack_Of_Product`, `List_Double_User`)
   - Los cambios se persisten automáticamente en archivos usando `saveToFile()` o `save_data_file_users()`

5. **Carga de productos:**
   - `Controller_Main_View.loadProducts()` lee los archivos del catálogo
   - Para cada producto, se crea un `Product_Component` que se agrega al `FlowPane`
   - Los productos se cargan desde `src/Files/InfoProducts/` usando `loadProductFromCatalog()`

6. **Persistencia automática:**
   - Cada operación que modifica datos (agregar al carrito, favoritos, comprar, cambiar contraseña) guarda automáticamente en archivo
   - Esto garantiza que los datos persistan entre sesiones

---

## Consideraciones Técnicas

- **Compatibilidad hacia atrás**: Los métodos de carga manejan archivos con formatos antiguos (3 campos) y nuevos (4 campos)
- **Manejo de errores**: Todos los métodos de lectura/escritura incluyen try-catch para manejar excepciones
- **Validaciones**: Se realizan validaciones antes de agregar productos o usuarios para evitar duplicados
- **Thread safety**: La aplicación usa `Platform.runLater()` para operaciones de UI en JavaFX
- **Separación de responsabilidades**: Cada clase tiene una responsabilidad específica siguiendo principios SOLID
