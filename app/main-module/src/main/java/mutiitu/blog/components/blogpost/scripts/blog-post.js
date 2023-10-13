import { getAttributes } from '/components/_framework/framework.js';

export class BlogPost extends HTMLElement { 
    title = undefined;
    subtitle = undefined;
    content = undefined;

    constructor() {
        super();
        this.attachShadow({ mode: 'open' })
        loadCSSFrom(['/css/global-style.css', '/components/blogpost/css/blog-post.css'], this.shadowRoot)
      }
    
      connectedCallback() {
        const attr = getAttributes(this, 'title', 'subtitle', 'content');
        Object.assign(this, attr);

        this.shadowRoot.innerHTML = 
        /*html*/
      ` <div class="post">
          <h2 class="title">${this.title}</h2>
          <h2 class="subtitle">${this.subtitle}</h2>
          <div class="content">${this.content}</div>
        </div>`;
      }
  }

customElements.define('mu-blog-post', BlogPost);
