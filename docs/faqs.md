* How to start the project. 

    Run  in the root folder, with parameter for live editing.

    ```
    gradle run -t
    ```

    or run
    ```
    gradle run :main-module:run
    ```

* How to build the persistence module.
    ```
    gradle persistence-module:build
    ```

* How is the architecture.
  * webserver-module 
        
        Is a wrapper over javalin-framework , trying to mimic MVC.net framework.

  * persistence-module
        
        used for storage
        where persistence model live

  * main-module
    
        where business application controllers lives

* Where is the template engine.
    
    // TODO

* How save data on BDD
    
    // TODO

* Where are the samples
    
    // TODO

* Where are the tests
    
    // TODO

* How to return simple text

    ```
    use class StringResponse(UiComponent)
    ```

* How to return html text

    ```
    use class HtmlResponse(UiComponent)
    ```
