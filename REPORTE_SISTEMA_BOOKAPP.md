# REPORTE DETALLADO DE PANTALLAS Y ROLES - BOOKAPP

## INTRODUCCIÓN
Este documento describe la estructura de navegación y funcionalidad de la aplicación BookApp, detallando cada pantalla y los permisos asociados según el rol del usuario (Administrador, Bibliotecario y Lector).

---

## 1. PANTALLAS GENERALES (Públicas / Acceso)
Estas pantallas gestionan el ciclo de vida de la sesión del usuario.

*   **SplashFragment:**
    *   **Función:** Pantalla de carga inicial.
    *   **Detalle:** Verifica si el usuario ya está autenticado. Si existe sesión, redirige al Dashboard correspondiente; de lo contrario, envía al Login.
*   **LoginFragment:**
    *   **Función:** Puerta de enlace al sistema.
    *   **Detalle:** Contiene campos de correo y contraseña. Implementa la lógica de redirección basada en roles (`UserRole`).
*   **SignUpFragment:**
    *   **Función:** Registro de nuevos usuarios.
    *   **Detalle:** Permite la creación de cuentas tipo "Lector" de forma autónoma.

---

## 2. ROL: ADMINISTRADOR (ADMIN)
El rol con mayores privilegios, enfocado en la gestión de personal y análisis de datos.

*   **DashboardAdminFragment (Panel Principal):**
    *   **Detalle:** Muestra un resumen del estado de la biblioteca: total de préstamos activos y una lista de "Morosos" (usuarios con préstamos vencidos). Incluye badges de alerta rojos para resaltar la urgencia.
*   **ListaUsuariosFragment:**
    *   **Detalle:** Un directorio completo de todos los usuarios (Administradores, Bibliotecarios y Lectores). Permite la búsqueda y filtrado.
*   **RegistrarUsuarioFragment:**
    *   **Detalle:** Formulario exclusivo para que el administrador cree nuevas cuentas de nivel staff (otros administradores o bibliotecarios).
*   **Módulo de Reportes:**
    *   **ReporteMensualFragment:** Visualiza el volumen de transacciones del mes.
    *   **TopLibrosFragment:** Identifica los libros con mayor demanda.
    *   **ComparacionMensualFragment:** Gráficas comparativas de crecimiento o actividad.

---

## 3. ROL: BIBLIOTECARIO
Encargado de la gestión operativa de los activos físicos.

*   **DashboardBibliotecarioFragment:**
    *   **Detalle:** Panel de acciones rápidas. Contiene accesos directos a "Prestar Libro", "Recibir Devolución" y "Consultar Catálogo".
*   **RegistrarLibroFragment:**
    *   **Detalle:** Gestión del inventario. Permite añadir títulos, autores, categorías, imágenes de portada y stock.
*   **RegistrarPrestamoFragment:**
    *   **Detalle:** Flujo para asignar un libro a un socio. Valida la disponibilidad del libro antes de procesar.
*   **RegistrarDevolucionFragment:**
    *   **Detalle:** Proceso inverso al préstamo. Actualiza el estado del libro a "Disponible" y cierra el registro del préstamo.
*   **RegistrarSocioFragment:**
    *   **Detalle:** Registro de nuevos clientes físicos que no necesariamente usan la app pero piden libros en ventanilla.

---

## 4. ROL: LECTOR / USUARIO
Enfocado en el consumo de contenidos y autogestión de préstamos.

*   **DashboardLectorFragment:**
    *   **Detalle:** Pantalla de bienvenida que muestra recomendaciones y un acceso rápido a "Mis Préstamos" para evitar olvidos de fechas.
*   **CatalogoLibrosFragment:**
    *   **Detalle:** Galería visual de todos los libros. Incluye un sistema de filtrado por estado (Disponible, Prestado, No Disponible).
*   **LibroDetalleFragment:**
    *   **Detalle:** Información expandida del libro (sinopsis, autor, año). Permite al lector iniciar la solicitud de préstamo si el libro está disponible.
*   **ConfirmacionPrestamoFragment:**
    *   **Detalle:** Pantalla de "ticket virtual" que confirma que la solicitud ha sido procesada correctamente.

---

## 5. PANTALLAS DE SOPORTE Y CONFIGURACIÓN
Comunes para la mayoría de los usuarios.

*   **CalendarioPrestamosFragment:** Vista mensual donde se marcan los hitos de entregas y devoluciones.
*   **MiPerfilFragment / PerfilUsuarioFragment:** Gestión de datos personales (nombre, correo, foto de perfil).
*   **ConfiguracionFragment:** Ajustes de la aplicación como notificaciones, modo oscuro y cierre de sesión.

---
**Generado automáticamente por el Asistente de Desarrollo.**
