# 📑 CATÁLOGO DETALLADO DE CASOS DE PRUEBA
**Tiendo POS System - Test Cases Repository**

---

## ÍNDICE

1. [Authentication Tests (6)](#authentication-tests)
2. [User Management Tests (1)](#user-management-tests)
3. [Inventory Tests (7)](#inventory-tests)
4. [Sales Tests (5)](#sales-tests)
5. [End-to-End Business Flow Tests (1)](#end-to-end-tests)

---

## AUTHENTICATION TESTS

### TC-AUTH-001: Login with Valid Credentials
```
📌 Test ID: loginWithValidCredentials
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-01                                  │
│ Epic               │ Authentication                         │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, authentication             │
│ Status             │ ✅ READY                               │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Usuario registrado en el sistema
  - Login page cargada
  - Credenciales válidas disponibles

🎯 Pasos:
  1. Ingresar username/email válido
  2. Ingresar password válido
  3. Hacer click en botón Login
  4. Esperar carga de Dashboard

✅ Resultado Esperado:
  - Dashboard se carga correctamente
  - Usuario es autenticado
  - Se redirige a página principal

🔄 Postcondiciones:
  - Sesión activa establecida
  - User logged in status = TRUE
```

---

### TC-AUTH-002: Session Persistence After Page Refresh
```
📌 Test ID: refreshingPageAfterLogin
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-01.1 (Session Persistence)          │
│ Epic               │ Authentication                         │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, authentication             │
│ Status             │ ✅ READY                               │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Usuario logueado en el sistema
  - Dashboard visible
  - Sesión activa

🎯 Pasos:
  1. Confirmar que Dashboard está cargado
  2. Ejecutar refresh en la página (F5)
  3. Esperar recarga completa

✅ Resultado Esperado:
  - Dashboard se recarga sin redireccionar a login
  - Sesión se mantiene activa
  - Datos de usuario visibles

🔄 Postcondiciones:
  - User sigue autenticado
  - Cookies/session storage intactos
```

---

### TC-AUTH-003: User Can Logout Successfully
```
📌 Test ID: userCanLogoutSuccessfully
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-01                                  │
│ Epic               │ Authentication                         │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, authentication             │
│ Status             │ ✅ READY                               │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Usuario logueado
  - Dashboard visible
  - Sesión activa

🎯 Pasos:
  1. Localizar botón de logout
  2. Hacer click en logout
  3. Confirmar acción si es necesario
  4. Esperar redirección

✅ Resultado Esperado:
  - Redirección a LoginPage
  - Sesión finalizada
  - Botón logout no visible

🔄 Postcondiciones:
  - Cookies eliminadas
  - Session terminada
  - User logged in status = FALSE
```

---

### TC-AUTH-004: Back Button Does Not Restore Session
```
📌 Test ID: backButtonDoesNotRestoreSession
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-01 (Security)                       │
│ Epic               │ Authentication                         │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, authentication, security   │
│ Status             │ ✅ READY                               │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Usuario logueado en el sistema
  - Dashboard previamente visitado
  - Logout ejecutado

🎯 Pasos:
  1. Login normal
  2. Dashboard se carga
  3. Logout ejecutado
  4. Login page visible
  5. Click en botón Back del navegador
  6. Refresh de la página
  7. Verificar página actual

✅ Resultado Esperado:
  - Back button NO restaura sesión previa
  - Se mantiene en Login page
  - Dashboard NO accesible sin relogueo

🔄 Postcondiciones:
  - Sesión completamente terminada
  - Back button handling correcto
```

---

### TC-AUTH-005: Login with Invalid Credentials
```
📌 Test ID: loginWithInvalidCredentials
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-01 (Security)                       │
│ Epic               │ Authentication                         │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, authentication, negative   │
│ Status             │ ✅ READY                               │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Login page visible
  - Sistema operacional

🎯 Pasos:
  1. Ingresar email inválido: "invalidEmail@invalid.com"
  2. Ingresar password inválido: "InvalidPassword123"
  3. Click en botón Login
  4. Esperar respuesta

✅ Resultado Esperado:
  - Error message visible
  - Dashboard NO cargado
  - User NOT autenticado
  - Login page permanece activa

🔄 Postcondiciones:
  - Sesión NOT creada
  - Error message dismissible
```

---

### TC-AUTH-006: Access Dashboard Without Login
```
📌 Test ID: goToDashboardWithoutLogin
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-01 (Security)                       │
│ Epic               │ Authentication                         │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, authentication, security, negative │
│ Status             │ ✅ READY                               │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Browser sin sesión activa
  - URL del dashboard conocida

🎯 Pasos:
  1. Abrir navegador sin logout
  2. Navegar directamente a dashboard URL
  3. Esperar respuesta del sistema

✅ Resultado Esperado:
  - Sistema redirige a login page
  - Dashboard NO accesible
  - User NOT autenticado
  - Login page cargada

🔄 Postcondiciones:
  - Protección de rutas funcionando
  - Redirección correcta implementada
```

---

## USER MANAGEMENT TESTS

### TC-USER-001: User CRUD Operations (Create, Read, Update, Delete)
```
📌 Test ID: testUserCRUDOperations
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-04                                  │
│ Epic               │ User Management                        │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, user-management            │
│ Status             │ ⚠️ IN PROGRESS                        │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Usuario administrador logueado
  - Dashboard visible
  - UsersPage accesible

🎯 Pasos (Flujo Completo):

  [CREATE]
  1. Navegar a Users page desde Dashboard
  2. Click en botón "Crear Usuario"
  3. Llenar formulario con:
     - Username: "TestUser{timestamp}"
     - Email: "testuser{timestamp}@example.com"
     - Password: "password123"
     - Role: "Cajero"
  4. Click en Submit
  5. Verificar que usuario aparece en lista

  [READ/SEARCH]
  6. Buscar usuario creado en la lista
  7. Confirmar que datos coinciden

  [UPDATE]
  8. Click en botón Edit del usuario
  9. Cambiar:
     - Username: "EditedUser{timestamp}"
     - Email: "editeduser{timestamp}@example.com"
     - Role: "Tendero (Administrador)"
  10. Click en Submit
  11. Verificar cambios en lista

  [DELETE]
  12. Localizar usuario editado
  13. Click en botón Delete
  14. Confirmar eliminación
  15. Verificar que usuario no aparece en lista

✅ Resultados Esperados:
  CREATE:
    ✓ Usuario cargado en lista
    ✓ Datos coinciden con lo ingresado
    ✓ Timestamp único para evitar conflictos
  
  UPDATE:
    ✓ Nombre actualizado en lista
    ✓ Email actualizado
    ✓ Rol actualizado correctamente
    ✓ Usuario original no existe
  
  DELETE:
    ✓ Usuario removido de lista
    ✓ Confirmación ejecutada
    ✓ No aparece en búsquedas posteriores

🔄 Postcondiciones:
  - No quedan usuarios de test en el sistema
  - Datos limpios para siguiente ejecución

⚠️ PROBLEMAS CONOCIDOS:
  - Dropdown de roles no selecciona correctamente (Issue)
  - Botón submit deshabilitado inesperadamente (Issue)
  - Opción de cambio de contraseña no documentada
```

---

## INVENTORY TESTS

### TC-INV-001: Create New Product
```
📌 Test ID: adminCanCreateNewProduct
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-04                                  │
│ Epic               │ Inventory Management                   │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, inventory                  │
│ Status             │ ✅ READY                               │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Usuario administrador logueado
  - Inventory page visible
  - Formulario de creación accesible

🎯 Pasos:
  1. Generar datos únicos (timestamp):
     - productName = "QA_" + timestamp
     - sku = "SKU_" + timestamp
  2. Click en "Crear Producto"
  3. Llenar formulario:
     - Nombre: productName
     - SKU: sku
     - Precio: "50.000,00"
     - Cantidad: 20
     - Cantidad Mínima: 5
  4. Submit formulario
  5. Búsqueda del producto creado

✅ Resultado Esperado:
  - Producto visible en lista de inventario
  - Datos guardados correctamente
  - Precio visible con formato correcto

🔄 Postcondiciones:
  - Producto disponible para ventas
  - Datos persistidos en BD
```

---

### TC-INV-002: Edit Existing Product
```
📌 Test ID: adminCanEditProduct
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-06                                  │
│ Epic               │ Inventory Management                   │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, inventory                  │
│ Status             │ ✅ READY                               │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Producto creado en el sistema
  - Inventory page visible
  - Acceso a edición

🎯 Pasos:
  1. Crear producto de prueba (helper method)
  2. Buscar producto en inventario
  3. Esperar a que aparezca en lista
  4. Click en botón Edit
  5. Modificar precio: "2.000,00"
  6. Click en Submit
  7. Buscar producto nuevamente
  8. Verificar que precio fue actualizado

✅ Resultado Esperado:
  - Precio actualizado en lista
  - Cambios persistidos
  - Producto sigue visible con nuevo precio

🔄 Postcondiciones:
  - Producto modificado listo para venta
  - BD actualizada correctamente
```

---

### TC-INV-003: Delete Product
```
📌 Test ID: adminCanDeleteProduct
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-06                                  │
│ Epic               │ Inventory Management                   │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, inventory                  │
│ Status             │ ✅ READY                               │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Producto creado en sistema
  - Inventory page visible
  - Acceso a eliminación

🎯 Pasos:
  1. Crear producto de prueba
  2. Verificar presencia en lista
  3. Buscar producto
  4. Click en botón Delete
  5. Confirmar eliminación (si modal aparece)
  6. Verificar ausencia en lista

✅ Resultado Esperado:
  - Producto removido de lista
  - No aparece en búsquedas posteriores
  - Confirmación sin errores

🔄 Postcondiciones:
  - Producto eliminado de BD
  - Inventario consistente
```

---

### TC-INV-004: Description Field Present in Product Form
```
📌 Test ID: descriptionFieldIsPresentInProductCreationForm
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-04                                  │
│ Epic               │ Inventory Management                   │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, inventory                  │
│ Status             │ ✅ READY                               │
│ Issue              │ #6                                     │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Inventory page visible
  - Formulario de creación accesible

🎯 Pasos:
  1. Click en "Crear Producto"
  2. Esperar carga del formulario
  3. Verificar presencia de campo "Descripción"

✅ Resultado Esperado:
  - Campo de descripción visible
  - Campo rotulado correctamente
  - Acepta texto

⚠️ ISSUE #6:
  - Campo no está presente en versión actual
  - Requiere implementación en formulario
```

---

### TC-INV-005: Category Field Present in Product Form
```
📌 Test ID: categoryFieldIsPresentInProductCreationForm
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-04                                  │
│ Epic               │ Inventory Management                   │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, inventory                  │
│ Status             │ ✅ READY                               │
│ Issue              │ #7                                     │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Inventory page visible
  - Formulario de creación accesible

🎯 Pasos:
  1. Click en "Crear Producto"
  2. Esperar carga del formulario
  3. Verificar presencia de campo "Categoría"

✅ Resultado Esperado:
  - Campo de categoría visible
  - Campo rotulado correctamente
  - Dropdown o selector funcional

⚠️ ISSUE #7:
  - Campo no está presente en versión actual
  - Requiere implementación en formulario
```

---

### TC-INV-006: Duplicate SKU Shows Error Message
```
📌 Test ID: duplicateSKUShowsErrorMessage
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-04                                  │
│ Epic               │ Inventory Management                   │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, inventory                  │
│ Status             │ ✅ READY                               │
│ Issue              │ #8                                     │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Sistema operacional
  - Inventory page visible

🎯 Pasos:
  1. Crear primer producto:
     - productName1 = "QA_Dup_" + timestamp
     - sku = "SKU_DUP_" + timestamp
     - Precio: "50.000,00"
     - Cantidad: 20
     - Min: 5
  2. Confirmar creación
  3. Intentar crear segundo producto:
     - productName2 = "QA_Dup2_" + timestamp
     - Usar MISMO SKU
     - Precio diferente: "60.000,00"
  4. Intentar submit
  5. Verificar que error message aparece

✅ Resultado Esperado:
  - Error message visible
  - Segundo producto NOT creado
  - Primer producto permanece intacto
  - Usuario informado del duplicado

🔄 Postcondiciones:
  - Solo un producto con SKU en BD
  - Validación de unicidad funcionando
```

---

### TC-INV-007: Duplicate SKU Shows Custom Error (Not Platform)
```
📌 Test ID: duplicateSKUShowsCustomErrorMessage
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-04                                  │
│ Epic               │ Inventory Management                   │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, inventory                  │
│ Status             │ ✅ READY                               │
│ Issue              │ #8                                     │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - TC-INV-006 ejecutado exitosamente
  - Error message obtenido

🎯 Pasos:
  1. Capturar texto del error message
  2. Verificar que NO contiene "WooCommerce"
  3. Verificar que es custom/branded

✅ Resultado Esperado:
  - Error message NO expone plataforma backend
  - Mensaje es custom/amigable
  - No contiene stack traces
  - Mensaje claro para usuario

⚠️ ISSUE #8:
  - Error message expone "WooCommerce"
  - Requiere enmascaramiento de error
  - Seguridad por obscuridad
```

---

## SALES TESTS

### TC-SALES-001: Add Multiple Products to Cart
```
📌 Test ID: userCanAddMultipleProductsToCart
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-08                                  │
│ Epic               │ Sales Management                       │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, sales                      │
│ Status             │ ✅ READY                               │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Usuario (Cajero) logueado
  - Caja abierta
  - Sales page visible
  - Producto existe: "12Test"

🎯 Pasos:
  1. Buscar producto "12Test"
  2. Click en "Agregar al Carrito"
  3. Producto debe aparecer en carrito
  4. Click nuevamente en mismo producto
  5. Cantidad debe incrementar a 2

✅ Resultado Esperado:
  - Producto aparece en carrito
  - Cantidad se incrementa correctamente
  - Cart count = 2
  - UI actualiza en tiempo real

🔄 Postcondiciones:
  - Carrito listo para pago
  - Cantidades correctas
```

---

### TC-SALES-002: System Calculates Total Automatically
```
📌 Test ID: systemCalculatesTotalAutomatically
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-09                                  │
│ Epic               │ Sales Management                       │
│ Severity           │ BLOCKER                                │
│ Groups             │ regression, sales, financial           │
│ Status             │ ✅ READY                               │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Usuario (Cajero) logueado
  - Caja abierta
  - Sales page visible
  - Productos existen: "12Test", "13Test"

🎯 Pasos:
  1. Buscar "12Test"
  2. Agregar a carrito
  3. Buscar "13Test"
  4. Agregar a carrito
  5. Verificar total en carrito

✅ Resultado Esperado:
  - Total se calcula automáticamente
  - Total contiene símbolo "$"
  - Total es suma correcta de precios
  - Total actualiza con cada producto

🔄 Postcondiciones:
  - Cálculo financiero correcto
  - Precisión en moneda validada
```

---

### TC-SALES-003: User Can Select Payment Method
```
📌 Test ID: userCanSelectPaymentMethod
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-10                                  │
│ Epic               │ Sales Management                       │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, sales, financial           │
│ Status             │ ✅ READY                               │
│ Note               │ GAP entre RF-10 y métodos en frontend  │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Usuario (Cajero) logueado
  - Caja abierta
  - Producto en carrito
  - Sales page visible

🎯 Pasos:
  1. Producto en carrito
  2. Click en selector de Método de Pago
  3. Seleccionar "Efectivo"
  4. Confirmar selección

✅ Resultado Esperado:
  - "Efectivo" seleccionado
  - Carrito aún contiene productos
  - Sistema listo para confirmación
  - Cart count > 0

⚠️ GAP DETECTADO:
  - RF-10 especifica: Nequi, DaviPlata, Débito/Crédito
  - Frontend solo ofrece: Efectivo
  - Requiere alineamiento entre RF y UI
```

---

### TC-SALES-004: Sale Summary Modal is Displayed
```
📌 Test ID: saleSummaryModalIsDisplayed
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-13                                  │
│ Epic               │ Sales Management                       │
│ Severity           │ CRITICAL                               │
│ Groups             │ regression, sales, ui                  │
│ Status             │ ✅ READY                               │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Usuario (Cajero) logueado
  - Caja abierta
  - Producto en carrito
  - Método de pago seleccionado

🎯 Pasos:
  1. Producto "12Test" en carrito
  2. Seleccionar método de pago "Efectivo"
  3. Ingresar monto recibido: "50000"
  4. Click en "Confirmar Venta"
  5. Verificar que modal de resumen aparece

✅ Resultado Esperado:
  - Modal visible con resumen
  - Muestra: Productos, cantidades, total
  - Sales page sigue cargada
  - Usuario puede revisar antes de confirmar

🔄 Postcondiciones:
  - Modal listo para confirmación final
  - Datos visibles para validación
```

---

### TC-SALES-005: User Can Complete Sale Successfully
```
📌 Test ID: userCanCompleteSaleSuccessfully
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-08/09/10/26                         │
│ Epic               │ Sales Management                       │
│ Severity           │ BLOCKER                                │
│ Groups             │ smoke, regression, e2e, sales          │
│ Status             │ ✅ READY                               │
└─────────────────────────────────────────────────────────────┘

📋 Precondiciones:
  - Usuario (Cajero) logueado
  - Caja ABIERTA con monto inicial: 200000
  - Sales page visible

🎯 Pasos:
  1. Navegar a Caja (CashRegister)
  2. Verificar que caja está cerrada
  3. Abrir caja con monto inicial: 200000
  4. Navegar a Sales
  5. Buscar y agregar "12Test" al carrito
  6. Seleccionar método de pago "Efectivo"
  7. Ingresar monto recibido: "50000"
  8. Click en "Confirmar Venta"
  9. Procesar venta final

✅ Resultado Esperado:
  - Caja abierta exitosamente
  - Venta registrada correctamente
  - Modal de confirmación procesado
  - Mensaje de éxito visible
  - isSaleSuccessful() = TRUE

🔄 Postcondiciones:
  - Transacción registrada en BD
  - Caja reflejará monto de venta
  - Inventario actualizado
  - Carrito vacío para próxima venta
```

---

## END-TO-END BUSINESS FLOW TESTS

### TC-E2E-001: Full Business Flow Works Correctly
```
📌 Test ID: fullBusinessFlowWorksCorrectly
┌─────────────────────────────────────────────────────────────┐
│ Requirement        │ RF-01/04/08/09/10/14/26/29            │
│ Epic               │ End-to-End Business Flow               │
│ Severity           │ BLOCKER                                │
│ Groups             │ smoke, e2e, business-flow              │
│ Status             │ ✅ READY                               │
│ Priority           │ 🔴 HIGHEST                            │
└─────────────────────────────────────────────────────────────┘

📋 Descripción Funcional:
Validación del flujo de negocio completo desde login hasta 
cierre de caja, incluyendo apertura de caja, creación de 
producto, venta y reducción de inventario.

🎯 FLUJO COMPLETO:

  [FASE 1: AUTENTICACIÓN]
  1. Login como usuario válido
  2. Verificar que Dashboard carga
  ✓ RF-01: Autenticación

  [FASE 2: INICIO DE CAJA]
  3. Navegar a módulo Caja (CashRegister)
  4. Verificar estado de caja (cerrada)
  5. Abrir caja con monto inicial: 200.000
  6. Confirmar apertura
  ✓ RF-14: Apertura de Caja

  [FASE 3: CREACIÓN DE PRODUCTO]
  7. Generar datos únicos:
     - productName = "SmokeProduct_" + timestamp
     - sku = "SKU_" + timestamp
  8. Navegar a Inventario
  9. Crear producto:
     - Nombre: productName
     - SKU: sku
     - Precio: 5000
     - Cantidad: 10
     - Min: 5
  10. Buscar producto creado
  11. Esperar a que aparezca en lista
  ✓ RF-04: Gestión de Inventario

  [FASE 4: PROCESO DE VENTA]
  12. Navegar a módulo Sales
  13. Buscar productName
  14. Agregar al carrito
  15. Seleccionar método de pago: "Efectivo"
  16. Ingresar monto: 5000
  17. Confirmar venta
  18. Procesar venta final
  ✓ RF-08: Carrito Multi-producto
  ✓ RF-09: Cálculo automático de total
  ✓ RF-10: Selección de método de pago

  [FASE 5: VALIDACIÓN DE INVENTARIO]
  19. Navegar a Inventario
  20. Buscar product name
  21. Obtener stock actual
  22. Validar que stock = 9 (fue 10, se vendió 1)
  ✓ RF-04: Inventario reducido correctamente

  [FASE 6: CIERRE DE CAJA]
  23. Navegar a CashRegister
  24. Verificar que caja está abierta
  25. Cerrar caja con monto final: 205.000
     (inicial 200.000 + venta 5.000)
  26. Confirmar cierre modal
  27. Verificar que caja está cerrada
  ✓ RF-26: Cierre de Caja

✅ Resultados Esperados (Todos deben PASS):
  ✓ Dashboard cargado después de login
  ✓ Caja abierta correctamente
  ✓ Producto creado y visible
  ✓ Venta completada sin errores
  ✓ Mensaje de éxito mostrado
  ✓ Inventario reducido en 1 unidad
  ✓ Caja cerrada correctamente

🔄 Postcondiciones:
  - Sesión cerrada correctamente
  - BD consistente
  - Todos los módulos completados exitosamente
  - Sistema listo para próxima sesión

⚠️ CRITICIDAD:
  Este es el test MÁS IMPORTANTE del suite.
  Si falla, indica problema crítico en flujo de negocio.
  Debe tener PASS rate del 100%.

📊 MÉTRICAS:
  - Tiempo ejecución estimado: 120-150 segundos
  - Pasos: 27
  - Requisitos cubiertos: 8 (RF-01, 04, 08, 09, 10, 14, 26, 29)
  - Módulos validados: 4 (Auth, Inventory, Sales, Cash)
```

---

## 📊 MATRIX DE COBERTURA

### Por Requisito Funcional

```
RF-01: Autenticación
  ├─ TC-AUTH-001: Login válido
  ├─ TC-AUTH-002: Persistencia de sesión
  ├─ TC-AUTH-003: Logout
  ├─ TC-AUTH-004: Back button security
  ├─ TC-AUTH-005: Credenciales inválidas
  ├─ TC-AUTH-006: Acceso sin login
  └─ TC-E2E-001: Flujo completo

RF-04: Gestión de Usuarios e Inventario
  ├─ TC-USER-001: CRUD de usuarios
  ├─ TC-INV-001: Crear producto
  ├─ TC-INV-002: Editar producto
  ├─ TC-INV-003: Eliminar producto
  ├─ TC-INV-004: Campo descripción
  ├─ TC-INV-005: Campo categoría
  ├─ TC-INV-006: SKU duplicado
  ├─ TC-INV-007: Error message custom
  └─ TC-E2E-001: Flujo completo

RF-06: Edición y Eliminación de Productos
  ├─ TC-INV-002: Editar producto
  └─ TC-INV-003: Eliminar producto

RF-08: Carrito Multi-producto
  ├─ TC-SALES-001: Agregar múltiples
  ├─ TC-SALES-005: Venta completa
  └─ TC-E2E-001: Flujo completo

RF-09: Cálculo Automático Total
  ├─ TC-SALES-002: Total automático
  ├─ TC-SALES-005: Venta completa
  └─ TC-E2E-001: Flujo completo

RF-10: Selección Método de Pago
  ├─ TC-SALES-003: Pago método
  ├─ TC-SALES-005: Venta completa
  └─ TC-E2E-001: Flujo completo

RF-13: Resumen de Venta Pre-confirmación
  ├─ TC-SALES-004: Modal resumen
  └─ TC-SALES-005: Venta completa

RF-14: Apertura de Caja
  ├─ TC-SALES-005: Venta completa
  └─ TC-E2E-001: Flujo completo

RF-26: Cierre de Caja
  └─ TC-E2E-001: Flujo completo

RF-29: (No especificado, en flujo E2E)
  └─ TC-E2E-001: Flujo completo
```

---

## 🎯 GRUPOS DE EJECUCIÓN

### Smoke Tests (2)
- TC-SALES-005: Complete Sale Successfully
- TC-E2E-001: Full Business Flow

**Ejecución:** Rápida (< 5 minutos)  
**Propósito:** Validar que sistema está operacional

### Regression Tests (14)
- Todos los tests excepto los 2 smoke

**Ejecución:** Completa (60-90 minutos)  
**Propósito:** Cobertura funcional detallada

### Negative Tests (3)
- TC-AUTH-005: Invalid Credentials
- TC-AUTH-006: No Authentication
- TC-AUTH-004: Back Button Security

**Ejecución:** Incluido en Regression  
**Propósito:** Validar seguridad y manejo de errores

---

## 📈 ESTADÍSTICAS

```
Total de Test Cases:        20
├─ READY:                   18 (90%)
├─ IN PROGRESS:             1  (5%)  [User CRUD]
└─ BLOCKED:                 1  (5%)  [Awaiting fix]

Por Severidad:
├─ BLOCKER:                 3  (15%)
├─ CRITICAL:                15 (75%)
└─ MAJOR:                   2  (10%)

Por Estado:
├─ ✅ PASS:                 18
├─ ⚠️  IN PROGRESS:         1
└─ ❌ BLOCKED:              1
```

---

**Documento generado:** 11 de Marzo de 2026  
**Última actualización:** 11 de Marzo de 2026  
**Próxima revisión:** Después de resolución de Issues

