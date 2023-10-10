package mutiitu.blog.services;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.mutiitu.dao.BlogEntryDao;
import com.mutiitu.dao.MigrateDatabase;
import com.mutiitu.dao.MigrateDatabaseImpl;
import com.mutiitu.domain.BlogEntryModel;
//import com.mutiitu.persistence.SQLiteDB;

import jakarta.transaction.Transactional;

public class TestService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    // @Inject
    // SQLiteDB SQLiteDB;

    @Inject
    BlogEntryDao blogEntryDao;

    @Inject
    MigrateDatabase migrateDatabase;

    public BlogEntryModel TestA() {
        // create database
        // var migrate = new MigrateDatabaseImpl();
        // migrate.create();
        //
        // var authorCrud = new
        // PersistenceFactory<AuthorModel>().create(AuthorModel.class);
        // var author = new AuthorModel();
        // author.setSurname("Pepe");
        // author.setName("Pepona");
        // authorCrud.insert(author);
        //
        // var blogEntryCrud = new
        // PersistenceFactory<BlogEntryModel>().create(BlogEntryModel.class);
        // var blogEntry = new BlogEntryModel();
        // blogEntry.setTitle("title");
        // blogEntry.setSubtitle("title");
        // blogEntry.setAuthorId(1);
        // blogEntryCrud.insert(blogEntry);
        //
        // var hh = authorCrud.getById(1);
        // System.out.println(hh);
        //
        // var blog = blogEntryCrud.getById(1);
        // System.out.println("---------------------");
        // System.out.println(blog.getAuthor());

        // var author2 = new AuthorDao();
        // var author = new AuthorModel();
        // author.setName("de 19");
        // author.setSurname("de 19");
        // author2.insert(author);

        // var tx = SQLiteDB.getTransactionManager();
        try {
            // tx.begin();

            // create database
            migrateDatabase.create();

            var mm = new BlogEntryModel();
            mm.setAuthorId(1);
            mm.setTitle("jkjgfl");
            mm.setSubtitle("jkjgfl");
            mm.setContent(
                    """
                                          <h1 class="fs-3xl m:fs-4xl l:fs-5xl fw-bold s:fw-heavy lh-tight mb-2 long">
                                          How to convert an HTML string into real HTML or DOM using JavaScript?
                                        </h1>

                                          <div class="spec__tags flex flex-wrap">
                                              <a class="crayons-tag   " style="
                                    --tag-bg: rgba(247, 223, 30, 0.10);
                                    --tag-prefix: #f7df1e;
                                    --tag-bg-hover: rgba(247, 223, 30, 0.10);
                                    --tag-prefix-hover: #f7df1e;
                                  " href="/t/javascript"><span class="crayons-tag__prefix">#</span>javascript</a>
                                          </div>
                                      </div>
                                    </header>

                                    <div class="crayons-article__main">

                                      <div class="crayons-article__body text-styles spec__body" data-article-id="694072" id="article-body">
                                        <p><a href="https://melvingeorge.me/blog/convert-html-string-into-real-html-or-dom-javascript">Originally posted here!</a></p>

                            <p>To convert an HTML string into real HTML or DOM, you can use the <code>DOMParser</code> Web API using JavaScript. The <code>DOMParser</code> helps us to parse HTML or XML string into real Document or DOM nodes.</p>

                            <h3>
                              <a name="tldr" href="#tldr">
                              </a>
                              TL;DR
                            </h3>



                            <div class="highlight js-code-highlight">
                            <pre class="highlight javascript"><code><span class="c1">// html string</span>
                            <span class="kd">const</span> <span class="nx">htmlStr</span> <span class="o">=</span> <span class="dl">"</span><span class="s2">&lt;h1&gt;Hello World!&lt;/h1&gt;</span><span class="dl">"</span><span class="p">;</span>

                            <span class="c1">// make a new parser</span>
                            <span class="kd">const</span> <span class="nx">parser</span> <span class="o">=</span> <span class="k">new</span> <span class="nx">DOMParser</span><span class="p">();</span>

                            <span class="c1">// convert html string into DOM</span>
                            <span class="kd">const</span> <span class="nb">document</span> <span class="o">=</span> <span class="nx">parser</span><span class="p">.</span><span class="nx">parseFromString</span><span class="p">(</span><span class="nx">htmlStr</span><span class="p">,</span> <span class="dl">"</span><span class="s2">text/html</span><span class="dl">"</span><span class="p">);</span>
                            </code></pre>
                            <div class="highlight__panel js-actions-panel">
                            <div class="highlight__panel-action js-fullscreen-code-action">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20px" height="20px" viewbox="0 0 24 24" class="highlight-action crayons-icon highlight-action--fullscreen-on"><title>Enter fullscreen mode</title>
                                <path d="M16 3h6v6h-2V5h-4V3zM2 3h6v2H4v4H2V3zm18 16v-4h2v6h-6v-2h4zM4 19h4v2H2v-6h2v4z"></path>
                            </svg>

                                <svg xmlns="http://www.w3.org/2000/svg" width="20px" height="20px" viewbox="0 0 24 24" class="highlight-action crayons-icon highlight-action--fullscreen-off"><title>Exit fullscreen mode</title>
                                <path d="M18 7h4v2h-6V3h2v4zM8 9H2V7h4V3h2v6zm10 8v4h-2v-6h6v2h-4zM8 15v6H6v-4H2v-2h6z"></path>
                            </svg>

                            </div>
                            </div>
                            </div>



                            <p>For example, let's say you have a HTML string of <code>h1</code> tag with the text of <code>Hello World!</code> like this,<br>
                            </p>

                            <div class="highlight js-code-highlight">
                            <pre class="highlight javascript"><code><span class="c1">// html string</span>
                            <span class="kd">const</span> <span class="nx">htmlStr</span> <span class="o">=</span> <span class="dl">"</span><span class="s2">&lt;h1&gt;Hello World!&lt;/h1&gt;</span><span class="dl">"</span><span class="p">;</span>
                            </code></pre>
                            <div class="highlight__panel js-actions-panel">
                            <div class="highlight__panel-action js-fullscreen-code-action">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20px" height="20px" viewbox="0 0 24 24" class="highlight-action crayons-icon highlight-action--fullscreen-on"><title>Enter fullscreen mode</title>
                                <path d="M16 3h6v6h-2V5h-4V3zM2 3h6v2H4v4H2V3zm18 16v-4h2v6h-6v-2h4zM4 19h4v2H2v-6h2v4z"></path>
                            </svg>

                                <svg xmlns="http://www.w3.org/2000/svg" width="20px" height="20px" viewbox="0 0 24 24" class="highlight-action crayons-icon highlight-action--fullscreen-off"><title>Exit fullscreen mode</title>
                                <path d="M18 7h4v2h-6V3h2v4zM8 9H2V7h4V3h2v6zm10 8v4h-2v-6h6v2h-4zM8 15v6H6v-4H2v-2h6z"></path>
                            </svg>

                            </div>
                            </div>
                            </div>



                            <p>Now, to convert this string into a real HTML tag we can use the <code>DOMParser</code> web API.</p>

                            <p>So first, we have to make a parser using the <code>new</code> keyword like this,<br>
                            </p>

                            <div class="highlight js-code-highlight">
                            <pre class="highlight javascript"><code><span class="c1">// html string</span>
                            <span class="kd">const</span> <span class="nx">htmlStr</span> <span class="o">=</span> <span class="dl">"</span><span class="s2">&lt;h1&gt;Hello World!&lt;/h1&gt;</span><span class="dl">"</span><span class="p">;</span>

                            <span class="c1">// make a new parser</span>
                            <span class="kd">const</span> <span class="nx">parser</span> <span class="o">=</span> <span class="k">new</span> <span class="nx">DOMParser</span><span class="p">();</span>
                            </code></pre>
                            <div class="highlight__panel js-actions-panel">
                            <div class="highlight__panel-action js-fullscreen-code-action">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20px" height="20px" viewbox="0 0 24 24" class="highlight-action crayons-icon highlight-action--fullscreen-on"><title>Enter fullscreen mode</title>
                                <path d="M16 3h6v6h-2V5h-4V3zM2 3h6v2H4v4H2V3zm18 16v-4h2v6h-6v-2h4zM4 19h4v2H2v-6h2v4z"></path>
                            </svg>

                                <svg xmlns="http://www.w3.org/2000/svg" width="20px" height="20px" viewbox="0 0 24 24" class="highlight-action crayons-icon highlight-action--fullscreen-off"><title>Exit fullscreen mode</title>
                                <path d="M18 7h4v2h-6V3h2v4zM8 9H2V7h4V3h2v6zm10 8v4h-2v-6h6v2h-4zM8 15v6H6v-4H2v-2h6z"></path>
                            </svg>

                            </div>
                            </div>
                            </div>



                            <p>After that, we can use the <code>parseFromString()</code> method in the <code>parser</code> object and pass:</p>

                            <ul>
                            <li>the raw HTML string as the first argument</li>
                            <li>and the <code>mime</code> type or the type of the document contained in the string as the second argument. In our case, the mime-type value is <code>text/html</code>.</li>
                            </ul>

                            <p>There are other mime types we can use such as:</p>

                            <ul>
                            <li><code>text/xml</code></li>
                            <li><code>application/xml</code></li>
                            <li><code>application/xhtml+xml</code></li>
                            <li><code>image/svg+xml</code></li>
                            </ul>

                            <p>So it will look like this,<br>
                            </p>

                            <div class="highlight js-code-highlight">
                            <pre class="highlight javascript"><code><span class="c1">// html string</span>
                            <span class="kd">const</span> <span class="nx">htmlStr</span> <span class="o">=</span> <span class="dl">"</span><span class="s2">&lt;h1&gt;Hello World!&lt;/h1&gt;</span><span class="dl">"</span><span class="p">;</span>

                            <span class="c1">// make a new parser</span>
                            <span class="kd">const</span> <span class="nx">parser</span> <span class="o">=</span> <span class="k">new</span> <span class="nx">DOMParser</span><span class="p">();</span>

                            <span class="c1">// convert html string into DOM</span>
                            <span class="kd">const</span> <span class="nb">document</span> <span class="o">=</span> <span class="nx">parser</span><span class="p">.</span><span class="nx">parseFromString</span><span class="p">(</span><span class="nx">htmlStr</span><span class="p">,</span> <span class="dl">"</span><span class="s2">text/html</span><span class="dl">"</span><span class="p">);</span>
                            </code></pre>
                            <div class="highlight__panel js-actions-panel">
                            <div class="highlight__panel-action js-fullscreen-code-action">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20px" height="20px" viewbox="0 0 24 24" class="highlight-action crayons-icon highlight-action--fullscreen-on"><title>Enter fullscreen mode</title>
                                <path d="M16 3h6v6h-2V5h-4V3zM2 3h6v2H4v4H2V3zm18 16v-4h2v6h-6v-2h4zM4 19h4v2H2v-6h2v4z"></path>
                            </svg>

                                <svg xmlns="http://www.w3.org/2000/svg" width="20px" height="20px" viewbox="0 0 24 24" class="highlight-action crayons-icon highlight-action--fullscreen-off"><title>Exit fullscreen mode</title>
                                <path d="M18 7h4v2h-6V3h2v4zM8 9H2V7h4V3h2v6zm10 8v4h-2v-6h6v2h-4zM8 15v6H6v-4H2v-2h6z"></path>
                            </svg>

                            </div>
                            </div>
                            </div>



                            <p>Now the HTML string is converted to an HTML DOM node. You can now use the usual methods and properties available on a DOM node such as <code>appendChild()</code>, <code>classList</code>, etc.</p>

                            <p>See the above code live in <a href="https://jsbin.com/zabahepozo/2/edit?js,console">JSBin</a>.</p>

                            <p>That's all 😃!</p>

                            <h3>
                              <a name="feel-free-to-share-if-you-found-this-useful-" href="#feel-free-to-share-if-you-found-this-useful-">
                              </a>
                              Feel free to share if you found this useful 😃.
                            </h3>

                                                """);
            blogEntryDao.insert(mm);

            // var result = tx.required(
            // () -> {
            // return BlogEntryDao.getWithAuthorsById(2);
            // });

            var result = blogEntryDao.getWithAuthorsById(7);

            System.out.println("---------------------");
            System.out.println(result.getTitle());
            System.out.println(result.getAuthorId());

            return result;

            // var blogEntity = blogDao2.getWithAuthorsById(2);

            // System.out.println("---------------------");
            // System.out.println(blogEntity.getTitle());
            // System.out.println(blogEntity.getAuthorId());

        } catch (Exception ex) {
            logger.error(null, ex);
            throw ex;
        } finally {
            // tx.getTransaction().rollback();

        }

    }
}
