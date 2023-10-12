documenta configuracion
    vscode
        java language suport for Java(TM) By RedHat

    formater    
        da las gracias a :
            https://dev.to/marcushellberg/how-to-configure-vs-code-java-formatting-1p10
            acuerda de settings

    installa gradle en la imagen 
        el build        

    evidencias rendimiento
        mysql ( threads abiertos )
            show status where variable_name = 'threads_connected';
            

Como ver el log en docker:
    docker container logs mutiitu-blog-java-my-java-app-1 --tail 200 --follow 2>&1 | grep "Add instance to thread"

Como liberar memoria de Java:
    sh-4.2$ jcmd
        66 org.gradle.launcher.daemon.bootstrap.GradleDaemon 8.4
        307 worker.org.gradle.process.internal.worker.GradleWorkerMain 'Gradle Worker Daemon 1'
        457 org.gradle.launcher.GradleMain run -DDEBUG=true -t --args=-mortadelo -filemon
        1517 mutiitu.blog.Application -mortadelo -filemon
        1711 jdk.jcmd/sun.tools.jcmd.JCmd

        jcmd 1517 GC.run

        jmap -histo:live,file=/tmp/histo.data 1517

        jmap -histo:live 457 | grep "mutiitu"

Como inspeccionar los objetos creados:
    sh-4.2$ jmap -histo:live 3589 | grep "mutiitu"
 416:             8            192  com.mutiitu.framework.core.JavalinHandler
 490:             1            136  com.mutiitu.framework.core.FileWatcher
 675:             3             72  com.mutiitu.persistence.SQLiteDBScope$$Lambda/0x00007f19200ccf20
 735:             1             64  com.mutiitu.domain._BlogEntryModel
 949:             1             40  mutiitu.blog.components.Header2
 950:             1             40  mutiitu.blog.components.Header3
1038:             1             32  com.mutiitu.framework.core.ApplicationStarter
1228:             1             24  com.mutiitu.persistence.SQLiteDBScope
1291:             1             24  mutiitu.blog.controllers.BlogController
1436:             1             16  com.mutiitu.dao.BlogEntryDao$$FastClassByGuice$$279568
1437:             1             16  com.mutiitu.framework.core.ApplicationStarter$$FastClassByGuice$$4f96a
1438:             1             16  com.mutiitu.framework.core.ApplicationStarter$$Lambda/0x00007f19201335f0
1439:             1             16  com.mutiitu.framework.core.AutoShutdownPlugin
1440:             1             16  com.mutiitu.framework.core.AutoShutdownPlugin$$Lambda/0x00007f19200968a8
1441:             1             16  com.mutiitu.framework.core.di.CoreModule$$Lambda/0x00007f1920038f68
1442:             1             16  com.mutiitu.persistence.SQLiteDB$$FastClassByGuice$$13a2be
1443:             1             16  com.mutiitu.persistence.SQLiteDBScope$$Lambda/0x00007f19200b0ee8
1775:             1             16  mutiitu.blog.components.Header
1776:             1             16  mutiitu.blog.components.Header$$FastClassByGuice$$337659
1777:             1             16  mutiitu.blog.components.Header2$$FastClassByGuice$$49a379
1778:             1             16  mutiitu.blog.components.Header3$$FastClassByGuice$$502734
1779:             1             16  mutiitu.blog.components.HeaderServiceImpl
1780:             1             16  mutiitu.blog.components.HeaderServiceImpl$$FastClassByGuice$$72dae4
1781:             1             16  mutiitu.blog.controllers.BlogController$$FastClassByGuice$$853d44
1782:             1             16  mutiitu.blog.controllers.BlogController$$Lambda/0x00007f1920133810
1783:             1             16  mutiitu.blog.controllers.admin.AdminController$$FastClassByGuice$$9c79b1
1784:             1             16  mutiitu.blog.services.TestService$$FastClassByGuice$$67849e

Como entrar en la máquina docker:
 docker container exec -it mutiitu-blog-java-my-java-app-1 sh


Un articulo sobre grunt??
    Este repositorio es realmente chulo.
    https://github.com/medialize/playground.sass.js/blob/master/tasks/copy.js

Articulo sobre extender pebble ??
    https://pebbletemplates.io/wiki/guide/extending-pebble/

Articulo sobre como los linters, los spell checking y la resolucion de dependencias con intellisense ayudan a minimizar los errores de código. ??
    * ex: nomenclatura. 
        * snake
          * etc.. mu-blog-post

Gestor de dependencias en el navegador
    Vengo de : jspn
    https://stackoverflow.com/questions/63179813/how-to-run-the-monaco-editor-from-a-cdn-like-cdnjs
    ```
    <script type="module">
    import * as monaco from 'https://cdn.jsdelivr.net/npm/monaco-editor@0.39.0/+esm';

    monaco.editor.create(document.querySelector('.monaco'));
    </script>
    <div class="monaco" style="min-height: 100px"></div>
    ```

Quizás crear una sintaxis para pebble que le falta a VS ?
    https://microsoft.github.io/monaco-editor/monarch.html
