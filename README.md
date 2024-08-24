# 📋 TodoApp

**Gestor de Tareas con Categorías**

Este proyecto es una aplicación web desarrollada con **Spring Boot** que permite a los usuarios gestionar tareas y categorías. La aplicación utiliza **Hibernate** para el manejo de la persistencia de datos y **SQL** para la base de datos.

---

## 🌟 Características

- **Gestión de Tareas**: Los usuarios pueden **crear, leer, actualizar y eliminar** tareas. Cada tarea tiene un título, descripción, estado, y pertenece a una categoría.
- **Gestión de Categorías**: Los usuarios pueden gestionar las categorías a las que pertenecen las tareas. Las categorías pueden ser **creadas, leídas, actualizadas y eliminadas**.
- **Relación entre Tareas y Categorías**: Una categoría puede tener múltiples tareas asociadas, y una tarea está asociada a una única categoría.

---

## 🛠️ Tecnologías Utilizadas

- **Spring Boot**: Framework principal para la construcción de la aplicación.
- **Hibernate**: ORM utilizado para la interacción con la base de datos.
- **SQL**: Lenguaje utilizado para la gestión de la base de datos.
- **Swagger**: Para ve la documentacion acceda a la siguiente URL: http://localhost:8080/swagger-ui/index.html#/

---

## 🚀 Uso

Una vez que la aplicación esté en funcionamiento, se puede acceder a ella desde el navegador web a través de `http://localhost:8080`. 

Además, es necesario levantar el archivo `index.html` en la siguiente URL: `http://127.0.0.1:5500/index.html` (escucha peticiones en el puerto 5500).

---

## 📝 CRUD de Tareas

- **Crear**: Los usuarios pueden crear una nueva tarea asignándola a una categoría existente.
- **Leer**: Los usuarios pueden ver la lista de todas las tareas y filtrar por categoría.
- **Actualizar**: Los usuarios pueden editar los detalles de una tarea.
- **Eliminar**: Los usuarios pueden eliminar una tarea existente.

---

## 🗂️ CRUD de Categorías

- **Crear**: Los usuarios pueden crear una nueva categoría para organizar sus tareas.
- **Leer**: Los usuarios pueden ver todas las categorías disponibles.
- **Actualizar**: Los usuarios pueden editar los detalles de una categoría.
- **Eliminar**: Los usuarios pueden eliminar una categoría, siempre y cuando no tenga tareas asociadas.


## 🛠 Skills y patrones de diseño
Java - Spring Boot- Git - MVC - DTO - Inyección de dependencias.

