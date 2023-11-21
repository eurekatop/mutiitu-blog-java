package mutiitu.blog.controllers;

import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.mutiitu.annotations.Transactional;
import com.mutiitu.dao.BlogEntryDao;
import com.mutiitu.dao.ContactMeDao;
import com.mutiitu.dao.MigrateDatabase;
import com.mutiitu.dao.MigrateDatabaseImpl;
import com.mutiitu.domain.ContactMeModel;
import com.mutiitu.framework.core.JavalinController;
import com.mutiitu.framework.core.annotations.Controller;
import com.mutiitu.framework.core.annotations.Method;
import com.mutiitu.framework.core.annotations.Path;
import com.mutiitu.framework.core.http.responses.HtmlResponse;
import com.mutiitu.framework.core.http.responses.HttpResponse;
import com.mutiitu.framework.core.http.responses.StringResponse;
import com.mutiitu.persistence.PersistenceFactory;
import com.mutiitu.framework.core.http.responses.JsonResponse;


import mutiitu.blog.components._model.AuthorUIModel;
import mutiitu.blog.components._model.BlogPostUIModel;
import mutiitu.blog.components._model.CardUIModel;
import mutiitu.blog.components.blogpost.BlogPostUIComponent;
import mutiitu.blog.components.card.CardUIComponent;
import mutiitu.blog.components.editor.EditorUIComponent;
import mutiitu.blog.components.home.HomePage;
import mutiitu.blog.components.infinitescroll.InfiniteScrollUIComponent;
import mutiitu.blog.components.markdown.MarkdownUIComponent;
import mutiitu.blog.layouts.components.ComponentsLayout;
import mutiitu.blog.layouts.home.ContactMeLayout;
import mutiitu.blog.layouts.home.HomeLayout;
import mutiitu.blog.layouts.resume.ResumeLayout;
import mutiitu.blog.models.dto.BlogEntryInputDto;
import mutiitu.blog.services.BlogEntryService;

@Controller
public class HomePageController extends JavalinController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    // TODO: inject depenencies in function

    @Inject
    private HomePage homePage;
    @Inject
    private CardUIComponent cardUIComponent;

    @Inject
    private BlogEntryService blogEntryService;

    @Inject
    HomeLayout homeLayout;

    @Inject
    ResumeLayout resumeLayout;

    @Inject
    ComponentsLayout componentsLayout;

    @Inject
    ContactMeLayout contactMeLayout;


    @Inject
    MigrateDatabase migrateDatabase;

    @Inject
    ContactMeDao contactMeDao;




    @Path(Value = "/")
    @Method(Value = "GET")
    public HttpResponse Home() {

        try {
            return homeLayout.render();
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }



    @Path(Value = "/resume")
    @Method(Value = "GET")
    public HttpResponse Resume() {

        try {
            return resumeLayout.render();
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }


    @Path(Value = "/contact/me")
    @Method(Value = "POST") 
    @Transactional
    public HttpResponse ContactMe() {

        try {
            String name = ctx.formParam("name");
            String email = ctx.formParam("email");
            String subject = ctx.formParam("subject");
            String message = ctx.formParam("message");

            logger.info ( "CONTACTO!!!  name " + name  );
            logger.info ( "CONTACTO!!!  email " + email  );
            logger.info ( "CONTACTO!!!  subject " + subject  );
            logger.info ( "CONTACTO!!!  message " + message  );


//            var migrate = new MigrateDatabaseImpl();


            var contact = new ContactMeModel();
            contact.setEmail(email);
            contact.setMessage(message);
            contact.setName(name);
            contact.setSubject(subject);
            contactMeDao.insert(contact);


    //    var c = contactMeDao.getContactMes();
    //    for (ContactMeModel contactMeModel : c) {
    //        logger.info ( contactMeModel.getName());
    //    }


            return contactMeLayout.render();
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }



    @Path(Value = "/components")
    @Method(Value = "GET")
    public HttpResponse Components() {

        try {
            return componentsLayout.render();
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }


    @Transactional
    @Path(Value = "/homepage")
    @Method(Value = "GET")
    public HttpResponse homePage() {

        try {
            var blogIds = blogEntryService.GetAllBlogIds();
            var blogs = blogEntryService.GetBlogs(10);

            homePage.init("Home Page", blogIds, blogs);

            return new HtmlResponse(homePage);
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }

    @Path(Value = "/card")
    @Method(Value = "GET")
    public HttpResponse Card() {
        try {

            // TODO: change pebble template call constructor with some syntax
            var card = new CardUIModel();
            card.title = "com xerra";
            card.content = "com xerra";
            card.subtitle = "fdfds";

            cardUIComponent.init(card);

            return new HtmlResponse(cardUIComponent);
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }

    @Path(Value = "/blogpost")
    @Method(Value = "GET")
    public HttpResponse BlogPost() {
        try {

            // TODO: change pebble template call constructor with some syntax
            var author = new AuthorUIModel();
            author.name = "ds";
            author.surname = "fdds";

            var blog = new BlogPostUIModel();
            blog.title = "THFGDSGD ";
            blog.subtitle = "dff ds";
            blog.content = "Thfjds fdsf hds";

            var blogUiComponent = new BlogPostUIComponent();
            blogUiComponent.init(blog, author);

            return new HtmlResponse(blogUiComponent);
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }

    @Path(Value = "/infinitescroll")
    @Method(Value = "GET")
    public HttpResponse InfiniteScroll() {
        try {

            var infiniteScroll = new InfiniteScrollUIComponent();
            // infiniteScroll.init(blog, author);

            return new HtmlResponse(infiniteScroll);
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }

    @Path(Value = "/markdown")
    @Method(Value = "GET")
    public HttpResponse Markdown() {
        try {

            var uic = new MarkdownUIComponent();
            uic.init("markdown\n" + //
                    "Copy code\n" + //
                    "# Creación de un Store Reactivo con Histórico Utilizando RxJS y la Clase CoreStore\n" + //
                    "\n" + //
                    "*Por [Tu Nombre]*\n" + //
                    "\n" + //
                    "![RxJS](https://www.tunombre.com/images/rxjs.jpg)\n" + //
                    "\n" + //
                    "## Introducción\n" + //
                    "\n" + //
                    "En el emocionante mundo del desarrollo de aplicaciones, la gestión eficiente de datos y su observación en tiempo real son esenciales. RxJS, una biblioteca de programación reactiva para JavaScript, ofrece una solución poderosa para este desafío. En este artículo, exploraremos cómo utilizar RxJS para construir un store reactivo con histórico, utilizando una clase ingeniosa llamada CoreStore.\n" + //
                    "\n" + //
                    "## ¿Qué es un Store Reactivo con Histórico?\n" + //
                    "\n" + //
                    "Un store reactivo es una pieza clave en el desarrollo de aplicaciones modernas. Sirve como el centro de mando para el almacenamiento y la gestión de datos, lo que facilita su actualización y observación en toda la aplicación. Un store reactivo con histórico lleva las cosas un paso más allá al registrar un historial de cambios en los datos, lo que resulta invaluable para el seguimiento de cambios en el estado de la aplicación a lo largo del tiempo.\n" + //
                    "\n" + //
                    "## Explorando la Clase CoreStore\n" + //
                    "\n" + //
                    "Nuestro viaje comienza con la clase CoreStore, que es el núcleo de nuestra solución para un store reactivo con histórico. Vamos a sumergirnos en las características más destacadas de esta clase:\n" + //
                    "\n" + //
                    "```javascript\n" + //
                    "import { Observable, ReplaySubject, bufferCount, concat, distinctUntilChanged, firstValueFrom, map, skip, takeUntil } from 'rxjs';\n" + //
                    "\n" + //
                    "export class CoreStore<T> {\n" + //
                    "  // Propiedades iniciales\n" + //
                    "  protected _windowSize = 10;\n" + //
                    "  protected _countEmittedData: { [index: string] : number} = {}\n" + //
                    "  protected _dataSubjects: { [index: string]: ReplaySubject<T> } = {};\n" + //
                    "\n" + //
                    "  // Otros métodos\n" + //
                    "  // ...\n" + //
                    "}\n" + //
                    "Constructor y Propiedades Iniciales: En la clase CoreStore, comenzamos definiendo propiedades iniciales, como _windowSize, que establece el tamaño del historial, y _dataSubjects, que almacena los datos observables.\n" + //
                    "\n" + //
                    "Método set: El método set permite establecer el valor de un elemento de datos identificado por una clave específica. Este método crea un observable si no existe uno para la clave y emite el nuevo valor a los suscriptores.\n" + //
                    "\n" + //
                    "Método setProp: El método setProp permite actualizar una propiedad específica del estado asociado a una clave. También crea un observable si no existe uno y emite el estado actualizado.\n" + //
                    "\n" + //
                    "Método get: El método get se utiliza para recuperar el valor más reciente de un elemento de datos identificado por una clave. El método devuelve una promesa que se resuelve con el valor o undefined si el elemento no existe.\n" + //
                    "\n" + //
                    "Método getObservable: Este método devuelve un observable para un elemento de datos específico, lo que permite a los suscriptores observar las actualizaciones del valor.\n" + //
                    "\n" + //
                    "Método observeProp: observeProp crea un observable que se utiliza para escuchar cambios en una propiedad específica del estado. Los valores se emiten solo si la propiedad cambia, lo que ahorra tiempo y recursos.\n" + //
                    "\n" + //
                    "Método debug: Este método proporciona una forma de depurar el flujo de datos observados, emitiendo datos en bloques y agregando un contador a cada emisión para el seguimiento.\n" + //
                    "\n" + //
                    "Uso de RxJS para Reactividad\n" + //
                    "La clase CoreStore aprovecha las capacidades de RxJS para gestionar y observar datos de manera reactiva. Algunos operadores RxJS, como bufferCount, skip, y distinctUntilChanged, se utilizan para controlar el flujo de datos y garantizar que los suscriptores reciban actualizaciones relevantes.\n" + //
                    "\n" + //
                    "Conclusión\n" + //
                    "La clase CoreStore proporciona una base sólida para la creación de un store reactivo con histórico en aplicaciones JavaScript utilizando RxJS. Esta implementación permite un control detallado sobre los datos y su historial, lo que puede ser fundamental para aplicaciones que requieren un seguimiento preciso de los cambios en el estado. La combinación de RxJS y esta clase ofrece una solución eficiente y robusta para la gestión de datos reactivos en aplicaciones modernas.\n" + //
                    "\n" + //
                    "En resumen, RxJS y CoreStore son aliados poderosos en la construcción de aplicaciones reactivas con historial de cambios. Aprovechar estas herramientas puede marcar la diferencia en la forma en que gestionamos y observamos datos en nuestras aplicaciones, brindando una experiencia más fluida y eficiente para los usuarios.\n" + //
                    "\n" + //
                    "¡Espero que este artículo te haya proporcionado una visión sólida sobre cómo crear un store reactivo con histórico en JavaScript utilizando RxJS y la clase CoreStore! Si tienes alguna pregunta o comentario, no dudes en dejarlos a continuación.\n" + //
                    "\n" + //
                    "css\n" + //
                    "Copy code\n" + //
                    "\n" + //
                    "Solo asegúrate de reemplazar \"[Tu Nombre]\" por tu nombre real o el nombre del autor del artículo.");

            return new HtmlResponse(uic);
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }

    @Path(Value = "/editor")
    @Method(Value = "GET")
    public HttpResponse Editor() {
        try {

            var uic = new EditorUIComponent();
            uic.init("Hola melón");

            return new HtmlResponse(uic);
        } catch (Exception ex) {
            logger.error(null, ex);
            return new JsonResponse(ex);
        }
    }



}
