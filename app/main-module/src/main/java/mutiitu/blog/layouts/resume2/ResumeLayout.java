package mutiitu.blog.layouts.resume2;

import com.google.inject.Inject;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import mutiitu.blog.components.card.CardUIComponent;
import mutiitu.blog.components.markdown.MarkdownUIComponent;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResumeLayout {

    @Inject
    CardUIComponent cardUIComponent;

    @Inject
    MarkdownUIComponent markdownUIComponent;

    @Inject
    MarkdownUIComponent markdownUIComponent2;


    public HttpResponse render() {
        try {

            markdownUIComponent.init(content);
            markdownUIComponent2.init(content2);
            
            // "/mutiitu/blog/layouts/home/HomeLayout.html"
            // TODO: GC
            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = engine.getTemplate("mutiitu/blog/layouts/resume/ResumeLayout.html");
            Map<String, Object> context = new HashMap<>();

            var items = new ArrayList<String>();
            items.add("a ");
            items.add("b ");
            items.add("bfdjfdslfjldf  ");

            context.put("name", "Mitchell");

            context.put("items", items);
            context.put("card", cardUIComponent);
            context.put("markdown", markdownUIComponent);
            context.put("markdown2", markdownUIComponent2);
            context.put("document", content);

            Writer writer = new StringWriter();

            compiledTemplate.evaluate(writer, context);
            String output = writer.toString();
            return new HtmlResponse(output);

        } catch (Exception ex) {
            // TODO: errors
            ex.printStackTrace();
            return new StringResponse("output");
        }
    }

    public String content = """
        Proof of Concept (POC) 

        # Este proyecto es un experimento usando el framework de Javalin. La intención era usar su mínima infraestrucutura para implementar las mismas funcionalidaes con otros "web frameworks"
        
        * El frontal de aa aplicación muestra una frontal con scroll infinito de entradas de blog.
        * El backoffice deberé permitir dar de alta un blog /admin/add/blog 
        
        La intención es a medida desarrollemos ser conscientes de todas las técnicas necesarias  que disponemos normalmente de forma más fácil en otros web frameworks. Enumeremos algunas de ellas.
            * Rutas y gestion de rutas, soporte de métodos.
            * Validación de la entrada.
            * Serializacion
            * Object mapping
              * Serialización y deserialización
            * Autenticación
            * Inyeccion de dependencias
            * Middlewares
            * Soporte de plantillas
            * Integracion con SPA
            * Persistencia de datos
              * Orm
              * SQL
              * ...
            * Entorno transaccional
              * AOP
            * Inyección de dependencias
            * Facilidad de desarrollo
              * Hot reloading
              * Depuración
              * Mínimo código de infraestructura
              * ...
            * Rendimiento
              * Numeros de peticiones concurrentes
              * Velocidad
              * ...
            * Gestion de errores
            * Soporte para distintos tipos de transferencia
              * json
              * xml
              * binary
            * WebSockets
            * Versiones de Http/3
            * Https
        
            * ...
        
        # Serie de Implementación de Blogs con Diferentes Tecnologías
        
        Esta serie de artículos técnicos tiene como objetivo explorar y comparar diversas tecnologías para la implementación de un blog. A lo largo de esta serie, demostraremos cómo crear un blog funcional utilizando diferentes lenguajes de programación, frameworks y herramientas populares en el mundo del desarrollo web.
        
        ## Contenido de la Serie
        
        - [Artículo 1: Implementación del Blog con [Tecnología 1]](enlace-al-articulo-1.md)
        - [Artículo 2: Implementación del Blog con [Tecnología 2]](enlace-al-articulo-2.md)
        - [Artículo 3: Implementación del Blog con [Tecnología 3]](enlace-al-articulo-3.md)
        - [Artículo 4: Comparación y Conclusiones](enlace-al-articulo-4.md)
        
        Cada artículo de la serie aborda una tecnología específica y proporciona un tutorial paso a paso sobre cómo crear un blog utilizando esa tecnología. También destacamos las ventajas y desventajas de cada enfoque, lo que te permitirá tomar decisiones informadas en futuros proyectos de desarrollo web.
        
        ## Objetivos del Proyecto
        
        - Ayudar a los desarrolladores a comprender y evaluar diferentes tecnologías para proyectos web.
        - Ofrecer ejemplos prácticos y orientación detallada para la implementación de un blog con cada tecnología.
        - Facilitar la toma de decisiones al destacar las fortalezas y debilidades de cada enfoque.
        
        ## Cómo Contribuir
        
        ¡Todas las contribuciones son bienvenidas! Si deseas agregar una nueva tecnología a la serie, mejorar la documentación existente o corregir errores, simplemente sigue estos pasos:
        
        1. [Fork](https://github.com/tu-usuario/tu-fork) este repositorio.
        2. Crea una rama con el nombre de tu característica o corrección (`git checkout -b mi-nueva-tecnologia`).
        3. Realiza tus cambios y asegúrate de seguir las guías de estilo y las convenciones de formato.
        4. Realiza un [pull request](https://github.com/tu-usuario/proyecto/pulls) a la rama principal de este repositorio.
        
        ## Licencia
        
        Este proyecto se encuentra bajo la licencia [Nombre de la Licencia]. Consulta el archivo [LICENSE](LICENSE) para obtener más detalles.
                       """;

        String content2 = """
            # Curriculum Vitae

            ## Información de Contacto
            
            - **Nombre:** [Tu Nombre]
            - **Ubicación:** [Ubicación]
            - **Teléfono:** [Número de Teléfono]
            - **Email:** [Tu Correo Electrónico]
            - **Sitio Web:** [Tu Sitio Web o Perfil de LinkedIn]
            
            ## Resumen Profesional
            
            Soy un Full Stack Developer con amplia experiencia en el desarrollo de aplicaciones web y móviles. Mi enfoque se centra en la creación de soluciones técnicas eficientes y de alta calidad. Tengo habilidades sólidas tanto en el desarrollo frontend como en el backend, lo que me permite trabajar en proyectos completos desde la concepción hasta la implementación. Estoy apasionado por aprender nuevas tecnologías y resolviendo desafíos técnicos.
            
            ## Experiencia Laboral
            
            ### Full Stack Developer en [Nombre de la Empresa]
            - **Fechas:** [Fecha de Inicio] - [Fecha de Finalización]
            - Desarrollo y mantenimiento de aplicaciones web y móviles.
            - Colaboración en proyectos de equipo, comunicación con stakeholders.
            - Implementación de soluciones tecnológicas eficientes.
            
            ### Frontend Developer en [Nombre de la Empresa]
            - **Fechas:** [Fecha de Inicio] - [Fecha de Finalización]
            - Diseño y desarrollo de interfaces de usuario responsivas.
            - Implementación de características interactivas y amigables para el usuario.
            - Optimización de la velocidad y rendimiento del sitio web.
            
            ### Backend Developer en [Nombre de la Empresa]
            - **Fechas:** [Fecha de Inicio] - [Fecha de Finalización]
            - Diseño y desarrollo de APIs y servicios web.
            - Manejo de bases de datos y optimización de consultas.
            - Implementación de lógica de negocio y seguridad.
            
            ## Educación
            
            ### Licenciatura en Informática
            - **Institución:** [Nombre de la Universidad]
            - **Fecha de Graduación:** [Fecha de Graduación]
            
            ## Habilidades Técnicas
            
            - Lenguajes de Programación: JavaScript, Python, Java
            - Tecnologías Frontend: HTML, CSS, React, Angular
            - Tecnologías Backend: Node.js, Express, Django
            - Bases de Datos: MongoDB, MySQL, PostgreSQL
            - Herramientas de Control de Versiones: Git
            - Metodologías Ágiles: Scrum, Kanban
            - Despliegue y Hosting: Docker, AWS, Heroku

            
            | Habilidades Técnicas                 |                           |
            |--------------------------------------|---------------------------|
            | **Lenguajes de Programación**        | JavaScript, Python, Java |
            | **Tecnologías Frontend**             | HTML, CSS, React, Angular |
            | **Tecnologías Backend**              | Node.js, Express, Django  |
            | **Bases de Datos**                   | MongoDB, MySQL, PostgreSQL |
            | **Herramientas de Control de Versiones** | Git                   |
            | **Metodologías Ágiles**              | Scrum, Kanban             |
            | **Despliegue y Hosting**             | Docker, AWS, Heroku       |

            
            ## Proyectos Destacados
            
            ### [Nombre del Proyecto]
            - **Descripción:** Breve descripción del proyecto.
            - **Tecnologías Utilizadas:** Lista de tecnologías utilizadas.
            - **Enlace al Proyecto:** [Enlace al Proyecto en GitHub o sitio web]
            
            ### [Nombre del Proyecto]
            - **Descripción:** Breve descripción del proyecto.
            - **Tecnologías Utilizadas:** Lista de tecnologías utilizadas.
            - **Enlace al Proyecto:** [Enlace al Proyecto en GitHub o sitio web]
            
            ## Certificaciones
            
            - [Nombre de la Certificación]: [Fecha de Obtención]
            - [Nombre de la Certificación]: [Fecha de Obtención]
            
            ## Idiomas
            
            - [Idioma]: Nivel Avanzado
            - [Idioma]: Nivel Intermedio
            
            ## Intereses
            
            - Desarrollo Web y Móvil
            - Tecnologías Emergentes
            - Aprendizaje Continuo
            
            ## Referencias
            
            Disponibles a petición.
            
                            """;

}
