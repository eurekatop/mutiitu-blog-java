function handleRouteChange() {
    debugger
    var currentPath = window.location.pathname;

    console.log("Ruta actual: " + currentPath);
  
    // Aquí puedes realizar acciones específicas para cada ruta
    if (currentPath === "/ruta1") {
      // Lógica para la ruta "/ruta1"
    } else if (currentPath === "/ruta2") {
      // Lógica para la ruta "/ruta2"
    } else {
      // Lógica por defecto o manejo de rutas no encontradas
    }
  }




  window.addEventListener("DOMContentLoaded", () => {
    window.addEventListener("popstate", (event) => {
        console.log(
          `location: ${document.location}, state: ${JSON.stringify(event.state)}`,
        );
      });
      history.pushState({ page: 2 }, "title 2", "?page=2");
      history.pushState({ page: 1 }, "title 1", "?page=1");
      console.log ( history.state )
  })

